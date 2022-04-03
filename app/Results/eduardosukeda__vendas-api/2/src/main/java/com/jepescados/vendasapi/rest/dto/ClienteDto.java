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
import com.jepescados.vendasapi.model.entity.Cliente;
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
public class ClienteDto {

 private  Integer id;

 private  UsuarioDto usuario;

 private  EnderecoDto endereco;

 private  String cpf;

 private  LocalDateTime dataCriacao;

 private  LocalDateTime dataAlteracao;

public ClienteDto(Cliente cliente) {
    this.id = cliente.getId();
    this.usuario = new UsuarioDto(cliente.getUsuario());
    this.cpf = cliente.getCpf();
    this.endereco = new EnderecoDto(cliente.getEndereco());
    this.dataCriacao = cliente.getDataCriacao();
    this.dataAlteracao = cliente.getDataAlteracao();
}
public LocalDateTime getDataAlteracao(){
    return dataAlteracao;
}


public List<ClienteDto> converter(List<Cliente> clientes){
    return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
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


public String getCpf(){
    return cpf;
}


}