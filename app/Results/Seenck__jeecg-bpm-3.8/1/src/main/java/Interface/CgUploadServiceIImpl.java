package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CgUploadServiceI;
public class CgUploadServiceIImpl implements CgUploadServiceI{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public void writeBack(String cgFormId,String cgFormName,String cgFormField,String fileId,String fileUrl){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/writeBack"))
    .queryParam("cgFormId",cgFormId)
    .queryParam("cgFormName",cgFormName)
    .queryParam("cgFormField",cgFormField)
    .queryParam("fileId",fileId)
    .queryParam("fileUrl",fileUrl)
;
  restTemplate.put(builder.toUriString(), null);
}


public void deleteFile(CgUploadEntity file){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteFile"))
    .queryParam("file",file)
;
  restTemplate.put(builder.toUriString(), null);
}


}