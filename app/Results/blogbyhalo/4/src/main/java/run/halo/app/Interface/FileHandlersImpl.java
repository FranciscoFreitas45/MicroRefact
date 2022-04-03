package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.FileHandlers;
public class FileHandlersImpl implements FileHandlers{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public UploadResult upload(MultipartFile file,AttachmentType attachmentType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/upload"))
    .queryParam("file",file)
    .queryParam("attachmentType",attachmentType)
;  UploadResult aux = restTemplate.getForObject(builder.toUriString(), UploadResult.class);

 return aux;
}


public void delete(Attachment attachment){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("attachment",attachment)
;
  restTemplate.put(builder.toUriString(), null);
}


}