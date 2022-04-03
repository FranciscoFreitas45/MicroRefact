package de.metas.ui.web.window.model;
 import de.metas.ui.web.window.WindowConstants;
import lombok.Builder;
import lombok.Value;
@Value
@Builder
public class DocumentReadonly {

 public  DocumentReadonly NOT_READONLY;

 private  boolean parentActive;

 private  boolean active;

 private  boolean processed;

 private  boolean processing;

 private  Boolean fieldsReadonly;


public boolean computeFieldReadonly(String fieldName,boolean alwaysUpdateable){
    // Case: parent document is not active => fields of this document shall be completely readonly (including the IsActive flag)
    if (!parentActive) {
        // readonly
        return true;
    }
    // Case: this or parent document is processed => fields of this document shall be completely readonly if they were not flagged with AlwaysUpdateable
    if (processed || processing) {
        // readonly if not always updateable
        return !alwaysUpdateable;
    }
    // Case: this document is not active => fields of this document shall be completely readonly, BUT NOT the IsActive flag.
    // We shall alow user to edit it
    if (!active) {
        if (WindowConstants.FIELDNAME_IsActive.equals(fieldName)) {
            // not readonly
            return false;
        } else {
            // readonly
            return true;
        }
    }
    // If we reached this point, it means the document and parent document are active and not processed
    // => readonly if fields are readonly.
    return fieldsReadonly != null ? fieldsReadonly : false;
}


public DocumentReadonly ofParent(DocumentReadonly parentDocumentReadonly){
    return builder().parentActive(parentDocumentReadonly.active).active(parentDocumentReadonly.active).processed(parentDocumentReadonly.processed).processed(parentDocumentReadonly.processing).fieldsReadonly(// unknown (will fallback to not-readonly)
    null).build();
}


}