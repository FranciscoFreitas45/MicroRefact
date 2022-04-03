package de.metas.ui.web.handlingunits.process;
 import org.adempiere.model.InterfaceWrapperHelper.load;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorRowFilter;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.uom.IUOMDAO;
import de.metas.util.Services;
public class WEBUI_Add_Batch_SerialNo_To_CUs extends HUEditorProcessTemplateimplements IProcessPrecondition{

@Autowired
 private  DocumentCollection documentsCollection;

 private  IHandlingUnitsBL handlingUnitsBL;

 private  String SERIALNO_SEPARATOR;

 private  String PARAM_SerialNo;

@Param(parameterName = PARAM_SerialNo, mandatory = true)
 private  String p_SerialNo;

 private Set<Integer> changedHUs;

 private ArrayList<String> _serialNumbers;


public void updateViewFromResult(WebuiHUTransformCommandResult result){
    final HUEditorView view = getView();
    view.addHUIds(result.getHuIdsToAddToView());
    view.removeHUIds(result.getHuIdsToRemoveFromView());
    if (!result.getHuIdsChanged().isEmpty()) {
        removeHUsIfDestroyed(result.getHuIdsChanged());
    }
}


@Override
public int compare(HUEditorRow o1,HUEditorRow o2){
    if (!isAggregateHU(o1)) {
        return -1;
    }
    if (!isAggregateHU(o2)) {
        return 1;
    }
    return 0;
}


public boolean isEligibleHuRow(HUEditorRow huRow){
    if (!huRow.isCU()) {
        return false;
    }
    if (huRow.getC_UOM_ID() != IUOMDAO.C_UOM_ID_Each) {
        return false;
    }
    return huRow.isHUStatusPlanning() || huRow.isHUStatusActive();
}


public ArrayList<String> parseSerialNumbers(){
    final String serialNumbersNorm = p_SerialNo.replace("\n", SERIALNO_SEPARATOR).replace(";", SERIALNO_SEPARATOR);
    final List<String> serialNosList = Splitter.on(SERIALNO_SEPARATOR).trimResults().omitEmptyStrings().splitToList(serialNumbersNorm);
    return new ArrayList<>(serialNosList);
}


public ImmutableList<HUEditorRow> orderSelectedCUs(){
    return streamSelectedRows(HUEditorRowFilter.select(Select.ALL)).sorted(new Comparator<HUEditorRow>() {

        @Override
        public int compare(final HUEditorRow o1, final HUEditorRow o2) {
            if (!isAggregateHU(o1)) {
                return -1;
            }
            if (!isAggregateHU(o2)) {
                return 1;
            }
            return 0;
        }
    }).collect(ImmutableList.toImmutableList());
}


public boolean removeSelectedRowsIfHUDestoyed(){
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    if (selectedRowIds.isEmpty()) {
        return false;
    } else if (selectedRowIds.isAll()) {
        return false;
    }
    final HUEditorView view = getView();
    final ImmutableSet<HuId> selectedHUIds = view.streamByIds(selectedRowIds).map(HUEditorRow::getHuId).filter(Objects::nonNull).collect(ImmutableSet.toImmutableSet());
    return removeHUsIfDestroyed(selectedHUIds);
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!isHUEditorView()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not the HU view");
    }
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    if (selectedRowIds.isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    if (!streamSelectedRows(HUEditorRowFilter.select(Select.ALL)).allMatch(huRow -> isEligibleHuRow(huRow))) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(WEBUI_HU_Constants.MSG_WEBUI_ONLY_CU));
    }
    return ProcessPreconditionsResolution.accept();
}


public void processCURow(HUEditorRow selectedCuRow){
    final ArrayList<String> availableSerialNumbers = getSerialNumbers();
    if (availableSerialNumbers.isEmpty()) {
        return;
    }
    final HUEditorRow parentRow = getParentHURowOrNull(selectedCuRow);
    final HUEditorRow topLevelRow = parentRow == null ? null : getParentHURowOrNull(parentRow);
    final HUEditorRow.HUEditorRowHierarchy huEditorRowHierarchy = HUEditorRow.HUEditorRowHierarchy.builder().cuRow(selectedCuRow).parentRow(parentRow).topLevelRow(topLevelRow).build();
    final WebuiHUTransformCommandResult result = WEBUIHUCreationWithSerialNumberService.builder().documentCollections(documentsCollection).view(getView()).build().action_CreateCUs_With_SerialNumbers(huEditorRowHierarchy, availableSerialNumbers);
    updateViewFromResult(result);
}


public ArrayList<String> getSerialNumbers(){
    if (_serialNumbers == null) {
        _serialNumbers = parseSerialNumbers();
    }
    return _serialNumbers;
}


public HUEditorRow getParentHURowOrNull(HUEditorRow cuRow){
    if (cuRow == null) {
        return null;
    }
    return getView().getParentRowByChildIdOrNull(cuRow.getId());
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
public String doIt(){
    orderSelectedCUs().forEach(this::processCURow);
    return MSG_OK;
}


@Override
public void postProcess(boolean success){
    if (!success) {
        return;
    }
    removeSelectedRowsIfHUDestoyed();
    invalidateView();
}


public boolean isAggregateHU(HUEditorRow huRow){
    final I_M_HU hu = huRow.getM_HU();
    return handlingUnitsBL.isAggregateHU(hu);
}


}