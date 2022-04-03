package br.com.fatecmogidascruzes.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.DTO.File;
import br.com.fatecmogidascruzes.Request.FileRequest;
public class FileRequestImpl implements FileRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void addDocument(File file,Long id){
 restTemplate.put("http://15/Selection/{id}/File/addDocument",file,id);
 return ;
}


}