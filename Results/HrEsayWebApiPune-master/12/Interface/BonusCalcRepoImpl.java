import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class BonusCalcRepoImpl implements BonusCalcRepo{

 private RestTemplate restTemplate;

  String url = "http://11";


public List<BonusCalc> findByDelStatusAndBonusId(int i,int bonusId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByDelStatusAndBonusId"))
    .queryParam("i",i)
    .queryParam("bonusId",bonusId);
  List<BonusCalc> aux = restTemplate.getForObject(builder.toUriString(), List<BonusCalc>.class)

 return aux;
}


}