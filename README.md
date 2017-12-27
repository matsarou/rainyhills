# rainyhills

To run the application:
add the rainyhills.war in the WILDFLY_HOME\standalone\deployments and hit:
http://localhost:8080/rainyhills/api/volume/3,2,4,1,2
http://localhost:8080/rainyhills/api/volume/4 1 1 0 2 3
http://localhost:8080/rainyhills/api/volume/4 3,6 , 8
http://localhost:8080/rainyhills/api/volume/error
http://localhost:8080/rainyhills/api/volume/4,3,tt,7
In the first three examples you will take the integer volume.
In the last two examples you will take an error with code and description.


The application contains:
1) one REST layer implemented with Jersey, which delegates the logic to the business/service layer
2) one business/service layer implemented with Java 8 and CDI for dependency injection, which calculates the volume of the remaining water

I tested my code with junit tests, which are included inside the class VolumeTest.java

I used gradle to build my application.
In order to build I run:
 gradlew clean build
The produced .war file contains all the classes and it is located under the folder build\libs

As server container I used Wildfly.
In order to deploy my application, I added the .war file under the folder WILDFLY_HOME\standalone\deployments
and I started the server by running standalone.bat inside the folder bin.

The algorithm that calculates the volume has the following logic:
I assumed that each number of the given array represents one point on the surface,
and that each point has a current state and a previous state.
The current state is the state after the rain and the previous is the initial number.
For each point of the surface, I calculated the diff of the two states (current - previous), and I assumed that the volume is the
sum of these diff numbers.
I used two arrays that keep the current and previous state of each point.
The first array keeps the point with the maximum height on the left of the point.(LMH)
The second array keeps the point with the maximum height on the right of the point.(RMH)
The current state is the minimum of the LMH and RMH.
The time complexity of the algorithm is O(N^2), because when the size of the array doubles, the running time increses by N*N

A possible improvement is to keep the left and right maximum height for each point in one array.
This approach would give time complexity O(N), beacuse we would have to iterate over the array only once.