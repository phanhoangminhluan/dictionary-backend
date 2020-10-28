FROM maven:3.6.3-jdk-8-slim AS build
COPY pom.xml /home/usr/app/
WORKDIR /home/usr/app
RUN mvn dependency:go-offline
COPY src src
RUN apt update
RUN apt install -y curl iputils-ping
RUN mvn clean install
ENTRYPOINT ["mvn", "spring-boot:run"]
EXPOSE 8080
EXPOSE 35729

#docker run -it --rm -p 8080:8080 -p 35729:35729 --name dict-backend -v ~/Projects/dict-be/dictionary-backend/:/app-dict/ dict-be