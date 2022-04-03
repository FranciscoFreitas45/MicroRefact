package de.metas.ui.web.order.sales.purchasePlanning.view;
 import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Supplier;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_UOM;
import de.metas.quantity.Quantity;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.util.Check;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
@Value
public class PurchaseRowChangeRequest {

@Getter(AccessLevel.PRIVATE)
 private Quantity qtyToPurchase;

@Getter(AccessLevel.PRIVATE)
 private BigDecimal qtyToPurchaseWithoutUOM;

 private ZonedDateTime purchaseDatePromised;


public PurchaseRowChangeRequest of(List<JSONDocumentChangedEvent> fieldChangeRequests){
    Check.assumeNotEmpty(fieldChangeRequests, "field change requests shall not be empty");
    final PurchaseRowChangeRequestBuilder builder = builder();
    for (final JSONDocumentChangedEvent fieldChangeRequest : fieldChangeRequests) {
        final String fieldName = fieldChangeRequest.getPath();
        if (PurchaseRow.FIELDNAME_QtyToPurchase.equals(fieldName)) {
            final BigDecimal qtyToPurchase = fieldChangeRequest.getValueAsBigDecimal();
            Check.assumeNotNull(qtyToPurchase, "Parameter qtyToPurchase is not null for {}", fieldChangeRequest);
            builder.qtyToPurchaseWithoutUOM(qtyToPurchase);
        } else if (PurchaseRow.FIELDNAME_DatePromised.equals(fieldName)) {
            final ZonedDateTime datePromised = fieldChangeRequest.getValueAsZonedDateTime();
            Check.assumeNotNull(datePromised, "Parameter datePromised is not null for {}", fieldChangeRequest);
            builder.purchaseDatePromised(datePromised);
        } else {
            throw new AdempiereException("Field " + fieldName + " is not editable");
        }
    }
    return builder.build();
}


public Quantity getQtyToPurchase(Supplier<I_C_UOM> defaultUOMSupplier){
    if (getQtyToPurchase() != null) {
        return getQtyToPurchase();
    } else if (getQtyToPurchaseWithoutUOM() != null) {
        final BigDecimal qtyToPurchase = getQtyToPurchaseWithoutUOM();
        return Quantity.of(qtyToPurchase, defaultUOMSupplier.get());
    } else {
        return null;
    }
}


}