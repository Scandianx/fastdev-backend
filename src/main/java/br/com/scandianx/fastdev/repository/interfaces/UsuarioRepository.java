package br.com.scandianx.fastdev.repository.interfaces;



import org.springframework.security.core.userdetails.UserDetails;

import br.com.scandianx.fastdev.model.Usuario;

public interface UsuarioRepository {
    Usuario findByLogin(String login);
    Usuario save(Usuario usuario);
}
