package de.metas.ui.web.picking.husToPick;
 import java.util.List;
import java.util.function.Supplier;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableList;
import de.metas.bpartner.ShipmentAllocationBestBeforePolicy;
import de.metas.inoutcandidate.api.IPackagingDAO;
import de.metas.inoutcandidate.api.IShipmentScheduleBL;
import de.metas.inoutcandidate.api.Packageable;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.process.IADProcessDAO;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.ImmutableDocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.handlingunits.HUEditorViewBuilder;
import de.metas.ui.web.handlingunits.HUEditorViewFactoryTemplate;
import de.metas.ui.web.handlingunits.SqlHUEditorViewRepository.SqlHUEditorViewRepositoryBuilder;
import de.metas.ui.web.order.sales.hu.reservation.HUReservationDocumentFilterService;
import de.metas.ui.web.picking.pickingslot.PickingSlotRowId;
import de.metas.ui.web.picking.pickingslot.PickingSlotView;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.descriptor.annotation.ViewColumnHelper.ClassViewColumnOverrides;
import de.metas.ui.web.view.json.JSONFilterViewRequest;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.MediaType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.util.Services;
import de.metas.util.lang.CoalesceUtil;
import lombok.NonNull;
@ViewFactory(windowId = HUsToPickViewFactory.WINDOW_ID_STRING, viewTypes = { JSONViewDataType.grid, JSONViewDataType.includedView })
public class HUsToPickViewFactory extends HUEditorViewFactoryTemplate{

 static  String WINDOW_ID_STRING;

 public  WindowId WINDOW_ID;

 private  HUReservationDocumentFilterService huReservationDocumentFilterService;

 private  IADProcessDAO adProcessDAO;

 private  IPackagingDAO packagingDAO;

 private  IShipmentScheduleBL shipmentScheduleBL;


@Override
public List<SqlDocumentFilterConverter> createFilterConverters(){
    return ImmutableList.<SqlDocumentFilterConverter>builder().addAll(super.createFilterConverters()).addAll(HUsToPickViewFilters.createFilterConverters()).build();
}


@Override
public IView filterView(IView view,JSONFilterViewRequest filterViewRequest,Supplier<IViewsRepository> viewsRepo){
    final CreateViewRequest.Builder filterViewBuilder = CreateViewRequest.filterViewBuilder(view, filterViewRequest);
    if (view instanceof HUEditorView) {
        final HUEditorView huEditorView = HUEditorView.cast(view);
        filterViewBuilder.setParameters(huEditorView.getParameters());
        final ViewId parentViewId = huEditorView.getParentViewId();
        final IView parentView = viewsRepo.get().getView(parentViewId);
        if (parentView instanceof PickingSlotView) {
            final PickingSlotView pickingSlotView = PickingSlotView.cast(parentView);
            filterViewBuilder.setParameter(HUsToPickViewFilters.PARAM_CurrentShipmentScheduleId, pickingSlotView.getCurrentShipmentScheduleId());
        }
    }
    final CreateViewRequest createViewRequest = filterViewBuilder.build();
    return createView(createViewRequest);
}


@Override
public DocumentFilterDescriptorsProvider createFilterDescriptorsProvider(){
    return ImmutableDocumentFilterDescriptorsProvider.builder().addDescriptors(HUsToPickViewFilters.createFilterDescriptors()).addDescriptors(super.createFilterDescriptorsProvider()).build();
}


public CreateViewRequest createViewRequest(ViewId pickingSlotViewId,PickingSlotRowId pickingSlotRowId,ShipmentScheduleId shipmentScheduleId){
    final ShipmentAllocationBestBeforePolicy bestBeforePolicy = shipmentScheduleBL.getBestBeforePolicy(shipmentScheduleId);
    final Packageable packageable = packagingDAO.getByShipmentScheduleId(shipmentScheduleId);
    final DocumentFilter stickyFilter = huReservationDocumentFilterService.createDocumentFilterIgnoreAttributes(packageable);
    return CreateViewRequest.builder(WINDOW_ID, JSONViewDataType.includedView).setParentViewId(pickingSlotViewId).setParentRowId(pickingSlotRowId.toDocumentId()).setParameter(HUsToPickViewFilters.PARAM_CurrentShipmentScheduleId, shipmentScheduleId).setParameter(HUsToPickViewFilters.PARAM_BestBeforePolicy, bestBeforePolicy).addStickyFilters(stickyFilter).setFilters(// https://github.com/metasfresh/metasfresh-webui-api/issues/1067
    DocumentFilterList.of(HUsToPickViewFilters.createHUIdsFilter(true))).build();
}


@Override
public void customizeViewLayout(ViewLayout.Builder viewLayoutBuilder,JSONViewDataType viewDataType){
    viewLayoutBuilder.clearElements().addElementsFromViewRowClassAndFieldNames(HUEditorRow.class, viewDataType, ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_HUCode).restrictToMediaType(MediaType.SCREEN).build(), ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_Product), ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_HU_UnitType).restrictToMediaType(MediaType.SCREEN).build(), ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_PackingInfo).restrictToMediaType(MediaType.SCREEN).build(), ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_QtyCU), ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_UOM), ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_HUStatus).restrictToMediaType(MediaType.SCREEN).build(), ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_BestBeforeDate), ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_Locator));
}


