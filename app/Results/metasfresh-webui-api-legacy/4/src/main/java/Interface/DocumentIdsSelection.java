package Interface;
public interface DocumentIdsSelection {

   public DocumentIdsSelection ofCommaSeparatedString(String string);
   public DocumentIdsSelection fromNullable(DocumentId documentId);
   public boolean isSingleDocumentId();
   public boolean isEmpty();
   public String toCommaSeparatedString();
   public DocumentId getSingleDocumentId();
   public DocumentIdsSelection of(Collection<DocumentId> documentIds);
   public ImmutableSet<T> toSet(Function<DocumentId,T> mapper);
}