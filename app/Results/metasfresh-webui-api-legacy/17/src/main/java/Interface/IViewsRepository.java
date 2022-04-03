package Interface;
public interface IViewsRepository {

   public IView createView(CreateViewRequest request);
   public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId);
   public T getView(ViewId viewId,Class<T> type);
   public IView filterView(ViewId viewId,JSONFilterViewRequest jsonRequest);
   public Object getFilterParameterTypeahead(Object Object);
   public Object getFilterParameterDropdown(Object Object);
}