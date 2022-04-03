package de.metas.ui.web.window.datatypes.json;
 import java.util.HashMap;
import java.util.Map;
import de.metas.security.IUserRolePermissions;
import de.metas.ui.web.window.controller.DocumentPermissionsHelper;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.model.Document;
import lombok.NonNull;
public class JSONDocumentPermissions {

 private  IUserRolePermissions permissions;

 private  Map<DocumentPath,Boolean> readonlyDocuments;


public boolean isReadonly(Document document){
    return readonlyDocuments.computeIfAbsent(document.getDocumentPath(), documentPath -> !DocumentPermissionsHelper.canEdit(document, permissions));
}


public void apply(DocumentPath documentPath,JSONIncludedTabInfo jsonIncludedTabInfo){
// TODO: implement... but it's not so critical atm
}


}