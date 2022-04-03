package com.ushahidi.swiftriver.core.support.dozer;
 import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.dozer.DozerConverter;
public class AbstractRuleItemListToStringConverter extends DozerConverter<List, String>{

 protected  ObjectMapper objectMapper;

public AbstractRuleItemListToStringConverter() {
    super(List.class, String.class);
}
@Override
public String convertTo(List source,String destination){
    String json = null;
    try {
        json = objectMapper.writeValueAsString(source);
        destination = json;
    } catch (JsonParseException jp) {
    } catch (JsonMappingException jm) {
    } catch (IOException io) {
    }
    return json;
}


public void setObjectMapper(ObjectMapper objectMapper){
    this.objectMapper = objectMapper;
}


}