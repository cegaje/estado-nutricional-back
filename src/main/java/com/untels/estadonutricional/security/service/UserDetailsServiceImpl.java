package com.untels.estadonutricional.security.service;

import com.untels.estadonutricional.security.entity.Usuario;
import com.untels.estadonutricional.security.entity.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String correoElectronico)
            throws UsernameNotFoundException {
        Usuario usuario = usuarioService
                .obtenerUnoPorCorreoElectronico(correoElectronico)
                .get();

        return UsuarioPrincipal.build(usuario);
    }

}
