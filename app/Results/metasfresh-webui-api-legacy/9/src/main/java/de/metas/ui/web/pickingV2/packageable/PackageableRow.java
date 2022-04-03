package de.metas.ui.web.pickingV2.packageable;
 import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collection;
import org.adempiere.warehouse.WarehouseTypeId;
import org.compiere.util.TimeUtil;
import java.util.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.ITranslatableString;
import de.metas.inoutcandidate.api.Packageable;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.inoutcandidate.model.I_M_Packageable_V;
import de.metas.order.OrderId;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValues;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValuesHolder;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.user.UserId;
import de.metas.util.Check;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;
@ToString(exclude = "values")
public class PackageableRow implements IViewRow{

@ViewColumn(widgetType = DocumentFieldWidgetType.Text, captionKey = I_M_Packageable_V.COLUMNNAME_C_OrderSO_ID, seqNo = 10)
@Getter
 private  String orderDocumentNo;

@ViewColumn(widgetType = DocumentFieldWidgetType.Lookup, captionKey = I_M_Packageable_V.COLUMNNAME_C_BPartner_Customer_ID, seqNo = 20)
@Getter
 private  LookupValue customer;

@ViewColumn(widgetType = DocumentFieldWidgetType.Text, captionKey = I_M_Packageable_V.COLUMNNAME_M_Warehouse_Type_ID, seqNo = 30)
 private  ITranslatableString warehouseTypeName;

@ViewColumn(widgetType = DocumentFieldWidgetType.Integer, captionKey = "Lines", seqNo = 40)
 private  int lines;

@ViewColumn(widgetType = DocumentFieldWidgetType.Text, captionKey = "Picking_User_ID", seqNo = 50)
 private  LookupValue lockedByUser;

@ViewColumn(widgetType = DocumentFieldWidgetType.Lookup, captionKey = I_M_Packageable_V.COLUMNNAME_M_Shipper_ID, seqNo = 60)
 private  LookupValue shipper;

@ViewColumn(widgetType = DocumentFieldWidgetType.LocalDate, captionKey = I_M_Packageable_V.COLUMNNAME_DeliveryDate, seqNo = 70)
 private  LocalDate deliveryDate;

@ViewColumn(widgetType = DocumentFieldWidgetType.Text, captionKey = "LineNetAmt", seqNo = 80)
 private  ITranslatableString lineNetAmt;

@ViewColumn(widgetType = DocumentFieldWidgetType.ZonedDateTime, captionKey = I_M_Packageable_V.COLUMNNAME_PreparationDate, seqNo = 90)
@Getter
 private  ZonedDateTime preparationDate;

 private  ViewRowFieldNameAndJsonValuesHolder<PackageableRow> values;

 private  PackageableRowId rowId;

@Getter
 private  ImmutableList<Packageable> packageables;

@Getter
 private  ImmutableSet<ShipmentScheduleId> shipmentScheduleIds;


public PackageableRow cast(IViewRow row){
    return (PackageableRow) row;
}


@Override
public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues(){
    return values.get(this);
}


public ImmutableSet<ShipmentScheduleId> extractShipmentScheduleIds(Collection<Packageable> packageables){
    return packageables.stream().map(Packageable::getShipmentScheduleId).collect(ImmutableSet.toImmutableSet());
}


public boolean isNotLocked(){
    return !isLocked();
}


public boolean isLockedBy(UserId userId){
    return lockedByUser != null && UserId.equals(userId, lockedByUser.getIdAs(UserId::ofRepoId));
}


public LocalDate calculateEarliestDeliveryDate(Collection<Packageable> packageables){
    return packageables.stream().map(Packageable::getDeliveryDate).filter(Objects::nonNull).map(TimeUtil::asLocalDate).min(LocalDate::compareTo).orElse(null);
}


@Override
public DocumentPath getDocumentPath(){
    // TODO Auto-generated method stub
    return null;
}


public boolean isLocked(){
    return lockedByUser != null;
}


public ZonedDateTime calculateEarliestPreparationTime(Collection<Packageable> packageables){
    return packageables.stream().map(Packageable::getPreparationDate).filter(Objects::nonNull).min(ZonedDateTime::compareTo).orElse(null);
}


@Override
public boolean isProcessed(){
    return false;
}


@Override
public DocumentId getId(){
    return rowId.getDocumentId();
}


@Override
public ImmutableSet<String> getFieldNames(){
    return values.getFieldNames();
}


}