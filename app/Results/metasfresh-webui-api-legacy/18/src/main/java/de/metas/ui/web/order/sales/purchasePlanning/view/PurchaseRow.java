package de.metas.ui.web.order.sales.purchasePlanning.view;
 import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_UOM;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import de.metas.bpartner.BPartnerId;
import de.metas.money.Money;
import de.metas.printing.esb.base.util.Check;
import de.metas.product.ProductId;
import de.metas.purchasecandidate.DemandGroupReference;
import de.metas.purchasecandidate.PurchaseCandidatesGroup;
import de.metas.purchasecandidate.PurchaseCandidatesGroup.PurchaseCandidatesGroupBuilder;
import de.metas.purchasecandidate.PurchaseDemand;
import de.metas.purchasecandidate.availability.AvailabilityResult;
import de.metas.purchasecandidate.availability.AvailabilityResult.Type;
import de.metas.purchasecandidate.grossprofit.PurchaseProfitInfo;
import de.metas.purchasecandidate.grossprofit.PurchaseProfitInfoRequest;
import de.metas.purchasecandidate.grossprofit.PurchaseProfitInfoService;
import de.metas.purchasecandidate.model.I_C_PurchaseCandidate;
import de.metas.quantity.Quantity;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValues;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValuesHolder;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn;
import de.metas.ui.web.view.descriptor.annotation.ViewColumnHelper;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.ViewEditorRenderMode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@ToString(doNotUseGetters = true)
public class PurchaseRow implements IViewRow{

@Nullable
 private  PurchaseProfitInfoService purchaseProfitInfoService;

@Nullable
 private  PurchaseRowLookups lookups;

 public  String FIELDNAME_QtyToPurchase;

 public  String FIELDNAME_PurchasedQty;

 public  String FIELDNAME_DatePromised;

@ViewColumn(captionKey = "M_Product_ID", widgetType = DocumentFieldWidgetType.Lookup, seqNo = 10)
 private  LookupValue product;

 private  LookupValue attributeSetInstance;

@ViewColumn(captionKey = "Vendor_ID", widgetType = DocumentFieldWidgetType.Lookup, seqNo = 20)
 private  LookupValue vendorBPartner;

@ViewColumn(captionKey = I_C_PurchaseCandidate.COLUMNNAME_ProfitSalesPriceActual, widgetType = DocumentFieldWidgetType.Amount, seqNo = 23)
 private  Money profitSalesPriceActual;

@ViewColumn(captionKey = I_C_PurchaseCandidate.COLUMNNAME_ProfitPurchasePriceActual, widgetType = DocumentFieldWidgetType.Amount, seqNo = 25)
 private  Money profitPurchasePriceActual;

@ViewColumn(captionKey = "PercentGrossProfit", widgetType = DocumentFieldWidgetType.Amount, seqNo = 25)
 private  BigDecimal profitPercent;

@ViewColumn(captionKey = "Qty_AvailableToPromise", widgetType = DocumentFieldWidgetType.Quantity, seqNo = 30)
 private  Quantity qtyAvailableToPromise;

@ViewColumn(captionKey = "QtyToDeliver", widgetType = DocumentFieldWidgetType.Quantity, seqNo = 40)
 private  Quantity qtyToDeliver;

@ViewColumn(fieldName = FIELDNAME_QtyToPurchase, captionKey = "QtyToPurchase", widgetType = DocumentFieldWidgetType.Quantity, seqNo = 50)
@Getter
 private  Quantity qtyToPurchase;

@ViewColumn(fieldName = FIELDNAME_PurchasedQty, captionKey = "PurchasedQty", widgetType = DocumentFieldWidgetType.Quantity, seqNo = 55)
@Getter(AccessLevel.PRIVATE)
 private  Quantity purchasedQty;

@ViewColumn(captionKey = "C_UOM_ID", widgetType = DocumentFieldWidgetType.Text, seqNo = 60)
 private  String uomOrAvailablility;

@ViewColumn(fieldName = FIELDNAME_DatePromised, captionKey = "DatePromised", widgetType = DocumentFieldWidgetType.ZonedDateTime, seqNo = 70)
@Getter
 private  ZonedDateTime datePromised;

 private  PurchaseRowId rowId;

 private  ImmutableMap<PurchaseRowId,PurchaseRow> _includedRowsById;

 private  boolean readonly;

@Getter(AccessLevel.PRIVATE)
 private  PurchaseCandidatesGroup purchaseCandidatesGroup;

 private  ViewRowFieldNameAndJsonValuesHolder<PurchaseRow> values;

 private  ImmutableMap<String,ViewEditorRenderMode> ViewEditorRenderModeByFieldName_ReadOnly;

