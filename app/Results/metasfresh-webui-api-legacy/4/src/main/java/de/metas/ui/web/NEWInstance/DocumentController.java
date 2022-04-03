package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentController {

 private Document document;


@GetMapping
("/getFieldNames")
public Set<String> getFieldNames(){
  return document.getFieldNames();
}


@GetMapping
("/isEmpty")
public Object isEmpty(@RequestParam(name = "Object") Object Object){
  return document.isEmpty(Object);
}


@GetMapping
("/copy")
public Document copy(@RequestParam(name = "parentDocumentCopy") Document parentDocumentCopy,@RequestParam(name = "copyMode") CopyMode copyMode){
  return document.copy(parentDocumentCopy,copyMode);
}


@GetMapping
("/getFieldViews")
public Collection<IDocumentFieldView> getFieldViews(){
  return document.getFieldViews();
}


@GetMapping
("/stream")
public Object stream(@RequestParam(name = "Object") Object Object){
  return document.stream(Object);
}


@GetMapping
("/map")
public Object map(@RequestParam(name = "Object") Object Object){
  return document.map(Object);
}


@GetMapping
("/getFieldLookupValues")
public LookupValuesList getFieldLookupValues(@RequestParam(name = "fieldName") String fieldName){
  return document.getFieldLookupValues(fieldName);
}


@GetMapping
("/getFieldLookupValuesForQuery")
public LookupValuesList getFieldLookupValuesForQuery(@RequestParam(name = "fieldName") String fieldName,@RequestParam(name = "query") String query){
  return document.getFieldLookupValuesForQuery(fieldName,query);
}


@PutMapping
("/processValueChanges")
public void processValueChanges(@RequestParam(name = "events") List<JSONDocumentChangedEvent> events,@RequestParam(name = "reason") ReasonSupplier reason){
document.processValueChanges(events,reason);
}


@PutMapping
("/processValueChange")
public void processValueChange(@RequestParam(name = "fieldName") String fieldName,@RequestParam(name = "value") Object value,@RequestParam(name = "reason") ReasonSupplier reason){
document.processValueChange(fieldName,value,reason);
}


@GetMapping
("/getFieldView")
public IDocumentFieldView getFieldView(@RequestParam(name = "fieldName") String fieldName){
  return document.getFieldView(fieldName);
}


@GetMapping
("/asEvaluatee")
public IDocumentEvaluatee asEvaluatee(){
  return document.asEvaluatee();
}


@GetMapping
("/saveIfValidAndHasChanges")
public DocumentSaveStatus saveIfValidAndHasChanges(){
  return document.saveIfValidAndHasChanges();
}


@GetMapping
("/getFieldViewOrNull")
public IDocumentFieldView getFieldViewOrNull(@RequestParam(name = "fieldName") String fieldName){
  return document.getFieldViewOrNull(fieldName);
}


@GetMapping
("/builder")
public Builder builder(@RequestParam(name = "entityDescriptor") DocumentEntityDescriptor entityDescriptor){
  return document.builder(entityDescriptor);
}


@GetMapping
("/checkAndGetValidStatus")
public DocumentValidStatus checkAndGetValidStatus(@RequestParam(name = "onValidStatusChanged") OnValidStatusChanged onValidStatusChanged){
  return document.checkAndGetValidStatus(onValidStatusChanged);
}


@GetMapping
("/setDynAttribute")
public Object setDynAttribute(@RequestParam(name = "name") String name,@RequestParam(name = "value") Object value){
  return document.setDynAttribute(name,value);
}


@GetMapping
("/getDocumentPath")
public DocumentPath getDocumentPath(){
  return document.getDocumentPath();
}


@GetMapping
("/getDocumentId")
public DocumentId getDocumentId(){
  return document.getDocumentId();
}


@GetMapping
("/getEntityDescriptor")
public DocumentEntityDescriptor getEntityDescriptor(){
  return document.getEntityDescriptor();
}


@GetMapping
("/setShadowParentDocumentEvaluatee")
public Builder setShadowParentDocumentEvaluatee(@RequestParam(name = "shadowParentDocumentEvaluatee") IDocumentEvaluatee shadowParentDocumentEvaluatee){
  return document.setShadowParentDocumentEvaluatee(shadowParentDocumentEvaluatee);
}


@PutMapping
("/assertNewDocumentAllowed")
public void assertNewDocumentAllowed(@RequestParam(name = "detailId") DetailId detailId){
document.assertNewDocumentAllowed(detailId);
}


@GetMapping
("/getIncludedDocument")
public Document getIncludedDocument(@RequestParam(name = "detailId") DetailId detailId,@RequestParam(name = "rowId") DocumentId rowId){
  return document.getIncludedDocument(detailId,rowId);
}


@GetMapping
("/hasField")
public boolean hasField(@RequestParam(name = "fieldName") String fieldName){
  return document.hasField(fieldName);
}


}