package DTO;
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

 private  boolean singleRowDetail;

 private  Optional<AdTabId> _adTabId;

 private  Optional<String> _tableName;

 private  Optional<SOTrx> _soTrx;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public int getFieldsCount(){
    return _fieldBuilders.size();
}


public Optional<AdTabId> getAdTabId(){
    return _adTabId;
}


public ITranslatableString getCaption(){
    return _caption;
}


public ILogicExpression getReadonlyLogic(){
    return _readonlyLogic;
}


public ILogicExpression getDisplayLogic(){
    return _displayLogic;
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


public DocumentFieldDescriptor.Builder getSingleIdFieldBuilderOrNull(){
    final List<DocumentFieldDescriptor.Builder> idFieldBuilders = getIdFieldBuilders();
    return idFieldBuilders.size() == 1 ? idFieldBuilders.get(0) : null;
}


public AdProcessId getPrintProcessId(){
    return _printProcessId;
}


public ImmutableList<DocumentFieldDescriptor> getIdFields(){
    return getFields().values().stream().filter(field -> field.isKey()).collect(ImmutableList.toImmutableList());
}


public Optional<String> getTableName(){
    return _tableName;
}


public T getDataBindingBuilder(Class<T> builderType){
    @SuppressWarnings("unchecked")
    final T dataBindingBuilder = (T) _dataBinding;
    return dataBindingBuilder;
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


public Optional<SOTrx> getSOTrx(){
    return _soTrx;
}


public ILogicExpression getAllowDeleteLogic(){
    return _allowDeleteLogic;
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


public ITranslatableString getDescription(){
    return _description;
}


public ILogicExpression getAllowCreateNewLogic(){
    return _allowCreateNewLogic;
}


public DocumentEntityDataBindingDescriptor getOrBuildDataBinding(){
    Preconditions.checkNotNull(_dataBinding, "dataBinding");
    return _dataBinding.getOrBuild();
}


public String getSingleIdFieldNameOrNull(){
    final List<DocumentFieldDescriptor.Builder> idFieldBuilders = getIdFieldBuilders();
    return idFieldBuilders.size() == 1 ? idFieldBuilders.get(0).getFieldName() : null;
}


public DetailId getDetailId(){
    return _detailId;
}


public DocumentFieldDescriptor.Builder getFieldBuilder(String fieldName){
    return _fieldBuilders.get(fieldName);
}


public Collection<DocumentFieldDescriptor.Builder> getFieldBuilders(){
    return _fieldBuilders.values();
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


public DocumentQueryOrderByList getDefaultOrderBys(){
    return getFieldBuilders().stream().filter(field -> field.isDefaultOrderBy()).sorted(Ordering.natural().onResultOf(field -> field.getDefaultOrderByPriority())).map(field -> DocumentQueryOrderBy.byFieldName(field.getFieldName(), field.isDefaultOrderByAscending())).collect(DocumentQueryOrderByList.toDocumentQueryOrderByList());
}


public String getInternalName(){
    if (_internalName != null) {
        return _internalName;
    }
    return getId();
}


public List<DocumentFieldDescriptor.Builder> getIdFieldBuilders(){
    return _fieldBuilders.values().stream().filter(DocumentFieldDescriptor.Builder::isKey).collect(ImmutableList.toImmutableList());
}


public WindowId getWindowId(){
    Check.assume(_documentType == DocumentType.Window, "expected document type to be {} but it was {}", DocumentType.Window, _documentType);
    return WindowId.of(_documentTypeId);
}


public Builder setDocumentType(AdWindowId adWindowId){
    return setDocumentType(DocumentType.Window, DocumentId.of(adWindowId.getRepoId()));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDocumentType"))

.queryParam("adWindowId",adWindowId);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public Builder setCaption(String caption){
    _caption = TranslatableStrings.constant(caption);
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCaption"))

.queryParam("caption",caption);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public Builder addField(DocumentFieldDescriptor.Builder fieldBuilder){
    assertFieldsNotBuilt();
    // 
    // Update field from entity
    fieldBuilder.setDetailId(getDetailId()).setEntityReadonlyLogic(getReadonlyLogic());
    // Add field
    _fieldBuilders.put(fieldBuilder.getFieldName(), fieldBuilder);
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addField"))

.queryParam("fieldBuilder",fieldBuilder);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public DocumentEntityDescriptor build(){
    assertNotBuilt();
    _built = true;
    return new DocumentEntityDescriptor(this);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/build"))

DocumentEntityDescriptor aux = restTemplate.getForObject(builder.toUriString(),DocumentEntityDescriptor.class);
return aux;
}


public Builder addElement(DocumentLayoutElementDescriptor.Builder elementBuilder){
    Check.assumeNotNull(elementBuilder, "Parameter elementBuilder is not null");
    elementBuilders.add(elementBuilder);
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addElement"))

.queryParam("elementBuilder",elementBuilder);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


}