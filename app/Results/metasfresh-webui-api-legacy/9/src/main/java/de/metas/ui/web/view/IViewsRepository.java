package de.metas.ui.web.view;
 import java.util.List;
import java.util.Objects;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONFilterViewRequest;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.NonNull;
public interface IViewsRepository {


public void closeView(ViewId viewId,ViewCloseAction closeAction)
;

public void notifyRecordsChanged(TableRecordReferenceSet recordRefs)
;

public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId)
;

public IView getViewIfExists(ViewId viewId)
;

public IView filterView(ViewId viewId,JSONFilterViewRequest jsonRequest)
;

public void notifyRecordChanged(String tableName,int recordId){
    notifyRecordsChanged(TableRecordReferenceSet.of(tableName, recordId));
}
;

public IView deleteStickyFilter(ViewId viewId,String filterId)
;

public List<IView> getViews()
;

public void invalidateView(IView view)
;

public List<ViewProfile> getAvailableProfiles(WindowId windowId,JSONViewDataType viewDataType)
;

public IView createView(CreateViewRequest request)
;

public T getView(ViewId viewId,Class<T> type){
    @SuppressWarnings("unchecked")
    final T view = (T) getView(viewId);
    return view;
}
;

public IViewsIndexStorage getViewsStorageFor(ViewId viewId)
;

}