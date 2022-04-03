package de.metas.ui.web.material.cockpit.rowfactory;
 import de.metas.quantity.Quantity.addToNullable;
import de.metas.util.Check.assumeNotNull;
import java.util.HashSet;
import java.util.Set;
import org.compiere.model.I_C_UOM;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import de.metas.material.cockpit.model.I_MD_Stock;
import de.metas.quantity.Quantity;
import de.metas.ui.web.material.cockpit.MaterialCockpitRow;
import de.metas.uom.IUOMDAO;
import de.metas.util.Services;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
@EqualsAndHashCode(of = "plantId")
@ToString
public class CountingSubRowBucket {

 private  int plantId;

 private  Quantity qtyOnHandEstimate;

 private  Quantity qtyOnHandStock;

 private  Set<Integer> cockpitRecordIds;

 private  Set<Integer> stockRecordIds;


public void addStockRecord(I_MD_Stock stockRecord){
    final IUOMDAO uomDAO = Services.get(IUOMDAO.class);
    final I_C_UOM uom = uomDAO.getById(stockRecord.getM_Product().getC_UOM_ID());
    qtyOnHandStock = addToNullable(qtyOnHandStock, stockRecord.getQtyOnHand(), uom);
    stockRecordIds.add(stockRecord.getMD_Stock_ID());
}


public CountingSubRowBucket create(int plantId){
    return new CountingSubRowBucket(plantId);
}


public void addCockpitRecord(I_MD_Cockpit cockpitRecord){
    final IUOMDAO uomDAO = Services.get(IUOMDAO.class);
    final I_C_UOM uom = uomDAO.getById(cockpitRecord.getM_Product().getC_UOM_ID());
    qtyOnHandEstimate = addToNullable(qtyOnHandEstimate, cockpitRecord.getQtyOnHandEstimate(), uom);
    cockpitRecordIds.add(cockpitRecord.getMD_Cockpit_ID());
}


public MaterialCockpitRow createIncludedRow(MainRowWithSubRows mainRowBucket){
    final MainRowBucketId productIdAndDate = assumeNotNull(mainRowBucket.getProductIdAndDate(), "productIdAndDate may not be null; mainRowBucket={}", mainRowBucket);
    return MaterialCockpitRow.countingSubRowBuilder().date(productIdAndDate.getDate()).productId(productIdAndDate.getProductId().getRepoId()).plantId(plantId).qtyOnHandEstimate(qtyOnHandEstimate).qtyOnHandStock(qtyOnHandStock).allIncludedCockpitRecordIds(cockpitRecordIds).allIncludedStockRecordIds(stockRecordIds).build();
}


}