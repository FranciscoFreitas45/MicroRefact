package com.jepescados.vendasapi.rest.dto;
 import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.jepescados.vendasapi.model.entity.Departamento;
import com.jepescados.vendasapi.model.entity.Endereco;
public class EnderecoDto {

 private  Integer id;

 private  String rua;

 private  Integer numero;

 private  String cep;

 private  String cidade;

 private  String estado;

 private  String bairro;

 private  String complemento;

 private  String pontoReferencia;

 private  LocalDateTime dataCriacao;

 private  LocalDateTime dataAlteracao;

public EnderecoDto(Endereco endereco) {
    this.id = endereco.getId();
    this.rua = endereco.getRua();
    this.numero = endereco.getNumero();
    this.cep = endereco.getCep();
    this.cidade = endereco.getCidade();
    this.estado = endereco.getEstado();
    this.bairro = endereco.getBairro();
    this.complemento = endereco.getComplemento();
    this.pontoReferencia = endereco.getPontoReferencia();
    this.dataCriacao = endereco.getDataCriacao();
    this.dataAlteracao = endereco.getDataAlteracao();
}
public String getRua(){
    return rua;
}


public String getCep(){
    return cep;
}


public String getBairro(){
    return bairro;
}


public Integer getNumero(){
    return numero;
}


public String getComplemento(){
    return complemento;
}


public LocalDateTime getDataAlteracao(){
    return dataAlteracao;
}


public String getCidade(){
    return cidade;
}


public String getEstado(){
    return estado;
}


public List<EnderecoDto> converter(List<Endereco> enderecos){
    return enderecos.stream().map(EnderecoDto::new).collect(Collectors.toList());
}


public String getPontoReferencia(){
    return pontoReferencia;
}


public LocalDateTime getDataCriacao(){
    return dataCriacao;
}


public Integer getId(){
    return id;
}


}