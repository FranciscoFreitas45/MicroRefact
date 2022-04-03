package Interface;
public interface DocumentId {

   public String toJson();
   public DocumentId of(RepoIdAware id);
   public DocumentId ofString(String idStr);
}