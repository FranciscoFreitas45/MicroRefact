package com.jepescados.vendasapi.rest;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.jepescados.vendasapi.model.entity.Departamento;
import com.jepescados.vendasapi.model.entity.Endereco;
import com.jepescados.vendasapi.service.EnderecoService;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

@Autowired
 private  EnderecoService service;


@GetMapping
public List<Endereco> listar(){
    return this.service.listar();
}


@GetMapping("{id}")
public Endereco listarPorId(Integer id){
    return service.listarPorId(id);
}


@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public void salvar(Endereco endereco){
    this.service.salvar(endereco);
}


}