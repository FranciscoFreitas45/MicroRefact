package de.metas.ui.web.handlingunits.process;
 import java.util.stream.Stream;
import org.adempiere.exceptions.AdempiereException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.picking.PickFrom;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.handlingunits.picking.requests.PickRequest;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.logging.LogManager;
import de.metas.order.OrderLineId;
import de.metas.picking.api.PickingSlotId;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.picking.husToPick.HUsToPickViewFactory;
import de.metas.ui.web.pporder.PPOrderLineRow;
import de.metas.ui.web.pporder.PPOrderLinesView;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.process.descriptor.ProcessParamLookupValuesProvider;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.util.GuavaCollectors;
import lombok.Builder;
import lombok.Value;
public class WEBUI_M_HU_Pick extends ViewBasedProcessTemplateimplements IProcessDefaultParametersProvider,IProcessPrecondition{

 private  Logger logger;

@Autowired
 private  PickingCandidateService pickingCandidateService;

@Param(parameterName = WEBUI_M_HU_Pick_ParametersFiller.PARAM_M_PickingSlot_ID, mandatory = true)
 private  int pickingSlotIdInt;

@Param(parameterName = WEBUI_M_HU_Pick_ParametersFiller.PARAM_M_ShipmentSchedule_ID, mandatory = true)
 private  int shipmentScheduleIdInt;

 private  HuId huId;

 private  boolean topLevelHU;

 private  boolean huStatusActive;


public WEBUI_M_HU_Pick_ParametersFiller createNewDefaultParametersFiller(){
    final HURow row = getSingleHURow();
    return WEBUI_M_HU_Pick_ParametersFiller.defaultFillerBuilder().huId(row.getHuId()).salesOrderLineId(getSalesOrderLineId()).build();
}


@// 
ProcessParamLookupValuesProvider(// 
parameterName = WEBUI_M_HU_Pick_ParametersFiller.PARAM_M_ShipmentSchedule_ID, // 
numericKey = true, lookupSource = LookupSource.lookup)
public LookupValuesList getShipmentScheduleValues(LookupDataSourceContext context){
    return createNewDefaultParametersFiller().getShipmentScheduleValues(context);
}


public HURow getSingleHURow(){
    return streamHURows().collect(GuavaCollectors.singleElementOrThrow(() -> new AdempiereException("only one selected row was expected")));
}


@Override
public Object getParameterDefaultValue(IProcessDefaultParameter parameter){
    return createNewDefaultParametersFiller().getDefaultValue(parameter);
}


public HURow toHURowOrNull(IViewRow row){
    if (row instanceof HUEditorRow) {
        final HUEditorRow huRow = HUEditorRow.cast(row);
        return HURow.builder().huId(huRow.getHuId()).topLevelHU(huRow.isTopLevel()).huStatusActive(huRow.isHUStatusActive()).build();
    } else if (row instanceof PPOrderLineRow) {
        final PPOrderLineRow ppOrderLineRow = PPOrderLineRow.cast(row);
        // this process does not apply to source HUs
        if (ppOrderLineRow.isSourceHU()) {
            return null;
        }
        if (!ppOrderLineRow.getType().isHUOrHUStorage()) {
            return null;
        }
        return HURow.builder().huId(ppOrderLineRow.getHuId()).topLevelHU(ppOrderLineRow.isTopLevelHU()).huStatusActive(ppOrderLineRow.isHUStatusActive()).build();
    } else {
        new AdempiereException("Row type not supported: " + row).throwIfDeveloperModeOrLogWarningElse(logger);
        return null;
    }
}


@// 
ProcessParamLookupValuesProvider(// 
parameterName = WEBUI_M_HU_Pick_ParametersFiller.PARAM_M_PickingSlot_ID, // 
dependsOn = WEBUI_M_HU_Pick_ParametersFiller.PARAM_M_ShipmentSchedule_ID, // 
numericKey = true, lookupSource = LookupSource.lookup)
public LookupValuesList getPickingSlotValues(LookupDataSourceContext context){
    final WEBUI_M_HU_Pick_ParametersFiller filler = WEBUI_M_HU_Pick_ParametersFiller.pickingSlotFillerBuilder().shipmentScheduleId(ShipmentScheduleId.ofRepoId(shipmentScheduleIdInt)).build();
    return filler.getPickingSlotValues(context);
}


@Override
public String doIt(){
    final HURow row = getSingleHURow();
    pickHU(row);
    return MSG_OK;
}


public OrderLineId getSalesOrderLineId(){
    final IView view = getView();
    if (view instanceof PPOrderLinesView) {
        final PPOrderLinesView ppOrderLinesView = PPOrderLinesView.cast(view);
        return ppOrderLinesView.getSalesOrderLineId();
    } else {
        return null;
    }
}


public Stream<HURow> streamHURows(){
    return streamSelectedRows().map(row -> toHURowOrNull(row)).filter(Objects::nonNull).filter(HURow::isTopLevelHU).filter(HURow::isHuStatusActive);
}


public void pickHU(HURow row){
    final HuId huId = row.getHuId();
    final PickingSlotId pickingSlotId = PickingSlotId.ofRepoId(pickingSlotIdInt);
    final ShipmentScheduleId shipmentScheduleId = ShipmentScheduleId.ofRepoId(shipmentScheduleIdInt);
    pickingCandidateService.pickHU(PickRequest.builder().shipmentScheduleId(shipmentScheduleId).pickFrom(PickFrom.ofHuId(huId)).pickingSlotId(pickingSlotId).build());
    // NOTE: we are not moving the HU to shipment schedule's locator.
    pickingCandidateService.processForHUIds(ImmutableSet.of(huId), shipmentScheduleId);
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (HUsToPickViewFactory.WINDOW_ID.equals(getWindowId())) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not needed in HUsToPick view");
    }
    final ImmutableList<HURow> firstRows = streamHURows().limit(2).collect(ImmutableList.toImmutableList());
    if (firstRows.isEmpty()) {
        // NOTE: we decided to hide this action when there is not available,
        // because we want to cover the requirements of https://github.com/metasfresh/metasfresh-webui-api/issues/683,
        // were we need to hide the action for source HU lines... and does not worth the effort to handle particularly that case.
        return ProcessPreconditionsResolution.rejectWithInternalReason("no eligible HU rows found");
    // return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(WEBUI_M_HU_Messages.MSG_WEBUI_ONLY_TOP_LEVEL_HU));
    }
    if (firstRows.size() != 1) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public void postProcess(boolean success){
    if (!success) {
        return;
    }
    invalidateView();
}


}