package br.com.scandianx.fastdev.components;

import org.springframework.stereotype.Component;

import br.com.scandianx.fastdev.entity.Usuario;

@Component
public class WhatsappObserver implements UsuarioObserver {

    @Override
    public void notificarNovoUsuario(Usuario usuario) {
        // Lógica para enviar WhatsApp
        System.out.println("Enviando WhatsApp para: " + usuario.getTelefone());
    }
}