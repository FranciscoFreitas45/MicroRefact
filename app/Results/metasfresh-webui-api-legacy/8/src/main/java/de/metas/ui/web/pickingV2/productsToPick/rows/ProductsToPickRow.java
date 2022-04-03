package de.metas.ui.web.pickingV2.productsToPick.rows;
 import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.picking.PickingCandidate;
import de.metas.handlingunits.picking.PickingCandidateApprovalStatus;
import de.metas.handlingunits.picking.PickingCandidateId;
import de.metas.handlingunits.picking.PickingCandidatePickStatus;
import de.metas.i18n.ITranslatableString;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.material.planning.pporder.PPOrderBOMLineId;
import de.metas.material.planning.pporder.PPOrderId;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.shipping.ShipperId;
import de.metas.ui.web.pickingV2.productsToPick.rows.factory.ProductsToPickRowsDataFactory;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValues;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValuesHolder;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.TranslationSource;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.ViewEditorRenderMode;
import de.metas.ui.web.window.descriptor.WidgetSize;
import de.metas.util.lang.CoalesceUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@ToString(exclude = "values")
public class ProductsToPickRow implements IViewRow{

 public  String FIELD_ProductValue;

@ViewColumn(fieldName = FIELD_ProductValue, widgetType = DocumentFieldWidgetType.Text, captionKey = "ProductValue", // captionKeyIsSysConfig=true, // TODO
widgetSize = WidgetSize.Small)
 private  String productValue;

 public  String FIELD_ProductName;

@ViewColumn(fieldName = FIELD_ProductName, widgetType = DocumentFieldWidgetType.Text, captionKey = "ProductName", widgetSize = WidgetSize.Medium)
@Getter
 private  ITranslatableString productName;

 public  String FIELD_ProductPackageSize;

@ViewColumn(fieldName = FIELD_ProductPackageSize, widgetType = DocumentFieldWidgetType.Text, captionKey = "PackageSize", widgetSize = WidgetSize.Small)
 private  String productPackageSize;

 public  String FIELD_ProductPackageSizeUOM;

@ViewColumn(fieldName = FIELD_ProductPackageSizeUOM, widgetType = DocumentFieldWidgetType.Text, captionKey = "Package_UOM_ID", widgetSize = WidgetSize.Small)
 private  String productPackageSizeUOM;

 public  String FIELD_Locator;

@ViewColumn(fieldName = FIELD_Locator, widgetType = DocumentFieldWidgetType.Lookup, captionKey = "M_Locator_ID", widgetSize = WidgetSize.Small)
 private  LookupValue locator;

 public  String FIELD_LotNumber;

@// 
ViewColumn(// 
fieldName = FIELD_LotNumber, // 
widgetType = DocumentFieldWidgetType.Text, // 
captionKey = ProductsToPickRowsDataFactory.ATTR_LotNumber, // 
captionTranslationSource = TranslationSource.ATTRIBUTE_NAME, widgetSize = WidgetSize.Small)
 private  String lotNumber;

 public  String FIELD_ExpiringDate;

@// 
ViewColumn(// 
fieldName = FIELD_ExpiringDate, // 
widgetType = DocumentFieldWidgetType.LocalDate, // 
captionKey = ProductsToPickRowsDataFactory.ATTR_BestBeforeDate, // 
captionTranslationSource = TranslationSource.ATTRIBUTE_NAME, widgetSize = WidgetSize.Small)
@Getter
 private  LocalDate expiringDate;

 public  String FIELD_RepackNumber;

@// 
ViewColumn(// 
fieldName = FIELD_RepackNumber, // 
widgetType = DocumentFieldWidgetType.Text, // 
captionKey = ProductsToPickRowsDataFactory.ATTR_RepackNumber, // 
captionTranslationSource = TranslationSource.ATTRIBUTE_NAME, widgetSize = WidgetSize.Small)
 private  String repackNumber;

