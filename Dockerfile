FROM tomcat:9.0.2-alpine
MAINTAINER Bilgin Ibryam
EXPOSE 8080
COPY webapp/target/ROOT.war /maven/ROOT.war
RUN rm -fr /usr/local/tomcat/webapps/* && mv /maven/ROOT.war /usr/local/tomcat/webapps/
RUN chgrp -R 0 /usr/local/tomcat && chmod -R g=u /usr/local/tomcat
CMD catalina.sh run
