### RotaBuilder - Employee Rostering
*A Java web application for nurse scheduling based on [OptaPlanner](http://optaplanner.org/) and [Apache Isis](http://isis.apache.org/)*

### (option 1) Local: Build and Run with Maven

    mvn clean install -DskipTests=true
    cd webapp
    mvn jetty:run

#### (option 2) Local: Build and Run with Docker

    mvn clean install -DskipTests=true
    docker build --rm -t bibryam/rotabuilder .
    docker run -p 8080:8080 bibryam/rotabuilder
 
#### (option 3) OpenShift Online: Deploy existing Docker image to OpenShift
Create an OpenShift Online [account](https://manage.openshift.com/). Notice, even the free tier account is enough to try out the application.
Install OpenShift client locally (oc) and login to OpenShift. The following commands will run the latest Rotabuilder Docker image and expose its URL to outside world. 

    oc new-project rotabuilder
    oc new-app bibryam/rotabuilder:latest -e CATALINA_OPTS="-Xmx300m"
    oc expose service rotabuilder

The process takes some time as it has to download the Docker images and there are not much free resources on a free tier account. Notice also we limit Tomcat heap size in order to run in a Docker container with 512MB memory. After few minutes, you should be able to access your Rotabuilder instance.

### Login
    http://localhost:8080/

    test:test - no tenancies
    user:user - default demo user
    admin:admin - admin user

### Live Demo on Red Hat OpenShift

See live demo running at [http://rotabuilder.com](http://rotabuilder.com) 

### Screenshot

![project view](https://2.bp.blogspot.com/-jb-IJWAOtbY/WjUIQ1tRCII/AAAAAAAAJ5Y/83vQxgATqJwC_gh2xsNgEyjAGsYuz9aMgCLcBGAs/s1600/Screen%2BShot%2B2017-12-16%2Bat%2B11.47.33.png)


### Application Features
 - Manage employees
 - Manage skills
 - Manage days on/off requests
 - Manage shift on/off requests
 - Manage contracts
 - Manage shifts 
 - Create automatic employee-shift assignments
 
