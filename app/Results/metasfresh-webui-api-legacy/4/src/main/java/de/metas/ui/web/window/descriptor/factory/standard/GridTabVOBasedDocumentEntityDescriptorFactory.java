package de.metas.ui.web.window.descriptor.factory.standard;
 import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.adempiere.ad.expression.api.ConstantLogicExpression;
import org.adempiere.ad.expression.api.IExpression;
import org.adempiere.ad.expression.api.ILogicExpression;
import org.adempiere.ad.table.api.IADTableDAO;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.IPair;
import org.adempiere.util.lang.ImmutablePair;
import org.compiere.Adempiere;
import org.compiere.model.GridFieldDefaultFilterDescriptor;
import org.compiere.model.GridFieldVO;
import org.compiere.model.GridTabVO;
import org.compiere.model.I_AD_Column;
import org.compiere.model.I_AD_Tab;
import org.compiere.model.I_AD_UI_Element;
import org.compiere.model.I_AD_UI_ElementField;
import org.compiere.model.X_AD_UI_ElementField;
import org.compiere.util.DisplayType;
import org.compiere.util.Evaluatees;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import de.metas.adempiere.service.IColumnBL;
import de.metas.elasticsearch.indexer.IESModelIndexer;
import de.metas.elasticsearch.indexer.IESModelIndexersRegistry;
import de.metas.i18n.IModelTranslationMap;
import de.metas.logging.LogManager;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.session.WebRestApiContextProvider;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DataTypes;
import de.metas.ui.web.window.descriptor.ButtonFieldActionDescriptor;
import de.metas.ui.web.window.descriptor.ButtonFieldActionDescriptor.ButtonFieldActionType;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDefaultFilterDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Builder;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.FullTextSearchLookupDescriptorProvider;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.LookupDescriptorProvider;
import de.metas.ui.web.window.descriptor.LookupDescriptorProviders;
import de.metas.ui.web.window.descriptor.sql.SqlDocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlDocumentFieldDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlLookupDescriptor;
import de.metas.ui.web.window.model.DocumentsRepository;
import de.metas.ui.web.window.model.IDocumentFieldValueProvider;
import de.metas.ui.web.window.model.lookup.LabelsLookup;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.ui.web.window.model.lookup.TimeZoneLookupDescriptor;
import de.metas.ui.web.window.model.sql.SqlDocumentsRepository;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
public class GridTabVOBasedDocumentEntityDescriptorFactory {

 private  Logger logger;

 private  IColumnBL adColumnBL;

 private  DocumentsRepository documentsRepository;

 private  Map<Integer,String> _adFieldId2columnName;

 private  DefaultValueExpressionsFactory defaultValueExpressionsFactory;

 private  SpecialDocumentFieldsCollector _specialFieldsCollector;

