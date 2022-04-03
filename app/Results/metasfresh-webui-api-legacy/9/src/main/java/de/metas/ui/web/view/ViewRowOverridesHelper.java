package de.metas.ui.web.view;
 import lombok.experimental.UtilityClass;
@UtilityClass
public class ViewRowOverridesHelper {

 public  NullViewRowOverrides NULL;


public boolean extractSupportIncludedViews(IViewRow row,IViewRowOverrides rowOverrides){
    if (rowOverrides != null) {
        if (rowOverrides.getIncludedViewId(row) != null) {
            return true;
        }
    }
    return row.getIncludedViewId() != null;
}


public IViewRowOverrides getViewRowOverrides(IView view){
    if (view instanceof IViewRowOverrides) {
        return (IViewRowOverrides) view;
    } else {
        return NULL;
    }
}


public ViewId extractIncludedViewId(IViewRow row,IViewRowOverrides rowOverrides){
    if (rowOverrides != null) {
        final ViewId includedViewId = rowOverrides.getIncludedViewId(row);
        if (includedViewId != null) {
            return includedViewId;
        }
    }
    return row.getIncludedViewId();
}


}