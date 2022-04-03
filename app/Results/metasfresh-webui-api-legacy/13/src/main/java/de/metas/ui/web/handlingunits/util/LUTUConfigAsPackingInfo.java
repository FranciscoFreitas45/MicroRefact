package de.metas.ui.web.handlingunits.util;
 import java.math.BigDecimal;
import org.compiere.model.I_C_UOM;
import com.google.common.base.MoreObjects;
import de.metas.handlingunits.allocation.ILUTUConfigurationFactory;
import de.metas.handlingunits.model.I_M_HU_LUTU_Configuration;
import de.metas.handlingunits.model.I_M_HU_PI;
import de.metas.util.Check;
public class LUTUConfigAsPackingInfo implements IHUPackingInfo{

 private  I_M_HU_LUTU_Configuration lutuConfig;


@Override
public BigDecimal getQtyCUsPerTU(){
    return lutuConfig.getQtyCU();
}


@Override
public I_C_UOM getQtyCUsPerTU_UOM(){
    return ILUTUConfigurationFactory.extractUOMOrNull(lutuConfig);
}


@Override
public BigDecimal getQtyTUsPerLU(){
    return lutuConfig.getQtyTU();
}


@Override
public boolean isInfiniteQtyCUsPerTU(){
    return lutuConfig.isInfiniteQtyCU();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(lutuConfig).toString();
}


@Override
public I_M_HU_PI getM_LU_HU_PI(){
    return lutuConfig.getM_LU_HU_PI();
}


@Override
public boolean isInfiniteQtyTUsPerLU(){
    return lutuConfig.isInfiniteQtyTU();
}


@Override
public I_M_HU_PI getM_TU_HU_PI(){
    return lutuConfig.getM_TU_HU_PI();
}


}