package DTO;
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
public class PackageableRow implements IViewRow{

 private  ViewId viewId;

 private  DocumentId id;

 private  DocumentPath documentPath;

 private  LookupValue order;

 private  LookupValue product;

 private  Quantity qtyOrdered;

 private  Quantity qtyPicked;

 private  LookupValue bpartner;

 private  ZonedDateTime preparationDate;

 private  ShipmentScheduleId shipmentScheduleId;

 private  Optional<OrderLineId> salesOrderLineId;

 private  ViewId includedViewId;

 private  ViewRowFieldNameAndJsonValuesHolder<PackageableRow> values;


@Override
public IViewRowAttributes getAttributes(){
    throw new EntityNotFoundException("Row does not support attributes");
}


@Override
public ViewId getIncludedViewId(){
    return includedViewId;
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