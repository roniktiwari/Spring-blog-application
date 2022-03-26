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

## REST API FOR POST RESOURCE 

| SR No. | #HTTP Method | #URL Path | #Status code | #Description  |
| :---: | :---: | :---: | :---: | :---: | 
| #1 | GET | /api/posts | 200 (OK) | Get all posts| 
| #2 | GET | /api/posts/{id} | 200 (OK) | Get post by id | 
| #3 | POST | /api/posts | 201 (Created) | craete a new post | 
| #4 | PUT | /api/posts/{id} | 200K | Update existing post with id | 
| #5 | DELETE | /api/posts/{id} | 200K |  Delete an existing post with id | 
| #6 | GET | /api/posts?pageSize=5 & pageNo=1 & sortBy=firstName| 200K |  pagination and sorting post | 
              
             
 ## REST APIs FOR POST COMMENT RESOURCE 
 
 
| SR No. | #HTTP Method | #URL Path | #Status code | #Description  |
| :---: | :---: | :---: | :---: | :---: |  
| #1 | GET | /api/posts/{postId}/comments | 200 (OK) | Get the list of all the comment for a particular post | 
| #2 | GET | /api/posts/{postId}/comments/{id} | 200 (OK) | Get an specific comment by id for a partiulcar post by postId | 
| #3 | POST | /api/posts/{postId}/comments | 201 (Created) | craete a comment for a partiular post having id = postId | 
| #4 | PUT | /api/posts/{postId}/comments/{id} | 200K | Update an existing comment by commentId for particular post having postId | 
| #5 | DELETE | /api/posts/{id}/comments/{commentId} | 200K |  Delete an existing comment by commentId for a particular post having id as postId | 
| #5 | GET | /api/posts?pageSize=5 & pageNo=1 & sortBy=firstName| 200K |  pagination and sorting post | 



## REST APIs FOR SIGNUP APPLICATION

| SR No. | #HTTP Method | #URL Path | #Status code | #Description  |
| :---: | :---: | :---: | :---: | :---: | 
| #1 | POST | /api/auth/signup | 200 (OK) | signup | 
| #2 | GET | /api/auth/signin | 200 (OK)  | signin  | 

### FIRST STEP TO CRAETE A MAVEN PROJECT WITH DEPENDECY SHOWING IN THE IMAGE 


![image](https://user-images.githubusercontent.com/48161515/158632178-8f16238b-308d-4f49-aeee-ad8b5a44ae2d.png)



IMPORTANT POINT :- 
 
Whenever we add spring-data-jpa dependecy on class Path , It will try to create datasource object by looking into application.properties files
We will provide several configuration property in properties file to configure JPA 

#### we specify dialect so that hibernate can generate appropriate type of SQL statement for corresponding database configured 

## Q. Why we don't right @Repository on the interface which implement JpaRepository?
###  ANS . We don't need to right @Repository explicitly on the interface beacuse class which implement JpaRepository alread have it by default

![image](https://user-images.githubusercontent.com/48161515/158828333-7d0870d1-8bb3-4513-9088-86ba55546744.png)

- @ResponseStatus is used on the top of exception class , which cause the springboot to respond with the specified HTTP code

- DTO Stands for data transfer object , we pass dto from one layer to another 
- So whenever client stand the json payload it get converted to corresponding java object (DTO) for the conversion we use @RequestBody anotation 
- Returning directly entity may lead to security issue 



## PAGINATION AND SORTING 

- Pagination and sorting are mostly required when we want to display domain data in tabular form in UI .
- Pagination allow user to view the small portion of data at a time 
- sorting allow user to view the data in more organised way
- Pagination consist of two fields �
                
                - Page Number 
                - Page Size 

- Sorting is done on a single or multiple feild on a table 
- Generally pagination and sorting parameter are optional and thus part of request url as query parameters
- The default pagination and sorting value shall be clearly documeted in API Docs .

                                       - http://localhost:8080/api/posts?pageSize=5
                                       - http://localhost:8080/api/posts?pageSize=5&geNumner=6
                                       - http://localhost:8080/api/posts?pageSize=5&geNumner=6&sortBy=title
                                     
-There are two java interface that help in achieving sorting and pagination
                  
                    - Pageable 
                    - Page 
                    
   
