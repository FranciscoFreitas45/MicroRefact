package Interface;
public interface ViewRowIdsSelection {

   public ViewRowIdsSelection of(ViewId viewId,DocumentIdsSelection rowIds);
   public ViewRowIdsSelection ofNullableStrings(String viewIdStr,Set<String> rowIdsStringSet);
   public Object getRowIds(Object Object);
   public Object getViewId(Object Object);
}