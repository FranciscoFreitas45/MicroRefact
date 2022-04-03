package com.jepescados.vendasapi.rest;
 import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.jepescados.vendasapi.model.entity.Categoria;
import com.jepescados.vendasapi.model.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

 private  CategoriaRepository repository;


@DeleteMapping("{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deletar(Integer id){
    repository.deleteById(id);
}


@GetMapping
public List<Categoria> listar(){
    return repository.findAll();
}


@PutMapping("{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void atualizar(Integer id,Categoria categoriaAtualizada){
    repository.findById(id).map(c -> {
        c.setNome(categoriaAtualizada.getNome());
        c.setDataAlteracao(LocalDateTime.now());
        return repository.save(c);
    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
}


@GetMapping("{id}")
public Categoria listarPorId(Integer id){
    return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
}


@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public Categoria salvar(Categoria categoria){
    return repository.save(categoria);
}


}