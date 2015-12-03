## Civil Air Patrol Fuel Reporting System
### Stand Alone Android Application (SAA) 

### 1.0 Introduction
#### 1.1 Background
The Civil Air Patrol is the official auxiliary of the United States Air Force and a non-profit organization. CAP supports America’s communities through emergency/disaster relief, aerospace education for youth and the general public, and cadet programs for teenage youth. 

#### 1.2 Objectives and Success Criteria
The CAP-FRS will streamline and simplify the process through which CAP members submit fuel receipts to the US Air Force for reimbursement. Success will be measured by a reduced percentage of rejected submissions due to error, and a reduction in operator time required for submission. The SAA, Stand Alone Android Application, will provide the same functionality as the SAW, Standalone Website, on the Android platform.



#### 1.3 Conventions
- SAW: Stand Alone Website
- SAA: Stand Alone Android Application
- Android: Mobile device running with a modern version of the Android OS
SmartPhone / Smart Device: Any smart device such as an iPhone or an Android device that has a processor unit and is usually used to browse the internet, or to run various applications.




#### 1.4 Intended Audience and Reading Suggestions
The SAA is designed to be used by any Civil Air Patrol unit that follows the standard Air Force fuel receipt turn in system.  The primary users will be members of the Civil Air Patrol, but could also extend to members of the Air Force itself such as administration clerks looking at the format of the PDF.
        
The SAW documentation will provide further background on the FRS project. 




#### 1.5 Product Scope
The SAA will provide the same functionality as the SAW; provide a convenient interface through which to easily generate fuel receipts for CAP sorties. It will consist of an initial interface prototype, functionality prototype, full prototype, and final product versions.




### 2.0 Overall Description
##### 2.1 Vision Statement
Our goal will be to completely streamline the process that the Civil Air Patrol uses and create one standardized method in an internet web application.  This application would create a PDF document for any Civil Air Patrol member that used it and would take in all data that is necessary for a fuel receipt submission.  The final product will be able to pass the inspection of the documents that occurs in the Air Force and decrease rejection rates of fuel receipt submissions by at least 5%.




#### 2.2 Product Functions

The product will provide the following functions:

1. Receive user input to fill required form fields
2. Utilize the device’s built in camera function
3. Generate a PDF document with the fields and image
4. Email the PDF document to the user


#### 2.3 User Classes and Characteristics
- Standard User; Civil Air Patrol Member:  Will use this Android application to update the fields, upload a picture and generate a PDF.  

- Air Force Receipts Accountant: Will be able to look at the site and send suggestions for later updates to make things more standardized and further decrease possible fuel receipt rejection.




#### 2.4 Operating Environment
The SAA will only be supported on Android devices with a modern version of the operating system. Use of the application on older versions of Android may be possible but is not officially supported by the SAA team. At the moment the SAA is operational on KitKat and above Android platforms.  The function of application will not be resource intensive.  The device must be able to download and install the application, and support its operation.  Users of the SAA will be able to use the application even without an internet connection, although the emailing process will not occur till a network connection is found. 
        


#### 2.5 Design and Implementation Constraints
Both design and implementation will be constrained by the Android API and all applicable standards and specifications for Android applications as put forth by Google. Implementation and support are constrained by the availability of the SAA team. Support for the application is not guaranteed after March 19th. 


#### 2.6 Assumptions and Dependencies

Assumptions
1. The user is able to install third party software not vetted by Google on their android device.
2. The user has not installed any software that will interfere with the functionality of the SAA. 

Dependencies
1. The device must have a camera
2. The device must have internet connectivity to email PDF to user
3. The device must have a configured email client




### 3.0 Scope
#### 3.1 Scope of Initial Release
The interface prototype will demonstrate the design of the interface. It will show the orientation of buttons and options available to the user. This version is not required to provide any actual functionality.

The functionality prototype will demonstrate the functionality of the application. It will accept user input, accept camera input, generate a PDF document containing all the information gathered from the user, and then email the user the document. 

#### 3.2 Subsequent Releases
Later releases will be a full prototype and a final version. The full prototype will provide all the required functionality. The final version specifications will be decided based on user feedback on the full prototype.

#### 3.3 Limitations and Exclusions
Both design and implementation will be constrained by the Android API and all applicable standards and specifications for Android applications as put forth by Google. Implementation and support are constrained by the availability of the SAA team. Support for the application is not guaranteed after March 19th. 

### 4.0 Business Context
#### 4.1 Stakeholder Profiles


| Stakeholder | Description|
|-------------|------------|
|CAP Members | Primary users|
|US Air Force| Secondary users: read|
	

#### 4.2 Project Priorities


|Dimensions|	Drive|	Constraint|	Degree of Freedom|
|----------|---------|------------|------------------|
|Schedule|	Probable release by Winter 2015|	Internet connection required|	N/A|
|Features|	Included in each release which is stated in the “Vision of the Solution” section| Will not sync with Air Force website|N/A|
|Quality|	Decrease the rejection rates of fuel receipt submissions by  5%| N/A|	N/A|
	

### 5.0 External Interface Requirements
#### 5.1 User Interface
The user interface will consist of a single page with fillable text boxes corresponding to each field that must appear on the fuel receipt.  There will be a button to clear all textboxes, a button to take or choose a picture you have already taken, and a button to email the final PDF to the specified email address.


#### 5.2 Hardware Requirements
The user’s device must have a camera.


#### 5.3 Communication Interfaces
The user’s device must have an internet connection to send the email. The particulars of the connection and any application data usage costs are out of the scope of the SAA.  The device must be running a modern version of Android.




### 6.0 System Features
#### 6.1 Generate and Create All Fields: Priority 9
Creates all fields necessary for user to fill in with relevant data in regards to the mission,  fuel burn and other necessary statistics.


#### 6.2 Take Picture: Priority 9
Links to take picture to be used with data fields to send in as the receipt to the Air Force


#### 6.3 Forward to email: Priority 9
Once all data is gathered adds file to queue to be emailed when next available to the user of the specified email address.


### 7.0 Other Non Functional Requirements
#### 7.1 Performance Requirements
The functionality of the SAA will be to generate a PDF once completed and be able to put the PDF in queue to be emailed.  As speed is connectivity related the SAA takes no accountability for the transfer of any sent files.  The SAA will generate the PDF in under 5 seconds.



#### 7.2 Safety Requirements
The SAA will pose no safety threats to the user or the user’s device as long as device is being operated within all regular safety bounds.  The SAA team advises against flying or driving while operating your mobile device.


#### 7.3 Security Requirements
The SAA’s internal operation does not constitute any security risk; and does not assume any responsibility for the security of the user’s device or the software and hardware that it interfaces with (camera, email client).


#### 7.4 Software Quality Attributes
Maintenance and readability will be top priorities for the code base. Proper object oriented design practices will be employed to ensure compartmentalization and extensibility. 


#### 7.5 Business Rules
The Bellingham CAP is fully authorized to use the SAA software.  All other entities are required to obtain permissions from developers to use the software past the date of 2016/02/01 or the end of the testing phase, whichever occurs first.


Version 1.4                                                                                                 