package Interface;
public interface IViewsRepository {

   public List<IView> getViews();
   public Object stream(Object Object);
   public Object map(Object Object);
   public T getView(ViewId viewId,Class<T> type);
}