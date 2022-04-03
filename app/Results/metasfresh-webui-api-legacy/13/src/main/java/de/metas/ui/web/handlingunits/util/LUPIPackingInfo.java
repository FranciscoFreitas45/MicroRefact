package de.metas.ui.web.handlingunits.util;
 import java.math.BigDecimal;
import org.compiere.model.I_C_UOM;
import com.google.common.base.MoreObjects;
import de.metas.handlingunits.model.I_M_HU_PI;
import de.metas.util.Check;
public class LUPIPackingInfo implements IHUPackingInfo{

 private  I_M_HU_PI luPI;


@Override
public BigDecimal getQtyCUsPerTU(){
    return null;
}


@Override
public I_C_UOM getQtyCUsPerTU_UOM(){
    return null;
}


@Override
public BigDecimal getQtyTUsPerLU(){
    return null;
}


@Override
public boolean isInfiniteQtyCUsPerTU(){
    return true;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(luPI).toString();
}


@Override
public I_M_HU_PI getM_LU_HU_PI(){
    return luPI;
}


@Override
public boolean isInfiniteQtyTUsPerLU(){
    return true;
}


@Override
public I_M_HU_PI getM_TU_HU_PI(){
    return null;
}


}