# Тех. задание

1. GET /set-amount  
   ```
    query параметры:
   ```
   | Название      | Тип       |
   | :-----------: |:---------:|
   | value         | double    |
   
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

## Запуск приложения
java -jar server-*version*-.jar  
Порт: 8080
