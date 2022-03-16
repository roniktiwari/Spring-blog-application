# Spring-blog-application

1.     This is a spring based application
                    - In this project we are building the rest APIs
                             - REST stands for Representational state transfer
                             - State means Data , Representational means Data fronat ( it can be xml , json , html etc )
                             - tansfer means carring data between client and server
                   
2.    Client will send http request to the server , server will process the request and prepare the respsose accordingly 

3.    How we can say that a particular API is Rest API  ? 
                                  - Client server server should be independent of each other ,  it doesn't matter i what language the backend of the application is developed 
                                  - Stateless   : - No data should be stored on the server during the processing of the request , The state of session should be saved on clients 
                                                    ends 
                                                    
                                  - The client should have the ability to store responses in cache , this greatly improve the performance of the API 
                                  - The server can have mutiple layers for implementation , this layerd architecture improve scalability by enabling load balancing 
                             
4. The fundamental concept of REST based system is "Resource" 

5.  Resource is anything that you want to expose to the outside world through your application 

6.  So whenever we are going to create an application , we should first identify the resources in our application 
                                                          - For springboot blog application 
                                                               :-   POST 
                                                               :-   Comment
                                                               :-   User
                                                               :-   Tags 
                                                              
7. The Resources can be identified using UNIFORM RESOURCE IDENTIFIER ( URI ) for web based system .
                            - For web based system , HTTP is the most common protocol to communicate with the external system
                            - One can identify a unique resource using a URI 
                            
   Consider we are developing a blog application : - 
     - we can define several endpoints as  - 
                                         - GET  : http://localhost:8080/api/posts    // Return list of all posts 
                                         - GET  : https://localhost:8080/api/posts/2  // Return a post whose ID is 2 
                                         - POST : https://localhost:8080/api/posts    // create a new post 
                    
8. In REST based Applications 🦖 : The Relationship are often modelled by a sub Resource :

                                                  -  / posts / {postId} / comments   --- {Return List of Comments for particular post }
                                                  -  /posts / { postId } / comments / {commentId} ------{ Return a particular comment of a post }
   

                                       NOTE :-  USE SUB RESOURCES WHEN CHILD OBJECT DOES NOT EXIST WITHOUT PARENT 
                                                   -- For ex Comments does not exist without post 
                  
9. We will be developing the complete backend of web application in this project 

10. High level requirement of the project 

                     - User should be able to delete , update , create , retrieve posts
                     - User Should be able to perform pagination and sorting 
                     - user should be able to create , update , delete comments for posts
                     - User should be able to register to blog Application
                     - User should be able login to blog Application

11. We will be building Rest APIs supporting pagination and sorting , logon and signup .
12. Proper Rest APIs Exception handling and error handling 
13. Securing Rest APIs with Role Based Security 
14. Use token Based Authentication 
15. Document all the rest APIs for consumers 
16. We will deploy our blog Application in production 




TECH STACK 
---------------

JAVA PLATFORM : -  JAVA 8+ 

FRAMEWORK  : -  Spring Framework and its sub packages like SPRING-DATA-JPA, SPRING-BOOT ,SPRING-SECURITY

BUILD TOOL : - MAVEN

DATABASE : MYSQL 

REST CLIENT : - POSTMAN 

IDE :- INTELLIJE IDEA

PRODUCTION :- AWS 

REST API DUCUMENTATION : - SWAGGER 



We are building rest APIs for blog Application . The Resources that can be identified in blog domain :- 
                                    
                                     - Post 
                                     - Comment 
                                     - User 
                                     
The Application will be build on three tire Architecture 
            - Controller  (         Also known as API layer                   )
            - Service     (         Contains Bussiness Logic                  )       
            - Dao         (         to perform database related operation     )
            
1. DTO  -- DTO Will be passed from one layer to another layer 



| Attempt | #1 | #2 | #3 | #4 | #5 | #6 | #7 | #8 | #9 | #10 | #11 | #12 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| Seconds | 301 | 283 | 290 | 286 | 289 | 285 | 287 | 287 | 272 | 276 | 269 | 254 |
               
              
