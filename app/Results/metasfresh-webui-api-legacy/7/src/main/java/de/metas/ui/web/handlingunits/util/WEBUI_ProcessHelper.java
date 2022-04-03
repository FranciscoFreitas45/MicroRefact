package de.metas.ui.web.handlingunits.util;
 import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import javax.annotation.Nullable;
import org.adempiere.service.ISysConfigBL;
import org.compiere.util.Env;
import de.metas.bpartner.BPartnerId;
import de.metas.handlingunits.IHUPIItemProductDAO;
import de.metas.handlingunits.model.I_M_HU_PI_Item;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.product.ProductId;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.util.Services;
import de.metas.util.StringUtils;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
@UtilityClass
public class WEBUI_ProcessHelper {

 private  String SYSCONFIG_ALLOW_INFINIT_CAPACITY_TUS;


public String buildHUPIItemString(I_M_HU_PI_Item huPIItem){
    final String result = StringUtils.formatMessage("{} ({} x {})", huPIItem.getM_HU_PI_Version().getName(), // it's always integer quantities
    huPIItem.getQty().setScale(0, RoundingMode.HALF_UP), huPIItem.getIncluded_HU_PI().getName());
    return result;
}


public LookupValuesList retrieveHUPIItemProducts(Properties ctx,ProductId productId,BPartnerId bpartnerId,boolean includeVirtualItem){
    final List<I_M_HU_PI_Item_Product> list = retrieveHUPIItemProductRecords(ctx, productId, bpartnerId, includeVirtualItem);
    return list.stream().sorted(Comparator.comparing(I_M_HU_PI_Item_Product::getName)).map(huPIItemProduct -> IntegerLookupValue.of(huPIItemProduct.getM_HU_PI_Item_Product_ID(), huPIItemProduct.getName())).collect(LookupValuesList.collect());
}


public List<I_M_HU_PI_Item_Product> retrieveHUPIItemProductRecords(Properties ctx,ProductId productId,BPartnerId bpartnerId,boolean includeVirtualItem){
    final IHUPIItemProductDAO hupiItemProductDAO = Services.get(IHUPIItemProductDAO.class);
    final ISysConfigBL sysConfigBL = Services.get(ISysConfigBL.class);
    final boolean allowInfiniteCapacity = sysConfigBL.getBooleanValue(SYSCONFIG_ALLOW_INFINIT_CAPACITY_TUS, true, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));
    final List<I_M_HU_PI_Item_Product> list = hupiItemProductDAO.retrieveTUs(ctx, productId, bpartnerId, allowInfiniteCapacity);
    if (includeVirtualItem) {
        list.add(hupiItemProductDAO.retrieveVirtualPIMaterialItemProduct(ctx));
    }
    return list;
}


}