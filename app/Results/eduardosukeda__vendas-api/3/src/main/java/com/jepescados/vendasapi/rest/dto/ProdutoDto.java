package com.jepescados.vendasapi.rest.dto;
 import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.jepescados.vendasapi.model.entity.Categoria;
import com.jepescados.vendasapi.model.entity.Funcionario;
import com.jepescados.vendasapi.model.entity.Imagem;
import com.jepescados.vendasapi.model.entity.Produto;
import com.jepescados.vendasapi.model.repository.CategoriaRepository;
public class ProdutoDto {

 private  Integer id;

 private  String nome;

 private  String descricao;

 private  Double peso;

 private  Double preco;

 private  Boolean promocao;

 private  Boolean maisVendidos;

 private  Double quantidadeMinimaVenda;

 private  Double quantidadeEstoque;

 private  Categoria categoria;

 private  List<Imagem> imagens;

 private  LocalDateTime dataCriacao;

 private  LocalDateTime dataAlteracao;

public ProdutoDto(Produto prod) {
    this.id = prod.getId();
    this.nome = prod.getNome();
    this.descricao = prod.getDescricao();
    this.peso = prod.getPeso();
    this.preco = prod.getPreco();
    this.promocao = prod.isPromocao();
    this.maisVendidos = prod.isMaisVendidos();
    this.quantidadeMinimaVenda = prod.getQuantidadeMinimaVenda();
    this.quantidadeEstoque = prod.getQuantidadeEstoque();
    this.categoria = prod.getCategoria();
    this.imagens = prod.getImagens();
    this.dataCriacao = prod.getDataCriacao();
    this.dataAlteracao = prod.getDataAlteracao();
}
public String getDescricao(){
    return descricao;
}


public Double getPeso(){
    return peso;
}


public Double getPreco(){
    return preco;
}


public Boolean getMaisVendidos(){
    return maisVendidos;
}


public ProdutoDto converter(Produto produto){
    return new ProdutoDto(produto);
}


public List<Imagem> getImagens(){
    return imagens;
}


public Integer getId(){
    return id;
}


public String getNome(){
    return nome;
}


public Double getQuantidadeMinimaVenda(){
    return quantidadeMinimaVenda;
}


public LocalDateTime getDataAlteracao(){
    return dataAlteracao;
}


public LocalDateTime getDataCriacao(){
    return dataCriacao;
}


public Boolean getPromocao(){
    return promocao;
}


public Double getQuantidadeEstoque(){
    return quantidadeEstoque;
}


public Categoria getCategoria(){
    return categoria;
}


}