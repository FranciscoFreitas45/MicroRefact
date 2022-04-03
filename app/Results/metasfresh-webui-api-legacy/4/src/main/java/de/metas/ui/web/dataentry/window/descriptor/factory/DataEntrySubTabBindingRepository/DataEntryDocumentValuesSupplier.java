package de.metas.ui.web.dataentry.window.descriptor.factory.DataEntrySubTabBindingRepository;
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
public class DataEntryDocumentValuesSupplier implements DocumentValuesSupplier{

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


@Override
public Object getValue(DocumentFieldDescriptor fieldDescriptor){
    return dataEntryWebuiTools.extractDataEntryValueForField(dataEntryRecord, fieldDescriptor);
}


}