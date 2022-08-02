FROM maven:3.8.4-jdk-11-slim as builder
WORKDIR /src
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package
RUN mvn sonar:sonar \
  -Dsonar.projectName=rmt-new-deposit-service-dev\
  -Dsonar.projectKey=rmt-new-deposit-service-dev \
  -Dsonar.host.url=https://sonarqube8.andersenlab.com \
  -Dsonar.login=ce98d4abe4b77d9cbe924f524857f4c30668118e \
  -Dsonar.java.binaries=/src/target/classes/**/* \
  -Dsonar.java.test.binaries=/src/target/test-classes/**/* \
  -Dsonar.java.test.exclude=test \
  -Dsonar.coverage.exclusions=**/Jwt*.java,**/Dto*.java,src/main/java/com/andersenlab/rmtbanking/depositservice/controller/*,src/main/java/com/andersenlab/rmtbanking/depositservice/entity/enums/*,src/main/java/com/andersenlab/rmtbanking/depositservice/service/exeption/*,src/main/java/com/andersenlab/rmtbanking/depositservice/entity/*,src/main/java/com/andersenlab/rmtbanking/depositservice/dto/*,src/main/java/com/andersenlab/rmtbanking/depositservice/DepositServiceApplication.java \
  -Dsonar.jacoco.reportPath=/src/target/jacoco.exec

FROM alpine:3.15.3
RUN apk --no-cache add openjdk11-jre
COPY --from=builder /src/target/deposit-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
