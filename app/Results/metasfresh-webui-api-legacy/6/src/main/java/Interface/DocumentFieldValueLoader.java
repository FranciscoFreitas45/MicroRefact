package Interface;
public interface DocumentFieldValueLoader {

   public Object retrieveFieldValue(ResultSet rs,boolean isDisplayColumnAvailable,String adLanguage,LookupDescriptor lookupDescriptor);
}