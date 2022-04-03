package Interface;
public interface Document {

   public Set<String> getFieldNames();
   public Object isEmpty(Object Object);
   public Document copy(Document parentDocumentCopy,CopyMode copyMode);
   public Collection<IDocumentFieldView> getFieldViews();
   public Object stream(Object Object);
   public Object map(Object Object);
   public LookupValuesList getFieldLookupValues(String fieldName);
   public LookupValuesList getFieldLookupValuesForQuery(String fieldName,String query);
   public void processValueChanges(List<JSONDocumentChangedEvent> events,ReasonSupplier reason);
   public void processValueChange(String fieldName,Object value,ReasonSupplier reason);
   public IDocumentFieldView getFieldView(String fieldName);
   public IDocumentEvaluatee asEvaluatee();
   public DocumentSaveStatus saveIfValidAndHasChanges();
   public IDocumentFieldView getFieldViewOrNull(String fieldName);
   public Builder builder(DocumentEntityDescriptor entityDescriptor);
   public DocumentValidStatus checkAndGetValidStatus(OnValidStatusChanged onValidStatusChanged);
}