 private  ImmutableMap<String,ViewEditorRenderMode> ViewEditorRenderModeByFieldName_Editable;


public void setProfitInfo(PurchaseProfitInfo profitInfo){
    if (profitInfo != null) {
        profitSalesPriceActual = profitInfo.getProfitSalesPriceActual().orElse(null);
        profitPurchasePriceActual = profitInfo.getProfitPurchasePriceActual().orElse(null);
        profitPercent = profitInfo.getProfitPercent().map(percent -> percent.roundToHalf(RoundingMode.HALF_UP).toBigDecimal()).orElse(null);
    } else {
        profitSalesPriceActual = null;
        profitPurchasePriceActual = null;
        profitPercent = null;
    }
    resetFieldNamesAndValues();
}


public void assertRowType(PurchaseRowType expectedRowType){
    getRowId().assertRowType(expectedRowType);
    final PurchaseRowType rowType = getType();
    if (rowType != expectedRowType) {
        throw new AdempiereException("Expected " + expectedRowType + " but it was " + rowType + ": " + this);
    }
}


public PurchaseRowId getRowId(){
    return rowId;
}


public I_C_UOM getCurrentUOM(){
    return getQtyToPurchase().getUOM();
}


@Override
public DocumentId getId(){
    return getRowId().toDocumentId();
}


public void setIncludedRows(ImmutableList<PurchaseRow> includedRows){
    _includedRowsById = Maps.uniqueIndex(includedRows, PurchaseRow::getRowId);
}


@Override
public ImmutableSet<String> getFieldNames(){
    return values.getFieldNames();
}


public Stream<PurchaseCandidatesGroup> streamPurchaseCandidatesGroup(){
    final Stream<PurchaseCandidatesGroup> includedRowsStream = getIncludedRows().stream().flatMap(PurchaseRow::streamPurchaseCandidatesGroup);
    final PurchaseCandidatesGroup candidatesGroup = getPurchaseCandidatesGroup();
    if (candidatesGroup == null) {
        return includedRowsStream;
    } else {
        return Stream.concat(Stream.of(candidatesGroup), includedRowsStream);
    }
}


public ViewRowFieldNameAndJsonValuesHolder<PurchaseRow> createViewRowFieldNameAndJsonValuesHolder(boolean readonly){
    return ViewRowFieldNameAndJsonValuesHolder.builder(PurchaseRow.class).widgetTypesByFieldName(ViewColumnHelper.getWidgetTypesByFieldName(PurchaseRow.class)).viewEditorRenderModeByFieldName(readonly ? ViewEditorRenderModeByFieldName_ReadOnly : ViewEditorRenderModeByFieldName_Editable).build();
}


@Override
public Map<String,ViewEditorRenderMode> getViewEditorRenderModeByFieldName(){
    return values.getViewEditorRenderModeByFieldName();
}


public void updateQtysFromIncludedRows(){
    Quantity qtyToPurchaseSum = null;
    Quantity purchasedQtySum = null;
    for (final PurchaseRow includedRow : getIncludedRows()) {
        qtyToPurchaseSum = Quantity.addNullables(qtyToPurchaseSum, includedRow.getQtyToPurchase());
        purchasedQtySum = Quantity.addNullables(purchasedQtySum, includedRow.getPurchasedQty());
    }
    setQtyToPurchase(qtyToPurchaseSum);
    setPurchasedQty(purchasedQtySum);
}


public void setPurchaseCandidatesGroup(PurchaseCandidatesGroup purchaseCandidatesGroup){
    if (Objects.equals(this.purchaseCandidatesGroup, purchaseCandidatesGroup)) {
        return;
    }
    // NOTE: we assume purchaseCandidatesGroup's vendor and product wasn't changed!
    this.purchaseCandidatesGroup = purchaseCandidatesGroup;
    setQtyToPurchase(purchaseCandidatesGroup.getQtyToPurchase());
    setPurchasedQty(purchaseCandidatesGroup.getPurchasedQty());
    setProfitInfo(purchaseCandidatesGroup.getProfitInfoOrNull());
    setDatePromised(purchaseCandidatesGroup.getPurchaseDatePromised());
}


public PurchaseRow copy(){
    return new PurchaseRow(this);
}


public void setQtyToPurchase(Quantity qtyToPurchase){
    if (Objects.equals(this.qtyToPurchase, qtyToPurchase)) {
        return;
    }
    this.qtyToPurchase = qtyToPurchase;
    uomOrAvailablility = qtyToPurchase != null ? lookups.createUOMLookupValue(qtyToPurchase.getUOM()) : null;
    resetFieldNamesAndValues();
}


public void resetFieldNamesAndValues(){
    values.clearValues();
}


@Override
public Collection<PurchaseRow> getIncludedRows(){
    return _includedRowsById.values();
}


public void setPurchasedQty(Quantity purchasedQty){
    if (Objects.equals(this.purchasedQty, purchasedQty)) {
        return;
    }
    this.purchasedQty = purchasedQty;
    resetFieldNamesAndValues();
}


public void setAvailabilityInfoRow(PurchaseRow availabilityResultRow){
    setAvailabilityInfoRows(ImmutableList.of(availabilityResultRow));
}


public void changeIncludedRow(PurchaseRowId includedRowId,PurchaseRowChangeRequest request){
    assertRowType(PurchaseRowType.GROUP);
    final PurchaseRow lineRow = getIncludedRowById(includedRowId);
    lineRow.changeRow(request);
    updateQtysFromIncludedRows();
}


public void assertRowEditable(){
    if (readonly) {
        throw new AdempiereException("readonly").setParameter("rowId", getRowId());
    }
}


@Override
public Map<String,DocumentFieldWidgetType> getWidgetTypesByFieldName(){
    return values.getWidgetTypesByFieldName();
}


public PurchaseRow getIncludedRowById(PurchaseRowId rowId){
    final PurchaseRow includedRow = _includedRowsById.get(rowId);
    if (includedRow == null) {
        throw new EntityNotFoundException("Included row not found").appendParametersToMessage().setParameter("rowId", rowId).setParameter("this", this);
    }
    return includedRow;
}


@Override
public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues(){
    return values.get(this);
}


@Override
public PurchaseRowType getType(){
    return getRowId().getType();
}


public void setAvailabilityInfoRows(List<PurchaseRow> availabilityResultRows){
    assertRowType(PurchaseRowType.LINE);
    availabilityResultRows.forEach(availabilityResultRow -> availabilityResultRow.assertRowType(PurchaseRowType.AVAILABILITY_DETAIL));
    // If there is at least one "available on vendor" row,
    // we shall order directly and not aggregate later on a Purchase Order.
    final boolean allowPOAggregation = availabilityResultRows.stream().noneMatch(row -> row.getRowId().isAvailableOnVendor());
    setIncludedRows(ImmutableList.copyOf(availabilityResultRows));
    setPurchaseCandidatesGroup(getPurchaseCandidatesGroup().allowingPOAggregation(allowPOAggregation));
}


@Override
public DocumentPath getDocumentPath(){
    // TODO Auto-generated method stub
    return null;
}


public void setDatePromised(ZonedDateTime datePromised){
    if (Objects.equals(this.datePromised, datePromised)) {
        return;
    }
    this.datePromised = datePromised;
    resetFieldNamesAndValues();
}


public void changeRow(PurchaseRowChangeRequest request){
    assertRowType(PurchaseRowType.LINE);
    assertRowEditable();
    // 
    final PurchaseCandidatesGroup candidatesGroup = getPurchaseCandidatesGroup();
    final PurchaseCandidatesGroupBuilder newCandidatesGroup = candidatesGroup.toBuilder();
    boolean hasChanges = false;
    // 
    // QtyToPurchase
    final Quantity qtyToPurchase = request.getQtyToPurchase(this::getCurrentUOM);
    boolean qtyToPurchaseChanged = false;
    if (qtyToPurchase != null && !Objects.equals(candidatesGroup.getQtyToPurchase(), qtyToPurchase)) {
        newCandidatesGroup.qtyToPurchase(qtyToPurchase);
        qtyToPurchaseChanged = true;
        hasChanges = true;
    }
    // 
    // PurchaseDatePromised
    final ZonedDateTime purchaseDatePromised = request.getPurchaseDatePromised();
    if (purchaseDatePromised != null) {
        newCandidatesGroup.purchaseDatePromised(purchaseDatePromised);
        hasChanges = true;
    }
    // 
    // Recompute Profit Info
    if (qtyToPurchaseChanged) {
        final PurchaseProfitInfo profitInfo = purchaseProfitInfoService.calculateNoFail(PurchaseProfitInfoRequest.builder().salesOrderAndLineIds(candidatesGroup.getSalesOrderAndLineIds()).qtyToPurchase(qtyToPurchase).vendorProductInfo(candidatesGroup.getVendorProductInfo()).build());
        newCandidatesGroup.profitInfoOrNull(profitInfo);
        hasChanges = true;
    }
    // 
    // Stop here if there were no changes
    if (!hasChanges) {
        return;
    }
    // 
    setPurchaseCandidatesGroup(newCandidatesGroup.build());
}


@Override
public boolean isProcessed(){
    return readonly;
}


public List<DemandGroupReference> getDemandGroupReferences(){
    Check.assume(PurchaseRowType.LINE.equals(getType()), "only 'line'-type rows have demandGroupReferences; this={}", this);
    return purchaseCandidatesGroup.getDemandGroupReferences();
}


}