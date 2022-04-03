package de.metas.ui.web.material.cockpit.rowfactory;
 import de.metas.quantity.Quantity.addToNullable;
import java.util.HashSet;
import java.util.Set;
import org.compiere.model.I_C_UOM;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import de.metas.material.cockpit.model.I_MD_Stock;
import de.metas.quantity.Quantity;
import de.metas.uom.IUOMDAO;
import de.metas.util.Services;
import lombok.Getter;
import lombok.NonNull;
@Getter
public class MainRowBucket {

 private  Quantity qtyOnHandEstimate;

 private  Quantity qtyOnHand;

 private  Quantity pmmQtyPromised;

 private  Quantity qtyReservedSale;

 private  Quantity qtyReservedPurchase;

 private  Quantity qtyMaterialentnahme;

 private  Quantity qtyRequiredForProduction;

 private  Quantity qtyAvailableToPromiseEstimate;

 private  Set<Integer> cockpitRecordIds;

 private  Set<Integer> stockRecordIds;


public void addStockRecord(I_MD_Stock stockRecord){
    final IUOMDAO uomDAO = Services.get(IUOMDAO.class);
    final I_C_UOM uom = uomDAO.getById(stockRecord.getM_Product().getC_UOM_ID());
    qtyOnHand = addToNullable(qtyOnHand, stockRecord.getQtyOnHand(), uom);
    stockRecordIds.add(stockRecord.getMD_Stock_ID());
}


public void addDataRecord(I_MD_Cockpit cockpitRecord){
    final IUOMDAO uomDAO = Services.get(IUOMDAO.class);
    final I_C_UOM uom = uomDAO.getById(cockpitRecord.getM_Product().getC_UOM_ID());
    pmmQtyPromised = addToNullable(pmmQtyPromised, cockpitRecord.getPMM_QtyPromised_OnDate(), uom);
    qtyMaterialentnahme = addToNullable(qtyMaterialentnahme, cockpitRecord.getQtyMaterialentnahme(), uom);
    qtyRequiredForProduction = addToNullable(qtyRequiredForProduction, cockpitRecord.getQtyRequiredForProduction(), uom);
    qtyReservedPurchase = addToNullable(qtyReservedPurchase, cockpitRecord.getQtyReserved_Purchase(), uom);
    qtyReservedSale = addToNullable(qtyReservedSale, cockpitRecord.getQtyReserved_Sale(), uom);
    qtyAvailableToPromiseEstimate = addToNullable(qtyAvailableToPromiseEstimate, cockpitRecord.getQtyAvailableToPromiseEstimate(), uom);
    qtyOnHandEstimate = addToNullable(qtyOnHandEstimate, cockpitRecord.getQtyOnHandEstimate(), uom);
    cockpitRecordIds.add(cockpitRecord.getMD_Cockpit_ID());
}


}