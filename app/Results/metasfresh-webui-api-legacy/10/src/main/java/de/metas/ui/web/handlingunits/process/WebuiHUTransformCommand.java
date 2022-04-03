package de.metas.ui.web.handlingunits.process;
 import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.allocation.transfer.HUTransformService;
import de.metas.handlingunits.allocation.transfer.IHUSplitBuilder;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_PI_Item;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.quantity.Quantity;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorRowId;
import de.metas.ui.web.handlingunits.process.WebuiHUTransformCommandResult.WebuiHUTransformCommandResultBuilder;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.FillMandatoryException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.impl.TableRecordReference;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;
public class WebuiHUTransformCommand {

 private  HUEditorRow _selectedRow;

 private  List<TableRecordReference> _contextDocumentLines;

 private  WebuiHUTransformParameters _parameters;


public WebuiHUTransformCommandResult action_SplitCU_To_ExistingTU(HUEditorRow cuRow,I_M_HU tuHU,Quantity qtyCU){
    final IHandlingUnitsBL handlingUnitsBL = Services.get(IHandlingUnitsBL.class);
    final List<I_M_HU> createdCUs = newHUTransformation().cuToExistingTU(cuRow.getM_HU(), qtyCU, tuHU);
    final HuId huIdChanged = cuRow.getHURowId().getTopLevelHUId();
    final HuId topLevelHuIdChanged = HuId.ofRepoId(handlingUnitsBL.getTopLevelParent(tuHU).getM_HU_ID());
    final ImmutableList<HuId> huIdsCreated = createdCUs.stream().map(I_M_HU::getM_HU_ID).map(HuId::ofRepoId).collect(ImmutableList.toImmutableList());
    return WebuiHUTransformCommandResult.builder().huIdChanged(huIdChanged).huIdChanged(topLevelHuIdChanged).huIdsCreated(huIdsCreated).build();
}


public ActionType getActionType(){
    return getParameters().getActionType();
}


public WebuiHUTransformCommandResult action_SplitTU_To_NewTUs(HUEditorRow tuRow,BigDecimal qtyTU){
    final IHandlingUnitsBL handlingUnitsBL = Services.get(IHandlingUnitsBL.class);
    // TODO: if qtyTU is the "maximum", then don't do anything, but show a user message
    final I_M_HU fromTU = tuRow.getM_HU();
    final I_M_HU fromTopLevelHU = handlingUnitsBL.getTopLevelParent(fromTU);
    final List<I_M_HU> createdHUs = newHUTransformation().tuToNewTUs(fromTU, qtyTU);
    final ImmutableSet<HuId> huIdsToAddToView = createdHUs.stream().map(I_M_HU::getM_HU_ID).map(HuId::ofRepoId).collect(ImmutableSet.toImmutableSet());
    final WebuiHUTransformCommandResultBuilder resultBuilder = WebuiHUTransformCommandResult.builder().huIdsToAddToView(huIdsToAddToView);
    if (handlingUnitsBL.isDestroyedRefreshFirst(fromTopLevelHU)) {
        resultBuilder.huIdToRemoveFromView(HuId.ofRepoId(fromTopLevelHU.getM_HU_ID()));
    } else {
        resultBuilder.huIdChanged(HuId.ofRepoId(fromTopLevelHU.getM_HU_ID()));
    }
    return resultBuilder.build();
}


public WebuiHUTransformCommandResult execute(){
    final HUEditorRow row = getSelectedRow();
    final ActionType action = getActionType();
    final WebuiHUTransformParameters parameters = getParameters();
    switch(action) {
        case CU_To_NewCU:
            {
                return action_SplitCU_To_NewCU(row, Quantity.of(parameters.getQtyCU(), row.getC_UOM()));
            }
        case CU_To_ExistingTU:
            {
                return action_SplitCU_To_ExistingTU(row, parameters.getTuHU(), Quantity.of(parameters.getQtyCU(), row.getC_UOM()));
            }
        case CU_To_NewTUs:
            {
                if (parameters.getHuPIItemProduct() == null) {
                    throw new FillMandatoryException(WEBUI_M_HU_Transform.PARAM_M_HU_PI_Item_Product_ID);
                }
                return action_SplitCU_To_NewTUs(row, parameters.getHuPIItemProduct(), Quantity.of(parameters.getQtyCU(), row.getC_UOM()), parameters.isHuPlanningReceiptOwnerPM_TU());
            }
        case TU_Set_Ownership:
            {
                return action_updatePlanningReceiptOwnerPM(row, parameters.isHuPlanningReceiptOwnerPM_TU());
            }
        case TU_To_NewTUs:
            {
                return action_SplitTU_To_NewTUs(row, parameters.getQtyTU());
            }
        case TU_To_NewLUs:
            {
                if (parameters.getHuPIItem() == null) {
                    throw new FillMandatoryException(WEBUI_M_HU_Transform.PARAM_M_HU_PI_Item_ID);
                }
                return action_SplitTU_To_NewLU(row, parameters.getHuPIItem(), parameters.getQtyTU(), parameters.isHuPlanningReceiptOwnerPM_LU());
            }
        case LU_Set_Ownership:
            {
                return action_updatePlanningReceiptOwnerPM(row, parameters.isHuPlanningReceiptOwnerPM_LU());
            }
        case TU_To_ExistingLU:
            {
                return action_SplitTU_To_ExistingLU(row, parameters.getLuHU(), parameters.getQtyTU());
            }
        // 
        default:
            {
                throw new AdempiereException("@Unknown@ @Action@ " + action);
            }
    }
}


public WebuiHUTransformCommandResult action_SplitTU_To_ExistingLU(HUEditorRow tuRow,I_M_HU luHU,BigDecimal qtyTU){
    newHUTransformation().tuToExistingLU(tuRow.getM_HU(), qtyTU, luHU);
    final HUEditorRowId tuRowId = tuRow.getHURowId();
    return WebuiHUTransformCommandResult.builder().huIdChanged(tuRowId.getTopLevelHUId()).huIdChanged(HuId.ofRepoId(luHU.getM_HU_ID())).fullViewInvalidation(// because it might be that the TU is inside an LU of which we don't know the ID
    true).build();
}


public HUTransformService newHUTransformation(){
    return HUTransformService.builder().referencedObjects(getContextDocumentLines()).build();
}


public WebuiHUTransformCommandResult action_SplitTU_To_NewLU(HUEditorRow tuRow,I_M_HU_PI_Item huPIItem,BigDecimal qtyTU,boolean isOwnPackingMaterials){
    final List<I_M_HU> createdHUs = newHUTransformation().tuToNewLUs(tuRow.getM_HU(), qtyTU, huPIItem, isOwnPackingMaterials);
    final ImmutableSet<HuId> huIdsToAddToView = createdHUs.stream().map(I_M_HU::getM_HU_ID).map(HuId::ofRepoId).collect(ImmutableSet.toImmutableSet());
    return WebuiHUTransformCommandResult.builder().huIdsToAddToView(huIdsToAddToView).huIdChanged(tuRow.getHURowId().getTopLevelHUId()).fullViewInvalidation(// because it might be that the TU is inside an LU of which we don't know the ID
    true).build();
}


public HUEditorRow getSelectedRow(){
    return _selectedRow;
}


public List<TableRecordReference> getContextDocumentLines(){
    return _contextDocumentLines;
}


public WebuiHUTransformParameters getParameters(){
    return _parameters;
}


public WebuiHUTransformCommandResult action_SplitCU_To_NewCU(HUEditorRow cuRow,Quantity qtyCU){
    // TODO: if qtyCU is the "maximum", then don't do anything, but show a user message
    final List<I_M_HU> createdHUs = newHUTransformation().cuToNewCU(cuRow.getM_HU(), qtyCU);
    final Predicate<? super I_M_HU> // 
    newCUisDifferentFromInputHU = createdHU -> createdHU.getM_HU_ID() != cuRow.getHuId().getRepoId();
    final ImmutableSet<HuId> createdHUIds = createdHUs.stream().filter(newCUisDifferentFromInputHU).map(I_M_HU::getM_HU_ID).map(HuId::ofRepoId).collect(ImmutableSet.toImmutableSet());
    return WebuiHUTransformCommandResult.builder().huIdChanged(cuRow.getHURowId().getTopLevelHUId()).huIdsToAddToView(createdHUIds).huIdsCreated(createdHUIds).build();
}


public WebuiHUTransformCommandResult action_SplitCU_To_NewTUs(HUEditorRow cuRow,I_M_HU_PI_Item_Product tuPIItemProduct,Quantity qtyCU,boolean isOwnPackingMaterials){
    final List<I_M_HU> createdHUs = newHUTransformation().cuToNewTUs(cuRow.getM_HU(), qtyCU, tuPIItemProduct, isOwnPackingMaterials);
    final ImmutableSet<HuId> createdHUIds = createdHUs.stream().map(I_M_HU::getM_HU_ID).map(HuId::ofRepoId).collect(ImmutableSet.toImmutableSet());
    return WebuiHUTransformCommandResult.builder().huIdChanged(cuRow.getHURowId().getTopLevelHUId()).huIdsToAddToView(createdHUIds).huIdsCreated(createdHUIds).build();
}


public WebuiHUTransformCommandResult action_updatePlanningReceiptOwnerPM(HUEditorRow row,boolean huPlanningReceiptOwnerPM){
    final I_M_HU hu = row.getM_HU();
    hu.setHUPlanningReceiptOwnerPM(huPlanningReceiptOwnerPM);
    InterfaceWrapperHelper.save(hu);
    return WebuiHUTransformCommandResult.builder().huIdChanged(HuId.ofRepoId(hu.getM_HU_ID())).build();
}


}