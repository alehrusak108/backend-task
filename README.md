1. Spring Boot (Data JPA, REST, Security) - has very much out of box already tested features which allows 
to build a REST API with minimum of custom code: Controllers definition, Validation, Exception Handling, 
Embedded Tomcat server, Security Configuration, Jackson Serialization/Deserialization, Declarative Transaction Management, etc.
The negative side of Spring Boot usage is that it pretty much increases the bootstrap time of an application.
As an alternative we can also consider plan Java 11 with HttpServer, JDBI and custom Web Security implementation,
but this will totally increase the development time as long as possible issues in testing phase and should be used
only to achieve the fastest bootstrap time and the lowest memory consumption of an application. Prometheus can be used to collect metrics. It has integration with Spring Boot Actuator 
where all REST methods metrics are exposed (execution time, counts of failed/success requests, etc.). 
Specific metrics must be added to a service layer of an application, especially when calling third-party services, 
like AWS. Also third-party services calls should be resilient: we can use Resilience4J and its integration with Spring 
to provide Retry policy. For example AmasonS3Service methods must be resilient and metered.
 
2. PostgreSQL - relational database fits pretty well into the given domain of task. PostgreSQL is a relatively 
lightweight, has pretty good default setting, relatively easy to configure and is a free software. 
We can also choose MySQL for the same reasons, but as for me I have more experience with PostgreSQL than with MySQL. 
NoSQL databases here will not give us any benefits over relational databases in scope of this task.

3. I would consider the possible load of this application. The other thing to consider is privacy of images that Users 
may load as their photo or as image for their Idea. In a code I introduced a stub for AWS S3 usage as storage for such 
images, but in this case images should only have public access in S3 to be displayed to the User in browser. If we want 
to have them private then we should use CDN. I would not recommend storing images as BLOB columns in database as soon 
as size of database will depend very much on how many images Users uploaded. Currently I've implemented only one image
per User and one image per idea, but the scope of the task may be changed of course. Another point to consider is content 
of images: it must be appropriate. We can use AWS Rekognition to identify if image is appropriate to be shown in public resource.
 
4. Security of the API is handled by Spring Security with authentication and authorization based on JWT. 
JWT stands for stateless which is required in REST API. Here, we must have also an endpoint to refresh the token, 
but unfortunately here I did not implement it. We can also consider Spring Boot Keycloak integration for identity and 
access management for possible future implementation of SSO for example via Google, Facebook or other services.

5. I introduced Swagger in this application, so the API is pretty well documented by default and can be documented 
even better if we customize the description of RestControllers via Swagger annotations. Basically, I'll focus on 
possible synchronous calls of an API and how do specific REST methods must work together. For example, when User 
creates an Idea, a frontend must send two synchronous requests: first to upload Idea data, and then to upload an Idea image. 
Also backend and frontend might discuss the process of JWT token retrieval and refresh.

Please, consider this application more as an example of possible solution. Not all implemented functionality 
was fully tested and not all issues are fixed at this moment. Also, I did not write tests here as soon as it 
can require some more time to have the API fully covered with test. I would suggest the following testing strategy: 
service layer of an application must be covered by Unit Tests using JUnit and Mockito. Then we can use SpringBootTest 
to write an integration tests which will have an embedded in-memory PostgreSQL database and data initialization/cleanup 
methods for each test. In this case we can use MockMvc and RestControllers will be the subject of these integration tests.
    
         
