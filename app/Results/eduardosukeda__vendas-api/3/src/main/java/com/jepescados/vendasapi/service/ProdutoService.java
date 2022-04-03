package com.jepescados.vendasapi.service;
 import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jepescados.vendasapi.model.entity.Produto;
import com.jepescados.vendasapi.model.repository.FuncionarioRepository;
import com.jepescados.vendasapi.model.repository.ProdutoRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
@Service
@Data
@NoArgsConstructor
public class ProdutoService {

@Autowired
 private  ProdutoRepository repository;


public byte[] adicionarImagem(Integer id,Part arquivo){
    Optional<Produto> produto = repository.findById(id);
    return produto.map(prod -> {
        try {
            InputStream is = arquivo.getInputStream();
            byte[] bytes = new byte[(int) arquivo.getSize()];
            IOUtils.readFully(is, bytes);
            // prod.setImagens(bytes);
            repository.save(prod);
            is.close();
            return bytes;
        } catch (IOException e) {
            return null;
        }
    }).orElse(null);
}


public List<Produto> listar(){
    List<Produto> temp = repository.findAll();
    return temp;
}


public Produto salvarProduto(Produto produto){
    return repository.save(produto);
}


public Produto salvar(String ProdutoJson,List<MultipartFile> imagens){
    /*
		ObjectMapper mapper = new ObjectMapper();
		Produto prod = new Produto();		
		
		try{
			prod = mapper.readValue(ProdutoJson, Produto.class);
			
			if(imagens != null) {
				byte[] bytes = imagens.getBytes();
				prod.setImagens(bytes);
			} else {
				prod.setImagens(null);
			}
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }		 		
		return repository.save(prod);
		*/
    return null;
}


public Produto listarPorId(Integer id){
    return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
}


}