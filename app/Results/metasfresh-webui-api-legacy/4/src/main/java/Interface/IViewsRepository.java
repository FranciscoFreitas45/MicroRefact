package Interface;
public interface IViewsRepository {

   public T getView(ViewId viewId,Class<T> type);
   public void notifyRecordsChanged(TableRecordReferenceSet recordRefs);
}