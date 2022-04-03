package DTO;
 import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.FloatRepository;
import domain.Brotherhood;
import domain.Parade;
import forms.FormObjectParadeFloat;
import forms.FormObjectParadeFloatCheckbox;
public class FloatService {

 private  FloatRepository floatRepository;

 private  BrotherhoodService brotherhoodService;

 private  ParadeService paradeService;

 private  Validator validator;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public Map<Integer,String> getMapAvailableFloats(){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    List<domain.Float> floats = loggedBrotherhood.getFloats();
    Map<Integer, String> map = new HashMap<>();
    for (domain.Float floatt : floats) map.put(floatt.getId(), floatt.getTitle());
    return map;
}


public List<String> getPicturesOfFloat(int floatId,boolean parade){
    this.brotherhoodService.loggedAsBrotherhood();
    Assert.isTrue(parade);
    domain.Float floatt;
    List<String> pictures;
    floatt = this.findOne(floatId);
    Assert.notNull(floatt);
    Brotherhood bro = this.brotherhoodService.loggedBrotherhood();
    Assert.isTrue(bro.getFloats().contains(floatt));
    pictures = floatt.getPictures();
    return pictures;
}


public domain.Float findOne(int id){
    return this.floatRepository.findOne(id);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))

.queryParam("id",id)
;
domain.Float aux = restTemplate.getForObject(builder.toUriString(),domain.Float.class);
return aux;
}


public Boolean isUrl(String url){
    try {
        new URL(url).toURI();
        return true;
    } catch (Exception e) {
        return false;
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isUrl"))

.queryParam("url",url)
;
Boolean aux = restTemplate.getForObject(builder.toUriString(),Boolean.class);
return aux;
}


}