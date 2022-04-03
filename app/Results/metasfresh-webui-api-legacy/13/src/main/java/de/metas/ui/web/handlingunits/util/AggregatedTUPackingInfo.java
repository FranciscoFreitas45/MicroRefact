package de.metas.ui.web.handlingunits.util;
 import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.compiere.model.I_C_UOM;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.exceptions.HUException;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_Item;
import de.metas.handlingunits.model.I_M_HU_PI;
import de.metas.handlingunits.storage.IHUProductStorage;
import de.metas.logging.LogManager;
import de.metas.util.Services;
public class AggregatedTUPackingInfo implements IHUPackingInfo{

 private  Logger logger;

 private  I_M_HU aggregatedTU;

 private  Supplier<IHUProductStorage> huProductStorageSupplier;


@Override
public BigDecimal getQtyCUsPerTU(){
    final IHUProductStorage huProductStorage = getHUProductStorage();
    if (huProductStorage == null) {
        return null;
    }
    final BigDecimal qtyTUsPerLU = getQtyTUsPerLU();
    if (qtyTUsPerLU == null || qtyTUsPerLU.signum() == 0) {
        return null;
    }
    final BigDecimal qtyCUTotal = huProductStorage.getQty().toBigDecimal();
    final BigDecimal qtyCUsPerTU = qtyCUTotal.divide(qtyTUsPerLU, 0, RoundingMode.HALF_UP);
    return qtyCUsPerTU;
}


@Override
public I_C_UOM getQtyCUsPerTU_UOM(){
    final IHUProductStorage huProductStorage = getHUProductStorage();
    if (huProductStorage == null) {
        return null;
    }
    return huProductStorage.getC_UOM();
}


@Override
public BigDecimal getQtyTUsPerLU(){
    final I_M_HU_Item parentHUItem = aggregatedTU.getM_HU_Item_Parent();
    if (parentHUItem == null) {
        // note: shall not happen because we assume the aggregatedTU is really an aggregated TU.
        new HUException("Invalid aggregated TU. Parent item is null; aggregatedTU=" + aggregatedTU).throwIfDeveloperModeOrLogWarningElse(logger);
        return null;
    }
    return parentHUItem.getQty();
}


@Override
public boolean isInfiniteQtyCUsPerTU(){
    return false;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(aggregatedTU).toString();
}


@Override
public I_M_HU_PI getM_LU_HU_PI(){
    // no LU
    return null;
}


@Override
public boolean isInfiniteQtyTUsPerLU(){
    return false;
}


public IHUProductStorage getHUProductStorage(){
    return huProductStorageSupplier.get();
}


@Override
public I_M_HU_PI getM_TU_HU_PI(){
    final I_M_HU_PI tuPI = Services.get(IHandlingUnitsBL.class).getEffectivePI(aggregatedTU);
    if (tuPI == null) {
        new HUException("Invalid aggregated TU. Effective PI could not be fetched; aggregatedTU=" + aggregatedTU).throwIfDeveloperModeOrLogWarningElse(logger);
        return null;
    }
    return tuPI;
}


}