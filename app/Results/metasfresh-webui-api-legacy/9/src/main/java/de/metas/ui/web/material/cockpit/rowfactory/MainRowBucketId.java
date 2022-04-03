package de.metas.ui.web.material.cockpit.rowfactory;
 import java.math.BigDecimal;
import java.time.LocalDate;
import org.compiere.util.TimeUtil;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import de.metas.material.cockpit.model.I_MD_Stock;
import de.metas.product.ProductId;
import lombok.NonNull;
import lombok.Value;
@Value
public class MainRowBucketId {

 private ProductId productId;

 private LocalDate date;

 private  BigDecimal pmmQtyPromised;

 private  BigDecimal qtyReserved;

 private  BigDecimal qtyOrdered;

 private  BigDecimal qtyMaterialentnahme;

 private  BigDecimal qtyMrp;

 private  BigDecimal qtyPromised;

 private  BigDecimal qtyOnHand;


public MainRowBucketId createInstanceForCockpitRecord(I_MD_Cockpit dataRecord){
    return new MainRowBucketId(ProductId.ofRepoId(dataRecord.getM_Product_ID()), TimeUtil.asLocalDate(dataRecord.getDateGeneral()));
}


public MainRowBucketId createPlainInstance(ProductId productId,LocalDate date){
    return new MainRowBucketId(productId, date);
}


public MainRowBucketId createInstanceForStockRecord(I_MD_Stock stockRecord,LocalDate date){
    return new MainRowBucketId(ProductId.ofRepoId(stockRecord.getM_Product_ID()), date);
}


}