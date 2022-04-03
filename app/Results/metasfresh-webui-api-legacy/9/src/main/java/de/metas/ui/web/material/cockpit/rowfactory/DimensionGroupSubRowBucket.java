package de.metas.ui.web.material.cockpit.rowfactory;
 import de.metas.quantity.Quantity.addToNullable;
import de.metas.util.Check.assumeNotNull;
import java.util.HashSet;
import java.util.Set;
import org.compiere.model.I_C_UOM;
import de.metas.dimension.DimensionSpecGroup;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import de.metas.material.cockpit.model.I_MD_Stock;
import de.metas.quantity.Quantity;
import de.metas.ui.web.material.cockpit.MaterialCockpitRow;
import de.metas.uom.IUOMDAO;
import de.metas.util.Services;
import lombok.Data;
import lombok.NonNull;
@Data
public class DimensionGroupSubRowBucket {

 private  DimensionSpecGroup dimensionSpecGroup;

 private  Quantity pmmQtyPromised;

 private  Quantity qtyReservedSale;

 private  Quantity qtyReservedPurchase;

 private  Quantity qtyMaterialentnahme;

 private  Quantity qtyRequiredForProduction;

 private  Quantity qtyAvailableToPromiseEstimate;

 private  Quantity qtyOnHandStock;

 private  Set<Integer> cockpitRecordIds;

 private  Set<Integer> stockRecordIds;


public void addStockRecord(I_MD_Stock stockRecord){
    final IUOMDAO uomDAO = Services.get(IUOMDAO.class);
    final I_C_UOM uom = uomDAO.getById(stockRecord.getM_Product().getC_UOM_ID());
    qtyOnHandStock = addToNullable(qtyOnHandStock, stockRecord.getQtyOnHand(), uom);
    stockRecordIds.add(stockRecord.getMD_Stock_ID());
}


public DimensionGroupSubRowBucket create(DimensionSpecGroup dimensionSpecGroup){
    return new DimensionGroupSubRowBucket(dimensionSpecGroup);
}


public void addCockpitRecord(I_MD_Cockpit cockpitRecord){
    final IUOMDAO uomDAO = Services.get(IUOMDAO.class);
    final I_C_UOM uom = uomDAO.getById(cockpitRecord.getM_Product().getC_UOM_ID());
    pmmQtyPromised = addToNullable(pmmQtyPromised, cockpitRecord.getPMM_QtyPromised_OnDate(), uom);
    qtyMaterialentnahme = addToNullable(qtyMaterialentnahme, cockpitRecord.getQtyMaterialentnahme(), uom);
    qtyRequiredForProduction = addToNullable(qtyRequiredForProduction, cockpitRecord.getQtyRequiredForProduction(), uom);
    qtyReservedPurchase = addToNullable(qtyReservedPurchase, cockpitRecord.getQtyReserved_Purchase(), uom);
    qtyReservedSale = addToNullable(qtyReservedSale, cockpitRecord.getQtyReserved_Sale(), uom);
    qtyAvailableToPromiseEstimate = addToNullable(qtyAvailableToPromiseEstimate, cockpitRecord.getQtyAvailableToPromiseEstimate(), uom);
    cockpitRecordIds.add(cockpitRecord.getMD_Cockpit_ID());
}


public MaterialCockpitRow createIncludedRow(MainRowWithSubRows mainRowBucket){
    final MainRowBucketId productIdAndDate = assumeNotNull(mainRowBucket.getProductIdAndDate(), "productIdAndDate may not be null; mainRowBucket={}", mainRowBucket);
    return MaterialCockpitRow.attributeSubRowBuilder().date(productIdAndDate.getDate()).productId(productIdAndDate.getProductId().getRepoId()).dimensionGroup(dimensionSpecGroup).pmmQtyPromised(getPmmQtyPromised()).qtyMaterialentnahme(getQtyMaterialentnahme()).qtyRequiredForProduction(getQtyRequiredForProduction()).qtyReservedPurchase(getQtyReservedPurchase()).qtyReservedSale(getQtyReservedSale()).qtyOnHandStock(getQtyOnHandStock()).allIncludedCockpitRecordIds(cockpitRecordIds).allIncludedStockRecordIds(stockRecordIds).build();
}


}