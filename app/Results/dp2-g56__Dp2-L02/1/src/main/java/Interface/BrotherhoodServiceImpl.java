package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.BrotherhoodService;
public class BrotherhoodServiceImpl implements BrotherhoodService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Brotherhood findOne(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id)
;  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


public List<Brotherhood> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
;  List<Brotherhood> aux = restTemplate.getForObject(builder.toUriString(), List<Brotherhood>.class);

 return aux;
}


public List<Parade> getParadesByBrotherhoodFinal(Brotherhood b){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getParadesByBrotherhoodFinal"))
    .queryParam("b",b)
;  List<Parade> aux = restTemplate.getForObject(builder.toUriString(), List<Parade>.class);

 return aux;
}


public List<Member> getMembersOfBrotherhood(Brotherhood bro){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getMembersOfBrotherhood"))
    .queryParam("bro",bro)
;  List<Member> aux = restTemplate.getForObject(builder.toUriString(), List<Member>.class);

 return aux;
}


public List<Float> getFloatsByBrotherhood(Brotherhood b){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFloatsByBrotherhood"))
    .queryParam("b",b)
;  List<Float> aux = restTemplate.getForObject(builder.toUriString(), List<Float>.class);

 return aux;
}


public int getBrotherhoodIdByLegalRecord(int legalRecordId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBrotherhoodIdByLegalRecord"))
    .queryParam("legalRecordId",legalRecordId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int getBrotherhoodIdByInceptionRecord(int inceptionRecordId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBrotherhoodIdByInceptionRecord"))
    .queryParam("inceptionRecordId",inceptionRecordId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int getBrotherhoodIdByPeriodRecord(int periodRecordId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBrotherhoodIdByPeriodRecord"))
    .queryParam("periodRecordId",periodRecordId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public List<Brotherhood> getBrotherhoodsByArea(Integer areaId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBrotherhoodsByArea"))
    .queryParam("areaId",areaId)
;  List<Brotherhood> aux = restTemplate.getForObject(builder.toUriString(), List<Brotherhood>.class);

 return aux;
}


public Brotherhood loggedBrotherhood(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedBrotherhood"))
  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


public Brotherhood reconstructBrotherhood(Brotherhood brotherhood,BindingResult binding){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reconstructBrotherhood"))
    .queryParam("brotherhood",brotherhood)
    .queryParam("binding",binding);
  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


public Brotherhood save(Brotherhood h){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("h",h);
  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


public Brotherhood addPicture(String picture,Brotherhood brotherhood){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addPicture"))
    .queryParam("picture",picture)
    .queryParam("brotherhood",brotherhood);
  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


public void loggedAsBrotherhood(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedAsBrotherhood"))

  restTemplate.put(builder.toUriString(), null);
}


}