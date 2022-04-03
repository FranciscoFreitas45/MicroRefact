package Interface;
public interface ETag {

   public ETag of(long version,Map<String,String> attributes);
   public ETag overridingAttributes(Map<String,String> overridingAttributes);
}