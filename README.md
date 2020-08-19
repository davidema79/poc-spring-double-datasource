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

#### Triggers Configuration
In this version, variables in the Cloud Build Trigger are expected to be defined so that the file `cloudBuild.yaml` can inject those into the environment context where GAE will run our application.

In particular, they are:
```shell script
_INSTANCE01_USR : <username of the Cloud SQL instance 1>
_INSTANCE01_PWD : <password of the Cloud SQL instance 1>
_INSTANCE02_USR : <username of the Cloud SQL instance 2>
_INSTANCE02_PWD : <password of the Cloud SQL instance 2>
```
These variables are used in `cloudBuild.yml` and then in `application-cloud.yml` which will finally inject them into the Application Context.
