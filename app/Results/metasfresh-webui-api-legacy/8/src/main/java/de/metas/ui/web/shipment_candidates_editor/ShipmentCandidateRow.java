package de.metas.ui.web.shipment_candidates_editor;
 import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nullable;
import org.adempiere.mm.attributes.AttributeSetInstanceId;
import org.adempiere.mm.attributes.util.ASIEditingInfo;
import org.adempiere.mm.attributes.util.ASIEditingInfo.WindowType;
import com.google.common.collect.ImmutableMap;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.inoutcandidate.api.ShipmentScheduleUserChangeRequest;
import de.metas.inoutcandidate.api.ShipmentScheduleUserChangeRequest.ShipmentScheduleUserChangeRequestBuilder;
import de.metas.lang.SOTrx;
import de.metas.product.ProductId;
import de.metas.ui.web.pattribute.WebuiASIEditingInfo;
import de.metas.ui.web.pattribute.WebuiASIEditingInfoAware;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValues;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValuesHolder;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.ViewEditorRenderMode;
import de.metas.ui.web.window.descriptor.WidgetSize;
import lombok.Builder;
import lombok.NonNull;
public class ShipmentCandidateRow implements WebuiASIEditingInfoAware,IViewRow{

@ViewColumn(seqNo = 10, widgetType = DocumentFieldWidgetType.Lookup, widgetSize = WidgetSize.Small, captionKey = "C_OrderSO_ID")
 private  LookupValue salesOrder;

@ViewColumn(seqNo = 20, widgetType = DocumentFieldWidgetType.Lookup, widgetSize = WidgetSize.Small, captionKey = "C_BPartner_Customer_ID")
 private  LookupValue customer;

@ViewColumn(seqNo = 30, widgetType = DocumentFieldWidgetType.Lookup, widgetSize = WidgetSize.Small, captionKey = "M_Warehouse_ID")
 private  LookupValue warehouse;

@ViewColumn(seqNo = 40, widgetType = DocumentFieldWidgetType.Lookup, widgetSize = WidgetSize.ExtraLarge, captionKey = "M_Product_ID")
 private  LookupValue product;

 public  String FIELD_asi;

@ViewColumn(seqNo = 50, widgetType = DocumentFieldWidgetType.ProductAttributes, fieldName = FIELD_asi, captionKey = "M_AttributeSetInstance_ID", editor = ViewEditorRenderMode.ALWAYS)
 private  LookupValue asi;

@ViewColumn(seqNo = 60, widgetType = DocumentFieldWidgetType.Quantity, widgetSize = WidgetSize.Small, captionKey = "QtyOrdered")
 private  BigDecimal qtyOrdered;

 public  String FIELD_qtyToDeliverUserEntered;

@ViewColumn(seqNo = 70, widgetType = DocumentFieldWidgetType.Quantity, fieldName = FIELD_qtyToDeliverUserEntered, widgetSize = WidgetSize.Small, captionKey = "QtyToDeliver_Override", editor = ViewEditorRenderMode.ALWAYS)
 private  BigDecimal qtyToDeliverUserEntered;

@ViewColumn(seqNo = 80, widgetType = DocumentFieldWidgetType.Lookup, widgetSize = WidgetSize.Medium, captionKey = "PackDescription")
 private  String packingDescription;

 public  String FIELD_qtyToDeliverCatchOverride;

@ViewColumn(seqNo = 90, widgetType = DocumentFieldWidgetType.Quantity, fieldName = FIELD_qtyToDeliverCatchOverride, widgetSize = WidgetSize.Small, captionKey = "QtyToDeliverCatch_Override", editor = ViewEditorRenderMode.ALWAYS)
 private  BigDecimal qtyToDeliverCatchOverride;

@ViewColumn(seqNo = 100, widgetType = DocumentFieldWidgetType.Lookup, widgetSize = WidgetSize.Small, captionKey = "Catch_UOM_ID")
 private  LookupValue catchUOM;

@ViewColumn(seqNo = 110, widgetType = DocumentFieldWidgetType.ZonedDateTime, widgetSize = WidgetSize.Small, captionKey = "PreparationDate")
 private  ZonedDateTime preparationDate;

 private  boolean processed;

 private  ShipmentScheduleId shipmentScheduleId;

 private  DocumentId rowId;

 private  int salesOrderLineNo;

 private  ViewRowFieldNameAndJsonValuesHolder<ShipmentCandidateRow> values;

 private  ImmutableMap<String,ViewEditorRenderMode> fieldNameAndJsonValues;

 private  boolean catchWeight;

 private  PackingInfo packingInfo;

