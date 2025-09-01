package br.com.scandianx.fastdev.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.scandianx.fastdev.entity.Usuario;
import br.com.scandianx.fastdev.repository.interfaces.UsuarioRepository;

@Repository
public interface UsuarioRepositoryImpl extends JpaRepository<Usuario, Long>, UsuarioRepository {
    Usuario findByLogin(String login);
    
    

}
