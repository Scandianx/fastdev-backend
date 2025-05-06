package br.com.scandianx.fastdev.components;

import org.springframework.stereotype.Component;

import br.com.scandianx.fastdev.model.Usuario;

@Component
public class EmailObserver implements UsuarioObserver {

    @Override
    public void notificarNovoUsuario(Usuario usuario) {
        // Lógica para enviar e-mail
        System.out.println("Enviando e-mail para: " + usuario.getUsername());
    }
}
