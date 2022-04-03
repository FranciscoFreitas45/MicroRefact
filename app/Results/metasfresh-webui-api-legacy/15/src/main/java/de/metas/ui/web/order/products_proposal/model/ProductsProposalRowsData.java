package de.metas.ui.web.order.products_proposal.model;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.bpartner.BPartnerId;
import de.metas.currency.Amount;
import de.metas.lang.SOTrx;
import de.metas.pricing.PriceListVersionId;
import de.metas.product.ProductId;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.order.products_proposal.campaign_price.CampaignPriceProvider;
import de.metas.ui.web.order.products_proposal.campaign_price.CampaignPriceProviders;
import de.metas.ui.web.order.products_proposal.filters.ProductsProposalViewFilter;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowChangeRequest.RowUpdate;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowChangeRequest.UserChange;
import de.metas.ui.web.order.products_proposal.service.Order;
import de.metas.ui.web.view.IEditableView.RowEditingContext;
import de.metas.ui.web.view.ViewHeaderProperties;
import de.metas.ui.web.view.template.IEditableRowsData;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdIntSequence;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.util.GuavaCollectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
public class ProductsProposalRowsData implements IEditableRowsData<ProductsProposalRow>{

 private  DocumentIdIntSequence nextRowIdSequence;

 private  CampaignPriceProvider campaignPriceProvider;

 private  ArrayList<DocumentId> rowIdsOrderedAndFiltered;

 private  ArrayList<DocumentId> rowIdsOrdered;

 private  HashMap<DocumentId,ProductsProposalRow> rowsById;

@Getter
 private  Optional<PriceListVersionId> singlePriceListVersionId;

@Getter
 private  Optional<PriceListVersionId> basePriceListVersionId;

@Getter
 private  Optional<Order> order;

@Getter
 private  Optional<BPartnerId> bpartnerId;

@Getter
 private  SOTrx soTrx;

@Getter
 private  ViewHeaderProperties headerProperties;

 private  ProductsProposalViewFilter filter;


public Set<ProductId> getProductIds(){
    return rowsById.values().stream().map(ProductsProposalRow::getProductId).collect(ImmutableSet.toImmutableSet());
}


public void patchRow(DocumentId rowId,ProductsProposalRowChangeRequest request){
    changeRow(rowId, row -> ProductsProposalRowReducers.reduce(row, request));
}


@Override
public ImmutableList<ProductsProposalRow> getAllRows(){
    return getTopLevelRows();
}


public void addOrUpdateRow(ProductsProposalRowAddRequest request){
    final ProductsProposalRow existingRow = getRowByProductAndASI(request.getProductId(), request.getAsiDescription()).orElse(null);
    if (existingRow != null) {
        patchRow(existingRow.getId(), RowUpdate.builder().price(createPrice(request.getProductId(), request.getPriceListPrice())).lastShipmentDays(request.getLastShipmentDays()).copiedFromProductPriceId(request.getCopiedFromProductPriceId()).build());
    } else {
        addRow(createRow(request));
    }
}


@Override
public ImmutableList<ProductsProposalRow> getTopLevelRows(){
    return rowIdsOrderedAndFiltered.stream().map(rowsById::get).collect(ImmutableList.toImmutableList());
}


public void addRow(ProductsProposalRow row){
    // add first
    rowIdsOrderedAndFiltered.add(0, row.getId());
    // add first
    rowIdsOrdered.add(0, row.getId());
    rowsById.put(row.getId(), row);
}


@Override
public ImmutableMap<DocumentId,ProductsProposalRow> getDocumentId2TopLevelRows(){
    return rowIdsOrderedAndFiltered.stream().map(rowsById::get).collect(GuavaCollectors.toImmutableMapByKey(ProductsProposalRow::getId));
}


public void addOrUpdateRows(List<ProductsProposalRowAddRequest> requests){
    requests.forEach(this::addOrUpdateRow);
}


public ProductProposalPrice createPrice(ProductId productId,Amount priceListPrice){
    final ProductProposalCampaignPrice campaignPrice = campaignPriceProvider.getCampaignPrice(productId).orElse(null);
    return ProductProposalPrice.builder().priceListPrice(priceListPrice).campaignPrice(campaignPrice).build();
}


public void filter(ProductsProposalViewFilter filter){
    if (Objects.equals(this.filter, filter)) {
        return;
    }
    this.filter = filter;
    rowIdsOrderedAndFiltered = rowIdsOrdered.stream().filter(rowId -> rowsById.get(rowId).isMatching(filter)).collect(Collectors.toCollection(ArrayList::new));
}


public void removeRowsByIds(Set<DocumentId> rowIds){
    rowIdsOrderedAndFiltered.removeAll(rowIds);
    rowIdsOrdered.removeAll(rowIds);
    rowIds.forEach(rowsById::remove);
}


public ProductsProposalViewFilter getFilter(){
    return filter;
}


@Override
public DocumentIdsSelection getDocumentIdsToInvalidate(TableRecordReferenceSet recordRefs){
    return DocumentIdsSelection.EMPTY;
}


@Override
public Map<DocumentId,ProductsProposalRow> getDocumentId2AllRows(){
    return getDocumentId2TopLevelRows();
}


@Override
public int size(){
    return rowIdsOrderedAndFiltered.size();
}


@Override
public void invalidateAll(){
}


public ProductsProposalRow createRow(ProductsProposalRowAddRequest request){
    return ProductsProposalRow.builder().id(nextRowIdSequence.nextDocumentId()).product(request.getProduct()).asiDescription(request.getAsiDescription()).price(createPrice(request.getProductId(), request.getPriceListPrice())).packingMaterialId(request.getPackingMaterialId()).packingDescription(request.getPackingDescription()).lastShipmentDays(request.getLastShipmentDays()).copiedFromProductPriceId(request.getCopiedFromProductPriceId()).build().withExistingOrderLine(order.orElse(null));
}


public void changeRow(DocumentId rowId,UnaryOperator<ProductsProposalRow> mapper){
    if (!rowIdsOrderedAndFiltered.contains(rowId)) {
        throw new EntityNotFoundException(rowId.toJson());
    }
    rowsById.compute(rowId, (key, oldRow) -> {
        if (oldRow == null) {
            throw new EntityNotFoundException(rowId.toJson());
        }
        return mapper.apply(oldRow);
    });
}


public Optional<ProductsProposalRow> getRowByProductAndASI(ProductId productId,ProductASIDescription asiDescription){
    return rowsById.values().stream().filter(row -> productId.equals(row.getProductId()) && asiDescription.equals(row.getAsiDescription())).findFirst();
}


}