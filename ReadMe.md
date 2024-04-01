<p align="center" style="font-size: 50px; font-weight: bold">
  Decardo
</p>

<p align="center" style="font-size: 35px; font-weight: bold">
  Технічне завдання
</p>

<p align="center" style="font-size: 25px; font-weight: bold">
  Предметна область
</p>

3D Арт-галерея - це онлайн-платформа, де автори можуть демонструвати свої 3D роботи, а користувачі можуть переглядати, коментувати та оцінювати їх. Основні сутності предметної області включають роботи (3D моделі), авторів цих робіт, ціну, коментарі користувачів та оцінки. Користувачі можуть бути як звичайними відвідувачами, так і адміністраторами, які мають права на додавання та редагування контенту.

<p align="center" style="font-size: 25px; font-weight: bold">
  Опис функціональності
</p>

1) Система повинна дозволяти користувачам переглядати колекції 3D арт-робіт.
2) Користувачі повинні мати можливість шукати роботи за різними критеріями, такими як автор, жанр, рік створення і т.д.
3) Користувачі можуть залишати коментарі та оцінки для робіт.
4) Адміністратор повинен мати можливість додавати, видаляти та редагувати роботи та інформацію про авторів.
5) Адміністратор повинен мати можливість модерувати коментарі та оцінки.

<p align="center" style="font-size: 50px; font-weight: bold">
  Endpoint
</p>

Посилання на swagger [{baseUrl}/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

<p align="center" style="font-size: 50px; font-weight: bold">
  База даних
</p>

<p align="center" style="font-size: 35px; font-weight: bold">
  Структура таблиць
</p>

<p align="center" style="font-size: 25px; font-weight: bold">
  Таблиця "USER"
</p>

Таблиця "USER" містить інформацію про користувачів системи.

| Стовпець | Тип          | Опис                                                 |
|----------|--------------|------------------------------------------------------|
| ID       | SERIAL       | Унікальний ідентифікатор користувача                 |
| USERNAME | VARCHAR(255) | Ім'я користувача                                     |
| PASSWORD | VARCHAR(255) | Пароль користувача                                   |
| EMAIL    | VARCHAR(255) | Адреса електронної пошти користувача (необов'язково) |
| ROLE     | VARCHAR(5)   | Роль користувача в системі (за замовчуванням 'user') |
| DETAILS  | TEXT         | Додаткові деталі про користувача                     |

<p align="center" style="font-size: 25px; font-weight: bold">
  Таблиця "WORK"
</p>

Таблиця "WORK" містить інформацію про роботи в системі.

| Стовпець    | Тип          | Опис                                   |
|-------------|--------------|----------------------------------------|
| ID          | SERIAL       | Унікальний ідентифікатор роботи        |
| TITLE       | VARCHAR(255) | Назва роботи                           |
| AUTHOR_ID   | INT          | Унікальний ідентифікатор автора роботи |
| DESCRIPTION | TEXT         | Опис роботи                            |
| CREATE_DATE | TIMESTAMP    | Дата та час створення роботи           |
| FILE_URL    | VARCHAR(255) | URL-адреса файлу з роботою             |

<p align="center" style="font-size: 25px; font-weight: bold">
  Таблиця "WATCHLIST"
</p>

Таблиця "WATCHLIST" відображає список для перегляду користувачів.

| Стовпець | Тип | Опис                                 |
|----------|-----|--------------------------------------|
| WORK_ID  | INT | Унікальний ідентифікатор роботи      |
| USER_ID  | INT | Унікальний ідентифікатор користувача |

<p align="center" style="font-size: 25px; font-weight: bold">
  Таблиця "COMMENT"
</p>

Таблиця "COMMENT" зберігає коментарі користувачів до робіт.

| Стовпець    | Тип       | Опис                                 |
|-------------|-----------|--------------------------------------|
| WORK_ID     | INT       | Унікальний ідентифікатор роботи      |
| USER_ID     | INT       | Унікальний ідентифікатор користувача |
| COMMENT     | TEXT      | Текст коментаря                      |
| CREATE_DATE | TIMESTAMP | Дата та час створення коментаря      |

<p align="center" style="font-size: 25px; font-weight: bold">
  Таблиця "RATING"
</p>

Таблиця "RATING" відображає рейтинги користувачів для робіт.

| Стовпець    | Тип       | Опис                                 |
|-------------|-----------|--------------------------------------|
| WORK_ID     | INT       | Унікальний ідентифікатор роботи      |
| USER_ID     | INT       | Унікальний ідентифікатор користувача |
| RATING      | INT       | Оцінка, яку поставив користувач      |
| CREATE_DATE | TIMESTAMP | Дата та час створення оцінки         |

<p align="center" style="font-size: 25px; font-weight: bold">
  ER діаграма
</p>

<p align="center">
  <img style="height: 600px; width: 1100px" alt="ER diagrams" src="diagrams/png/ER-diagram.png" />
</p>

<p align="center" style="font-size: 35px; font-weight: bold">
  Запити до модулю 1
</p>

<p align="center" style="font-size: 20px; font-weight: bold">
  Прості запити
</p>

1) Вибрати всі роботи (заголовки та описи)

```sql
SELECT TITLE, DESCRIPTION
FROM "WORK";
```

2) Вибрати всіх користувачів з їхніми ролями

