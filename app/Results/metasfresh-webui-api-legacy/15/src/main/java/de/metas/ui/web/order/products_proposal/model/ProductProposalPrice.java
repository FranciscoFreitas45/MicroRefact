package de.metas.ui.web.order.products_proposal.model;
 import java.math.BigDecimal;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import de.metas.currency.Amount;
import de.metas.currency.CurrencyCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class ProductProposalPrice {

@Getter
 private  BigDecimal userEnteredPriceValue;

@Getter
 private  CurrencyCode currencyCode;

 private  Amount priceListPrice;

 private  ProductProposalCampaignPrice campaignPrice;

@Getter
 private  boolean campaignPriceUsed;

@Getter
 private  boolean priceListPriceUsed;


public ProductProposalPrice withPriceListPriceValue(BigDecimal priceListPriceValue){
    return withPriceListPrice(Amount.of(priceListPriceValue, getCurrencyCode()));
}


public ProductProposalPrice withUserEnteredPriceValue(BigDecimal userEnteredPriceValue){
    if (this.userEnteredPriceValue.equals(userEnteredPriceValue)) {
        return this;
    }
    return toBuilder().userEnteredPriceValue(userEnteredPriceValue).build();
}


public Amount getUserEnteredPrice(){
    return Amount.of(getUserEnteredPriceValue(), getCurrencyCode());
}


public ProductProposalPrice withPriceListPrice(Amount priceListPrice){
    if (this.priceListPrice.equals(priceListPrice)) {
        return this;
    }
    return toBuilder().priceListPrice(priceListPrice).build();
}


}