 public  String FIELD_Qty;

@ViewColumn(fieldName = FIELD_Qty, widgetType = DocumentFieldWidgetType.Quantity, captionKey = "Qty", widgetSize = WidgetSize.Small)
 private  Quantity qty;

 public  String FIELD_QtyOverride;

@ViewColumn(fieldName = FIELD_QtyOverride, widgetType = DocumentFieldWidgetType.Quantity, captionKey = "Qty_Override", widgetSize = WidgetSize.Small, editor = ViewEditorRenderMode.ALWAYS)
 private  Quantity qtyOverride;

 public  String FIELD_QtyReview;

@ViewColumn(fieldName = FIELD_QtyReview, widgetType = DocumentFieldWidgetType.Quantity, captionKey = "Qty", widgetSize = WidgetSize.Small, editor = ViewEditorRenderMode.ALWAYS)
@Getter
 private  BigDecimal qtyReview;

 public  String FIELD_PickStatus;

@ViewColumn(fieldName = FIELD_PickStatus, captionKey = "PickStatus", widgetType = DocumentFieldWidgetType.List, listReferenceId = PickingCandidatePickStatus.AD_REFERENCE_ID, widgetSize = WidgetSize.Small)
 private  PickingCandidatePickStatus pickStatus;

 public  String FIELD_ApprovalStatus;

@ViewColumn(fieldName = FIELD_ApprovalStatus, captionKey = "ApprovalStatus", widgetType = DocumentFieldWidgetType.List, listReferenceId = PickingCandidateApprovalStatus.AD_REFERENCE_ID, widgetSize = WidgetSize.Small)
 private  PickingCandidateApprovalStatus approvalStatus;

 private  ProductsToPickRowId rowId;

 private  ProductsToPickRowType rowType;

 private  ProductInfo productInfo;

@Getter
 private  boolean huReservedForThisRow;

 private  boolean processed;

@Getter
@Nullable
 private  PickingCandidateId pickingCandidateId;

@Getter
@Nullable
 private  ShipperId shipperId;

 private  ImmutableList<ProductsToPickRow> includedRows;

 private  ViewRowFieldNameAndJsonValuesHolder<ProductsToPickRow> values;

