package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class GridTabVOBasedDocumentEntityDescriptorFactoryImpl implements GridTabVOBasedDocumentEntityDescriptorFactory{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public ILogicExpression getTabDisplayLogic(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTabDisplayLogic"))
  ILogicExpression aux = restTemplate.getForObject(builder.toUriString(), ILogicExpression.class);

 return aux;
}


public void addFieldsCharacteristic(Set<String> fieldNames,Characteristic characteristic){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addFieldsCharacteristic"))
    .queryParam("fieldNames",fieldNames)
    .queryParam("characteristic",characteristic);

  restTemplate.put(builder.toUriString(), null);
}


public DocumentFieldDescriptor.Builder documentFieldByAD_Field_ID(int adFieldId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/documentFieldByAD_Field_ID"))
    .queryParam("adFieldId",adFieldId);
  DocumentFieldDescriptor.Builder aux = restTemplate.getForObject(builder.toUriString(), DocumentFieldDescriptor.Builder.class);

 return aux;
}


public String getLabelsFieldName(I_AD_UI_Element uiElement){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLabelsFieldName"))
    .queryParam("uiElement",uiElement);
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public DocumentFieldDescriptor.Builder documentField(String fieldName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/documentField"))
    .queryParam("fieldName",fieldName);
  DocumentFieldDescriptor.Builder aux = restTemplate.getForObject(builder.toUriString(), DocumentFieldDescriptor.Builder.class);

 return aux;
}


public DocumentFieldDescriptor.Builder documentFieldByAD_UI_ElementField(I_AD_UI_ElementField elementFieldRecord){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/documentFieldByAD_UI_ElementField"))
    .queryParam("elementFieldRecord",elementFieldRecord);
  DocumentFieldDescriptor.Builder aux = restTemplate.getForObject(builder.toUriString(), DocumentFieldDescriptor.Builder.class);

 return aux;
}


public DocumentEntityDescriptor.Builder documentEntity(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/documentEntity"))
  DocumentEntityDescriptor.Builder aux = restTemplate.getForObject(builder.toUriString(), DocumentEntityDescriptor.Builder.class);

 return aux;
}


public DocumentFieldDescriptor.Builder getSpecialField_DocumentSummary(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSpecialField_DocumentSummary"))
  DocumentFieldDescriptor.Builder aux = restTemplate.getForObject(builder.toUriString(), DocumentFieldDescriptor.Builder.class);

 return aux;
}


public Map<Characteristic,DocumentFieldDescriptor.Builder> getSpecialField_DocSatusAndDocAction(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSpecialField_DocSatusAndDocAction"))
  Map<Characteristic,DocumentFieldDescriptor.Builder> aux = restTemplate.getForObject(builder.toUriString(), Map<Characteristic,DocumentFieldDescriptor.Builder>.class);

 return aux;
}


}