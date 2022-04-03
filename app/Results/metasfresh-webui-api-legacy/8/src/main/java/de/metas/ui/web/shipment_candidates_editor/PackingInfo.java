package de.metas.ui.web.shipment_candidates_editor;
 import java.math.BigDecimal;
import java.math.RoundingMode;
import de.metas.quantity.Quantity;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
public class PackingInfo {

 public  PackingInfo NONE;

@NonNull
 private BigDecimal qtyCUsPerTU;

@NonNull
 private String description;


public boolean isNone(){
    return NONE.equals(this);
}


public BigDecimal computeQtyCUsByQtyUserEntered(BigDecimal qtyUserEntered){
    return isNone() ? qtyUserEntered : computeQtyCUsByQtyTUs(qtyUserEntered);
}


public BigDecimal computeQtyTUsByQtyCUs(BigDecimal qtyCUs){
    return qtyCUs.divide(qtyCUsPerTU, 0, RoundingMode.DOWN);
}


public BigDecimal computeQtyCUsByQtyTUs(BigDecimal qtyTUs){
    return qtyTUs.multiply(qtyCUsPerTU);
}


public BigDecimal computeQtyUserEnteredByQtyCUs(BigDecimal qtyCUs){
    return isNone() ? qtyCUs : computeQtyTUsByQtyCUs(qtyCUs);
}


}