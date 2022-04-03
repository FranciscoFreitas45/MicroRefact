package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class PickingSlotViewRepositoryImpl implements PickingSlotViewRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public List<PickingSlotRow> retrievePickingSlotsRows(PickingSlotQuery query){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/retrievePickingSlotsRows"))
    .queryParam("query",query);
  List<PickingSlotRow> aux = restTemplate.getForObject(builder.toUriString(), List<PickingSlotRow>.class);

 return aux;
}


}