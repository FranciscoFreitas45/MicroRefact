package Interface;
public interface Document {

   public Object setDynAttribute(String name,Object value);
   public Document copy(Document parentDocumentCopy,CopyMode copyMode);
   public DocumentPath getDocumentPath();
   public DocumentId getDocumentId();
   public DocumentEntityDescriptor getEntityDescriptor();
   public IDocumentEvaluatee asEvaluatee();
   public Builder setShadowParentDocumentEvaluatee(IDocumentEvaluatee shadowParentDocumentEvaluatee);
   public void assertNewDocumentAllowed(DetailId detailId);
   public void processValueChanges(List<JSONDocumentChangedEvent> events,ReasonSupplier reason);
   public Document getIncludedDocument(DetailId detailId,DocumentId rowId);
   public LookupValuesList getFieldLookupValues(String fieldName);
   public LookupValuesList getFieldLookupValuesForQuery(String fieldName,String query);
   public boolean hasField(String fieldName);
   public Builder builder(DocumentEntityDescriptor entityDescriptor);
}