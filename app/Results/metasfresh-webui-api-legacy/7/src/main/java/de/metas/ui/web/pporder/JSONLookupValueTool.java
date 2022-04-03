package de.metas.ui.web.pporder;
 import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Product;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import lombok.experimental.UtilityClass;
@UtilityClass
public class JSONLookupValueTool {


public JSONLookupValue createProductLookupValue(I_M_Product product){
    if (product == null) {
        return null;
    }
    final String displayName = product.getValue() + "_" + product.getName();
    return JSONLookupValue.of(product.getM_Product_ID(), displayName);
}


public JSONLookupValue createUOMLookupValue(I_C_UOM uom){
    if (uom == null) {
        return null;
    }
    return JSONLookupValue.of(uom.getC_UOM_ID(), uom.getUOMSymbol());
}


}