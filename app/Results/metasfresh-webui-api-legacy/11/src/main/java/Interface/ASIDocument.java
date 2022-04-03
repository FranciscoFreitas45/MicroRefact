package Interface;
public interface ASIDocument {

   public DocumentId getDocumentId();
   public Collection<IDocumentFieldView> getFieldViews();
   public Object stream(Object Object);
   public Object map(Object Object);
}