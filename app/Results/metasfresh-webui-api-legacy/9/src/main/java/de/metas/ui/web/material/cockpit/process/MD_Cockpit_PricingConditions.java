package de.metas.ui.web.material.cockpit.process;
 import java.util.Set;
import org.compiere.model.I_M_Product;
import com.google.common.collect.ImmutableSet;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.material.cockpit.MaterialCockpitRow;
import de.metas.ui.web.order.pricingconditions.view.ProductPricingConditionsViewFactory;
public class MD_Cockpit_PricingConditions extends MaterialCockpitViewBasedProcess{


public Set<Integer> getProductIds(){
    return streamSelectedRows().map(MaterialCockpitRow::getProductId).filter(productId -> productId > 0).distinct().limit(2).collect(ImmutableSet.toImmutableSet());
}


@Override
public String doIt(){
    getResult().setRecordsToOpen(I_M_Product.Table_Name, getProductIds(), ProductPricingConditionsViewFactory.WINDOW_ID_STRING);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    final Set<Integer> productIds = getProductIds();
    if (productIds.isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    return ProcessPreconditionsResolution.accept();
}


}