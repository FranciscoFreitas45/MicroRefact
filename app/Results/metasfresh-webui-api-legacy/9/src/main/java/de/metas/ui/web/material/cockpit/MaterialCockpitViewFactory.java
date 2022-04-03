package de.metas.ui.web.material.cockpit;
 import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.service.ISysConfigBL;
import de.metas.i18n.TranslatableStrings;
import de.metas.process.AdProcessId;
import de.metas.process.IADProcessDAO;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.material.cockpit.filters.MaterialCockpitFilters;
import de.metas.ui.web.material.cockpit.process.MD_Cockpit_DocumentDetail_Display;
import de.metas.ui.web.material.cockpit.process.MD_Cockpit_PricingConditions;
import de.metas.ui.web.material.cockpit.process.MD_Cockpit_ShowStockDetails;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.descriptor.ViewLayout.Builder;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.factory.standard.DefaultDocumentDescriptorFactory;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
@// 
ViewFactory(// 
windowId = MaterialCockpitUtil.WINDOWID_MaterialCockpitView_String, viewTypes = { JSONViewDataType.grid, JSONViewDataType.includedView })
public class MaterialCockpitViewFactory implements IViewFactory{

 public  String SYSCFG_DisplayIncludedRows;

 private  MaterialCockpitRowRepository materialCockpitRowRepository;

 private  MaterialCockpitFilters materialCockpitFilters;


public void assertWindowIdOfRequestIsCorrect(CreateViewRequest request){
    final ViewId viewId = request.getViewId();
    final WindowId windowId = viewId.getWindowId();
    Check.errorUnless(MaterialCockpitUtil.WINDOWID_MaterialCockpitView.equals(windowId), "The parameter request needs to have WindowId={}, but has {} instead; request={};", MaterialCockpitUtil.WINDOWID_MaterialCockpitView, windowId, request);
}


@Override
public IView createView(CreateViewRequest request){
    assertWindowIdOfRequestIsCorrect(request);
    final DocumentFilterDescriptorsProvider filterDescriptors = materialCockpitFilters.getFilterDescriptors();
    final DocumentFilterList requestFilters = materialCockpitFilters.extractDocumentFilters(request);
    final DocumentFilterList filtersToUse = request.isUseAutoFilters() ? materialCockpitFilters.createAutoFilters() : requestFilters;
    final MaterialCockpitView view = MaterialCockpitView.builder().viewId(request.getViewId()).description(TranslatableStrings.empty()).filters(filtersToUse).filterDescriptors(filterDescriptors).rowsData(materialCockpitRowRepository.createRowsData(filtersToUse)).relatedProcessDescriptor(createProcessDescriptor(MD_Cockpit_DocumentDetail_Display.class)).relatedProcessDescriptor(createProcessDescriptor(MD_Cockpit_PricingConditions.class)).relatedProcessDescriptor(createProcessDescriptor(MD_Cockpit_ShowStockDetails.class)).build();
    return view;
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
    Check.errorUnless(MaterialCockpitUtil.WINDOWID_MaterialCockpitView.equals(windowId), "The parameter windowId needs to be {}, but is {} instead; viewDataType={}; ", MaterialCockpitUtil.WINDOWID_MaterialCockpitView, windowId, viewDataType);
    final boolean displayIncludedRows = Services.get(ISysConfigBL.class).getBooleanValue(SYSCFG_DisplayIncludedRows, true);
    final Builder viewlayOutBuilder = ViewLayout.builder().setWindowId(windowId).setHasTreeSupport(displayIncludedRows).setTreeCollapsible(true).setTreeExpandedDepth(ViewLayout.TreeExpandedDepth_AllCollapsed).addElementsFromViewRowClass(MaterialCockpitRow.class, viewDataType).setFilters(materialCockpitFilters.getFilterDescriptors().getAll());
    return viewlayOutBuilder.build();
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