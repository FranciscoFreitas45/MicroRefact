package Interface;
public interface IViewsRepository {

   public T getView(ViewId viewId,Class<T> type);
   public void closeView(ViewId viewId,ViewCloseAction closeAction);
   public IView getViewIfExists(ViewId viewId);
   public IView createView(CreateViewRequest request);
   public void invalidateView(IView view);
}