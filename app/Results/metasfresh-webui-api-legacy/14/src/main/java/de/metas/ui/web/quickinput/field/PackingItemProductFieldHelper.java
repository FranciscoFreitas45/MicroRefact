package de.metas.ui.web.quickinput.field;
 import de.metas.handlingunits.HUPIItemProductId;
import de.metas.handlingunits.IHUPIItemProductDAO;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.handlingunits.model.I_M_ProductPrice;
import de.metas.pricing.PriceListId;
import de.metas.pricing.service.IPriceListDAO;
import de.metas.pricing.service.ProductPrices;
import de.metas.util.Services;
import lombok.NonNull;
import org.compiere.model.I_M_PriceList_Version;
import org.springframework.stereotype.Component;
import java.util.Optional;
@Component
public class PackingItemProductFieldHelper {

 private  IHUPIItemProductDAO huPIItemProductsRepo;

 private  IPriceListDAO priceListsRepo;


public Optional<I_M_HU_PI_Item_Product> getDefaultPackingMaterial(DefaultPackingItemCriteria defaultPackingItemCriteria){
    // try to load the packing item defined at price list level
    final Optional<I_M_HU_PI_Item_Product> defaultPIProduct = getDefaultPackingMaterialFromPriceList(defaultPackingItemCriteria);
    if (defaultPIProduct.isPresent()) {
        return defaultPIProduct;
    }
    // if not found, check the default packing item for product
    return huPIItemProductsRepo.retrieveDefaultForProduct(defaultPackingItemCriteria.getProductId(), defaultPackingItemCriteria.getBPartnerLocationId().getBpartnerId(), defaultPackingItemCriteria.getDate());
}


public PriceListId getPriceListIdFor(DefaultPackingItemCriteria defaultPackingItemCriteria){
    return priceListsRepo.retrievePriceListIdByPricingSyst(defaultPackingItemCriteria.getPricingSystemId(), defaultPackingItemCriteria.getBPartnerLocationId(), defaultPackingItemCriteria.getSoTrx());
}


public Optional<I_M_HU_PI_Item_Product> getDefaultPackingMaterialFromPriceList(DefaultPackingItemCriteria defaultPackingItemCriteria){
    final PriceListId priceListId = Optional.ofNullable(defaultPackingItemCriteria.getPriceListId()).orElseGet(() -> getPriceListIdFor(defaultPackingItemCriteria));
    if (priceListId == null) {
        return Optional.empty();
    }
    final I_M_PriceList_Version priceListVersion = priceListsRepo.retrievePriceListVersionOrNull(priceListId, defaultPackingItemCriteria.getDate(), null);
    if (priceListVersion == null) {
        return Optional.empty();
    }
    return ProductPrices.newQuery(priceListVersion).setProductId(defaultPackingItemCriteria.getProductId()).list(I_M_ProductPrice.class).stream().map(productPrice -> HUPIItemProductId.ofRepoIdOrNone(productPrice.getM_HU_PI_Item_Product_ID())).filter(id -> HUPIItemProductId.isRegular(id)).findFirst().map(huPIItemProductsRepo::getById);
}


}