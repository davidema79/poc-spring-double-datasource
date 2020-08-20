# Integration/Migration PoC: Spring with 2 DataSources

## Build the project locally
This project is configured to use Gradle 6.6 on jdk 11.

Command:
```shell
  $ ./gradlew clean build -Ppg_user=<username> -Ppg_pwd=<password> -Pmysql_user=<username> -Pmysql_pwd=<password>
``` 

## Build the project on Cloud Build
This project is ready to be built in GCP infrastructure using ***Cloud Build***. Deployment pipeline that executes the building steps and delivery of this project on Cloud Build are described in the file `cloudBuild.yaml` in the root directory.

#### Editing Pipeline
Edit the file `./cloudBuild.yaml` in order to modify the current pipeline.

After editing that file one should test locally the changes before committing and push against the repository, using the utility `cloud-build-local`.
In order to configure your local machine with **cloud-build-local**please follow the instructions in this [link](https://cloud.google.com/cloud-build/docs/build-debug-locally).

Command:
```shell script
   $ cloud-build-local --config=cloudBuild.yaml --dryrun=false --push=false ./
``` 

## Step 1 - Connecting to Cloud SQL from Local
In order to verify the connection has been configured correctly and Liquibase works fine on Cloud SQL, we first try to connect to GCP Instances from our local machine.

That is possible only if: 
1. Both Cloud SQL instances have a cloud IPv4
2. In the `Connectivity` section inside the `Connections` tab of both the SQL instances the **public** IPv4 of the local machine has been added (i.e. 86.176.123.123/32) below the *Authorised networks* inside the section **Public IP**. 

```shell script
  $ ./gradlew clean build
  $ java -Dspring.profiles.active=cloud -DINSTANCE01_USR=<username_user_1> -DINSTANCE01_PWD=<pwd_user_1> -DINSTANCE02_USR=<username_user_2> -DINSTANCE02_PWD=<username_user_2> -jar build/libs/poc-spring-double-datasource-1.0.0-SNAPSHOT.jar
```


