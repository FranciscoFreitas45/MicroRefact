package de.metas.ui.web.pickingV2.productsToPick.rows.factory;
 import java.util.HashMap;
import java.util.Map;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Product;
import de.metas.i18n.ITranslatableString;
import de.metas.product.IProductDAO;
import de.metas.product.ProductId;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductInfo;
import de.metas.uom.IUOMDAO;
import de.metas.uom.UomId;
import lombok.Builder;
import lombok.NonNull;
public class ProductInfoSupplier {

 private  IProductDAO productsRepo;

 private  IUOMDAO uomsRepo;

 private  Map<ProductId,ProductInfo> productInfos;


public ProductInfo retrieveProductInfo(ProductId productId){
    final I_M_Product productRecord = productsRepo.getById(productId);
    final UomId packageUOMId = UomId.ofRepoIdOrNull(productRecord.getPackage_UOM_ID());
    final String packageSizeUOM;
    if (packageUOMId != null) {
        final I_C_UOM packageUOM = uomsRepo.getById(packageUOMId);
        packageSizeUOM = packageUOM.getUOMSymbol();
    } else {
        packageSizeUOM = null;
    }
    final ITranslatableString productName = InterfaceWrapperHelper.getModelTranslationMap(productRecord).getColumnTrl(I_M_Product.COLUMNNAME_Name, productRecord.getName());
    return ProductInfo.builder().productId(productId).code(productRecord.getValue()).name(productName).packageSize(productRecord.getPackageSize()).packageSizeUOM(packageSizeUOM).build();
}


public ProductInfo getByProductId(ProductId productId){
    return productInfos.computeIfAbsent(productId, this::retrieveProductInfo);
}


}