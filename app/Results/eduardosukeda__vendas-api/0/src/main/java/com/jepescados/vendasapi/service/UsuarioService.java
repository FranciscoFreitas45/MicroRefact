package com.jepescados.vendasapi.service;
 import java.util.List;
import java.util.Optional;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.jepescados.vendasapi.exception.UsuarioCadastradoException;
import com.jepescados.vendasapi.model.entity.Endereco;
import com.jepescados.vendasapi.model.entity.Funcionario;
import com.jepescados.vendasapi.model.entity.Usuario;
import com.jepescados.vendasapi.model.repository.UsuarioRepository;
@Service
public class UsuarioService implements UserDetailsService{

@Autowired
 private  UsuarioRepository repository;


public List<Usuario> listar(){
    return repository.findAll();
}


@Override
public UserDetails loadUserByUsername(String login){
    Usuario usuario = repository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));
    return User.builder().username(usuario.getLogin()).password(usuario.getSenha()).roles(usuario.getRole()).build();
}


public Usuario salvar(Usuario usuario){
    boolean exists = repository.existsByLogin(usuario.getLogin());
    if (exists) {
        throw new UsuarioCadastradoException(usuario.getLogin());
    }
    return repository.save(usuario);
}


public Usuario listarPorId(Integer id){
    return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
}


}