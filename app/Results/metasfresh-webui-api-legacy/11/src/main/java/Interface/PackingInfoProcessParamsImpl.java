package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class PackingInfoProcessParamsImpl implements PackingInfoProcessParams{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Object builder(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object defaultLUTUConfigManager(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/defaultLUTUConfigManager"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public void setTU_HU_PI_Item_Product_ID(int tu_HU_PI_Item_Product_ID){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTU_HU_PI_Item_Product_ID"))
    .queryParam("tu_HU_PI_Item_Product_ID",tu_HU_PI_Item_Product_ID);

  restTemplate.put(builder.toUriString(), null);
}


public void setLuPiItemId(int lu_PI_Item_ID){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLuPiItemId"))
    .queryParam("lu_PI_Item_ID",lu_PI_Item_ID);

  restTemplate.put(builder.toUriString(), null);
}


public void setQtyLU(BigDecimal qtyLU){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQtyLU"))
    .queryParam("qtyLU",qtyLU);

  restTemplate.put(builder.toUriString(), null);
}


public void setQtyTU(BigDecimal qtyTU){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQtyTU"))
    .queryParam("qtyTU",qtyTU);

  restTemplate.put(builder.toUriString(), null);
}


public void setQtyCU(BigDecimal qtyCU){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQtyCU"))
    .queryParam("qtyCU",qtyCU);

  restTemplate.put(builder.toUriString(), null);
}


}