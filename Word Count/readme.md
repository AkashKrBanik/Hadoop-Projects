## Running the WordCount Project
I used IntelliJ IDEA Community Edition to build this project. To start, I created a new Maven project.
- Opened IntelliJ and clicked on New Project.
- Selected Maven as the project type and ensured that Java JDK is installed.
- I named the project as WordCount, and set the group and artifact IDs. I set the groupId as org.akb and left the artifact ID as default. 
After the project was created, I deleted the default main class, as it wasn’t needed for this project.

The next step was to add the necessary dependencies to the pom.xml file.

### 1. Create a Maven Project
Add the following dependencies in `pom.xml`:
```xml
<dependencies>
    <dependency>
        <groupId>org.apache.hadoop</groupId>
        <artifactId>hadoop-common</artifactId>
        <version>3.3.6</version>
    </dependency>
    <dependency>
        <groupId>org.apache.hadoop</groupId>
        <artifactId>hadoop-mapreduce-client-core</artifactId>
        <version>3.3.6</version>
    </dependency>
</dependencies>
```
Now reloaded the Maven project so that all dependencies were downloaded and added to the classpath. 

### 2. Implement WordCount Classes
The WordCount project consists of three main components: 
- **Mapper**: `WC_Mapper.java`    – Handles the map phase of the process. 
- **Reducer**: `WC_Reducer.java`  – Handles the reduce phase of the process. 
- **Runner**: `WC_Runner.java`    – Acts as the driver to set up and run the MapReduce job. 

### 3. Build the Project
Once all the code was written in the mapper, reducer and runner files, I built the project using Maven. I opened the terminal in IntelliJ and ran the following commands to clean and package the project: 
```bash
mvn clean package
```
This generated a JAR file in the target folder, which I would use to run the WordCount job. 

### 4. Run the Project
Next, I created a simple text file as input.txt with some random text in it.
#### Create Input File
```bash
nano input.txt
```

Add sample text:
```
This is the input file for hadoop project
This is for Cloud Computing with AKB hadoop project file
This is done for now
```

#### Upload to HDFS
I then created a directory in HDFS to store the input file.
```bash
hadoop fs -mkdir /input
```
Next, I copied the input file to HDFS.
```bash
hadoop fs -put input.txt /input
```

#### Execute WordCount Job
Finally, I ran the WordCount job by executing the following command: 
```bash
hadoop jar target/wordcount-1.0-SNAPSHOT.jar org.akb.WC_Runner /input /output
```

#### View Output
Once the job finished, I verified the output by listing the contents of the /output directory in HDFS.
```bash
hadoop fs -cat /output/part-00000
```
---
### Screenshots
#### Input File Contents:
<img src="https://github.com/AkashKrBanik/Hadoop-Projects/blob/main/Word%20Count/WC_SS_2.png" alt="Login Form" width="700">

#### Output:
<img src="https://github.com/AkashKrBanik/Hadoop-Projects/blob/main/Word%20Count/WC_SS_3.png" alt="Login Form" width="700">

#### Output File Contents:
<img src="https://github.com/AkashKrBanik/Hadoop-Projects/blob/main/Word%20Count/WC_SS_4.png" alt="Login Form" width="700">

## Conclusion

By following this guide, you can successfully run your project on a distributed environment. The WordCount example demonstrates the basics of Hadoop's MapReduce framework.
