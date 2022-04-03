package de.metas.ui.web.view;
 import java.util.List;
import java.util.function.Supplier;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONFilterViewRequest;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.NonNull;
public interface IViewFactory {


public IView filterView(IView view,JSONFilterViewRequest filterViewRequest,Supplier<IViewsRepository> viewsRepo){
    final CreateViewRequest createViewRequest = CreateViewRequest.filterViewBuilder(view, filterViewRequest).build();
    return createView(createViewRequest);
}
;

public IView deleteStickyFilter(IView view,String filterId){
    final CreateViewRequest createViewRequest = CreateViewRequest.deleteStickyFilterBuilder(view, filterId).build();
    return createView(createViewRequest);
}
;

public List<ViewProfile> getAvailableProfiles(WindowId windowId){
    return ImmutableList.of();
}
;

public void setViewsRepository(IViewsRepository viewsRepository){
}
;

public IView createView(CreateViewRequest request)
;

public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId)
;

}