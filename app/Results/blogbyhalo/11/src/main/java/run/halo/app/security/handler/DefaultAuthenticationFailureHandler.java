package run.halo.app.security.handler;
 import cn.hutool.extra.servlet.ServletUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import run.halo.app.exception.AbstractHaloException;
import run.halo.app.model.support.BaseResponse;
import run.halo.app.utils.ExceptionUtils;
import run.halo.app.utils.JsonUtils;
@Slf4j
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler{

 private  boolean productionEnv;

 private  ObjectMapper objectMapper;

public DefaultAuthenticationFailureHandler() {
}
@Override
public void onFailure(HttpServletRequest request,HttpServletResponse response,AbstractHaloException exception){
    log.warn("Handle unsuccessful authentication, ip: [{}]", ServletUtil.getClientIP(request));
    log.error("Authentication failure: [{}], status: [{}], data: [{}]", exception.getMessage(), exception.getStatus(), exception.getErrorData());
    BaseResponse<Object> errorDetail = new BaseResponse<>();
    errorDetail.setStatus(exception.getStatus().value());
    errorDetail.setMessage(exception.getMessage());
    errorDetail.setData(exception.getErrorData());
    if (!productionEnv) {
        errorDetail.setDevMessage(ExceptionUtils.getStackTrace(exception));
    }
    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    response.setStatus(exception.getStatus().value());
    response.getWriter().write(objectMapper.writeValueAsString(errorDetail));
}


public void setProductionEnv(boolean productionEnv){
    this.productionEnv = productionEnv;
}


public void setObjectMapper(ObjectMapper objectMapper){
    Assert.notNull(objectMapper, "Object mapper must not be null");
    this.objectMapper = objectMapper;
}


}