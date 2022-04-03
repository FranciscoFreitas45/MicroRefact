package com.jepescados.vendasapi.rest;
 import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.jepescados.vendasapi.exception.DepartamentoCadastradoException;
import com.jepescados.vendasapi.model.entity.Categoria;
import com.jepescados.vendasapi.model.entity.Departamento;
import com.jepescados.vendasapi.model.repository.DepartamentoRepository;
import com.jepescados.vendasapi.rest.dto.DepartamentoDto;
import com.jepescados.vendasapi.service.DepartamentoService;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/departamentos")
@RequiredArgsConstructor
public class DepartamentoController {

@Autowired
 private  DepartamentoService service;

@Autowired
 private  DepartamentoRepository repository;


@DeleteMapping("{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deletar(Integer id){
    repository.deleteById(id);
}


@GetMapping
public List<DepartamentoDto> listar(){
    List<Departamento> departamentos = repository.findAll();
    return DepartamentoDto.converter(departamentos);
}


@PutMapping("{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void atualizar(Integer id,Departamento departamentoAtualizado){
    repository.findById(id).map(d -> {
        d.setNome(departamentoAtualizado.getNome());
        d.setTelefone(departamentoAtualizado.getTelefone());
        d.setDataAlteracao(LocalDateTime.now());
        return repository.save(d);
    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
}


@GetMapping("{id}")
public Departamento listarPorId(Integer id){
    return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
}


@PostMapping()
@ResponseStatus(HttpStatus.CREATED)
public Departamento salvar(Departamento departamento){
    boolean existe = repository.existsByNome(departamento.getNome());
    if (existe) {
        throw new DepartamentoCadastradoException(departamento.getNome());
    }
    return repository.save(departamento);
}


}