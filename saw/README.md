## Civil Air Patrol Fuel Reporting System
### Stand Alone Website (SAW) Software Requirements Specification

### 1. Introduction
#### 1.1 Purpose
The CAP-FRS Stand Alone Website (SAW) will provide a simple and streamlined process to generate the fuel receipt documents required by the US Air Force during their reimbursement process. This software requirements specification (SRS) outlines the function, capabilities, and constraints of this system. The SAW is intended to be the first in a sequence of software solutions intended to assist the Civil Air Patrol (CAP) with its fuel reimbursement process and decrease rejection by at least 5%.


#### 1.2 Document Conventions
- SAW: Stand Alone Website meant to do the work for the Civil Air Patrol.

- SmartPhone / Smart Device: Any smart device such as an iPhone or an Android device that has a processor unit and is usually used to browse the internet, or to run various applications.


#### 1.3 Intended Audience and Reading Suggestions
The SAW is designed to be used by any Civil Air Patrol unit that follows the standard Air Force fuel receipt turn in system.  The primary users will be members of the Civil Air Patrol, but could also extend to members of the Air Force itself such as administration clerks looking at the format of the PDF.

Other reading suggestions:
Basic internet safety: http://www.ncpc.org/topics/internet-safety


#### 1.4 Product Scope
Version 1 of the SAW software for the Civil Air Patrol will prioritize utilizing a stand alone website hosted on a private web server that will help to standardize all fuel receipt submissions to the Air Force.  It will have an entry point for all required fields needed on a fuel receipt document as well as an upload link for the actual fuel receipt image, using any standard camera picture currently stored on the client machine.  The SAW is only meant to be used with desktops or laptops and is currently not completely integrated with tablets, smartphones or other smart devices.




### 2. Overall Description
#### 2.1 Product Perspective
The SAW software is the first installation of software in a series meant to standardize the way the Civil Air Patrol will submit their fuel receipts for reimbursement.  This will be done through a website that will have an upload link for a receipt picture and fields for every item the Air Force requires on the fuel receipt document to eliminate user error.  


#### 2.2 Product Functions
The SAW software will create a PDF that is intended to be uploaded to the Air Force WIMRS official website.  It will allow users to upload a picture and fill out the necessary fields, after which it will create a PDF for the user that can be uploaded to the Air Force website.


#### 2.3 User Classes and Characteristics
- Standard User; Civil Air Patrol Member:  Will use this website to update the fields, upload a picture and generate a PDF.  

- Admin:  Will have access to change the website and possibly add more fields as Air Force requirements change.

- Air Force Receipts Accountant: Will be able to look at the site and send suggestions for later updates to make things more standardized and further decrease possible fuel receipt rejection.


#### 2.4 Operating Environment
- Overview:  The SAW software is designed to be used with a PC, as well as an active and working internet connection to connect to the website.  However, the PC will need to have enough space to save the PDF, as well as have the memory and processing power to create the PDF.


- User operating environment: For the SAW software should be a desktop or laptop with a secure internet connection.  The actual user location is irrelevant as the SAW software is an internet based application.


- Server information:  The website is hosted on an Apache web server, running on a highly available Ubuntu 14.04 GNU/Linux virtual private server (VPS). The web server must not exceed the capabilities of the VPS, which is a single core, 1GB instance. Also, the total net web traffic to and from the server must not be in excess of 2TB per calendar month. The server must comply and operate within the GNU/Linux environment and function normally under the constraints of the system firewall and other system security measures.








#### 2.5 Design and Implementation Constraints
Later designs and implementations may be limited by security as the information that is submitted must be kept private.  Although there is no sensitive information that is being used it may create a problem due to Air Force regulations.  The SAW software and any further implementation or changes to it must comply with any and all Air Force regulations for fuel receipt submissions as well as any Air Force regulations for security regarding mission flight data.  


The current design will be limited to a standalone web application that will have input fields for the required data and an upload for the picture.  It will then create a pdf for the user.  Anything further goes outside the scope of the SAW V1 server.


#### 2.6 User Documentation
Receipts need to have fields on them regarding the name of the operator, all the mission information as well as flight information.  This must be added to the PDF.


#### 2.7 Assumptions and Dependencies
- Internet connection
- Hard Drive space on local machine for PDF document storage
- PC or laptop installed with modern web browser with JavaScript and HTML5


### 3. External Interface Requirements
#### 3.1 User Interfaces
The user interface will consist of a single page with fillable text boxes corresponding to each field that must appear on the fuel receipt.  There will be an upload button that will allow the user to select a picture on their device and insert it into the PDF document, as well as cropping the image as necessary.  At the very end the user can submit the information, generating the file and initiating the download.


#### 3.2 Hardware Requirements
Computer device built after 2000A.D.


#### 3.3 Software Interfaces
- Internet connection (for initial page load only)
- Modern web browser with HTML5 and JavaScript support
- Disk access for saving generated file


#### 3.4 Communications Interfaces
Communication will be done over a standard internet connection.  The V1 of the SAW software will not have any additional security measures and will conduct all of its work on the client side -- no user information will be transmitted.

### 4. System Features
#### 4.1 Upload Picture: Priority 9
The upload picture feature is high priority as it is a required field for submitting a fuel receipt to the Air Force. A user will be able to click on the button and browse his or her computer for a picture file that will be moved onto the PDF for later submission.


#### 4.2 Field Fill In: Priority 9
The field fill in is high priority as the fields are required by the Air Force to have a valid fuel receipt turn in. There will be a field for each item required by the Air Force which will eliminate the user error of forgetting a required field.


#### 4.3 Generating PDF: Priority 9
The SAW software will generate a PDF that can be uploaded to the Air Force WIMRS official website. This will be the final product and will provide a clean format that is easy to read and meant to accomplish the objective of reducing fuel receipt rejections.


### 5. Other Nonfunctional Requirements
#### 5.1 Performance Requirements
The website will utilize client-side JavaScript to provide results at the speed of the user’s PC and web browser. The PDF generation is potentially computationally intensive, but will take no longer than two seconds to complete and be ready for download. 


#### 5.2 Safety Requirements
Conforming with all HTTP, HTML, and JavaScript standards ensures that there will not be any unexpected or malicious behavior on behalf of the website. 


#### 5.3 Security Requirements
The use of client-side scripting means that user input and the receipt image are never sent back to the web server or over the global internet. In conjunction with a stateless, static website, the user is guaranteed that their data is as secure as their local PC.


#### 5.4 Software Quality Attributes
A preview of the generated document will be provided before it is offered for download to ensure that it meets the user’s quality expectations and to facility input error catching.


#### 5.5 Business Rules
The Bellingham CAP is fully authorized to use the SAW software.  All other entities are required to obtain permissions from developers to use the software past the date of 2015/12/01 or the end of the testing phase, whichever occurs first.lopers to use the software past the date of 2015/12/01 or the end of the testing phase, whichever occurs first.
