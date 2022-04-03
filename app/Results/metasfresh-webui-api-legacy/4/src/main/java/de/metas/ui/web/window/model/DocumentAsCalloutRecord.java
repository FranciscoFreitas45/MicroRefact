package de.metas.ui.web.window.model;
 import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import org.adempiere.ad.callout.api.ICalloutRecord;
import org.adempiere.model.InterfaceWrapperHelper;
import com.google.common.base.MoreObjects;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import lombok.NonNull;
public class DocumentAsCalloutRecord implements ICalloutRecord{

 private  ReasonSupplier REASON_Value_DirectSetOnCalloutRecord;

 private  Reference<Document> _documentRef;


@Override
public void dataRefreshRecursively(){
    // TODO dataRefreshRecursively: refresh document and it's children
    throw new UnsupportedOperationException();
}


@Override
public Object getValue(String columnName){
    final Document document = getDocument();
    return InterfaceWrapperHelper.getValueOrNull(document, columnName);
}


@Override
public void dataRefreshAll(){
    // NOTE: there is no "All" concept here, so we are just refreshing this document
    final Document document = getDocument();
    document.refreshFromRepository();
}


@Override
public T getModel(Class<T> modelClass){
    final Document document = getDocument();
    return DocumentInterfaceWrapper.wrap(document, modelClass);
}


@Override
public boolean dataSave(boolean manualCmd){
    // TODO dataSave: save document but also update the DocumentsCollection!
    throw new UnsupportedOperationException();
}


public Document getDocument(){
    final Document document = _documentRef.get();
    if (document == null) {
        throw new IllegalStateException("Document reference already expired");
    }
    return document;
}


@Override
public String getTableName(){
    final Document document = getDocument();
    return document.getEntityDescriptor().getTableName();
}


@Override
public T getModelBeforeChanges(Class<T> modelClass){
    final Document document = getDocument();
    return DocumentInterfaceWrapper.wrapUsingOldValues(document, modelClass);
}


@Override
public String setValue(String columnName,Object value){
    final Document document = getDocument();
    document.setValue(columnName, value, REASON_Value_DirectSetOnCalloutRecord);
    return "";
}


@Override
public void dataRefresh(){
    final Document document = getDocument();
    document.refreshFromRepository();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(_documentRef.get()).toString();
}


@Override
public int getAD_Tab_ID(){
    final Document document = getDocument();
    return document.getEntityDescriptor().getAdTabId().getRepoId();
}


}