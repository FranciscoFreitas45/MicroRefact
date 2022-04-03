package de.metas.ui.web.handlingunits.process;
 import org.adempiere.model.InterfaceWrapperHelper.create;
import org.adempiere.model.InterfaceWrapperHelper.getContextAware;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.mm.attributes.api.AttributeConstants;
import org.adempiere.util.lang.IContextAware;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.util.Env;
import org.compiere.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHUContext;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.allocation.transfer.HUTransformService;
import de.metas.handlingunits.attribute.storage.IAttributeStorage;
import de.metas.handlingunits.attribute.storage.IAttributeStorageFactory;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_PI_Item;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.printing.esb.base.util.Check;
import de.metas.quantity.Quantity;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
public class WEBUIHUCreationWithSerialNumberService {

 private  DocumentCollection documentCollections;

 private  IHandlingUnitsBL handlingUnitsBL;

 private  IHandlingUnitsDAO handlingUnitsRepo;

 private  HUEditorView view;

 private  Set<HuId> huIDsChanged;

 private  Set<HuId> huIDsAdded;

 private  Set<HuId> huIDsToRemove;


public HUTransformService newHUTransformation(){
    return HUTransformService.builder().referencedObjects(getContextDocumentLines()).build();
}


public Set<HuId> splitIntoCUs(HUEditorRow.HUEditorRowHierarchy huEditorRowHierarchy,int cuNumber){
    if (cuNumber == 0) {
        return ImmutableSet.of();
    }
    final Set<HuId> splitCUs = new HashSet<>();
    while (cuNumber > splitCUs.size()) {
        final int maxCUsAllowedPerBatch = cuNumber - splitCUs.size();
        splitCUs.addAll(createCUsBatch(huEditorRowHierarchy, maxCUsAllowedPerBatch));
    }
    return splitCUs;
}


public int calculateTUCapacity(I_M_HU parentHU){
    final I_M_HU_PI_Item_Product huPIP = IHandlingUnitsBL.extractPIItemProductOrNull(parentHU);
    if (huPIP == null) {
        // TODO
        throw new UnsupportedOperationException();
    }
    return huPIP.getQty().intValueExact();
}


public I_M_HU createNonAggregatedTU(HUEditorRow tuRow,HUEditorRow luRow){
    final I_M_HU newTU = newHUTransformation().tuToNewTUs(tuRow.getM_HU(), BigDecimal.ONE).get(0);
    if (luRow != null) {
        final I_M_HU oldLU = luRow.getM_HU();
        if (handlingUnitsBL.isDestroyed(oldLU)) {
            final I_M_HU_PI_Item luPIItem = oldLU.getM_HU_LUTU_Configuration().getM_LU_HU_PI_Item();
            final List<I_M_HU> tuToNewLUs = newHUTransformation().tuToNewLUs(newTU, BigDecimal.ONE, luPIItem, false);
            huIDsToRemove.add(HuId.ofRepoId(oldLU.getM_HU_ID()));
            huIDsAdded.add(HuId.ofRepoId(tuToNewLUs.get(0).getM_HU_ID()));
        } else {
            moveTUToLU(newTU.getM_HU_ID(), luRow, tuRow);
        }
    }
    return newTU;
}


public List<TableRecordReference> getContextDocumentLines(){
    if (view == null) {
        return ImmutableList.of();
    }
    return view.getReferencingDocumentPaths().stream().map(referencingDocumentPath -> documentCollections.getTableRecordReference(referencingDocumentPath)).collect(GuavaCollectors.toImmutableList());
}


public Set<HuId> createCUsBatch(HUEditorRow.HUEditorRowHierarchy huEditorRowHierarchy,int maxCUsAllowedPerBatch){
    final Set<HuId> splitCUIDs = new HashSet<>();
    final IHandlingUnitsDAO handlingUnitsDAO = Services.get(IHandlingUnitsDAO.class);
    final HUEditorRow cuRow = huEditorRowHierarchy.getCuRow();
    final HUEditorRow parentRow = huEditorRowHierarchy.getParentRow();
    final int initialQtyCU = cuRow.getQtyCU().intValueExact();
    I_M_HU huToSplit = cuRow.getM_HU();
    int numberOfCUsToCreate;
    if (parentRow == null) {
        // The CU will not be split when its qty gets to 1. So make sure the selected CU gets in the list of split CUs
        final int selectedCUID = huToSplit.getM_HU_ID();
        splitCUIDs.add(HuId.ofRepoId(selectedCUID));
        numberOfCUsToCreate = maxCUsAllowedPerBatch < initialQtyCU ? maxCUsAllowedPerBatch : initialQtyCU;
        for (int i = 0; i < numberOfCUsToCreate; i++) {
            final List<I_M_HU> createdCUs = newHUTransformation().cuToNewCU(huToSplit, Quantity.of(BigDecimal.ONE, cuRow.getC_UOM()));
            final Predicate<? super I_M_HU> // 
            newCUisDifferentFromInputHU = createdHU -> createdHU.getM_HU_ID() != cuRow.getHuId().getRepoId();
            splitCUIDs.addAll(createdCUs.stream().filter(newCUisDifferentFromInputHU).map(I_M_HU::getM_HU_ID).map(HuId::ofRepoId).collect(ImmutableSet.toImmutableSet()));
        }
    } else {
        Check.assume(parentRow.isTU(), " Parent row must be TU: " + parentRow);
        I_M_HU parentHU = parentRow.getM_HU();
        if (isAggregateHU(parentRow)) {
            final HUEditorRow luRow = huEditorRowHierarchy.getTopLevelRow();
            parentHU = createNonAggregatedTU(parentRow, luRow);
            huToSplit = handlingUnitsDAO.retrieveIncludedHUs(parentHU).get(0);
        }
        final int tuCapacity = calculateTUCapacity(parentHU);
        numberOfCUsToCreate = Util.getMinimumOfThree(tuCapacity, maxCUsAllowedPerBatch, initialQtyCU);
        for (int i = 0; i < numberOfCUsToCreate; i++) {
            final List<I_M_HU> createdCUs = newHUTransformation().cuToExistingTU(huToSplit, Quantity.of(BigDecimal.ONE, cuRow.getC_UOM()), parentHU);
            splitCUIDs.addAll(createdCUs.stream().map(I_M_HU::getM_HU_ID).map(HuId::ofRepoId).collect(ImmutableSet.toImmutableSet()));
        }
    }
    return splitCUIDs;
}


public void moveTUToLU(int tuId,HUEditorRow luRow,HUEditorRow tuRow){
    final I_M_HU newTU = create(Env.getCtx(), tuId, I_M_HU.class, ITrx.TRXNAME_ThreadInherited);
    newHUTransformation().tuToExistingLU(newTU, BigDecimal.ONE, luRow.getM_HU());
}


public IAttributeStorage getAttributeStorage(IHUContext huContext,I_M_HU hu){
    final IAttributeStorageFactory attributeStorageFactory = huContext.getHUAttributeStorageFactory();
    final IAttributeStorage attributeStorage = attributeStorageFactory.getAttributeStorage(hu);
    return attributeStorage;
}


public void assignSerialNumberToCU(HuId huId,String serialNo){
    final I_M_HU hu = handlingUnitsRepo.getById(huId);
    final IContextAware ctxAware = getContextAware(hu);
    final IHUContext huContext = handlingUnitsBL.createMutableHUContext(ctxAware);
    final IAttributeStorage attributeStorage = getAttributeStorage(huContext, hu);
    Check.errorUnless(attributeStorage.hasAttribute(AttributeConstants.ATTR_SerialNo), "There is no SerialNo attribute {} defined for the handling unit {}", AttributeConstants.ATTR_SerialNo, hu);
    attributeStorage.setValue(AttributeConstants.ATTR_SerialNo, serialNo.trim());
    attributeStorage.saveChangesIfNeeded();
}


public WebuiHUTransformCommandResult action_CreateCUs_With_SerialNumbers(HUEditorRow.HUEditorRowHierarchy huEditorRowHierarchy,List<String> availableSerialNumbers){
    final HUEditorRow selectedCuRow = huEditorRowHierarchy.getCuRow();
    final int qtyCU = selectedCuRow.getQtyCU().intValueExact();
    if (qtyCU == 1) {
        final String serialNo = availableSerialNumbers.remove(0);
        assignSerialNumberToCU(selectedCuRow.getHuId(), serialNo);
        huIDsChanged.add(selectedCuRow.getHuId());
    } else {
        final int serialNoCount = availableSerialNumbers.size();
        final int cusToCreateCount = qtyCU < serialNoCount ? qtyCU : serialNoCount;
        final Set<HuId> splittedCUIDs = splitIntoCUs(huEditorRowHierarchy, cusToCreateCount);
        assignSerialNumbersToCUs(splittedCUIDs, availableSerialNumbers);
        huIDsAdded.addAll(splittedCUIDs);
    }
    return WebuiHUTransformCommandResult.builder().huIdsChanged(huIDsChanged).huIdsToRemoveFromView(huIDsToRemove).huIdsToAddToView(huIDsAdded).build();
}


public void assignSerialNumbersToCUs(Set<HuId> cuIDs,List<String> availableSerialNumbers){
    final List<HuId> listOfCUIDs = ImmutableList.copyOf(cuIDs);
    final int numberOfCUs = listOfCUIDs.size();
    for (int i = 0; i < numberOfCUs; i++) {
        if (availableSerialNumbers.isEmpty()) {
            return;
        }
        assignSerialNumberToCU(listOfCUIDs.get(i), availableSerialNumbers.remove(0));
    }
}


public boolean isAggregateHU(HUEditorRow huRow){
    final I_M_HU hu = huRow.getM_HU();
    return handlingUnitsBL.isAggregateHU(hu);
}


}