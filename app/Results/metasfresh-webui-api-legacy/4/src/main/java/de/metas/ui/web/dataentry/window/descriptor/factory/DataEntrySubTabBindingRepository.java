package de.metas.ui.web.dataentry.window.descriptor.factory;
 import de.metas.util.Check.assumeNotNull;
import java.util.Optional;
import java.util.function.Function;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.ITableRecordReference;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.Adempiere;
import org.compiere.util.Env;
import de.metas.dataentry.DataEntryFieldId;
import de.metas.dataentry.DataEntrySubTabId;
import de.metas.dataentry.FieldType;
import de.metas.dataentry.data.DataEntryRecord;
import de.metas.dataentry.data.DataEntryRecordQuery;
import de.metas.dataentry.data.DataEntryRecordRepository;
import de.metas.dataentry.model.I_DataEntry_SubTab;
import de.metas.ui.web.window.controller.DocumentPermissionsHelper;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.Document.DocumentValuesSupplier;
import de.metas.ui.web.window.model.DocumentQuery;
import de.metas.ui.web.window.model.DocumentsRepository;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.ui.web.window.model.IDocumentFieldView;
import de.metas.ui.web.window.model.OrderedDocumentsList;
import de.metas.user.UserId;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
public class DataEntrySubTabBindingRepository implements DocumentsRepository{

 private  String VERSION_DEFAULT;

 private  DataEntryRecordRepository dataEntryRecordRepository;

 private  DataEntryWebuiTools dataEntryWebuiTools;

 private  DataEntryRecord dataEntryRecord;

