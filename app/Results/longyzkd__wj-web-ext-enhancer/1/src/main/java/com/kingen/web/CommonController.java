package com.kingen.web;
 import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.common.collect.Lists;
import com.kingen.bean.User;
import com.kingen.service.CommonService;
import com.kingen.service.account.AccountService;
import com.kingen.util.DateUtils;
import com.kingen.util.FastjsonFilter;
import com.kingen.util.SpringContextHolder;
import com.kingen.util.mapper.JsonMapper;
import com.kingen.vo.Comboable;
@Controller
public class CommonController {

 protected  Logger logger;

@Autowired
 private  AccountService service;

 private  String FILTER_NAME;


public User getCurrentUser(){
    User user = (User) SecurityUtils.getSubject().getPrincipal();
    return user;
}


@RequestMapping(value = "combo/{entityName}")
public void data(String entityName,HttpServletResponse response){
    List<Object> result = service.list(entityName);
    List<Comboable> com = Lists.newArrayList();
    if (!CollectionUtils.isEmpty(result)) {
        for (Object o : result) {
            if (o instanceof Comboable) {
                com.add((Comboable) o);
            }
        }
    }
    writeJson(response, com);
}


public HttpServletRequest getRequest(){
    // return ServletActionContext.getRequest();
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
}


public void writeJackson(HttpServletResponse response,Object object,String[] includesProperties,String[] excludesProperties){
    try {
        // 转换器
        ObjectMapper mapper = new ObjectMapper();
        // 将对象转换成json
        String json = serializeOnlyGivenFields(object, Arrays.asList(excludesProperties));
        response.setContentType("text/html;charset=utf-8");
        response.getOutputStream().write(json.getBytes("UTF-8"));
        response.getOutputStream().flush();
        response.getOutputStream().close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


@InitBinder
public void initBinder(WebDataBinder binder){
    // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
    binder.registerCustomEditor(String.class, new PropertyEditorSupport() {

        @Override
        public void setAsText(String text) {
            setValue(text == null ? null : text.trim());
        // setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
        // setValue(text == null ? null : StringEscapeUtils.escapeJavaScript(text.trim()));
        }

        @Override
        public String getAsText() {
            Object value = getValue();
            return value != null ? value.toString() : "";
        }
    });
    // Date 类型转换
    binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {

        @Override
        public void setAsText(String text) {
            setValue(DateUtils.parseDate(StringUtils.trim(text)));
            logger.debug("日期---" + getValue());
        }
    });
}


public void writeJsonByFilter(HttpServletResponse response,Object object,String[] includesProperties,String[] excludesProperties){
    try {
        // excludes优先于includes
        FastjsonFilter filter = new FastjsonFilter();
        if (excludesProperties != null && excludesProperties.length > 0) {
            filter.getExcludes().addAll(Arrays.<String>asList(excludesProperties));
        }
        if (includesProperties != null && includesProperties.length > 0) {
            filter.getIncludes().addAll(Arrays.<String>asList(includesProperties));
        }
        // logger.info("对象转JSON：要排除的属性[" + excludesProperties + "]要包含的属性[" + includesProperties + "]");
        String json;
        String User_Agent = getRequest().getHeader("User-Agent");
        if (StringUtils.indexOfIgnoreCase(User_Agent, "MSIE 6") > -1) {
            // 使用SerializerFeature.BrowserCompatible特性会把所有的中文都会序列化为\\uXXXX这种格式，字节数会多一些，但是能兼容IE6
            json = JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.BrowserCompatible);
        } else {
            // 使用SerializerFeature.WriteDateUseDateFormat特性来序列化日期格式的类型为yyyy-MM-dd hh24:mi:ss
            // 使用SerializerFeature.DisableCircularReferenceDetect特性关闭引用检测和生成
            json = JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
        }
        // logger.info("转换后的JSON字符串：" + json);
        response.setContentType("text/html;charset=utf-8");
        // response.getWriter().write(json);
        // response.getWriter().flush();
        // response.getWriter().close();
        // response.getOutputStream().write(json.getBytes());
        response.getOutputStream().write(json.getBytes("UTF-8"));
        response.getOutputStream().flush();
        response.getOutputStream().close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


@Override
public String getAsText(){
    Object value = getValue();
    return value != null ? value.toString() : "";
}


@ExceptionHandler({ BindException.class, ConstraintViolationException.class, ValidationException.class })
public String bindException(){
    return "error/400";
}


@Override
public void setAsText(String text){
    setValue(DateUtils.parseDate(StringUtils.trim(text)));
    logger.debug("日期---" + getValue());
}


public void writeJson(HttpServletResponse response,Object object,String[] excludesProperties){
    writeJsonByFilter(response, object, null, excludesProperties);
}


public String renderString(HttpServletResponse response,String string,String type){
    try {
        response.reset();
        response.setContentType(type);
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(string);
        return null;
    } catch (IOException e) {
        return null;
    }
}


@Override
public Object findFilterId(Annotated a){
    return FILTER_NAME;
}


@ExceptionHandler({ AuthenticationException.class })
public String authenticationException(){
    return "error/403";
}


public String serializeOnlyGivenFields(Object o,Collection<String> fields){
    if ((fields == null) || fields.isEmpty())
        fields = new HashSet<String>();
    Set<String> properties = new HashSet<String>(fields);
    SimpleBeanPropertyFilter filter = new SimpleBeanPropertyFilter.FilterExceptFilter(properties);
    SimpleFilterProvider fProvider = new SimpleFilterProvider();
    fProvider.addFilter(FILTER_NAME, filter);
    ObjectMapper mapper = new ObjectMapper();
    mapper.setAnnotationIntrospector(new AnnotationIntrospector());
    String json = mapper.writer(fProvider).writeValueAsString(o);
    return json;
}


public void writeJsonInclude(HttpServletResponse response,Object object,String[] includesProperties){
    writeJsonByFilter(response, object, includesProperties, null);
}


public HttpSession getSession(){
    // return ServletActionContext.getRequest().getSession();
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
}


public String renderJsonString(HttpServletResponse response,Object object){
    return renderString(response, JsonMapper.toJsonString(object), "application/json");
}


}