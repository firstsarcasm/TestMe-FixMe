[comment]: <> (//todo translate to english)
# IN PROGRESS
# Documentation

All requests instead of get-token и register require aithorization header:
Authorization: <get-token result>

1. GET /set-amount  
   ```
    query paramters:
   ```
   | Name          | Type      | Required              | Constraints                  |
   | :-----------: |:---------:|:--------------------: |:----------------------------:|
   | value         | double    | yes                   | grather than zero                  |
   
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
   1. Метод возвращает значение amount
   2. Возвращает в ответе json вида:
   ```json
   {
    "result":"Your amount of money is 10"
   }
   ```

---
2. GET /add-one 
   Case:  
   1. Метод добавляет единицу к amount
   2. Возвращает в ответе json вида:
   ```json
   {
    "result":"Your amount of money is 11"
   }
   ```

result содержит текст Your amount of money is + значение параметра "value"

Значение amount по умолчанию равно 0

---
4. POST /get-token
   ```
    Описание тела запроса:
   ```
   | Название      | Тип       | Обязательность       | Ограничения                  |
   | :-----------: |:---------:|:--------------------:|:----------------------------:|
   | username      | String    | да                   | Больше нуля                  |
   | pwd           | String    | да                   | Больше нуля                  |
   | email         | String    | да                   | Больше нуля                  |
   ```
     Пример тела запроса:
   ```
   ```json
    {
      "username": "username",
      "pwd": "password",
      "email": "some email"
    }
   ```

   Case:
   1. Метод осуществляет поиск пользователя в базе данных
   2. Метод формирует токен для доступа пользователя к сервису
   3. Возвращает в ответе json вида:
   ```json
   {
   "result":"Bearer <token-value>"
   }
   ```

---

5. POST /register
   ```
    Описание тела запроса:
   ```
   | Название      | Тип       | Обязательность       | Ограничения                  |
      | :-----------: |:---------:|:--------------------:|:----------------------------:|
   | username      | String    | да                   | Больше нуля                  |
   | pwd           | String    | да                   | Больше нуля                  |
   | email         | String    | да                   | Больше нуля                  |
   ```
     Пример тела запроса:
   ```
   ```json
    {
      "username": "username",
      "pwd": "password",
      "email": "some email"
    }
   ```
   Сценарий:
   1. Метод регистрирует пользователя
      1. Метод формирует уникальный ключ пользователя
      2. Метод сохраняет данные пользователя в таблицу user
   2. Возвращает в ответе json вида:
   ```json
   {
    "result":"success"
   }
   ```
---
### Обработка ошибок  
   При возниконовении любых ошибок во время работы программы пользователю должен вернуться ответ следющего формата:  
   ```json
   {
     "timestamp": "04-07-2021 08:57:39",
     "status": "ERROR",
     "message": "error description"
   }
   ```

## Запуск приложения
java -jar server-*version*-.jar  
Порт: 8080

## Swagger

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

## Actuator

http://localhost:8080/actuator

## Интерфейс базы данных

http://localhost:8080/h2-console