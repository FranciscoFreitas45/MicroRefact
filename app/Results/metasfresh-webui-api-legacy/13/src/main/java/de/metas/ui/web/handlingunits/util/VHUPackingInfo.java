package de.metas.ui.web.handlingunits.util;
 import java.math.BigDecimal;
import java.util.List;
import java.util.function.Supplier;
import org.compiere.model.I_C_UOM;
import com.google.common.base.Suppliers;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_PI;
import de.metas.handlingunits.storage.IHUProductStorage;
import de.metas.util.Check;
import de.metas.util.Services;
public class VHUPackingInfo implements IHUPackingInfo{

 private  Supplier<IHUProductStorage> huProductStorageSupplier;


@Override
public BigDecimal getQtyCUsPerTU(){
    final IHUProductStorage huProductStorage = getHUProductStorage();
    if (huProductStorage == null) {
        return null;
    }
    return huProductStorage.getQty().toBigDecimal();
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
    return null;
}


@Override
public boolean isInfiniteQtyCUsPerTU(){
    return false;
}


@Override
public I_M_HU_PI getM_LU_HU_PI(){
    return null;
}


@Override
public boolean isInfiniteQtyTUsPerLU(){
    return true;
}


public IHUProductStorage getHUProductStorage(){
    return huProductStorageSupplier.get();
}


@Override
public I_M_HU_PI getM_TU_HU_PI(){
    return null;
}


}