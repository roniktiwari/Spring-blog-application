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
                    
## Relationship between domains 

### ONE POST CAN HAVE MULTIPLE COMMENTS  ---> That means post is having one to many relationship with comments 
to stablis this relationshiop we have used JPA anotation such as @OneToMany and @ManyToOne for bi-directional relationship


### 🅰️  @ManyToOne(fetch = FetchType.LAZY)  --> FETCHTYPE.Lazy tells the hibernate to fetch only the related enties from the db when you use the relationship 


# BUILD COMMENT REST API 

RETS APIs for comments resource 

| SR No. | #HTTP Method | #URL Path | #Status code | #Description  |
| :---: | :---: | :---: | :---: | :---: | 
| #1 | GET | /api/posts/{id}/comments | 200 (OK) | Get all comments of a particular post having postId = id | 
| #2 | GET | /api/posts/{id}/comments/{commentId} | 200 (OK) | Get a particular comment having comment id = commentId of a particular post having id = id | 
| #3 | POST | /api/posts/{id}/comments | 201 (Created) | craete a new comment for a particular post having postId = id   | 
| #4 | PUT | /api/posts/{id}/comments/{commentId} | 200K | Update an existing comment having id = commentId for a particular post having  post id =id | 
| #5 | DELETE | /api/posts/{id}/comments/{id}| 200K |  Delete an existing comment with id = commentId for particular post with id = postId | 
| #6 | GET | /api/posts/{id}/comments?pageSize=5 & pageNo=1 & sortBy=name| 200K |  pagination and sorting comments | 


#### 🌀 We validate a java bean with standard framework - JSR 380 also know as bean validation 2.0
#### 🌀 Validation of user input is super common requirement in most application and java bean validation framework become de facto standerd for handling this logic this kind of logic 

#### 🌀 JSR 380 is a specification of JAVA API for bean validation and this ensure that property of bean meet specific creteria
#### 🌀 Hibernate Validator is the reference implementation of validation API
#### 🌀 Springboot provide 


NOTE : - In order to validaton exception response, Global exceptionHandler class extends ResponseEntityExceptionHandler and override its method 

## Now lets come to the security of the application 
##### When we add spring-security dependecy in our application, springframework provide us a default user with user id ad "user" and password will be printed 
##### inside the console of the application whenever we start it. 
##### By Default spring security provide security to all the url of the application.


when you add <dependecncy> springboot-stater-security </dependency> in the pom.xml file , it will download all the spring security related jars in the callpath 
In order to configure.

WebSecurityConfigurerAdapter is a class which implements WebSecurityConfigurer interface which provide default spring security configuration.
we can overide some of the method of the method to customise default security configuration.


The disadvantage of basic auth is that we need to send username and password in every request 

in order to create new user in the application , we need to override "userDetailsService" of the class.

![image](https://user-images.githubusercontent.com/48161515/164883718-cc478b8a-bede-4408-a9a5-3c9b1e0c02b3.png)

In order to implement role based authentication from database. We need to create two entity class -
            
                                                          - User  - one user can have many roles
                                                          - Role  - one role can have attach to many user
                                                          


![image](https://user-images.githubusercontent.com/48161515/164894191-707dedec-edcc-4d1a-bfdf-44e49cb7fa0d.png)


JWT ( JSON AUTHENTICATIONN TOKEN ) is a standard for securing the Rest Api's. It is the best way make secure communication b/w client and server.
It follow stateless authentication mechanism without storing any session information on the server side.

WHEN SHOULD YOU USE JSON WEB TOKEN 
----------------------------------


1. Authorization : It is the most common scenerio to use JWT. Once the user logged in each subsequent request will inculde JWT allowing user to access the resources      that are permitted with that token. Single sign on feature that widely use now a days 

2. information exchange : JSON web token consist of three parts seprated by "."  -


                                    -> Header
                                    -> PayLoad 
                                    -> Signature 

![jwt-token-structure](https://user-images.githubusercontent.com/48161515/164965542-f1f9b0ae-8863-4660-8f88-cc53485b8f87.svg)

#### Header consist of two property : Type of token , Algorithm used 
#### Payload consist of request body data 

#### Signature is the encoded form of (header + payload + secret key)






