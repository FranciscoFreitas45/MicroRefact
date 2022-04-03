package com.jepescados.vendasapi.rest.form;
 import java.time.LocalDateTime;
import java.util.List;
import com.jepescados.vendasapi.model.entity.Categoria;
import com.jepescados.vendasapi.model.entity.Imagem;
import com.jepescados.vendasapi.model.entity.Produto;
import com.jepescados.vendasapi.model.repository.CategoriaRepository;
public class ProdutoForm {

 private  String nome;

 private  String descricao;

 private  Boolean maisVendidos;

 private  Double peso;

 private  Double preco;

 private  Boolean promocao;

 private  Double quantidadeMinimaVenda;

 private  Double quantidadeEstoque;

 private  Integer idCategoria;

 private  Imagem imagemPrincipal;

 private  List<Imagem> maisImagens;

 private  LocalDateTime dataCriacao;

 private  LocalDateTime dataAlteracao;


public String getDescricao(){
    return descricao;
}


public void setMaisVendidos(Boolean maisVendidos){
    this.maisVendidos = maisVendidos;
}


public Boolean getMaisVendidos(){
    return maisVendidos;
}


public Double getPeso(){
    return peso;
}


public void setPreco(Double preco){
    this.preco = preco;
}


public Produto converter(CategoriaRepository categoriaRepository){
    Categoria categoria = categoriaRepository.findById(idCategoria).get();
    return new Produto(nome, descricao, peso, preco, promocao, maisVendidos, quantidadeMinimaVenda, quantidadeEstoque, categoria, dataCriacao, dataAlteracao);
}


public String getNome(){
    return nome;
}


public Double getQuantidadeMinimaVenda(){
    return quantidadeMinimaVenda;
}


public void setNome(String nome){
    this.nome = nome;
}


public void setIdCategoria(Integer idCategoria){
    this.idCategoria = idCategoria;
}


public void setMaisImagens(List<Imagem> maisImagens){
    this.maisImagens = maisImagens;
}


public Boolean getPromocao(){
    return promocao;
}


public void setPeso(Double peso){
    this.peso = peso;
}


public void setPromocao(Boolean promocao){
    this.promocao = promocao;
}


public void setDescricao(String descricao){
    this.descricao = descricao;
}


public Double getPreco(){
    return preco;
}


public void setQuantidadeMinimaVenda(Double quantidadeMinimaVenda){
    this.quantidadeMinimaVenda = quantidadeMinimaVenda;
}


public void setDataCriacao(LocalDateTime dataCriacao){
    this.dataCriacao = dataCriacao;
}


public void setQuantidadeEstoque(Double quantidadeEstoque){
    this.quantidadeEstoque = quantidadeEstoque;
}


public void setImagemPrincipal(Imagem imagemPrincipal){
    this.imagemPrincipal = imagemPrincipal;
}


public void setDataAlteracao(LocalDateTime dataAlteracao){
    this.dataAlteracao = dataAlteracao;
}


public LocalDateTime getDataAlteracao(){
    return dataAlteracao;
}


public Integer getIdCategoria(){
    return idCategoria;
}


public Imagem getImagemPrincipal(){
    return imagemPrincipal;
}


public LocalDateTime getDataCriacao(){
    return dataCriacao;
}


public Double getQuantidadeEstoque(){
    return quantidadeEstoque;
}


public List<Imagem> getMaisImagens(){
    return maisImagens;
}


}