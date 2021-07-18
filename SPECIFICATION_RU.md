# Тех. задание

Все запросы кроме get-token и register требуют наличия header'a атворизации:
Authorization: <результат выполнения метода get-token>

1. GET /set-amount  
   ```
    query параметры:
   ```
   | Название      | Тип       | Обязательность       | Ограничения                  |
   | :-----------: |:---------:|:--------------------:|:----------------------------:|
   | value         | double    | да                   | Больше нуля                  |
   
   Сценарий:  
   1. Метод принимает число(value) на вход  
   2. Устанавливает значение amount пользователя равное value    
   3. Возвращает в ответе json вида:  
   ```json
   {  
    "result":"Your amount of money is 10"  
   }  
   ```  
---
2. GET /get-amount   
Сценарий:
   1. Метод возвращает значение amount
   2. Возвращает в ответе json вида:
   ```json
   {
    "result":"Your amount of money is 10"
   }
   ```

---
2. GET /add-one
Сценарий:
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

   Сценарий:
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

[swagger](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)

## Actuator

[actuator](http://localhost:8080/actuator)

## Интерфейс базы данных

Для хранения данных сервис использует встроенную БД h2.   
Коннекты к базе можно найти в файле [application.properties](src/main/resources/application.properties)