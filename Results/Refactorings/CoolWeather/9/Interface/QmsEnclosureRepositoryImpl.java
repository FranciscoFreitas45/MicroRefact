import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class QmsEnclosureRepositoryImpl implements QmsEnclosureRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<QmsEnclosure> findAllByInspectionInfoIdAndInspectionKbn(Integer inspectionInfoId,String inpectionKbn){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findAllByInspectionInfoIdAndInspectionKbn"))
    .queryParam("inspectionInfoId",inspectionInfoId)
    .queryParam("inpectionKbn",inpectionKbn);
  List<QmsEnclosure> aux = restTemplate.getForObject(builder.toUriString(), List<QmsEnclosure>.class)

 return aux;
}


}