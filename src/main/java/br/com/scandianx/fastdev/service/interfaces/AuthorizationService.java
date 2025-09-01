package br.com.scandianx.fastdev.service.interfaces;

import br.com.scandianx.fastdev.entity.Usuario;

public interface AuthorizationService {
    public Usuario findUserByToken(String token);
}
