package Interface;
public interface IViewsRepository {

   public T getView(ViewId viewId,Class<T> type);
   public Object getById(Object Object);
   public Object getAttributes(Object Object);
   public Object toJson(Object Object);
   public Object processChanges(Object Object);
   public Object getAttributeTypeahead(Object Object);
   public Object getAttributeDropdown(Object Object);
}