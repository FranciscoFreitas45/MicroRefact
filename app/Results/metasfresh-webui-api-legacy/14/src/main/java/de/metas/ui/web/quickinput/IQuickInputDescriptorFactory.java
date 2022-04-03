package de.metas.ui.web.quickinput;
 import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Component;
import de.metas.lang.SOTrx;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.descriptor.DetailId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
public interface IQuickInputDescriptorFactory {

 private  DocumentType documentType;

 private  DocumentId documentTypeId;

 private  String tableName;


public MatchingKey ofTableName(String tableName){
    final DocumentType documentType = null;
    final DocumentId documentTypeId = null;
    return new MatchingKey(documentType, documentTypeId, tableName);
}
;

public QuickInputDescriptor createQuickInputDescriptor(DocumentType documentType,DocumentId documentTypeId,DetailId detailId,Optional<SOTrx> soTrx)
;

public MatchingKey includedDocument(DocumentType rootDocumentType,DocumentId rootDocumentTypeId,String includedTableName){
    return new MatchingKey(rootDocumentType, rootDocumentTypeId, includedTableName);
}
;

public Set<MatchingKey> getMatchingKeys()
;

}