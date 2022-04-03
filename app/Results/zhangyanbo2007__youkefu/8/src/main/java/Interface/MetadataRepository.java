package Interface;
public interface MetadataRepository {

   public MetadataTable findByTablename(String tablename);
}