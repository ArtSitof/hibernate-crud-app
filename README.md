# hibernate crud-app
Это приложение позволяет производить простые CRUD операции с базой данных.
Дле работы приложения необходимо:
- установить [MySQL](https://dev.mysql.com/downloads/mysql/);
- создать в MySQL пользователя, connection, базу данных;
- созать таблицу **employees** в MySQL:
```MySQL
CREATE TABLE employees (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(15),
  surname varchar(25),
  department varchar(20),
  salary int,
  PRIMARY KEY (id)
);
```
- в среде разработки подключаем Hibernate Framework и добавляем [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/);
- в конфигурационном файле hibernate.cfg.xml указываем свою базу данных, имя пользователя и пароль к базе.

В классе Main реализованы статические методы для CRUD операций.
