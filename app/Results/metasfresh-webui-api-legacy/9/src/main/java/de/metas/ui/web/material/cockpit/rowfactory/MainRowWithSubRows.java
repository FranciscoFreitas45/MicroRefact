package de.metas.ui.web.material.cockpit.rowfactory;
 import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.google.common.collect.ImmutableList;
import de.metas.dimension.DimensionSpec;
import de.metas.dimension.DimensionSpecGroup;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import de.metas.material.cockpit.model.I_MD_Stock;
import de.metas.material.event.commons.AttributesKey;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.material.cockpit.MaterialCockpitRow;
import de.metas.ui.web.material.cockpit.MaterialCockpitRow.MainRowBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
@Data
@EqualsAndHashCode(of = "productIdAndDate")
public class MainRowWithSubRows {

 private  MainRowBucketId productIdAndDate;

 private  MainRowBucket mainRow;

 private  Map<DimensionSpecGroup,DimensionGroupSubRowBucket> dimensionGroupSubRows;

 private  Map<Integer,CountingSubRowBucket> countingSubRows;


public void addEmptyAttributesSubrowBucket(DimensionSpecGroup dimensionSpecGroup){
    dimensionGroupSubRows.computeIfAbsent(dimensionSpecGroup, DimensionGroupSubRowBucket::create);
}


public List<DimensionGroupSubRowBucket> findOrCreateSubRowBucket(AttributesKey dataRecordAttributesKey,DimensionSpec dimensionSpec){
    final ImmutableList.Builder<DimensionGroupSubRowBucket> result = ImmutableList.builder();
    DimensionSpecGroup otherGroup = null;
    boolean addedToAnyGroup = false;
    for (final DimensionSpecGroup group : dimensionSpec.retrieveGroups()) {
        final AttributesKey dimensionAttributesKey = group.getAttributesKey();
        if (DimensionSpecGroup.EMPTY_GROUP.equals(group) && AttributesKey.NONE.equals(dataRecordAttributesKey)) {
            result.add(dimensionGroupSubRows.computeIfAbsent(group, DimensionGroupSubRowBucket::create));
            addedToAnyGroup = true;
            continue;
        } else if (dataRecordAttributesKey.intersects(dimensionAttributesKey)) {
            result.add(dimensionGroupSubRows.computeIfAbsent(group, DimensionGroupSubRowBucket::create));
            addedToAnyGroup = true;
            continue;
        }
        // while iterating, also look out out for "otherGroup"
        if (DimensionSpecGroup.OTHER_GROUP.equals(group)) {
            otherGroup = group;
        }
    }
    if (!addedToAnyGroup && otherGroup != null) {
        result.add(dimensionGroupSubRows.computeIfAbsent(otherGroup, DimensionGroupSubRowBucket::create));
    }
    return result.build();
}


public void addCockpitRecordToCounting(I_MD_Cockpit stockEstimate){
    final CountingSubRowBucket countingSubRow = countingSubRows.computeIfAbsent(stockEstimate.getPP_Plant_ID(), CountingSubRowBucket::create);
    countingSubRow.addCockpitRecord(stockEstimate);
}


public void assertProductIdAndDateOfDataRecord(I_MD_Cockpit dataRecord){
    final MainRowBucketId key = MainRowBucketId.createInstanceForCockpitRecord(dataRecord);
    Check.errorUnless(productIdAndDate.equals(key), "The given parameter 'dataRecord' does not fit into this bucket; our productIdAndDate={}; dataRecord's productIdAndDate={}; fdataRecord={}", productIdAndDate, key, dataRecord);
}


public MaterialCockpitRow createMainRowWithSubRows(){
    final MainRowBuilder mainRowBuilder = MaterialCockpitRow.mainRowBuilder().qtyMaterialentnahme(mainRow.getQtyMaterialentnahme()).qtyRequiredForProduction(mainRow.getQtyRequiredForProduction()).qtyOnHandEstimate(mainRow.getQtyOnHandEstimate()).qtyOnHandStock(mainRow.getQtyOnHand()).qtyReservedPurchase(mainRow.getQtyReservedPurchase()).qtyAvailableToPromiseEstimate(mainRow.getQtyAvailableToPromiseEstimate()).qtyReservedSale(mainRow.getQtyReservedSale()).pmmQtyPromised(mainRow.getPmmQtyPromised()).allIncludedCockpitRecordIds(mainRow.getCockpitRecordIds()).allIncludedStockRecordIds(mainRow.getStockRecordIds());
    for (final CountingSubRowBucket subRowBucket : countingSubRows.values()) {
        final MaterialCockpitRow subRow = subRowBucket.createIncludedRow(this);
        mainRowBuilder.includedRow(subRow);
    }
    for (final DimensionGroupSubRowBucket subRowBucket : dimensionGroupSubRows.values()) {
        final MaterialCockpitRow subRow = subRowBucket.createIncludedRow(this);
        mainRowBuilder.includedRow(subRow);
    }
    return mainRowBuilder.build();
}


public void addEmptyCountingSubrowBucket(int plantId){
    countingSubRows.computeIfAbsent(plantId, CountingSubRowBucket::create);
}


public void addStockRecord(I_MD_Stock stockRecord,DimensionSpec dimensionSpec,boolean includePerPlantDetailRows){
    if (includePerPlantDetailRows) {
        addStockRecordToCounting(stockRecord);
    }
    addStockRecordToDimensionGroups(stockRecord, dimensionSpec);
    mainRow.addStockRecord(stockRecord);
}


public MainRowWithSubRows create(MainRowBucketId productIdAndDate){
    return new MainRowWithSubRows(productIdAndDate);
}


public void addCockpitRecordToDimensionGroups(I_MD_Cockpit dataRecord,DimensionSpec dimensionSpec){
    assertProductIdAndDateOfDataRecord(dataRecord);
    final AttributesKey attributesKey = AttributesKey.ofString(dataRecord.getAttributesKey());
    final List<DimensionGroupSubRowBucket> subRowBuckets = findOrCreateSubRowBucket(attributesKey, dimensionSpec);
    subRowBuckets.forEach(bucket -> bucket.addCockpitRecord(dataRecord));
}


public void addStockRecordToCounting(I_MD_Stock stockRecord){
    final int plantId = stockRecord.getM_Warehouse().getPP_Plant_ID();
    final CountingSubRowBucket countingSubRow = countingSubRows.computeIfAbsent(plantId, CountingSubRowBucket::create);
    countingSubRow.addStockRecord(stockRecord);
}


public void addStockRecordToDimensionGroups(I_MD_Stock dataRecord,DimensionSpec dimensionSpec){
    final AttributesKey attributesKey = AttributesKey.ofString(dataRecord.getAttributesKey());
    final List<DimensionGroupSubRowBucket> subRowBuckets = findOrCreateSubRowBucket(attributesKey, dimensionSpec);
    subRowBuckets.forEach(bucket -> bucket.addStockRecord(dataRecord));
}


public void addCockpitRecord(I_MD_Cockpit cockpitRecord,DimensionSpec dimensionSpec,boolean includePerPlantDetailRows){
    if (cockpitRecord.getQtyOnHandCount().signum() != 0 || cockpitRecord.getPP_Plant_ID() > 0) {
        if (includePerPlantDetailRows) {
            addCockpitRecordToCounting(cockpitRecord);
        }
    } else {
        addCockpitRecordToDimensionGroups(cockpitRecord, dimensionSpec);
    }
    mainRow.addDataRecord(cockpitRecord);
}


}