 private  BigDecimal qtyToDeliverUserEnteredInitial;

 private  BigDecimal qtyToDeliverCatchOverrideInitial;

 private  AttributeSetInstanceId asiIdInitial;


public boolean qtyToDeliverCatchOverrideIsChanged(){
    final Optional<Boolean> nullValuechanged = isNullValuesChanged(qtyToDeliverCatchOverrideInitial, qtyToDeliverCatchOverride);
    return nullValuechanged.orElseGet(() -> qtyToDeliverCatchOverrideInitial.compareTo(qtyToDeliverCatchOverride) != 0);
}


public Optional<Boolean> isNullValuesChanged(Object initial,Object current){
    final boolean wasNull = initial == null;
    final boolean isNull = current == null;
    if (wasNull) {
        if (isNull) {
            return Optional.of(false);
        }
        // was null and is not null anymore
        return Optional.of(true);
    }
    if (isNull) {
        // was not null and is now
        return Optional.of(true);
    }
    // was not null and still is not null; will need to compare the current values
    return Optional.empty();
}


public ImmutableMap<String,ViewEditorRenderMode> buildFieldNameAndJsonValues(boolean catchWeight){
    final ImmutableMap.Builder<String, ViewEditorRenderMode> result = ImmutableMap.builder();
    result.put(FIELD_qtyToDeliverCatchOverride, catchWeight ? ViewEditorRenderMode.ALWAYS : ViewEditorRenderMode.NEVER);
    return result.build();
}


@Override
public DocumentId getId(){
    return rowId;
}


@Override
public Set<String> getFieldNames(){
    return values.getFieldNames();
}


public int getSalesOrderLineNo(){
    return salesOrderLineNo;
}


public Optional<ShipmentScheduleUserChangeRequest> createShipmentScheduleUserChangeRequest(){
    final ShipmentScheduleUserChangeRequestBuilder builder = ShipmentScheduleUserChangeRequest.builder().shipmentScheduleId(shipmentScheduleId);
    boolean changes = false;
    if (qtyToDeliverUserEnteredInitial.compareTo(qtyToDeliverUserEntered) != 0) {
        BigDecimal qtyCUsToDeliver = packingInfo.computeQtyCUsByQtyUserEntered(qtyToDeliverUserEntered);
        builder.qtyToDeliverStockOverride(qtyCUsToDeliver);
        changes = true;
    }
    if (qtyToDeliverCatchOverrideIsChanged()) {
        builder.qtyToDeliverCatchOverride(qtyToDeliverCatchOverride);
        changes = true;
    }
    final AttributeSetInstanceId asiId = asi.getIdAs(AttributeSetInstanceId::ofRepoIdOrNone);
    if (!Objects.equals(asiIdInitial, asiId)) {
        builder.asiId(asiId);
        changes = true;
    }
    return changes ? Optional.of(builder.build()) : Optional.empty();
}


@Override
public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues(){
    return values.get(this);
}


@Override
public Map<String,ViewEditorRenderMode> getViewEditorRenderModeByFieldName(){
    return fieldNameAndJsonValues;
}


public ShipmentCandidateRow withChanges(ShipmentCandidateRowUserChangeRequest userChanges){
    final ShipmentCandidateRowBuilder rowBuilder = toBuilder();
    if (userChanges.getQtyToDeliverUserEntered() != null) {
        rowBuilder.qtyToDeliverUserEntered(userChanges.getQtyToDeliverUserEntered());
    }
    if (userChanges.getQtyToDeliverCatchOverride() != null) {
        rowBuilder.qtyToDeliverCatchOverride(userChanges.getQtyToDeliverCatchOverride());
    }
    if (userChanges.getAsi() != null) {
        rowBuilder.asi(userChanges.getAsi());
    }
    return rowBuilder.build();
}


@Override
public WebuiASIEditingInfo getWebuiASIEditingInfo(AttributeSetInstanceId asiId){
    final ProductId productId = product.getIdAs(ProductId::ofRepoIdOrNull);
    final ASIEditingInfo info = ASIEditingInfo.builder().type(WindowType.Regular).productId(productId).attributeSetInstanceId(asiId).callerTableName(null).callerColumnId(-1).soTrx(SOTrx.SALES).build();
    return WebuiASIEditingInfo.builder(info).build();
}


@Override
public DocumentPath getDocumentPath(){
    return null;
}


@Override
public boolean isProcessed(){
    return processed;
}


public String getSalesOrderDisplayNameOrEmpty(){
    return salesOrder != null ? salesOrder.getDisplayName() : "";
}


}