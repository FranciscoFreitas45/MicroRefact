package de.metas.ui.web.handlingunits.util;
 import java.math.BigDecimal;
import java.util.List;
import org.compiere.model.I_C_UOM;
import com.google.common.base.MoreObjects;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_PI;
import de.metas.handlingunits.storage.IHUProductStorage;
import de.metas.util.Services;
import lombok.NonNull;
public class TUPackingInfo implements IHUPackingInfo{

 private  I_M_HU tuHU;

 private  Supplier<IHUProductStorage> huProductStorageSupplier;


@Override
public BigDecimal getQtyCUsPerTU(){
    final IHUProductStorage huProductStorage = getHUProductStorage();
    return huProductStorage == null ? null : huProductStorage.getQty().toBigDecimal();
}


@Override
public I_C_UOM getQtyCUsPerTU_UOM(){
    final IHUProductStorage huProductStorage = getHUProductStorage();
    return huProductStorage == null ? null : huProductStorage.getC_UOM();
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
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(tuHU).toString();
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
    return Services.get(IHandlingUnitsBL.class).getPI(tuHU);
}


}