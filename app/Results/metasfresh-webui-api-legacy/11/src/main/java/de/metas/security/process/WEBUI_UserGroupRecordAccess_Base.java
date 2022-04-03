package de.metas.security.process;
 import java.util.List;
import java.util.Set;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.SpringContextHolder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.security.Principal;
import de.metas.security.permissions.Access;
import de.metas.security.permissions.record_access.PermissionIssuer;
import de.metas.security.permissions.record_access.RecordAccessGrantRequest;
import de.metas.security.permissions.record_access.RecordAccessRevokeRequest;
import de.metas.security.permissions.record_access.RecordAccessService;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.view.IView;
import de.metas.user.UserGroupId;
import de.metas.user.UserId;
import de.metas.util.Check;
public class WEBUI_UserGroupRecordAccess_Base extends ViewBasedProcessTemplateimplements IProcessPrecondition{

 private  RecordAccessService userGroupRecordAccessService;

@Param(parameterName = "PrincipalType", mandatory = true)
 private  String principalTypeCode;

@Param(parameterName = "AD_User_ID", mandatory = false)
 private  UserId userId;

@Param(parameterName = "AD_UserGroup_ID", mandatory = false)
 private  UserGroupId userGroupId;

 private  String PARAM_PermissionCode;

@Param(parameterName = PARAM_PermissionCode, mandatory = false)
 private  String permissionCode;


public void revokeAccessFromSelectedRows(){
    final Principal principal = getPrincipal();
    final UserId requestedBy = getUserId();
    final boolean revokeAllPermissions;
    final List<Access> permissionsToRevoke;
    final Access permission = getPermissionOrNull();
    if (permission == null) {
        revokeAllPermissions = true;
        permissionsToRevoke = ImmutableList.of();
    } else {
        revokeAllPermissions = false;
        permissionsToRevoke = ImmutableList.of(permission);
    }
    final IView view = getView();
    getSelectedRowIds().stream().map(view::getTableRecordReferenceOrNull).forEach(recordRef -> userGroupRecordAccessService.revokeAccess(RecordAccessRevokeRequest.builder().recordRef(recordRef).principal(principal).revokeAllPermissions(revokeAllPermissions).permissions(permissionsToRevoke).issuer(PermissionIssuer.MANUAL).requestedBy(requestedBy).build()));
}


public Principal getPrincipal(){
    final PrincipalType principalType = PrincipalType.ofCode(principalTypeCode);
    if (PrincipalType.USER.equals(principalType)) {
        return Principal.userId(userId);
    } else if (PrincipalType.USER_GROUP.equals(principalType)) {
        return Principal.userGroupId(userGroupId);
    } else {
        throw new AdempiereException("@Unknown@ @PrincipalType@: " + principalType);
    }
}


public Access getPermissionOrNull(){
    if (Check.isEmpty(permissionCode, true)) {
        return null;
    } else {
        return Access.ofCode(permissionCode);
    }
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (getView().getTableNameOrNull() == null) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("no table based view");
    }
    if (getSelectedRowIds().isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection().toInternal();
    }
    if (getSelectedRowIds().isAll()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("selecting ALL rows is not supported");
    }
    return ProcessPreconditionsResolution.accept();
}


public Set<Access> getPermissionsToGrant(){
    final Access permission = getPermissionOrNull();
    if (permission == null) {
        throw new FillMandatoryException(PARAM_PermissionCode);
    }
    if (Access.WRITE.equals(permission)) {
        return ImmutableSet.of(Access.READ, Access.WRITE);
    } else {
        return ImmutableSet.of(permission);
    }
}


public void grantAccessToSelectedRows(){
    final Principal principal = getPrincipal();
    final Set<Access> permissionsToGrant = getPermissionsToGrant();
    final UserId requestedBy = getUserId();
    final IView view = getView();
    getSelectedRowIds().stream().map(view::getTableRecordReferenceOrNull).forEach(recordRef -> userGroupRecordAccessService.grantAccess(RecordAccessGrantRequest.builder().recordRef(recordRef).principal(principal).permissions(permissionsToGrant).issuer(PermissionIssuer.MANUAL).requestedBy(requestedBy).build()));
}


}