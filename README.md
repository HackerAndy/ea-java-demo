# Build and run
    MAC/Linux:
    ```sh
    ./mvnw clean --file rest/pom.xml spring-boot:run
    ```
    Windows:
    ```sh
    cd rest
    mvnw.cmd clean --file rest/pom.xml spring-boot:run
    ```

# Testing
http://localhost:8080/employees
http://localhost:8080/swagger-ui/index.html

Execute Tests:
```sh
 ./mvnw test   
 ```

# Other Instructions:

## Test Tutorial:
https://www.bezkoder.com/spring-boot-webmvctest

## Adding OpenAI 3.0 (Swagger)
https://www.baeldung.com/spring-rest-openapi-documentation

## Maven Multi Module
https://spring.io/guides/gs/multi-module/

## Deployments
* [GitHub Actions TO Azure](https://learn.microsoft.com/en-us/azure/app-service/deploy-github-actions?tabs=applevel)

    GitHub Secrets Location (Azure publishing profile)  
    In GitHub:  
    `Settings > Security > Secrets and variables > Actions > New repository secret`

* Spring Boot TO Azure App Services
https://learn.microsoft.com/en-us/training/modules/deploy-java-spring-boot-app-service-mysql/1-introduction 

    ```sh
    cd rest
    ../mvnw com.microsoft.azure:azure-webapp-maven-plugin:1.12.0:config
    ```