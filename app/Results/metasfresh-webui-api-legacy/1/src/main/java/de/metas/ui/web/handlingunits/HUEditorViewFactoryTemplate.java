package de.metas.ui.web.handlingunits;
 import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.ui.web.view.SqlViewFactory;
import org.adempiere.ad.dao.ConstantQueryFilter;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.ad.dao.ISqlQueryFilter;
import org.adempiere.ad.dao.impl.InArrayQueryFilter;
import org.adempiere.ad.window.api.IADWindowDAO;
import org.adempiere.model.PlainContextAware;
import org.adempiere.service.ISysConfigBL;
import org.compiere.model.I_AD_Tab;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import de.metas.cache.CCache;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.attribute.HUAttributeConstants;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.reservation.HUReservationService;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.logging.LogManager;
import de.metas.process.BarcodeScannerType;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.ImmutableDocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.document.filter.sql.SqlParamsCollector;
import de.metas.ui.web.handlingunits.SqlHUEditorViewRepository.SqlHUEditorViewRepositoryBuilder;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.SqlViewBinding;
import de.metas.ui.web.view.descriptor.SqlViewBindingFactory;
import de.metas.ui.web.view.descriptor.SqlViewRowFieldBinding;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import de.metas.ui.web.window.descriptor.factory.standard.LayoutFactory;
import de.metas.ui.web.window.descriptor.sql.SqlDocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlSelectValue;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import lombok.NonNull;
import lombok.Value;
public class HUEditorViewFactoryTemplate implements IViewFactory{

 private  Logger logger;

@Autowired
 private  DocumentDescriptorFactory documentDescriptorFactory;

@Autowired
 private  HUReservationService huReservationService;

 private  String SYSCFG_AlwaysUseSameLayout;

 private  ImmutableListMultimap<String,HUEditorViewCustomizer> viewCustomizersByReferencingTableName;

 private  ImmutableMap<String,HUEditorRowIsProcessedPredicate> rowProcessedPredicateByReferencingTableName;

 private  ImmutableMap<String,Boolean> rowAttributesAlwaysReadonlyByReferencingTableName;

 private  CCache<Integer,SqlViewBinding> sqlViewBindingCache;

 private  CCache<ViewLayoutKey,ViewLayout> layouts;

 private  String FILTER_ID;

 public  HUBarcodeSqlDocumentFilterConverter instance;

