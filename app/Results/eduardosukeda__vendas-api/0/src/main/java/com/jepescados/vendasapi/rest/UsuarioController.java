package com.jepescados.vendasapi.rest;
 import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.jepescados.vendasapi.exception.UsuarioCadastradoException;
import com.jepescados.vendasapi.model.entity.Endereco;
import com.jepescados.vendasapi.model.entity.Usuario;
import com.jepescados.vendasapi.model.repository.CategoriaRepository;
import com.jepescados.vendasapi.model.repository.UsuarioRepository;
import com.jepescados.vendasapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

@Autowired
 private  UsuarioService service;

@Autowired
 private  PasswordEncoder passwordEncoder;


@GetMapping
public List<Usuario> listar(){
    return this.service.listar();
}


@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public void salvar(Usuario usuario){
    try {
        String senha = usuario.getSenha();
        usuario.setSenha(passwordEncoder.encode(senha));
        service.salvar(usuario);
    } catch (UsuarioCadastradoException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}


@GetMapping("{id}")
public Usuario listarPorId(Integer id){
    return service.listarPorId(id);
}


}