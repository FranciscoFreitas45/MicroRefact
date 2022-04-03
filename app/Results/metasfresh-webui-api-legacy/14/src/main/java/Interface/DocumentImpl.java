package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentImpl implements Document{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Object setDynAttribute(String name,Object value){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDynAttribute"))
    .queryParam("name",name)
    .queryParam("value",value);
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


public DocumentPath getDocumentPath(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDocumentPath"))
  DocumentPath aux = restTemplate.getForObject(builder.toUriString(), DocumentPath.class);

 return aux;
}


public DocumentId getDocumentId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDocumentId"))
  DocumentId aux = restTemplate.getForObject(builder.toUriString(), DocumentId.class);

 return aux;
}


public DocumentEntityDescriptor getEntityDescriptor(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEntityDescriptor"))
  DocumentEntityDescriptor aux = restTemplate.getForObject(builder.toUriString(), DocumentEntityDescriptor.class);

 return aux;
}


public IDocumentEvaluatee asEvaluatee(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/asEvaluatee"))
  IDocumentEvaluatee aux = restTemplate.getForObject(builder.toUriString(), IDocumentEvaluatee.class);

 return aux;
}


public Builder setShadowParentDocumentEvaluatee(IDocumentEvaluatee shadowParentDocumentEvaluatee){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setShadowParentDocumentEvaluatee"))
    .queryParam("shadowParentDocumentEvaluatee",shadowParentDocumentEvaluatee);
  Builder aux = restTemplate.getForObject(builder.toUriString(), Builder.class);

 return aux;
}


public void assertNewDocumentAllowed(DetailId detailId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/assertNewDocumentAllowed"))
    .queryParam("detailId",detailId);

  restTemplate.put(builder.toUriString(), null);
}


public void processValueChanges(List<JSONDocumentChangedEvent> events,ReasonSupplier reason){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/processValueChanges"))
    .queryParam("events",events)
    .queryParam("reason",reason);

  restTemplate.put(builder.toUriString(), null);
}


public Document getIncludedDocument(DetailId detailId,DocumentId rowId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getIncludedDocument"))
    .queryParam("detailId",detailId)
    .queryParam("rowId",rowId);
  Document aux = restTemplate.getForObject(builder.toUriString(), Document.class);

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


public boolean hasField(String fieldName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/hasField"))
    .queryParam("fieldName",fieldName);
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public Builder builder(DocumentEntityDescriptor entityDescriptor){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))
    .queryParam("entityDescriptor",entityDescriptor);
  Builder aux = restTemplate.getForObject(builder.toUriString(), Builder.class);

 return aux;
}


}