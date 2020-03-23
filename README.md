# Json Web Token App

## Reference
- **https://www.toptal.com/java/rest-security-with-jwt-spring-security-and-java** REST Security with JWT using Java and Spring Security
- **https://www.youtube.com/watch?v=X80nJ5T7YpE** Spring Boot + Spring Security + JWT from scratch - **Java Brains**
- **https://www.baeldung.com/manually-set-user-authentication-spring-security** Manually set user authentication in **Spring Security**
- **https://www.jsonwebtoken.io/** JSON Web Token documentation
- **https://bcrypt-generator.com/** Free tool to encode password using **bcrypt**

## Content
- Demostration how to protect web services using json web token in Spring MVC for Aktia interview

## Started date
- 21.03.2020

## Release
- 24.03.2020

## Configuration
- **spring shell** run spring shell
- **init --list** list all dependencies and documentation about spring shell
- **init --build=maven --packaging=war --java-version=1.8 --dependencies=web,data-jpa,data-rest,devtools,security,h2 --artifact-id=jwt-app --group-id=fi.aktia.demo --version=0.0.1 --description="Demonstrate how to protect rest API using json web token in Spring MVC" jwt-app.zip** initialize a Spring Boot project
- **unzip jwt-app && mv jwt-app.zip** unzip project's zipped file and delete it after finishing
- **cd jwt-app && mvn dependency:resolve** navigate to project's root folder and install dependencies.
- **mvn eclipse:eclipse** run this command if you could not import project to eclipse
- **mvn dependency:get -Dartifact=groupId:artifactId:version** use this command if you only want to download a single dependency

## Project structure

  
## Database connection
- **spring.security.user.name=dev** set default user for **Spring Security**
- **spring.security.user.password=99bbc2a8-70ba-4945-ac79-5da6c9eae268** set default password for **Spring Security**

## AWS demo user
- **aktia-demo** username
- **okGjrqLUb5adJ3bLwYlG2gO39XCRiI6wLuf+lhy0rH4=** password

## Storing sourcecode on CodeCommit
- **git init** navigate project's root folder and initialize git
- **git remote add origin https://git-codecommit.us-east-1.amazonaws.com/v1/repos/jwt-app** declare a repository in git config file
- **git add . && git commit -m "Initialized a spring boot project" && git push --set-upstream origin master** upload project to **CodeCommit** 

## Testing
- **mvn spring-boot:run** starting project and open the following link **http://localhost:8080** using a browser.
- **curl -H "Content-Type: application/json" -X POST --data '{"username":"admin","password":"admin"}' http://localhost:8080/auth** request to generate token for admin
- **curl -H "Content-Type: application/json" -X POST --data '{"username":"user","password":"user"}' http://localhost:8080/auth** request to generate token for admin
- **curl -H "Authorization: <ACCESS_TOKEN>" http://localhost:8080/refresh** request to refresh token
- **curl -H "Authorization: <ADMIN_ROLE_ACCESS_TOKEN>" http://localhost:8080/api/v1/users** list all users from database
- **curl -H "Authorization: <USER_ROLE_ACCESS_TOKEN>" http://localhost:8080/api/v1/users** 403 Forbidden
- **curl -H "Authorization: <ADMIN_ROLE_ACCESS_TOKEN>" http://localhost:8080/api/v1/user/1** Get info admin
- **curl -H "Authorization: <ADMIN_ROLE_ACCESS_TOKEN>" http://localhost:8080/api/v1/user/2** Get info user by admin
- **curl -H "Authorization: <USER_ROLE_ACCESS_TOKEN>" http://localhost:8080/api/v1/user/2** Get info user by user
 

## Author
- Dinh Duc Thinh
- Student at **Haaga Helia University**
- Full Stack Developer at **Capgemini Finland**

## Copyright
- © https://www.tikkidinh.com