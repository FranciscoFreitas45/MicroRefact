package de.metas.ui.web.picking.packageable;
 import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import org.adempiere.util.lang.impl.TableRecordReference;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.inoutcandidate.model.I_M_Packageable_V;
import de.metas.order.OrderLineId;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.picking.PickingConstants;
import de.metas.ui.web.picking.pickingslot.PickingSlotViewsIndexStorage;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.IViewRowAttributes;
import de.metas.ui.web.view.IViewRowType;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewRow.DefaultRowType;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValues;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValuesHolder;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.ViewColumnLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
@ToString(exclude = "values")
public class PackageableRow implements IViewRow{

 private  ViewId viewId;

 private  DocumentId id;

 private  DocumentPath documentPath;

@ViewColumn(widgetType = DocumentFieldWidgetType.Lookup, captionKey = I_M_Packageable_V.COLUMNNAME_C_OrderSO_ID, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 10) })
 private  LookupValue order;

@ViewColumn(widgetType = DocumentFieldWidgetType.Lookup, captionKey = I_M_Packageable_V.COLUMNNAME_M_Product_ID, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 20) })
 private  LookupValue product;

@ViewColumn(widgetType = DocumentFieldWidgetType.Quantity, captionKey = I_M_Packageable_V.COLUMNNAME_QtyOrdered, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 30) })
 private  Quantity qtyOrdered;

@ViewColumn(widgetType = DocumentFieldWidgetType.Quantity, captionKey = "QtyPicked", layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 35) })
 private  Quantity qtyPicked;

@ViewColumn(widgetType = DocumentFieldWidgetType.Lookup, captionKey = I_M_Packageable_V.COLUMNNAME_C_BPartner_Customer_ID, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 40) })
 private  LookupValue bpartner;

@ViewColumn(widgetType = DocumentFieldWidgetType.ZonedDateTime, captionKey = I_M_Packageable_V.COLUMNNAME_PreparationDate, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 50) })
 private  ZonedDateTime preparationDate;

 private  ShipmentScheduleId shipmentScheduleId;

 private  Optional<OrderLineId> salesOrderLineId;

 private  ViewId includedViewId;

 private  ViewRowFieldNameAndJsonValuesHolder<PackageableRow> values;


public DocumentId createRowIdFromShipmentScheduleId(ShipmentScheduleId shipmentScheduleId){
    return DocumentId.of(shipmentScheduleId.getRepoId());
}


@Override
public IViewRowAttributes getAttributes(){
    throw new EntityNotFoundException("Row does not support attributes");
}


@Override
public ViewId getIncludedViewId(){
    return includedViewId;
}


public TableRecordReference createTableRecordReferenceFromShipmentScheduleId(ShipmentScheduleId shipmentScheduleId){
    return TableRecordReference.of(I_M_Packageable_V.Table_Name, shipmentScheduleId);
}


@Override
public DocumentId getId(){
    return id;
}


public TableRecordReference getTableRecordReference(){
    return createTableRecordReferenceFromShipmentScheduleId(getShipmentScheduleId());
}


public ShipmentScheduleId getShipmentScheduleId(){
    return shipmentScheduleId;
}


@Override
public ImmutableSet<String> getFieldNames(){
    return values.getFieldNames();
}


public ProductId getProductId(){
    return product != null ? ProductId.ofRepoIdOrNull(product.getIdAsInt()) : null;
}


public PackageableRow cast(IViewRow row){
    return (PackageableRow) row;
}


@Override
public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues(){
    return values.get(this);
}


@Override
public IViewRowType getType(){
    return DefaultRowType.Row;
}


@Override
public DocumentPath getDocumentPath(){
    return documentPath;
}


@Override
public boolean hasAttributes(){
    return false;
}


@Override
public boolean isProcessed(){
    return false;
}


public Optional<OrderLineId> getSalesOrderLineId(){
    return salesOrderLineId;
}


public Quantity getQtyOrderedWithoutPicked(){
    return qtyOrdered.subtract(qtyPicked).toZeroIfNegative();
}


@Override
public List<? extends IViewRow> getIncludedRows(){
    return ImmutableList.of();
}


}