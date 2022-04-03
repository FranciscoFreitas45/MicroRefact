package de.metas.ui.web.config;
 import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.adempiere.exceptions.AdempiereException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.logging.LogManager;
import de.metas.ui.web.login.exceptions.NotLoggedInException;
import de.metas.ui.web.window.datatypes.Values;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.GuavaCollectors;
@Component
// Order: IMPORTANT: because we want to call this handler before any other. Else, if it's the last one added, it might be that it will be never called
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebuiExceptionHandler implements HandlerExceptionResolver,ErrorAttributes{

 private  Logger logger;

 private  String REQUEST_ATTR_EXCEPTION;

 private  String ATTR_Timestamp;

 private  String ATTR_Status;

 private  String ATTR_Error;

 private  String ATTR_Exception;

 private  String ATTR_ExceptionAttributes;

 private  String ATTR_Message;

 private  String ATTR_Stacktrace;

 private  String ATTR_Path;

@Value("${de.metas.ui.web.config.WebuiExceptionHandler.logExceptions:true}")
 private  boolean logExceptions;

 private  Set<Class<?>> EXCEPTIONS_ExcludeFromLogging;

 private  Map<Class<?>,HttpStatus> EXCEPTION_HTTPSTATUS;


public JSONOptions newJSONOptions(){
    return JSONOptions.newInstance();
}


public boolean isExcludeFromLogging(Throwable ex){
    for (final Class<?> exceptionClass : EXCEPTIONS_ExcludeFromLogging) {
        if (exceptionClass.isAssignableFrom(ex.getClass())) {
            return true;
        }
    }
    return false;
}


@Override
public Map<String,Object> getErrorAttributes(RequestAttributes requestAttributes,boolean includeStackTrace){
    final Map<String, Object> errorAttributes = new LinkedHashMap<>();
    errorAttributes.put(ATTR_Timestamp, ZonedDateTime.now());
    addStatus(errorAttributes, requestAttributes);
    addErrorDetails(errorAttributes, requestAttributes, includeStackTrace);
    addPath(errorAttributes, requestAttributes);
    return errorAttributes;
}


public void addStackTrace(Map<String,Object> errorAttributes,Throwable error){
    final StringWriter stackTrace = new StringWriter();
    error.printStackTrace(new PrintWriter(stackTrace));
    stackTrace.flush();
    final String stackTraceStr = stackTrace.toString();
    final Iterable<String> stackTraceList = Splitter.on("\n").split(stackTraceStr);
    errorAttributes.put(ATTR_Stacktrace, stackTraceList);
}


@Override
public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex){
    logExceptionIfNeeded(ex, handler);
    final Throwable cause = ex == null ? null : AdempiereException.extractCause(ex);
    request.setAttribute(REQUEST_ATTR_EXCEPTION, cause);
    response.setHeader("Cache-Control", "no-cache");
    // don't forward, go with default processing
    return null;
}


public void addPath(Map<String,Object> errorAttributes,RequestAttributes requestAttributes){
    final String path = getAttribute(requestAttributes, RequestDispatcher.ERROR_REQUEST_URI);
    if (path != null) {
        errorAttributes.put(ATTR_Path, path);
    }
}


@Override
public Throwable getError(RequestAttributes requestAttributes){
    Throwable exception = getAttribute(requestAttributes, REQUEST_ATTR_EXCEPTION);
    if (exception == null) {
        exception = getAttribute(requestAttributes, RequestDispatcher.ERROR_EXCEPTION);
    }
    if (exception != null) {
        exception = AdempiereException.extractCause(exception);
    }
    return exception;
}


@SuppressWarnings("unchecked")
public T getAttribute(RequestAttributes requestAttributes,String name){
    return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
}


public void addStatus(Map<String,Object> errorAttributes,RequestAttributes requestAttributes){
    Integer status = null;
    // 
    // Extract HTTP status from EXCEPTION_HTTPSTATUS map
    final Throwable error = getError(requestAttributes);
    if (error != null) {
        final Class<? extends Throwable> errorClass = error.getClass();
        status = EXCEPTION_HTTPSTATUS.entrySet().stream().filter(e -> isErrorMatching(e.getKey(), errorClass)).map(e -> e.getValue().value()).findFirst().orElse(null);
    }
    // 
    // Extract HTTP status from attributes
    if (status == null) {
        status = getAttribute(requestAttributes, RequestDispatcher.ERROR_STATUS_CODE);
    }
    if (status == null) {
        errorAttributes.put(ATTR_Status, 999);
        errorAttributes.put(ATTR_Error, "None");
        return;
    }
    errorAttributes.put(ATTR_Status, status);
    try {
        errorAttributes.put(ATTR_Error, HttpStatus.valueOf(status).getReasonPhrase());
    } catch (final Exception ex) {
        // Unable to obtain a reason
        errorAttributes.put(ATTR_Error, "Http Status " + status);
    }
}


public void addErrorMessage(Map<String,Object> errorAttributes,Throwable error){
    errorAttributes.put(ATTR_Message, error.getLocalizedMessage());
}


public void logExceptionIfNeeded(Exception ex,Object handler){
    if (!logExceptions) {
        return;
    }
    if (isExcludeFromLogging(ex)) {
        logger.debug("Got REST (excluded from logging) exception from handler={}", handler, ex);
    } else {
        logger.warn("Got REST exception from handler={}", handler, ex);
    }
}


public boolean isErrorMatching(Class<?> baseClass,Class<?> clazz){
    return baseClass.isAssignableFrom(clazz);
}


public void addErrorDetails(Map<String,Object> errorAttributes,RequestAttributes requestAttributes,boolean includeStackTrace){
    // 
    // Get exception and
    // Set "exception" attribute.
    Throwable error = getError(requestAttributes);
    if (error != null) {
        while (error instanceof ServletException && error.getCause() != null) {
            error = ((ServletException) error).getCause();
        }
        errorAttributes.put(ATTR_Exception, error.getClass().getName());
        addErrorMessage(errorAttributes, error);
        if (includeStackTrace && !isExcludeFromLogging(error)) {
            addStackTrace(errorAttributes, error);
        }
    }
    // 
    // Set "message" attribute
    final Object message = getAttribute(requestAttributes, RequestDispatcher.ERROR_MESSAGE);
    if ((!StringUtils.isEmpty(message) || errorAttributes.get(ATTR_Message) == null) && !(error instanceof BindingResult)) {
        errorAttributes.put(ATTR_Message, StringUtils.isEmpty(message) ? "No message available" : message);
    }
    // 
    // Set "exceptionAttributes" attribute
    if (error instanceof AdempiereException) {
        final Map<String, Object> exceptionAttributes = ((AdempiereException) error).getParameters();
        if (exceptionAttributes != null && !exceptionAttributes.isEmpty()) {
            final JSONOptions jsonOpts = newJSONOptions();
            final Map<String, Object> jsonExceptionAttributes = exceptionAttributes.entrySet().stream().map(entry -> GuavaCollectors.entry(entry.getKey(), Values.valueToJsonObject(entry.getValue(), jsonOpts, String::valueOf))).collect(GuavaCollectors.toImmutableMap());
            errorAttributes.put(ATTR_ExceptionAttributes, jsonExceptionAttributes);
        }
    }
}


}