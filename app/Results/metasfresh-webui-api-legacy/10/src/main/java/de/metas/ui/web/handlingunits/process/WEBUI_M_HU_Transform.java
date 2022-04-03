package de.metas.ui.web.handlingunits.process;
 import com.google.common.collect.ImmutableSet;
import de.metas.Profiles;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_PI_Item;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.IProcessParametersCallout;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.handlingunits.process.WebuiHUTransformCommand.ActionType;
import de.metas.ui.web.process.descriptor.ProcessParamLookupValuesProvider;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.adempiere.model.InterfaceWrapperHelper.load;
@Profile(Profiles.PROFILE_Webui)
public class WEBUI_M_HU_Transform extends HUEditorProcessTemplateimplements IProcessDefaultParametersProvider,IProcessPrecondition,IProcessParametersCallout{

@Autowired
 private  DocumentCollection documentsCollection;

 public  String PARAM_CheckExistingHUsInsideView;

 protected  String PARAM_Action;

@Param(parameterName = PARAM_Action, mandatory = true)
 private  String p_Action;

 protected  String PARAM_M_HU_PI_Item_Product_ID;

@Param(parameterName = PARAM_M_HU_PI_Item_Product_ID)
 private  I_M_HU_PI_Item_Product p_M_HU_PI_Item_Product;

 protected  String PARAM_M_HU_PI_Item_ID;

@Param(parameterName = PARAM_M_HU_PI_Item_ID)
 private  I_M_HU_PI_Item p_M_HU_PI_Item;

 protected  String PARAM_M_TU_HU_ID;

@Param(parameterName = PARAM_M_TU_HU_ID)
 private  I_M_HU p_M_TU_HU;

 protected  String PARAM_M_LU_HU_ID;

@Param(parameterName = PARAM_M_LU_HU_ID)
 private  I_M_HU p_M_LU_HU;

 protected  String PARAM_QtyCU;

@Param(parameterName = PARAM_QtyCU)
 private  BigDecimal p_QtyCU;

 protected  String PARAM_QtyTU;

@Param(parameterName = PARAM_QtyTU)
 private  BigDecimal p_QtyTU;

 protected  String PARAM_HUPlanningReceiptOwnerPM_LU;

@Param(parameterName = PARAM_HUPlanningReceiptOwnerPM_LU)
 private  boolean p_HUPlanningReceiptOwnerPM_LU;

