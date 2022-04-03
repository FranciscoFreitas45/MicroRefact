package com.jepescados.vendasapi.service;
 import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.Part;
import javax.xml.bind.ParseConversionEvent;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jepescados.vendasapi.model.entity.Funcionario;
import com.jepescados.vendasapi.model.entity.Usuario;
import com.jepescados.vendasapi.model.repository.DepartamentoRepository;
import com.jepescados.vendasapi.model.repository.FuncionarioRepository;
import com.jepescados.vendasapi.rest.dto.FuncionarioDto;
import lombok.Data;
import lombok.NoArgsConstructor;
@Service
@Data
@NoArgsConstructor
public class FuncionarioService {

@Autowired
 private  FuncionarioRepository repository;


public List<Funcionario> listar(){
    List<Funcionario> temp = repository.findAll();
    return temp;
}


public Funcionario salvarFuncionario(Funcionario funcionario){
    return repository.save(funcionario);
}


public byte[] adicionarFoto(Integer id,Part arquivo){
    Optional<Funcionario> funcionario = repository.findById(id);
    return funcionario.map(func -> {
        try {
            InputStream is = arquivo.getInputStream();
            byte[] bytes = new byte[(int) arquivo.getSize()];
            IOUtils.readFully(is, bytes);
            func.setFoto(bytes);
            repository.save(func);
            is.close();
            return bytes;
        } catch (IOException e) {
            return null;
        }
    }).orElse(null);
}


public Funcionario salvar(String funcionarioJson,MultipartFile foto){
    ObjectMapper mapper = new ObjectMapper();
    Funcionario func = new Funcionario();
    try {
        func = mapper.readValue(funcionarioJson, Funcionario.class);
        if (foto != null) {
            byte[] bytes = foto.getBytes();
            func.setFoto(bytes);
        } else {
            func.setFoto(null);
        }
    } catch (IOException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return repository.save(func);
}


public Funcionario listarPorId(Integer id){
    return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
}


}