```sql
SELECT USERNAME, ROLE
FROM "USER";
```

3) Вибрати всі коментарі для конкретної роботи.

```sql
SELECT COMMENT
FROM "COMMENT"
WHERE WORK_ID = 1;
```

4) Вибрати всі роботи, які були створені після певної дати.

```sql
SELECT TITLE
FROM "WORK"
WHERE CREATE_DATE > '2024-03-01';
```

5) Вибрати всі рейтинги для певного користувача.

```sql
SELECT RATING
FROM "RATING"
WHERE USER_ID = 1;
```

<p align="center" style="font-size: 20px; font-weight: bold">
  Агрегатні функції
</p>

1) Знайти кількість робіт у базі

```sql
SELECT COUNT(*)
FROM "WORK";
```

2) Знайти середній рейтинг усіх робіт

```sql
SELECT AVG(RATING)
FROM "RATING";
```

3) Знайти максимальну кількість коментарів для роботи.

```sql
SELECT MAX(CNT)
FROM (SELECT COUNT(*) AS CNT
      FROM "COMMENT"
      GROUP BY WORK_ID) AS SUBQUERY;
```

4) Знайти мінімальний рейтинг для кожної роботи

```sql
SELECT WORK_ID, MIN(RATING)
FROM "RATING"
GROUP BY WORK_ID;
```

5) Знайти загальну кількість робіт, які зберігаються у відстежуванні для кожного користувача.

```sql
SELECT USER_ID, COUNT(*)
FROM "WATCHLIST"
GROUP BY USER_ID;
```

<p align="center" style="font-size: 20px; font-weight: bold">
  Запити з групуванням
</p>

1) Знайти кількість робіт, які створив кожний користувач

```sql
SELECT U.USERNAME, COUNT(*)
FROM "WORK" W
         JOIN "USER" U ON W.AUTHOR_ID = U.ID
GROUP BY U.USERNAME;
```

2) Знайти середній рейтинг усіх робіт

```sql
SELECT WORK_ID, AVG(RATING)
FROM "RATING"
GROUP BY WORK_ID;
```

3) Знайти кількість коментарів для кожного користувача

```sql
SELECT USER_ID, COUNT(*)
FROM "COMMENT"
GROUP BY USER_ID;
```

4) Знайти середню кількість робіт, які додані до відстежування кожним користувачем

```sql
SELECT USER_ID, AVG(CNT)
FROM (SELECT USER_ID, COUNT(*) AS CNT
      FROM "WATCHLIST"
      GROUP BY USER_ID) AS SUBQUERY
GROUP BY USER_ID;
```

