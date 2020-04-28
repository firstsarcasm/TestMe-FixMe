# Тех. задание

1. GET /set-amount
query параметры:
  Название: value  
  Тип: double
Сценарий:
1. Метод принимает число(value) на вход
2. Устанавливает значение amount пользователя равное value
3. Возвращает в ответе json вида
{
 "result":"Your amount of money is 10"
}
  
2. GET /get-amount
Сценарий:
1. Метод возвращает значение amount
2. Возвращает в ответе json вида
{
 "result":"Your amount of money is 10"
}

2. GET /add-one
Сценарий:
1. Метод добавляет единицу к amount
2. Возвращает в ответе json вида
{
 "result":"Your amount of money is 11"
}
  
result содержит текст Your amount of money is + значение параметра value, переданного в запросе