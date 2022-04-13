1. Spring Boot (Data JPA, REST, Security) - has very much out of box already tested features which allows 
to build a REST API with minimum of custom code: Controllers definition, Validation, Exception Handling, 
Embedded Tomcat server, Security Configuration, Jackson Serialization/Deserialization, Declarative Transaction Management, etc.
The negative side of Spring Boot usage is that it pretty much increases the bootstrap time of an application.
As an alternative we can also consider plan Java 11 with HttpServer, JDBI and custom Web Security Management,
but this will totally increase the development time and should be used only to achieve the fastest bootstrap time and
the lowest memory consumption of an application. The above considerations made in scope of possible extension of 
an application functionality.
2. PostgreSQL - relational database fits pretty well into the given domain of task. PostgreSQL is a relatively 
lightweight, has pretty good default setting and relatively easy to configure and also PostgreSQL is a free software. 
We can also choose MySQL for the same reasons, but as for me I have less experience with this one than with PostgreSQL. 
I will not recommend Oracle because it is a proprietary software, harder to configure and has very much built-in
features and systems which are not required in scope of this task. NoSQL databases here will not give us any benefits 
over relational databases in scope of this task.
3. I would consider the possible load of this application and privacy of images that Users may load as their photo 
or as image for their Idea. In a code I introduced a stub for AWS S3 usage as storage for such images, but in this case 
images should only have public access in S3. If we want to have them private then we should use CDN. 
I would not recommend storing images as BLOB columns in database as soon as size of database will depend very much 
on how many images Users uploaded. Currently I've implemented only one image per User and one image per idea, 
but the scope of the task may be changed of course. Another point to consider is content of images: it must 
be appropriate and we can use AWS Rekognition to identify if image is appropriate to be shown in public resource. 
4. Security of the API is handled by Spring Security with authentication and authorization based on JWT. 
JWT stands for stateless which is required in REST API. Here, we must have also an endpoint to refresh the token, 
but unfortunately here I did not implement it. We can also consider Spring Boot Keycloak integration for identity and 
access management for possible future implementation of SSO for example via Google, Facebook or other services.
5. I introduced Swagger in this application, so the API is pretty well documented by default and can be documented 
even better if we customize the description of RestControllers via Swagger annotations. Basically, I'll focus on 
possible synchronous calls of an API and how do methods work together. For example, when User creates an Idea, 
a frontend must send two synchronous requests: first to upload Idea data, and then to upload an Idea image. 
Also backend and frontend might discuss the process of JWT token refresh.
    
         
