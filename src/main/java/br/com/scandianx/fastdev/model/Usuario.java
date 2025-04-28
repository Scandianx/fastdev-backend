package br.com.scandianx.fastdev.model;

import java.util.Collection;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tusuarios")
public abstract class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_usuario")
    @SequenceGenerator(name = "generator_usuario", sequenceName = "sequence_usuario", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = true)
    private String password;
    @Enumerated(EnumType.STRING)
    private UsuarioRole role;

    
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UsuarioRole getRole() {
        return role;
    }

    public void setRole(UsuarioRole role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UsuarioRole.ADMIN) {
            return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_GRINGADEV"),
                new SimpleGrantedAuthority("ROLE_BRDEV"),
                new SimpleGrantedAuthority("ROLE_USER")
            );
        } else if (this.role == UsuarioRole.GRINGADEV) {
            return List.of(
                new SimpleGrantedAuthority("ROLE_GRINGADEV"),
                new SimpleGrantedAuthority("ROLE_BRDEV"),
                new SimpleGrantedAuthority("ROLE_USER")
            );
        } else if (this.role == UsuarioRole.BRDEV) {
            return List.of(
                new SimpleGrantedAuthority("ROLE_BRDEV"),
                new SimpleGrantedAuthority("ROLE_USER")
            );
        } else {
            return List.of(
                new SimpleGrantedAuthority("ROLE_USER")
            );
        }
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}