package Interface;
public interface HUEditorRowAttributesProvider {

   public void invalidateAll();
   public DocumentId createAttributeKey(HuId huId);
   public HUEditorRowAttributes getAttributes(DocumentId viewRowId,DocumentId huId);
}