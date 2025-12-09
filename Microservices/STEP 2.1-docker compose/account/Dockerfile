#start with a base image containing Java runtime
FROM eclipse-temurin:17-jdk
#Informaion around who maintains the image
MAINTAINER kodli
#add the application jar to the image
COPY target/account-0.0.1-SNAPSHOT.jar account-0.0.1-SNAPSHOT.jar
#execute the image
ENTRYPOINT ["java", "-jar","account-0.0.1-SNAPSHOT.jar"]

