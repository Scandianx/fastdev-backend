package br.com.scandianx.fastdev.components;

import br.com.scandianx.fastdev.entity.Usuario;

public interface UsuarioObserver {
    void notificarNovoUsuario(Usuario usuario);
}
