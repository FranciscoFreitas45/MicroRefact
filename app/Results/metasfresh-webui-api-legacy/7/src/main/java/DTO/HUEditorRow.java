package DTO;
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
public class HUEditorRow implements IViewRow{

 private  String SYSCFG_PREFIX;

 private  DocumentPath documentPath;

 private  HUEditorRowId rowId;

 private  HUEditorRowType type;

 private  boolean topLevel;

 private  boolean processed;

 private  BPartnerId bpartnerId;

 public  String FIELDNAME_M_HU_ID;

 private  HuId huId;

 public  String FIELDNAME_HUCode;

 private  String code;

 public  String FIELDNAME_Locator;

 private  JSONLookupValue locator;

 public  String FIELDNAME_Product;

 private  JSONLookupValue product;

 public  String FIELDNAME_IsOwnPalette;

 private  Boolean isOwnPalette;

 public  String FIELDNAME_HU_UnitType;

 private  JSONLookupValue huUnitType;

 public  String FIELDNAME_PackingInfo;

 private  String packingInfo;

 public  String FIELDNAME_QtyCU;

 private  BigDecimal qtyCU;

 public  String FIELDNAME_UOM;

 private  JSONLookupValue uom;

 public  String FIELDNAME_HUStatus;

 private  JSONLookupValue huStatusDisplay;

 public  String FIELDNAME_IsReserved;

 private  boolean huReserved;

 private  String huStatus;

 public  String FIELDNAME_BestBeforeDate;

 private  LocalDate bestBeforeDate;

 private  Optional<HUEditorRowAttributesSupplier> attributesSupplier;

 private  List<HUEditorRow> includedRows;

 private  ImmutableMultimap<OrderLineId,HUEditorRow> includedOrderLineReservations;

 private  String _summary;

 private  ViewRowFieldNameAndJsonValuesHolder<HUEditorRow> values;

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

 private  HUEditorRow cuRow;

 private  HUEditorRow parentRow;

