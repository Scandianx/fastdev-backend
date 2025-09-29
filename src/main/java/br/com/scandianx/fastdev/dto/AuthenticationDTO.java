package br.com.scandianx.fastdev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthenticationDTO {

    @NotBlank(message = "login obrigatório")
    @Size(max = 100, message = "login deve ter no máximo 100 caracteres")
    private String login;

    @NotBlank(message = "senha obrigatória")
    @Size(min = 8, max = 72, message = "senha deve ter entre 8 e 72 caracteres")
    private String password;

    // Construtor padrão (necessário para Spring)
    public AuthenticationDTO() {}

    // Construtor completo (opcional)
    public AuthenticationDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    // Getters e Setters
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
}
