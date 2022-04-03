package de.metas.ui.web.view;
 import java.util.Objects;
import javax.annotation.Nullable;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
public class ViewRowIdsOrderedSelection {

 private ViewId viewId;

 private long size;

 private DocumentQueryOrderByList orderBys;

 private int queryLimit;

 private boolean queryLimitHit;


public boolean equals(ViewRowIdsOrderedSelection s1,ViewRowIdsOrderedSelection s2){
    return Objects.equals(s1, s2);
}


public String getSelectionId(){
    return getViewId().getViewId();
}


public ViewRowIdsOrderedSelection withSize(int size){
    return this.size == size ? this : toBuilder().size(size).build();
}


public WindowId getWindowId(){
    return getViewId().getWindowId();
}


}