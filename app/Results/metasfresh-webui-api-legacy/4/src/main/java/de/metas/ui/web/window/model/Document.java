package de.metas.ui.web.window.model;
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


public FieldInitializationMode getFieldInitializerMode(){
    return _fieldInitializerMode;
}


public void updateAllDependencies(){
    logger.trace("Updating all dependencies for {}", this);
    // N/A
    final String triggeringFieldName = null;
    // Document level properties (e.g. docuemnt readonly)
    for (final String documentFieldName : DocumentFieldDependencyMap.DOCUMENT_ALL_FIELDS) {
        for (final DependencyType triggeringDependencyType : DocumentFieldDependencyMap.DEPENDENCYTYPES_DocumentLevel) {
            updateOnDependencyChanged(documentFieldName, (IDocumentField) null, triggeringFieldName, triggeringDependencyType);
        }
    }
    // Fields
    for (final IDocumentField documentField : getFields()) {
        for (final DependencyType triggeringDependencyType : DocumentFieldDependencyMap.DEPENDENCYTYPES_FieldLevel) {
            updateOnDependencyChanged(documentField.getFieldName(), documentField, triggeringFieldName, triggeringDependencyType);
        }
    }
}


public void markAsNotNew(){
    _new = false;
}


public boolean hasChangesRecursivelly(){
    // 
    // Check this document
    if (hasChanges()) {
        return true;
    }
    // 
    // Check included documents
    for (final IIncludedDocumentsCollection includedDocumentsPerDetailId : includedDocuments.values()) {
        if (includedDocumentsPerDetailId.hasChangesRecursivelly()) {
            return true;
        }
    }
    // no changes
    return false;
}


