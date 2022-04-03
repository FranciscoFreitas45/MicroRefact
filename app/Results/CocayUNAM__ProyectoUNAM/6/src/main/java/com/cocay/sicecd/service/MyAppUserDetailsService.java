package com.cocay.sicecd.service;
 import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.cocay.sicecd.model.Usuario_sys;
import com.cocay.sicecd.repo.Usuario_sysRep;
@Service
public class MyAppUserDetailsService implements UserDetailsService{

@Autowired
 private  Usuario_sysRep usuarioSys;


@Override
public UserDetails loadUserByUsername(String userName){
    Usuario_sys activeUserInfo = usuarioSys.findByRfc(userName).get(0);
    if (activeUserInfo != null) {
        if (activeUserInfo.getFk_id_estatus_usuario_sys().getNombre().equals("Inactivo")) {
            activeUserInfo = null;
        }
    }
    UserDetails userDetails;
    GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getFk_id_perfil_sys().getNombre());
    userDetails = (UserDetails) new User(activeUserInfo.getRfc(), activeUserInfo.getPassword(), Arrays.asList(authority));
    return userDetails;
}


}