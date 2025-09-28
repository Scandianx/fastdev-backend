package br.com.scandianx.fastdev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public class RegisterDTO {

    @NotBlank(message = "nome obrigatório")
    @Size(max = 60, message = "nome deve ter no máximo 60 caracteres")
    private String nome;

    @NotBlank(message = "login obrigatório")
    @Email(message = "login deve ser um e-mail válido")
    @Size(max = 100, message = "login deve ter no máximo 100 caracteres")
    private String login;

    @NotBlank(message = "senha obrigatória")
    @Size(min = 8, max = 72, message = "senha deve ter entre 8 e 72 caracteres")
    private String password;

    @NotBlank(message = "nome completo obrigatório")
    @Size(max = 120, message = "nome completo deve ter no máximo 120 caracteres")
    private String nomeCompleto;

    @NotBlank(message = "telefone obrigatório")
    @Pattern(regexp = "^[0-9()\\-\\s+]{10,20}$", message = "telefone inválido")
    private String telefone;

    // Construtor padrÃ£o
    public RegisterDTO() {}

    // Construtor completo (opcional)
    public RegisterDTO(String nome, String login, String password, String nomeCompleto, String telefone) {
        this.nome = nome;
        this.login = login;
        this.password = password;
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
