package de.metas.ui.web.window.model.Document;
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
public class Builder {

 private  DocumentEntityDescriptor _entityDescriptor;

 private  Document _parentDocument;

 private  FieldInitializationMode _fieldInitializerMode;

 private  DocumentValuesSupplier _documentValuesSupplier;

 private  DocumentPath _documentPath;

 private  Integer _windowNo;

 private  AtomicInteger _nextWindowNo;

 private  IDocumentEvaluatee shadowParentDocumentEvaluatee;

 private  IDocumentChangesCollector changesCollector;


public Document initializeAsNewDocument(IntSupplier newDocumentIdSupplier,String version){
    return initializeAsNewDocument(new SimpleDocumentValuesSupplier(DocumentId.supplier(newDocumentIdSupplier), version));
}


public DocumentId getDocumentId(){
    return _documentValuesSupplier.getDocumentId();
}


public IDocumentChangesCollector getChangesCollector(){
    return changesCollector;
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


public boolean isNewDocument(){
    return _fieldInitializerMode == FieldInitializationMode.NewDocument;
}


public boolean isWritable(){
    final Document parentDocument = getParentDocument();
    if (parentDocument == null) {
        return isNewDocument();
    } else {
        return parentDocument.isWritable();
    }
}


public FieldInitializationMode getFieldInitializerMode(){
    return _fieldInitializerMode;
}


public Document initializeAsExistingRecord(DocumentValuesSupplier documentValuesSupplier){
    _fieldInitializerMode = FieldInitializationMode.Load;
    _documentValuesSupplier = documentValuesSupplier;
    return build();
}


public Builder setChangesCollector(IDocumentChangesCollector changesCollector){
    this.changesCollector = changesCollector;
    return this;
}


public Builder setShadowParentDocumentEvaluatee(IDocumentEvaluatee shadowParentDocumentEvaluatee){
    this.shadowParentDocumentEvaluatee = shadowParentDocumentEvaluatee;
    return this;
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


public Document getParentDocument(){
    return _parentDocument;
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


public DocumentField buildField(DocumentFieldDescriptor descriptor,Document document){
    return new DocumentField(descriptor, document);
}


public Builder setParentDocument(Document parentDocument){
    _parentDocument = parentDocument;
    return this;
}


public DocumentEntityDescriptor getEntityDescriptor(){
    return _entityDescriptor;
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


}