package de.metas.ui.web.handlingunits.util;
 import java.math.BigDecimal;
import org.compiere.model.I_C_UOM;
import de.metas.handlingunits.model.I_M_HU_PI;
public interface IHUPackingInfo {


public BigDecimal getQtyCUsPerTU()
;

public I_C_UOM getQtyCUsPerTU_UOM()
;

public BigDecimal getQtyTUsPerLU()
;

public boolean isInfiniteQtyCUsPerTU()
;

public I_M_HU_PI getM_LU_HU_PI()
;

public boolean isInfiniteQtyTUsPerLU()
;

public I_M_HU_PI getM_TU_HU_PI()
;

}