# Email Send Project

 A spring boot application integrated to STMP Relay of SendGrid/Gmail


### What You Need:
    * A favorite text editor or IDE
    * JDK 1.8
    * Maven 3.3+
    * You can also import the code straight into your IDE:
        IntelliJ IDEA        
        Spring Tool Suite (STS)
### Source Code
    * Option# 1 - Download and unzip the source repository
    * Option# 2 - Clone it using Git: git clone - https://github.com/aaronlim3/Send-Email.git
    * Compile the source code  from the root of the project using the following command
        mvn clean install

### How to run
    * Navigate to the root of the project.
        * Run Spring Boot app using Maven        
            mvn spring-boot:run
        * Run Spring Boot app with java -jar command
        java -jar target/emailsend-0.0.1-SNAPSHOT.jar

### Test run the app
    * run curl
        curl -v -X POST "http://localhost:8091/api/send" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"email_message\": \"this is ok\", \"email_recipient\": \"email@example.com\", \"email_subject\": \"test email\"}"




### Note
    Due to time constraints.
    
    * An ARCHITECTURE.md with technical decisions
    * API Metrics
    * API Resiliency
    * Security
    * Deploying your solution somewhere (URL) for us to play with it.
