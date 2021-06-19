# zowe-client-java-sdk

A partial implementation of a Zowe SDK for Java - Work In Progress  
  
Run GetJobsTest.java, IssueCommandTest.java and SubmitJobsTest.java to see current functionality available. You need to replace both instances of "xxxxxxxx" with username and password for your the host you provide that has z/OSMF installed.  
  
Functionality provided so far:  
  
    GetJobs (all things about jobs) 
    IssueCommand (mvs commands)
    SubmitJobs (partial)
    
## Build
  
The following maven command at the root prompt of the project will produce ZoweJavaSDK.jar in the target directory:
  
    mvn clean package  
  
## Logger  
  
Logger packaged used for the project is log4j2.  
  
Under 'resources' directory you will find the logger configuration file log4j2.xml.  
  
Change <Root level="debug"> assignment accordingly to your needs.  
  

  
