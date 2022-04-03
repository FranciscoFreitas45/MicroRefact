package de.metas.ui.web.material.cockpit.rowfactory;
 import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.adempiere.ad.dao.IQueryBL;
import org.compiere.model.I_S_Resource;
import org.compiere.model.X_S_Resource;
import org.springframework.stereotype.Service;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.ImmutableSet;
import de.metas.dimension.DimensionSpec;
import de.metas.dimension.DimensionSpecGroup;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import de.metas.material.cockpit.model.I_MD_Stock;
import de.metas.product.ProductId;
import de.metas.ui.web.material.cockpit.MaterialCockpitRow;
import de.metas.ui.web.material.cockpit.MaterialCockpitUtil;
import de.metas.util.Services;
import lombok.NonNull;
import lombok.Value;
@Service
public class MaterialCockpitRowFactory {

@NonNull
 private LocalDate date;

@NonNull
 private ImmutableSet<ProductId> productIdsToListEvenIfEmpty;

@NonNull
 private List<I_MD_Cockpit> cockpitRecords;

@NonNull
 private List<I_MD_Stock> stockRecords;

 private boolean includePerPlantDetailRows;


public void addCockpitRowsToResult(CreateRowsRequest request,DimensionSpec dimensionSpec,Map<MainRowBucketId,MainRowWithSubRows> result){
    for (final I_MD_Cockpit cockpitRecord : request.getCockpitRecords()) {
        final MainRowBucketId mainRowBucketId = MainRowBucketId.createInstanceForCockpitRecord(cockpitRecord);
        final MainRowWithSubRows mainRowBucket = result.computeIfAbsent(mainRowBucketId, key -> MainRowWithSubRows.create(key));
        mainRowBucket.addCockpitRecord(cockpitRecord, dimensionSpec, request.isIncludePerPlantDetailRows());
    }
}


public List<I_S_Resource> retrieveCountingPlants(boolean includePerPlantDetailRows){
    if (!includePerPlantDetailRows) {
        return ImmutableList.of();
    }
    return Services.get(IQueryBL.class).createQueryBuilder(I_S_Resource.class).addOnlyActiveRecordsFilter().addEqualsFilter(I_S_Resource.COLUMNNAME_ManufacturingResourceType, X_S_Resource.MANUFACTURINGRESOURCETYPE_Plant).create().list();
}


public void addStockRowsToResult(CreateRowsRequest request,DimensionSpec dimensionSpec,Map<MainRowBucketId,MainRowWithSubRows> result){
    for (final I_MD_Stock stockRecord : request.getStockRecords()) {
        final MainRowBucketId mainRowBucketId = MainRowBucketId.createInstanceForStockRecord(stockRecord, request.getDate());
        final MainRowWithSubRows mainRowBucket = result.computeIfAbsent(mainRowBucketId, key -> MainRowWithSubRows.create(key));
        mainRowBucket.addStockRecord(stockRecord, dimensionSpec, request.isIncludePerPlantDetailRows());
    }
}


public List<MaterialCockpitRow> createRows(CreateRowsRequest request){
    final Map<MainRowBucketId, MainRowWithSubRows> emptyRowBuckets = createEmptyRowBuckets(request.getProductIdsToListEvenIfEmpty(), request.getDate(), request.isIncludePerPlantDetailRows());
    final DimensionSpec dimensionSpec = MaterialCockpitUtil.retrieveDimensionSpec();
    final Map<MainRowBucketId, MainRowWithSubRows> result = new HashMap<>(emptyRowBuckets);
    addCockpitRowsToResult(request, dimensionSpec, result);
    addStockRowsToResult(request, dimensionSpec, result);
    return result.values().stream().map(MainRowWithSubRows::createMainRowWithSubRows).collect(ImmutableList.toImmutableList());
}


@VisibleForTesting
public Map<MainRowBucketId,MainRowWithSubRows> createEmptyRowBuckets(ImmutableSet<ProductId> productIds,LocalDate timestamp,boolean includePerPlantDetailRows){
    final DimensionSpec dimensionSpec = MaterialCockpitUtil.retrieveDimensionSpec();
    final List<DimensionSpecGroup> groups = dimensionSpec.retrieveGroups();
    final List<I_S_Resource> plants = retrieveCountingPlants(includePerPlantDetailRows);
    final Builder<MainRowBucketId, MainRowWithSubRows> result = ImmutableMap.builder();
    for (final ProductId productId : productIds) {
        final MainRowBucketId key = MainRowBucketId.createPlainInstance(productId, timestamp);
        final MainRowWithSubRows mainRowBucket = MainRowWithSubRows.create(key);
        for (final I_S_Resource plant : plants) {
            mainRowBucket.addEmptyCountingSubrowBucket(plant.getS_Resource_ID());
        }
        for (final DimensionSpecGroup group : groups) {
            mainRowBucket.addEmptyAttributesSubrowBucket(group);
        }
        result.put(key, mainRowBucket);
    }
    return result.build();
}


}