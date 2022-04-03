package de.metas.ui.web.window.invalidation;
 import java.util.Collection;
import java.util.HashMap;
import org.adempiere.util.lang.impl.TableRecordReference;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class DocumentToInvalidate {

 private  TableRecordReference recordRef;

@Getter
 private  boolean invalidateDocument;

 private  HashMap<String,IncludedDocumentToInvalidate> includedDocumentsByTableName;


public DocumentId getDocumentId(){
    return DocumentId.of(recordRef.getRecord_ID());
}


public String getTableName(){
    return recordRef.getTableName();
}


public void addIncludedDocument(String includedTableName,int includedRecordId){
    getIncludedDocument(includedTableName).addRecordId(includedRecordId);
}


public void invalidateDocument(){
    invalidateDocument = true;
}


public void invalidateAllIncludedDocuments(String includedTableName){
    getIncludedDocument(includedTableName).invalidateAll();
}


public IncludedDocumentToInvalidate getIncludedDocument(String includedTableName){
    return includedDocumentsByTableName.computeIfAbsent(includedTableName, IncludedDocumentToInvalidate::new);
}


public Collection<IncludedDocumentToInvalidate> getIncludedDocuments(){
    return includedDocumentsByTableName.values();
}


}