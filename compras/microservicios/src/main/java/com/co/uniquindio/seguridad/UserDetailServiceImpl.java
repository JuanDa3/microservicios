package com.co.uniquindio.seguridad;

import com.co.uniquindio.persistencia.entidades.Usuario;
import com.co.uniquindio.persistencia.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo
                .findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con correo " + correo + " no existe."));

        return new UserDetailsImpl(usuario);
    }
}
