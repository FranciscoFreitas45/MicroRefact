import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class QmsMaterielTypeRepositoryImpl implements QmsMaterielTypeRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<QmsMaterielType> findByMaterielTypeCdAndFlagStatus(String materielTypeCd,String string){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByMaterielTypeCdAndFlagStatus"))
    .queryParam("materielTypeCd",materielTypeCd)
    .queryParam("string",string);
  List<QmsMaterielType> aux = restTemplate.getForObject(builder.toUriString(), List<QmsMaterielType>.class)

 return aux;
}


public List<QmsMaterielType> findByMaterielTypeCdAndFlagStatus(String materielTypeCd,String string){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByMaterielTypeCdAndFlagStatus"))
    .queryParam("materielTypeCd",materielTypeCd)
    .queryParam("string",string);
  List<QmsMaterielType> aux = restTemplate.getForObject(builder.toUriString(), List<QmsMaterielType>.class)

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("save"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("save"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findById"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


public List<QmsMaterielType> findByMaterielTypeCdAndFlagStatus(String materielTypeCd,String string){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByMaterielTypeCdAndFlagStatus"))
    .queryParam("materielTypeCd",materielTypeCd)
    .queryParam("string",string);
  List<QmsMaterielType> aux = restTemplate.getForObject(builder.toUriString(), List<QmsMaterielType>.class)

 return aux;
}


}