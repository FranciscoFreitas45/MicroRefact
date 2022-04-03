package com.qidian.hcm.common.interceptor;
 import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.jwt.JwtUserInfo;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.authorization.dto.ConditionDTO;
import com.qidian.hcm.module.authorization.dto.PermissionDTO;
import com.qidian.hcm.module.authorization.dto.PermissionMetaDataDTO;
import com.qidian.hcm.module.authorization.dto.RolePermissionDTO;
import com.qidian.hcm.module.authorization.enums.ActionType;
import com.qidian.hcm.module.authorization.enums.MenuCode;
import com.qidian.hcm.module.authorization.enums.PlatformType;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class TenantThreadHelper {

 private  ThreadLocal<JwtUserInfo> TOKEN_HOLDER;

private TenantThreadHelper() {
}
public void setToken(JwtUserInfo member){
    TOKEN_HOLDER.set(member);
}


public JwtUserInfo getToken(){
    return TOKEN_HOLDER.get();
}


public List<Long> getPermissionOrgIds(PlatformType platformType,MenuCode menuCode,ActionType actionType){
    RolePermissionDTO rolePermissionDTO = getToken().getRolePermission();
    if (rolePermissionDTO == null) {
        throw new BizException(ResultCode.DO_NOT_HAVE_ACCESS);
    }
    List<PermissionMetaDataDTO> permissionMetaDataDTOs = PlatformType.backend == platformType ? rolePermissionDTO.getBackend() : rolePermissionDTO.getFrontend();
    if (CollectionUtils.isEmpty(permissionMetaDataDTOs)) {
        throw new BizException(ResultCode.DO_NOT_HAVE_ACCESS);
    }
    Map<MenuCode, List<PermissionDTO>> menuPermissionsMap = permissionMetaDataDTOs.stream().collect(Collectors.toMap(PermissionMetaDataDTO::getCode, PermissionMetaDataDTO::getPermissions));
    if (!menuPermissionsMap.containsKey(menuCode)) {
        throw new BizException(ResultCode.DO_NOT_HAVE_ACCESS);
    }
    List<Long> orgIds = new ArrayList<>();
    for (PermissionDTO permissionDTO : menuPermissionsMap.get(menuCode)) {
        if (permissionDTO.getAction() == actionType) {
            for (ConditionDTO conditionDTO : permissionDTO.getConditions()) {
                orgIds.add(Long.valueOf(conditionDTO.getValue()));
            }
        }
    }
    return orgIds;
}


}