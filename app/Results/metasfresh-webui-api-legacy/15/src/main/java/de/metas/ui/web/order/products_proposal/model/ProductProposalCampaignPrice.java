package de.metas.ui.web.order.products_proposal.model;
 import java.math.BigDecimal;
import de.metas.currency.Amount;
import de.metas.currency.CurrencyCode;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
@Builder
@EqualsAndHashCode
@ToString
public class ProductProposalCampaignPrice {

@NonNull
 private  Amount amount;

 private  boolean applyOnlyIfLessThanStandardPrice;


public CurrencyCode getCurrencyCode(){
    return amount.getCurrencyCode();
}


public boolean amountValueComparingEqualsTo(BigDecimal other){
    return amount.valueComparingEqualsTo(other);
}


public Amount applyOn(Amount standardPrice){
    if (applyOnlyIfLessThanStandardPrice) {
        return this.amount.min(standardPrice);
    } else {
        return amount;
    }
}


}