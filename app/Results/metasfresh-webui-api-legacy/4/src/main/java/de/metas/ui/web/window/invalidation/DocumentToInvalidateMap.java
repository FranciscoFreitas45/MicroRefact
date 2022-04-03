package de.metas.ui.web.window.invalidation;
 import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import lombok.ToString;
@ToString
public class DocumentToInvalidateMap {

 private  Map<TableRecordReference,DocumentToInvalidate> documents;


public TableRecordReferenceSet getRootRecords(){
    return TableRecordReferenceSet.of(documents.keySet());
}


public int size(){
    return documents.size();
}


public Collection<DocumentToInvalidate> toCollection(){
    return documents.values();
}


public boolean isEmpty(){
    return documents.isEmpty();
}


public DocumentToInvalidate getDocumentToInvalidate(TableRecordReference rootDocumentRef){
    return documents.computeIfAbsent(rootDocumentRef, DocumentToInvalidate::new);
}


}