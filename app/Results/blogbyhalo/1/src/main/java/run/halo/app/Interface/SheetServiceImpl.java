package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.SheetService;
public class SheetServiceImpl implements SheetService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public Object countByStatus(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countByStatus"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Sheet createBy(Sheet sheet,Set<SheetMeta> metas,boolean autoSave){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createBy"))
    .queryParam("sheet",sheet)
    .queryParam("metas",metas)
    .queryParam("autoSave",autoSave)
;  Sheet aux = restTemplate.getForObject(builder.toUriString(), Sheet.class);

 return aux;
}


}