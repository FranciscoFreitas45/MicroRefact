package run.halo.app.core;
 import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import run.halo.app.model.support.BaseResponse;
@ControllerAdvice("run.halo.app.controller")
public class CommonResultControllerAdvice implements ResponseBodyAdvice<Object>{


@Override
@NonNull
public Object beforeBodyWrite(Object body,MethodParameter returnType,MediaType contentType,Class<? extends HttpMessageConverter<?>> converterType,ServerHttpRequest request,ServerHttpResponse response){
    MappingJacksonValue container = getOrCreateContainer(body);
    // The contain body will never be null
    beforeBodyWriteInternal(container, contentType, returnType, request, response);
    return container;
}


@Override
public boolean supports(MethodParameter returnType,Class<? extends HttpMessageConverter<?>> converterType){
    return AbstractJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
}


public void beforeBodyWriteInternal(MappingJacksonValue bodyContainer,MediaType contentType,MethodParameter returnType,ServerHttpRequest request,ServerHttpResponse response){
    // Get return body
    Object returnBody = bodyContainer.getValue();
    if (returnBody instanceof BaseResponse) {
        // If the return body is instance of BaseResponse, then just do nothing
        BaseResponse<?> baseResponse = (BaseResponse<?>) returnBody;
        HttpStatus status = HttpStatus.resolve(baseResponse.getStatus());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        response.setStatusCode(status);
        return;
    }
    // get status
    var status = HttpStatus.OK;
    if (response instanceof ServletServerHttpResponse) {
        var servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        status = HttpStatus.resolve(servletResponse.getStatus());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
    var baseResponse = new BaseResponse<>(status.value(), status.getReasonPhrase(), returnBody);
    bodyContainer.setValue(baseResponse);
}


public MappingJacksonValue getOrCreateContainer(Object body){
    return body instanceof MappingJacksonValue ? (MappingJacksonValue) body : new MappingJacksonValue(body);
}


}