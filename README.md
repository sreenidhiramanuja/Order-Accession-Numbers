# Order-Accession-Numbers
This is a maven project to order **Accession Numbers**. 

## To Build the project
1. Download the zip or clone the project  
2. Make sure Java 1.8 is installed and configured  
3. Make sure latest Maven is installed and configured  
4. In the command prompt (Windows/Linux) go to the directory where pom.xml file is present  
5. You can run "mvn --version" to view maven and Java paths  
6. Run "mvn clean package" to build the project  
7. At the end you should see "BUILD SUCCESS"  
8. Target classes and jar will be built  
9. Run "java -cp target\ebi-0.0.1-SNAPSHOT.jar com.embl.ebi.OrderAccnNumber <list of Accession numbers>"  
10. Or you can cd to the directory "target\classes" and run the command "java -cp . com.embl.ebi.OrderAccnNumber <list of accession numbers>"  
11. You will see the output "Ordered Accession Number list" followed by ordered list of accession numbers entered  
12. Run the step 9/10 withe different set of accession numbers  


## Assumptions
* Accession numbers will have the characters only between 0-9, A-Z. If there are special characters like "* ($" that particular number will be omitted  
* Users can enter accession number with or without ",". Space " " is must to distinguish different accession numbers
* Ordering takes based on character value. Ex: A00004 comes ahead of A01
* **Consecutive digits are considered** for ordering and NOT consecutive numbers. Ex: ABCD0018, ABCD0019, ABCD0020, ABCD0021 will be ordered as ABCD18-19, ABCD20-21. This is as per the guidelines.

