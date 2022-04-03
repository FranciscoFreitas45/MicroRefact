package Interface;
public interface DocumentQueryOrderByList {

   public DocumentQueryOrderByList parse(String orderBysListStr);
   public boolean isEmpty();
   public boolean equals(DocumentQueryOrderByList list1,DocumentQueryOrderByList list2);
   public Comparator<T> toComparator(FieldValueExtractor<T> fieldValueExtractor,JSONOptions jsonOpts);
}