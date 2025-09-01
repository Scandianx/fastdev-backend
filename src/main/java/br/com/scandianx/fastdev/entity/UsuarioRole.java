package br.com.scandianx.fastdev.entity;

public enum UsuarioRole {
    ADMIN("admin"),
    GRINGADEV("gringadev"),
    BRDEV("brdev"),
    USER("user");

    private String role;

    UsuarioRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
