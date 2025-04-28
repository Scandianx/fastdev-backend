Claro. Vou refazer tudo em um **roteiro linear**, fluído e mais forte, exatamente no formato que você pediu.

Aqui está a versão melhorada:  

---

# FastDev – Roteiro de Apresentação

## 1. Introdução

FastDev é uma plataforma de streaming educacional desenvolvida com Java Spring Boot no backend e ReactJS no frontend.  
Nosso foco desde a modelagem foi garantir **escalabilidade**, **organização** e **extensão futura** através da aplicação rigorosa dos padrões **MVC**, **SOLID** e **Repository Pattern**, além da arquitetura pensada para futura migração para **bancos NoSQL**, compatíveis com o alto volume de dados característico de plataformas de mídia.

---

## 2. Arquitetura da Aplicação

### 2.1 Padrão MVC

**Model:**  
- Entidades JPA (`@Entity`) mapeiam diretamente as tabelas do banco relacional.  


**View:**  
- No backend, a view é representada pelas respostas REST em formato JSON, consumidas pelo frontend em ReactJS.

**Controller:**  
- Gerencia as requisições HTTP e delega o processamento para os serviços.

Exemplo de Controller:
```java
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {
        return authService.login(data);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO data) {
        return authService.register(data);
    }
}
```

---

## 3. Aplicação dos Princípios SOLID

**SRP (Single Responsibility Principle):**  
Cada classe no projeto possui apenas uma responsabilidade clara.  
Exemplo: DTOs tratam apenas transporte de dados; Serviços apenas regras de negócio.

**OCP (Open/Closed Principle):**  
As entidades são desenhadas para serem estendidas sem precisar alterar o código base.  
Exemplo: novas categorias de vídeos herdam de `VideoAbstrato`.

**LSP (Liskov Substitution Principle):**  
Subclasses podem substituir suas superclasses sem alterar o comportamento esperado.  
Exemplo: `Visualizador` e `Afiliado` herdam de `Usuario` e podem ser utilizados nas autenticações.

**ISP (Interface Segregation Principle):**  
Interfaces foram criadas de maneira específica para cada necessidade.  
Exemplo:
```java
public interface PlaylistRepository {
    Playlist save(Playlist p);
}
```

**DIP (Dependency Inversion Principle):**  
Serviços se apoiam em abstrações (interfaces) e não diretamente em classes concretas.
```java
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UsuarioRepository usuarioRepository;
}
```

---

## 4. Repository Pattern

Adotamos o **Repository Pattern** para garantir o desacoplamento entre a lógica de negócio e o banco de dados, possibilitando no futuro migrarmos para um banco **NoSQL** mais escalável (como MongoDB), considerando o crescimento natural de aplicações de streaming.

Exemplo:
```java
public interface VisualizadorRepository {
    Optional<Usuario> findByLogin(String login);
    Visualizador save(Visualizador v);
}
```
Implementação Spring Data:
```java
@Repository
public interface VisualizadorRepositoryImpl extends JpaRepository<Visualizador, Long>, VisualizadorRepository {
}
```

---

## 5. Sistema de Autenticação

### 5.1 Modelo de Usuário

**Classe abstrata `Usuario`:**
```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private UsuarioRole role;
}
```
Responsável por centralizar autenticação e autorização.

**Subclasses:**  
- `Visualizador`: adiciona `nomeCompleto`.  
- `Afiliado`: adiciona `codigoAfiliado` e `comissao`.

### 5.2 Implementação de UserDetailsService

```java
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(login)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
```



## 6. Facade Pattern: AuthenticationService

Para abstrair a complexidade das operações de autenticação, foi criado o **AuthenticationService** como uma Facade:

Interface:
```java
public interface AuthenticationService {
    ResponseEntity<LoginResponseDTO> login(AuthenticationDTO data);
    ResponseEntity<String> register(RegisterDTO data);
}
```
Implementação:
```java
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired private AuthenticationManager authManager;
    @Autowired private TokenService tokenService;
    @Autowired private UsuarioRepository usuarioRepository;

    @Override
    public ResponseEntity<LoginResponseDTO> login(AuthenticationDTO data) {
        var tokenReq = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        authManager.authenticate(tokenReq);
        var user = (UserDetails) usuarioRepository.findByLogin(data.login()).orElseThrow();
        String jwt = tokenService.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDTO(jwt));
    }

    @Override
    public ResponseEntity<String> register(RegisterDTO data) {
        if (usuarioRepository.findByLogin(data.login()).isPresent()) {
            return ResponseEntity.badRequest().body("Esse email já foi cadastrado");
        }
        // Persistência de novo usuário omitida
        return ResponseEntity.ok("Cadastrado com sucesso!");
    }
}
```

---

## 7. Modelos de Domínio e Extensibilidade

**VideoAbstrato**:
```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class VideoAbstrato {
    @Id @GeneratedValue
    private Long id;
    private String titulo;
    private boolean gratuito;
    public abstract String getUrl();
}
```
Subclasses como `VideoYoutube` e `VideoVimeo` permitem expansão sem impactar a estrutura existente.

**Enums para pagamentos:**
```java
public enum TipoPagamento {
    PIX, CARTAO_CREDITO, DEBITO;
}
public enum TipoPlano {
    MENSAL, SEMESTRAL, ANUAL;
}
```
Facilitam a evolução dos meios de pagamento.

---

## 8. Considerações Finais

- **Arquitetura MVC** promove a separação de responsabilidades e facilita a manutenção.
- **Princípios SOLID** tornam o sistema robusto, extensível e seguro.
- **Repository Pattern** prepara o projeto para migração a bancos de dados escaláveis, como NoSQL.
- **Facade** simplifica operações sensíveis como autenticação e registro.
- **Modelagem com Herança** permite novas features sem quebrar o funcionamento atual.

---