 private  HUEditorRow topLevelRow;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public Builder setProcessed(boolean processed){
    this.processed = processed;
    return this;
}


public ProductId getProductId(){
    final JSONLookupValue productLV = getProduct();
    return productLV != null ? ProductId.ofRepoId(productLV.getKeyAsInt()) : null;
}


public JSONLookupValue getHUStatusDisplay(){
    return huStatusDisplay;
}


public Builder setPackingInfo(String packingInfo){
    this.packingInfo = packingInfo;
    return this;
}


public LocalDate getBestBeforeDate(){
    return bestBeforeDate;
}


public I_M_HU getM_HU(){
    final HuId huId = getHuId();
    if (huId == null) {
        return null;
    }
    return Services.get(IHandlingUnitsDAO.class).getById(huId);
}


public JSONLookupValue getLocator(){
    return locator;
}


@Override
public List<HUEditorRow> getIncludedRows(){
    return includedRows;
}


public HUToReport getAsHUToReport(){
    final HUToReport huToReport = getAsHUToReportOrNull();
    if (huToReport == null) {
        throw new AdempiereException("Cannot convert " + this + " to " + HUToReport.class);
    }
    return huToReport;
}


@Override
public HUEditorRowAttributes getAttributes(){
    if (!attributesSupplier.isPresent()) {
        throw new EntityNotFoundException("row does not support attributes");
    }
    final HUEditorRowAttributes attributes = attributesSupplier.get().get();
    if (attributes == null) {
        throw new EntityNotFoundException("row does not support attributes");
    }
    return attributes;
}


public HUToReport getAsHUToReportOrNull(){
    // allow reports for all types ; see task https://github.com/metasfresh/metasfresh/issues/5540
    return HUEditorRowAsHUToReport.of(this);
}


public Builder setCode(String code){
    this.code = code;
    return this;
}


public boolean hasDirectChild(DocumentId childId){
    return getIncludedRows().stream().filter(row -> childId.equals(row.getId())).findAny().isPresent();
}


public boolean isHUStatusActive(){
    return X_M_HU.HUSTATUS_Active.equals(huStatus);
}


public LookupValue toLookupValue(){
    return IntegerLookupValue.of(HuId.toRepoId(getHuId()), getSummary());
}


public HUEditorRowAttributesProvider getAttributesProviderOrNull(){
    return attributesProvider;
}


public HUEditorRowType getType(){
    return Check.assumeNotNull(type, "Parameter type is not null");
}


public boolean isPureHU(){
    return getType().isPureHU();
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


public boolean matchesStringFilter(String stringFilter){
    if (Check.isEmpty(stringFilter, true)) {
        return true;
    }
    final String rowDisplayName = getSummary();
    final Function<String, String> normalizer = s -> StringUtils.stripDiacritics(s.trim()).toLowerCase();
    final String rowDisplayNameNorm = normalizer.apply(rowDisplayName);
    final String stringFilterNorm = normalizer.apply(stringFilter);
    return rowDisplayNameNorm.contains(stringFilterNorm);
}


public HUEditorRowId getRowId(){
    return Check.assumeNotNull(_rowId, "Parameter rowId is not null");
}


@Override
public DocumentId getId(){
    return getHURowId().toDocumentId();
}


public String getM_Product_DisplayName(){
    final JSONLookupValue productLV = getProduct();
    return productLV == null ? null : productLV.getCaption();
}


public String getHUStatus(){
    return huStatus;
}


@Override
public ImmutableSet<String> getFieldNames(){
    return values.getFieldNames();
}


public int getC_UOM_ID(){
    final JSONLookupValue uomLV = getUOM();
    return uomLV == null ? -1 : uomLV.getKeyAsInt();
}


public boolean isHUStatusPlanning(){
    return X_M_HU.HUSTATUS_Planning.equals(huStatus);
}


public JSONLookupValue getUOM(){
    return uom;
}


@Override
public boolean hasAttributes(){
    return attributesSupplier.isPresent();
}


public boolean isHUStatusDestroyed(){
    return X_M_HU.HUSTATUS_Destroyed.equals(huStatus);
}


public I_C_UOM getC_UOM(){
    final int uomId = getC_UOM_ID();
    if (uomId <= 0) {
        return null;
    }
    return InterfaceWrapperHelper.create(Env.getCtx(), uomId, I_C_UOM.class, ITrx.TRXNAME_None);
}


public Builder setProduct(JSONLookupValue product){
    this.product = product;
    return this;
}


public JSONLookupValue getProduct(){
    return product;
}


public String getSummary(){
    if (_summary == null) {
        _summary = buildSummary();
    }
    return _summary;
}


public boolean isTopLevel(){
    return Check.assumeNotNull(topLevel, "Parameter topLevel is not null");
}


public HUEditorRowId getHURowId(){
    return rowId;
}


public Builder addIncludedRow(HUEditorRow includedRow){
    if (includedRows == null) {
        includedRows = new ArrayList<>();
    }
    includedRows.add(includedRow);
    return this;
}


public Optional<HUEditorRowAttributesSupplier> getAttributesSupplier(){
    return attributesSupplier;
}


public Optional<HUEditorRow> getIncludedRowById(DocumentId rowId){
    return streamRecursive().filter(row -> rowId.equals(row.getId())).map(HUEditorRow::cast).findFirst();
}


public String getValue(){
    return code;
}


@Override
public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues(){
    return values.get(this);
}


public Builder setRowId(HUEditorRowId rowId){
    _rowId = rowId;
    return this;
}


public DocumentPath getDocumentPath(){
    final HUEditorRowId rowId = getRowId();
    return DocumentPath.rootDocumentPath(windowId, rowId.getHuId());
}


public Builder setQtyCU(BigDecimal qtyCU){
    this.qtyCU = qtyCU;
    return this;
}


public HuId getHuId(){
    return huId;
}


public BigDecimal getQtyCU(){
    return qtyCU;
}


public BPartnerId getBPartnerId(){
    return bpartnerId;
}


public String getPackingInfo(){
    return packingInfo;
}


public boolean isCU(){
    return getType().isCU();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isCU"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isTU(){
    return getType() == HUEditorRowType.TU;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isTU"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isLU(){
    return getType() == HUEditorRowType.LU;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isLU"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isHUPlanningReceiptOwnerPM(){
    // TODO: cache it or better it shall be provided when the row is created
    final I_M_HU hu = getM_HU();
    if (hu == null) {
        return false;
    }
    return hu.isHUPlanningReceiptOwnerPM();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isHUPlanningReceiptOwnerPM"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}