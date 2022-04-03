package de.metas.ui.web.material.cockpit;
 import org.adempiere.model.InterfaceWrapperHelper.loadOutOfTrx;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Product;
import org.compiere.model.I_M_Product_Category;
import org.compiere.model.I_S_Resource;
import org.compiere.util.Env;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import java.util.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.dimension.DimensionSpecGroup;
import de.metas.i18n.IMsgBL;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import de.metas.material.cockpit.model.I_MD_Stock;
import de.metas.product.IProductDAO;
import de.metas.product.ProductCategoryId;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.IViewRowType;
import de.metas.ui.web.view.ViewRow.DefaultRowType;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValues;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValuesHolder;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.ViewColumnLayout;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.ViewColumnLayout.Displayed;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.util.Check;
import de.metas.util.Services;
import de.metas.util.collections.CollectionUtils;
import de.metas.util.collections.ListUtils;
import de.metas.util.lang.CoalesceUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;
@ToString
@EqualsAndHashCode(of = "documentId")
public class MaterialCockpitRow implements IViewRow{

 public  String SYSCFG_PREFIX;

 private  String SEPARATOR;

 private  Joiner DOCUMENT_ID_JOINER;

 private  LocalDate date;

@Getter
 private  int productId;

@// 
ViewColumn(// 
widgetType = DocumentFieldWidgetType.Text, // 
captionKey = I_MD_Cockpit.COLUMNNAME_ProductValue, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 10) })
 private  String productValue;

@// 
ViewColumn(// 
widgetType = DocumentFieldWidgetType.Text, // 
captionKey = I_MD_Cockpit.COLUMNNAME_ProductName, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 20) })
 private  String productName;

@// 
ViewColumn(// 
widgetType = DocumentFieldWidgetType.Text, // 
captionKey = I_M_Product.COLUMNNAME_M_Product_Category_ID, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 30, displayed = Displayed.SYSCONFIG, displayedSysConfigPrefix = SYSCFG_PREFIX) })
@Getter
@VisibleForTesting
 private  String productCategoryOrSubRowName;

@Getter
 private  DimensionSpecGroup dimensionGroupOrNull;

 public  String FIELDNAME_Manufacturer_ID;

@// 
ViewColumn(// 
fieldName = FIELDNAME_Manufacturer_ID, // 
captionKey = FIELDNAME_Manufacturer_ID, // 
widgetType = DocumentFieldWidgetType.Lookup, layouts = { @// 
ViewColumnLayout(// 
when = JSONViewDataType.grid, // 
seqNo = 32, displayed = Displayed.SYSCONFIG, displayedSysConfigPrefix = SYSCFG_PREFIX) })
 private  Supplier<LookupValue> manufacturer;

 public  String FIELDNAME_PackageSize;

@// 
ViewColumn(// 
fieldName = FIELDNAME_PackageSize, // 
captionKey = FIELDNAME_PackageSize, // 
widgetType = DocumentFieldWidgetType.Text, layouts = { @// 
ViewColumnLayout(// 
when = JSONViewDataType.grid, // 
seqNo = 34, displayed = Displayed.SYSCONFIG, displayedSysConfigPrefix = SYSCFG_PREFIX) })
 private  String packageSize;

 public  String FIELDNAME_C_UOM_ID;

@// 
ViewColumn(// 
fieldName = FIELDNAME_C_UOM_ID, // 
captionKey = FIELDNAME_C_UOM_ID, // 
widgetType = DocumentFieldWidgetType.Lookup, layouts = { @// 
ViewColumnLayout(// 
when = JSONViewDataType.grid, // 
seqNo = 32, displayed = Displayed.SYSCONFIG, displayedSysConfigPrefix = SYSCFG_PREFIX) })
 private  Supplier<LookupValue> uom;

@// 
ViewColumn(// 
widgetType = DocumentFieldWidgetType.Quantity, // 
captionKey = I_MD_Cockpit.COLUMNNAME_PMM_QtyPromised_OnDate, layouts = { @// 
ViewColumnLayout(// 
when = JSONViewDataType.grid, // 
seqNo = 40, displayed = Displayed.SYSCONFIG, displayedSysConfigPrefix = SYSCFG_PREFIX) })
 private  BigDecimal pmmQtyPromised;

@// 
ViewColumn(// 
widgetType = DocumentFieldWidgetType.Quantity, // 
captionKey = I_MD_Cockpit.COLUMNNAME_QtyReserved_Sale, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 50) })
 private  BigDecimal qtyReservedSale;

