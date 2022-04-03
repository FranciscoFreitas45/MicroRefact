package de.metas.ui.web.handlingunits;
 import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.bpartner.BPartnerId;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHUQueryBuilder;
import de.metas.handlingunits.IHUStatusBL;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.exceptions.HUException;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_Locator;
import de.metas.handlingunits.model.I_M_Warehouse;
import de.metas.handlingunits.model.X_M_HU;
import de.metas.handlingunits.model.X_M_HU_PI_Version;
import de.metas.handlingunits.reservation.HUReservationService;
import de.metas.handlingunits.storage.IHUProductStorage;
import de.metas.handlingunits.storage.IHUStorage;
import de.metas.handlingunits.storage.IHUStorageFactory;
import de.metas.i18n.IMsgBL;
import de.metas.logging.LogManager;
import de.metas.order.OrderLineId;
import de.metas.product.IProductBL;
import de.metas.product.ProductId;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverters;
import de.metas.ui.web.handlingunits.HUIdsFilterHelper.HUIdsFilterData;
import de.metas.ui.web.handlingunits.util.HUPackingInfoFormatter;
import de.metas.ui.web.handlingunits.util.HUPackingInfos;
import de.metas.ui.web.view.SqlViewRowIdsOrderedSelectionFactory;
import de.metas.ui.web.view.ViewEvaluationCtx;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewRowIdsOrderedSelection;
import de.metas.ui.web.view.ViewRowIdsOrderedSelectionFactory;
import de.metas.ui.web.view.descriptor.SqlAndParams;
import de.metas.ui.web.view.descriptor.SqlViewBinding;
import de.metas.ui.web.view.descriptor.SqlViewKeyColumnNamesMap;
import de.metas.ui.web.view.descriptor.SqlViewRowIdsConverter;
import de.metas.ui.web.view.descriptor.SqlViewSelectData;
import de.metas.ui.web.view.descriptor.SqlViewSelectionQueryBuilder;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import de.metas.util.collections.PagedIterator.Page;
import lombok.Builder;
import lombok.NonNull;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.ad.service.IADReferenceDAO;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.exceptions.DBException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.model.PlainContextAware;
import org.compiere.model.I_C_UOM;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.slf4j.Logger;
import javax.annotation.Nullable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.adempiere.model.InterfaceWrapperHelper.loadOutOfTrx;
public class SqlHUEditorViewRepository implements HUEditorViewRepository{

 private  String MSG_HU_RESERVED;

 private  Logger logger;

 private  WindowId windowId;

 private  HUEditorRowAttributesProvider attributesProvider;

 private  HUEditorRowIsProcessedPredicate rowProcessedPredicate;

 private  HUReservationService huReservationService;

 private  boolean showBestBeforeDate;

 private  SqlViewBinding sqlViewBinding;

 private  ViewRowIdsOrderedSelectionFactory viewSelectionFactory;

