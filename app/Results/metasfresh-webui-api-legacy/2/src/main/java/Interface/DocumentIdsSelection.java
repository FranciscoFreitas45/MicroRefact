package Interface;
public interface DocumentIdsSelection {

   public DocumentIdsSelection ofCommaSeparatedString(String string);
   public DocumentIdsSelection ofStringSet(Collection<String> stringDocumentIds);
   public boolean isEmpty();
}