package de.metas.ui.web.order.products_proposal.model;
 import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowChangeRequest.UserChange;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowChangeRequest.UserChange.UserChangeBuilder;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import lombok.experimental.UtilityClass;
@UtilityClass
public class ProductsProposalRowActions {


public UserChange toUserChangeRequest(List<JSONDocumentChangedEvent> fieldChangeRequests){
    final UserChangeBuilder builder = UserChange.builder();
    for (final JSONDocumentChangedEvent fieldChangeRequest : fieldChangeRequests) {
        final String fieldName = fieldChangeRequest.getPath();
        if (ProductsProposalRow.FIELD_Qty.equals(fieldName)) {
            builder.qty(Optional.ofNullable(fieldChangeRequest.getValueAsBigDecimal()));
        } else if (ProductsProposalRow.FIELD_Price.equals(fieldName)) {
            builder.price(Optional.of(fieldChangeRequest.getValueAsBigDecimal(BigDecimal.ZERO)));
        } else if (ProductsProposalRow.FIELD_Description.equals(fieldName)) {
            builder.description(Optional.ofNullable(fieldChangeRequest.getValueAsString(null)));
        }
    }
    return builder.build();
}


}