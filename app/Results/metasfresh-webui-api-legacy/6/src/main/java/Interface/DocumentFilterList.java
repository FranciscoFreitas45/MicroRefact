package Interface;
public interface DocumentFilterList {

   public boolean isEmpty();
   public void forEach(Consumer<DocumentFilter> consumer);
   public DocumentFilterList ofList(Collection<DocumentFilter> list);
   public ImmutableList<DocumentFilter> toList();
}