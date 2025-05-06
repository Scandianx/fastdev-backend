package br.com.scandianx.fastdev.service.interfaces;

import br.com.scandianx.fastdev.model.Usuario;

public interface AuthorizationService {
    public Usuario findUserByToken(String token);
}