 protected  String PARAM_HUPlanningReceiptOwnerPM_TU;

@Param(parameterName = PARAM_HUPlanningReceiptOwnerPM_TU)
 private  boolean p_HUPlanningReceiptOwnerPM_TU;


public void updateViewFromResult(WebuiHUTransformCommandResult result){
    final HUEditorView view = getView();
    boolean changes = false;
    if (view.addHUIds(result.getHuIdsToAddToView())) {
        changes = true;
    }
    if (view.removeHUIds(result.getHuIdsToRemoveFromView())) {
        changes = true;
    }
    if (!result.getHuIdsChanged().isEmpty()) {
        removeHUsIfDestroyed(result.getHuIdsChanged());
        changes = true;
    }
    if (removeSelectedRowsIfHUDestoyed()) {
        changes = true;
    }
    // 
    if (changes) {
        view.invalidateAll();
    }
}


@Override
public void onParameterChanged(String parameterName){
    final String actionName = p_Action;
    if (ActionType.TU_To_NewLUs.toString().equals(actionName)) {
        onParameterChanged_ActionTUToNewLUs(parameterName);
    }
    if (PARAM_Action.equals(parameterName) && ActionType.CU_To_NewTUs.toString().equals(actionName)) {
        onParameterChanged_ActionCUToNewTUs();
    }
}


@ProcessParamLookupValuesProvider(parameterName = PARAM_Action, dependsOn = {}, numericKey = false)
public LookupValuesList getActions(){
    return newParametersFiller().getActions(getProcessInfo().getAdProcessId());
}


@ProcessParamLookupValuesProvider(parameterName = PARAM_M_LU_HU_ID, dependsOn = PARAM_Action, numericKey = true, lookupSource = LookupSource.lookup, lookupTableName = I_M_HU.Table_Name)
public LookupValuesList getLULookupValues(LookupDataSourceContext context){
    return newParametersFiller().getLUsLookupValues(context);
}


public boolean removeSelectedRowsIfHUDestoyed(){
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    if (selectedRowIds.isEmpty()) {
        return false;
    } else if (selectedRowIds.isAll()) {
        return false;
    }
    final HUEditorView view = getView();
    final ImmutableSet<HuId> selectedHUIds = view.streamByIds(selectedRowIds).map(row -> row.getHuId()).filter(Objects::nonNull).collect(ImmutableSet.toImmutableSet());
    return removeHUsIfDestroyed(selectedHUIds);
}


@ProcessParamLookupValuesProvider(parameterName = PARAM_M_TU_HU_ID, dependsOn = PARAM_Action, numericKey = true, lookupSource = LookupSource.lookup, lookupTableName = I_M_HU.Table_Name)
public LookupValuesList getTULookupValues(LookupDataSourceContext context){
    return newParametersFiller().getTUsLookupValues(context);
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!isHUEditorView()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not the HU view");
    }
    if (!getSelectedRowIds().isSingleDocumentId()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    return ProcessPreconditionsResolution.accept();
}


public void onParameterChanged_ActionCUToNewTUs(){
    final Optional<I_M_HU_PI_Item_Product> packingItemOptional = newParametersFiller().getDefaultM_HU_PI_Item_Product();
    if (packingItemOptional.isPresent()) {
        p_M_HU_PI_Item_Product = packingItemOptional.get();
        p_QtyCU = packingItemOptional.get().getQty();
    }
}


@ProcessParamLookupValuesProvider(parameterName = PARAM_M_HU_PI_Item_Product_ID, dependsOn = PARAM_Action, numericKey = true, lookupTableName = I_M_HU_PI_Item_Product.Table_Name)
public LookupValuesList getM_HU_PI_Item_Products(){
    return newParametersFiller().getM_HU_PI_Item_Products();
}


@Override
public Object getParameterDefaultValue(IProcessDefaultParameter parameter){
    final String parameterName = parameter.getColumnName();
    return newParametersFiller().getParameterDefaultValue(parameterName);
}


public List<TableRecordReference> getContextDocumentLines(){
    return getView().getReferencingDocumentPaths().stream().map(referencingDocumentPath -> documentsCollection.getTableRecordReference(referencingDocumentPath)).collect(GuavaCollectors.toImmutableList());
}


public void onParameterChanged_ActionTUToNewLUs(String parameterName){
    if (PARAM_Action.equals(parameterName)) {
        final I_M_HU_PI_Item defaultHUPIItem = newParametersFiller().getDefaultM_LU_PI_ItemOrNull();
        p_M_HU_PI_Item = defaultHUPIItem;
        if (defaultHUPIItem != null) {
            p_QtyTU = defaultHUPIItem.getQty();
        }
    } else if (PARAM_M_HU_PI_Item_ID.equals(parameterName) && p_M_HU_PI_Item != null) {
        p_QtyTU = p_M_HU_PI_Item.getQty();
    }
}


public boolean removeHUsIfDestroyed(Collection<HuId> huIds){
    final ImmutableSet<HuId> destroyedHUIds = huIds.stream().distinct().map(huId -> load(huId, I_M_HU.class)).filter(Services.get(IHandlingUnitsBL.class)::isDestroyed).map(I_M_HU::getM_HU_ID).map(HuId::ofRepoId).collect(ImmutableSet.toImmutableSet());
    if (destroyedHUIds.isEmpty()) {
        return false;
    }
    final HUEditorView view = getView();
    final boolean changes = view.removeHUIds(destroyedHUIds);
    return changes;
}


@Override
@RunOutOfTrx
public String doIt(){
    final WebuiHUTransformParameters parameters = WebuiHUTransformParameters.builder().actionType(p_Action == null ? null : ActionType.valueOf(p_Action)).huPIItemProduct(p_M_HU_PI_Item_Product).huPIItem(p_M_HU_PI_Item).tuHU(p_M_TU_HU).luHU(p_M_LU_HU).qtyCU(p_QtyCU).qtyTU(p_QtyTU).huPlanningReceiptOwnerPM_TU(ActionType.valueOf(p_Action).equals(ActionType.TU_Set_Ownership) != p_HUPlanningReceiptOwnerPM_TU).huPlanningReceiptOwnerPM_LU(ActionType.valueOf(p_Action).equals(ActionType.LU_Set_Ownership) != p_HUPlanningReceiptOwnerPM_LU).build();
    final WebuiHUTransformCommand command = WebuiHUTransformCommand.builder().selectedRow(getSingleSelectedRow()).contextDocumentLines(getContextDocumentLines()).parameters(parameters).build();
    final WebuiHUTransformCommandResult result = command.execute();
    updateViewFromResult(result);
    return MSG_OK;
}


@ProcessParamLookupValuesProvider(parameterName = PARAM_M_HU_PI_Item_ID, dependsOn = PARAM_Action, numericKey = true, lookupTableName = I_M_HU_PI_Item.Table_Name)
public LookupValuesList getM_HU_PI_Item_ID(){
    return newParametersFiller().getM_HU_PI_Item_IDs();
}


public WebuiHUTransformParametersFiller newParametersFiller(){
    final HUEditorView view = getView();
    final HUEditorRow selectedRow = getSingleSelectedRow();
    return WebuiHUTransformParametersFiller.builder().view(view).selectedRow(selectedRow).actionType(p_Action == null ? null : ActionType.valueOf(p_Action)).checkExistingHUsInsideView(view.getParameterAsBoolean(PARAM_CheckExistingHUsInsideView, false)).build();
}


}