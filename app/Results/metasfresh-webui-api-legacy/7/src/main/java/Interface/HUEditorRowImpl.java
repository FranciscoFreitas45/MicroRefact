package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class HUEditorRowImpl implements HUEditorRow{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public boolean isHUPlanningReceiptOwnerPM(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isHUPlanningReceiptOwnerPM"))
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public I_M_HU getM_HU(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getM_HU"))
  I_M_HU aux = restTemplate.getForObject(builder.toUriString(), I_M_HU.class);

 return aux;
}


public I_C_UOM getC_UOM(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getC_UOM"))
  I_C_UOM aux = restTemplate.getForObject(builder.toUriString(), I_C_UOM.class);

 return aux;
}


public boolean isCU(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isCU"))
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public boolean isTU(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isTU"))
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public boolean isLU(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isLU"))
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public ProductId getProductId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getProductId"))
  ProductId aux = restTemplate.getForObject(builder.toUriString(), ProductId.class);

 return aux;
}


}