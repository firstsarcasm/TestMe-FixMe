[comment]: <> (//todo translate to english)
# IN PROGRESS
# Documentation

All requests instead of get-token и register require authorization header:
Authorization : <get-token result>

1. GET /set-amount  
   ```
    query paramters:
   ```
   | Name          | Type      | Required              | Constraints                  |
   | :-----------: |:---------:|:--------------------: |:----------------------------:|
   | value         | double    | yes                   | grater than zero                  |
   
   Case:  
   1. Method gets number('value')  
   2. Method sets 'amount' value for user as 'value' from request    
   3. Method returns json of the form:  
   ```json
   {  
    "result":"Your amount of money is 10"  
   }  
   ```  
---
2. GET /get-amount   
   Case:  
   1. Method returns the value of amount
   2. Method returns json of the form:
   ```json
   {
    "result":"Your amount of money is 10"
   }
   ```

---
2. GET /add-one 
   Case:  
   1. Method adds one to amount
   2. Method returns json of the form:
   ```json
   {
    "result":"Your amount of money is 11"
   }
   ```

result contains text "Your amount of money is" + value of the "value" parameter

amount is 0 by default   
   

---
4. POST /get-token
   ```
    Descriptiion of body params:
   ```
   | Name          | Type      | Required             | Constraints                  |
   | :-----------: |:---------:|:--------------------:|:----------------------------:|
   | username      | String    | yes                  | grater than zero             |
   | pwd           | String    | yes                  | grater than zero             |
   | email         | String    | yes                  | grater than zero             |
   ```
     Request body example:
   ```
   ```json
    {
      "username": "username",
      "pwd": "password",
      "email": "some email"
    }
   ```

   Case:
   1. Method makes search of user in db
   2. Method produces token for user access to the service
   3. Method returns json of the form:
   ```json
   {
   "result":"Bearer <token-value>"
   }
   ```

---

5. POST /register
   ```
    Descriptiion of body params:
   ```
   | Name          | Type      | Required             | Constraints                  |
   | :-----------: |:---------:|:--------------------:|:----------------------------:|
   | username      | String    | yes                  | grater than zero             |
   | pwd           | String    | yes                  | grater than zero             |
   | email         | String    | yes                  | grater than zero             |
   ```
     Request body example:
   ```
   ```json
    {
      "username": "username",
      "pwd": "password",
      "email": "some email"
    }
   ```
   Сценарий:
   1. Method registers a user
      1. Method genereates unique user key
      2. Method save user data to the table 'user'
   2. Method returns json of the form:
   ```json
   {
    "result":"success"
   }
   ```
---
### Error handling  
   При возниконовении любых ошибок во время работы программы пользователю должен вернуться ответ следющего формата:  
   ```json
   {
     "timestamp": "04-07-2021 08:57:39",
     "status": "ERROR",
     "message": "error description"
   }
   ```

## How to run
java -jar server-*version*-.jar  
Port: 8080

## Swagger

[swagger](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)

## Actuator

[actuator](http://localhost:8080/actuator)

## DB interface

For storing data the service uses an embedded h2 db.  
Credentials are provided in [application.properties](src/main/resources/application.properties) file
