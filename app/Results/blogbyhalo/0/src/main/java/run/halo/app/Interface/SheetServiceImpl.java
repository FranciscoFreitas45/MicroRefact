package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.SheetService;
public class SheetServiceImpl implements SheetService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public Object pageBy(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/pageBy"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Page<SheetListVO> convertToListVo(Page<Sheet> sheetPage){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/convertToListVo"))
    .queryParam("sheetPage",sheetPage)
;  Page<SheetListVO> aux = restTemplate.getForObject(builder.toUriString(), Page<SheetListVO>.class);

 return aux;
}


public Object getById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public SheetDetailVO convertToDetailVo(Sheet sheet){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/convertToDetailVo"))
    .queryParam("sheet",sheet)
;  SheetDetailVO aux = restTemplate.getForObject(builder.toUriString(), SheetDetailVO.class);

 return aux;
}


public void publishVisitEvent(Integer sheetId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/publishVisitEvent"))
    .queryParam("sheetId",sheetId)
;
  restTemplate.put(builder.toUriString(), null);
}


public Object getBySlug(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBySlug"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}