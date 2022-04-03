package com.ushahidi.swiftriver.core.support.dozer;
 import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;
import com.ushahidi.swiftriver.core.api.dto.CreateRuleDTO.RuleCondition;
public class RuleConditionListToStringConverter extends AbstractRuleItemListToStringConverter{


@Override
public List convertFrom(String source,List destination){
    List<RuleCondition> conditionsList = null;
    try {
        conditionsList = objectMapper.readValue(source, new TypeReference<List<RuleCondition>>() {
        });
        destination = conditionsList;
    } catch (JsonParseException jp) {
    } catch (JsonMappingException jm) {
    } catch (IOException io) {
    }
    return conditionsList;
}


}