package br.com.scandianx.fastdev.dto;

import br.com.scandianx.fastdev.entity.UsuarioRole;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateUsuarioDTO {
    @Size(max = 60, message = "nome deve ter no máximo 60 caracteres")
    private String nome;

    @Pattern(regexp = "^[0-9()\\-\\s+]{10,20}$", message = "telefone inválido")
    private String telefone;

    @Size(min = 8, max = 72, message = "senha deve ter entre 8 e 72 caracteres")
    private String password;

    // Opcional no PATCH; enum já restringe valores válidos
    private UsuarioRole role;

    @Size(max = 120, message = "nome completo deve ter no máximo 120 caracteres")
    private String nomeCompleto; // aplicável para Visualizador

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UsuarioRole getRole() { return role; }
    public void setRole(UsuarioRole role) { this.role = role; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
}
