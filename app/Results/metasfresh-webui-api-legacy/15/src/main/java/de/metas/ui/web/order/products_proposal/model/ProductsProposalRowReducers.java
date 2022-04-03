package de.metas.ui.web.order.products_proposal.model;
 import java.math.BigDecimal;
import org.adempiere.exceptions.AdempiereException;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRow.ProductsProposalRowBuilder;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowChangeRequest.RowSaved;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowChangeRequest.RowUpdate;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowChangeRequest.UserChange;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
@UtilityClass
public class ProductsProposalRowReducers {


public ProductsProposalRow reduce(ProductsProposalRow row,ProductsProposalRowChangeRequest request){
    if (request instanceof UserChange) {
        return reduceUserRequest(row, (UserChange) request);
    } else if (request instanceof RowUpdate) {
        return reduceRowUpdate(row, (RowUpdate) request);
    }
    if (request instanceof RowSaved) {
        return reduceRowSaved(row, (RowSaved) request);
    } else {
        throw new AdempiereException("Unknown request: " + request);
    }
}


public ProductsProposalRow reduceRowUpdate(ProductsProposalRow row,RowUpdate request){
    return row.toBuilder().price(request.getPrice()).lastShipmentDays(request.getLastShipmentDays()).copiedFromProductPriceId(request.getCopiedFromProductPriceId()).build();
}


public ProductsProposalRow reduceRowSaved(ProductsProposalRow row,RowSaved request){
    return row.toBuilder().productPriceId(request.getProductPriceId()).price(request.getPrice()).copiedFromProductPriceId(null).build();
}


public ProductsProposalRow reduceUserRequest(ProductsProposalRow row,UserChange request){
    final ProductsProposalRowBuilder newRowBuilder = row.toBuilder();
    if (request.getQty() != null) {
        newRowBuilder.qty(request.getQty().orElse(null));
    }
    if (request.getPrice() != null) {
        if (!row.isPriceEditable()) {
            throw new AdempiereException("Price is not editable").setParameter("row", row);
        }
        final BigDecimal newUserEnteredPriceValue = request.getPrice().orElse(BigDecimal.ZERO);
        final ProductProposalPrice newPrice = row.getPrice().withUserEnteredPriceValue(newUserEnteredPriceValue);
        newRowBuilder.price(newPrice);
    }
    if (request.getDescription() != null) {
        newRowBuilder.description(request.getDescription().orElse(null));
    }
    return newRowBuilder.build();
}


}