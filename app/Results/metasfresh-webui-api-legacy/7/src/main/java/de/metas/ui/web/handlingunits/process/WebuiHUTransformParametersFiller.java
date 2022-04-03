package de.metas.ui.web.handlingunits.process;
 import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nullable;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import org.adempiere.ad.service.IADReferenceDAO;
import org.adempiere.ad.service.IADReferenceDAO.ADRefListItem;
import org.compiere.model.I_AD_Process_Para;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import de.metas.bpartner.BPartnerId;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.allocation.transfer.HUTransformService;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_PI_Item;
import de.metas.handlingunits.model.I_M_HU_PI_Version;
import de.metas.handlingunits.model.X_M_HU;
import de.metas.printing.esb.base.util.Check;
import de.metas.process.AdProcessId;
import de.metas.process.IADProcessDAO;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.product.ProductId;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorRowFilter;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.handlingunits.process.WebuiHUTransformCommand.ActionType;
import de.metas.ui.web.handlingunits.util.WEBUI_ProcessHelper;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlLookupDescriptor;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
public class WebuiHUTransformParametersFiller {

 private  IADReferenceDAO adReferenceDAO;

 private  IHandlingUnitsDAO handlingUnitsDAO;

 private  IHandlingUnitsBL handlingUnitsBL;

 private  HUEditorView _view;

 private  HUEditorRow _selectedRow;

 private  ActionType _actionType;

