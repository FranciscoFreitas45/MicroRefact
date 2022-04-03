package Interface;
public interface ViewId {

   public String getViewIdPart();
   public int getPartAsInt(int index);
   public ViewId ofParts(WindowId windowId,String viewIdPart,String otherParts);
}