 private  DocumentEntityDescriptor.Builder _documentEntityBuilder;


public boolean isTreatFieldAsKey(GridFieldVO gridFieldVO,GridTabVO gridTabVO,DocumentEntityDescriptor.Builder entityDescriptorBuilder){
    final boolean gridTabVOHasKeyColumns = gridTabVO.getFields().stream().anyMatch(GridFieldVO::isKey);
    if (gridTabVOHasKeyColumns) {
        return gridFieldVO.isKey();
    } else {
        return gridFieldVO.isParentLink();
    }
}


public DocumentFieldDescriptor.Builder documentFieldByAD_UI_ElementField(I_AD_UI_ElementField elementFieldRecord){
    final Builder builder = documentFieldByAD_Field_ID(elementFieldRecord.getAD_Field_ID());
    if (X_AD_UI_ElementField.TYPE_Tooltip.equals(elementFieldRecord.getType())) {
        final String tooltipIconName = Check.assumeNotEmpty(elementFieldRecord.getTooltipIconName(), "An elementFieldRecord with type=tooltip needs to have a tooltipIcon; elementFieldRecord={}", elementFieldRecord);
        builder.setTooltipIconName(tooltipIconName);
    }
    return builder;
}


public ILogicExpression extractReadOnlyLogic(GridFieldVO gridFieldVO,boolean keyColumn,boolean isParentLinkColumn){
    if (keyColumn) {
        return ConstantLogicExpression.TRUE;
    } else // 
    // Readonly logic in case of parent link column which is not parent link in this window.
    // NOTE: in SwingUI/application dictionary, in case a column is flagged as ParentLink it is automatically flagged as IsUpdateable=N.
    // So, here we are identifying this case and consider it as editable.
    // e.g. BPartner (pharma) window -> Product tab
    if (!gridFieldVO.isUpdateable() && gridFieldVO.isParentLink() && !isParentLinkColumn && gridFieldVO.isMandatory()) {
        return ConstantLogicExpression.FALSE;
    } else if (gridFieldVO.isReadOnly()) {
        return ConstantLogicExpression.TRUE;
    } else // 
    // Readonly logic in case of not Updateable
    // NOTE: in Swing UI, this property was interpreted as: allow the field to be read-write until it's saved. After that, it's readonly.
    // But here, on Webui we no longer have this concept, since we are auto-saving it.
    // NOTE2: we are checking the AD_Field/AD_Column.IsParentLink and in case is true, we consider the only reason why Updateable=N is because of them.
    // So basically in that case we ignore it.
    // 
    // Example where this rule is needed: have a table which has a column which is parent link, but in some windows we are displaying it as header (first tab),
    // and we want to allow the user setting it.
    if (!gridFieldVO.isUpdateable() && !gridFieldVO.isParentLink()) {
        return ConstantLogicExpression.FALSE;
    } else {
        return gridFieldVO.getReadOnlyLogic();
    }
}


public DocumentFieldDescriptor.Builder documentFieldByAD_Field_ID(int adFieldId){
    final String fieldName = _adFieldId2columnName.get(adFieldId);
    return documentField(fieldName);
}


public DocumentFieldDescriptor.Builder documentField(String fieldName){
    return documentEntity().getFieldBuilder(fieldName);
}


public ILogicExpression extractTabReadonlyLogic(GridTabVO gridTabVO){
    if (gridTabVO.isView()) {
        return ConstantLogicExpression.TRUE;
    }
    // 
    // Check if tab is always readonly
    if (gridTabVO.isReadOnly()) {
        return ConstantLogicExpression.TRUE;
    }
    // 
    // Check if tab's readonly expression
    final ILogicExpression tabReadonlyLogic = gridTabVO.getReadOnlyLogic();
    if (tabReadonlyLogic.isConstantTrue()) {
        return ConstantLogicExpression.TRUE;
    }
    return tabReadonlyLogic;
}


public boolean isCurrentlyUsedParentLinkField(GridFieldVO gridFieldVO,DocumentEntityDescriptor.Builder entityDescriptorBuilder){
    // issue https://github.com/metasfresh/metasfresh/issues/4622 :
    // Even if it's not flagged as parent-link in AD_Column, it can be configured that way in AD_Tab
    // if (!gridFieldVO.isParentLink())
    // {
    // return false;
    // }
    final SqlDocumentEntityDataBindingDescriptor.Builder entityBindings = entityDescriptorBuilder.getDataBindingBuilder(SqlDocumentEntityDataBindingDescriptor.Builder.class);
    final String parentLinkColumnName = entityBindings.getSqlParentLinkColumnName();
    // if there is a parent link column, only the respective gridFieldVO is a key
    return Objects.equals(gridFieldVO.getColumnName(), parentLinkColumnName);
}


public void collectSpecialFieldsDone(){
    if (_specialFieldsCollector == null) {
        return;
    }
    _specialFieldsCollector.collectFinish();
}


public ILogicExpression extractMandatoryLogic(GridFieldVO gridFieldVO){
    if (gridFieldVO.isMandatoryLogicExpression()) {
        return gridFieldVO.getMandatoryLogic();
    } else if (gridFieldVO.isMandatory()) {
        // consider it mandatory only if is displayed
        return gridFieldVO.getDisplayLogic();
    } else {
        return ConstantLogicExpression.FALSE;
    }
}


public DocumentFieldDefaultFilterDescriptor createDefaultFilterDescriptor(GridFieldDefaultFilterDescriptor gridFieldDefaultFilterInfo,String fieldName,DocumentFieldWidgetType widgetType,Class<?> valueClass,LookupDescriptorProvider lookupDescriptorProvider){
    if (gridFieldDefaultFilterInfo == null) {
        return null;
    }
    final Object autoFilterInitialValue = extractAutoFilterInitialValue(gridFieldDefaultFilterInfo, fieldName, widgetType, valueClass, lookupDescriptorProvider);
    return DocumentFieldDefaultFilterDescriptor.builder().defaultFilter(gridFieldDefaultFilterInfo.isDefaultFilter()).defaultFilterSeqNo(gridFieldDefaultFilterInfo.getDefaultFilterSeqNo()).rangeFilter(gridFieldDefaultFilterInfo.isRangeFilter()).showFilterIncrementButtons(gridFieldDefaultFilterInfo.isShowFilterIncrementButtons()).autoFilterInitialValue(autoFilterInitialValue).facetFilter(gridFieldDefaultFilterInfo.isFacetFilter()).facetFilterSeqNo(gridFieldDefaultFilterInfo.getFacetFilterSeqNo()).maxFacetsToFetch(gridFieldDefaultFilterInfo.getMaxFacetsToFetch()).build();
}


public DocumentEntityDescriptor.Builder createDocumentEntityBuilder(GridTabVO gridTabVO,GridTabVO parentTabVO,boolean isSOTrx,List<I_AD_UI_Element> labelsUIElements){
    final String tableName = gridTabVO.getTableName();
    final DetailId detailId = parentTabVO == null ? null : DetailId.fromAD_Tab_ID(gridTabVO.getAD_Tab_ID());
    // 
    // Entity Data binding
    if (!Check.isEmpty(gridTabVO.getOrderByClause(), true)) {
        logger.warn("Ignoring SQL order by for {}. See https://github.com/metasfresh/metasfresh/issues/412.", gridTabVO);
    }
    final SqlDocumentEntityDataBindingDescriptor.Builder dataBinding = SqlDocumentEntityDataBindingDescriptor.builder().setDocumentsRepository(documentsRepository).setTableName(tableName).setTableAliasFromDetailId(detailId).setChildToParentLinkColumnNames(extractChildParentLinkColumnNames(gridTabVO, parentTabVO)).setSqlWhereClause(gridTabVO.getWhereClause());
    final ILogicExpression allowInsert = ConstantLogicExpression.of(gridTabVO.isInsertRecord());
    final ILogicExpression allowDelete = ConstantLogicExpression.of(gridTabVO.isDeleteable());
    final ILogicExpression readonlyLogic = extractTabReadonlyLogic(gridTabVO);
    final ILogicExpression allowCreateNewLogic = allowInsert.andNot(readonlyLogic);
    final ILogicExpression allowDeleteLogic = allowDelete.andNot(readonlyLogic);
    // 
    final ILogicExpression displayLogic = gridTabVO.getDisplayLogic().evaluatePartial(Evaluatees.mapBuilder().put(WebRestApiContextProvider.CTXNAME_IsWebUI, DisplayType.toBooleanString(true)).build());
    // 
    // Entity descriptor
    final DocumentEntityDescriptor.Builder entityDescriptorBuilder = DocumentEntityDescriptor.builder().setDocumentType(gridTabVO.getAdWindowId()).setDetailId(detailId).setInternalName(gridTabVO.getInternalName()).setCaption(gridTabVO.getNameTrls(), gridTabVO.getName()).setDescription(gridTabVO.getDescriptionTrls(), gridTabVO.getDescription()).setReadonlyLogic(readonlyLogic).setAllowCreateNewLogic(allowCreateNewLogic).setAllowDeleteLogic(allowDeleteLogic).setDisplayLogic(displayLogic).setAllowQuickInput(gridTabVO.isAllowQuickInput()).setDataBinding(dataBinding).setHighVolume(gridTabVO.IsHighVolume).setAD_Tab_ID(// legacy
    gridTabVO.getAD_Tab_ID()).setTableName(// legacy
    tableName).setIsSOTrx(// legacy
    isSOTrx).setPrintProcessId(gridTabVO.getPrintProcessId()).setRefreshViewOnChangeEvents(gridTabVO.isRefreshViewOnChangeEvents());
    // Fields descriptor
    gridTabVO.getFields().stream().forEach(gridFieldVO -> createAndAddDocumentField(entityDescriptorBuilder, gridFieldVO, isTreatFieldAsKey(gridFieldVO, gridTabVO, entityDescriptorBuilder)));
    // 
    // Labels field descriptors
    labelsUIElements.forEach(labelUIElement -> createAndAddLabelsDocumentField(entityDescriptorBuilder, labelUIElement));
    return entityDescriptorBuilder;
}


public void collectSpecialField(DocumentFieldDescriptor.Builder field){
    if (_specialFieldsCollector == null) {
        return;
    }
    _specialFieldsCollector.collect(field);
}


public DocumentEntityDescriptor.Builder documentEntity(){
    return _documentEntityBuilder;
}


public DocumentFieldDefaultFilterDescriptor createLabelsDefaultFilterInfo(I_AD_UI_Element labelsUIElement){
    if (!labelsUIElement.isAllowFiltering()) {
        return null;
    }
    return DocumentFieldDefaultFilterDescriptor.builder().seqNo(Integer.MAX_VALUE).defaultFilter(true).build();
}


public Map<Characteristic,DocumentFieldDescriptor.Builder> getSpecialField_DocSatusAndDocAction(){
    return _specialFieldsCollector == null ? null : _specialFieldsCollector.getDocStatusAndDocAction();
}


public boolean extractAlwaysUpdateable(GridFieldVO gridFieldVO){
    if (gridFieldVO.isVirtualColumn() || !gridFieldVO.isUpdateable()) {
        return false;
    }
    // HARDCODED: DocAction shall always be updateable
    if (WindowConstants.FIELDNAME_DocAction.equals(gridFieldVO.getColumnName())) {
        return true;
    }
    return gridFieldVO.isAlwaysUpdateable();
}


public LookupDescriptorProvider wrapFullTextSeachFilterDescriptorProvider(LookupDescriptorProvider databaseLookupDescriptorProvider){
    final String modelTableName = databaseLookupDescriptorProvider.getTableName().orElse(null);
    if (modelTableName == null) {
        return databaseLookupDescriptorProvider;
    }
    final IESModelIndexersRegistry esModelIndexersRegistry = Services.get(IESModelIndexersRegistry.class);
    final IESModelIndexer modelIndexer = esModelIndexersRegistry.getFullTextSearchModelIndexer(modelTableName).orElse(null);
    if (modelIndexer == null) {
        return databaseLookupDescriptorProvider;
    }
    final Client elasticsearchClient = Adempiere.getBean(org.elasticsearch.client.Client.class);
    return FullTextSearchLookupDescriptorProvider.builder().elasticsearchClient(elasticsearchClient).modelTableName(modelIndexer.getModelTableName()).esIndexName(modelIndexer.getIndexName()).esSearchFieldNames(modelIndexer.getFullTextSearchFieldNames()).databaseLookupDescriptorProvider(databaseLookupDescriptorProvider).build();
}


public Object extractAutoFilterInitialValue(GridFieldDefaultFilterDescriptor gridFieldDefaultFilterInfo,String fieldName,DocumentFieldWidgetType widgetType,Class<?> valueClass,LookupDescriptorProvider lookupDescriptorProvider){
    final String autoFilterInitialValueStr = gridFieldDefaultFilterInfo.getDefaultValue();
    if (Check.isBlank(autoFilterInitialValueStr)) {
        return null;
    } else if (widgetType.isDateOrTime() && DocumentFieldDefaultFilterDescriptor.AUTOFILTER_INITIALVALUE_DATE_NOW.equalsIgnoreCase(autoFilterInitialValueStr.trim())) {
        return DocumentFieldDefaultFilterDescriptor.AUTOFILTER_INITIALVALUE_DATE_NOW;
    } else {
        final LookupDataSource lookupDataSource = widgetType.isLookup() ? createFilterLookupDataSourceOrNull(lookupDescriptorProvider) : null;
        return DataTypes.convertToValueClass(fieldName, autoFilterInitialValueStr, widgetType, valueClass, lookupDataSource);
    }
}


public IPair<String,String> extractChildParentLinkColumnNames(GridTabVO childTabVO,GridTabVO parentTabVO){
    // If this is the master tab then there is no parent link
    if (parentTabVO == null) {
        return null;
    }
    // 
    // Find parent's link column name
    final int parentLinkColumnId = childTabVO.getParent_Column_ID();
    String parentLinkColumnName = parentTabVO.getColumnNameByAD_Column_ID(parentLinkColumnId);
    if (parentLinkColumnName == null) {
        parentLinkColumnName = parentTabVO.getKeyColumnName();
    }
    if (parentLinkColumnName == null) {
        return null;
    }
    // 
    // Find child's link column name and then return the pair
    final Set<String> childLinkColumnNames = childTabVO.getLinkColumnNames();
    if (childLinkColumnNames.isEmpty()) {
        // No child link columns
        return null;
    } else if (childLinkColumnNames.size() == 1) {
        final String childLinkColumnName = childLinkColumnNames.iterator().next();
        return ImmutablePair.of(childLinkColumnName, parentLinkColumnName);
    } else if (childLinkColumnNames.contains(parentLinkColumnName)) {
        final String childLinkColumnName = parentLinkColumnName;
        return ImmutablePair.of(childLinkColumnName, parentLinkColumnName);
    } else {
        return null;
    }
}


public DocumentFieldDescriptor.Builder getSpecialField_DocumentSummary(){
    return _specialFieldsCollector == null ? null : _specialFieldsCollector.getDocumentSummary();
}


public LabelsLookup createLabelsLookup(I_AD_UI_Element labelsUIElement,String tableName){
    final I_AD_Tab labelsTab = labelsUIElement.getLabels_Tab();
    final String labelsTableName = labelsTab.getAD_Table().getTableName();
    final String linkColumnName;
    if (labelsTab.getParent_Column_ID() > 0) {
        linkColumnName = labelsTab.getParent_Column().getColumnName();
    } else {
        linkColumnName = InterfaceWrapperHelper.getKeyColumnName(tableName);
    }
    final String labelsLinkColumnName;
    if (labelsTab.getAD_Column_ID() > 0) {
        labelsLinkColumnName = labelsTab.getAD_Column().getColumnName();
    } else {
        labelsLinkColumnName = linkColumnName;
    }
    final I_AD_Column labelsValueColumn = labelsUIElement.getLabels_Selector_Field().getAD_Column();
    final String labelsValueColumnName = labelsValueColumn.getColumnName();
    final LookupDescriptor labelsValuesLookupDescriptor = SqlLookupDescriptor.builder().setCtxTableName(labelsTableName).setCtxColumnName(labelsValueColumnName).setDisplayType(labelsValueColumn.getAD_Reference_ID()).setWidgetType(DescriptorsFactoryHelper.extractWidgetType(labelsValueColumnName, labelsValueColumn.getAD_Reference_ID())).setAD_Reference_Value_ID(labelsValueColumn.getAD_Reference_Value_ID()).setAD_Val_Rule_ID(labelsValueColumn.getAD_Val_Rule_ID()).buildForDefaultScope();
    return LabelsLookup.builder().labelsTableName(labelsTableName).labelsValueColumnName(labelsValueColumnName).labelsValuesLookupDescriptor(labelsValuesLookupDescriptor).labelsLinkColumnName(labelsLinkColumnName).tableName(tableName).linkColumnName(linkColumnName).build();
}


public void createAndAddLabelsDocumentField(DocumentEntityDescriptor.Builder entityDescriptor,I_AD_UI_Element labelsUIElement){
    final String labelsFieldName = getLabelsFieldName(labelsUIElement);
    final String tablename = entityDescriptor.getTableName().get();
    final LabelsLookup lookupDescriptor = createLabelsLookup(labelsUIElement, tablename);
    final SqlDocumentEntityDataBindingDescriptor.Builder entityBindings = entityDescriptor.getDataBindingBuilder(SqlDocumentEntityDataBindingDescriptor.Builder.class);
    final SqlDocumentFieldDataBindingDescriptor fieldBinding = SqlDocumentFieldDataBindingDescriptor.builder().setFieldName(labelsFieldName).setVirtualColumn(false).setMandatory(false).setWidgetType(DocumentFieldWidgetType.Labels).setValueClass(DocumentFieldWidgetType.Labels.getValueClass()).setSqlValueClass(DocumentFieldWidgetType.Labels.getValueClass()).setLookupDescriptor(lookupDescriptor).build();
    entityBindings.addField(fieldBinding);
    final IModelTranslationMap trlMap = InterfaceWrapperHelper.getModelTranslationMap(labelsUIElement);
    final DocumentFieldDescriptor.Builder fieldBuilder = DocumentFieldDescriptor.builder(labelsFieldName).setCaption(trlMap.getColumnTrl(I_AD_UI_Element.COLUMNNAME_Name, labelsUIElement.getName())).setDescription(trlMap.getColumnTrl(I_AD_UI_Element.COLUMNNAME_Description, labelsUIElement.getDescription())).setKey(false).setWidgetType(DocumentFieldWidgetType.Labels).setValueClass(fieldBinding.getValueClass()).setLookupDescriptorProvider(lookupDescriptor).setDefaultFilterInfo(createLabelsDefaultFilterInfo(labelsUIElement)).setDataBinding(fieldBinding);
    // 
    // Add Field builder to document entity
    entityDescriptor.addField(fieldBuilder);
    // 
    // Collect special field
    collectSpecialField(fieldBuilder);
}


public DocumentFieldDescriptor.Builder addInternalVirtualField(String fieldName,DocumentFieldWidgetType widgetType,IDocumentFieldValueProvider valueProvider){
    final DocumentEntityDescriptor.Builder documentEntity = documentEntity();
    final DocumentFieldDescriptor.Builder fieldBuilder = DocumentFieldDescriptor.builder(fieldName).setWidgetType(widgetType).setVirtualField(valueProvider).setDisplayLogic(// never display it because it's internal
    false);
    // 
    // Add Field builder to document entity
    documentEntity.addField(fieldBuilder);
    // 
    // Collect special field
    collectSpecialField(fieldBuilder);
    return fieldBuilder;
}


public void createAndAddDocumentField(DocumentEntityDescriptor.Builder entityDescriptorBuilder,GridFieldVO gridFieldVO,boolean keyColumn){
    // From entry data-binding:
    final SqlDocumentEntityDataBindingDescriptor.Builder entityBindings = entityDescriptorBuilder.getDataBindingBuilder(SqlDocumentEntityDataBindingDescriptor.Builder.class);
    // From GridFieldVO:
    // 
    DocumentFieldWidgetType widgetType;
    final Class<?> valueClass;
    final Optional<IExpression<?>> defaultValueExpression;
    final boolean alwaysUpdateable;
    final LookupDescriptorProvider lookupDescriptorProvider;
    final Optional<LookupDescriptor> lookupDescriptor;
    ILogicExpression readonlyLogic;
    final boolean isParentLinkColumn = isCurrentlyUsedParentLinkField(gridFieldVO, entityDescriptorBuilder);
    final String sqlColumnName = gridFieldVO.getColumnName();
    if (// assumes that the column is not only flagged as parent link, but is also the parent link *in this particular document*
    isParentLinkColumn) {
        widgetType = DocumentFieldWidgetType.Integer;
        valueClass = widgetType.getValueClass();
        alwaysUpdateable = false;
        lookupDescriptorProvider = LookupDescriptorProviders.NULL;
        lookupDescriptor = Optional.empty();
        defaultValueExpression = Optional.empty();
        readonlyLogic = ConstantLogicExpression.TRUE;
    } else if (// single key column
    gridFieldVO.isKey()) {
        widgetType = DocumentFieldWidgetType.Integer;
        valueClass = widgetType.getValueClass();
        alwaysUpdateable = false;
        lookupDescriptorProvider = LookupDescriptorProviders.NULL;
        lookupDescriptor = Optional.empty();
        defaultValueExpression = Optional.empty();
        readonlyLogic = ConstantLogicExpression.TRUE;
    } else {
        if (WindowConstants.FIELDNAME_TimeZone.contentEquals(sqlColumnName)) {
            lookupDescriptorProvider = TimeZoneLookupDescriptor.provider;
            widgetType = DocumentFieldWidgetType.Lookup;
        } else {
            final int displayType = gridFieldVO.getDisplayType();
            widgetType = DescriptorsFactoryHelper.extractWidgetType(sqlColumnName, displayType);
            final String ctxTableName = Services.get(IADTableDAO.class).retrieveTableName(gridFieldVO.getAD_Table_ID());
            lookupDescriptorProvider = wrapFullTextSeachFilterDescriptorProvider(SqlLookupDescriptor.builder().setCtxTableName(ctxTableName).setCtxColumnName(sqlColumnName).setWidgetType(widgetType).setDisplayType(displayType).setAD_Reference_Value_ID(gridFieldVO.getAD_Reference_Value_ID()).setAD_Val_Rule_ID(gridFieldVO.getAD_Val_Rule_ID()).buildProvider());
        }
        lookupDescriptor = lookupDescriptorProvider.provide();
        valueClass = DescriptorsFactoryHelper.getValueClass(widgetType, lookupDescriptor);
        defaultValueExpression = defaultValueExpressionsFactory.extractDefaultValueExpression(gridFieldVO.getDefaultValue(), sqlColumnName, widgetType, valueClass, gridFieldVO.isMandatory(), gridFieldVO.isUseDocSequence());
        readonlyLogic = extractReadOnlyLogic(gridFieldVO, keyColumn, isParentLinkColumn);
        alwaysUpdateable = extractAlwaysUpdateable(gridFieldVO);
    }
    // 
    // Button action
    final ButtonFieldActionDescriptor buttonAction;
    if (!isParentLinkColumn && widgetType.isButton()) {
        buttonAction = extractButtonFieldActionDescriptor(entityDescriptorBuilder.getTableNameOrNull(), sqlColumnName, gridFieldVO.AD_Process_ID);
        if (buttonAction != null) {
            final ButtonFieldActionType actionType = buttonAction.getActionType();
            if (actionType == ButtonFieldActionType.processCall) {
                widgetType = DocumentFieldWidgetType.ProcessButton;
            } else if (actionType == ButtonFieldActionType.genericZoomInto) {
                widgetType = DocumentFieldWidgetType.ZoomIntoButton;
                // allow pressing the button
                readonlyLogic = ConstantLogicExpression.FALSE;
            }
        }
    } else {
        buttonAction = null;
    }
    // 
    // ORDER BY SortNo
    int orderBySortNo = gridFieldVO.getSortNo();
    if (orderBySortNo == 0 && keyColumn) {
        orderBySortNo = Integer.MAX_VALUE;
    }
    final String sqlColumnSql = gridFieldVO.getColumnSQL(false);
    final SqlDocumentFieldDataBindingDescriptor fieldBinding = SqlDocumentFieldDataBindingDescriptor.builder().setFieldName(sqlColumnName).setTableName(entityBindings.getTableName()).setTableAlias(entityBindings.getTableAlias()).setColumnName(sqlColumnName).setColumnSql(sqlColumnSql).setVirtualColumn(gridFieldVO.isVirtualColumn()).setMandatory(gridFieldVO.isMandatoryDB()).setWidgetType(widgetType).setValueClass(valueClass).setSqlValueClass(entityBindings.getPOInfo().getColumnClass(sqlColumnName)).setLookupDescriptor(lookupDescriptor.orElse(null)).setKeyColumn(keyColumn).setEncrypted(gridFieldVO.isEncryptedColumn()).setDefaultOrderBy(orderBySortNo).build();
    final String parentLinkFieldName = isParentLinkColumn ? entityBindings.getSqlParentLinkColumnName() : null;
    final int fieldMaxLength = widgetType.isStrictText() && gridFieldVO.getFieldLength() > 0 ? gridFieldVO.getFieldLength() : 0;
    final DocumentFieldDescriptor.Builder fieldBuilder = DocumentFieldDescriptor.builder(sqlColumnName).setCaption(gridFieldVO.getHeaderTrls(), gridFieldVO.getHeader()).setDescription(gridFieldVO.getDescriptionTrls(), gridFieldVO.getDescription()).setKey(keyColumn).setParentLink(isParentLinkColumn, parentLinkFieldName).setWidgetType(widgetType).setFieldMaxLength(fieldMaxLength).setButtonActionDescriptor(buttonAction).setLookupDescriptorProvider(lookupDescriptorProvider).setValueClass(fieldBinding.getValueClass()).setVirtualField(fieldBinding.isVirtualColumn()).setCalculated(gridFieldVO.isCalculated()).setDefaultValueExpression(defaultValueExpression).addCharacteristicIfTrue(keyColumn, Characteristic.SideListField).addCharacteristicIfTrue(keyColumn, Characteristic.GridViewField).setReadonlyLogic(readonlyLogic).setAlwaysUpdateable(alwaysUpdateable).setMandatoryLogic(extractMandatoryLogic(gridFieldVO)).setDisplayLogic(gridFieldVO.getDisplayLogic()).setDefaultFilterInfo(createDefaultFilterDescriptor(gridFieldVO.getDefaultFilterDescriptor(), sqlColumnName, widgetType, fieldBinding.getValueClass(), lookupDescriptorProvider)).setDataBinding(fieldBinding);
    // 
    // Add Field builder to document entity
    entityDescriptorBuilder.addField(fieldBuilder);
    // 
    // Add Field's data binding to entity data binding
    entityBindings.addField(fieldBinding);
    // 
    // Collect special field
    collectSpecialField(fieldBuilder);
}


public LookupDataSource createFilterLookupDataSourceOrNull(LookupDescriptorProvider lookupDescriptorProvider){
    if (lookupDescriptorProvider == null) {
        return null;
    }
    final LookupDescriptor lookupDescriptor = lookupDescriptorProvider.provideForFilter().orElse(null);
    if (lookupDescriptor == null) {
        return null;
    }
    return LookupDataSourceFactory.instance.getLookupDataSource(lookupDescriptor);
}


public ButtonFieldActionDescriptor extractButtonFieldActionDescriptor(String tableName,String fieldName,int adProcessId){
    // Process call
    if (adProcessId > 0) {
        // FIXME: hardcoded, exclude field when considering ProcessButton widget
        // because it's AD_Process_ID it's a placeholder-ish one.
        if (WindowConstants.FIELDNAME_DocAction.equals(fieldName) || WindowConstants.FIELDNAME_Processing.equals(fieldName)) {
            return null;
        }
        return ButtonFieldActionDescriptor.processCall(ProcessId.ofAD_Process_ID(adProcessId));
    // TODO widgetType = DocumentFieldWidgetType.ProcessButton;
    }
    // Generic ZoomInto button
    if (tableName != null) {
        if (adColumnBL.isRecordIdColumnName(fieldName)) {
            final String zoomIntoTableIdFieldName = adColumnBL.getTableIdColumnName(tableName, fieldName).orElse(null);
            if (zoomIntoTableIdFieldName != null) {
                return ButtonFieldActionDescriptor.genericZoomInto(zoomIntoTableIdFieldName);
            }
        }
    }
    // 
    return null;
}


public String getLabelsFieldName(I_AD_UI_Element uiElement){
    return "Labels_" + uiElement.getAD_UI_Element_ID();
}


public ILogicExpression getTabDisplayLogic(){
    return documentEntity().getDisplayLogic();
}


public void addFieldsCharacteristic(Set<String> fieldNames,Characteristic characteristic){
    Check.assumeNotNull(characteristic, "Parameter characteristic is not null");
    fieldNames.stream().map(fieldName -> documentField(fieldName)).forEach(field -> field.addCharacteristic(characteristic));
}


}