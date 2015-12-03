package org.bellinghamcap.cap_frs;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.widget.DatePicker;
import java.util.Calendar;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

/**
 * Credit: Dara - http://www.worldbestlearningcenter.com/tips/Android-date-picker-dialog.htm
 */

@SuppressLint("ValidFragment")
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    EditText edit6;
    public DateDialog(View view){
        edit6=(EditText)view;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the dialog
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        //show to the selected date in the text box
        String date = (month+1) + "/"+ day + "/" + year;
        edit6.setText(date);
    }
}
