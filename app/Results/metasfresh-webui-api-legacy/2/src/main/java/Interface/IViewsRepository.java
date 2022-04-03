package Interface;
public interface IViewsRepository {

   public T getView(ViewId viewId,Class<T> type);
   public IView createView(CreateViewRequest request);
   public IView filterView(ViewId viewId,JSONFilterViewRequest jsonRequest);
   public IView deleteStickyFilter(ViewId viewId,String filterId);
   public void closeView(ViewId viewId,ViewCloseAction closeAction);
   public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId);
   public List<ViewProfile> getAvailableProfiles(WindowId windowId,JSONViewDataType viewDataType);
}