 private  SqlViewSelectData sqlViewSelect;


public LocalDate extractBestBeforeDate(HUEditorRowAttributesProvider attributesProvider,HUEditorRowId rowId){
    if (attributesProvider == null) {
        return null;
    }
    final DocumentId attributesKey = attributesProvider.createAttributeKey(rowId.getHuId());
    final HUEditorRowAttributes attributes = attributesProvider.getAttributes(rowId.toDocumentId(), attributesKey);
    return attributes.getBestBeforeDate().orElse(null);
}


public JSONLookupValue createHUStatusDisplayLookupValue(I_M_HU hu){
    final IHUStatusBL huStatusBL = Services.get(IHUStatusBL.class);
    final String huStatusKey;
    final String huStatusDisplayName;
    if (// if e.g. a reserved HU was shipped, it shall be shown as "shipped" not "reserved"
    hu.isReserved() && huStatusBL.isPhysicalHU(hu)) {
        huStatusKey = MSG_HU_RESERVED;
        huStatusDisplayName = Services.get(IMsgBL.class).getMsg(Env.getCtx(), huStatusKey);
    } else {
        final IADReferenceDAO adReferenceDAO = Services.get(IADReferenceDAO.class);
        huStatusKey = hu.getHUStatus();
        huStatusDisplayName = adReferenceDAO.retrieveListNameTrl(X_M_HU.HUSTATUS_AD_Reference_ID, huStatusKey);
    }
    return JSONLookupValue.of(huStatusKey, huStatusDisplayName);
}


@Override
public List<HUEditorRow> retrieveHUEditorRows(Set<HuId> huIds,HUEditorRowFilter filter){
    huReservationService.warmup(huIds);
    final HuId topLevelHUId = null;
    return retrieveTopLevelHUs(huIds, filter).stream().map(hu -> createHUEditorRow(hu, topLevelHUId)).collect(GuavaCollectors.toImmutableList());
}


@Override
public boolean containsAnyOfRowIds(ViewRowIdsOrderedSelection selection,DocumentIdsSelection rowIds){
    return viewSelectionFactory.containsAnyOfRowIds(selection, rowIds);
}


public HUEditorRow createHUEditorRow(HuId parentHUId,HuId topLevelHUId,IHUProductStorage huStorage,boolean processed){
    // final Stopwatch stopwatch = Stopwatch.createStarted();
    final I_M_HU hu = huStorage.getM_HU();
    final HuId huId = HuId.ofRepoId(hu.getM_HU_ID());
    final ProductId productId = huStorage.getProductId();
    final HUEditorRowAttributesProvider attributesProviderEffective = !huId.equals(parentHUId) ? attributesProvider : null;
    final Optional<OrderLineId> reservedForOrderLineId = huReservationService.getOrderLineIdByReservedVhuId(huId);
    final HUEditorRow huEditorRow = HUEditorRow.builder(windowId).setRowId(HUEditorRowId.ofHUStorage(huId, topLevelHUId, productId)).setType(HUEditorRowType.HUStorage).setTopLevel(false).setProcessed(processed).setAttributesProvider(attributesProviderEffective).setHUUnitType(JSONLookupValue.of(X_M_HU_PI_Version.HU_UNITTYPE_VirtualPI, "CU")).setHUStatus(hu.getHUStatus()).setReservedForOrderLine(reservedForOrderLineId.orElse(null)).setHUStatusDisplay(createHUStatusDisplayLookupValue(hu)).setProduct(createProductLookupValue(productId)).setUOM(createUOMLookupValue(huStorage.getC_UOM())).setQtyCU(huStorage.getQty().toBigDecimal()).build();
    // System.out.println("createHUEditorRow: created " + huEditorRow + " (storage) in " + stopwatch);
    return huEditorRow;
}


@Override
public String buildSqlWhereClause(ViewRowIdsOrderedSelection selection,DocumentIdsSelection rowIds){
    return SqlViewSelectionQueryBuilder.prepareSqlWhereClause().sqlTableAlias(I_M_HU.Table_Name).keyColumnNamesMap(SqlViewKeyColumnNamesMap.ofIntKeyField(I_M_HU.COLUMNNAME_M_HU_ID)).selectionId(selection.getSelectionId()).rowIds(rowIds).rowIdsConverter(getRowIdsConverter()).build();
}


@Override
public HUEditorRow retrieveForHUId(HuId huId){
    if (huId == null) {
        return null;
    }
    // TODO: check if the huId is part of our collection
    final I_M_HU hu = Services.get(IHandlingUnitsDAO.class).getByIdOutOfTrx(huId);
    // assume given huId is a top level HU
    final HuId topLevelHUId = null;
    return createHUEditorRow(hu, topLevelHUId);
}


public IHUProductStorage getSingleProductStorage(I_M_HU hu){
    final IHUStorage huStorage = Services.get(IHandlingUnitsBL.class).getStorageFactory().getStorage(hu);
    final ProductId productId = huStorage.getSingleProductIdOrNull();
    if (productId == null) {
        return null;
    }
    final IHUProductStorage productStorage = huStorage.getProductStorage(productId);
    return productStorage;
}


public List<I_M_HU> retrieveTopLevelHUs(Collection<HuId> huIds,HUEditorRowFilter filter){
    if (huIds.isEmpty()) {
        return ImmutableList.of();
    }
    final IQueryBuilder<I_M_HU> queryBuilder = HUEditorRowFilters.toHUQueryBuilderPart(filter).setContext(PlainContextAware.newOutOfTrx()).setOnlyTopLevelHUs().setOnlyActiveHUs(// retrieve ALL HUs, see https://github.com/metasfresh/metasfresh-webui-api/issues/563
    false).createQueryBuilder();
    if (huIds != null && !huIds.isEmpty()) {
        queryBuilder.addInArrayFilter(I_M_HU.COLUMN_M_HU_ID, huIds);
    }
    return queryBuilder.create().list();
}


@Override
public Set<HuId> retrieveHUIdsEffective(HUIdsFilterData huIdsFilter,DocumentFilterList filters,SqlDocumentFilterConverterContext context){
    final ImmutableSet<HuId> onlyHUIds = extractHUIds(huIdsFilter);
    if (filters.isEmpty() && !huIdsFilter.hasInitialHUQuery() && onlyHUIds != null) {
        // shortcut: don't bother the DB but return the list of IDs that we already have
        return onlyHUIds;
    }
    // Create HU query
    IHUQueryBuilder huQuery = huIdsFilter.getInitialHUQueryOrNull();
    if (huQuery == null) {
        huQuery = Services.get(IHandlingUnitsDAO.class).createHUQueryBuilder();
    }
    huQuery.setContext(PlainContextAware.newOutOfTrx());
    // Only HUs
    if (onlyHUIds != null) {
        huQuery.addOnlyHUIds(HuId.toRepoIds(onlyHUIds));
    }
    // Exclude HUs
    huQuery.addHUIdsToExclude(HuId.toRepoIds(huIdsFilter.getShallNotHUIds()));
    // 
    // Convert the "filters" to SQL
    if (!filters.isEmpty()) {
        final SqlDocumentFilterConverter sqlFilterConverter = SqlDocumentFilterConverters.createEntityBindingEffectiveConverter(sqlViewBinding);
        huQuery.addFilter(sqlFilterConverter.createQueryFilter(filters, SqlOptions.usingTableAlias(sqlViewBinding.getTableAlias()), context));
    }
    final List<Integer> huRepoIds = huQuery.createQuery().listIds();
    return HuId.ofRepoIds(huRepoIds);
}


@Override
public ViewRowIdsOrderedSelection createSelectionFromSelection(ViewEvaluationCtx viewEvalCtx,ViewRowIdsOrderedSelection fromSelection,DocumentQueryOrderByList orderBys){
    return viewSelectionFactory.createOrderedSelectionFromSelection(viewEvalCtx, fromSelection, DocumentFilterList.EMPTY, orderBys, SqlDocumentFilterConverterContext.EMPTY);
}


public JSONLookupValue createProductLookupValue(ProductId productId){
    if (productId == null) {
        return null;
    }
    final String displayName = Services.get(IProductBL.class).getProductValueAndName(productId);
    return JSONLookupValue.of(productId.getRepoId(), displayName);
}


@Override
public SqlViewRowIdsConverter getRowIdsConverter(){
    return sqlViewBinding.getRowIdsConverter();
}


public String extractPackingInfo(I_M_HU hu,HUEditorRowType huUnitType){
    if (!huUnitType.isPureHU()) {
        return "";
    }
    if (huUnitType == HUEditorRowType.VHU) {
        return "";
    }
    try {
        return HUPackingInfoFormatter.newInstance().setShowLU(true).format(HUPackingInfos.of(hu));
    } catch (final Exception ex) {
        logger.warn("Failed extracting packing info for {}", hu, ex);
        return "?";
    }
}


public JSONLookupValue createUOMLookupValue(I_C_UOM uom){
    if (uom == null) {
        return null;
    }
    return JSONLookupValue.of(uom.getC_UOM_ID(), uom.getUOMSymbol());
}


@Override
public ViewRowIdsOrderedSelection createSelection(ViewEvaluationCtx viewEvalCtx,ViewId viewId,DocumentFilterList filters,DocumentQueryOrderByList orderBys,SqlDocumentFilterConverterContext filterConverterCtx){
    final boolean applySecurityRestrictions = true;
    return viewSelectionFactory.createOrderedSelection(viewEvalCtx, viewId, filters, orderBys, applySecurityRestrictions, filterConverterCtx);
}


@Override
public void invalidateCache(){
    if (attributesProvider != null) {
        attributesProvider.invalidateAll();
    }
}


public JSONLookupValue createLocatorLookupValue(int locatorId){
    if (locatorId <= 0) {
        return null;
    }
    final I_M_Locator locator = loadOutOfTrx(locatorId, I_M_Locator.class);
    if (locator == null) {
        return JSONLookupValue.unknown(locatorId);
    }
    final I_M_Warehouse warehouse = loadOutOfTrx(locator.getM_Warehouse_ID(), I_M_Warehouse.class);
    final String caption = Stream.of(warehouse.getName(), locator.getValue(), locator.getX(), locator.getX1(), locator.getY(), locator.getZ()).filter(part -> !Check.isEmpty(part, true)).map(String::trim).collect(Collectors.joining("_"));
    return JSONLookupValue.of(locatorId, caption);
}


@Override
public ViewRowIdsOrderedSelection removeRowIdsFromSelection(ViewRowIdsOrderedSelection selection,DocumentIdsSelection rowIdsToRemove){
    return viewSelectionFactory.removeRowIdsFromSelection(selection, rowIdsToRemove);
}


@Override
public ViewRowIdsOrderedSelection addRowIdsToSelection(ViewRowIdsOrderedSelection selection,DocumentIdsSelection rowIdsToAdd){
    return viewSelectionFactory.addRowIdsToSelection(selection, rowIdsToAdd);
}


@Override
public void deleteSelection(ViewRowIdsOrderedSelection selection){
    viewSelectionFactory.deleteSelection(selection.getSelectionId());
}


public ImmutableSet<HuId> extractHUIds(HUIdsFilterData huIdsFilter){
    final Set<HuId> initialHUIds = huIdsFilter.getInitialHUIds();
    final Set<HuId> huIdsToInclude = huIdsFilter.getMustHUIds();
    final Set<HuId> huIdsToExclude = huIdsFilter.getShallNotHUIds();
    if (initialHUIds == null && huIdsToInclude.isEmpty() && huIdsToExclude.isEmpty()) {
        // no restrictions
        return null;
    }
    final Set<HuId> initialHUIdsOrEmpty = initialHUIds != null ? initialHUIds : ImmutableSet.of();
    return Stream.concat(initialHUIdsOrEmpty.stream(), huIdsToInclude.stream()).filter(huId -> !huIdsToExclude.contains(huId)).distinct().collect(ImmutableSet.toImmutableSet());
}


@Override
public Page<HuId> retrieveHUIdsPage(ViewEvaluationCtx viewEvalCtx,ViewRowIdsOrderedSelection selection,int firstRow,int maxRows){
    final SqlAndParams sqlAndParams = sqlViewSelect.selectByPage().viewEvalCtx(viewEvalCtx).viewId(selection.getViewId()).firstRowZeroBased(firstRow).pageLength(maxRows).build();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
        pstmt = DB.prepareStatement(sqlAndParams.getSql(), ITrx.TRXNAME_ThreadInherited);
        pstmt.setMaxRows(maxRows);
        DB.setParameters(pstmt, sqlAndParams.getSqlParams());
        rs = pstmt.executeQuery();
        final Set<HuId> huIds = new LinkedHashSet<>();
        int lastRowMax = -1;
        while (rs.next()) {
            final int huId = rs.getInt(I_M_HU.COLUMNNAME_M_HU_ID);
            if (huId <= 0) {
                continue;
            }
            huIds.add(HuId.ofRepoId(huId));
            final int lastRow = rs.getInt(SqlViewSelectData.COLUMNNAME_Paging_SeqNo_OneBased);
            lastRowMax = Math.max(lastRowMax, lastRow);
        }
        if (huIds.isEmpty()) {
            // shall not happen
            return null;
        } else {
            final int lastRowZeroBased = lastRowMax - 1;
            return Page.ofRowsAndLastRowIndex(ImmutableList.copyOf(huIds), lastRowZeroBased);
        }
    } catch (final SQLException ex) {
        throw DBException.wrapIfNeeded(ex).setSqlIfAbsent(sqlAndParams.getSql(), sqlAndParams.getSqlParams());
    } finally {
        DB.close(rs, pstmt);
    }
}


@Override
public void warmUp(Set<HuId> huIds){
    // caches the given HUs with one SQL query
    InterfaceWrapperHelper.loadByRepoIdAwares(huIds, I_M_HU.class);
    huReservationService.warmup(huIds);
}


}