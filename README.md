# Integration/Migration PoC: Spring with 2 DataSources

## Build the project locally
This project uses Gradle 6.6 on jdk 11.

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

## Project Evolution 

### Step 1 - Connecting to Cloud SQL from Local
In order to verify the connection has been configured correctly and Liquibase works fine on Cloud SQL, we first try to connect to GCP Instances from our local machine.

That is possible only if: 
1. Both Cloud SQL instances have a cloud IPv4
2. In the `Connectivity` section inside the `Connections` tab of both the SQL instances the **public** IPv4 of the local machine has been added (i.e. 86.176.123.123/32) below the *Authorised networks* inside the section **Public IP**. 

```shell script
  $ ./gradlew clean build
  $ java -Dspring.profiles.active=cloud -DINSTANCE01_USR=<username_user_1> -DINSTANCE01_PWD=<pwd_user_1> -DINSTANCE02_USR=<username_user_2> -DINSTANCE02_PWD=<username_user_2> -jar build/libs/poc-spring-double-datasource-1.0.0-SNAPSHOT.jar
```

### Step 2 - Deploying on AppEng and using Public IP
Because at this stage the project is using the Public IP, the infrastructure does not need any specific change, but the project does.
This [guide](https://cloud.google.com/sql/docs/mysql/connect-app-engine-standard#public-ip-default) explains which are the tweaks the code need. However, because this is a SpringBoot application therefore we have to make those changes with a pinch of salt.

First thing to do is including the dependencies for both mysql a postgres *Socket Factory* in the `build.gradle` file, afterwards we need to populate the DataSource objects with 2 new properties: 
1. cloud sql *instances names* 
1. the socket factory class name.

`HikariDataSource` class saves those properties in a specific Map. Thanks the annotation `@ConfigurationProperties(prefix="spring.datasource")` in the configuration classes, we are able to achieve that just by adding in the application properties a map of values.
The name of the latter has to match the field name in the `HikariDataSource` class: `dataSourceProperties`. Therefore `data-source-properties` is the new property which is a map of value.

Please refer to the `JpaConfig` classes and the `application-cloud.yml` file.   


