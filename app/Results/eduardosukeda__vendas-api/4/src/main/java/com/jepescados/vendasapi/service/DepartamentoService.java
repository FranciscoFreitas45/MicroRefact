package com.jepescados.vendasapi.service;
 import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.jepescados.vendasapi.exception.DepartamentoCadastradoException;
import com.jepescados.vendasapi.model.entity.Departamento;
import com.jepescados.vendasapi.model.repository.DepartamentoRepository;
@Service
public class DepartamentoService {

@Autowired
 private  DepartamentoRepository repository;


public List<Departamento> listar(){
    return repository.findAll();
}


public Departamento salvar(Departamento departamento){
    boolean existe = repository.existsByNome(departamento.getNome());
    if (existe) {
        throw new DepartamentoCadastradoException(departamento.getNome());
    }
    return repository.save(departamento);
}


public Departamento listarPorId(Integer id){
    return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
}


}