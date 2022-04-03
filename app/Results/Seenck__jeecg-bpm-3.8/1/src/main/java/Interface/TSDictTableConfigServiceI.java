package Interface;
public interface TSDictTableConfigServiceI {

   public boolean checkDictAuth(String dictionary,String dictCondition);
   public Object getDictText(String dictionary,String dictCondition,String value);
}