package br.com.scandianx.fastdev.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.scandianx.fastdev.entity.Usuario;
import br.com.scandianx.fastdev.entity.Visualizador;
import br.com.scandianx.fastdev.repository.impl.UsuarioRepositoryImpl;
import br.com.scandianx.fastdev.repository.interfaces.UsuarioRepository;
import br.com.scandianx.fastdev.security.TokenService;
import br.com.scandianx.fastdev.service.interfaces.AuthorizationService;


@Service
public class AuthorizationServiceImpl implements UserDetailsService, AuthorizationService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }
    @Override
    public Usuario findUserByToken (String token){
    	try {
    		
         if (token == null || !token.startsWith("Bearer ")) {
                return null;
         }
    	String newToken = token.substring(7);
    	String username = tokenService.validateToken(newToken);
    	Usuario usuario = usuarioRepository.findByLogin(username);
    	return usuario;
    	} catch (Exception e) {
    		return null;
    	}
    }

    
}
