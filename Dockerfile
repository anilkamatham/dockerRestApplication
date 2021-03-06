FROM openjdk:8u131-jdk-alpine
LABEL author="anilkamatham"
LABEL email="anilo.kamatham@gmail.com"
LABEL app="spring restful application for institues"
WORKDIR /usr/local/bin
#RUN apt-get update 
#RUN apt-get install -y openjdk-8-jdk
RUN mkdir dockerrestapp
COPY DockerRestApp-0.0.1-SNAPSHOT.jar ./dockerrestapp/dockerRestApp.jar
EXPOSE 8090
CMD ["java", "-jar", "/usr/local/bin/dockerrestapp/dockerRestApp.jar"]