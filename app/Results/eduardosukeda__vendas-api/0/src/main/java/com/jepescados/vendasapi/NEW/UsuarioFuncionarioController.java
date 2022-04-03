package com.jepescados.vendasapi.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jepescados.vendasapi.model.entity.Usuario;
@RestController
@CrossOrigin
public class UsuarioFuncionarioController {

@Autowired
 private UsuarioFuncionarioService usuariofuncionarioservice;


}