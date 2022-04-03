package Interface;
public interface IViewsRepository {

   public T getView(ViewId viewId,Class<T> type);
   public IView createView(CreateViewRequest request);
   public void notifyRecordsChanged(TableRecordReferenceSet recordRefs);
   public void invalidateView(IView view);
}