package com.jepescados.vendasapi.rest;
 import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.IdClass;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jepescados.vendasapi.model.entity.Categoria;
import com.jepescados.vendasapi.model.entity.Departamento;
import com.jepescados.vendasapi.model.entity.Imagem;
import com.jepescados.vendasapi.model.entity.Produto;
import com.jepescados.vendasapi.model.repository.CategoriaRepository;
import com.jepescados.vendasapi.model.repository.ImagemRepository;
import com.jepescados.vendasapi.model.repository.ProdutoRepository;
import com.jepescados.vendasapi.rest.dto.ProdutoDto;
import com.jepescados.vendasapi.rest.form.ProdutoForm;
import com.jepescados.vendasapi.service.ProdutoService;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

@Autowired
 private  ProdutoService service;

@Autowired
 private  ProdutoRepository repository;

@Autowired
 private  CategoriaRepository categoriaRepository;

@Autowired
 private  ImagemRepository imagemRepository;


@DeleteMapping("{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deletar(Integer id){
    repository.deleteById(id);
}


@GetMapping
public List<ProdutoDto> listar(){
    List<Produto> prods = repository.findAll();
    return ProdutoDto.converter(prods);
}


@PutMapping("{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void atualizar(Integer id,Produto produtoAtualizado,MultipartFile imagemPrincipal,List<MultipartFile> maisImagens){
    List<Imagem> imagens = new ArrayList<Imagem>();
    repository.findById(id).map(p -> {
        p.setNome(produtoAtualizado.getNome());
        p.setDescricao(produtoAtualizado.getDescricao());
        p.setPeso(produtoAtualizado.getPeso());
        p.setPreco(produtoAtualizado.getPreco());
        p.setPromocao(produtoAtualizado.isPromocao());
        p.setMaisVendidos(produtoAtualizado.isMaisVendidos());
        p.setQuantidadeMinimaVenda(produtoAtualizado.getQuantidadeMinimaVenda());
        p.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
        p.setCategoria(produtoAtualizado.getCategoria());
        p.setDataAlteracao(LocalDateTime.now());
        return repository.save(p);
    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
}


@PutMapping("{id}/foto")
public byte[] adicionarFoto(Integer id,Part arquivo){
    return this.service.adicionarImagem(id, arquivo);
}


@GetMapping("{id}")
public ProdutoDto listarPorId(Integer id){
    Optional<Produto> prodOptional = repository.findById(id);
    if (prodOptional.isPresent()) {
        return ProdutoDto.converter(prodOptional.get());
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}


@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public Produto salvar(String produtoJson,MultipartFile imagemPrincipal,List<MultipartFile> maisImagens){
    ObjectMapper mapper = new ObjectMapper();
    Produto produto = new Produto();
    List<Imagem> imagens = new ArrayList<Imagem>();
    try {
        produto = mapper.readValue(produtoJson, Produto.class);
    } catch (IOException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    Produto prod = repository.save(produto);
    try {
        if (imagemPrincipal != null) {
            byte[] bytes = imagemPrincipal.getBytes();
            imagens.add(new Imagem(bytes, 1, prod));
            this.imagemRepository.save(new Imagem(bytes, 1, prod));
            if (maisImagens != null) {
                int count = 1;
                for (MultipartFile outraFoto : maisImagens) {
                    byte[] bytesOutraFoto = outraFoto.getBytes();
                    imagens.add(new Imagem(bytesOutraFoto, count, prod));
                    this.imagemRepository.save(new Imagem(bytesOutraFoto, count, prod));
                    count++;
                }
            }
        // this.imagemRepository.saveAll(imagens);
        }
    } catch (IOException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return prod;
}


}