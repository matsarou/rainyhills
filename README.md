# rainyhills

The application contains:
1) one REST layer implemented with Jersey, which delegates the logic to the business/service layer
2) one business/service layer implemented with Java 8 and CDI for dependency injection, which calculates the volume of the remaining water

You can deploy the application with a server container , for example Wildfly.
Build the project by running gradlew build
Add the produced .war file to the Wildfly folder deployments
Start the server by running standalone.bat
Hit the following urls at a browser:
http://localhost:8080/rainyhills/api/volume/3,2,4,1,2
or
http://localhost:8080/rainyhills/api/volume/4 1 1 0 2 3

