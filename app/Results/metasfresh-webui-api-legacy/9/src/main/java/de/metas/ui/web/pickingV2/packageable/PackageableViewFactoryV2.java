package de.metas.ui.web.pickingV2.packageable;
 import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableList;
import de.metas.money.MoneyService;
import de.metas.process.AdProcessId;
import de.metas.process.IADProcessDAO;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.pickingV2.PickingConstantsV2;
import de.metas.ui.web.pickingV2.packageable.process.PackageablesView_OpenProductsToPick;
import de.metas.ui.web.pickingV2.packageable.process.PackageablesView_PrintPicklist;
import de.metas.ui.web.pickingV2.packageable.process.PackageablesView_UnlockAll;
import de.metas.ui.web.pickingV2.packageable.process.PackageablesView_UnlockFromLoggedUser;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Services;
import lombok.NonNull;
@ViewFactory(windowId = PickingConstantsV2.WINDOWID_PackageableView_String, viewTypes = { JSONViewDataType.grid, JSONViewDataType.includedView })
public class PackageableViewFactoryV2 implements IViewFactory{

 private  PackageableRowsRepository rowsRepo;


public Iterable<? extends RelatedProcessDescriptor> getRelatedProcessDescriptors(){
    return ImmutableList.of(createProcessDescriptor(PackageablesView_OpenProductsToPick.class), createProcessDescriptor(PackageablesView_UnlockFromLoggedUser.class), createProcessDescriptor(PackageablesView_UnlockAll.class), createProcessDescriptor(PackageablesView_PrintPicklist.class));
}


@Override
public PackageableView createView(CreateViewRequest request){
    final ViewId viewId = request.getViewId();
    viewId.assertWindowId(PickingConstantsV2.WINDOWID_PackageableView);
    final DocumentFilterDescriptorsProvider filterDescriptors = getFilterDescriptorsProvider();
    final PackageableRowsData rowsData = rowsRepo.newPackageableRowsData().filters(request.getFiltersUnwrapped(filterDescriptors)).stickyFilters(request.getStickyFilters()).build();
    return PackageableView.builder().viewId(viewId).rowsData(rowsData).relatedProcessDescriptors(getRelatedProcessDescriptors()).viewFilterDescriptors(filterDescriptors).build();
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
    return ViewLayout.builder().setWindowId(PickingConstantsV2.WINDOWID_PackageableView).setCaption(// TODO: trl
    "Picking").setAllowOpeningRowDetails(false).setFilters(getFilterDescriptorsProvider().getAll()).addElementsFromViewRowClass(PackageableRow.class, viewDataType).build();
}


public DocumentFilterDescriptorsProvider getFilterDescriptorsProvider(){
    return PackageableViewFilters.getDescriptors();
}


public RelatedProcessDescriptor createProcessDescriptor(Class<?> processClass){
    final IADProcessDAO adProcessDAO = Services.get(IADProcessDAO.class);
    final AdProcessId processId = adProcessDAO.retrieveProcessIdByClass(processClass);
    if (processId == null) {
        throw new AdempiereException("No processId found for " + processClass);
    }
    return RelatedProcessDescriptor.builder().processId(processId).anyTable().anyWindow().displayPlace(DisplayPlace.ViewQuickActions).build();
}


}