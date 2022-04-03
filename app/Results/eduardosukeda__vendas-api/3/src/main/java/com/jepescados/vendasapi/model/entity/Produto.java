package com.jepescados.vendasapi.model.entity;
 import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import org.hibernate.annotations.Type;
import org.hibernate.type.TrueFalseType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
@Entity
public class Produto {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Integer id;

@Column(nullable = false, length = 150)
 private  String nome;

@Column(nullable = false, length = 255)
 private  String descricao;

@OneToMany(fetch = FetchType.EAGER, mappedBy = "produto")
 private  List<Imagem> imagens;

@Column
 private  Double peso;

@Column
 private  Double preco;

@Type(type = "true_false")
@Column(name = "in_promocao", nullable = false)
 private  boolean promocao;

@Type(type = "true_false")
@Column(name = "mais_vendidos", nullable = false)
 private  boolean maisVendidos;

@Column(name = "quantidade_minima_venda")
 private  Double quantidadeMinimaVenda;

@Column(name = "in_quantidade_estoque", nullable = false)
 private  double quantidadeEstoque;

@ManyToOne
@JoinColumn(name = "id_categoria")
 private  Categoria categoria;

@JsonDeserialize(using = LocalDateTimeDeserializer.class)
@JsonSerialize(using = LocalDateTimeSerializer.class)
@Column(name = "dh_criacao", nullable = false, updatable = false)
 private  LocalDateTime dataCriacao;

@JsonDeserialize(using = LocalDateTimeDeserializer.class)
@JsonSerialize(using = LocalDateTimeSerializer.class)
@Column(name = "dh_alteracao")
 private  LocalDateTime dataAlteracao;

public Produto() {
}public Produto(String nome, String descricao, Double peso, Double preco, boolean promocao, boolean maisVendidos, Double quantidadeMinimaVenda, double quantidadeEstoque, Categoria categoria, LocalDateTime dataCriacao, LocalDateTime dataAlteracao) {
    this.nome = nome;
    this.descricao = descricao;
    this.peso = peso;
    this.preco = preco;
    this.promocao = promocao;
    this.maisVendidos = maisVendidos;
    this.quantidadeMinimaVenda = quantidadeMinimaVenda;
    this.quantidadeEstoque = quantidadeEstoque;
    this.categoria = categoria;
    this.dataCriacao = dataCriacao;
    this.dataAlteracao = dataAlteracao;
}
public String getDescricao(){
    return descricao;
}


public void setMaisVendidos(boolean maisVendidos){
    this.maisVendidos = maisVendidos;
}


public Double getPeso(){
    return peso;
}


public void setPreco(Double preco){
    this.preco = preco;
}


@PrePersist
public void prePersist(){
    setDataCriacao(LocalDateTime.now());
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


public void setNome(String nome){
    this.nome = nome;
}


public void setId(Integer id){
    this.id = id;
}


public void setPeso(Double peso){
    this.peso = peso;
}


public void setPromocao(boolean promocao){
    this.promocao = promocao;
}


public void setDescricao(String descricao){
    this.descricao = descricao;
}


public Double getPreco(){
    return preco;
}


public boolean isMaisVendidos(){
    return maisVendidos;
}


public void setQuantidadeMinimaVenda(Double quantidadeMinimaVenda){
    this.quantidadeMinimaVenda = quantidadeMinimaVenda;
}


public void setDataCriacao(LocalDateTime dataCriacao){
    this.dataCriacao = dataCriacao;
}


public void setCategoria(Categoria categoria){
    this.categoria = categoria;
}


@JsonIgnore
public List<Imagem> getImagens(){
    return imagens;
}


public void setImagens(List<Imagem> imagens){
    this.imagens = imagens;
}


public void setQuantidadeEstoque(double quantidadeEstoque){
    this.quantidadeEstoque = quantidadeEstoque;
}


public void setDataAlteracao(LocalDateTime dataAlteracao){
    this.dataAlteracao = dataAlteracao;
}


public boolean isPromocao(){
    return promocao;
}


public LocalDateTime getDataAlteracao(){
    return dataAlteracao;
}


public LocalDateTime getDataCriacao(){
    return dataCriacao;
}


public double getQuantidadeEstoque(){
    return quantidadeEstoque;
}


public Categoria getCategoria(){
    return categoria;
}


}