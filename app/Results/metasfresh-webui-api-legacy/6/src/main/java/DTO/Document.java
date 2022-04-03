package DTO;
 import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import org.adempiere.ad.callout.api.ICalloutExecutor;
import org.adempiere.ad.callout.api.ICalloutRecord;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.ad.expression.api.IExpression;
import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.ILogicExpression;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import org.adempiere.ad.ui.spi.ExceptionHandledTabCallout;
import org.adempiere.ad.ui.spi.ITabCallout;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.service.ClientId;
import org.adempiere.util.lang.IAutoCloseable;
import org.compiere.util.Env;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.document.engine.IDocumentBL;
import de.metas.document.exceptions.DocumentProcessingException;
import de.metas.lang.SOTrx;
import de.metas.letters.model.Letters;
import de.metas.logging.LogManager;
import de.metas.organization.OrgId;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DataTypes;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDependencyMap;
import de.metas.ui.web.window.descriptor.DocumentFieldDependencyMap.DependencyType;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.exceptions.DocumentFieldNotFoundException;
import de.metas.ui.web.window.exceptions.DocumentFieldReadonlyException;
import de.metas.ui.web.window.exceptions.DocumentNotFoundException;
import de.metas.ui.web.window.exceptions.InvalidDocumentStateException;
import de.metas.ui.web.window.model.DocumentsRepository.SaveResult;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import de.metas.ui.web.window.model.IDocumentField.FieldInitializationMode;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
public class Document {

 private  Logger logger;

 public  Document NULL;

 private  ReasonSupplier REASON_Value_DirectSetOnDocument;

 private  ReasonSupplier REASON_Value_NewDocument;

 private  ReasonSupplier REASON_Value_Refreshing;

 private  ReasonSupplier REASON_Value_ParentLinkUpdateOnSave;

 private  DocumentEntityDescriptor entityDescriptor;

 private  int windowNo;

 private  DocumentPath documentPath;

 private  boolean _new;

 private  boolean _deleted;

 private  boolean _writable;

 private  FieldInitializationMode _initializingMode;

 private  DocumentValidStatus _valid;

 private  DocumentValidStatus _validOnCheckout;

 private  DocumentSaveStatus _saveStatus;

 private  DocumentSaveStatus _saveStatusOnCheckout;

 private  DocumentStaleState _staleStatus;

 private  ReentrantReadWriteLock _lock;

 private  DocumentReadonly parentReadonly;

 private  DocumentReadonly readonly;

 private  ITabCallout documentCallout;

 private  ICalloutExecutor fieldCalloutExecutor;

 private  DocumentAsCalloutRecord _calloutRecord;

 private  ImmutableMap<String,IDocumentField> fieldsByName;

 private  ImmutableList<IDocumentFieldView> idFields;

 private  IDocumentField parentLinkField;

 private  Document _parentDocument;

 private  Map<DetailId,IIncludedDocumentsCollection> includedDocuments;

 private  IDocumentEvaluatee _evaluatee;

 private  IDocumentEvaluatee _shadowParentEvaluatee;

 private  IDocumentChangesCollector changesCollector;

 private  Map<String,Object> _dynAttributes;

 private Object NO_VALUE;

 private  Supplier<DocumentId> documentIdSupplier;

 private  String version;

 private  DocumentValuesSupplier parentSupplier;

 private  DocumentType documentType;

 private  DocumentId documentTypeId;

 private  IDocumentEvaluatee _evaluatee;

 private  Object parentLinkValue;

 private  boolean writable;

 private OnValidStatusChanged DO_NOTHING;

 private OnValidStatusChanged MARK_NOT_SAVED;

 private  boolean staled;

 private  String version;

 private  DocumentEntityDescriptor _entityDescriptor;

 private  Document _parentDocument;

 private  FieldInitializationMode _fieldInitializerMode;

 private  DocumentValuesSupplier _documentValuesSupplier;

 private  DocumentPath _documentPath;

 private  Integer _windowNo;

 private  AtomicInteger _nextWindowNo;

 private  IDocumentEvaluatee shadowParentDocumentEvaluatee;

