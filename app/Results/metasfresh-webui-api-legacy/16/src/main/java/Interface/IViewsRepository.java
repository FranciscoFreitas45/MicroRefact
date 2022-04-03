package Interface;
public interface IViewsRepository {

   public IView createView(CreateViewRequest request);
   public T getView(ViewId viewId,Class<T> type);
   public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId);
}