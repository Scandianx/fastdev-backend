package br.com.scandianx.fastdev.entity;

public enum UsuarioRole {
    ADMIN("admin"),
    USDEV("usdev"),
    BRLDEV("brldev"),
    USER("user");

    private String role;

    UsuarioRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
