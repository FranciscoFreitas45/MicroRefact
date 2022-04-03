package edu.xr.campusweibo.web.rest.util;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
public class HeaderUtil {

 private  Logger log;

 private  String APPLICATION_NAME;


public HttpHeaders createEntityCreationAlert(String entityName,String param){
    return createAlert(APPLICATION_NAME + "." + entityName + ".created", param);
}


public HttpHeaders createFailureAlert(String entityName,String errorKey,String defaultMessage){
    log.error("Entity creation failed, {}", defaultMessage);
    HttpHeaders headers = new HttpHeaders();
    headers.add("X-campusweiboApp-error", "error." + errorKey);
    headers.add("X-campusweiboApp-params", entityName);
    return headers;
}


public HttpHeaders createAlert(String message,String param){
    HttpHeaders headers = new HttpHeaders();
    headers.add("X-campusweiboApp-alert", message);
    headers.add("X-campusweiboApp-params", param);
    return headers;
}


public HttpHeaders createEntityDeletionAlert(String entityName,String param){
    return createAlert(APPLICATION_NAME + "." + entityName + ".deleted", param);
}


public HttpHeaders createEntityUpdateAlert(String entityName,String param){
    return createAlert(APPLICATION_NAME + "." + entityName + ".updated", param);
}


}