 private  IDocumentChangesCollector changesCollector;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public FieldInitializationMode getFieldInitializerMode(){
    return _fieldInitializerMode;
}


public int getWindowNo(){
    if (_windowNo == null) {
        final Document parentDocument = getParentDocument();
        if (parentDocument == null) {
            _windowNo = _nextWindowNo.incrementAndGet();
        } else {
            _windowNo = parentDocument.getWindowNo();
        }
    }
    return _windowNo;
}


public IDocumentEvaluatee getParentDocumentEvaluateeOrNull(){
    if (_shadowParentEvaluatee != null) {
        return _shadowParentEvaluatee;
    }
    final Document parentDocument = getParentDocument();
    if (parentDocument != null) {
        return parentDocument.asEvaluatee();
    }
    return null;
}


public Document getIncludedDocument(DetailId detailId,DocumentId rowId){
    final IIncludedDocumentsCollection includedDocuments = getIncludedDocumentsCollection(detailId);
    return includedDocuments.getDocumentById(rowId);
}


public DocumentId getDocumentId(){
    return _documentValuesSupplier.getDocumentId();
}


public IDocumentField getField(String fieldName){
    final IDocumentField documentField = getFieldOrNull(fieldName);
    if (documentField == null) {
        throw new DocumentFieldNotFoundException(this, fieldName);
    }
    return documentField;
}


public DocumentSaveStatus getSaveStatus(){
    return _saveStatus;
}


public ClientId getClientId(){
    final IDocumentField field = getFieldOrNull(WindowConstants.FIELDNAME_AD_Client_ID);
    return ClientId.ofRepoIdOrNull(field != null ? field.getValueAsInt(-1) : -1);
}


public IDocumentField getFieldOrNull(String fieldName){
    final IDocumentField documentField = fieldsByName.get(fieldName);
    return documentField;
}


public Set<String> getAvailableDynAttributes(){
    final Map<String, Object> dynAttributes = _dynAttributes;
    if (dynAttributes == null) {
        return ImmutableSet.of();
    }
    return ImmutableSet.copyOf(dynAttributes.keySet());
}


public ICalloutExecutor getFieldCalloutExecutor(){
    return fieldCalloutExecutor;
}


@Override
public String getVersion(){
    return parentSupplier.getVersion();
}


public DocumentsRepository getDocumentRepository(){
    return entityDescriptor.getDataBinding().getDocumentsRepository();
}


public Collection<IIncludedDocumentsCollection> getIncludedDocumentsCollections(){
    return includedDocuments.values();
}


public IDocumentEvaluatee getEvaluatee(DocumentFieldDescriptor fieldInScope){
    if (fieldInScope == null) {
        return _evaluatee;
    }
    return _evaluatee.fieldInScope(fieldInScope.getFieldName());
}


public Object getDocumentIdAsJson(){
    final DocumentId documentId = getDocumentId();
    if (documentId.isInt()) {
        return documentId.toInt();
    }
    return documentId.toString();
}


public DocumentPath getDocumentPath(){
    if (_documentPath == null) {
        final DocumentEntityDescriptor entityDescriptor = getEntityDescriptor();
        final DocumentId documentId = getDocumentId();
        final Document parentDocument = getParentDocument();
        if (parentDocument == null) {
            _documentPath = DocumentPath.rootDocumentPath(entityDescriptor.getDocumentType(), entityDescriptor.getDocumentTypeId(), documentId);
        } else {
            _documentPath = parentDocument.getDocumentPath().createChildPath(entityDescriptor.getDetailId(), documentId);
        }
    }
    return _documentPath;
}


public Properties getCtx(){
    // FIXME use document level context
    return Env.getCtx();
}


public OrderedDocumentsList getIncludedDocuments(DetailId detailId,DocumentIdsSelection documentIds){
    if (documentIds.isEmpty()) {
        return OrderedDocumentsList.newEmpty();
    }
    final IIncludedDocumentsCollection includedDocuments = getIncludedDocumentsCollection(detailId);
    return includedDocuments.getDocumentsByIds(documentIds);
}


public OrgId getOrgId(){
    final IDocumentField field = getFieldOrNull(WindowConstants.FIELDNAME_AD_Org_ID);
    return OrgId.ofRepoIdOrNull(field != null ? field.getValueAsInt(-1) : -1);
}


public DocumentValuesSupplier getDocumentValuesSupplierEffective(Document document){
    DocumentValuesSupplier documentValuesSupplierEffective = _documentValuesSupplier;
    // 
    // Initialize the fields
    if (isNewDocument()) {
        documentValuesSupplierEffective = new InitialFieldValueSupplier(document, _documentValuesSupplier);
    }
    return documentValuesSupplierEffective;
}


public DocumentReadonly getReadonly(){
    return readonly;
}


public Collection<IDocumentFieldView> getFieldViews(){
    final Collection<IDocumentField> documentFields = fieldsByName.values();
    return ImmutableList.<IDocumentFieldView>copyOf(documentFields);
}


public DocumentEntityDescriptor getEntityDescriptor(){
    return _entityDescriptor;
}


public T getDynAttribute(String name,T defaultValue){
    if (_dynAttributes == null) {
        return defaultValue;
    }
    final Object valueObj = _dynAttributes.get(name);
    if (valueObj == null) {
        return defaultValue;
    }
    @SuppressWarnings("unchecked")
    final T value = (T) valueObj;
    return value;
}


public IDocumentChangesCollector getChangesCollector(){
    return changesCollector;
}


public DocumentValidStatus getValidStatus(){
    return _valid;
}


public Collection<IDocumentField> getFields(){
    return fieldsByName.values();
}


public Set<DocumentStandardAction> getStandardActions(){
    final EnumSet<DocumentStandardAction> standardActions = EnumSet.allOf(DocumentStandardAction.class);
    // Remove Clone action if not supported
    if (!getEntityDescriptor().isCloneEnabled()) {
        standardActions.remove(DocumentStandardAction.Clone);
    }
    // Remove Print action if document is not printable (https://github.com/metasfresh/metasfresh-webui-api/issues/570)
    if (!getEntityDescriptor().isPrintable()) {
        standardActions.remove(DocumentStandardAction.Print);
    }
    // Remove letter action if functionality is not enabled (https://github.com/metasfresh/metasfresh-webui-api/issues/178)
    if (!Letters.isEnabled()) {
        standardActions.remove(DocumentStandardAction.Letter);
    }
    return standardActions;
}


public Document getParentDocument(){
    return _parentDocument;
}


public IIncludedDocumentsCollection getIncludedDocumentsCollection(DetailId detailId){
    final IIncludedDocumentsCollection includedDocumentsForDetailId = includedDocuments.get(detailId);
    if (includedDocumentsForDetailId == null) {
        throw new IllegalArgumentException("detailId '" + detailId + "' not found for " + this);
    }
    return includedDocumentsForDetailId;
}


public DocumentStaleState getStale(){
    return _staleStatus;
}


public int getDocumentIdAsInt(){
    return getDocumentId().toInt();
}


public Set<String> getFieldNames(){
    return fieldsByName.keySet();
}


public LookupValuesList getFieldLookupValuesForQuery(String fieldName,String query){
    return getField(fieldName).getLookupValuesForQuery(query);
}


public Document getRootDocument(){
    Document parent = getParentDocument();
    if (parent == null) {
        return this;
    }
    while (parent.getParentDocument() != null) {
        parent = parent.getParentDocument();
    }
    return parent;
}


public DetailId getDetailId(){
    return documentPath.getDetailId();
}


public DocumentId getDocumentIdOrNull(){
    // TODO handle NO ID field or composed PK
    if (idFields.size() != 1) {
        // Get it from document path.
        // This will cover the case of missing ID which was somehow generated internally
        if (getParentDocument() == null) {
            return getDocumentPath().getDocumentId();
        } else {
            return getDocumentPath().getSingleRowId();
        }
    }
    final Object idObj = idFields.get(0).getValue();
    return DocumentId.ofObjectOrNull(idObj);
}


public IDocumentFieldView getFieldView(String fieldName){
    return getField(fieldName);
}


public IDocumentFieldView getFieldViewOrNull(String fieldName){
    return getFieldOrNull(fieldName);
}


public LookupValuesList getFieldLookupValues(String fieldName){
    return getField(fieldName).getLookupValues();
}


@Override
public Object getValue(DocumentFieldDescriptor fieldDescriptor){
    // 
    // Ask parent first
    {
        final Object value = parentSupplier.getValue(fieldDescriptor);
        if (value != NO_VALUE) {
            return value;
        }
    }
    // 
    // Primary Key field
    if (fieldDescriptor.isKey()) {
        final DocumentId id = parentSupplier.getDocumentId();
        if (id == null) {
            return null;
        } else if (id.isInt()) {
            return id.toInt();
        } else {
            return id.toJson();
        }
    }
    // 
    // Parent link field
    if (fieldDescriptor.isParentLink()) {
        return parentLinkValue;
    }
    // 
    // Default value expression
    final IExpression<?> defaultValueExpression = fieldDescriptor.getDefaultValueExpression().orElse(null);
    if (defaultValueExpression != null) {
        final IDocumentEvaluatee evaluateeEffective = getEvaluatee(fieldDescriptor);
        final Object value = defaultValueExpression.evaluate(evaluateeEffective, OnVariableNotFound.Fail);
        if (value != null && String.class.equals(defaultValueExpression.getValueClass()) && Check.isEmpty(value.toString(), false)) {
            // FIXME: figure out how we can get rid of this hardcoded corner case! ... not sure if is needed
            logger.warn("Development hint: Converting default value empty string to null. Please check how can we avoid this case" + // 
            "\n FieldDescriptor: {}" + // 
            "\n Document: {}", fieldDescriptor, this);
            return null;
        }
        return value;
    }
    // 
    // Window User Preferences (only if it's not a virtual field)
    if (documentType == DocumentType.Window && !fieldDescriptor.isVirtualField()) {
        final Properties ctx = Env.getCtx();
        final AdWindowId adWindowId = documentTypeId.toId(AdWindowId::ofRepoId);
        final String fieldName = fieldDescriptor.getFieldName();
        // 
        // Preference (user) - P|
        {
            // retrieve Window level preferences
            final boolean retrieveGlobalPreferences = false;
            final String valueStr = Env.getPreference(ctx, adWindowId, fieldName, retrieveGlobalPreferences);
            if (!Check.isEmpty(valueStr, false)) {
                return valueStr;
            }
        }
        // 
        // Preference (System) - # $
        {
            final boolean retrieveGlobalPreferences = true;
            final String valueStr = Env.getPreference(ctx, adWindowId, fieldName, retrieveGlobalPreferences);
            if (!Check.isEmpty(valueStr, false)) {
                return valueStr;
            }
        }
    }
    // 
    // Fallback
    return DocumentValuesSupplier.NO_VALUE;
}


public IDocumentFieldView getFieldUpToRootOrNull(String fieldName){
    Document document = this;
    while (document != null) {
        final IDocumentFieldView field = document.getFieldOrNull(fieldName);
        if (field != null) {
            return field;
        }
        document = document.getParentDocument();
    }
    return null;
}


public void processValueChanges(List<JSONDocumentChangedEvent> events,ReasonSupplier reason){
    for (final JSONDocumentChangedEvent event : events) {
        if (JSONDocumentChangedEvent.JSONOperation.replace == event.getOperation()) {
            processValueChange(event.getPath(), event.getValue(), reason);
        } else {
            throw new IllegalArgumentException("Unknown operation: " + event);
        }
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/processValueChanges"))

.queryParam("events",events);
.queryParam("reason",reason);
restTemplate.put(builder.toUriString(),null);
}


public DocumentSaveStatus saveIfValidAndHasChanges(){
    // 
    // Update parent link field
    // TODO: i think this is no longer needed since we preallocate the IDs
    if (parentLinkField != null) {
        final Document parentDocument = getParentDocument();
        if (parentDocument != null) {
            final int parentLinkValueOld = parentLinkField.getValueAsInt(-1);
            final int parentLinkValueNew = parentDocument.getDocumentIdAsInt();
            if (parentLinkValueOld != parentLinkValueNew) {
                logger.warn("Updating parent link value: {} -> {}", parentLinkValueOld, parentLinkValueNew);
                setValue(parentLinkField, parentLinkValueNew, REASON_Value_ParentLinkUpdateOnSave);
            }
        }
    }
    // 
    // Check if valid for saving
    final DocumentValidStatus validState = checkAndGetValidStatus(OnValidStatusChanged.MARK_NOT_SAVED);
    // FIXME: i think this is no longer needed because we use OnValidStatusChanged.MARK_NOT_SAVED
    if (!validState.isValid()) {
        logger.debug("Skip saving because document {} is not valid: {}", this, validState);
        return setSaveStatusAndReturn(DocumentSaveStatus.notSaved(validState));
    }
    // 
    // Try saving it
    try {
        return saveIfHasChanges();
    } catch (final Exception saveEx) {
        // Directly propagate user validation exceptions
        // NOTE: this is kind of workaround until we really fix how we mark if a document/included document got some errors.
        // Known issue(s):
        // When saving an included document (e.g. a line) is failing, the exception is caught (here) but for header document,
        // so here we are flagging the header document instead of flagging the line.
        if (AdempiereException.isUserValidationError(saveEx)) {
            throw AdempiereException.wrapIfNeeded(saveEx);
        }
        // NOTE: usually if we do the right checks we shall not get to this
        logger.warn("Failed saving document, but IGNORED: {}", this, saveEx);
        setValidStatusAndReturn(DocumentValidStatus.invalid(saveEx), OnValidStatusChanged.DO_NOTHING);
        return setSaveStatusAndReturn(DocumentSaveStatus.notSaved(saveEx));
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveIfValidAndHasChanges"))

DocumentSaveStatus aux = restTemplate.getForObject(builder.toUriString(),DocumentSaveStatus.class);
return aux;
}


}