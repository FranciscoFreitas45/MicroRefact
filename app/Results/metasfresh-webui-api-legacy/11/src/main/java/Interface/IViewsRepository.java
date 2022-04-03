package Interface;
public interface IViewsRepository {

   public T getView(ViewId viewId,Class<T> type);
   public void invalidateView(IView view);
   public IView createView(CreateViewRequest request);
   public void notifyRecordChanged(String tableName,int recordId);
}