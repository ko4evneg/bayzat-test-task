FROM tomcat:9.0.68-jdk17

COPY target/ROOT.war /usr/local/tomcat/webapps/
