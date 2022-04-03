package Interface;
public interface DocumentQueryOrderByList {

   public DocumentQueryOrderByList ofList(List<DocumentQueryOrderBy> list);
   public Comparator<T> toComparator(FieldValueExtractor<T> fieldValueExtractor,JSONOptions jsonOpts);
}