package Interface;
public interface DocumentQueryOrderByList {

   public DocumentQueryOrderByList ofList(List<DocumentQueryOrderBy> list);
   public boolean equals(DocumentQueryOrderByList list1,DocumentQueryOrderByList list2);
   public ImmutableList<DocumentQueryOrderBy> toList();
}