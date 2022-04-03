package de.metas.ui.web.order.pricingconditions.view;
 import de.metas.util.lang.CoalesceUtil.coalesce;
import java.math.BigDecimal.ZERO;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_M_DiscountSchemaBreak;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.bpartner.BPartnerId;
import de.metas.interfaces.I_C_OrderLine;
import de.metas.money.CurrencyId;
import de.metas.money.Money;
import de.metas.pricing.conditions.PriceSpecification;
import de.metas.pricing.conditions.PriceSpecificationType;
import de.metas.pricing.conditions.PricingConditionsBreak;
import de.metas.pricing.conditions.PricingConditionsBreakId;
import de.metas.pricing.conditions.PricingConditionsId;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValues;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValuesHolder;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.ViewColumnLayout;
import de.metas.ui.web.view.descriptor.annotation.ViewColumnHelper;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.ViewEditorRenderMode;
import de.metas.util.lang.Percent;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@ToString(exclude = "values")
public class PricingConditionsRow implements IViewRow{

 public  String FIELDNAME_StatusColor;

@ViewColumn(fieldName = FIELDNAME_StatusColor, captionKey = " ", widgetType = DocumentFieldWidgetType.Color, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 1), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 1) })
 private  String statusColor;

@ViewColumn(captionKey = "C_BPartner_ID", widgetType = DocumentFieldWidgetType.Lookup, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 10), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 10) })
 private  LookupValue bpartner;

@ViewColumn(captionKey = "IsCustomer", widgetType = DocumentFieldWidgetType.YesNo, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 20), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 20) })
@Getter
 private  boolean customer;

@ViewColumn(captionKey = "M_Product_ID", widgetType = DocumentFieldWidgetType.Lookup, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 22), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 22) })
 private  LookupValue product;

@ViewColumn(captionKey = I_M_DiscountSchemaBreak.COLUMNNAME_BreakValue, widgetType = DocumentFieldWidgetType.Number, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 23), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 23) })
 private  BigDecimal breakValue;

 public  String FIELDNAME_BasePriceType;

@ViewColumn(fieldName = FIELDNAME_BasePriceType, captionKey = I_M_DiscountSchemaBreak.COLUMNNAME_PriceBase, widgetType = DocumentFieldWidgetType.List, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 25), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 25) })
 private  LookupValue basePriceType;

 public  String FIELDNAME_BasePricingSystem;

@ViewColumn(fieldName = FIELDNAME_BasePricingSystem, captionKey = I_M_DiscountSchemaBreak.COLUMNNAME_Base_PricingSystem_ID, widgetType = DocumentFieldWidgetType.Lookup, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 30), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 30) })
 private  LookupValue basePricingSystem;

 static  String FIELDNAME_BasePrice;

@ViewColumn(fieldName = FIELDNAME_BasePrice, captionKey = "PriceStd", widgetType = DocumentFieldWidgetType.CostPrice, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 40), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 40) })
@Getter
 private  BigDecimal basePriceAmt;

 static  String FIELDNAME_PricingSystemSurcharge;

@ViewColumn(fieldName = FIELDNAME_PricingSystemSurcharge, captionKey = I_M_DiscountSchemaBreak.COLUMNNAME_PricingSystemSurchargeAmt, widgetType = DocumentFieldWidgetType.CostPrice, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 45), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 45) })
 private  BigDecimal pricingSystemSurchargeAmt;

 static  String FIELDNAME_C_Currency_ID;

@ViewColumn(fieldName = FIELDNAME_C_Currency_ID, captionKey = "C_Currency_ID", widgetType = DocumentFieldWidgetType.Lookup, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 47), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 47) })
 private  LookupValue currency;

 static  String FIELDNAME_Discount;

@ViewColumn(fieldName = FIELDNAME_Discount, captionKey = "Discount", widgetType = DocumentFieldWidgetType.Number, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 50), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 50) })
 private  BigDecimal discount;

 public  String FIELDNAME_PaymentTerm;

@ViewColumn(fieldName = FIELDNAME_PaymentTerm, captionKey = "C_PaymentTerm_ID", widgetType = DocumentFieldWidgetType.Lookup, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 60), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 60) })
 private  LookupValue paymentTerm;

 public  String FIELDNAME_PaymentDiscount;

@ViewColumn(fieldName = FIELDNAME_PaymentDiscount, captionKey = "PaymentDiscount", widgetType = DocumentFieldWidgetType.Number, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 70), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 70) })
 private  BigDecimal paymentDiscountOverride;

@ViewColumn(captionKey = I_C_OrderLine.COLUMNNAME_PriceActual, widgetType = DocumentFieldWidgetType.Number, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 100), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 100) })
 private  BigDecimal netPrice;

@ViewColumn(captionKey = "LastInOutDate", widgetType = DocumentFieldWidgetType.LocalDate, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 130), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 130) })
 private  LocalDate dateLastInOut;

 private  String FIELDNAME_CreatedBy;

@ViewColumn(fieldName = FIELDNAME_CreatedBy, captionKey = "CreatedBy", widgetType = DocumentFieldWidgetType.Lookup, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 120), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 120) })
 private  LookupValue createdBy;

@ViewColumn(captionKey = "Created", widgetType = DocumentFieldWidgetType.Timestamp, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 110), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 110) })
 private  Instant dateCreated;

 private  PricingConditionsRowLookups lookups;

@Getter
 private  DocumentId id;

@Getter
 private  boolean editable;

@Getter
 private  PricingConditionsId pricingConditionsId;

