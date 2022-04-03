package de.metas.ui.web.order.products_proposal.model;
 import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HUPIItemProductId;
import de.metas.i18n.ITranslatableString;
import de.metas.order.OrderLineId;
import de.metas.pricing.ProductPriceId;
import de.metas.product.ProductId;
import de.metas.ui.web.order.products_proposal.filters.ProductsProposalViewFilter;
import de.metas.ui.web.order.products_proposal.service.Order;
import de.metas.ui.web.order.products_proposal.service.OrderLine;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValues;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValuesHolder;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.ViewEditorRenderMode;
import de.metas.util.Check;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@ToString(exclude = { "values" })
public class ProductsProposalRow implements IViewRow{

 public  String FIELD_Product;

@ViewColumn(seqNo = 10, fieldName = FIELD_Product, captionKey = "M_Product_ID", widgetType = DocumentFieldWidgetType.Lookup)
@Getter
 private  LookupValue product;

 public  String FIELD_Qty;

@ViewColumn(seqNo = 20, fieldName = FIELD_Qty, captionKey = "Qty", widgetType = DocumentFieldWidgetType.Quantity, editor = ViewEditorRenderMode.ALWAYS)
@Getter
 private  BigDecimal qty;

 public  String FIELD_PackDescription;

@ViewColumn(seqNo = 30, fieldName = FIELD_PackDescription, widgetType = DocumentFieldWidgetType.Text)
@Getter
 private  ITranslatableString packingDescription;

 public  String FIELD_ASI;

@ViewColumn(seqNo = 40, fieldName = FIELD_ASI, captionKey = "M_AttributeSetInstance_ID", widgetType = DocumentFieldWidgetType.Text)
@Getter
 private  ProductASIDescription asiDescription;

 public  String FIELD_LastShipmentDays;

@ViewColumn(seqNo = 50, fieldName = FIELD_LastShipmentDays, captionKey = "LastShipmentDays", widgetType = DocumentFieldWidgetType.Integer)
@Getter
 private  Integer lastShipmentDays;

 public  String FIELD_Price;

@ViewColumn(seqNo = 60, fieldName = FIELD_Price, captionKey = "Price", widgetType = DocumentFieldWidgetType.Amount)
 private  BigDecimal userEnteredPrice;

 public  String FIELD_Currency;

@ViewColumn(seqNo = 61, fieldName = FIELD_Currency, captionKey = "C_Currency_ID", widgetType = DocumentFieldWidgetType.Text)
 private  String currencyCodeStr;

 public  String FIELD_IsCampaignPrice;

@ViewColumn(seqNo = 70, fieldName = FIELD_IsCampaignPrice, captionKey = "IsCampaignPrice", widgetType = DocumentFieldWidgetType.YesNo)
 private  boolean isCampaignPrice;

 public  String FIELD_BPartner;

@ViewColumn(displayed = false, fieldName = FIELD_BPartner, captionKey = "C_BPartner_ID", widgetType = DocumentFieldWidgetType.Lookup)
 private  LookupValue bpartner;

 public  String FIELD_LastSalesInvoiceDate;

@ViewColumn(displayed = false, fieldName = FIELD_LastSalesInvoiceDate, captionKey = "LastSalesInvoiceDate", widgetType = DocumentFieldWidgetType.LocalDate)
@Getter
 private  LocalDate lastSalesInvoiceDate;

 public  String FIELD_Description;

@ViewColumn(displayed = false, fieldName = FIELD_Description, captionKey = "Description", widgetType = DocumentFieldWidgetType.Text, editor = ViewEditorRenderMode.ALWAYS)
@Getter
 private  String description;

 private  DocumentId id;

@Getter
 private  int seqNo;

@Getter
 private  HUPIItemProductId packingMaterialId;

@Getter
 private  ProductPriceId productPriceId;

@Getter
 private  ProductPriceId copiedFromProductPriceId;

@Getter
 private  ProductProposalPrice price;

@Getter
 private  OrderLineId existingOrderLineId;

 private  ViewRowFieldNameAndJsonValuesHolder<ProductsProposalRow> values;

 private  ImmutableMap<String,ViewEditorRenderMode> EDITOR_RENDER_MODES;


public ProductsProposalRow withLastShipmentDays(Integer lastShipmentDays){
    if (Objects.equals(this.lastShipmentDays, lastShipmentDays)) {
        return this;
    } else {
        return toBuilder().lastShipmentDays(lastShipmentDays).build();
    }
}


@Override
public DocumentId getId(){
    return id;
}


public boolean isFieldEditable(String fieldName){
    final ViewEditorRenderMode renderMode = getViewEditorRenderModeByFieldName().get(fieldName);
    return renderMode != null ? renderMode.isEditable() : false;
}


public boolean isQtySet(){
    final BigDecimal qty = getQty();
    return qty != null && qty.signum() != 0;
}


public boolean isMatching(ProductsProposalViewFilter filter){
    if (!Check.isEmpty(filter.getProductName()) && !getProductName().toLowerCase().contains(filter.getProductName().toLowerCase())) {
        return false;
    }
    return true;
}


@Override
public ImmutableSet<String> getFieldNames(){
    return values.getFieldNames();
}


public String getProductName(){
    return getProduct().getDisplayName();
}


public ProductId getProductId(){
    return getProduct().getIdAs(ProductId::ofRepoId);
}


public ProductsProposalRow cast(IViewRow row){
    return (ProductsProposalRow) row;
}


public ProductsProposalRow withExistingOrderLine(Order order){
    if (order == null) {
        return this;
    }
    final OrderLine existingOrderLine = order.getFirstMatchingOrderLine(getProductId(), getPackingMaterialId()).orElse(null);
    if (existingOrderLine == null) {
        return this;
    }
    return toBuilder().qty(existingOrderLine.isPackingMaterialWithInfiniteCapacity() ? existingOrderLine.getQtyEnteredCU() : BigDecimal.valueOf(existingOrderLine.getQtyEnteredTU())).existingOrderLineId(existingOrderLine.getOrderLineId()).description(existingOrderLine.getDescription()).build();
}


@Override
public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues(){
    return values.get(this);
}


@Override
public Map<String,ViewEditorRenderMode> getViewEditorRenderModeByFieldName(){
    return values.getViewEditorRenderModeByFieldName();
}


public boolean isChanged(){
    return getProductPriceId() == null || !getPrice().isPriceListPriceUsed();
}


@Override
public DocumentPath getDocumentPath(){
    return null;
}


@Override
public boolean isProcessed(){
    return false;
}


public boolean isPriceEditable(){
    return isFieldEditable(FIELD_Price);
}


}