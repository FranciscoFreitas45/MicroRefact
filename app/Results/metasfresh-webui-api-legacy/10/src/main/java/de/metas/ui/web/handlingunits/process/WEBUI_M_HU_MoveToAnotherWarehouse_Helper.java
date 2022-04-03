package de.metas.ui.web.handlingunits.process;
 import java.util.Comparator;
import java.util.List;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.IHUStatusBL;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.attribute.IHUAttributesDAO;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_Warehouse;
import de.metas.handlingunits.movement.api.HUMovementResult;
import de.metas.handlingunits.movement.api.IHUMovementBL;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import de.metas.ui.web.process.descriptor.ProcessParamLookupValuesProvider;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.util.Services;
public class WEBUI_M_HU_MoveToAnotherWarehouse_Helper extends HUEditorProcessTemplateimplements IProcessPrecondition{

 private  IHandlingUnitsDAO handlingUnitsDAO;

 private  IHUMovementBL huMovementBL;

 private  IHUStatusBL huStatusBL;

 final  IHUAttributesDAO huAttributesDAO;

 private  HUMovementResult movementResult;

@Param(parameterName = I_M_Warehouse.COLUMNNAME_M_Warehouse_ID, mandatory = true)
 private  I_M_Warehouse warehouse;


@ProcessParamLookupValuesProvider(parameterName = I_M_Warehouse.COLUMNNAME_M_Warehouse_ID, numericKey = true, lookupSource = LookupSource.lookup)
public LookupValuesList getAvailableWarehouses(LookupDataSourceContext evalCtx){
    final List<org.compiere.model.I_M_Warehouse> warehousesToLoad = handlingUnitsDAO.retrieveWarehousesWhichContainNoneOf(streamSelectedHUs(Select.ONLY_TOPLEVEL).collect(ImmutableList.toImmutableList()));
    return warehousesToLoad.stream().sorted(Comparator.comparing(org.compiere.model.I_M_Warehouse::getName)).map(warehouse -> IntegerLookupValue.of(warehouse.getM_Warehouse_ID(), warehouse.getName())).filter(evalCtx.getFilterPredicate()).collect(LookupValuesList.collect());
}


@Override
public String doIt(){
    final List<I_M_HU> hus = streamSelectedHUs(Select.ONLY_TOPLEVEL).collect(ImmutableList.toImmutableList());
    assertHUsEligible();
    movementResult = huMovementBL.moveHUsToWarehouse(hus, warehouse);
    return MSG_OK;
}


@Override
@OverridingMethodsMustInvokeSuper
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!isHUEditorView()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not the HU view");
    }
    if (!streamSelectedHUIds(Select.ONLY_TOPLEVEL).findAny().isPresent()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(WEBUI_HU_Constants.MSG_WEBUI_ONLY_TOP_LEVEL_HU));
    }
    final boolean activeTopLevelHUsSelected = streamSelectedHUs(Select.ONLY_TOPLEVEL).filter(huRecord -> huStatusBL.isPhysicalHU(huRecord)).findAny().isPresent();
    if (!activeTopLevelHUsSelected) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("no physical (etc active) hus selected");
    }
    return ProcessPreconditionsResolution.accept();
}


public void assertHUsEligible()


@Override
public void postProcess(boolean success){
    // Invalidate the view
    // On refresh we expect the HUs we moved, to not be present here anymore.
    if (movementResult != null) {
        getView().removeHUsAndInvalidate(movementResult.getHusMoved());
    }
}


}