 private  String PARAM_Barcode;

@NonNull
 private WindowId windowId;

@NonNull
 private JSONViewDataType viewDataType;


public List<HUEditorViewCustomizer> getViewCustomizers(String referencingTableName){
    return viewCustomizersByReferencingTableName.get(referencingTableName);
}


@OverridingMethodsMustInvokeSuper
public DocumentFilterDescriptorsProvider createFilterDescriptorsProvider(){
    final DocumentEntityDescriptor huEntityDescriptor = getHUEntityDescriptor();
    final Collection<DocumentFilterDescriptor> huStandardFilters = huEntityDescriptor.getFilterDescriptors().getAll();
    return ImmutableDocumentFilterDescriptorsProvider.builder().addDescriptors(huStandardFilters).addDescriptor(HUBarcodeSqlDocumentFilterConverter.createDocumentFilterDescriptor()).build();
}


public DocumentFilterList extractStickyFilters(DocumentFilterList requestStickyFilters,Set<Integer> filterOnlyIds){
    final DocumentFilter stickyFilter_HUIds_Existing = HUIdsFilterHelper.findExistingOrNull(requestStickyFilters);
    // Create the sticky filter by HUIds from builder's huIds (if any huIds)
    if (stickyFilter_HUIds_Existing == null && filterOnlyIds != null && !filterOnlyIds.isEmpty()) {
        final DocumentFilter stickyFilter_HUIds_New = HUIdsFilterHelper.createFilter(HuId.ofRepoIds(filterOnlyIds));
        return DocumentFilterList.of(stickyFilter_HUIds_New).mergeWith(requestStickyFilters);
    } else {
        return requestStickyFilters;
    }
}


public DocumentFilterDescriptor createDocumentFilterDescriptor(){
    final ITranslatableString barcodeCaption = Services.get(IMsgBL.class).translatable("Barcode");
    return DocumentFilterDescriptor.builder().setFilterId(FILTER_ID).setDisplayName(barcodeCaption).setParametersLayoutType(PanelLayoutType.SingleOverlayField).setFrequentUsed(false).addParameter(DocumentFilterParamDescriptor.builder().setFieldName(PARAM_Barcode).setDisplayName(barcodeCaption).setWidgetType(DocumentFieldWidgetType.Text).barcodeScannerType(BarcodeScannerType.QRCode)).build();
}


@Nullable
public String extractReferencingTablename(Set<DocumentPath> referencingDocumentPaths){
    final String referencingTableName;
    if (!referencingDocumentPaths.isEmpty()) {
        // assuming all document paths have the same window
        final WindowId referencingWindowId = referencingDocumentPaths.iterator().next().getWindowId();
        referencingTableName = documentDescriptorFactory.getDocumentEntityDescriptor(referencingWindowId).getTableNameOrNull();
    } else {
        referencingTableName = null;
    }
    return referencingTableName;
}


@Override
public boolean canConvert(String filterId){
    return Objects.equals(filterId, FILTER_ID);
}


public HUEditorRowIsProcessedPredicate getRowProcessedPredicate(String referencingTableName){
    return rowProcessedPredicateByReferencingTableName.getOrDefault(referencingTableName, HUEditorRowIsProcessedPredicates.NEVER);
}


public void customizeHUEditorViewRepository(SqlHUEditorViewRepository.SqlHUEditorViewRepositoryBuilder huEditorViewRepositoryBuilder){
// nothing on this level
}


public DocumentFilterDescriptorsProvider getViewFilterDescriptors(){
    return getSqlViewBinding().getViewFilterDescriptors();
}


public SqlViewBinding getSqlViewBinding(){
    // not important
    final int key = 0;
    return sqlViewBindingCache.getOrLoad(key, this::createSqlViewBinding);
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId_NOTUSED){
    final ViewLayoutKey key = ViewLayoutKey.of(windowId, viewDataType);
    return layouts.getOrLoad(key, this::createHUViewLayout);
}


public SqlViewBinding createSqlViewBinding(){
    // Get HU's standard entity descriptor. We will needed all over.
    final DocumentEntityDescriptor huEntityDescriptor = getHUEntityDescriptor();
    // 
    // Static where clause
    final StringBuilder sqlWhereClause = new StringBuilder();
    {
        // top level
        sqlWhereClause.append(I_M_HU.COLUMNNAME_M_HU_Item_Parent_ID + " is null");
        // Consider window tab's where clause if any
        final I_AD_Tab huTab = Services.get(IADWindowDAO.class).retrieveFirstTab(WEBUI_HU_Constants.WEBUI_HU_Window_ID.toAdWindowId());
        if (!Check.isEmpty(huTab.getWhereClause(), true)) {
            sqlWhereClause.append("\n AND (").append(huTab.getWhereClause()).append(")");
        }
    }
    // 
    // Start preparing the sqlViewBinding builder
    final List<String> displayFieldNames = ImmutableList.of(I_M_HU.COLUMNNAME_M_HU_ID);
    final SqlViewBinding.Builder sqlViewBinding = SqlViewBinding.builder().tableName(I_M_HU.Table_Name).displayFieldNames(displayFieldNames).sqlWhereClause(sqlWhereClause.toString()).rowIdsConverter(HUSqlViewRowIdsConverter.instance);
    // 
    // View fields: from M_HU's entity descriptor
    {
        // NOTE: we need to add all HU's standard fields because those might be needed for some of the standard filters defined
        final SqlDocumentEntityDataBindingDescriptor huEntityBindings = SqlDocumentEntityDataBindingDescriptor.cast(huEntityDescriptor.getDataBinding());
        huEntityBindings.getFields().stream().map(huField -> SqlViewBindingFactory.createViewFieldBinding(huField, displayFieldNames)).forEach(sqlViewBinding::field);
    }
    // 
    // View field: BestBeforeDate
    {
        final String sqlBestBeforeDate = HUAttributeConstants.sqlBestBeforeDate(sqlViewBinding.getTableAlias() + "." + I_M_HU.COLUMNNAME_M_HU_ID);
        sqlViewBinding.field(SqlViewRowFieldBinding.builder().fieldName(HUEditorRow.FIELDNAME_BestBeforeDate).widgetType(DocumentFieldWidgetType.LocalDate).sqlSelectValue(SqlSelectValue.builder().virtualColumnSql(sqlBestBeforeDate).columnNameAlias(HUEditorRow.FIELDNAME_BestBeforeDate).build()).fieldLoader((rs, adLanguage) -> rs.getTimestamp(HUEditorRow.FIELDNAME_BestBeforeDate)).build());
    }
    // 
    // View filters and converters
    {
        sqlViewBinding.filterDescriptors(createFilterDescriptorsProvider()).filterConverter(HUBarcodeSqlDocumentFilterConverter.instance).filterConverter(HUIdsFilterHelper.SQL_DOCUMENT_FILTER_CONVERTER).filterConverters(createFilterConverters());
    }
    // 
    return sqlViewBinding.build();
}


@Override
public String getSql(SqlParamsCollector sqlParamsOut,DocumentFilter filter,SqlOptions sqlOpts_NOTUSED,SqlDocumentFilterConverterContext context){
    final Object barcodeObj = filter.getParameter(PARAM_Barcode).getValue();
    if (barcodeObj == null) {
        throw new IllegalArgumentException("Barcode parameter is null: " + filter);
    }
    final String barcode = barcodeObj.toString().trim();
    if (barcode.isEmpty()) {
        throw new IllegalArgumentException("Barcode parameter is empty: " + filter);
    }
    final ImmutableSet<HuId> huIds = Services.get(IHandlingUnitsDAO.class).createHUQueryBuilder().setContext(PlainContextAware.newOutOfTrx()).onlyContextClient(// avoid enforcing context AD_Client_ID because it might be that we are not in a user thread (so no context)
    false).setOnlyWithBarcode(barcode).setOnlyTopLevelHUs(false).createQueryBuilder().setOption(IQueryBuilder.OPTION_Explode_OR_Joins_To_SQL_Unions).create().listIds(HuId::ofRepoId);
    if (huIds.isEmpty()) {
        return ConstantQueryFilter.of(false).getSql();
    }
    final ImmutableSet<HuId> topLevelHuIds = Services.get(IHandlingUnitsBL.class).getTopLevelHUs(huIds);
    final ISqlQueryFilter sqlQueryFilter = new InArrayQueryFilter<>(I_M_HU.COLUMNNAME_M_HU_ID, topLevelHuIds);
    final String sql = sqlQueryFilter.getSql();
    sqlParamsOut.collectAll(sqlQueryFilter);
    return sql;
}


public List<SqlDocumentFilterConverter> createFilterConverters(){
    return ImmutableList.of();
}


public boolean isAlwaysUseSameLayout(){
    return Services.get(ISysConfigBL.class).getBooleanValue(SYSCFG_AlwaysUseSameLayout, false);
}


public void customizeViewLayout(ViewLayout.Builder viewLayoutBuilder,JSONViewDataType viewDataType){
// nothing on this level
}


public ViewLayout createHUViewLayout(ViewLayoutKey key){
    final WindowId windowId = key.getWindowId();
    final JSONViewDataType viewDataType = key.getViewDataType();
    final Collection<DocumentFilterDescriptor> all = getViewFilterDescriptors().getAll();
    final ViewLayout.Builder viewLayoutBuilder = ViewLayout.builder().setWindowId(windowId).setCaption("HU Editor").setEmptyResultText(LayoutFactory.HARDCODED_TAB_EMPTY_RESULT_TEXT).setEmptyResultHint(LayoutFactory.HARDCODED_TAB_EMPTY_RESULT_HINT).setIdFieldName(HUEditorRow.FIELDNAME_M_HU_ID).setFilters(all).setHasAttributesSupport(true).setHasTreeSupport(true).addElementsFromViewRowClass(HUEditorRow.class, viewDataType);
    if (!isAlwaysUseSameLayout()) {
        customizeViewLayout(viewLayoutBuilder, viewDataType);
    }
    return viewLayoutBuilder.build();
}


public void customizeHUEditorView(HUEditorViewBuilder huViewBuilder){
// nothing on this level
}


@Override
public HUEditorView createView(CreateViewRequest request){
    final ViewId viewId = request.getViewId();
    // 
    // Referencing documentPaths and tableName (i.e. from where are we coming, e.g. receipt schedule)
    final Set<DocumentPath> referencingDocumentPaths = request.getReferencingDocumentPaths();
    final String referencingTableName = extractReferencingTablename(referencingDocumentPaths);
    final SqlViewBinding sqlViewBinding = getSqlViewBinding();
    // 
    // HUEditorView rows repository
    final HUEditorViewRepository huEditorViewRepository;
    {
        final WindowId windowId = viewId.getWindowId();
        final boolean attributesAlwaysReadonly = rowAttributesAlwaysReadonlyByReferencingTableName.getOrDefault(referencingTableName, Boolean.TRUE);
        final SqlHUEditorViewRepositoryBuilder huEditorViewRepositoryBuilder = SqlHUEditorViewRepository.builder().windowId(windowId).rowProcessedPredicate(getRowProcessedPredicate(referencingTableName)).attributesProvider(HUEditorRowAttributesProvider.builder().readonly(attributesAlwaysReadonly).build()).sqlViewBinding(sqlViewBinding).huReservationService(huReservationService);
        customizeHUEditorViewRepository(huEditorViewRepositoryBuilder);
        huEditorViewRepository = huEditorViewRepositoryBuilder.build();
    }
    // 
    // HUEditorView
    {
        // Filters
        // as long as the deprecated getFilterOnlyIds() is around we can't ignore it
        @SuppressWarnings("deprecation")
        final DocumentFilterList stickyFilters = extractStickyFilters(request.getStickyFilters(), request.getFilterOnlyIds());
        final DocumentFilterDescriptorsProvider filterDescriptors = getViewFilterDescriptors();
        final DocumentFilterList userFilters = request.getFiltersUnwrapped(filterDescriptors);
        // Start building the HUEditorView
        final HUEditorViewBuilder huViewBuilder = HUEditorView.builder().setParentViewId(request.getParentViewId()).setParentRowId(request.getParentRowId()).setViewId(viewId).setViewType(request.getViewType()).setStickyFilters(stickyFilters).setFilters(userFilters).setFilterDescriptors(filterDescriptors).setReferencingDocumentPaths(referencingTableName, referencingDocumentPaths).orderBys(sqlViewBinding.getDefaultOrderBys()).setActions(request.getActions()).addAdditionalRelatedProcessDescriptors(request.getAdditionalRelatedProcessDescriptors()).setHUEditorViewRepository(huEditorViewRepository).setUseAutoFilters(request.isUseAutoFilters()).setParameters(request.getParameters());
        // 
        // Call view customizers
        getViewCustomizers(referencingTableName).forEach(viewCustomizer -> viewCustomizer.beforeCreate(huViewBuilder));
        customizeHUEditorView(huViewBuilder);
        return huViewBuilder.build();
    }
}


public DocumentEntityDescriptor getHUEntityDescriptor(){
    return documentDescriptorFactory.getDocumentEntityDescriptor(WEBUI_HU_Constants.WEBUI_HU_Window_ID);
}


}