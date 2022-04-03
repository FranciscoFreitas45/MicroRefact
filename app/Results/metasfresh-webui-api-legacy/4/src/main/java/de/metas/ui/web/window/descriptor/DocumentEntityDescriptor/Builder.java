package de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
 import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.ad.callout.api.ICalloutExecutor;
import org.adempiere.ad.callout.api.ICalloutRecord;
import org.adempiere.ad.callout.api.impl.CalloutExecutor;
import org.adempiere.ad.callout.api.impl.NullCalloutExecutor;
import org.adempiere.ad.callout.spi.ICalloutProvider;
import org.adempiere.ad.callout.spi.ImmutablePlainCalloutProvider;
import org.adempiere.ad.element.api.AdTabId;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.ad.expression.api.ConstantLogicExpression;
import org.adempiere.ad.expression.api.ILogicExpression;
import org.adempiere.ad.ui.api.ITabCalloutFactory;
import org.adempiere.ad.ui.spi.ITabCallout;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.CopyRecordFactory;
import org.compiere.SpringContextHolder;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.lang.SOTrx;
import de.metas.logging.LogManager;
import de.metas.printing.esb.base.util.Check;
import de.metas.process.AdProcessId;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvidersService;
import de.metas.ui.web.window.datatypes.DataTypes;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor.DocumentEntityDataBindingDescriptorBuilder;
import de.metas.ui.web.window.descriptor.DocumentFieldDependencyMap.DependencyType;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.HighVolumeReadWriteIncludedDocumentsCollection;
import de.metas.ui.web.window.model.HighVolumeReadonlyIncludedDocumentsCollection;
import de.metas.ui.web.window.model.IIncludedDocumentsCollection;
import de.metas.ui.web.window.model.IIncludedDocumentsCollectionFactory;
import de.metas.ui.web.window.model.SingleRowDetailIncludedDocumentsCollection;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import lombok.Getter;
import lombok.NonNull;
public class Builder {

 private  Logger logger;

 private  DocumentFilterDescriptorsProvidersService filterDescriptorsProvidersService;

 private  boolean _built;

 private  DocumentType _documentType;

 private  DocumentId _documentTypeId;

 private  String _internalName;

 private  ITranslatableString _caption;

 private  ITranslatableString _description;

 private  Map<String,DocumentFieldDescriptor.Builder> _fieldBuilders;

 private  Map<String,DocumentFieldDescriptor> _fields;

 private  Map<DetailId,DocumentEntityDescriptor> _includedEntitiesByDetailId;

 private  DocumentEntityDataBindingDescriptorBuilder _dataBinding;

 private  boolean _highVolume;

 private  DetailId _detailId;

 private  ILogicExpression _allowCreateNewLogic;

 private  ILogicExpression _allowDeleteLogic;

 private  ILogicExpression _displayLogic;

 private  ILogicExpression _readonlyLogic;

 private  boolean _allowQuickInput;

 private  boolean _refreshViewOnChangeEvents;

 private  boolean _calloutsEnabled;

 private  boolean _defaultTableCalloutsEnabled;

 private  AdProcessId _printProcessId;

 private  Boolean _cloneEnabled;

@Getter
 private  boolean singleRowDetail;

 private  Optional<AdTabId> _adTabId;

 private  Optional<String> _tableName;

