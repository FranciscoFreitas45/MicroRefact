package com.jepescados.vendasapi.rest.dto;
 import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.servlet.http.Part;
import javax.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jepescados.vendasapi.model.entity.Departamento;
import com.jepescados.vendasapi.model.entity.Endereco;
import com.jepescados.vendasapi.model.entity.Funcionario;
import com.jepescados.vendasapi.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.jepescados.vendasapi.Interface.UsuarioDto;
import com.jepescados.vendasapi.Interface.DepartamentoDto;
import com.jepescados.vendasapi.Interface.EnderecoDto;
public class FuncionarioDto {

 private  Integer id;

 private  UsuarioDto usuario;

 private  DepartamentoDto departamento;

 private  EnderecoDto endereco;

 private  String funcao;

 private  byte[] foto;

 private  LocalDateTime dataCriacao;

 private  LocalDateTime dataAlteracao;

public FuncionarioDto(Funcionario func) {
    this.id = func.getId();
    this.usuario = new UsuarioDto(func.getUsuario());
    this.departamento = new DepartamentoDto(func.getDepartamento());
    this.endereco = new EnderecoDto(func.getEndereco());
    this.funcao = func.getFuncao();
    this.foto = func.getFoto();
    this.dataCriacao = func.getDataCriacao();
    this.dataAlteracao = func.getDataAlteracao();
}
public String getFuncao(){
    return funcao;
}


public LocalDateTime getDataAlteracao(){
    return dataAlteracao;
}


public DepartamentoDto getDepartamento(){
    return departamento;
}


public byte[] getFoto(){
    return foto;
}


public List<FuncionarioDto> converter(List<Funcionario> funcionarios){
    return funcionarios.stream().map(FuncionarioDto::new).collect(Collectors.toList());
}


public EnderecoDto getEndereco(){
    return endereco;
}


public LocalDateTime getDataCriacao(){
    return dataCriacao;
}


public Integer getId(){
    return id;
}


public UsuarioDto getUsuario(){
    return usuario;
}


}