@// 
ViewColumn(// 
widgetType = DocumentFieldWidgetType.Quantity, // 
captionKey = I_MD_Cockpit.COLUMNNAME_QtyReserved_Purchase, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 60) })
// note that we use the getter for testing
@Getter
 private  BigDecimal qtyReservedPurchase;

 public  String FIELDNAME_QtyMaterialentnahme;

@// 
ViewColumn(// 
fieldName = FIELDNAME_QtyMaterialentnahme, // 
captionKey = FIELDNAME_QtyMaterialentnahme, // 
widgetType = DocumentFieldWidgetType.Quantity, layouts = { @// 
ViewColumnLayout(// 
when = JSONViewDataType.grid, // 
seqNo = 70, displayed = Displayed.SYSCONFIG, displayedSysConfigPrefix = SYSCFG_PREFIX) })
 private  BigDecimal qtyMaterialentnahme;

 public  String FIELDNAME_QtyRequiredForProduction;

@// 
ViewColumn(// 
fieldName = FIELDNAME_QtyRequiredForProduction, // 
captionKey = FIELDNAME_QtyRequiredForProduction, // 
widgetType = DocumentFieldWidgetType.Quantity, layouts = { @// 
ViewColumnLayout(// 
when = JSONViewDataType.grid, // 
seqNo = 80, displayed = Displayed.SYSCONFIG, displayedSysConfigPrefix = SYSCFG_PREFIX) })
 private  BigDecimal qtyRequiredForProduction;

 public  String FIELDNAME_QtyOnHandEstimate;

@// 
ViewColumn(// 
fieldName = FIELDNAME_QtyOnHandEstimate, // 
captionKey = FIELDNAME_QtyOnHandEstimate, // 
widgetType = DocumentFieldWidgetType.Quantity, layouts = { @// 
ViewColumnLayout(// 
when = JSONViewDataType.grid, // 
seqNo = 90, displayed = Displayed.SYSCONFIG, displayedSysConfigPrefix = SYSCFG_PREFIX) })
 private  BigDecimal qtyOnHandEstimate;

 public  String FIELDNAME_QtyAvailableToPromiseEstimate;

@// 
ViewColumn(// 
fieldName = FIELDNAME_QtyAvailableToPromiseEstimate, // 
captionKey = FIELDNAME_QtyAvailableToPromiseEstimate, // 
widgetType = DocumentFieldWidgetType.Quantity, layouts = { @// 
ViewColumnLayout(// 
when = JSONViewDataType.grid, // 
seqNo = 100, displayed = Displayed.SYSCONFIG, displayedSysConfigPrefix = SYSCFG_PREFIX) })
 private  BigDecimal qtyAvailableToPromiseEstimate;

@// 
ViewColumn(// 
widgetType = DocumentFieldWidgetType.Quantity, // 
captionKey = I_MD_Stock.COLUMNNAME_QtyOnHand, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 110) })
// note that we use the getter for testing
@Getter
 private  BigDecimal qtyOnHandStock;

 private  DocumentId documentId;

 private  DocumentPath documentPath;

 private  List<MaterialCockpitRow> includedRows;

 private  IViewRowType rowType;

@Getter
 private  Set<Integer> allIncludedCockpitRecordIds;

@Getter
 private  Set<Integer> allIncludedStockRecordIds;

 private  ViewRowFieldNameAndJsonValuesHolder<MaterialCockpitRow> values;


public LocalDate extractDate(List<MaterialCockpitRow> includedRows){
    return CollectionUtils.extractSingleElement(includedRows, row -> row.date);
}


public MaterialCockpitRow cast(IViewRow row){
    return (MaterialCockpitRow) row;
}


public void assertNullOrCommonUomId(List<Quantity> quantitiesToVerify){
    final boolean notOK = CollectionUtils.hasDifferentValues(ListUtils.copyAndFilter(quantitiesToVerify, Objects::nonNull), Quantity::getUomId);
    Check.errorIf(notOK, "Some of the given quantities have different UOMs; quantities={}", quantitiesToVerify);
}


@Override
public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues(){
    return values.get(this);
}


@Override
public IViewRowType getType(){
    return rowType;
}


@Override
public DocumentPath getDocumentPath(){
    return documentPath;
}


@Override
public boolean isProcessed(){
    return false;
}


@Override
public DocumentId getId(){
    return documentId;
}


public int extractProductId(List<MaterialCockpitRow> includedRows){
    return CollectionUtils.extractSingleElement(includedRows, MaterialCockpitRow::getProductId);
}


@Override
public ImmutableSet<String> getFieldNames(){
    return values.getFieldNames();
}


@Override
public List<MaterialCockpitRow> getIncludedRows(){
    return includedRows;
}


}