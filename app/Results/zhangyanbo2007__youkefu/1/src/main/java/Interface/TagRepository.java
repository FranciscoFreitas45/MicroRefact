package Interface;
public interface TagRepository {

   public List<Tag> findByOrgiAndTagtype(String orgi,String tagtype);
}