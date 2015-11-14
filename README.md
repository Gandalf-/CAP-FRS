## Civil Air Patrol Fuel Reporting System
### Vision & Scope


### 1. Business Requirements
#### 1.1 Background
The Civil Air Patrol is the official auxiliary of the United States Air Force and a non-profit organization. CAP supports America’s communities through emergency/disaster relief, aerospace education for youth and the general public, and cadet programs for teenage youth.
                
#### 1.2 Opportunity
By creating this application we hope to standardize receipt submissions for all CAP and lower Air Force receipt rejection rates.  Although CAP here will receive a copy, more can be made available to others around the US at a fee.


#### 1.3 Objectives and Success Criteria
The CAP-FRS will streamline and simplify the process through which CAP members submit fuel receipts to the US Air Force for reimbursement. Success will be measured by a reduced percentage of rejected submissions due to error, and a reduction in operator time required for submission.


#### 1.4 Customer Needs
CAP members must be able to submit their fuel receipts to the US Air Force system quickly and correctly. The CAP-FRS must be accessible to the majority of CAP members and improve on their current process.


#### 1.5 Risks

As with any system utilizing Internet connectivity and submission of user data, any possible security and privacy issues must be investigated as potential risk factors.

### 2. Vision of the Solution
#### 2.1 Vision Statement
Our goal will be to completely streamline the process that the Civil Air Patrol uses and create one standardized method in an internet web application.  This application would create a PDF document for any Civil Air Patrol member that used it and would take in all data that is necessary for a fuel receipt submission.  The final product will be able to pass the inspection of the documents that occurs in the Air Force and decrease rejection rates of fuel receipt submissions by at least 5%.  

#### 2.2 Major Features
- web application
- guided fields necessary submission
- thorough explanation on fields on what could get input rejected
- image upload 
- complete formatting of PDF

#### 2.3 Assumptions and Dependencies
The provided application is an internet application, and therefore requires users to have a working internet connection.

### 3. Scope and Limitations
#### 3.1 Scope of Initial Release
The initial release of the Civil Air Patrol web application will provide an online user interface for Civil Air Patrol members with a streamlined online method to create their PDF.  It will cover all necessary fields that must be on a fuel receipt submission and give thorough explanations on each field of what needs to be put in to avoid rejection from the Air Force.  After this it will create a pdf for the user that they can attach on the official website and submit for evaluation by the Air Force.

#### 3.2 Scope of Subsequent Releases
In the future one of the goals is to obtain access to the official website to link with the application.  This will avoid a redundancy in filling out some of the information, and will accelerate the process of submitting fuel receipts even further.  Another take on the same goal will be to link the official submission website with an automated script to fill in both fields at the same time while either filling out the website or filling out the form.  Once again this will take away the redundancy of putting in information that was already inserted, and accelerate the process of submitting the fuel receipts to the Air Force.


#### 3.3 Limitations and Exclusions
The original version of the application will not have any connection to the Air Force website.  It will be completed as its own stand alone application CAP members can fill out online which will create a pdf for them with their fuel receipt to attach to the Air Force online application.  There will also be no autofill options with the original and everything will have to be done manually at first.  Internet will be required to complete this as it will be an online application, sometimes requiring the pilots to return home before beginning to work on their fuel receipt submission, or before finalizing it.




### 4. Business Context
#### 4.1 Stakeholder Profiles

|Stakeholder| Description |
|--------|---------|
|CAP Members| Primary users|
|US Air Force | Secondary users: read|
	

#### 4.2 Project Priorities


|Dimensions |Driver| Constraint| Degree of Freedom|
|-----------|------|-----------|------------------|
|Schedule| Probable release by Fall 2015| Internet connection required |N/A|
|Features|Included in each release which is stated in the “Vision of the Solution” section|	Will not sync with Air Force website|	N/A|
|Quality|Decrease the rejection rates of fuel receipt submissions by  5%|	N/A|N/A
	

#### 5. Versions
        0.1        Base template
        0.2        Additions to Background, Objectives & Success, and Customer Needs
        0.3        Additions to Vision of Solution, and Scope and Limitations