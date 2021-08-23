# zowe-client-java-sdk

A partial implementation of a Zowe SDK for Java.  

This project provides a modern SDK for developers to create many types of applications running on their Windows or Mac instance against z/OS.
  
This project has been submitted to the Zowe community for continuation of full feature set under the Zowe open source framework for z/OS.

See https://github.com/zowe/zowe-client-java-sdk for continued development. 
  
Run GetJobsTest.java, IssueCommandTest.java and SubmitJobsTest.java to see current functionality available.  
You need to replace all instances of "xxxxxxxx" accordingly which define Z/OSMF connection details to your system.   
  
Functionality provided:  
    
    GetJobs (all things about jobs) 
    IssueCommand (mvs commands)  
    SubmitJobs (partial)  
      
## Build
  
The following maven command at the root prompt of the project will produce ZoweJavaSDK.jar in the target directory:
  
    mvn clean package  
  
## Logger  
  
Logger packaged used for the project is log4j2.  
  
Under 'resources' directory you will find the logger configuration file log4j2.xml.  
  
Change <Root level="debug"> assignment accordingly for your needs.  
  

  
