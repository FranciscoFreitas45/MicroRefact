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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofLookupValue"))

.queryParam("lookupValue",lookupValue);
.queryParam("adLanguage",adLanguage);
JSONLookupValue aux = restTemplate.getForObject(builder.toUriString(),JSONLookupValue.class);
return aux;
}


}