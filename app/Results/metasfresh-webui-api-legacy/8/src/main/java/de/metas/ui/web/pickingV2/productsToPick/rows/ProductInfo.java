package de.metas.ui.web.pickingV2.productsToPick.rows;
 import de.metas.i18n.ITranslatableString;
import de.metas.product.ProductId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class ProductInfo {

@NonNull
 private ProductId productId;

@NonNull
 private String code;

@NonNull
 private ITranslatableString name;

 private String packageSize;

 private String packageSizeUOM;


}