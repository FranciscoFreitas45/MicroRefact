package Interface;
public interface IViewsRepository {

   public IView getViewIfExists(ViewId viewId);
   public IView createView(CreateViewRequest request);
   public void closeView(ViewId viewId,ViewCloseAction closeAction);
}