package de.metas.ui.web.pporder;
 import java.math.BigDecimal;
import java.util.List;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import org.compiere.model.I_C_UOM;
import org.eevolution.model.I_PP_Order;
import org.eevolution.model.I_PP_Order_BOMLine;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.model.I_PP_Order_Qty;
import de.metas.handlingunits.model.X_M_HU;
import de.metas.material.planning.pporder.PPOrderBOMLineId;
import de.metas.material.planning.pporder.PPOrderId;
import de.metas.product.IProductDAO;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.IViewRowAttributes;
import de.metas.ui.web.view.IViewRowAttributesProvider;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValues;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValuesHolder;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.ViewColumnLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.uom.IUOMDAO;
import de.metas.util.Services;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class PPOrderLineRow implements IViewRow{

 private  DocumentPath documentPath;

@Getter
 private  PPOrderLineRowId rowId;

@Nullable
 private  Supplier<? extends IViewRowAttributes> attributesSupplier;

 private  List<PPOrderLineRow> includedDocuments;

 private  boolean processed;

 private  PPOrderId ppOrderId;

 private  PPOrderBOMLineId ppOrderBOMLineId;

 private  int ppOrderQtyId;

 private  HuId huId;

 private  boolean sourceHU;

 private  boolean topLevelHU;

@Nullable
 private  String issueMethod;

@ViewColumn(captionKey = "M_Product_ID", widgetType = DocumentFieldWidgetType.Lookup, layouts = @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 10))
 private  JSONLookupValue product;

@ViewColumn(captionKey = "Code", widgetType = DocumentFieldWidgetType.Text, layouts = @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 20))
 private  String code;

@ViewColumn(captionKey = "Type", widgetType = DocumentFieldWidgetType.Text, layouts = @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 30))
 private  PPOrderLineType type;

@ViewColumn(captionKey = "PackingInfo", widgetType = DocumentFieldWidgetType.Text, layouts = @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 40))
 private  String packingInfo;

@ViewColumn(captionKey = "QtyPlan", widgetType = DocumentFieldWidgetType.Quantity, layouts = @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 50))
 private  BigDecimal qtyPlan;

@ViewColumn(captionKey = "Qty", widgetType = DocumentFieldWidgetType.Quantity, layouts = @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 60))
 private  BigDecimal qty;

@ViewColumn(captionKey = "C_UOM_ID", widgetType = DocumentFieldWidgetType.Lookup, layouts = @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 70))
 private  JSONLookupValue uom;

@ViewColumn(captionKey = "HUStatus", widgetType = DocumentFieldWidgetType.Lookup, layouts = @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 80))
 private  JSONLookupValue huStatus;

 private  ViewRowFieldNameAndJsonValuesHolder<PPOrderLineRow> values;


public boolean isTopLevelHU(){
    return topLevelHU;
}


public BigDecimal getQty(){
    return qty;
}


public PPOrderId getOrderId(){
    return ppOrderId;
}


@Override
public DocumentId getId(){
    return rowId.toDocumentId();
}


public DocumentPath computeDocumentPath(){
    if (type == PPOrderLineType.MainProduct) {
        return DocumentPath.rootDocumentPath(PPOrderConstants.AD_WINDOW_ID_PP_Order, DocumentId.of(ppOrderId));
    } else if (type.isBOMLine()) {
        return DocumentPath.includedDocumentPath(PPOrderConstants.AD_WINDOW_ID_PP_Order, DocumentId.of(ppOrderId), PPOrderConstants.TABID_ID_PP_Order_BOMLine, DocumentId.of(ppOrderBOMLineId));
    } else if (type.isHUOrHUStorage()) {
        // Better return null because we don't want to have here all processes which are related to HUs.
        // More, in case the HU is destroyed, that HU will not be found in the standard HU Editor View so no process will be executed.
        // see https://github.com/metasfresh/metasfresh-webui-api/issues/1097#issuecomment-436944470, problem 2.
        // return DocumentPath.rootDocumentPath(WEBUI_HU_Constants.WEBUI_HU_Window_ID, DocumentId.of(huId));
        return null;
    } else {
        throw new IllegalStateException("Unknown type: " + type);
    }
}


@Override
public ImmutableSet<String> getFieldNames(){
    return values.getFieldNames();
}


public ProductId getProductId(){
    final JSONLookupValue product = getProduct();
    return product != null ? ProductId.ofRepoIdOrNull(product.getKeyAsInt()) : null;
}


public PPOrderLineRow cast(IViewRow viewRecord){
    return (PPOrderLineRow) viewRecord;
}


public I_C_UOM getUom(){
    final int uomId = getUomId();
    return Services.get(IUOMDAO.class).getById(uomId);
}


@Override
public boolean hasAttributes(){
    return attributesSupplier != null;
}


public int getUomId(){
    return uom == null ? -1 : uom.getKeyAsInt();
}


public boolean isReceipt(){
    return getType().canReceive();
}


@Override
public List<PPOrderLineRow> getIncludedRows(){
    return includedDocuments;
}


public JSONLookupValue getProduct(){
    return product;
}


@Override
public IViewRowAttributes getAttributes(){
    if (attributesSupplier == null) {
        throw new EntityNotFoundException("This PPOrderLineRow does not support attributes; this=" + this);
    }
    final IViewRowAttributes attributes = attributesSupplier.get();
    if (attributes == null) {
        throw new EntityNotFoundException("This PPOrderLineRow does not support attributes; this=" + this);
    }
    return attributes;
}


public BigDecimal getQtyPlan(){
    return qtyPlan;
}


@Nullable
public Supplier<IViewRowAttributes> createASIAttributesSupplier(IViewRowAttributesProvider asiAttributesProvider,DocumentId documentId,int asiId){
    if (asiId > 0) {
        return () -> asiAttributesProvider.getAttributes(documentId, DocumentId.of(asiId));
    } else {
        return null;
    }
}


public boolean isIssue(){
    return getType().canIssue();
}


public PPOrderBOMLineId getOrderBOMLineId(){
    return ppOrderBOMLineId;
}


public String getIssueMethod(){
    return issueMethod;
}


public int getPP_Order_Qty_ID(){
    return ppOrderQtyId;
}


public boolean isHUStatusActive(){
    return huStatus != null && X_M_HU.HUSTATUS_Active.equals(huStatus.getKey());
}


@Override
public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues(){
    return values.get(this);
}


@Override
public PPOrderLineType getType(){
    return type;
}


@Override
public DocumentPath getDocumentPath(){
    return documentPath;
}


@Override
public boolean isProcessed(){
    return processed;
}


public boolean isSourceHU(){
    return sourceHU;
}


public HuId getHuId(){
    return huId;
}


public String getPackingInfo(){
    return packingInfo;
}


}