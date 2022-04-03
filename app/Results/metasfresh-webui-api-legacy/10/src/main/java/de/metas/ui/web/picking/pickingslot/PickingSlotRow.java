package de.metas.ui.web.picking.pickingslot;
 import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.adempiere.warehouse.LocatorId;
import org.adempiere.warehouse.WarehouseId;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import de.metas.handlingunits.HuId;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.picking.api.PickingSlotId;
import de.metas.product.ProductId;
import de.metas.ui.web.handlingunits.HUEditorRowType;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import de.metas.ui.web.picking.PickingConstants;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValues;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValuesHolder;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.ViewColumnLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.MediaType;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class PickingSlotRow implements IViewRow{

 private  PickingSlotRowId pickingSlotRowId;

 private  PickingSlotRowType type;

 private  DocumentPath documentPath;

 private  boolean processed;

 private  ImmutableMap<PickingSlotRowId,PickingSlotRow> includedHURows;

 private  ViewRowFieldNameAndJsonValuesHolder<PickingSlotRow> values;

 private  ViewId includedViewId;

 private  LookupValue pickingSlotBPartner;

 private  LookupValue pickingSlotBPLocation;

 private  LookupValue pickingSlotWarehouse;

 private  LocatorId pickingSlotLocatorId;

 private  ITranslatableString pickingSlotCaption;

 private  boolean huTopLevel;

@ViewColumn(captionKey = "HUCode", widgetType = DocumentFieldWidgetType.Text, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 10), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 10) })
 private  String huCode;

@ViewColumn(captionKey = "M_Product_ID", widgetType = DocumentFieldWidgetType.Lookup, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 30), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 30) })
 private  JSONLookupValue huProduct;

@// 
ViewColumn(// 
captionKey = "M_HU_PI_Item_Product_ID", // 
widgetType = DocumentFieldWidgetType.Text, // 
restrictToMediaTypes = { MediaType.SCREEN }, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 40), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 40) })
 private  String huPackingInfo;

@ViewColumn(captionKey = "QtyCU", widgetType = DocumentFieldWidgetType.Quantity, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 50), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 50) })
 private  BigDecimal huQtyCU;


@Override
public boolean isSingleColumn(){
    return isPickingSlotRow();
}


public boolean isLU(){
    return isPickedHURow() && getType().isLU();
}


public boolean isTopLevelHU(){
    return huTopLevel;
}


public PickingSlotRowId getPickingSlotRowId(){
    return pickingSlotRowId;
}


public boolean isCUOrStorage(){
    return isPickedHURow() && getType().isCUOrStorage();
}


@Override
public DocumentId getId(){
    return pickingSlotRowId.toDocumentId();
}


public boolean isPickedHURow(){
    return pickingSlotRowId.isPickedHURow();
}


public BigDecimal getHuQtyCU(){
    return huQtyCU;
}


@Override
public ImmutableSet<String> getFieldNames(){
    return values.getFieldNames();
}


public PickingSlotRow cast(IViewRow row){
    return (PickingSlotRow) row;
}


public boolean isTU(){
    return isPickedHURow() && getType().isTU();
}


public Optional<PickingSlotRow> findIncludedRowById(PickingSlotRowId includedRowId){
    // This
    if (this.pickingSlotRowId.equals(includedRowId)) {
        return Optional.of(this);
    }
    // Direct children
    {
        final PickingSlotRow row = includedHURows.get(includedRowId);
        if (row != null) {
            return Optional.of(row);
        }
    }
    // Ask all included HU rows
    return includedHURows.values().stream().map(includedHURow -> includedHURow.findIncludedRowById(includedRowId).orElse(null)).filter(row -> row != null).findFirst();
}


@Override
public List<PickingSlotRow> getIncludedRows(){
    return ImmutableList.copyOf(includedHURows.values());
}


public WarehouseId getPickingSlotWarehouseId(){
    return pickingSlotWarehouse != null ? WarehouseId.ofRepoId(pickingSlotWarehouse.getIdAsInt()) : null;
}


public Stream<PickingSlotRow> streamIncludedRowsRecursivelly(){
    return includedHURows.values().stream().flatMap(PickingSlotRow::streamThisRowAndIncludedRowsRecursivelly);
}


@Override
public ViewId getIncludedViewId(){
    return includedViewId;
}


@Override
public ITranslatableString getSingleColumnCaption(){
    return pickingSlotCaption;
}


public Stream<PickingSlotRow> streamThisRowAndIncludedRowsRecursivelly(){
    final Stream<PickingSlotRow> thisRowStream = Stream.of(this);
    final Stream<PickingSlotRow> includedRowsStream = streamIncludedRowsRecursivelly();
    return Stream.concat(thisRowStream, includedRowsStream);
}


public PickingSlotId getPickingSlotId(){
    return getPickingSlotRowId().getPickingSlotId();
}


public boolean isCU(){
    return isPickedHURow() && getType().isCU();
}


public boolean isPickingSourceHURow(){
    return pickingSlotRowId.isPickingSourceHURow();
}


@Override
public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues(){
    return values.get(this);
}


@Override
public PickingSlotRowType getType(){
    return type;
}


@Override
public DocumentPath getDocumentPath(){
    return documentPath;
}


public ProductId getHuProductId(){
    return huProduct != null ? ProductId.ofRepoId(huProduct.getKeyAsInt()) : null;
}


@Override
public boolean isProcessed(){
    return processed;
}


public boolean isPickingSlotRow(){
    return pickingSlotRowId.isPickingSlotRow();
}


public int getBPartnerLocationId(){
    return pickingSlotBPLocation != null ? pickingSlotBPLocation.getIdAsInt() : -1;
}


public ITranslatableString buildPickingSlotCaption(String pickingSlotName,LookupValue pickingSlotBPartner,LookupValue pickingSlotBPLocation){
    return TranslatableStrings.join(" ", TranslatableStrings.constant(pickingSlotName), pickingSlotBPartner != null ? pickingSlotBPartner.getDisplayNameTrl() : TranslatableStrings.empty(), pickingSlotBPLocation != null ? pickingSlotBPLocation.getDisplayNameTrl() : TranslatableStrings.empty());
}


public LocatorId getPickingSlotLocatorId(){
    return pickingSlotLocatorId;
}


public HuId getHuId(){
    return pickingSlotRowId.getHuId();
}


public int getBPartnerId(){
    return pickingSlotBPartner != null ? pickingSlotBPartner.getIdAsInt() : -1;
}


}