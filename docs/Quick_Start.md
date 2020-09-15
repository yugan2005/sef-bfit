# ISYS3413 Startup code and  project Structure documentation 

# Quick Start

## With your IDE

1. Open the project in your IDE (e.g. IntelliJ IDEA)
1. Set the project as Maven project
1. Prepare the Database: Run  Using your IDE's "Run Java application", run `app.dao.DBMigrate` (located in `src/main/java/app/dao/DBMigrate.java`)
1. Using your IDE's "Run Java application", run `app.App` (located in `src/main/java/app/App.java`)
1. Go to http://localhost:7000
1. Happy Coding!

## maven

1. Install [Apache Maven](https://maven.apache.org/)
1. In your terminal, go to the root of the project. Then run the following commands
1. `mvn flyway:migrate`
1. `mvn compile exec:java`
1. Go to http://localhost:7000
1. Happy Coding!

## Project Structure

| Directory | Purpose |
| --- | --- |
| src/main/ | All application(app) code, including main,models, views(HTML files), controllers, SQL scripts and helpers. |
| src/main/java/app/ | Core application code including main(App.java), model, view, controller and utils. | 
| src/main/java/app/auth/ | Authentication(login) and access manager classes | 
| src/main/java/app/controllers/ | It includes controller for parsing appUser request from view | 
| src/main/java/app/dao/ | Dao includes simple java classes which contains JDBC logic. Dao(Data Access Object) is a design pattern. | 
| src/main/java/app/models/ | Classes for managing the data model and tables | 
| src/main/java/app/utils/ | Helper and util classes. Includes Views.java for mapping and population views, and PasswordGenerators helper class to encrypt the password | 
| src/main/resources| Application view files and assets such as CSS, images and database scripts | 
| src/main/resources/db/migration| Database initialisation SQL scripts for creating tables and inserting values | 
| src/main/resources/public| Applications assets such as cascading style sheets (CSS), JavaScript files, and images | 
| src/main/resources/views| View HTML pages, GUI for users and frontend folders| 
| src/test/java/app/| Application tests, each test has a folder that is referring the code that is testing   | 
| docs/| Project documentation including README and Framework introduction | 
| docs/README.md| A document that includes how to make the project, FAQ, and project structure | 
| docs/FAQ.md| Frequent Asked Questions | 
| docs/Framework.md| A document that includes an intro to Javalin and Maven | 
| pom.xml| POM is Project Object Model, A Maven XML file, that contains information (such as project dependencies) about the project and configuration details used by Maven to build the project. It contains default values for most projects. | 
| README.md| Main Repository README that explains the assignment setup | 
| .gitignore| Patterns for files that should be ignored by Git. | 
| sef-app.iml| IDE's environment setup file. | 

 