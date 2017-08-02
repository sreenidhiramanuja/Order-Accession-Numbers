# Order-Accession-Numbers
This is a maven project to order **Accession Numbers**.  
 (i) Master/Tag 3.0_Java8_Web provides Java 8 implementation along with Webend point
(ii) Tag 2.0_WedEndPoint provides with web interface  
(iii) Tag 1.0_CmdLine provides only command line interface  


## To Build the project
1. Download the zip or clone the project  
2. Make sure Java 1.8 is installed and configured  
3. Make sure latest Maven is installed and configured  
4. Unzip and in the command prompt (Windows/Linux) go to the directory where pom.xml file is present  
5. You can run "mvn --version" to view maven and Java paths  
6. Run "mvn clean package" to build the project  
7. At the end you should see "BUILD SUCCESS"  
8. Target classes and jar will be built  
9. Run "java -cp target\ebi-0.0.1-SNAPSHOT.jar com.embl.ebi.OrderAccnNumber"  
10. Or you can cd to the directory "target\classes" and run the command "java -cp . com.embl.ebi.OrderAccnNumber"  
11. You will see the message "Embedded Web Server started. Port number: 8000" and also "Click enter to stop the Web Server"
12. Please open any REST client like Chrome Advanced REST client  
13. Send a POST request to "http://localhost:8000/OrderAccn" with the following details  
	    Host = http://localhost:8000  
	    Path = /OrderAccn  
	    Type = POST  
	    Content Type = Text/Plain  
	    Body = [List of accession numbers]  
	    
Please see the screen to view the same. ![alt text](https://github.com/sreenidhiramanuja/Order-Accession-Numbers/blob/master/POST.png)  
14. Once the request is sent response will be ordered accession number list  
15. Run the step 12-13 with different set of accession numbers  
16. To stop the web server please click enter  
 
 
## Assumptions
* Accession numbers will have the characters only between 0-9, A-Z. If there are special characters like "* ($" that particular number will be omitted  
* Users can enter accession number with or without ",". Space " " is must to distinguish different accession numbers
* Ordering is based on character value. Ex: A00004 comes ahead of A01
* **Consecutive digits are considered** for ordering and NOT consecutive numbers. Ex: ABCD0018, ABCD0019, ABCD0020, ABCD0021, ABCD0022 will be ordered as ABCD0018-ABCD0019, ABCD0020-ABCD0022. This is as per the guidelines.

