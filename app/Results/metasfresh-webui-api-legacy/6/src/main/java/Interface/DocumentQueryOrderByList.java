package Interface;
public interface DocumentQueryOrderByList {

   public Collector<DocumentQueryOrderBy,?,DocumentQueryOrderByList> toDocumentQueryOrderByList();
   public boolean isEmpty();
   public ImmutableList<DocumentQueryOrderBy> toList();
   public DocumentQueryOrderByList ofList(List<DocumentQueryOrderBy> list);
}