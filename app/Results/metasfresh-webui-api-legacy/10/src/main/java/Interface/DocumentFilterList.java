package Interface;
public interface DocumentFilterList {

   public DocumentFilterList mergeWith(DocumentFilter filter);
   public Collector<DocumentFilter,?,DocumentFilterList> toDocumentFilterList();
   public Stream<DocumentFilter> stream();
   public Object filter(Object Object);
}