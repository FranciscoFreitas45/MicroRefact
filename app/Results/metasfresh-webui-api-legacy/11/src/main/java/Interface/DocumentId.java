package Interface;
public interface DocumentId {

   public DocumentId ofString(String idStr);
   public String toJson();
   public DocumentId of(RepoIdAware id);
}