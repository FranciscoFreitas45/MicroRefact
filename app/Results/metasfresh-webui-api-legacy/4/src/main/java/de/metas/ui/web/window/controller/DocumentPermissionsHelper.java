package de.metas.ui.web.window.controller;
 import javax.annotation.Nullable;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.ad.table.api.IADTableDAO;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.service.ClientId;
import org.adempiere.service.IRolePermLoggingBL;
import org.adempiere.service.IRolePermLoggingBL.NoSuchForeignKeyException;
import org.slf4j.Logger;
import de.metas.logging.LogManager;
import de.metas.organization.OrgId;
import de.metas.security.IUserRolePermissions;
import de.metas.security.permissions.ElementPermission;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.exceptions.DocumentPermissionException;
import de.metas.ui.web.window.exceptions.DocumentPermissionException.DocumentPermission;
import de.metas.ui.web.window.model.Document;
import de.metas.util.Services;
import lombok.NonNull;
public class DocumentPermissionsHelper {

 private  Logger logger;


public String checkCanEdit(Document document,IUserRolePermissions permissions){
    // In case document type is not Window, return OK because we cannot validate
    final DocumentPath documentPath = document.getDocumentPath();
    if (documentPath.getDocumentType() != DocumentType.Window) {
        // OK
        return null;
    }
    // Check if we have window write permission
    final AdWindowId adWindowId = documentPath.getWindowId().toAdWindowIdOrNull();
    if (adWindowId != null && !permissions.checkWindowPermission(adWindowId).hasWriteAccess()) {
        return "no window edit permission";
    }
    final int adTableId = getAdTableId(document);
    if (adTableId <= 0) {
        // not table based => OK
        return null;
    }
    final int recordId = getRecordId(document);
    final ClientId adClientId = document.getClientId();
    final OrgId adOrgId = document.getOrgId();
    if (adOrgId == null) {
        // the user cleared the field; field is flagged as mandatory; until user set the field, don't make a fuss.
        return null;
    }
    return permissions.checkCanUpdate(adClientId, adOrgId, adTableId, recordId);
}


public void assertViewAccess(WindowId windowId,String viewId,IUserRolePermissions permissions){
    final AdWindowId adWindowId = windowId.toAdWindowIdOrNull();
    if (adWindowId == null) {
        // cannot apply window access if the WindowId is not integer.
        // usually those are special window placeholders.
        // accept it
        return;
    }
    // 
    // Check AD_Window_ID access
    final ElementPermission windowPermission = permissions.checkWindowPermission(adWindowId);
    if (!windowPermission.hasReadAccess()) {
        final AdempiereException ex = DocumentPermissionException.of(DocumentPermission.WindowAccess, "@NoAccess@").setParameter("roleName", permissions.getName()).setParameter("view", viewId).setParameter("windowId", adWindowId);
        logAccessIfWindowExistsAndThrowEx(permissions, adWindowId, ex);
    }
}


public int getRecordId(Document document){
    if (document.isNew()) {
        return -1;
    } else {
        return document.getDocumentId().toIntOr(-1);
    }
}


public void logAccessIfWindowExistsAndThrowEx(IUserRolePermissions permissions,AdWindowId adWindowId,AdempiereException ex){
    final IRolePermLoggingBL rolePermLoggingBL = Services.get(IRolePermLoggingBL.class);
    // none
    final Boolean readWriteAccess = null;
    try {
        rolePermLoggingBL.logWindowAccess(permissions.getRoleId(), adWindowId, readWriteAccess, ex.getLocalizedMessage());
    } catch (final NoSuchForeignKeyException noSuchForeignKeyException) {
        // log it, but throw the "important" one, i.e. ex
        logger.warn("Caught NoSuchForeignKeyException for AD_Window_ID=" + adWindowId, noSuchForeignKeyException);
    }
    throw ex;
}


public void assertCanEdit(Document document,IUserRolePermissions permissions){
    final String errmsg = checkCanEdit(document, permissions);
    if (errmsg != null) {
        throw DocumentPermissionException.of(DocumentPermission.Update, errmsg);
    }
}


public void assertCanView(Document document,IUserRolePermissions permissions){
    // In case document type is not Window, return OK because we cannot validate
    if (document.getDocumentPath().getDocumentType() != DocumentType.Window) {
        // OK
        return;
    }
    // Check if we have window read permission
    final AdWindowId adWindowId = document.getDocumentPath().getWindowId().toAdWindowIdOrNull();
    if (adWindowId != null && !permissions.checkWindowPermission(adWindowId).hasReadAccess()) {
        throw DocumentPermissionException.of(DocumentPermission.View, "no window read permission");
    }
    final int adTableId = getAdTableId(document);
    if (adTableId <= 0) {
        // cannot apply security because this is not table based
        return;
    }
    final int recordId = getRecordId(document);
    final OrgId orgId = document.getOrgId();
    if (orgId == null) {
        // the user cleared the field; field is flagged as mandatory; until the user set the field, don't make a fuss.
        return;
    }
    final String errmsg = permissions.checkCanView(document.getClientId(), orgId, adTableId, recordId);
    if (errmsg != null) {
        throw DocumentPermissionException.of(DocumentPermission.View, errmsg);
    }
}


public boolean canEdit(Document document,IUserRolePermissions permissions){
    final String errmsg = checkCanEdit(document, permissions);
    return errmsg == null;
}


public int getAdTableId(Document document){
    final String tableName = document.getEntityDescriptor().getTableNameOrNull();
    if (tableName == null) {
        // cannot apply security because this is not table based
        // OK
        return -1;
    }
    return Services.get(IADTableDAO.class).retrieveTableId(tableName);
}


public ElementPermission checkWindowAccess(DocumentEntityDescriptor entityDescriptor,IUserRolePermissions permissions){
    final AdWindowId adWindowId = entityDescriptor.getWindowId().toAdWindowId();
    final ElementPermission windowPermission = permissions.checkWindowPermission(adWindowId);
    final boolean readAccess = windowPermission.hasReadAccess();
    final boolean writeAccess = windowPermission.hasWriteAccess();
    // no access
    if (!readAccess && !writeAccess) {
        final AdempiereException ex = DocumentPermissionException.of(DocumentPermission.WindowAccess, "@NoAccess@").setParameter("Role", permissions.getName()).setParameter("WindowName", entityDescriptor.getCaption()).setParameter("AD_Window_ID", adWindowId);
        logAccessIfWindowExistsAndThrowEx(permissions, adWindowId, ex);
    }
    return windowPermission;
}


}