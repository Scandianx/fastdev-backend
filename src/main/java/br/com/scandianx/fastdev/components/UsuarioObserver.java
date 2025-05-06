package br.com.scandianx.fastdev.components;

import br.com.scandianx.fastdev.model.Usuario;

public interface UsuarioObserver {
    void notificarNovoUsuario(Usuario usuario);
}
