package de.metas.ui.web.material.adapter;
 import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.mm.attributes.api.AttributesKeys;
import org.adempiere.mm.attributes.api.ImmutableAttributeSet;
import org.compiere.model.I_C_UOM;
import org.springframework.stereotype.Service;
import com.google.common.annotations.VisibleForTesting;
import de.metas.material.commons.attributes.AttributesKeyPattern;
import de.metas.material.dispo.commons.repository.atp.AvailableToPromiseQuery;
import de.metas.material.dispo.commons.repository.atp.AvailableToPromiseRepository;
import de.metas.material.dispo.commons.repository.atp.AvailableToPromiseResult;
import de.metas.material.dispo.commons.repository.atp.AvailableToPromiseResultGroup;
import de.metas.material.event.commons.AttributesKey;
import de.metas.product.IProductBL;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.ui.web.material.adapter.AvailableToPromiseResultForWebui.AvailableToPromiseResultForWebuiBuilder;
import de.metas.util.Services;
import lombok.NonNull;
@Service
public class AvailableToPromiseAdapter {

 private  IProductBL productsService;

 private  AvailableToPromiseRepository availableToPromiseRepository;


public Quantity extractQuantity(AvailableToPromiseResultGroup commonsResultGroup){
    final BigDecimal qty = commonsResultGroup.getQty();
    final I_C_UOM uom = productsService.getStockUOM(commonsResultGroup.getProductId());
    return Quantity.of(qty, uom);
}


@NonNull
public AvailableToPromiseResultForWebui retrieveAvailableStock(AvailableToPromiseQuery query){
    final AvailableToPromiseResult commonsAvailableStock = availableToPromiseRepository.retrieveAvailableStock(query);
    final AvailableToPromiseResultForWebuiBuilder clientResultBuilder = AvailableToPromiseResultForWebui.builder();
    final List<AvailableToPromiseResultGroup> commonsResultGroups = commonsAvailableStock.getResultGroups();
    for (final AvailableToPromiseResultGroup commonsResultGroup : commonsResultGroups) {
        final AvailableToPromiseResultForWebui.Group clientResultGroup = createClientResultGroup(commonsResultGroup);
        clientResultBuilder.group(clientResultGroup);
    }
    return clientResultBuilder.build();
}


public Set<AttributesKeyPattern> getPredefinedStorageAttributeKeys(){
    return availableToPromiseRepository.getPredefinedStorageAttributeKeys();
}


@VisibleForTesting
public AvailableToPromiseResultForWebui.Group.Type extractGroupType(AttributesKey attributesKey){
    if (AttributesKey.ALL.equals(attributesKey)) {
        return AvailableToPromiseResultForWebui.Group.Type.ALL_STORAGE_KEYS;
    } else if (AttributesKey.OTHER.equals(attributesKey)) {
        return AvailableToPromiseResultForWebui.Group.Type.OTHER_STORAGE_KEYS;
    } else {
        return AvailableToPromiseResultForWebui.Group.Type.ATTRIBUTE_SET;
    }
}


public AvailableToPromiseResultForWebui.Group createClientResultGroup0(AvailableToPromiseResultGroup commonsResultGroup){
    final Quantity quantity = extractQuantity(commonsResultGroup);
    final AttributesKey attributesKey = commonsResultGroup.getStorageAttributesKey();
    final AvailableToPromiseResultForWebui.Group.Type type = extractGroupType(attributesKey);
    final ImmutableAttributeSet attributes = AvailableToPromiseResultForWebui.Group.Type.ATTRIBUTE_SET.equals(type) ? AttributesKeys.toImmutableAttributeSet(attributesKey) : ImmutableAttributeSet.EMPTY;
    return AvailableToPromiseResultForWebui.Group.builder().productId(ProductId.ofRepoId(commonsResultGroup.getProductId())).qty(quantity).type(type).attributes(attributes).build();
}


public AvailableToPromiseResultForWebui.Group createClientResultGroup(AvailableToPromiseResultGroup commonsResultGroup){
    try {
        return createClientResultGroup0(commonsResultGroup);
    } catch (final RuntimeException e) {
        throw AdempiereException.wrapIfNeeded(e).appendParametersToMessage().setParameter("commonsResultGroup", commonsResultGroup);
    }
}


}