 private  DataEntryWebuiTools dataEntryWebuiTools;


@Override
public String getVersion(){
    return VERSION_DEFAULT;
}


@Override
public DocumentId getDocumentId(){
    final DocumentId documentId = DataEntrySubTabBindingRepository.createDocumentId(dataEntryRecord.getDataEntrySubTabId(), dataEntryRecord.getMainRecord());
    return documentId;
}


public void assertValidState(Document document){
    assertThisRepository(document.getEntityDescriptor());
    if (Adempiere.isUnitTestMode()) {
        return;
    }
    DocumentPermissionsHelper.assertCanEdit(document);
    Services.get(ITrxManager.class).assertThreadInheritedTrxExists();
}


@Override
public DocumentId retrieveParentDocumentId(DocumentEntityDescriptor parentEntityDescriptor,DocumentQuery childDocumentQuery){
    return DocumentId.of(childDocumentQuery.getParentLinkIdAsInt());
}


public DataEntryRecordQuery extractDataEntryRecordQuery(Document document){
    final DetailId detailId = document.getEntityDescriptor().getDetailId();
    final DataEntrySubTabId subGroupId = extractDataEntrySubGroupId(detailId);
    final Document parentDocument = document.getParentDocument();
    final TableRecordReference parentRecordReference = extractParentRecordReference(parentDocument);
    return DataEntryRecordQuery.builder().dataEntrySubTabId(subGroupId).recordId(parentRecordReference.getRecord_ID()).build();
}


@Override
public SaveResult save(Document document){
    assertValidState(document);
    final DataEntryRecord dataEntryRecord;
    if (document.isNew()) {
        dataEntryRecord = createDataEntryRecord(document);
    } else {
        final DataEntryRecordQuery dataEntryRecordQuery = extractDataEntryRecordQuery(document);
        dataEntryRecord = dataEntryRecordRepository.getBy(dataEntryRecordQuery).orElseThrow(() -> new AdempiereException("Unable to retrieve dataEntryRecord for query=" + dataEntryRecordQuery));
    }
    boolean refreshNeeded = updateDataEntryRecord(document, dataEntryRecord);
    dataEntryRecordRepository.save(dataEntryRecord);
    if (// at least one value was changed
    refreshNeeded) {
        refreshFromDataEntryRecord(document, Optional.of(dataEntryRecord));
    }
    // Notify the parent document that one of it's children were saved (copied from SqlDocumentsRepository)
    document.getParentDocument().onChildSaved(document);
    return SaveResult.SAVED;
}


public DataEntryRecord createDataEntryRecord(Document document){
    final TableRecordReference parentReference = extractParentRecordReference(document.getParentDocument());
    final DetailId detailId = document.getEntityDescriptor().getDetailId();
    final DataEntrySubTabId dataEntrySubTabId = extractDataEntrySubGroupId(detailId);
    return DataEntryRecord.builder().mainRecord(parentReference).dataEntrySubTabId(dataEntrySubTabId).build();
}


@Override
public void refresh(Document document){
    assertValidState(document);
    final DataEntryRecordQuery dataEntryRecordQuery = extractDataEntryRecordQuery(document);
    final Optional<DataEntryRecord> dataEntryRecord = dataEntryRecordRepository.getBy(dataEntryRecordQuery);
    refreshFromDataEntryRecord(document, dataEntryRecord);
}


public TableRecordReference extractParentRecordReference(Document parentDocument){
    final String tableName = assumeNotNull(parentDocument.getEntityDescriptor().getTableNameOrNull(), "The parent of dataEntry a document needs to have a table name; parentDocument={}", parentDocument);
    final TableRecordReference parentReference = TableRecordReference.of(tableName, parentDocument.getDocumentIdAsInt());
    return parentReference;
}


@Override
public void delete(Document document){
    assertValidState(document);
    final DataEntryRecordQuery dataEntryRecordQuery = extractDataEntryRecordQuery(document);
    dataEntryRecordRepository.deleteBy(dataEntryRecordQuery);
}


public DocumentId createDocumentId(DataEntrySubTabId subGroupId,ITableRecordReference parentRecordReference){
    final String documentIdStr = new StringBuilder().append("T").append(parentRecordReference.getTableName()).append("-R").append(parentRecordReference.getRecord_ID()).append("-SG").append(subGroupId.getRepoId()).toString();
    final DocumentId documentId = DocumentId.of(documentIdStr);
    return documentId;
}


@Override
public Document retrieveDocument(DocumentQuery query,IDocumentChangesCollector changesCollector){
    return retrieveDocumentIfExists(query, changesCollector).orElseGet(() -> createNewDocument(query.getEntityDescriptor(), query.getParentDocument(), changesCollector));
}


@Override
public Object getValue(DocumentFieldDescriptor fieldDescriptor){
    return dataEntryWebuiTools.extractDataEntryValueForField(dataEntryRecord, fieldDescriptor);
}


@Override
public OrderedDocumentsList retrieveDocuments(DocumentQuery query,IDocumentChangesCollector changesCollector){
    final OrderedDocumentsList documentsCollector = OrderedDocumentsList.newEmpty(query.getOrderBys());
    final Document document = retrieveDocumentIfExists(query, changesCollector).orElseGet(() -> createNewDocument(query.getEntityDescriptor(), query.getParentDocument(), changesCollector));
    documentsCollector.addDocument(document);
    return documentsCollector;
}


@Override
public int retrieveLastLineNo(DocumentQuery query){
    return 0;
}


public boolean updateDataEntryRecord(Document document,DataEntryRecord dataEntryRecord){
    boolean refreshNeeded = false;
    final UserId userId = UserId.ofRepoId(Env.getAD_User_ID(document.getCtx()));
    for (final IDocumentFieldView fieldView : document.getFieldViews()) {
        final DataEntryFieldBindingDescriptor dataBinding = fieldView.getDescriptor().getDataBindingNotNull(DataEntryFieldBindingDescriptor.class);
        final FieldType fieldType = dataBinding.getFieldType();
        if (fieldType.equals(FieldType.SUB_TAB_ID) || fieldType.equals(FieldType.PARENT_LINK_ID) || fieldType.equals(FieldType.CREATED_UPDATED_INFO)) {
            continue;
        }
        final Object dataEntryFieldValue = dataEntryWebuiTools.extractFieldValueForDataEntry(fieldView);
        final DataEntryFieldId dataEntryFieldId = dataEntryWebuiTools.computeDataEntryFieldId(fieldView);
        final boolean valueChanged = dataEntryRecord.setRecordField(dataEntryFieldId, userId, dataEntryFieldValue);
        refreshNeeded = refreshNeeded || valueChanged;
    }
    return refreshNeeded;
}


public Optional<Document> retrieveDocumentIfExists(DocumentQuery query,IDocumentChangesCollector changesCollector){
    final DocumentEntityDescriptor entityDescriptor = query.getEntityDescriptor();
    final Document parentDocument = query.getParentDocument();
    final Function<DocumentId, Document> existingDocumentsSupplier = query.getExistingDocumentsSupplier();
    final DetailId detailId = query.getEntityDescriptor().getDetailId();
    final DataEntrySubTabId dataEntrySubTabId = extractDataEntrySubGroupId(detailId);
    final TableRecordReference parentRecordReference = extractParentRecordReference(parentDocument);
    final DataEntryRecordQuery dataEntryRecordQuery = DataEntryRecordQuery.builder().dataEntrySubTabId(dataEntrySubTabId).recordId(parentRecordReference.getRecord_ID()).build();
    final Optional<DataEntryRecord> dataEntryRecord = dataEntryRecordRepository.getBy(dataEntryRecordQuery);
    if (!dataEntryRecord.isPresent()) {
        return Optional.empty();
    }
    final DocumentValuesSupplier documentValuesSupplier = new DataEntryDocumentValuesSupplier(dataEntryRecord.get(), dataEntryWebuiTools);
    Document document = null;
    if (existingDocumentsSupplier != null) {
        final DocumentId documentId = documentValuesSupplier.getDocumentId();
        document = existingDocumentsSupplier.apply(documentId);
    }
    if (document == null) {
        document = Document.builder(entityDescriptor).setParentDocument(parentDocument).setChangesCollector(changesCollector).initializeAsExistingRecord(documentValuesSupplier);
    }
    return Optional.ofNullable(document);
}


@Override
public String retrieveVersion(DocumentEntityDescriptor entityDescriptor,int documentIdAsInt){
    return VERSION_DEFAULT;
}


@Override
public Document createNewDocument(DocumentEntityDescriptor entityDescriptor,Document parentDocument,IDocumentChangesCollector changesCollector){
    final DocumentId documentId = createDocumentId(entityDescriptor, parentDocument);
    return Document.builder(entityDescriptor).setParentDocument(parentDocument).setChangesCollector(changesCollector).initializeAsNewDocument(documentId, VERSION_DEFAULT);
}


public DataEntrySubTabId extractDataEntrySubGroupId(DetailId detailId){
    final int subGroupId = detailId.getIdInt();
    Check.assume(detailId.getIdPrefix().equals(I_DataEntry_SubTab.Table_Name), "The given document.entityDescriptor.detailId needs to have prefix={}", I_DataEntry_SubTab.Table_Name);
    final DataEntrySubTabId dataEntrySubTabId = DataEntrySubTabId.ofRepoId(subGroupId);
    return dataEntrySubTabId;
}


public void refreshFromDataEntryRecord(Document document,Optional<DataEntryRecord> dataEntryRecord){
    if (!dataEntryRecord.isPresent()) {
        return;
    }
    final DocumentValuesSupplier documentValuesSupplier = new DataEntryDocumentValuesSupplier(dataEntryRecord.get(), dataEntryWebuiTools);
    document.refreshFromSupplier(documentValuesSupplier);
}


}