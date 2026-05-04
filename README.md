# SECURITY DEMONSTRATION

Моя реализация аутентификации и авторизации за счет ротации *Refresh* и *Access* токенов.

---

## Функционал

- Регистрация и вход в аккаунт
- Аутентификация и авторизация за счет ротации *Refresh* и *Access* токенов
- Различные ресурсы для разных пользователей в качестве примера
- Возможность обновлять доступ к ресурсам и продливать время входа в систему не входя повторно в аккаунт
- Гибкая настройка окружения в `.env`

---

## Технологии

- Java 25
- Spring (**Security**, Boot, Web, Data JPA, Validation)
- Hibernate
- Flyway
- jjwt
- PostgreSQL
- Docker (Dockerfile, docker-compose)

---

## Особенности

- Сложная система аутентификации и авторизации за счет ротации *Refresh* и *Access* токенов
- Ротация *Refresh* и *Access* токенов при заходе на специальный эндпоинт
- Хранение состояния *Refresh* токенов в базе данных
- Наличие времени действия и статуса у *Refresh* токенов
- Валидация просроченных, заблокированных и обновленных *Refresh* токенов
- Обновление времени аутентификации при стабильном посещении ресурсов пользователем
- Защита от CSRF атак (HttpOnly=true, Secure=true, SameSite=Strict)
- Валидация и защита *Access* токенов за счет секретного ключа для их подписи
- 3 заранее созданных пользователя с одноименными ролями для тестировки заготовленных сценариев:
    - username: **user**, password: **user**
    - username: **moderator**, password: **moderator**
    - username: **admin**, password: **admin**

--- 

## Установка

1. Клонируйте репозиторий

```Bash
git clone https://github.com/FedorDymovskij/security-demonstration
``` 

2. Перейдите в папку проекта

```Bash
cd .../security-demonstration
```

3. Установите JDK 25 (Если не установлено)
4. Установите Apache Maven и скомпилируйте проект (либо используйте заранее скомпилированный проект)

```Bash 
./mvnw clean package -DskipTests 
```

5. Настройте .env файл (детали указаны в файле)
6. Соберите все контейнеры и запустите их

```Bash
docker compose up --build
```

#### Если вы используете IDE (например IntelliJ IDEA) вы можете запустить базу данных отдельно

1. Поднимите `docker-compose-database.yml`

```bash
docker compose -f ./docker-compose-database.yml up --build
```

2. Запустите основной проект из вашей IDE

---

## Взаимодействие

### Эндпоинты:

<table width="100%">
  <thead>
    <tr>
    <th>Метод</th>
    <th>URI</th>
    <th>Требования к запросу</th>
    <th>Описание</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><b>POST</b></td>
      <td>/auth/register</td>
      <td>
        <pre><code>{
  "username": "username",
  "password": "password"
}</code></pre></td>
      <td>Регистрация нового аккаунта (роль по умолчанию <b>USER</b>)</td>
    </tr>
    <tr>
      <td><b>POST</b></td>
      <td>/auth/login</td>
      <td>
        <pre><code>{
  "username": "username",
  "password": "password"
}<code></pre></td>
      <td>Вход в существующий аккаунт и выдача <i>Refresh</i> токена в куке и <i>Access</i> токена в <b>JSON</b></td>
    </tr>
    <tr>
      <td><b>POST</b></td>
      <td>/auth/refresh</td>
      <td>Валидный <i>Refresh</i> токен в куке</td>
      <td>Валидация и выдача новой пары <i>Refresh</i> и <i>Access</i> токенов</td>
    </tr>
    <tr>
      <td><b>GET</b></td>
      <td>/public/resource</td>
      <td>Валидный <i>Access</i> токен в заголовке: <code>Authorization: Bearer </code></td>
      <td>Ресурс для всех пользователей</td>
    </tr>
    <tr>
      <td><b>GET</b></td>
      <td>/user/resource</td>
      <td>Валидный <i>Access</i> токен в заголовке: <code>Authorization: Bearer </code></td>
      <td>Ресурс для всех аутентифицированных пользователей с ролью <b>USER</b></td>
    </tr>
    <tr>
      <td><b>GET</b></td>
      <td>/moderator/resource</td>
      <td><i>Access</i> токен в заголовке: <code>Authorization: Bearer </code></td>
      <td>Ресурс для всех аутентифицированных пользователей с ролью <b>MODERATOR</b></td>
    </tr>
    <tr>
      <td><b>GET</b></td>
      <td>/admin/resource</td>
      <td><i>Access</i> токен в заголовке: <code>Authorization: Bearer </code></td>
      <td>Ресурс для всех аутентифицированных пользователей с ролью <b>ADMIN</b></td>
    </tr>
  </tbody>
</table>

#### Подсказка по статус-кодам

* `200 OK` — Успешно.
* `401 Unauthorized` — *Access* токен недействителен или отсутствует.
* `403 Forbidden` — Недостаточно прав (например пользователь с ролью **USER** пытается зайти на защищенный ресурс
  `/admin`).

## Что будет добавлено

1. Эндпоинт для выхода из аккаунта
2. CRUD - операции для управления аккаунтами и сессиями
3. Иерархия ролей по правам доступа
4. Простой фронтэнд для полноценной демонстрации проекта
5. Рефакторинг кода под гексагональную архитектуру ради модульности
