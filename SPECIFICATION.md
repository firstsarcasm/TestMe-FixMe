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
4. GET /get-token
   Сценарий:
   1. Метод формирует токен для доступа пользователя к сервису
   2. Возвращает в ответе json вида:
   ```json
   {
   "result":"Bearer <token-value>"
   }
   ```

---

5. GET /register
   Сценарий:
   1. Метод регистрирует пользователя
   2. Возвращает в ответе json вида:
   ```json
   {
    "result":"success"
   }
   ```

## Запуск приложения
java -jar server-*version*-.jar  
Порт: 8080

## Swagger

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

## Actuator

http://localhost:8080/actuator

##

http://localhost:8080/h2-console