public DocumentQueryOrderBy createBestBeforeDateOrderBy(ShipmentAllocationBestBeforePolicy bestBeforePolicy){
    final ShipmentAllocationBestBeforePolicy bestBeforePolicyEffective = CoalesceUtil.coalesce(bestBeforePolicy, ShipmentAllocationBestBeforePolicy.Expiring_First);
    if (bestBeforePolicyEffective == ShipmentAllocationBestBeforePolicy.Expiring_First) {
        return DocumentQueryOrderBy.builder().fieldName(HUEditorRow.FIELDNAME_BestBeforeDate).ascending(true).nullsLast(true).build();
    } else if (bestBeforePolicyEffective == ShipmentAllocationBestBeforePolicy.Newest_First) {
        return DocumentQueryOrderBy.builder().fieldName(HUEditorRow.FIELDNAME_BestBeforeDate).ascending(false).nullsLast(true).build();
    } else {
        throw new AdempiereException("Unknown best before policy: " + bestBeforePolicyEffective);
    }
}


@Override
public void customizeHUEditorViewRepository(SqlHUEditorViewRepositoryBuilder huEditorViewRepositoryBuilder){
    huEditorViewRepositoryBuilder.showBestBeforeDate(true);
}


@Override
public void customizeHUEditorView(HUEditorViewBuilder huViewBuilder){
    huViewBuilder.addAdditionalRelatedProcessDescriptor(createProcessDescriptor(de.metas.ui.web.picking.husToPick.process.WEBUI_Picking_HUEditor_PickHU.class)).addAdditionalRelatedProcessDescriptor(createProcessDescriptor(de.metas.ui.web.picking.husToPick.process.WEBUI_HUsToPick_PickCU.class)).addAdditionalRelatedProcessDescriptor(createProcessDescriptor(de.metas.ui.web.picking.husToPick.process.WEBUI_Picking_HUEditor_Create_M_Source_HUs.class)).clearOrderBys().orderBy(DocumentQueryOrderBy.builder().fieldName(HUEditorRow.FIELDNAME_IsReserved).ascending(false).nullsLast(true).build()).orderBy(createBestBeforeDateOrderBy(huViewBuilder.getParameter(HUsToPickViewFilters.PARAM_BestBeforePolicy))).orderBy(DocumentQueryOrderBy.byFieldName(HUEditorRow.FIELDNAME_M_HU_ID));
}


public RelatedProcessDescriptor createProcessDescriptor(Class<?> processClass){
    return RelatedProcessDescriptor.builder().processId(adProcessDAO.retrieveProcessIdByClassIfUnique(processClass)).displayPlace(DisplayPlace.ViewQuickActions).build();
}


}