@Getter
 private  PricingConditionsBreak pricingConditionsBreak;

 private  BasePricingSystemPriceCalculator basePricingSystemPriceCalculator;

@Getter
 private  PricingConditionsBreakId copiedFromPricingConditionsBreakId;

 private  ViewRowFieldNameAndJsonValuesHolder<PricingConditionsRow> values;


public BPartnerId getBpartnerId(){
    return BPartnerId.ofRepoId(bpartner.getIdAsInt());
}


public LookupValuesList getFieldDropdown(String fieldName){
    return lookups.getFieldDropdown(fieldName);
}


public void assertEditable(){
    if (!editable) {
        throw new AdempiereException("Row is not editable");
    }
}


public PricingConditionsRow copyAndChangeToEditable(){
    if (editable) {
        return this;
    }
    return toBuilder().editable(true).build();
}


@Override
public ImmutableSet<String> getFieldNames(){
    return values.getFieldNames();
}


@Override
public Map<String,DocumentFieldWidgetType> getWidgetTypesByFieldName(){
    return values.getWidgetTypesByFieldName();
}


public LookupValuesList getFieldTypeahead(String fieldName,String query){
    return lookups.getFieldTypeahead(fieldName, query);
}


public PricingConditionsRow cast(IViewRow row){
    return (PricingConditionsRow) row;
}


public ImmutableMap<String,ViewEditorRenderMode> buildViewEditorRenderModeByFieldName(boolean editable,PriceSpecificationType priceType,boolean temporaryPricingConditionsBreak){
    if (!editable) {
        return ImmutableMap.of();
    }
    final ImmutableMap.Builder<String, ViewEditorRenderMode> result = ImmutableMap.<String, ViewEditorRenderMode>builder().put(FIELDNAME_Discount, ViewEditorRenderMode.ALWAYS).put(FIELDNAME_PaymentTerm, ViewEditorRenderMode.ALWAYS).put(FIELDNAME_PaymentDiscount, ViewEditorRenderMode.ALWAYS);
    // 
    result.put(FIELDNAME_BasePriceType, ViewEditorRenderMode.ALWAYS);
    if (priceType == null || priceType == PriceSpecificationType.NONE) {
        result.put(FIELDNAME_BasePrice, ViewEditorRenderMode.NEVER).put(FIELDNAME_BasePricingSystem, ViewEditorRenderMode.NEVER).put(FIELDNAME_PricingSystemSurcharge, ViewEditorRenderMode.NEVER).put(FIELDNAME_C_Currency_ID, ViewEditorRenderMode.NEVER);
    } else if (priceType == PriceSpecificationType.FIXED_PRICE) {
        result.put(FIELDNAME_BasePrice, ViewEditorRenderMode.ALWAYS).put(FIELDNAME_BasePricingSystem, ViewEditorRenderMode.NEVER).put(FIELDNAME_PricingSystemSurcharge, ViewEditorRenderMode.NEVER).put(FIELDNAME_C_Currency_ID, ViewEditorRenderMode.ALWAYS);
    } else if (priceType == PriceSpecificationType.BASE_PRICING_SYSTEM) {
        result.put(FIELDNAME_BasePrice, ViewEditorRenderMode.NEVER).put(FIELDNAME_BasePricingSystem, ViewEditorRenderMode.ALWAYS).put(FIELDNAME_PricingSystemSurcharge, ViewEditorRenderMode.ALWAYS).put(FIELDNAME_C_Currency_ID, ViewEditorRenderMode.ALWAYS);
    }
    return result.build();
}


@Override
public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues(){
    return values.get(this);
}


@Override
public Map<String,ViewEditorRenderMode> getViewEditorRenderModeByFieldName(){
    return values.getViewEditorRenderModeByFieldName();
}


public CurrencyId getCurrencyId(){
    return CurrencyId.ofRepoId(currency.getIdAsInt());
}


@Override
public DocumentPath getDocumentPath(){
    // TODO i think we shall make this method not mandatory in interface
    return null;
}


@Override
public boolean isProcessed(){
    return !editable;
}


public String getBpartnerDisplayName(){
    return bpartner.getDisplayName();
}


public boolean isVendor(){
    return !isCustomer();
}


public DocumentId buildDocumentId(PricingConditionsBreak pricingConditionsBreak,LookupValue bpartner,boolean customer,boolean editableRow){
    final StringBuilder idStr = new StringBuilder();
    idStr.append(bpartner.getIdAsString());
    idStr.append("-").append(customer ? "C" : "V");
    if (editableRow) {
        idStr.append("-").append("editable");
    } else if (pricingConditionsBreak.getId() != null) {
        // In case the row is not editable, we shall also append the pricing conditions break ID to make it unique,
        // else would fail in case we are showing all pricing conditions, for all break levels for a given product.
        // (e.g. when we are opening it from material cockpit)
        final PricingConditionsBreakId pricingConditionsBreakId = pricingConditionsBreak.getId();
        idStr.append("-").append(pricingConditionsBreakId.getDiscountSchemaBreakId());
    }
    return DocumentId.ofString(idStr.toString());
}


public BigDecimal calculateNetPrice(){
    if (basePriceAmt == null) {
        return null;
    }
    final BigDecimal priceBeforeDiscount = basePriceAmt.add(coalesce(pricingSystemSurchargeAmt, ZERO));
    // TODO: hardcoded
    final int precision = 2;
    final BigDecimal priceAfterDiscount = pricingConditionsBreak.getDiscount().subtractFromBase(priceBeforeDiscount, precision);
    return priceAfterDiscount;
}


@Override
public List<? extends IViewRow> getIncludedRows(){
    return ImmutableList.of();
}


}