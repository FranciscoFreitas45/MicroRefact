package Interface;
public interface LookupValue {

   public T getIdAs(IntFunction<T> idMapper);
   public int getIdAsInt();
}