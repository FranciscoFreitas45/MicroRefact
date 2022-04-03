package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.FloatService;
public class FloatServiceImpl implements FloatService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public void deleteAllFloatsBrotherhood(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteAllFloatsBrotherhood"))
;
  restTemplate.put(builder.toUriString(), null);
}


public List<domain.Float> floatsInParadeInFinalMode(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/floatsInParadeInFinalMode"))
  List<domain.Float> aux = restTemplate.getForObject(builder.toUriString(), List<domain.Float>.class);

 return aux;
}


public List<domain.Float> showBrotherhoodFloats(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/showBrotherhoodFloats"))
  List<domain.Float> aux = restTemplate.getForObject(builder.toUriString(), List<domain.Float>.class);

 return aux;
}


public List<String> getPicturesOfFloat(int floatId,boolean parade){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPicturesOfFloat"))
    .queryParam("floatId",floatId)
    .queryParam("parade",parade);
  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


public domain.Float findOne(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id);
  domain.Float aux = restTemplate.getForObject(builder.toUriString(), domain.Float.class);

 return aux;
}


public Boolean isUrl(String url){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isUrl"))
    .queryParam("url",url);
  Boolean aux = restTemplate.getForObject(builder.toUriString(), Boolean.class);

 return aux;
}


public domain.Float addPicture(String picture,domain.Float floatt){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addPicture"))
    .queryParam("picture",picture)
    .queryParam("floatt",floatt);
  domain.Float aux = restTemplate.getForObject(builder.toUriString(), domain.Float.class);

 return aux;
}


public domain.Float create(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
  domain.Float aux = restTemplate.getForObject(builder.toUriString(), domain.Float.class);

 return aux;
}


public domain.Float reconstruct(domain.Float floatt,BindingResult binding){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reconstruct"))
    .queryParam("floatt",floatt)
    .queryParam("binding",binding);
  domain.Float aux = restTemplate.getForObject(builder.toUriString(), domain.Float.class);

 return aux;
}


public domain.Float save(domain.Float floatt){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("floatt",floatt);
  domain.Float aux = restTemplate.getForObject(builder.toUriString(), domain.Float.class);

 return aux;
}


public void remove(domain.Float floatt){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/remove"))
    .queryParam("floatt",floatt);

  restTemplate.put(builder.toUriString(), null);
}


}