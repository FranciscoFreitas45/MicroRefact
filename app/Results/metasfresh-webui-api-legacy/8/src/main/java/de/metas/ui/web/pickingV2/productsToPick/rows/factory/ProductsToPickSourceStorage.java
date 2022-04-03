package de.metas.ui.web.pickingV2.productsToPick.rows.factory;
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
public class ProductsToPickSourceStorage {

 private  IHandlingUnitsBL handlingUnitsBL;

 private  IProductBL productBL;

 private  Map<HuId,I_M_HU> husCache;

 private  Map<ReservableStorageKey,ReservableStorage> storages;

@NonNull
 private HuId huId;

@NonNull
 private ProductId productId;


public Map<HuId,I_M_HU> retrieveHUs(Collection<HuId> huIds){
    return Maps.uniqueIndex(handlingUnitsBL.getByIds(huIds), hu -> HuId.ofRepoId(hu.getM_HU_ID()));
}


public I_M_HU getHU(HuId huId){
    return husCache.computeIfAbsent(huId, handlingUnitsBL::getById);
}


public ReservableStorage getStorage(HuId huId,ProductId productId){
    final ReservableStorageKey key = ReservableStorageKey.of(huId, productId);
    return storages.computeIfAbsent(key, this::retrieveStorage);
}


public void warmUpCacheForHuIds(Collection<HuId> huIds){
    CollectionUtils.getAllOrLoad(husCache, huIds, this::retrieveHUs);
}


public ReservableStorage retrieveStorage(ReservableStorageKey key){
    final ProductId productId = key.getProductId();
    final I_M_HU hu = getHU(key.getHuId());
    final IHUProductStorage huProductStorage = handlingUnitsBL.getStorageFactory().getStorage(hu).getProductStorageOrNull(productId);
    if (huProductStorage == null) {
        final I_C_UOM uom = productBL.getStockUOM(productId);
        return new ReservableStorage(productId, Quantity.zero(uom));
    } else {
        final Quantity qtyFreeToReserve = huProductStorage.getQty();
        return new ReservableStorage(productId, qtyFreeToReserve);
    }
}


}