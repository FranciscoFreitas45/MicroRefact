package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class PricingConditionsRowLookupsImpl implements PricingConditionsRowLookups{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public PricingConditionsRowLookups newInstance(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/newInstance"))
  PricingConditionsRowLookups aux = restTemplate.getForObject(builder.toUriString(), PricingConditionsRowLookups.class);

 return aux;
}


public String getTemporaryPriceConditionsColor(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTemporaryPriceConditionsColor"))
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public LookupValue lookupProduct(ProductId productId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/lookupProduct"))
    .queryParam("productId",productId);
  LookupValue aux = restTemplate.getForObject(builder.toUriString(), LookupValue.class);

 return aux;
}


public LookupValue lookupPaymentTerm(PaymentTermId paymentTermId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/lookupPaymentTerm"))
    .queryParam("paymentTermId",paymentTermId);
  LookupValue aux = restTemplate.getForObject(builder.toUriString(), LookupValue.class);

 return aux;
}


public LookupValue lookupPriceType(PriceSpecificationType priceType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/lookupPriceType"))
    .queryParam("priceType",priceType);
  LookupValue aux = restTemplate.getForObject(builder.toUriString(), LookupValue.class);

 return aux;
}


public LookupValue lookupUser(UserId userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/lookupUser"))
    .queryParam("userId",userId);
  LookupValue aux = restTemplate.getForObject(builder.toUriString(), LookupValue.class);

 return aux;
}


public LookupValuesList getFieldTypeahead(String fieldName,String query){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFieldTypeahead"))
    .queryParam("fieldName",fieldName)
    .queryParam("query",query);
  LookupValuesList aux = restTemplate.getForObject(builder.toUriString(), LookupValuesList.class);

 return aux;
}


public LookupValuesList getFieldDropdown(String fieldName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFieldDropdown"))
    .queryParam("fieldName",fieldName);
  LookupValuesList aux = restTemplate.getForObject(builder.toUriString(), LookupValuesList.class);

 return aux;
}


public LookupValue lookupBPartner(BPartnerId bpartnerId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/lookupBPartner"))
    .queryParam("bpartnerId",bpartnerId);
  LookupValue aux = restTemplate.getForObject(builder.toUriString(), LookupValue.class);

 return aux;
}


}