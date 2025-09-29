
## FastDev (Codemy) Backend

API REST em Spring Boot para autenticação (JWT), vídeos, comentários e favoritos, com controle de acesso por nível e papéis.

- Swagger UI: http://localhost:8083/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8083/v3/api-docs

Obs.: a porta pode variar conforme `server.port`.

### Requisitos
- Java 17
- Maven 3.9+
- PostgreSQL 13+ (ou compatível)

### Configuração de ambiente
Crie `src/main/resources/application.properties` (ou exporte variáveis de ambiente). Exemplo PostgreSQL:

```properties
server.port=8083
spring.datasource.url=jdbc:postgresql://localhost:5432/fastdevdb
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
api.security.token.secret=${JWT_SECRET:change-me}

# Swagger
springdoc.swagger-ui.path=/swagger-ui
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
springdoc.override-with-generic-response=false
springdoc.paths-to-match=/api/**
```

Variáveis úteis:
- `JWT_SECRET`: segredo do JWT
- `SERVER_PORT`: para alterar a porta (ou ajuste `server.port`)

### Rodando local
```bash
mvn spring-boot:run
```

Build do jar:
```bash
mvn -DskipTests package
java -jar target/fastdev-0.0.1-SNAPSHOT.jar
```

### Segurança e papéis
- `/api/v1/**` exige autenticação, exceto:
  - `POST /api/v1/auth/login`
  - `POST /api/v1/auth/register-visualizador`
- Apenas ADMIN pode: `POST|PUT|DELETE /api/v1/videos/**` e `PATCH|DELETE /api/v1/usuarios/**`.
- Use o botão Authorize (Swagger UI) e informe o token (sem o prefixo Bearer; o Swagger adiciona).

### Contas seed (criado no startup)
- ADMIN: `admin@admin` / senha: `admin@admin`
- ADMIN: `scandianidev@gmail.com` / senha: `scandianidev@gmail.com`

### Dados iniciais de vídeos
No startup são criados até 10 vídeos (se não existirem pelos títulos) com a URL:
`https://youtube.com/watch?v=E96YS8IQSHs` 
Níveis: `FREE`, `BRLDEV`, `USDEV`, `PRIVATE`.

### Autenticação
1) Login
```http
POST /api/v1/auth/login
Content-Type: application/json

{ "login": "admin@admin", "password": "admin@admin" }
```
Resposta:
```json
{ "token": "<JWT>" }
```
2) Usar o token: Header `Authorization: Bearer <JWT>`.

### Exemplos de endpoints
- Listar permitidos: `GET /api/v1/videos`
- Buscar por título: `GET /api/v1/videos/search?titulo=java`
- Top favoritos: `GET /api/v1/videos/top?limit=10`
- Obter por id: `GET /api/v1/videos/{id}`
- Favoritar: `POST /api/v1/favoritos/{videoId}`
- Comentários do vídeo: `GET /api/v1/videos/{id}/comentarios`
- Comentar: `POST /api/v1/videos/{id}/comentarios` (JSON `{"texto":"..."}`)

### Front-end (páginas)
- `/auth-web`: login/cadastro (salva `fastdev_token` no localStorage)
- `/web`: home
- `/web/video?id=...`: assistir vídeo (todas as chamadas usam Authorization)



### Troubleshooting
- 401/403 no Swagger: clique em Authorize e informe o JWT.
- 404 em vídeo: confirme o `id` e o nível de acesso do usuário.
- Conexão DB: verifique `spring.datasource.*` e permissões.

### Principais dependências
- Spring Boot 3.4.4 (Web, Security, Validation, Data JPA, Thymeleaf)
- springdoc-openapi-starter-webmvc-ui (Swagger UI)
- PostgreSQL driver
- Auth0 Java JWT
