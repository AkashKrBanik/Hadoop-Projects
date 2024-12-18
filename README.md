# Hadoop-Projects

This project demonstrates how to set up a Hadoop environment and implement a simple WordCount application using Java and Hadoop's MapReduce framework. The purpose of the WordCount application is to count the occurrences of each word in a given text file.

## Prerequisites
- Xubuntu (or any Ubuntu or Linux-based OS) installed on VirtualBox
- OpenJDK 8
- Hadoop 3.3.6
- Maven
- IntelliJ IDEA

---

## Installation and Configuration

### 1. Installing Java 8

#### Step 1.1: Update the System
Run the following command to update the package manager:
```bash
sudo apt update
```

#### Step 1.2: Install OpenJDK 8
Proceed to install OpenJDK 8, which is the open-source implementation of the Java Platform. 
```bash
sudo apt install openjdk-8-jdk
```
When prompted for Yes/No, press Y to allow the installation to proceed.  

#### Step 1.3: Verify the installation:
```bash
java -version
```
If the installation is successful, the above command will display the openjdk version.

#### Step 1.4: Set Up JAVA_HOME
Hadoop needs to know where Java is installed, so I need to set the JAVA_HOME environment variable.
Find the Java path using the following command:
```bash
dirname $(dirname $(readlink -f $(which java)))
```

Edit `.bashrc`:
```bash
nano ~/.bashrc
```

Add the following and save it:
```bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
```

Now, reload the settings:
```bash
source ~/.bashrc
```

---

### 2. Installing Hadoop 3.3.6

#### Step 2.1: Download and Extract Hadoop
```bash
wget https://downloads.apache.org/hadoop/common/hadoop-3.3.6/hadoop-3.3.6.tar.gz
tar -xvzf hadoop-3.3.6.tar.gz
mv hadoop-3.3.6 hadoop
```
After the download was completed, I unzipped the downloaded file using:
```bash
tar -xvzf hadoop-3.3.6.tar.gz 
```

#### Step 2.2: Set Up Hadoop Environment Variables
To make things easier to navigate, I renamed the folder from hadoop-3.3.6 to just hadoop: 
```bash
mv hadoop-3.3.6 hadoop 
```
Now lets move on to set up the environment variables.
Edit `.bashrc`:
```bash
nano ~/.bashrc
```

Add the following and save it:
```bash
export HADOOP_HOME=~/hadoop
export HADOOP_INSTALL=$HADOOP_HOME
export HADOOP_MAPRED_HOME=$HADOOP_HOME
export HADOOP_COMMON_HOME=$HADOOP_HOME
export HADOOP_HDFS_HOME=$HADOOP_HOME
export HADOOP_YARN_HOME=$HADOOP_HOME
export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
```

Reload the settings:
```bash
source ~/.bashrc
```
This applied the new environment settings for Hadoop. 

---

### 3. Configuring Hadoop
Now that Hadoop was installed, I needed to configure several important files located in the ~/hadoop/etc/hadoop/ directory for the functioning of MapReduce properly. 

#### 3.1: Edit `core-site.xml`
```bash
nano ~/hadoop/etc/hadoop/core-site.xml
```

Add the following in the core-site.xml file and save it:
```xml
<property>
  <name>fs.defaultFS</name>
  <value>hdfs://localhost:9000</value>
</property>
```

Next, configure the hdfs-site.xml file to set up the HDFS (Hadoop Distributed File System) inside the Hadoop directory: 
#### 3.2: Edit `hdfs-site.xml`
```bash
nano ~/hadoop/etc/hadoop/hdfs-site.xml
```

Add these properties and save it:
```xml
<property>
  <name>dfs.replication</name>
  <value>1</value>
</property>
<property>
  <name>dfs.name.dir</name>
  <value>/home/akb/hadoop/data/namenode</value>
</property>
<property>
  <name>dfs.data.dir</name>
  <value>/home/akb/hadoop/data/datanode</value>
</property>
```

Now create directories for DataNode and NameNode:
```bash
mkdir -p ~/hadoop/data/namenode
mkdir -p ~/hadoop/data/datanode
```
Now configure the MapReduce framework by configuring the mapred-site.xml file in the hadoop directory. 
#### 3.3: Edit `mapred-site.xml`
```bash
nano ~/hadoop/etc/hadoop/mapred-site.xml
```

Now add and save the following properties:
```xml
<property>
  <name>mapreduce.framework.name</name>
  <value>yarn</value>
</property>
```
Now configure YARN, Hadoop's resource manager inside the Hadoop directory.
#### 3.4: Edit `yarn-site.xml`
```bash
nano ~/hadoop/etc/hadoop/yarn-site.xml
```

Add and save the following:
```xml
<property>
  <name>yarn.nodemanager.aux-services</name>
  <value>mapreduce_shuffle</value>
</property>
<property>
  <name>yarn.nodemanager.auxservices.mapreduce.shuffle.class</name>
  <value>org.apache.hadoop.mapred.ShuffleHandler</value>
</property>
```

---

### 4. Setup SSH
Hadoop requires SSH to communicate between different nodes. I need to set up SSH on my virtual machine.
#### Generate SSH Key
```bash
ssh-keygen -t rsa
```
Press Enter on each prompted asked, until the ssh key is generated. 

#### Authorize SSH Key
```bash
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
chmod 640 ~/.ssh/authorized_keys
```

#### Test SSH
```bash
ssh localhost
```

---

### 5. Starting Hadoop

Before starting Hadoop, I needed to format the NameNode. 
#### 5.1: Format NameNode
```bash
hdfs namenode -format
```

Now that everything was set up, I proceeded to start Hadoop. 
#### 5.2: Start HDFS
```bash
start-dfs.sh
```

#### 5.3: Start YARN
```bash
start-yarn.sh
```
#### OR Directly start all the services using
```bash
start-all.sh
```
#### 5.4: Verify the Services
To make sure everything was running, I used the jps command.
```bash
jps
```
This command listed running Hadoop services like NameNode, DataNode, ResourceManager, 
and NodeManager.

#### 5.5 Remember to stop all the Hadoop services, after running your project.
Stop all services directly using
```bash
stop-all.sh
```
OR separately
#### Stop HDFS
```bash
stop-dfs.sh
```

#### Stop YARN
```bash
stop-yarn.sh
```

#### 6. Accessing Hadoop
Finally, to access the Hadoop web interface and check the status of my cluster, I opened the 
web browser and went to: 
```bash
http://localhost:9870
```
#### This brought up the Hadoop cluster summary page, confirming that everything was working as expected. 
<img src="https://github.com/AkashKrBanik/Hadoop-Projects/blob/main/ss_1.png" alt="Login Form" width="800">

## Conclusion

By following this guide, you can successfully set up and configire Java and Hadoop, create a WordCount project, and run it on a distributed environment. The WordCount example demonstrates the basics of Hadoop's MapReduce framework.
