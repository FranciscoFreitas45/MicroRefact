package Interface;
public interface DocumentId {

   public DocumentId toIncludedRowId();
   public DocumentId of(RepoIdAware id);
   public T toId(IntFunction<T> mapper);
}