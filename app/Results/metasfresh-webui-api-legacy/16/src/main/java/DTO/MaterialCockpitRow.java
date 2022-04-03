package DTO;
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
public class MaterialCockpitRow implements IViewRow{

 public  String SYSCFG_PREFIX;

 private  String SEPARATOR;

 private  Joiner DOCUMENT_ID_JOINER;

 private  LocalDate date;

 private  int productId;

 private  String productValue;

 private  String productName;

 private  String productCategoryOrSubRowName;

 private  DimensionSpecGroup dimensionGroupOrNull;

 public  String FIELDNAME_Manufacturer_ID;

 private  Supplier<LookupValue> manufacturer;

 public  String FIELDNAME_PackageSize;

 private  String packageSize;

 public  String FIELDNAME_C_UOM_ID;

 private  Supplier<LookupValue> uom;

 private  BigDecimal pmmQtyPromised;

 private  BigDecimal qtyReservedSale;

 private  BigDecimal qtyReservedPurchase;

 public  String FIELDNAME_QtyMaterialentnahme;

 private  BigDecimal qtyMaterialentnahme;

 public  String FIELDNAME_QtyRequiredForProduction;

 private  BigDecimal qtyRequiredForProduction;

 public  String FIELDNAME_QtyOnHandEstimate;

 private  BigDecimal qtyOnHandEstimate;

 public  String FIELDNAME_QtyAvailableToPromiseEstimate;

 private  BigDecimal qtyAvailableToPromiseEstimate;

 private  BigDecimal qtyOnHandStock;

 private  DocumentId documentId;

 private  DocumentPath documentPath;

 private  List<MaterialCockpitRow> includedRows;

 private  IViewRowType rowType;

 private  Set<Integer> allIncludedCockpitRecordIds;

 private  Set<Integer> allIncludedStockRecordIds;

 private  ViewRowFieldNameAndJsonValuesHolder<MaterialCockpitRow> values;


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
public DocumentId getId(){
    return documentId;
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