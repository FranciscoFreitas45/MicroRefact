package Interface;
public interface LookupValue {

   public String getDisplayName(String adLanguage);
   public String getIdAsString();
   public int getIdAsInt();
   public T getIdAs(IntFunction<T> idMapper);
}