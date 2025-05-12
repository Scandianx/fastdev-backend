
# Arquitetura e Padrões de Projeto - FastDev

Este documento descreve os principais padrões de projeto e conceitos de arquitetura utilizados no desenvolvimento da aplicação **FastDev**.

---

## 🧱 Padrão MVC

Utilizamos o padrão arquitetural **Model-View-Controller** (MVC) para organizar o projeto em camadas distintas:

- **Model:** Contém as entidades JPA que representam as tabelas do banco de dados.
- **View:** Representada pelas APIs expostas via controllers REST.
- **Controller:** Camada responsável por receber requisições HTTP, delegar a lógica para os services e retornar as respostas.

---

## 🎯 SOLID

A aplicação segue os princípios **SOLID** para manter um código modular, coeso e fácil de manter:

- **S - Single Responsibility:** Cada classe possui uma responsabilidade única. A classe abstrata usuário só lida com autenticação por exemplo.
- **O - Open/Closed:** Graças ao princípio Open/Closed, conseguimos adicionar novos tipos de vídeo com regras e bancos distintos simplesmente criando novas classes e alterando VideoFactory — sem modificar a estrutura já existente.
- **L - Liskov Substitution:** Utilizamos abstrações (`Usuario`, `VideoAbstrato`) que permitem substituição por subclasses.
- **I - Interface Segregation:** Interfaces pequenas e específicas como `FavoritosService`, `ComentarioService`.
- **D - Dependency Inversion:** Controllers dependem das interfaces dos services, não das implementações.

---

## 🧩 Facade Pattern

Adotamos o **Facade Pattern** por meio das **interfaces de serviços** (`FavoritosService`, `ComentarioService`, etc.):

- Fornecem um ponto único de acesso às funcionalidades de negócio.
- Facilitam a manutenção e testes, desacoplando o controller da lógica de negócio.
- Permitem mudar a implementação de um serviço sem alterar quem o consome.

---

## 🗃️ Repository Pattern

As **interfaces de repositórios** como `ComentarioRepository`, `FavoritoRepository` seguem o padrão de projeto **Repository**:

- Permitem trocar facilmente a implementação para outro tipo de persistência (ex: banco NoSQL).
- Abstraem o acesso ao banco de dados e tornam o código mais limpo.
- Separação de responsabilidades entre acesso a dados e lógica de negócio.

---

## 🏭 Factory Pattern

Para a criação de objetos do tipo `VideoAbstrato`, aplicamos o padrão **Factory**:

- Permite instanciar vídeos com diferentes níveis de acesso sem acoplar diretamente o código às subclasses.
- Exemplo: `VideoFactory.createVideo(titulo, url, nivel)` decide dinamicamente qual instância retornar.

---

## 🔔 Observer Pattern

Implementamos o padrão **Observer** para notificação de novos usuários:

- Observadores (ex: `NotificadorPush`, `NotificadorWhatsapp`) são registrados para serem notificados quando um novo usuário for criado.
- Promove baixo acoplamento entre o fluxo de cadastro e os serviços de notificação.

---

## ✅ Conclusão

Esses padrões foram escolhidos para promover **escalabilidade**, **manutenibilidade** e **clareza** no desenvolvimento do FastDev. O uso combinado de **MVC + SOLID + padrões clássicos de design** garante uma base sólida para crescimento e evolução da aplicação.