 private  boolean _checkExistingHUsInsideView;


public LookupValuesList getM_HU_PI_Item_IDs(){
    final ActionType actionType = getActionType();
    if (actionType != ActionType.TU_To_NewLUs) {
        return LookupValuesList.EMPTY;
    }
    final List<I_M_HU_PI_Item> luPIItems = getAvailableLUPIItems();
    return luPIItems.stream().filter(luPIItem -> luPIItem.getM_HU_PI_Version().isCurrent() && luPIItem.getM_HU_PI_Version().isActive() && luPIItem.getM_HU_PI_Version().getM_HU_PI().isActive()).map(luPIItem -> IntegerLookupValue.of(luPIItem.getM_HU_PI_Item_ID(), WEBUI_ProcessHelper.buildHUPIItemString(luPIItem))).sorted(Comparator.comparing(IntegerLookupValue::getDisplayName)).collect(LookupValuesList.collect());
}


public ActionType getActionType(){
    return _actionType;
}


public LookupValuesList getTUsLookupValues_InsideView(LookupDataSourceContext context){
    final ActionType actionType = getActionType();
    if (actionType == ActionType.CU_To_ExistingTU) {
        return getView().streamAllRecursive(HUEditorRowFilter.builder().select(// ..needs to be a TU
        Select.TU).userInputFilter(context.getFilterOrIfAny(null)).excludeHUId(// ..may not be the one TU that 'cu' is already attached to
        getParentHUIdOfSelectedRow()).excludeHUStatus(X_M_HU.HUSTATUS_Destroyed).build()).sorted(Comparator.comparing(row -> row.getHuId().getRepoId())).map(row -> row.toLookupValue()).collect(LookupValuesList.collect());
    }
    return LookupValuesList.EMPTY;
}


public LookupValuesList getLUsLookupValues_All(LookupDataSourceContext context){
    final ActionType actionType = getActionType();
    if (actionType == ActionType.TU_To_ExistingLU) {
        // TODO: cache the descriptor
        // TODO: filter by LUs
        // TODO: search by barcode too
        final LookupDescriptor lookupDescriptor = SqlLookupDescriptor.builder().setCtxTableName(// ctxTableName
        null).setCtxColumnName("M_HU_ID").setDisplayType(DisplayType.Search).buildForDefaultScope();
        LookupDataSource dataSource = LookupDataSourceFactory.instance.getLookupDataSource(lookupDescriptor);
        final LookupValuesList result = dataSource.findEntities(context, context.getFilter(), context.getOffset(0), context.getLimit(10));
        return result;
    } else {
        return LookupValuesList.EMPTY;
    }
}


public LookupValuesList getLUsLookupValues(LookupDataSourceContext context){
    if (isCheckExistingHUsInsideView()) {
        return getLUsLookupValues_InsideView(context);
    } else {
        return getLUsLookupValues_All(context);
    }
}


public LookupValuesList getActions(AdProcessId processId){
    final Set<String> allowedActions = new HashSet<>();
    final HUEditorRow huRow = getSelectedRow();
    if (huRow.isCU()) {
        allowedActions.addAll(getActionTypesForCUs());
    } else if (huRow.isTU()) {
        allowedActions.addAll(getActionTypesForTUs());
    } else if (huRow.isLU()) {
        allowedActions.add(ActionType.LU_Set_Ownership.toString());
    }
    final IADProcessDAO adProcessDAO = Services.get(IADProcessDAO.class);
    final I_AD_Process_Para processParameter = adProcessDAO.retrieveProcessParameter(processId, WEBUI_M_HU_Transform.PARAM_Action);
    final int actionsReferenceId = processParameter.getAD_Reference_Value_ID();
    final Collection<ADRefListItem> allActiveActionItems = adReferenceDAO.retrieveListItems(actionsReferenceId);
    final String adLanguage = Env.getAD_Language();
    return allActiveActionItems.stream().filter(item -> allowedActions.contains(item.getValueName())).map(item -> StringLookupValue.of(item.getValueName(), item.getName(), item.getDescription())).sorted(Comparator.comparing(lookupValue -> lookupValue.getDisplayName(adLanguage))).collect(LookupValuesList.collect());
}


public Set<String> getActionTypesForTUs(){
    final Set<String> selectableTypes = new HashSet<>();
    selectableTypes.add(ActionType.TU_To_NewTUs.toString());
    selectableTypes.add(ActionType.TU_To_NewLUs.toString());
    selectableTypes.add(ActionType.TU_Set_Ownership.toString());
    final boolean existsLUs;
    if (isCheckExistingHUsInsideView()) {
        existsLUs = getView().matchesAnyRowRecursive(HUEditorRowFilter.builder().select(Select.LU).excludeHUId(getParentHUIdOfSelectedRow()).excludeHUStatus(X_M_HU.HUSTATUS_Destroyed).build());
    } else {
        existsLUs = true;
    }
    if (existsLUs) {
        selectableTypes.add(ActionType.TU_To_ExistingLU.toString());
    }
    return selectableTypes;
}


public LookupValuesList getTUsLookupValues(LookupDataSourceContext context){
    if (isCheckExistingHUsInsideView()) {
        return getTUsLookupValues_InsideView(context);
    } else {
        return getTUsLookupValues_All(context);
    }
}


public Optional<I_M_HU_PI_Item_Product> getDefaultM_HU_PI_Item_Product(){
    final HUEditorRow cuRow = getSelectedRow();
    final ProductId productId = cuRow.getProductId();
    final BPartnerId bpartnerId = IHandlingUnitsBL.extractBPartnerIdOrNull(cuRow.getM_HU());
    final List<I_M_HU_PI_Item_Product> allPIs = WEBUI_ProcessHelper.retrieveHUPIItemProductRecords(Env.getCtx(), productId, bpartnerId, false);
    return allPIs.stream().filter(hu -> hu.isActive() && hu.isDefaultForProduct()).min(Comparator.comparingInt(I_M_HU_PI_Item_Product::getM_HU_PI_Item_Product_ID));
}


public LookupValuesList getM_HU_PI_Item_Products(){
    final ActionType actionType = getActionType();
    if (actionType == ActionType.CU_To_NewTUs) {
        final HUEditorRow cuRow = getSelectedRow();
        return retrieveHUPItemProductsForNewTU(cuRow);
    }
    return LookupValuesList.EMPTY;
}


public LookupValuesList retrieveHUPItemProductsForNewTU(HUEditorRow cuRow){
    final ProductId productId = cuRow.getProductId();
    final BPartnerId bpartnerId = IHandlingUnitsBL.extractBPartnerIdOrNull(cuRow.getM_HU());
    return WEBUI_ProcessHelper.retrieveHUPIItemProducts(Env.getCtx(), productId, bpartnerId, // includeVirtualItem = false..moving a cu onto a "virtual" TU makes no sense. Instead, the user can just leave the CU as it is, or take it out of a physical TU
    false);
}


public HUEditorRow getSelectedRow(){
    return _selectedRow;
}


public LookupValuesList getLUsLookupValues_InsideView(LookupDataSourceContext context){
    final ActionType actionType = getActionType();
    if (actionType == ActionType.TU_To_ExistingLU) {
        return getView().streamAllRecursive(HUEditorRowFilter.builder().select(// ..needs to be a LU
        Select.LU).userInputFilter(context.getFilterOrIfAny(null)).excludeHUId(// ..may not be the one LU that 'tu' is already attached to
        getParentHUIdOfSelectedRow()).excludeHUStatus(X_M_HU.HUSTATUS_Destroyed).build()).sorted(Comparator.comparing(row -> row.getHuId().getRepoId())).map(row -> row.toLookupValue()).collect(LookupValuesList.collect());
    }
    return LookupValuesList.EMPTY;
}


public Object getParameterDefaultValue(String parameterName){
    if (WEBUI_M_HU_Transform.PARAM_QtyCU.equals(parameterName)) {
        // should work, because otherwise the param is not even shown.
        final I_M_HU cu = getSelectedRow().getM_HU();
        return HUTransformService.newInstance().getMaximumQtyCU(cu, getSelectedRow().getC_UOM()).toBigDecimal();
    } else if (WEBUI_M_HU_Transform.PARAM_QtyTU.equals(parameterName)) {
        // should work, because otherwise the param is not even shown.
        final I_M_HU tu = getSelectedRow().getM_HU();
        return HUTransformService.newInstance().getMaximumQtyTU(tu);
    } else if (WEBUI_M_HU_Transform.PARAM_HUPlanningReceiptOwnerPM_TU.equals(parameterName)) {
        // should work, because otherwise the param is not even shown.
        return getSelectedRow().isHUPlanningReceiptOwnerPM();
    } else if (WEBUI_M_HU_Transform.PARAM_HUPlanningReceiptOwnerPM_LU.equals(parameterName)) {
        // should work, because otherwise the param is not even shown.
        return getSelectedRow().isHUPlanningReceiptOwnerPM();
    }
    return IProcessDefaultParametersProvider.DEFAULT_VALUE_NOTAVAILABLE;
}


public HuId getParentHUIdOfSelectedRow(){
    final HUEditorRow huRow = getSelectedRow();
    final I_M_HU hu = huRow.getM_HU();
    final int parentIdOfSelectedHU = handlingUnitsDAO.retrieveParentId(hu);
    return HuId.ofRepoIdOrNull(parentIdOfSelectedHU);
}


public LookupValuesList getTUsLookupValues_All(LookupDataSourceContext context){
    final ActionType actionType = getActionType();
    if (actionType == ActionType.CU_To_ExistingTU) {
        // TODO: cache the descriptor
        // TODO: filter by TUs
        // TODO: search by barcode too
        final LookupDescriptor lookupDescriptor = SqlLookupDescriptor.builder().setCtxTableName(// ctxTableName
        null).setCtxColumnName("M_HU_ID").setDisplayType(DisplayType.Search).buildForDefaultScope();
        LookupDataSource dataSource = LookupDataSourceFactory.instance.getLookupDataSource(lookupDescriptor);
        final LookupValuesList result = dataSource.findEntities(context, context.getFilter(), context.getOffset(0), context.getLimit(10));
        return result;
    }
    return LookupValuesList.EMPTY;
}


public Set<String> getActionTypesForCUs(){
    final Set<String> selectableTypes = new HashSet<>();
    selectableTypes.add(ActionType.CU_To_NewCU.toString());
    selectableTypes.add(ActionType.CU_To_NewTUs.toString());
    final boolean existsTUs;
    if (isCheckExistingHUsInsideView()) {
        existsTUs = getView().matchesAnyRowRecursive(HUEditorRowFilter.builder().select(Select.TU).excludeHUId(getParentHUIdOfSelectedRow()).excludeHUStatus(X_M_HU.HUSTATUS_Destroyed).build());
    } else {
        existsTUs = true;
    }
    if (existsTUs) {
        selectableTypes.add(ActionType.CU_To_ExistingTU.toString());
    }
    return selectableTypes;
}


public boolean isCheckExistingHUsInsideView(){
    return _checkExistingHUsInsideView;
}


public HUEditorView getView(){
    return _view;
}


public I_M_HU_PI_Item getDefaultM_LU_PI_ItemOrNull(){
    final List<I_M_HU_PI_Item> luPIItems = getAvailableLUPIItems();
    final Optional<I_M_HU_PI_Item> defaultHUPIItem = luPIItems.stream().filter(luPIItem -> luPIItem.getM_HU_PI_Version().isCurrent() && luPIItem.getM_HU_PI_Version().isActive() && luPIItem.getM_HU_PI_Version().getM_HU_PI().isActive()).sorted(// TODO what to order by ?
    Comparator.comparing(I_M_HU_PI_Item::getM_HU_PI_Item_ID)).findFirst();
    return defaultHUPIItem.orElse(null);
}


public List<I_M_HU_PI_Item> getAvailableLUPIItems(){
    final HUEditorRow tuRow = getSelectedRow();
    final I_M_HU tuHU = tuRow.getM_HU();
    final I_M_HU_PI_Version effectivePIVersion = handlingUnitsBL.getEffectivePIVersion(tuHU);
    Check.errorIf(effectivePIVersion == null, "tuHU is inconsistent; hu={}", tuHU);
    final List<I_M_HU_PI_Item> luPIItems = handlingUnitsDAO.retrieveParentPIItemsForParentPI(effectivePIVersion.getM_HU_PI(), null, IHandlingUnitsBL.extractBPartnerIdOrNull(tuHU));
    return luPIItems;
}


}