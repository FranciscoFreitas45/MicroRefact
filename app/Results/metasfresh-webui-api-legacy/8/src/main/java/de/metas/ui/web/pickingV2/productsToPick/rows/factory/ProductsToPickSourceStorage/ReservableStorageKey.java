package de.metas.ui.web.pickingV2.productsToPick.rows.factory.ProductsToPickSourceStorage;
 import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.compiere.model.I_C_UOM;
import com.google.common.collect.Maps;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.storage.IHUProductStorage;
import de.metas.product.IProductBL;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.util.Services;
import de.metas.util.collections.CollectionUtils;
import lombok.NonNull;
import lombok.Value;
@Value(staticConstructor = "of")
public class ReservableStorageKey {

@NonNull
 private HuId huId;

@NonNull
 private ProductId productId;


}