5) Знайти загальну кількість коментарів для кожної роботи

```sql
SELECT WORK_ID, COUNT(*)
FROM "COMMENT"
GROUP BY WORK_ID;
```

<p align="center" style="font-size: 20px; font-weight: bold">
  Вкладені запити
</p>

1) Знайти всі роботи, до яких є коментарі

```sql
SELECT *
FROM "WORK"
WHERE ID IN (SELECT DISTINCT WORK_ID FROM "COMMENT");
```

2) Знайти всіх користувачів, які залишили коментарі до робіт, які вони самі створили

```sql
SELECT U.*
FROM "USER" U
         JOIN "COMMENT" C ON U.ID = C.USER_ID
         JOIN "WORK" W ON C.WORK_ID = W.ID AND W.AUTHOR_ID = U.ID;
```

3) Знайти всі роботи, які знаходяться у відстежуванні користувача з найбільшим ID

```sql
SELECT *
FROM "WORK"
WHERE ID IN (SELECT WORK_ID FROM "WATCHLIST" WHERE USER_ID = (SELECT MAX(ID) FROM "USER"));
```

4) Знайти всі роботи, які мають рейтинг 5 і в них є коментарі.

```sql
SELECT *
FROM "WORK"
WHERE ID IN (
    SELECT WORK_ID
    FROM "RATING"
    WHERE RATING = 5
) AND ID IN (
    SELECT DISTINCT WORK_ID
    FROM "COMMENT"
);
```

5) Знайти всіх користувачів, які залишили коментарі до робіт, які знаходяться у відстежуванні користувача з роллю 'admin'
```sql
SELECT DISTINCT u.*
FROM "USER" u
         JOIN "COMMENT" c ON u.ID = c.USER_ID
         JOIN "WATCHLIST" w ON c.WORK_ID = w.WORK_ID
         JOIN "USER" u_admin ON w.USER_ID = u_admin.ID AND u_admin.ROLE = 'admin';
```

<p align="center" style="font-size: 20px; font-weight: bold">
  Багатотабличні запити
</p>

1) Знайти всі роботи, що містяться у відстежуванні користувача з певною ролью

```sql
SELECT w.*
FROM "WORK" w
         JOIN "WATCHLIST" wl ON w.ID = wl.WORK_ID
         JOIN "USER" u ON wl.USER_ID = u.ID AND u.ROLE = 'admin';
```

2) Знайти всі коментарі для робіт, що містяться у відстежуванні користувача з певною ролью

```sql
SELECT c.*
FROM "COMMENT" c
         JOIN "WORK" w ON c.WORK_ID = w.ID
         JOIN "WATCHLIST" wl ON w.ID = wl.WORK_ID
         JOIN "USER" u ON wl.USER_ID = u.ID AND u.ROLE = 'admin';
```

3) Знайти всі рейтинги для робіт, що мають коментарі від користувачів з певною ролью.

```sql
SELECT r.*
FROM "RATING" r
         JOIN "WORK" w ON r.WORK_ID = w.ID
         JOIN "COMMENT" c ON w.ID = c.WORK_ID
         JOIN "USER" u ON c.USER_ID = u.ID AND u.ROLE = 'admin';
```

4) Знайти всі рейтинги для робіт, які знаходяться у відстежуванні користувача з певною ролью

```sql
SELECT r.*
FROM "RATING" r
         JOIN "WATCHLIST" wl ON r.WORK_ID = wl.WORK_ID
         JOIN "USER" u ON wl.USER_ID = u.ID AND u.ROLE = 'admin';
```

5) Знайти всі коментарі та рейтинги для робіт, що мають описи, що починаються з певної літери
```sql
SELECT c.*, r.*
FROM "COMMENT" c
         JOIN "RATING" r ON c.WORK_ID = r.WORK_ID
         JOIN "WORK" w ON c.WORK_ID = w.ID
WHERE LEFT(w.DESCRIPTION, 1) = 'D';
```