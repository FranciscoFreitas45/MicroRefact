package com.qidian.hcm.module.center.config;
 import com.qidian.hcm.common.constants.SystemConstant;
import com.qidian.hcm.common.interceptor.TenantThreadHelper;
import com.qidian.hcm.common.jwt.JwtUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import java.util.Objects;
@Component
public class MultiTenantIdentifierResolver implements CurrentTenantIdentifierResolver{


@Override
public boolean validateExistingCurrentSessions(){
    return true;
}


@Override
public String resolveCurrentTenantIdentifier(){
    JwtUserInfo user = TenantThreadHelper.getToken();
    if (Objects.nonNull(user) && StringUtils.isNotEmpty(user.getTenantName())) {
        return user.getTenantName();
    }
    return SystemConstant.DEFAULT_DB;
}


}