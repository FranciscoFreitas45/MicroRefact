package com.jepescados.vendasapi.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.jepescados.vendasapi.model.entity.Endereco;
import com.jepescados.vendasapi.model.repository.EnderecoRepository;
@Service
public class EnderecoService {

@Autowired
 private  EnderecoRepository repository;


public List<Endereco> listar(){
    return repository.findAll();
}


public Endereco salvar(Endereco endereco){
    return repository.save(endereco);
}


public Endereco listarPorId(Integer id){
    return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
}


}