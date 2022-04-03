package de.metas.ui.web.window.datatypes.json;
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
@ApiModel(value = "lookup-value", description = "pair of { field : value}")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@EqualsAndHashCode
public class JSONLookupValue {

 static  String PROPERTY_Key;

@JsonProperty(PROPERTY_Key)
@Getter
 private  String key;

@JsonIgnore
 private  Integer keyAsInt;

 private  String PROPERTY_Caption;

@JsonProperty(PROPERTY_Caption)
@Getter
 private  String caption;

 private  String PROPERTY_Description;

@JsonProperty(PROPERTY_Description)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
 private  String description;

 private  String PROPERTY_Attributes;

@JsonProperty(PROPERTY_Attributes)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
 private  Map<String,Object> attributes;

 private  String PROPERTY_Active;

@JsonProperty(PROPERTY_Active)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  Boolean active;

 private  String PROPERTY_ValidationInformation;

@JsonProperty(PROPERTY_ValidationInformation)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
 private  JSONLookupValueValidationInformation validationInformation;


public JSONLookupValue ofLookupValue(LookupValue lookupValue,String adLanguage){
    final String id = lookupValue.getIdAsString();
    final ITranslatableString displayNameTrl = lookupValue.getDisplayNameTrl();
    final ITranslatableString descriptionTrl = lookupValue.getDescriptionTrl();
    // final String adLanguage = Env.getAD_Language(Env.getCtx());
    final String displayName = displayNameTrl.translate(adLanguage);
    final String description = descriptionTrl.translate(adLanguage);
    final JSONLookupValueValidationInformation validationInformation = JSONLookupValueValidationInformation.ofNullable(lookupValue.getValidationInformation(), adLanguage);
    // NOTE: for bandwidth optimization, we provide the flag only when it's false
    final Boolean active = !lookupValue.isActive() ? Boolean.FALSE : null;
    return new JSONLookupValue(id, displayName, description, lookupValue.getAttributes(), active, validationInformation);
}


public int getKeyAsInt(){
    Integer keyAsInt = this.keyAsInt;
    if (keyAsInt == null) {
        keyAsInt = this.keyAsInt = Integer.parseInt(getKey());
    }
    return keyAsInt;
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
}


public boolean isActive(){
    return active == null || active.booleanValue();
}


public JSONLookupValue unknown(int id){
    return of(id, "<" + id + ">");
}


public Boolean extractActive(Map<String,Object> map){
    return StringUtils.toBoolean(map.get(PROPERTY_Active), null);
}


public IntegerLookupValue toIntegerLookupValue(){
    return IntegerLookupValue.builder().id(getKeyAsInt()).displayName(TranslatableStrings.constant(getCaption())).attributes(getAttributes()).active(isActive()).build();
}


public ITranslatableString extractDescription(Map<String,Object> map){
    final Object descriptionObj = map.get(PROPERTY_Description);
    final String descriptionStr = descriptionObj != null ? descriptionObj.toString() : "";
    final ITranslatableString description = TranslatableStrings.anyLanguage(descriptionStr);
    return description;
}


public JSONLookupValue ofNamePair(NamePair namePair){
    final Map<String, Object> attributes = null;
    final Boolean active = null;
    final JSONLookupValueValidationInformation validationInformation = null;
    return new JSONLookupValue(namePair.getID(), namePair.getName(), namePair.getDescription(), attributes, active, validationInformation);
}


public JSONLookupValue of(String key,String caption,String description){
    final Map<String, Object> attributes = null;
    final Boolean active = null;
    return new JSONLookupValue(key, caption, description, attributes, active, null);
}


public StringLookupValue toStringLookupValue(){
    return StringLookupValue.builder().id(getKey()).displayName(TranslatableStrings.constant(getCaption())).attributes(getAttributes()).active(isActive()).validationInformation(// NOTE: converting back from JSON is not supported nor needed atm
    null).build();
}


public ITranslatableString extractCaption(Map<String,Object> map){
    final Object captionObj = map.get(PROPERTY_Caption);
    final String caption = captionObj != null ? captionObj.toString() : "";
    final ITranslatableString displayName = TranslatableStrings.anyLanguage(caption);
    return displayName;
}


public IntegerLookupValue integerLookupValueFromJsonMap(Map<String,Object> map){
    final Object keyObj = map.get(PROPERTY_Key);
    if (keyObj == null) {
        return null;
    }
    final String keyStr = keyObj.toString().trim();
    if (keyStr.isEmpty()) {
        return null;
    }
    final int keyInt = Integer.parseInt(keyStr);
    final ITranslatableString displayName = extractCaption(map);
    final ITranslatableString description = extractDescription(map);
    final Boolean active = extractActive(map);
    final IntegerLookupValueBuilder builder = IntegerLookupValue.builder().id(keyInt).displayName(displayName).description(description).active(active);
    @SuppressWarnings("unchecked")
    final Map<String, Object> attributes = (Map<String, Object>) map.get(PROPERTY_Attributes);
    if (attributes != null && !attributes.isEmpty()) {
        builder.attributes(attributes);
    }
    return builder.build();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("key", key).add("caption", caption).add("attributes", attributes).add("active", active).add("validationInformation", validationInformation).toString();
}


}