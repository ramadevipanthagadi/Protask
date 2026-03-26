FROM tomcat:9

# Remove default apps (optional)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR file
COPY target/*.war /usr/local/tomcat/webapps/onlinebookstore.war

EXPOSE 8080
