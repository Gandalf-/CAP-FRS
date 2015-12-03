package org.bellinghamcap.cap_frs;

import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument.Page;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.pdf.PrintedPdfDocument;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri imgUri;
    private Uri pdfUri;
    private Bitmap imgBitmap;
    File mediaStorageDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Set up the date picker dialog.
     *
     * Uses code from http://www.worldbestlearningcenter.com/tips/Android-date-picker-dialog.htm
     *
     */
    public void onStart(){
        super.onStart();

        EditText serviceDate = (EditText)findViewById(R.id.editServiceDate);
        EditText missionDate = (EditText)findViewById(R.id.editMissionDate);

        serviceDate.setOnFocusChangeListener(new OnFocusChangeListener(){
            public void onFocusChange(View view, boolean hasFocus){
                if(hasFocus){
                    DateDialog dialog = new DateDialog(view);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });

        missionDate.setOnFocusChangeListener(new OnFocusChangeListener(){
            public void onFocusChange(View view, boolean hasFocus){
                if(hasFocus){
                    DateDialog dialog = new DateDialog(view);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });
    }
    /**
     **********************************************************************************************
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_clearcache) {
            deleteMediaFiles();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Image captured and saved to fileUri specified in the Intent
                //Toast.makeText(this, "Image saved to:\n" + imgUri.toString(), Toast.LENGTH_LONG).show();
                Bitmap origBitmap = null;
                try {
                    origBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (origBitmap != null) {
                    imgBitmap = Bitmap.createScaledBitmap(origBitmap, origBitmap.getWidth()/3, origBitmap.getHeight()/3, true);
                }
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
                Toast.makeText(this, "Image capture failed", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void getPicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imgUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);

        // start image capture intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    public void sendEmail(View view) {
        // onClick handler for the send email button

        // if we haven't generated the pdf yet, do so
        if (pdfUri == null) {
            GeneratePDF();
        }
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        String email = ((EditText) findViewById(R.id.editEmail)).getText().toString();
        intent.setData(Uri.parse("mailto:" + email));
        intent.putExtra(Intent.EXTRA_SUBJECT, "CAP-FRS Document");
        intent.putExtra(Intent.EXTRA_TEXT, "Fuel receipt PDF document attached.");
        if (pdfUri != null) {
            // pdfUri would only be null if there was an error generating
            intent.putExtra(Intent.EXTRA_STREAM, pdfUri);
        }
        try {
            startActivity(Intent.createChooser(intent, "Sending email"));
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(this, "Error: no email clients installed.", Toast.LENGTH_LONG).show();
        }
    }

    // Used a template from android developers
    // at http://developer.android.com/guide/topics/media/camera.html#saving-media

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static final int MEDIA_TYPE_PDF = 3;

    /** Create a file Uri for saving an image or video or pdf */
    private Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video or pdf */
    private File getOutputMediaFile(int type){

        if (mediaStorageDir == null) {
            mediaStorageDir = new File(getExternalFilesDir(
                    Environment.DIRECTORY_PICTURES), "CAP_FRS");
        }
        // If the application is uninstalled, any files saved in this location are removed.
        // Security is not enforced for files in this location and other applications may read,
        // change and delete them.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("CAP_FRS", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else if (type == MEDIA_TYPE_PDF) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "PDF_"+ timeStamp + ".pdf");
        } else {
            return null;
        }

        return mediaFile;
    }

    /** Delete files from media directory */
    private void deleteMediaFiles() {
        if (mediaStorageDir == null) {
            mediaStorageDir = new File(getExternalFilesDir(
                    Environment.DIRECTORY_PICTURES), "CAP_FRS");
        }
        if (mediaStorageDir.exists()) {
            for (File f : mediaStorageDir.listFiles()) {
                f.delete();
            }
        }
    }

    /** Calculate size of files in media directory in bytes */
    private int getMediaStorageDirSize() {
        int size = 0;
        if (mediaStorageDir == null) {
            mediaStorageDir = new File(getExternalFilesDir(
                    Environment.DIRECTORY_PICTURES), "CAP_FRS");
        }
        if (mediaStorageDir.exists()) {
            for (File f : mediaStorageDir.listFiles()) {
                size += f.length();
            }
        }
        return size;
    }

    private void GeneratePDF() {
        // set up the print attributes
        PrintAttributes printAttrs = new PrintAttributes.Builder().
                setColorMode(PrintAttributes.COLOR_MODE_COLOR).
                setMediaSize(PrintAttributes.MediaSize.NA_LETTER).
                setResolution(new PrintAttributes.Resolution("300dpi", PRINT_SERVICE, 300, 300)).
                setMinMargins(PrintAttributes.Margins.NO_MARGINS).
                build();
        // create a new document
        PrintedPdfDocument document = new PrintedPdfDocument(this,printAttrs);

        // start a page
        Page page = document.startPage(0);
        Canvas canvas = page.getCanvas();

//        // set up our output text
//        String outputText = String.format("FULL NAME: %s\n\nCAP ID: %s\nAIRCRAFT TAIL #: %s\tMODEL: %s\nMISSION DATE: %s\nMISSION SYMBOL: %s\nLIST ALL SORTIES INCLUDED: %s\nTOTAL GALLONS FOR ALL SORTIES: %s\nTOTAL COST FOR ALL SORTIES: $%s\nAVG FUEL BURN: %s Gallons/Hour\n\nREMARKS: %s\n\nVendor Name: %s\nVendor Location: %s\nDATE OF FUEL SERVICE: %s",
//                ((EditText) findViewById(R.id.editName)).getText(), ((EditText) findViewById(R.id.editCAPID)).getText(), ((EditText) findViewById(R.id.editAircraftTail)).getText(),
//                ((EditText) findViewById(R.id.editAircraftModel)).getText(), ((EditText) findViewById(R.id.editMissionDate)).getText(), ((EditText) findViewById(R.id.editMissionSymbol)).getText(),
//                ((EditText) findViewById(R.id.editSorties)).getText(), ((EditText) findViewById(R.id.editTotalGallons)).getText(), ((EditText) findViewById(R.id.editFuelCost)).getText(),
//                ((EditText) findViewById(R.id.editFuelBurn)).getText(), ((EditText) findViewById(R.id.editRemarks)).getText(), ((EditText) findViewById(R.id.editVendorName)).getText(),
//                ((EditText) findViewById(R.id.editVendorLocation)).getText(), ((EditText) findViewById(R.id.editServiceDate)).getText());

        // units are in points (1/72 of an inch)
        int titleBaseLine = 72;
        int leftMargin = 54;
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(36);
        canvas.drawText("FUEL RECEIPT INFORMATION", leftMargin, titleBaseLine, paint);
        paint.setTextSize(14);
        canvas.drawText(String.format("FULL NAME: %s", ((EditText) findViewById(R.id.editName)).getText()), leftMargin, titleBaseLine + 30, paint);
        canvas.drawText(String.format("CAP ID: %s",((EditText) findViewById(R.id.editCAPID)).getText()), leftMargin, titleBaseLine + 50, paint);
        canvas.drawText(String.format("AIRCRAFT TAIL #: %s\tMODEL:%s",((EditText) findViewById(R.id.editAircraftTail)).getText(),((EditText) findViewById(R.id.editAircraftModel)).getText()), leftMargin, titleBaseLine + 70, paint);
        canvas.drawText(String.format("MISSION DATE: %s",((EditText) findViewById(R.id.editMissionDate)).getText()), leftMargin, titleBaseLine + 90, paint);
        canvas.drawText(String.format("MISSION SYMBOL: %s",((EditText) findViewById(R.id.editMissionSymbol)).getText()), leftMargin, titleBaseLine + 110, paint);
        canvas.drawText(String.format("LIST ALL SORTIES INCLUDED: %s",((EditText) findViewById(R.id.editSorties)).getText()), leftMargin, titleBaseLine + 130, paint);
        canvas.drawText(String.format("TOTAL HOBBS TIME: %s",((EditText) findViewById(R.id.editHobbsTime)).getText()), leftMargin, titleBaseLine + 150, paint);
        canvas.drawText(String.format("TOTAL GALLONS FOR ALL SORTIES: %s",((EditText) findViewById(R.id.editTotalGallons)).getText()), leftMargin, titleBaseLine + 170, paint);
        canvas.drawText(String.format("TOTAL COST FOR ALL SORTIES: %s",((EditText) findViewById(R.id.editFuelCost)).getText()), leftMargin, titleBaseLine + 190, paint);
        // Calculate average fuel burn from given values
        double avgFuelBurn = 0.0;
        Editable totalGallons = ((EditText) findViewById(R.id.editTotalGallons)).getText();
        Editable hobbsTime = ((EditText) findViewById(R.id.editHobbsTime)).getText();
        if (totalGallons != null && hobbsTime != null) {
            try {
                avgFuelBurn = Double.parseDouble(totalGallons.toString()) / Double.parseDouble(hobbsTime.toString());
            } catch (NumberFormatException ex) {}
        }
        canvas.drawText(String.format("AVG FUEL BURN: %.2f Gallons/Hour",avgFuelBurn), leftMargin, titleBaseLine + 210, paint);
        canvas.drawText(String.format("REMARKS: %s",((EditText) findViewById(R.id.editRemarks)).getText()), leftMargin, titleBaseLine + 230, paint);
        canvas.drawText(String.format("Vendor Name: %s",((EditText) findViewById(R.id.editVendorName)).getText()), leftMargin, titleBaseLine + 250, paint);
        canvas.drawText(String.format("Vendor Location: %s",((EditText) findViewById(R.id.editVendorLocation)).getText()), leftMargin, titleBaseLine + 270, paint);
        canvas.drawText(String.format("DATE OF FUEL SERVICE: %s",((EditText) findViewById(R.id.editServiceDate)).getText()), leftMargin, titleBaseLine + 290, paint);

        // draw the image from the camera into the destination rectangle on the canvas
        double bottom = 390.0 + (canvas.getWidth()/2.0 - 54.0)*(4.0/3.0);
        Rect dstRect = new Rect(canvas.getWidth()/2,390,canvas.getWidth()-54,(int)bottom);
        if (imgBitmap != null) {
            canvas.drawBitmap(imgBitmap, null, dstRect, null);
        }

//        // Load the pdflayout by inflating it from the xml file
//        View pdflayoutView = getLayoutInflater().inflate(R.layout.pdf_layout, null);
//        pdflayoutView.measure(canvas.getWidth(), canvas.getHeight());
//        pdflayoutView.layout(0,0,canvas.getWidth(),canvas.getHeight());
//        TextView titleText = (TextView)pdflayoutView.findViewById(R.id.pdfTitleText);
//        TextView leftText = (TextView)pdflayoutView.findViewById(R.id.pdfLeftText);
//        ImageView imageView = (ImageView)pdflayoutView.findViewById(R.id.pdfImageView);
//
//        // Fill in the fields
//        titleText.setText("Fuel Receipt Information");
//        leftText.setText(outputText);
//        imageView.setImageURI(imgUri);
//
//        // Draw the layout
//        pdflayoutView.draw(canvas);


        // finish the page
        document.finishPage(page);

        // write the document content
        try {
            File pdfFile = getOutputMediaFile(MEDIA_TYPE_PDF);
            OutputStream out = new BufferedOutputStream(new FileOutputStream(pdfFile));
            document.writeTo(out);
            document.close();
            out.close();
            // create the Uri
            pdfUri = Uri.fromFile(pdfFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void viewPDF(View view) {
        // onClick handler for view pdf button
        // Generate the PDF and display it
        GeneratePDF();

        // Send the intent to open the file
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(pdfUri, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        try {
            startActivity(Intent.createChooser(intent, "Opening PDF"));
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(this, "Error: no pdf viewers installed.", Toast.LENGTH_LONG).show();
        }
    }
}
