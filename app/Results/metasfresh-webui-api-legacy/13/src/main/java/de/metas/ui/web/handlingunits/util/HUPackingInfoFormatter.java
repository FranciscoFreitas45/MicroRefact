package de.metas.ui.web.handlingunits.util;
 import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.compiere.model.I_C_UOM;
import org.compiere.util.DisplayType;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.model.I_M_HU_PI;
import de.metas.util.Services;
public class HUPackingInfoFormatter {

 private  boolean _showLU;


public String format(IHUPackingInfo huPackingInfo){
    final StringBuilder packingInfo = new StringBuilder();
    // 
    // LU
    if (isShowLU()) {
        final I_M_HU_PI luPI = huPackingInfo.getM_LU_HU_PI();
        if (luPI != null) {
            packingInfo.append(luPI.getName());
        }
    }
    // 
    // TU
    final I_M_HU_PI tuPI = huPackingInfo.getM_TU_HU_PI();
    if (tuPI != null && !Services.get(IHandlingUnitsBL.class).isVirtual(tuPI)) {
        if (packingInfo.length() > 0) {
            packingInfo.append(" x ");
        }
        final BigDecimal qtyTU = huPackingInfo.getQtyTUsPerLU();
        if (!huPackingInfo.isInfiniteQtyTUsPerLU() && qtyTU != null && qtyTU.signum() > 0) {
            packingInfo.append(qtyTU.intValue()).append(" ");
        }
        packingInfo.append(tuPI.getName());
    }
    // 
    // CU
    final BigDecimal qtyCU = huPackingInfo.getQtyCUsPerTU();
    if (!huPackingInfo.isInfiniteQtyCUsPerTU() && qtyCU != null && qtyCU.signum() > 0) {
        if (packingInfo.length() > 0) {
            packingInfo.append(" x ");
        }
        final DecimalFormat qtyFormat = DisplayType.getNumberFormat(DisplayType.Quantity);
        packingInfo.append(qtyFormat.format(qtyCU));
        final I_C_UOM uom = huPackingInfo.getQtyCUsPerTU_UOM();
        final String uomSymbol = uom == null ? null : uom.getUOMSymbol();
        if (uomSymbol != null) {
            packingInfo.append(" ").append(uomSymbol);
        }
    }
    if (packingInfo.length() == 0) {
        // no override
        return null;
    }
    return packingInfo.toString();
}


public boolean isShowLU(){
    return _showLU;
}


public HUPackingInfoFormatter newInstance(){
    return new HUPackingInfoFormatter();
}


public HUPackingInfoFormatter setShowLU(boolean showLU){
    _showLU = showLU;
    return this;
}


}