 private  ImmutableMap<String,ViewEditorRenderMode> viewEditorRenderModeByFieldName;


public boolean isEligibleForPicking(){
    return isEligibleForChangingPickStatus() && !isApproved() && pickStatus != null && pickStatus.isEligibleForPicking();
}


public boolean isQtyOverrideEditableByUser(){
    return isFieldEditable(FIELD_QtyOverride);
}


public ProductsToPickRow withRowType(ProductsToPickRowType rowType){
    return Objects.equals(this.rowType, rowType) ? this : toBuilder().rowType(rowType).build();
}


public boolean isEligibleForChangingPickStatus(){
    return !isProcessed() && getType().isPickable();
}


@Override
public DocumentId getId(){
    return rowId.toDocumentId();
}


@Override
public ImmutableSet<String> getFieldNames(){
    return values.getFieldNames();
}


public ProductId getProductId(){
    return rowId.getProductId();
}


public ProductsToPickRow cast(IViewRow row){
    return (ProductsToPickRow) row;
}


public ProductsToPickRow withQtyOverride(Quantity qtyOverride){
    return Objects.equals(this.qtyOverride, qtyOverride) ? this : toBuilder().qtyOverride(qtyOverride).build();
}


public String getLocatorName(){
    return locator != null ? locator.getDisplayName() : "";
}


@Override
public ImmutableMap<String,ViewEditorRenderMode> getViewEditorRenderModeByFieldName(){
    return viewEditorRenderModeByFieldName;
}


public boolean isEligibleForPacking(){
    return isEligibleForChangingPickStatus() && isApproved() && pickStatus != null && pickStatus.isEligibleForPacking();
}


public ProductsToPickRow withQty(Quantity qty){
    return Objects.equals(this.qty, qty) ? this : toBuilder().qty(qty).qtyOverride(null).build();
}


public boolean isEligibleForProcessing(){
    return isEligibleForChangingPickStatus() && pickStatus != null && pickStatus.isEligibleForProcessing();
}


public Quantity getQtyEffective(){
    return CoalesceUtil.coalesce(qtyOverride, qty);
}


public boolean isApproved(){
    return approvalStatus != null && approvalStatus.isApproved();
}


public ProductsToPickRow withFinishedGoodsQtyOverride(Quantity finishedGoodsQty,Quantity finishedGoodsQtyOverride){
    if (finishedGoodsQtyOverride != null) {
        // just to make sure
        Quantity.getCommonUomIdOfAll(finishedGoodsQty, finishedGoodsQtyOverride);
        // qty ............... finishedGoodsQty
        // qtyOverride ....... finishedGoodsQtyOverride
        // => qtyOverride = qty * (finishedGoodsQtyOverride / finishedGoodsQty)
        final BigDecimal multiplier = finishedGoodsQtyOverride.toBigDecimal().divide(finishedGoodsQty.toBigDecimal(), 12, RoundingMode.HALF_UP);
        final Quantity qtyOverride = qty.multiply(multiplier).roundToUOMPrecision();
        return withQtyOverride(qtyOverride);
    } else {
        return withQtyOverride((Quantity) null);
    }
}


public boolean isEligibleForReview(){
    return isEligibleForChangingPickStatus() && pickStatus != null && pickStatus.isEligibleForReview();
}


@Override
public List<ProductsToPickRow> getIncludedRows(){
    return includedRows;
}


public PPOrderId getPickFromPickingOrderId(){
    return rowId.getPickFromPickingOrderId();
}


public boolean isFieldEditable(String fieldName){
    final ViewEditorRenderMode renderMode = getViewEditorRenderModeByFieldName().get(fieldName);
    return renderMode != null && renderMode.isEditable();
}


public ShipmentScheduleId getShipmentScheduleId(){
    return rowId.getShipmentScheduleId();
}


public ImmutableMap<String,ViewEditorRenderMode> buildViewEditorRenderModeByFieldName(ProductsToPickRowType rowType){
    final ImmutableMap.Builder<String, ViewEditorRenderMode> result = ImmutableMap.builder();
    if (rowType.isPickable()) {
        result.put(FIELD_QtyOverride, ViewEditorRenderMode.ALWAYS);
    } else {
        result.put(FIELD_QtyOverride, ViewEditorRenderMode.NEVER);
    }
    return result.build();
}


public ProductsToPickRow withUpdatesFromPickingCandidateIfNotNull(PickingCandidate pickingCandidate){
    return pickingCandidate != null ? withUpdatesFromPickingCandidate(pickingCandidate) : this;
}


@Override
public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues(){
    return values.get(this);
}


public HuId getPickFromHUId(){
    return rowId.getPickFromHUId();
}


@Override
public ProductsToPickRowType getType(){
    return rowType;
}


@Override
public DocumentPath getDocumentPath(){
    // TODO Auto-generated method stub
    return null;
}


@Override
public boolean isProcessed(){
    return processed || !rowType.isPickable();
}


public boolean isEligibleForRejectPicking(){
    return isEligibleForChangingPickStatus() && !isApproved() && pickStatus != null && pickStatus.isEligibleForRejectPicking();
}


public PPOrderBOMLineId getIssueToOrderBOMLineId(){
    final PPOrderBOMLineId issueToOrderBOMLineId = rowId.getIssueToOrderBOMLineId();
    if (issueToOrderBOMLineId == null) {
        throw new AdempiereException("Product " + productName.getDefaultValue() + " is not issueable");
    }
    return issueToOrderBOMLineId;
}


public ProductsToPickRow withUpdatesFromPickingCandidate(PickingCandidate pickingCandidate){
    return toBuilder().qtyReview(pickingCandidate.getQtyReview()).pickStatus(pickingCandidate.getPickStatus()).approvalStatus(pickingCandidate.getApprovalStatus()).processed(!pickingCandidate.isDraft()).pickingCandidateId(pickingCandidate.getId()).build();
}


}