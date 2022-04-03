package DTO;
 import java.util.Map;
import javax.annotation.Nullable;
import org.compiere.util.NamePair;
import org.compiere.util.ValueNamePairValidationInformation;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue.IntegerLookupValueBuilder;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue.StringLookupValueBuilder;
import de.metas.util.StringUtils;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
public class JSONLookupValue {

 static  String PROPERTY_Key;

 private  String key;

 private  Integer keyAsInt;

 private  String PROPERTY_Caption;

 private  String caption;

 private  String PROPERTY_Description;

 private  String description;

 private  String PROPERTY_Attributes;

 private  Map<String,Object> attributes;

 private  String PROPERTY_Active;

 private  Boolean active;

 private  String PROPERTY_ValidationInformation;

 private  JSONLookupValueValidationInformation validationInformation;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public int getKeyAsInt(){
    Integer keyAsInt = this.keyAsInt;
    if (keyAsInt == null) {
        keyAsInt = this.keyAsInt = Integer.parseInt(getKey());
    }
    return keyAsInt;
}


public IntegerLookupValue toIntegerLookupValue(){
    return IntegerLookupValue.builder().id(getKeyAsInt()).displayName(TranslatableStrings.constant(getCaption())).attributes(getAttributes()).active(isActive()).build();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toIntegerLookupValue"))

IntegerLookupValue aux = restTemplate.getForObject(builder.toUriString(),IntegerLookupValue.class);
return aux;
}


public StringLookupValue stringLookupValueFromJsonMap(Map<String,Object> map){
    final Object keyObj = map.get(PROPERTY_Key);
    final String key = keyObj != null ? keyObj.toString() : null;
    final ITranslatableString displayName = extractCaption(map);
    final ITranslatableString description = extractDescription(map);
    final Boolean active = extractActive(map);
    final StringLookupValueBuilder builder = StringLookupValue.builder().id(key).displayName(displayName).description(description).active(active).validationInformation(// TODO: Extract this from map for future usages
    null);
    @SuppressWarnings("unchecked")
    final Map<String, Object> attributes = (Map<String, Object>) map.get(PROPERTY_Attributes);
    if (attributes != null && !attributes.isEmpty()) {
        builder.attributes(attributes);
    }
    return builder.build();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/stringLookupValueFromJsonMap"))

.queryParam("map",map);
StringLookupValue aux = restTemplate.getForObject(builder.toUriString(),StringLookupValue.class);
return aux;
}


}