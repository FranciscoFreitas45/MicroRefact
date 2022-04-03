package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class PurchaseRowLookupsImpl implements PurchaseRowLookups{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public LookupValue createProductLookupValue(ProductId productId,String productValue,String productName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createProductLookupValue"))
    .queryParam("productId",productId)
    .queryParam("productValue",productValue)
    .queryParam("productName",productName);
  LookupValue aux = restTemplate.getForObject(builder.toUriString(), LookupValue.class);

 return aux;
}


public LookupValue createASILookupValue(AttributeSetInstanceId attributeSetInstanceId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createASILookupValue"))
    .queryParam("attributeSetInstanceId",attributeSetInstanceId);
  LookupValue aux = restTemplate.getForObject(builder.toUriString(), LookupValue.class);

 return aux;
}


public LookupValue createBPartnerLookupValue(BPartnerId bpartnerId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createBPartnerLookupValue"))
    .queryParam("bpartnerId",bpartnerId);
  LookupValue aux = restTemplate.getForObject(builder.toUriString(), LookupValue.class);

 return aux;
}


public String createUOMLookupValue(I_C_UOM uom){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createUOMLookupValue"))
    .queryParam("uom",uom);
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public PurchaseRowLookups newInstance(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/newInstance"))
  PurchaseRowLookups aux = restTemplate.getForObject(builder.toUriString(), PurchaseRowLookups.class);

 return aux;
}


}