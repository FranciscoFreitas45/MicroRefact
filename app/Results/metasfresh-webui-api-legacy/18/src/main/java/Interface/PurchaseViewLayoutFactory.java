package Interface;
public interface PurchaseViewLayoutFactory {

   public Object builder(Object Object);
   public Object caption(Object Object);
   public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType);
}