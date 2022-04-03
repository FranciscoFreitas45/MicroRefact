package com.evolvingreality.onleave.web.rest.util;
 import java.net.URI;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
public class UriUtil {


public URI buildGetUri(T controller,Long id){
    UriComponents uriComponents = MvcUriComponentsBuilder.fromMethodName(controller.getClass(), "get", id).build();
    return uriComponents.encode().toUri();
}


}