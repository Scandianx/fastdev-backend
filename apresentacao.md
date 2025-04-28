**MVC no FastDev**  
- **Model**  
  - Entidades JPA que representam o domínio: por exemplo, `VideoAbstrato` e suas sub-classes (`VideoYoutube`), além de `Usuario`, `Visualizador`, `Administrador` etc.  
  - Essas classes contêm apenas atributos, getters/setters e anotações de mapeamento (e.g. `@Entity`, `@Table`).  
- **View**  
  - Camada frontend em ReactJS: componentes e páginas para exibição de catálogo, player de vídeo, formulários de login/cadastro, comentários e planos.  
  - Cada componente renderiza dados vindos dos Controllers via chamadas HTTP (fetch/Axios).  
- **Controller**  
  - REST Controllers em Spring Boot (`@RestController`) que expõem endpoints para:  
    - Autenticação (login, cadastro, recuperação de senha)  
    - Listagem e acesso a vídeos  
    - Processamento de pagamentos e assinaturas  
    - Avaliações e comentários  
  - Os Controllers recebem requisições, validam dados básicos e delegam toda a lógica de negócio para os Services.

---

**Princípios SOLID no FastDev**  
1. **Single Responsibility Principle (SRP)**  
   - Cada camada/classe tem uma única responsabilidade:  
     - **Models** só modelam dados.  
     - **Repositories** só acessam o banco.  
     - **Services** só contêm regras de negócio (e.g. lógica de assinatura, controle do teste grátis).  
     - **Controllers** só orquestram entrada/saída HTTP.  
2. **Open/Closed Principle (OCP)**  
   - Novos tipos de vídeo (ou outras entidades) são criados estendendo classes abstratas existentes (`VideoAbstrato`) sem necessidade de modificar seu código.  
3. **Liskov Substitution Principle (LSP)**  
   - Subclasses de `VideoAbstrato` (como `VideoYoutube`) podem ser usadas em qualquer lugar que espere um `VideoAbstrato`, garantindo consistência de comportamento.  
4. **Interface Segregation Principle (ISP)**  
   - Repositórios e serviços definem interfaces enxutas (e.g. `VisualizadorRepository`, `VideoService`) sem métodos inúteis, evitando “interfaces gordas”.  
5. **Dependency Inversion Principle (DIP)**  
   - Controllers e Services dependem de abstrações (interfaces) e não de implementações concretas. O Spring injeta as implementações corretas, facilitando testes e trocas de tecnologia.