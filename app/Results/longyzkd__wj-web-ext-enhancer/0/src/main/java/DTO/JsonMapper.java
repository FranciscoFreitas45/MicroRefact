package DTO;
 import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
public class JsonMapper extends ObjectMapper{

 private  long serialVersionUID;

 private  Logger logger;

 private  JsonMapper mapper;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

public JsonMapper() {
    // Include.ALWAYS 需要加上这个
    this(Include.NON_EMPTY);
}public JsonMapper(Include include) {
    // 设置输出时包含属性的风格
    if (include != null) {
        this.setSerializationInclusion(include);
    }
    // 允许单引号、允许不带引号的字段名称
    this.enableSimple();
    // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
    this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    // 空值处理为空串
    this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {

        @Override
        public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            jgen.writeString("");
        }
    });
    // 进行HTML解码。
    this.registerModule(new SimpleModule().addSerializer(String.class, new JsonSerializer<String>() {

        @Override
        public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            jgen.writeString(StringEscapeUtils.unescapeHtml4(value));
        }
    }));
    // 设置时区
    // getTimeZone("GMT+8:00")
    this.setTimeZone(TimeZone.getDefault());
}
public ObjectMapper getMapper(){
    return this;
}


public JsonMapper getInstance(){
    if (mapper == null) {
        mapper = new JsonMapper().enableSimple();
    }
    return mapper;
}


public String toJsonString(Object object){
    return JsonMapper.getInstance().toJson(object);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toJsonString"))

.queryParam("object",object)
;
String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


}