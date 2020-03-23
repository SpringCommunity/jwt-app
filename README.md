# Json Web Token App

## Content
- Demostration how to protect web services using json web token in Spring MVC for Aktia interview

## Started date
- 21.03.2020

## Release
- 23.03.2020

## Configuration
- **spring shell** run spring shell
- **init --list** list all dependencies and documentation about spring shell
- **init --build=maven --packaging=war --java-version=1.8 --dependencies=web,data-jpa,data-rest,devtools,security,h2 --artifact-id=jwt-app --group-id=fi.aktia.demo --version=0.0.1 --description="Demonstrate how to protect rest API using json web token in Spring MVC" jwt-app.zip** initialize a Spring Boot project
- **unzip jwt-app && mv jwt-app.zip** unzip project's zipped file and delete it after finishing
- **cd jwt-app && mvn dependency:resolve** navigate to project's root folder and install dependencies.
- **mvn eclipse:eclipse** run this command if you could not import project to eclipse
- **mvn dependency:get -Dartifact=groupId:artifactId:version** use this command if you only want to download a single dependency

## Project structure

''''
├── src
│   ├── main
│   │   ├── java
│   │   │   └── fi
│   │   │       └── aktia
│   │   │           └── demo
│   │   │               └── jwtapp
│   │   │                   ├── DemoApplication.java
│   │   │                   ├── ServletInitializer.java
│   │   │                   ├── bean
│   │   │                   │   ├── PermissionBean.java
│   │   │                   │   ├── RoleBean.java
│   │   │                   │   └── UserBean.java
│   │   │                   ├── config
│   │   │                   │   ├── JwtAuthenticationEntryPoint.java
│   │   │                   │   ├── JwtAuthenticationTokenFilter.java
│   │   │                   │   ├── WebSecurityConfig.java
│   │   │                   │   └── auth
│   │   │                   │       ├── bean
│   │   │                   │       │   ├── AuthRequestBean.java
│   │   │                   │       │   ├── AuthResponseBean.java
│   │   │                   │       │   └── AuthUserDetailsBean.java
│   │   │                   │       ├── controller
│   │   │                   │       │   └── AuthController.java
│   │   │                   │       └── service
│   │   │                   │           ├── AuthService.java
│   │   │                   │           ├── AuthServiceImpl.java
│   │   │                   │           └── AuthUserDetailsServiceImpl.java
│   │   │                   ├── controller
│   │   │                   │   ├── PermissionController.java
│   │   │                   │   ├── RoleController.java
│   │   │                   │   └── UserController.java
│   │   │                   └── repository
│   │   │                       ├── PermissionRepository.java
│   │   │                       ├── RoleRepository.java
│   │   │                       └── UserRepository.java
│   │   ├── resources
│   │   │   ├── application.properties
│   │   │   ├── banner.txt
│   │   │   ├── data.sql
│   │   │   ├── static
│   │   │   └── templates
│   │   └── webapp
│   └── test
│       
└── target
  '''
  
## Testing
- **spring.security.user.name=dev** set default user for **Spring Security**
- **spring.security.user.password=99bbc2a8-70ba-4945-ac79-5da6c9eae268** set default password for **Spring Security**
- **mvn spring-boot:run** starting project and open the following link **http://localhost:8080** using a browser.

## AWS demo user
- **aktia-demo** username
- **okGjrqLUb5adJ3bLwYlG2gO39XCRiI6wLuf+lhy0rH4=** password

## Storing sourcecode on CodeCommit
- **git init** navigate project's root folder and initialize git
- **git remote add origin https://git-codecommit.us-east-1.amazonaws.com/v1/repos/jwt-app** declare a repository in git config file
- **git add . && git commit -m "Initialized a spring boot project" && git push --set-upstream origin master** upload project to **CodeCommit** 

## Demo
- **curl -H "Content-Type: application/json" -X POST --data '{"username":"admin","password":"admin"}' http://localhost:8080/auth** request generating token
- **curl -H "Authorization: eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1ODU1OTA3ODcsImlhdCI6MTU4NDk4NTk4N30.EiDutTBOpNYG7OVWbyHuu-1HBHHuK8yxBaoLNPN2G5N7P0hOIuNMH-in90VKZGDsetJzUOXlXLMveelW4TbHdg" http://localhost:8080/refresh** request refreshing token

## Author
- Dinh Duc Thinh
- Student at **Haaga Helia University**
- Full Stack Developer at **Capgemini Finland**

## Copyright
- © https://www.tikkidinh.com