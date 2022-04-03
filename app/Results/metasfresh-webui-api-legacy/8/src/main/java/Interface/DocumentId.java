package Interface;
public interface DocumentId {

   public String toJson();
   public DocumentId ofString(String idStr);
   public DocumentId of(RepoIdAware id);
}