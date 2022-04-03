package de.metas.ui.web.handlingunits.HUEditorRow;
 import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import de.metas.bpartner.BPartnerId;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.X_M_HU;
import de.metas.handlingunits.report.HUToReport;
import de.metas.handlingunits.storage.IHUProductStorage;
import de.metas.order.OrderLineId;
import de.metas.product.ProductId;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.handlingunits.report.HUEditorRowAsHUToReport;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValues;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValuesHolder;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.ViewColumnLayout;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.ViewColumnLayout.Displayed;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.MediaType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.WidgetSize;
import de.metas.util.Check;
import de.metas.util.Services;
import de.metas.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Product;
import org.compiere.util.Env;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
public class Builder {

 private  WindowId windowId;

 private  HUEditorRowId _rowId;

 private  Boolean topLevel;

 private  HUEditorRowType type;

 private  Boolean processed;

 private  String code;

 private  JSONLookupValue huUnitType;

 private  String huStatus;

 private  boolean huReserved;

 private  JSONLookupValue huStatusDisplay;

 private  String packingInfo;

 private  JSONLookupValue product;

 private  Boolean isOwnPalette;

 private  JSONLookupValue uom;

 private  BigDecimal qtyCU;

 private  LocalDate bestBeforeDate;

 private  JSONLookupValue locator;

 private  BPartnerId bpartnerId;

 private  List<HUEditorRow> includedRows;

 private  OrderLineId orderLineReservation;

 private  HUEditorRowAttributesProvider attributesProvider;


public HUEditorRowId getRowId(){
    return Check.assumeNotNull(_rowId, "Parameter rowId is not null");
}


public Builder setIsOwnPalette(Boolean isOwnPalette){
    this.isOwnPalette = isOwnPalette;
    return this;
}


public Builder setProcessed(boolean processed){
    this.processed = processed;
    return this;
}


public Builder setPackingInfo(String packingInfo){
    this.packingInfo = packingInfo;
    return this;
}


public Builder setUOM(JSONLookupValue uom){
    this.uom = uom;
    return this;
}


public Builder setBestBeforeDate(LocalDate bestBeforeDate){
    this.bestBeforeDate = bestBeforeDate;
    return this;
}


public LocalDate getBestBeforeDate(){
    return bestBeforeDate;
}


public Builder setAttributesProvider(HUEditorRowAttributesProvider attributesProvider){
    this.attributesProvider = attributesProvider;
    return this;
}


public JSONLookupValue getLocator(){
    return locator;
}


public Builder setTopLevel(boolean topLevel){
    this.topLevel = topLevel;
    return this;
}


public Builder setProduct(JSONLookupValue product){
    this.product = product;
    return this;
}


public Builder setReservedForOrderLine(OrderLineId orderLineId){
    orderLineReservation = orderLineId;
    huReserved = orderLineId != null;
    return this;
}


public Builder setCode(String code){
    this.code = code;
    return this;
}


public boolean isTopLevel(){
    return Check.assumeNotNull(topLevel, "Parameter topLevel is not null");
}


public Builder setType(HUEditorRowType type){
    this.type = type;
    return this;
}


public Builder setBPartnerId(BPartnerId bpartnerId){
    this.bpartnerId = bpartnerId;
    return this;
}


public Builder addIncludedRow(HUEditorRow includedRow){
    if (includedRows == null) {
        includedRows = new ArrayList<>();
    }
    includedRows.add(includedRow);
    return this;
}


public Builder setHUStatus(String huStatus){
    this.huStatus = Check.assumeNotEmpty(huStatus, "Parameter huStatus may not be empty");
    return this;
}


public Builder setHUUnitType(JSONLookupValue huUnitType){
    this.huUnitType = huUnitType;
    return this;
}


public HUEditorRowAttributesProvider getAttributesProviderOrNull(){
    return attributesProvider;
}


public HUEditorRow build(){
    return new HUEditorRow(this);
}


public Builder setRowId(HUEditorRowId rowId){
    _rowId = rowId;
    return this;
}


public HUEditorRowType getType(){
    return Check.assumeNotNull(type, "Parameter type is not null");
}


public Builder setHUStatusDisplay(JSONLookupValue huStatusDisplay){
    this.huStatusDisplay = Check.assumeNotNull(huStatusDisplay, "Parameter huStatusDisplay may not be null");
    return this;
}


public DocumentPath getDocumentPath(){
    final HUEditorRowId rowId = getRowId();
    return DocumentPath.rootDocumentPath(windowId, rowId.getHuId());
}


public boolean isProcessed(){
    if (processed == null) {
        // NOTE: don't take the "Processed" field if any, because in frontend we will end up with a lot of grayed out completed sales orders, for example.
        // return DisplayType.toBoolean(values.getOrDefault("Processed", false));
        return false;
    } else {
        return processed.booleanValue();
    }
}


public Builder setQtyCU(BigDecimal qtyCU){
    this.qtyCU = qtyCU;
    return this;
}


public Builder setLocator(JSONLookupValue locator){
    this.locator = locator;
    return this;
}


public ImmutableMultimap<OrderLineId,HUEditorRow> prepareIncludedOrderLineReservations(HUEditorRow currentRow){
    final ImmutableMultimap.Builder<OrderLineId, HUEditorRow> includedOrderLineReservationsBuilder = ImmutableMultimap.builder();
    for (final HUEditorRow includedRow : buildIncludedRows()) {
        includedOrderLineReservationsBuilder.putAll(includedRow.getIncludedOrderLineReservations());
    }
    if (orderLineReservation != null) {
        includedOrderLineReservationsBuilder.put(orderLineReservation, currentRow);
    }
    return includedOrderLineReservationsBuilder.build();
}


public BPartnerId getBPartnerId(){
    return bpartnerId;
}


public List<HUEditorRow> buildIncludedRows(){
    if (includedRows == null || includedRows.isEmpty()) {
        return ImmutableList.of();
    }
    return ImmutableList.copyOf(includedRows);
}


}