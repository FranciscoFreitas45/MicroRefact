package de.metas.ui.web.pickingV2.productsToPick.rows.factory;
 import org.adempiere.exceptions.AdempiereException;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class ReservableStorage {

 private  ProductId productId;

 private  Quantity qtyFreeToReserve;


public void assertSameProductId(AllocablePackageable allocable){
    if (!ProductId.equals(productId, allocable.getProductId())) {
        throw new AdempiereException("ProductId not matching").appendParametersToMessage().setParameter("allocable", allocable).setParameter("storage", this);
    }
}


public Quantity reserve(AllocablePackageable allocable,Quantity qtyToReserve){
    assertSameProductId(allocable);
    final Quantity qtyReserved = reserveQty(qtyToReserve);
    allocable.allocateQty(qtyReserved);
    return qtyReserved;
}


public Quantity reserveQty(Quantity qtyToReserve){
    if (qtyToReserve.signum() <= 0) {
        return qtyToReserve.toZero();
    }
    if (qtyFreeToReserve.signum() <= 0) {
        return qtyToReserve.toZero();
    }
    final Quantity qtyToReserveEffective = qtyToReserve.min(qtyFreeToReserve);
    qtyFreeToReserve = qtyFreeToReserve.subtract(qtyToReserveEffective);
    return qtyToReserveEffective;
}


public Quantity computeEffectiveQtyToReserve(Quantity qtyToReserve){
    if (qtyToReserve.signum() <= 0) {
        return qtyToReserve.toZero();
    }
    if (qtyFreeToReserve.signum() <= 0) {
        return qtyToReserve.toZero();
    }
    return qtyToReserve.min(qtyFreeToReserve);
}


}