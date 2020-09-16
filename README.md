# RMIT ISYS 3413 Group 05

## Members
* Amante Docherty - s3785537
* Haonan Jiang - s3652190
* Sarah Bylmakers - s3736964
* Seng Long Huo - s3781162
* Zhi Jiang - s3596657

## Records

* Github repository : https://github.com/isys3413-sef-2020-sem2/team-project-group-5
* ClickUp Workspace : https://app.clickup.com/6916684/v/l/6k2jc-149?pr=6940613

## Code documentation

Please read the [Documentation](/docs) in `docs` folder.
Read the [Quick start](/docs/Quick_Start.md) to boot your application.

### Users

* Admin `Administrator`
  * email: admin@test.com
  * password: admin
* Admin `SEF Tutor`
  * email : tutor@rmit.edu.au
  * password : tutor   
* User `Customer`
  * email : user@test.com
  * password : user 
  
# Run it
**Note** Java version is 11. 

If the default JDK is Java 8, (run `javac -version`) you need set up the `JAVA_HOME` environment variable before compile.

* `export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk11.0.7-zulu.jdk/Contents/Home` (on mac, configure Java to V11)

If you don't have `maven` installed (run `which mvn`), you can install it as: `brew install maven` (on mac)

Enter the project root folder run:

* `mvn clean package`
* `java -jar target/sef-bfit-0.1-SNAPSHOT.jar`


go to `localhost:8080` get to the homepage.

Login by different users, you get different privileges. 

If you want to check database directly, you can go to `localhost:8080/h2-console`, database path file should be:
 
 `jdbc:h2:file:./target/webapp`