public boolean isInitializingNewDocument(){
    return isInitializing() && isNew();
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


public DocumentReadonly computeReadonly(){
    final ILogicExpression allFieldsReadonlyLogic = getEntityDescriptor().getReadonlyLogic();
    LogicExpressionResult allFieldsReadonly;
    try {
        allFieldsReadonly = allFieldsReadonlyLogic.evaluateToResult(asEvaluatee(), OnVariableNotFound.Fail);
    } catch (final Exception e) {
        allFieldsReadonly = LogicExpressionResult.FALSE;
        logger.warn("Failed evaluating entity readonly logic {} for {}. Considering {}", allFieldsReadonlyLogic, this, allFieldsReadonly, e);
    }
    final DocumentReadonly readonlyComputed = DocumentReadonly.builder().parentActive(parentReadonly.isActive()).active(isActive()).processed(parentReadonly.isProcessed() || isProcessed()).processing(parentReadonly.isProcessing() || isProcessing()).fieldsReadonly(allFieldsReadonly.booleanValue()).build();
    return readonlyComputed;
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


public Object setDynAttribute(String name,Object value){
    assertWritable();
    return setDynAttributeNoCheck(name, value);
}


public IDocumentField getField(String fieldName){
    final IDocumentField documentField = getFieldOrNull(fieldName);
    if (documentField == null) {
        throw new DocumentFieldNotFoundException(this, fieldName);
    }
    return documentField;
}


public IAutoCloseable lockForWriting(){
    // assume _lock is not null
    final WriteLock writeLock = _lock.writeLock();
    logger.debug("Acquiring write lock for {}: {}", this, writeLock);
    writeLock.lock();
    logger.debug("Acquired write lock for {}: {}", this, writeLock);
    return () -> {
        writeLock.unlock();
        logger.debug("Released write lock for {}: {}", this, writeLock);
    };
}


public void deleteFromRepository(){
    getDocumentRepository().delete(this);
    markAsDeleted();
}


public DocumentSaveStatus getSaveStatus(){
    return _saveStatus;
}


public DocumentSaveStatus setSaveStatusAndReturn(DocumentSaveStatus saveStatus){
    _saveStatus = saveStatus;
    final DocumentSaveStatus saveStatusOnCheckoutOld = _saveStatusOnCheckout;
    if (isInitializing()) {
        _saveStatusOnCheckout = saveStatus;
    }
    if (!isInitializingNewDocument() && !NullDocumentChangesCollector.isNull(changesCollector) && !saveStatus.equalsIgnoreReason(saveStatusOnCheckoutOld)) {
        changesCollector.collectDocumentSaveStatusChanged(getDocumentPath(), saveStatus);
    }
    return _saveStatus;
}


public void onChildSaved(Document document){
    getIncludedDocumentsCollection(document.getDetailId()).onChildSaved(document);
}


public IAutoCloseable lockForReading(){
    // assume _lock is not null
    final ReadLock readLock = _lock.readLock();
    logger.debug("Acquiring read lock for {}: {}", this, readLock);
    readLock.lock();
    logger.debug("Acquired read lock for {}: {}", this, readLock);
    return () -> {
        readLock.unlock();
        logger.debug("Released read lock for {}: {}", this, readLock);
    };
}


public ClientId getClientId(){
    final IDocumentField field = getFieldOrNull(WindowConstants.FIELDNAME_AD_Client_ID);
    return ClientId.ofRepoIdOrNull(field != null ? field.getValueAsInt(-1) : -1);
}


public void processDocAction(){
    assertWritable();
    final IDocumentField docActionField = getField(WindowConstants.FIELDNAME_DocAction);
    // 
    // Make sure it's saved
    saveIfValidAndHasChanges();
    if (hasChangesRecursivelly()) {
        // not relevant
        final String docAction = null;
        throw new DocumentProcessingException("Not all changes could be saved", this, docAction);
    }
    // 
    // Actually process the document
    // TODO: trigger the document workflow instead!
    final String docAction = docActionField.getValueAs(StringLookupValue.class).getIdAsString();
    // N/A
    final String expectedDocStatus = null;
    Services.get(IDocumentBL.class).processEx(this, docAction, expectedDocStatus);
    // 
    // Refresh it
    // and also mark all included documents as stale because it might be that processing add/removed/changed some data in included documents too
    refreshFromRepository();
    for (final IIncludedDocumentsCollection includedDocumentsPerDetail : includedDocuments.values()) {
        includedDocumentsPerDetail.markStaleAll();
    }
}


public void executeAllFieldCallouts(){
    fieldCalloutExecutor.executeAll((fieldName) -> {
        final IDocumentField documentField = getFieldOrNull(fieldName);
        if (documentField == null) {
            return null;
        }
        // Skip button callouts because it's expected to execute those callouts ONLY when the button is pressed
        final DocumentFieldWidgetType widgetType = documentField.getWidgetType();
        if (widgetType.isButton()) {
            return null;
        }
        return documentField.asCalloutField();
    });
}


public void updateOnDependencyChanged(String propertyName,IDocumentField documentField,String triggeringFieldName,DependencyType triggeringDependencyType){
    final ReasonSupplier reason = () -> "TriggeringField=" + triggeringFieldName + ", DependencyType=" + triggeringDependencyType;
    if (DependencyType.DocumentReadonlyLogic == triggeringDependencyType) {
        if (DocumentFieldDependencyMap.DOCUMENT_Readonly.equals(propertyName)) {
            updateReadonlyAndPropagate(reason);
        }
    } else if (DependencyType.ReadonlyLogic == triggeringDependencyType) {
        updateFieldReadOnlyAndCollect(documentField, reason);
    } else if (DependencyType.MandatoryLogic == triggeringDependencyType) {
        final LogicExpressionResult valueOld = documentField.getMandatory();
        final ILogicExpression mandatoryLogic = documentField.getDescriptor().getMandatoryLogic();
        try {
            final LogicExpressionResult mandatory = mandatoryLogic.evaluateToResult(asEvaluatee(), OnVariableNotFound.Fail);
            documentField.setMandatory(mandatory, changesCollector);
        } catch (final Exception e) {
            logger.warn("Failed evaluating mandatory logic {} for {}. Preserving {}", mandatoryLogic, documentField, documentField.getMandatory(), e);
        }
        changesCollector.collectMandatoryIfChanged(documentField, valueOld, reason);
    } else if (DependencyType.DisplayLogic == triggeringDependencyType) {
        final LogicExpressionResult valueOld = documentField.getDisplayed();
        updateFieldDisplayed(documentField);
        changesCollector.collectDisplayedIfChanged(documentField, valueOld, reason);
    } else if (DependencyType.LookupValues == triggeringDependencyType) {
        final boolean lookupValuesStaledOld = documentField.isLookupValuesStale();
        final boolean lookupValuesStaled = documentField.setLookupValuesStaled(triggeringFieldName);
        if (lookupValuesStaled && !lookupValuesStaledOld) {
            // https://github.com/metasfresh/metasfresh-webui-api/issues/551 check if we can leave the old value as it is
            final Object valueOld = documentField.getValue();
            if (valueOld != null) {
                final boolean currentValueStillValid = // because we did setLookupValuesStaled(), this causes a reload
                documentField.getLookupValues().stream().anyMatch(// check if the current value is still value after we reloaded the list
                value -> Objects.equals(value, valueOld));
                if (!currentValueStillValid) {
                    documentField.setValue(null, changesCollector);
                    changesCollector.collectValueIfChanged(documentField, valueOld, reason);
                }
            }
            // https://github.com/metasfresh/metasfresh-webui-frontend/issues/1165 - the value was not stale, but now it is => notify the frontend so it shall invalidate its cache
            changesCollector.collectLookupValuesStaled(documentField, reason);
        }
    } else if (DependencyType.FieldValue == triggeringDependencyType) {
        final IDocumentFieldValueProvider valueProvider = documentField.getDescriptor().getVirtualFieldValueProvider().orElse(null);
        if (valueProvider != null) {
            try {
                final Object valueOld = documentField.getValue();
                final Object valueNew = valueProvider.calculateValue(this);
                documentField.setInitialValue(valueNew, changesCollector);
                documentField.setValue(valueNew, changesCollector);
                changesCollector.collectValueIfChanged(documentField, valueOld, reason);
            } catch (final Exception ex) {
                logger.warn("Failed updating virtual field {} for {}", documentField, this, ex);
            }
        }
    } else {
        new AdempiereException("Unknown dependency type: " + triggeringDependencyType).throwIfDeveloperModeOrLogWarningElse(logger);
    }
}


public boolean isProcessing(){
    final IDocumentFieldView isActiveField = getFieldUpToRootOrNull(WindowConstants.FIELDNAME_Processing);
    // not processed if field missing
    return isActiveField != null ? isActiveField.getValueAsBoolean() : false;
}


public boolean isWritable(){
    final Document parentDocument = getParentDocument();
    if (parentDocument == null) {
        return isNewDocument();
    } else {
        return parentDocument.isWritable();
    }
}


public boolean isStaled(){
    return staled;
}


public boolean isInitializing(){
    return _initializingMode != null;
}


public IDocumentField getFieldOrNull(String fieldName){
    final IDocumentField documentField = fieldsByName.get(fieldName);
    return documentField;
}


public boolean isDeleted(){
    return _deleted;
}


public Set<String> getAvailableDynAttributes(){
    final Map<String, Object> dynAttributes = _dynAttributes;
    if (dynAttributes == null) {
        return ImmutableSet.of();
    }
    return ImmutableSet.copyOf(dynAttributes.keySet());
}


public Builder builder(DocumentEntityDescriptor entityDescriptor){
    return new Builder(entityDescriptor);
}


public ICalloutExecutor getFieldCalloutExecutor(){
    return fieldCalloutExecutor;
}


public void setParentReadonly(DocumentReadonly parentReadonly){
    final DocumentReadonly parentReadonlyOld = this.parentReadonly;
    this.parentReadonly = parentReadonly;
    if (!Objects.equals(parentReadonlyOld, parentReadonly)) {
        updateReadonlyAndPropagate(() -> "parent readonly state changed");
        updateIncludedDetailsStatus();
    }
}


public Document copy(Document parentDocumentCopy,CopyMode copyMode){
    return new Document(this, parentDocumentCopy, copyMode, parentDocumentCopy.changesCollector);
}


public void deleteIncludedDocuments(DetailId detailId,DocumentIdsSelection rowIds){
    final IIncludedDocumentsCollection includedDocuments = getIncludedDocumentsCollection(detailId);
    includedDocuments.deleteDocuments(rowIds);
    checkAndGetValidStatus();
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


public void updateReadonlyAndPropagate(ReasonSupplier reason){
    final DocumentReadonly readonlyOld = this.readonly;
    final DocumentReadonly readonlyNew = computeReadonly();
    if (Objects.equals(readonlyOld, readonlyNew)) {
        return;
    }
    this.readonly = readonlyNew;
    getFields().forEach(documentField -> updateFieldReadOnlyAndCollect(documentField, reason));
}


public boolean isNewDocument(){
    return _fieldInitializerMode == FieldInitializationMode.NewDocument;
}


public void assertWritable(){
    if (isInitializing()) {
        return;
    }
    if (!isWritable()) {
        throw new InvalidDocumentStateException(this, "not a writable copy");
    }
    if (isDeleted()) {
        throw new DocumentNotFoundException(getDocumentPath());
    }
}


public void updateIncludedDetailsStatus(){
    includedDocuments.values().forEach(IIncludedDocumentsCollection::updateStatusFromParent);
}


public void updateFieldDisplayed(IDocumentField documentField){
    // default false, i.e. not displayed
    LogicExpressionResult displayed = LogicExpressionResult.FALSE;
    final ILogicExpression displayLogic = documentField.getDescriptor().getDisplayLogic();
    try {
        displayed = displayLogic.evaluateToResult(asEvaluatee(), OnVariableNotFound.Fail);
    } catch (final Exception e) {
        logger.warn("Failed evaluating display logic {} for {}. Preserving {}", displayLogic, documentField, documentField.getDisplayed(), e);
        displayed = LogicExpressionResult.FALSE;
    }
    documentField.setDisplayed(displayed);
}


public void updateFieldsWhichDependsOn(String triggeringFieldName){
    final DocumentFieldDependencyMap dependencies = getEntityDescriptor().getDependencies();
    dependencies.consumeForChangedFieldName(triggeringFieldName, (dependentFieldName, dependencyType) -> {
        final IDocumentField dependentField = getFieldOrNull(dependentFieldName);
        if (dependentField == null) {
            // shall not happen
            logger.warn("Skip setting dependent propery {} because field is missing", dependentFieldName);
            return;
        }
        updateOnDependencyChanged(dependentFieldName, dependentField, triggeringFieldName, dependencyType);
    });
}


public Document initializeAsExistingRecord(DocumentValuesSupplier documentValuesSupplier){
    _fieldInitializerMode = FieldInitializationMode.Load;
    _documentValuesSupplier = documentValuesSupplier;
    return build();
}


public void processValueChanges(List<JSONDocumentChangedEvent> events,ReasonSupplier reason){
    for (final JSONDocumentChangedEvent event : events) {
        if (JSONDocumentChangedEvent.JSONOperation.replace == event.getOperation()) {
            processValueChange(event.getPath(), event.getValue(), reason);
        } else {
            throw new IllegalArgumentException("Unknown operation: " + event);
        }
    }
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


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("staled", staled).add("version", version).add("document", Document.this).toString();
}


public DocumentValidStatus setValidStatusAndReturn(DocumentValidStatus valid,OnValidStatusChanged onValidStatusChanged){
    // shall not happen
    Preconditions.checkNotNull(valid, "valid");
    // Don't check if changed because we want ALWAYS to collect the valid status
    // final DocumentValidStatus validOld = _valid;
    // if (Objects.equals(validOld, valid))
    // {
    // return validOld; // no change
    // }
    _valid = valid;
    if (isInitializing()) {
        _validOnCheckout = valid;
    }
    if (!isInitializingNewDocument() && !Objects.equals(valid, _validOnCheckout)) {
        changesCollector.collectDocumentValidStatusChanged(getDocumentPath(), valid);
    }
    if (!valid.isValid()) {
        onValidStatusChanged.onInvalidStatus(this, valid);
    }
    return valid;
}


public boolean hasField(String fieldName){
    return fieldsByName.containsKey(fieldName);
}


public OrderedDocumentsList getIncludedDocuments(DetailId detailId,DocumentIdsSelection documentIds){
    if (documentIds.isEmpty()) {
        return OrderedDocumentsList.newEmpty();
    }
    final IIncludedDocumentsCollection includedDocuments = getIncludedDocumentsCollection(detailId);
    return includedDocuments.getDocumentsByIds(documentIds);
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
}


public void updateFieldReadOnlyAndCollect(IDocumentField documentField,ReasonSupplier reason){
    final LogicExpressionResult readonlyOld = documentField.getReadonly();
    final LogicExpressionResult readonlyNew = computeFieldReadOnly(documentField);
    if (readonlyNew != null) {
        documentField.setReadonly(readonlyNew);
        changesCollector.collectReadonlyIfChanged(documentField, readonlyOld, reason);
    }
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


public void refreshFromSupplier(DocumentValuesSupplier documentValuesSupplier){
    initializeFields(FieldInitializationMode.Refresh, documentValuesSupplier);
}


public boolean isActive(){
    final IDocumentFieldView isActiveField = getFieldUpToRootOrNull(WindowConstants.FIELDNAME_IsActive);
    // active if field not found (shall not happen)
    return isActiveField != null ? isActiveField.getValueAsBoolean() : true;
}


public Document refreshFromRepositoryIfStaled(){
    if (getEntityDescriptor().getDataBinding().isVersioningSupported()) {
        if (getStale().checkStaled()) {
            refreshFromRepository();
        }
    }
    return this;
}


public void onInvalidStatus(Document document,DocumentValidStatus invalidStatus)


public boolean isRootDocument(){
    return getParentDocument() == null;
}


public Builder setChangesCollector(IDocumentChangesCollector changesCollector){
    this.changesCollector = changesCollector;
    return this;
}


public Collection<IDocumentFieldView> getFieldViews(){
    final Collection<IDocumentField> documentFields = fieldsByName.values();
    return ImmutableList.<IDocumentFieldView>copyOf(documentFields);
}


public ImmutableMap<DetailId,IIncludedDocumentsCollection> extractIncludedDocuments(Collection<DocumentEntityDescriptor> includedEntities){
    final ImmutableMap.Builder<DetailId, IIncludedDocumentsCollection> includedDocuments = ImmutableMap.builder();
    for (final DocumentEntityDescriptor includedEntityDescriptor : includedEntities) {
        // if (!includedEntityDescriptor.getFields().isEmpty())
        // {
        final DetailId detailId = includedEntityDescriptor.getDetailId();
        final IIncludedDocumentsCollection includedDocumentsForDetailId = includedEntityDescriptor.createIncludedDocumentsCollection(this);
        includedDocuments.put(detailId, includedDocumentsForDetailId);
        // }
        // recurse
        includedDocuments.putAll(extractIncludedDocuments(includedEntityDescriptor.getIncludedEntities()));
    }
    return includedDocuments.build();
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


public Document createIncludedDocument(DetailId detailId){
    final IIncludedDocumentsCollection includedDocuments = getIncludedDocumentsCollection(detailId);
    return includedDocuments.createNewDocument();
}


public DocumentValidStatus checkAndGetValidStatus(OnValidStatusChanged onValidStatusChanged){
    // 
    // Check document fields
    for (final IDocumentField documentField : getFields()) {
        // skip virtual fields, those does not matter
        if (documentField.isVirtualField()) {
            continue;
        }
        final DocumentValidStatus validState = documentField.updateStatusIfInitialInvalidAndGet(changesCollector);
        if (!validState.isValid()) {
            logger.trace("Considering document invalid because {} is not valid: {}", documentField, validState);
            return setValidStatusAndReturn(validState, onValidStatusChanged);
        }
    }
    // 
    // Check included documents
    for (final IIncludedDocumentsCollection includedDocumentsPerDetailId : includedDocuments.values()) {
        final DocumentValidStatus validState = includedDocumentsPerDetailId.checkAndGetValidStatus(onValidStatusChanged);
        if (!validState.isValid()) {
            logger.trace("Considering document invalid because {} is not valid: {}", includedDocumentsPerDetailId, validState);
            return setValidStatusAndReturn(DocumentValidStatus.invalidIncludedDocument(), onValidStatusChanged);
        }
    }
    // valid
    return setValidStatusAndReturn(DocumentValidStatus.documentValid(), onValidStatusChanged);
}


public void processValueChange(String fieldName,Object value,ReasonSupplier reason){
    final IDocumentField documentField = getField(fieldName);
    if (documentField.isReadonly()) {
        throw new DocumentFieldReadonlyException(fieldName, value);
    }
    setValue(documentField, value, reason);
    // FIXME: hardcoded DocAction processing
    if (WindowConstants.FIELDNAME_DocAction.equals(fieldName) && DocumentType.Window.equals(getDocumentPath().getDocumentType())) {
        processDocAction();
    }
}


public Collection<IDocumentField> getFields(){
    return fieldsByName.values();
}


public void refreshFromRepository(){
    getDocumentRepository().refresh(this);
}


public Builder setShadowParentDocumentEvaluatee(IDocumentEvaluatee shadowParentDocumentEvaluatee){
    this.shadowParentDocumentEvaluatee = shadowParentDocumentEvaluatee;
    return this;
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


public boolean isProcessed(){
    final IDocumentFieldView isActiveField = getFieldUpToRootOrNull(WindowConstants.FIELDNAME_Processed);
    // not processed if field missing
    return isActiveField != null ? isActiveField.getValueAsBoolean() : false;
}


public IIncludedDocumentsCollection getIncludedDocumentsCollection(DetailId detailId){
    final IIncludedDocumentsCollection includedDocumentsForDetailId = includedDocuments.get(detailId);
    if (includedDocumentsForDetailId == null) {
        throw new IllegalArgumentException("detailId '" + detailId + "' not found for " + this);
    }
    return includedDocumentsForDetailId;
}


public DocumentField buildField(DocumentFieldDescriptor descriptor,Document document){
    return new DocumentField(descriptor, document);
}


public DocumentSaveStatus saveIfHasChanges(){
    boolean wasNew = isNew();
    // 
    // Save this document
    boolean deleted = false;
    if (hasChanges()) {
        final SaveResult saveResult = getDocumentRepository().save(this);
        if (saveResult == SaveResult.DELETED) {
            deleted = true;
        }
        documentCallout.onSave(asCalloutRecord());
        logger.debug("Document saved: {}", this);
    } else {
        logger.debug("Skip saving because document has NO change: {}", this);
    }
    // Update "wasNew" flag: true only if the document was new before and we just save it now.
    wasNew = wasNew && !isNew();
    // 
    // Try also saving the included documents
    for (final IIncludedDocumentsCollection includedDocumentsForDetailId : includedDocuments.values()) {
        includedDocumentsForDetailId.saveIfHasChanges();
        // If document was new we need to invalidate all included documents.
        // NOTE: Usually this has no real effect besides some corner cases like BPartner window where Vendor and Customer tabs are referencing exactly the same record as the header.
        if (wasNew) {
            includedDocumentsForDetailId.markStaleAll();
        }
    }
    return setSaveStatusAndReturn(deleted ? DocumentSaveStatus.deleted() : DocumentSaveStatus.saved());
}


public Builder setParentDocument(Document parentDocument){
    _parentDocument = parentDocument;
    return this;
}


public Document initializeAsNewDocument(IntSupplier newDocumentIdSupplier,String version){
    return initializeAsNewDocument(new SimpleDocumentValuesSupplier(DocumentId.supplier(newDocumentIdSupplier), version));
}


public boolean hasDynAttribute(String name){
    final Map<String, Object> dynAttributes = _dynAttributes;
    return dynAttributes != null && dynAttributes.get(name) != null;
}


public LogicExpressionResult computeFieldReadOnly(IDocumentField documentField){
    // Check document's readonly logic
    final DocumentReadonly documentReadonlyLogic = getReadonly();
    if (documentReadonlyLogic.computeFieldReadonly(documentField.getFieldName(), documentField.isAlwaysUpdateable())) {
        return LogicExpressionResult.TRUE;
    }
    // Check field's readonly logic
    final ILogicExpression fieldReadonlyLogic = documentField.getDescriptor().getReadonlyLogic();
    try {
        final LogicExpressionResult readonly = fieldReadonlyLogic.evaluateToResult(asEvaluatee(), OnVariableNotFound.Fail);
        return readonly;
    } catch (final Exception e) {
        logger.warn("Failed evaluating readonly logic {} for {}. Preserving {}", fieldReadonlyLogic, documentField, documentField.getReadonly(), e);
        return null;
    }
}


public void initializeField(IDocumentField documentField,FieldInitializationMode mode,DocumentValuesSupplier fieldValueSupplier){
    boolean valueSet = false;
    Object valueOld = null;
    try {
        final DocumentFieldDescriptor fieldDescriptor = documentField.getDescriptor();
        // 
        // Get the initialization value
        final Object initialValue = fieldValueSupplier.getValue(fieldDescriptor);
        // 
        // Update field's initial value
        if (initialValue != DocumentValuesSupplier.NO_VALUE) {
            valueOld = documentField.getValue();
            documentField.setInitialValue(initialValue, changesCollector);
        }
        valueSet = true;
    } catch (final Exception e) {
        valueSet = false;
        if (FieldInitializationMode.NewDocument == mode) {
            logger.warn("Failed initializing {}, mode={} using {}. Keeping current initial value.", documentField, mode, fieldValueSupplier, e);
        } else {
            throw AdempiereException.wrapIfNeeded(e).appendParametersToMessage().setParameter("mode", mode).setParameter("documentField", documentField).setParameter("fieldValueSupplier", fieldValueSupplier);
        }
    }
    // 
    // After field was initialized, based on "mode", trigger events, update other fields etc
    if (FieldInitializationMode.NewDocument == mode) {
        // Collect the change event, even if there was no change because we just set the initial value
        changesCollector.collectValueChanged(documentField, REASON_Value_NewDocument);
    // NOTE: don't update fields flags which depend on this field because we will do it all together after all fields are initialized
    // NOTE: don't call callouts because we will do it all together after all fields are initialized
    } else if (FieldInitializationMode.Load == mode) {
    // NOTE: don't collect field changes because we are just initializing a new Document instance from an existing database record.
    // NOTE: don't update fields flags which depend on this field because we will do it all together after all fields are initialized
    // NOTE: don't call callouts because this was not a user change.
    } else if (FieldInitializationMode.Refresh == mode) {
        // NOTE: don't update fields flags which depend on this field because we will do it all together after all fields are initialized
        // NOTE: don't call callouts because this was not a user change.
        if (valueSet) {
            changesCollector.collectValueIfChanged(documentField, valueOld, REASON_Value_Refreshing);
        }
    }
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


public IDocumentEvaluatee asEvaluatee(){
    if (_evaluatee == null) {
        _evaluatee = new DocumentEvaluatee(this);
    }
    return _evaluatee;
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


public boolean checkStaled(){
    if (staled) {
        return true;
    }
    if (isNew()) {
        return false;
    }
    final String versionNow = getDocumentRepository().retrieveVersion(getEntityDescriptor(), getDocumentIdAsInt());
    if (Objects.equals(version, versionNow)) {
        return false;
    }
    staled = true;
    return true;
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


public ReentrantReadWriteLock createLock(){
    // don't create locks for any other entity which is not window
    final DocumentEntityDescriptor entityDescriptor = getEntityDescriptor();
    if (entityDescriptor.getDocumentType() != DocumentType.Window) {
        return null;
    }
    // 
    final Document parentDocument = getParentDocument();
    if (parentDocument == null) {
        return new ReentrantReadWriteLock();
    } else {
        // don't create lock for included documents
        return null;
    }
}


public IDocumentFieldView getFieldViewOrNull(String fieldName){
    return getFieldOrNull(fieldName);
}


public ICalloutRecord asCalloutRecord(){
    if (_calloutRecord == null) {
        _calloutRecord = new DocumentAsCalloutRecord(this);
    }
    return _calloutRecord;
}


public void assertNewDocumentAllowed(DetailId detailId){
    getIncludedDocumentsCollection(detailId).assertNewDocumentAllowed();
}


public boolean hasChanges(){
    // If this is a new document then always consider it as changed
    if (isNew()) {
        return true;
    }
    boolean changes = false;
    // 
    // Check document fields
    for (final IDocumentField documentField : getFields()) {
        if (documentField.hasChangesToSave()) {
            logger.trace("Considering document has changes because {} is changed", documentField);
            changes = true;
            break;
        }
    }
    return changes;
}


public Object setDynAttributeNoCheck(String name,Object value){
    Check.assumeNotEmpty(name, "name not empty");
    if (_dynAttributes == null) {
        _dynAttributes = new HashMap<>();
    }
    final Object valueOld = _dynAttributes.put(name, value);
    logger.trace("Changed document dyn attribute {}'s value: {} -> {}", name, valueOld, value);
    return valueOld;
}


public boolean isNew(){
    return _new;
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


public void markNotStaled(String version){
    staled = false;
    this.version = version;
}


public Document build(){
    final Document document = new Document(this);
    if (shadowParentDocumentEvaluatee != null) {
        document.setShadowParentDocumentEvaluatee(shadowParentDocumentEvaluatee);
    }
    final DocumentEntityDescriptor entityDescriptor = getEntityDescriptor();
    final ITabCallout documentCallout = entityDescriptor.createAndInitializeDocumentCallout(document.asCalloutRecord());
    document.documentCallout = ExceptionHandledTabCallout.wrapIfNeeded(documentCallout);
    // 
    // Initialize document fields
    final FieldInitializationMode fieldInitializerMode = getFieldInitializerMode();
    final DocumentValuesSupplier documentValuesSupplierEffective = getDocumentValuesSupplierEffective(document);
    document.initializeFields(fieldInitializerMode, documentValuesSupplierEffective);
    return document;
}


public void markAsDeleted(){
    _deleted = true;
    changesCollector.collectDeleted(getDocumentPath());
}


public void setValue(IDocumentField documentField,Object value,ReasonSupplier reason){
    assertWritable();
    final Object valueOld = documentField.getValue();
    documentField.setValue(value, changesCollector);
    // Check if changed. If not, stop here.
    final Object valueNew = documentField.getValue();
    if (DataTypes.equals(valueOld, valueNew)) {
        return;
    }
    // collect changed value
    changesCollector.collectValueChanged(documentField, reason != null ? reason : REASON_Value_DirectSetOnDocument);
    // Update all dependencies
    updateFieldsWhichDependsOn(documentField.getFieldName());
    // Callouts
    fieldCalloutExecutor.execute(documentField.asCalloutField());
    // Notify parent that one of it's children was changed
    if (!isRootDocument() && hasChanges()) {
        getParentDocument().getIncludedDocumentsCollection(getDetailId()).onChildChanged(this);
    }
}


public void initializeFields(FieldInitializationMode mode,DocumentValuesSupplier documentValuesSupplier){
    logger.trace("Initializing fields: mode={}", mode);
    if (_initializingMode != null) {
        throw new InvalidDocumentStateException(this, "already initializing");
    }
    _initializingMode = mode;
    try {
        // 
        // Actually initialize document fields
        for (final IDocumentField documentField : getFields()) {
            initializeField(documentField, mode, documentValuesSupplier);
        }
        // 
        // Fire callouts
        if (FieldInitializationMode.NewDocument == mode) {
            // FIXME: i think it would be better to trigger the callouts when setting the initial value
            try {
                executeAllFieldCallouts();
            } catch (final Exception e) {
                logger.warn("Failed executing callouts while initializing {}. Ignored.", this, e);
            }
            documentCallout.onNew(asCalloutRecord());
        } else if (FieldInitializationMode.Load == mode) {
        // don't call the callouts on load
        // we shall have the document as it is
        } else if (FieldInitializationMode.Refresh == mode) {
            documentCallout.onRefresh(asCalloutRecord());
        }
        // 
        // Update field's flags (readonly, mandatory etc)
        updateAllDependencies();
        // 
        // Mark the document not staled because we just initialized it
        getStale().markNotStaled(documentValuesSupplier.getVersion());
        // 
        // Update document's valid status
        checkAndGetValidStatus(OnValidStatusChanged.DO_NOTHING);
        updateIncludedDetailsStatus();
        // 
        // Update document's save status
        if (mode == FieldInitializationMode.NewDocument) {
            setSaveStatusAndReturn(DocumentSaveStatus.notSavedJustCreated());
        } else if (mode == FieldInitializationMode.Load || mode == FieldInitializationMode.Refresh) {
            setSaveStatusAndReturn(DocumentSaveStatus.savedJustLoaded());
        } else {
            throw new IllegalArgumentException("Unknown initialization mode: " + mode);
        }
    } finally {
        _initializingMode = null;
    }
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


}