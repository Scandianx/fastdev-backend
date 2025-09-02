# FastDev Backend

[PT-BR](#pt-br) • [EN](#en)

---

## PT-BR

### Visão Geral
FastDev Backend é uma API REST em Java com Spring Boot para autenticação, gestão de vídeos e comentários, com controle de acesso baseado em níveis. O objetivo é entregar uma base sólida, escalável e segura para aplicações web e mobile.

### Principais Recursos
- Autenticação com JWT
- Cadastro e listagem de vídeos
- Comentários por vídeo
- Validações com Jakarta Bean Validation
- Estrutura em camadas (controller, service, repository, entity, dto)
- CORS habilitado

### Stack
- Java 21
- Spring Boot 3.x
- Spring Web, Spring Security, Validation
- JPA/Hibernate
- Banco relacional compatível com PostgreSQL, Oracle ou H2
- Maven

### Clonar o Repositório
```bash
git clone https://github.com/Scandianx/fastdev-backend
cd fastdev-backend
```

### Pré-requisitos
- JDK 21
- Maven 3.9+
- Banco de dados rodando (ex.: PostgreSQL) ou H2 para desenvolvimento

### Configuração de Ambiente
Crie variáveis de ambiente ou um arquivo `application.properties`/`application-dev.properties` com:

```properties
server.port=8080
spring.datasource.url=jdbc:postgresql://localhost:5432/fastdev
spring.datasource.username=fastdev
spring.datasource.password=fastdev
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.jdbc.time_zone=UTC
api.security.token.secret=defina-um-segredo-forte
```

Para Oracle, ajuste o `spring.datasource.url` e o driver conforme necessário.

### Subir PostgreSQL com Docker (opcional)
```yaml
services:
  db:
    image: postgres:16
    environment:
      - POSTGRES_DB=fastdev
      - POSTGRES_USER=fastdev
      - POSTGRES_PASSWORD=fastdev
    ports:
      - "5432:5432"
    volumes:
      - fastdev_data:/var/lib/postgresql/data
volumes:
  fastdev_data:
```

### Executar em Desenvolvimento
```bash
mvn spring-boot:run
```

### Build de Produção
```bash
mvn -DskipTests package
java -jar target/fastdev-backend.jar
```

### Testes
```bash
mvn test
```

### Perfis
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Endpoints
#### Autenticação
`POST /auth/login`  
Body:
```json
{
  "login": "user",
  "password": "minha-senha"
}
```
Resposta 200:
```json
{
  "token": "jwt"
}
```

`POST /auth/register-visualizador`  
Body:
```json
{
  "nome": "Filipe",
  "login": "user",
  "password": "minha-senha",
  "nomeCompleto": "Filipe Scandiani",
  "telefone": "(22) 99999-0000"
}
```

#### Vídeos
`POST /videos`  
Header: `Authorization: Bearer {jwt}`  
Body:
```json
{
  "tipo": "youtube",
  "url": "https://www.youtube.com/watch?v=xxxxx",
  "titulo": "Introdução",
  "descricao": "Primeiro vídeo",
  "nivelAcesso": "BASICO"
}
```

`GET /videos`  
Retorna a lista de vídeos permitidos para o usuário autenticado.

#### Comentários
`POST /comentarios/{videoId}`  
Header: `Authorization: Bearer {jwt}`  
Body:
```json
{
  "texto": "Excelente conteúdo"
}
```

### Autenticação e Segurança
- Login retorna um JWT
- Envie o token no header `Authorization` no formato `Bearer jwt`
- O controle de acesso utiliza níveis em `nivelAcesso`

### Validações de Entrada
Os DTOs utilizam anotações como `@NotBlank`, `@Size`, `@Pattern`, `@URL` e `@NotNull`. Requisições inválidas retornam 400 com corpo padronizado.

Exemplo de erro 400:
```json
{
  "status": 400,
  "error": "Bad Request",
  "path": "/videos",
  "timestamp": "2025-09-01T00:00:00Z",
  "errors": [
    { "field": "titulo", "message": "título obrigatório" }
  ]
}
```

### Estrutura de Pastas Sugerida
```
src/main/java/br/com/scandianx/fastdev/
  config/
  controller2/
  dto/
  entity/
  repository/
  service/
  service/interfaces/
src/main/resources/
  application.properties
  application-dev.properties
```

### Convenções de Commit
- Conventional Commits
- Mensagens curtas e imperativas

### Troubleshooting
- Erro de conexão: verifique `spring.datasource.*`
- 401: verifique o header `Authorization`
- Erros 400: valide os campos do JSON enviado

### Roadmap
- Paginação de vídeos
- Soft delete de comentários
- Upload de thumbnails

### Licença
Definir licença do projeto no repositório.

---

## EN

### Overview
FastDev Backend is a Java Spring Boot REST API for authentication, video management and comments, with access control based on levels. It targets a secure, scalable foundation for web and mobile apps.

### Key Features
- JWT authentication
- Video create and list
- Per-video comments
- Jakarta Bean Validation
- Layered architecture
- CORS enabled

### Stack
- Java 21
- Spring Boot 3.x
- Spring Web, Spring Security, Validation
- JPA/Hibernate
- PostgreSQL, Oracle or H2
- Maven

### Clone
```bash
git clone https://github.com/Scandianx/fastdev-backend
cd fastdev-backend
```

### Requirements
- JDK 21
- Maven 3.9+
- Database running or H2 for development

### Environment
```properties
server.port=8080
spring.datasource.url=jdbc:postgresql://localhost:5432/fastdev
spring.datasource.username=fastdev
spring.datasource.password=fastdev
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.jdbc.time_zone=UTC
api.security.token.secret=define-a-strong-secret
```

### Run Dev
```bash
mvn spring-boot:run
```

### Build
```bash
mvn -DskipTests package
java -jar target/fastdev-backend.jar
```

### Tests
```bash
mvn test
```

### Endpoints
Auth
```http
POST /auth/login
```
Videos
```http
POST /videos
GET /videos
```
Comments
```http
POST /comentarios/{videoId}
```

### Error Example
```json
{
  "status": 400,
  "error": "Bad Request",
  "path": "/videos",
  "timestamp": "2025-09-01T00:00:00Z",
  "errors": [
    { "field": "titulo", "message": "título obrigatório" }
  ]
}
```

