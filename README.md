# API de Gerenciamento de Tarefas

Esta é uma API simples desenvolvida com Java e Spring Boot, com funcionalidades para gerenciamento de usuários e tarefas.

## Rotas da API

### 1. **POST /auth/login**
Autentica um usuário. Retorna um token de autenticação.

**Request Body:**
```json
{
  "username": "umCaraLegal",
  "password": "umaSenhaDificil!"
}
```

**Response:**
- **200 OK**: Retorna um token JWT.
- **400 Bad Request**: Se o usuário ou senha estiverem incorretos.

---

### 2. **POST /user**
Cria um novo usuário no sistema.

**Request Body:**
```json
{
   "name": "Kayo",
   "username": "kayoMacedo",
   "email": "kayo@gmail.com",
   "password": "playboiCartiFrances"
}
```

**Response:**
- **201 Created**: Usuário criado com sucesso.
- **400 Bad Request**: Se os dados de entrada estiverem incorretos.

---

### 3. **POST /task**
Cria uma nova tarefa para o usuário autenticado.

**Request Body:**
```json
{
   "title": "ir ao mercado",
   "finished": false,
   "createdAt": "2025-03-21T12:04:07.822+00:00",
   "updatedAt": "2025-03-21T12:04:07.822+00:00",
   "id": 1
}
```

**Response:**
- **201 Created**: Tarefa criada com sucesso.
- **400 Bad Request**: Se os dados da tarefa forem inválidos.
- **403 Unauthorized**: Se o usuário não estiver autenticado.

---

### 4. **GET /task?page=0**
Retorna a lista de tarefas do usuário autenticado, com paginação.

**Query Parameters:**
- `page`: Número da página para a consulta (default: 0).

**Response:**
- **200 OK**: Lista de tarefas.
- **403 Unauthorized**: Se o usuário não estiver autenticado.

---

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Security
- JPA 
- MySQL

---