 private  Optional<SOTrx> _soTrx;


public Builder setTableName(Optional<String> tableName){
    _tableName = tableName != null ? tableName : Optional.empty();
    return this;
}


public int getFieldsCount(){
    return _fieldBuilders.size();
}


public Builder setAllowQuickInput(boolean allowQuickInput){
    this._allowQuickInput = allowQuickInput;
    return this;
}


public Builder setCaption(String caption){
    _caption = TranslatableStrings.constant(caption);
    return this;
}


public Optional<AdTabId> getAdTabId(){
    return _adTabId;
}


public ITranslatableString getCaption(){
    return _caption;
}


public boolean isQueryIncludedTabOnActivate(){
    return true;
}


public void assertNotBuilt(){
    if (_built) {
        throw new IllegalStateException("Already built: " + this);
    }
}


public Builder setReadonlyLogic(ILogicExpression readonlyLogic){
    _readonlyLogic = readonlyLogic;
    updateFieldBuilders(fieldBuilder -> fieldBuilder.setEntityReadonlyLogic(readonlyLogic));
    return this;
}


public ILogicExpression getReadonlyLogic(){
    return _readonlyLogic;
}


public Builder setDetailId(DetailId detailId){
    _detailId = detailId;
    updateFieldBuilders(fieldBuilder -> fieldBuilder.setDetailId(detailId));
    return this;
}


public ILogicExpression getDisplayLogic(){
    return _displayLogic;
}


public boolean isDefaultTableCalloutsEnabled(){
    return _calloutsEnabled && _defaultTableCalloutsEnabled;
}


public IIncludedDocumentsCollectionFactory getIncludedDocumentsCollectionFactory(){
    if (isSingleRowDetail()) {
        return SingleRowDetailIncludedDocumentsCollection::new;
    }
    if (isHighVolume()) {
        if (getReadonlyLogic().isConstantTrue()) {
            return HighVolumeReadonlyIncludedDocumentsCollection::new;
        } else {
            return HighVolumeReadWriteIncludedDocumentsCollection::newInstance;
        }
    }
    // Fallback
    return HighVolumeReadWriteIncludedDocumentsCollection::newInstance;
}


public Builder addIncludedEntity(DocumentEntityDescriptor includedEntity){
    final DetailId detailId = includedEntity.getDetailId();
    Check.assumeNotNull(detailId, "detailId is not null for {}", includedEntity);
    _includedEntitiesByDetailId.put(detailId, includedEntity);
    return this;
}


public Builder setDocumentType(AdWindowId adWindowId){
    return setDocumentType(DocumentType.Window, DocumentId.of(adWindowId.getRepoId()));
}


public Builder setRefreshViewOnChangeEvents(boolean refreshViewOnChangeEvents){
    this._refreshViewOnChangeEvents = refreshViewOnChangeEvents;
    return this;
}


public Builder setInternalName(String internalName){
    this._internalName = internalName;
    return this;
}


public DocumentFieldDescriptor.Builder getSingleIdFieldBuilderOrNull(){
    final List<DocumentFieldDescriptor.Builder> idFieldBuilders = getIdFieldBuilders();
    return idFieldBuilders.size() == 1 ? idFieldBuilders.get(0) : null;
}


public AdProcessId getPrintProcessId(){
    return _printProcessId;
}


public Builder setHighVolume(boolean highVolume){
    _highVolume = highVolume;
    return this;
}


public ImmutableList<DocumentFieldDescriptor> getIdFields(){
    return getFields().values().stream().filter(field -> field.isKey()).collect(ImmutableList.toImmutableList());
}


public boolean isHighVolume(){
    return _highVolume;
}


public Optional<String> getTableName(){
    return _tableName;
}


public Builder addField(DocumentFieldDescriptor.Builder fieldBuilder){
    assertFieldsNotBuilt();
    // 
    // Update field from entity
    fieldBuilder.setDetailId(getDetailId()).setEntityReadonlyLogic(getReadonlyLogic());
    // Add field
    _fieldBuilders.put(fieldBuilder.getFieldName(), fieldBuilder);
    return this;
}


public Builder addAllIncludedEntities(Collection<DocumentEntityDescriptor> includedEntities){
    for (final DocumentEntityDescriptor includedEntity : includedEntities) {
        addIncludedEntity(includedEntity);
    }
    return this;
}


public Builder addFieldIf(boolean condition,Supplier<DocumentFieldDescriptor.Builder> fieldBuilderSupplier){
    if (!condition) {
        return this;
    }
    assertFieldsNotBuilt();
    addField(fieldBuilderSupplier.get());
    return this;
}


public boolean isAllowQuickInput(){
    return _allowQuickInput;
}


public T getDataBindingBuilder(Class<T> builderType){
    @SuppressWarnings("unchecked")
    final T dataBindingBuilder = (T) _dataBinding;
    return dataBindingBuilder;
}


public Builder setIsSOTrx(Optional<SOTrx> soTrx){
    _soTrx = soTrx != null ? soTrx : Optional.empty();
    return this;
}


public Map<String,DocumentFieldDescriptor> getFields(){
    if (_fields == null) {
        _fields = _fieldBuilders.values().stream().map(fieldBuilder -> fieldBuilder.getOrBuild()).collect(GuavaCollectors.toImmutableMapByKey(field -> field.getFieldName()));
    }
    return _fields;
}


public DocumentId getDocumentTypeId(){
    Check.assumeNotNull(_documentTypeId, "documentTypeId is set for {}", this);
    return _documentTypeId;
}


public Builder disableCallouts(){
    _calloutsEnabled = false;
    return this;
}


public Builder setPrintProcessId(AdProcessId printProcessId){
    _printProcessId = printProcessId;
    return this;
}


public Optional<SOTrx> getSOTrx(){
    return _soTrx;
}


public ILogicExpression getAllowDeleteLogic(){
    return _allowDeleteLogic;
}


public boolean isCloneEnabled(Optional<String> tableName){
    if (tableName == null || !tableName.isPresent()) {
        return false;
    }
    if (!CopyRecordFactory.isEnabled()) {
        return false;
    }
    return CopyRecordFactory.isEnabledForTableName(tableName.get());
}


public ImmutableMap<DetailId,DocumentEntityDescriptor> buildIncludedEntitiesByDetailId(){
    return ImmutableMap.copyOf(_includedEntitiesByDetailId);
}


public Builder setDataBinding(DocumentEntityDataBindingDescriptorBuilder dataBindingBuilder){
    _dataBinding = dataBindingBuilder;
    return this;
}


public String getTableNameOrNull(){
    return _tableName.orElse(null);
}


public DocumentType getDocumentType(){
    Check.assumeNotNull(_documentType, "documentType is set for {}", this);
    return _documentType;
}


public String getId(){
    final StringBuilder id = new StringBuilder();
    id.append(getDocumentType());
    id.append("-").append(getDocumentTypeId());
    final DetailId detailId = getDetailId();
    if (detailId != null) {
        id.append("-").append(detailId);
    }
    return id.toString();
}


public Builder setDisplayLogic(ILogicExpression displayLogic){
    _displayLogic = displayLogic;
    return this;
}


public Builder setDescription(String description){
    _description = TranslatableStrings.constant(description);
    return this;
}


public void assertFieldsNotBuilt(){
    assertNotBuilt();
    Check.assumeNull(_fields, "Fields not already built");
}


public ITranslatableString getDescription(){
    return _description;
}


public Builder disableDefaultTableCallouts(){
    _defaultTableCalloutsEnabled = false;
    return this;
}


public ILogicExpression getAllowCreateNewLogic(){
    return _allowCreateNewLogic;
}


public DocumentEntityDataBindingDescriptor getOrBuildDataBinding(){
    Preconditions.checkNotNull(_dataBinding, "dataBinding");
    return _dataBinding.getOrBuild();
}


public Builder setAD_Tab_ID(int AD_Tab_ID){
    _adTabId = Optional.ofNullable(AdTabId.ofRepoIdOrNull(AD_Tab_ID));
    return this;
}


public String getSingleIdFieldNameOrNull(){
    final List<DocumentFieldDescriptor.Builder> idFieldBuilders = getIdFieldBuilders();
    return idFieldBuilders.size() == 1 ? idFieldBuilders.get(0).getFieldName() : null;
}


public ICalloutExecutor buildCalloutExecutorFactory(Collection<DocumentFieldDescriptor> fields){
    if (!isCalloutsEnabled()) {
        return NullCalloutExecutor.instance;
    }
    // 
    // CalloutExecutor builder
    final String tableName = getTableName().orElse(ICalloutProvider.ANY_TABLE);
    final CalloutExecutor.Builder calloutExecutorBuilder = CalloutExecutor.builder().setTableName(tableName);
    // 
    // Create a provider from callouts which were programmatically registered on each field
    final ImmutablePlainCalloutProvider.Builder entityCalloutProviderBuilder = ImmutablePlainCalloutProvider.builder();
    for (final DocumentFieldDescriptor field : fields) {
        final List<IDocumentFieldCallout> fieldCallouts = field.getCallouts();
        if (fieldCallouts.isEmpty()) {
            continue;
        }
        for (final IDocumentFieldCallout fieldCallout : fieldCallouts) {
            final Set<String> dependsOnFieldNames = fieldCallout.getDependsOnFieldNames();
            if (dependsOnFieldNames.isEmpty()) {
                logger.warn("Callout {} has no dependencies. Skipped from adding to entity descriptor.\n Target: {} \n Source: {}", fieldCallout, this, field);
                continue;
            }
            for (final String dependsOnFieldName : dependsOnFieldNames) {
                entityCalloutProviderBuilder.addCallout(tableName, dependsOnFieldName, fieldCallout);
            }
        }
    }
    // 
    calloutExecutorBuilder.addCalloutProvider(entityCalloutProviderBuilder.build());
    // 
    // Standard callouts provider (which will fetch the callouts from application dictionary)
    if (isDefaultTableCalloutsEnabled()) {
        calloutExecutorBuilder.addDefaultCalloutProvider();
    }
    // 
    return calloutExecutorBuilder.build();
}


public boolean isTableName(String expectedTableName){
    return Objects.equals(expectedTableName, _tableName.orElse(null));
}


public DocumentFilterDescriptorsProvider createFilterDescriptors(){
    final DocumentFilterDescriptorsProvidersService filterDescriptorsProvidersService = this.filterDescriptorsProvidersService != null ? this.filterDescriptorsProvidersService : SpringContextHolder.instance.getBean(DocumentFilterDescriptorsProvidersService.class);
    final String tableName = getTableName().orElse(null);
    final AdTabId adTabId = getAdTabId().orElse(null);
    final Collection<DocumentFieldDescriptor> fields = getFields().values();
    return filterDescriptorsProvidersService.createFiltersProvider(adTabId, tableName, fields);
}


public DetailId getDetailId(){
    return _detailId;
}


public boolean isRefreshViewOnChangeEvents(){
    return _refreshViewOnChangeEvents;
}


public DocumentFieldDescriptor.Builder getFieldBuilder(String fieldName){
    return _fieldBuilders.get(fieldName);
}


public Builder setAllowCreateNewLogic(ILogicExpression allowCreateNewLogic){
    Check.assumeNotNull(allowCreateNewLogic, "Parameter allowCreateNewLogic is not null");
    _allowCreateNewLogic = allowCreateNewLogic;
    return this;
}


public boolean hasIdField(){
    return !getIdFieldBuilders().isEmpty();
}


public Collection<DocumentFieldDescriptor.Builder> getFieldBuilders(){
    return _fieldBuilders.values();
}


public Builder setCloneEnabled(boolean cloneEnabled){
    _cloneEnabled = cloneEnabled;
    return this;
}


public DocumentFieldDescriptor getParentLinkFieldOrNull(){
    final List<DocumentFieldDescriptor> parentLinkFields = getFields().values().stream().filter(DocumentFieldDescriptor::isParentLink).collect(ImmutableList.toImmutableList());
    if (parentLinkFields.isEmpty()) {
        return null;
    } else if (parentLinkFields.size() == 1) {
        return parentLinkFields.get(0);
    } else {
        throw new AdempiereException("More than one parent link fields found for " + this).appendParametersToMessage().setParameter("parentLinkFields", parentLinkFields);
    }
}


public Builder setAllowDeleteLogic(ILogicExpression allowDeleteLogic){
    Check.assumeNotNull(allowDeleteLogic, "Parameter allowDeleteLogic is not null");
    _allowDeleteLogic = allowDeleteLogic;
    return this;
}


public Builder setFilterDescriptorsProvidersService(DocumentFilterDescriptorsProvidersService filterDescriptorsProvidersService){
    this.filterDescriptorsProvidersService = filterDescriptorsProvidersService;
    return this;
}


public DocumentQueryOrderByList getDefaultOrderBys(){
    return getFieldBuilders().stream().filter(field -> field.isDefaultOrderBy()).sorted(Ordering.natural().onResultOf(field -> field.getDefaultOrderByPriority())).map(field -> DocumentQueryOrderBy.byFieldName(field.getFieldName(), field.isDefaultOrderByAscending())).collect(DocumentQueryOrderByList.toDocumentQueryOrderByList());
}


public DocumentEntityDescriptor build(){
    assertNotBuilt();
    _built = true;
    return new DocumentEntityDescriptor(this);
}


public String getInternalName(){
    if (_internalName != null) {
        return _internalName;
    }
    return getId();
}


public DocumentFieldDependencyMap buildDependencies(){
    final DocumentFieldDependencyMap.Builder dependenciesBuilder = DocumentFieldDependencyMap.builder();
    dependenciesBuilder.add(DocumentFieldDependencyMap.DOCUMENT_Readonly, getReadonlyLogic().getParameterNames(), DependencyType.DocumentReadonlyLogic);
    getFields().values().stream().forEach(field -> dependenciesBuilder.add(field.getDependencies()));
    return dependenciesBuilder.build();
}


public List<DocumentFieldDescriptor.Builder> getIdFieldBuilders(){
    return _fieldBuilders.values().stream().filter(DocumentFieldDescriptor.Builder::isKey).collect(ImmutableList.toImmutableList());
}


public boolean isCalloutsEnabled(){
    return _calloutsEnabled;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("documentType", _documentType).add("documentTypeId", _documentTypeId).add("detailId", _detailId).add("caption", _caption).add("tableName", _tableName).toString();
}


public void updateFieldBuilders(Consumer<DocumentFieldDescriptor.Builder> fieldUpdater){
    assertFieldsNotBuilt();
    _fieldBuilders.values().stream().forEach(fieldUpdater);
}


public WindowId getWindowId(){
    Check.assume(_documentType == DocumentType.Window, "expected document type to be {} but it was {}", DocumentType.Window, _documentType);
    return WindowId.of(_documentTypeId);
}


public boolean hasField(String fieldName){
    return getFieldBuilder(fieldName) != null;
}


public Builder setSingleRowDetail(boolean singleRowDetail){
    this.singleRowDetail = singleRowDetail;
    return this;
}


}