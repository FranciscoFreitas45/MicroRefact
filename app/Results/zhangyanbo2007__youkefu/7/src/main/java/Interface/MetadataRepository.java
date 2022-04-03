package Interface;
public interface MetadataRepository {

   public MetadataTable findByTablename(String tablename);
   public MetadataTable findById(String id);
}