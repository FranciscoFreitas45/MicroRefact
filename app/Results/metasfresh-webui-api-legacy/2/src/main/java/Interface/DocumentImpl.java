package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentImpl implements Document{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Set<String> getFieldNames(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFieldNames"))
  Set<String> aux = restTemplate.getForObject(builder.toUriString(), Set<String>.class);

 return aux;
}


public Object isEmpty(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Document copy(Document parentDocumentCopy,CopyMode copyMode){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/copy"))
    .queryParam("parentDocumentCopy",parentDocumentCopy)
    .queryParam("copyMode",copyMode);
  Document aux = restTemplate.getForObject(builder.toUriString(), Document.class);

 return aux;
}


public Collection<IDocumentFieldView> getFieldViews(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFieldViews"))
  Collection<IDocumentFieldView> aux = restTemplate.getForObject(builder.toUriString(), Collection<IDocumentFieldView>.class);

 return aux;
}


public Object stream(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/stream"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object map(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/map"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public LookupValuesList getFieldLookupValues(String fieldName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFieldLookupValues"))
    .queryParam("fieldName",fieldName);
  LookupValuesList aux = restTemplate.getForObject(builder.toUriString(), LookupValuesList.class);

 return aux;
}


public LookupValuesList getFieldLookupValuesForQuery(String fieldName,String query){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFieldLookupValuesForQuery"))
    .queryParam("fieldName",fieldName)
    .queryParam("query",query);
  LookupValuesList aux = restTemplate.getForObject(builder.toUriString(), LookupValuesList.class);

 return aux;
}


public void processValueChanges(List<JSONDocumentChangedEvent> events,ReasonSupplier reason){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/processValueChanges"))
    .queryParam("events",events)
    .queryParam("reason",reason);

  restTemplate.put(builder.toUriString(), null);
}


public void processValueChange(String fieldName,Object value,ReasonSupplier reason){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/processValueChange"))
    .queryParam("fieldName",fieldName)
    .queryParam("value",value)
    .queryParam("reason",reason);

  restTemplate.put(builder.toUriString(), null);
}


public IDocumentFieldView getFieldView(String fieldName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFieldView"))
    .queryParam("fieldName",fieldName);
  IDocumentFieldView aux = restTemplate.getForObject(builder.toUriString(), IDocumentFieldView.class);

 return aux;
}


public IDocumentEvaluatee asEvaluatee(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/asEvaluatee"))
  IDocumentEvaluatee aux = restTemplate.getForObject(builder.toUriString(), IDocumentEvaluatee.class);

 return aux;
}


public DocumentSaveStatus saveIfValidAndHasChanges(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveIfValidAndHasChanges"))
  DocumentSaveStatus aux = restTemplate.getForObject(builder.toUriString(), DocumentSaveStatus.class);

 return aux;
}


public IDocumentFieldView getFieldViewOrNull(String fieldName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFieldViewOrNull"))
    .queryParam("fieldName",fieldName);
  IDocumentFieldView aux = restTemplate.getForObject(builder.toUriString(), IDocumentFieldView.class);

 return aux;
}


public Builder builder(DocumentEntityDescriptor entityDescriptor){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))
    .queryParam("entityDescriptor",entityDescriptor);
  Builder aux = restTemplate.getForObject(builder.toUriString(), Builder.class);

 return aux;
}


public DocumentValidStatus checkAndGetValidStatus(OnValidStatusChanged onValidStatusChanged){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/checkAndGetValidStatus"))
    .queryParam("onValidStatusChanged",onValidStatusChanged);
  DocumentValidStatus aux = restTemplate.getForObject(builder.toUriString(), DocumentValidStatus.class);

 return aux;
}


}