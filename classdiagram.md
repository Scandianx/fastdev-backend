**VideoAbstrato**  
- **Atributos:**  
  - `id: Long`  
  - `titulo: String`  
  - `dtCriacao: LocalDateTime`  
- **Relacionamentos:**  
  - Superclasse de **VideoYoutube**  

---

**VideoYoutube**  
- **Atributos:**  
  - `urlYouTube: String`  
- **Relacionamentos:**  
  - Estende **VideoAbstrato**  

---

**Usuario**  
- **Atributos:**  
  - `id: Long`  
  - `nome: String`  
  - `login: String`  
  - `password: String`  
  - `role: UsuarioRole`  
- **Relacionamentos:**  
  - Superclasse de **Visualizador**  

---

**Visualizador**  
- **Atributos:**  
  - `nomeCompleto: String`  
- **Relacionamentos:**  
  - Estende **Usuario**  

---

**Comentario**  
- **Atributos:**  
  - `id: Long`  
  - `texto: String`  
  - `criadoEm: LocalDateTime`  
- **Relacionamentos:**  
  - `video: VideoAbstrato` (Many-To-One)  
  - `usuario: Usuario`      (Many-To-One)  

---

**Favorito**  
- **Atributos:**  
  - `id: Long`  
  - `favoritadoEm: LocalDateTime`  
- **Relacionamentos:**  
  - `video: VideoAbstrato` (Many-To-One)  
  - `usuario: Usuario`      (Many-To-One)  

---

**Playlist**  
- **Atributos:**  
  - `id: Long`  
  - `titulo: String`  
  - `dtCriacao: LocalDateTime`  
- **Relacionamentos:**  
  - `videos: List<VideoAbstrato>` (Many-To-Many)  

---

**Pagamento**  
- **Atributos:**  
  - `id: Long`  
  - `valor: BigDecimal`  
  - `tipoPagamento: TipoPagamento`  
  - `tipoPlano: TipoPlano`  
  - `dataInicio: LocalDateTime`  
  - `dataFinal: LocalDateTime`  
  - `dataCriacao: LocalDateTime`  
- **Relacionamentos:**  
  - `usuario: Usuario` (Many-To-One)  

---

> **Enums**  
> - **TipoPagamento**: `PIX` · `CARTAO_CREDITO` · `BOLETO`  
> - **TipoPlano**: `BRDEV` · `GRIANGDEV`  
> - **UsuarioRole**: `ADMIN` · `GRINGADEV` · `BRDEV` · `USER`