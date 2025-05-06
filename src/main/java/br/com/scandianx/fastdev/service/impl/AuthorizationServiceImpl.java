package br.com.scandianx.fastdev.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.scandianx.fastdev.model.Usuario;
import br.com.scandianx.fastdev.model.Visualizador;
import br.com.scandianx.fastdev.repository.impl.UsuarioRepositoryImpl;
import br.com.scandianx.fastdev.security.TokenService;


@Service
public class AuthorizationServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepositoryImpl usuarioRepository;

    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }

    
}
