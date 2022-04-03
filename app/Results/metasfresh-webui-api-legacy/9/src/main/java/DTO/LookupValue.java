package DTO;
 import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.KeyNamePair;
import org.compiere.util.NamePair;
import org.compiere.util.ValueNamePair;
import org.compiere.util.ValueNamePairValidationInformation;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.JavaProcess;
import de.metas.ui.web.process.descriptor.ProcessParamLookupValuesProvider;
import de.metas.util.lang.ReferenceListAwareEnum;
import de.metas.util.lang.RepoIdAware;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
public class LookupValue {

 protected  ValueNamePairValidationInformation validationInformation;

 protected  Object id;

 protected  ITranslatableString displayName;

 protected  ITranslatableString description;

 private  Boolean active;

 private  ImmutableMap<String,Object> additionalAttributes;

 private  Integer idInt;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public Object getId(){
    return id;
}


public ITranslatableString getDescriptionTrl(){
    return description;
}


public int getAttributeAsInt(String name,int defaultValue){
    final Object valueObj = getAttribute(name);
    if (valueObj == null) {
        return defaultValue;
    } else if (valueObj instanceof Number) {
        return ((Number) valueObj).intValue();
    } else {
        final String valueStr = valueObj.toString().trim();
        if (valueStr.isEmpty()) {
            return defaultValue;
        }
        return Integer.parseInt(valueStr);
    }
}


public T getAttribute(String name){
    if (additionalAttributes == null) {
        return null;
    }
    @SuppressWarnings("unchecked")
    final T value = (T) additionalAttributes.get(name);
    return value;
}


public Set<Integer> getAttributeAsIntSet(String name){
    final Object valueObj = getAttribute(name);
    if (valueObj == null) {
        return ImmutableSet.of();
    } else if (valueObj instanceof Collection) {
        @SuppressWarnings("unchecked")
        final Collection<Integer> coll = (Collection<Integer>) valueObj;
        return ImmutableSet.copyOf(coll);
    } else {
        final String valueStr = valueObj.toString().trim();
        if (valueStr.isEmpty()) {
            return ImmutableSet.of();
        }
        try {
            return Splitter.on(",").omitEmptyStrings().trimResults().splitToList(valueStr).stream().map(Integer::parseInt).collect(ImmutableSet.toImmutableSet());
        } catch (final Exception ex) {
            throw new AdempiereException("Cannot convert attribute value to integer Set", ex).setParameter("attributeName", name).setParameter("attributeValue", valueStr).setParameter("lookupValue", this).appendParametersToMessage();
        }
    }
}


public ValueNamePairValidationInformation getValidationInformation(){
    return validationInformation;
}


public T getIdAs(IntFunction<T> idMapper){
    return idMapper.apply(getIdAsInt());
}


public Map<String,Object> getAttributes(){
    return additionalAttributes != null ? additionalAttributes : ImmutableMap.of();
}


@Override
public int getIdAsInt(){
    return (Integer) id;
}


public ITranslatableString getDisplayNameTrl(){
    return displayName;
}


public String getDisplayName(String adLanguage){
    return displayName.translate(adLanguage);
}


public Boolean getActive(){
    return active;
}


public String getIdAsString(){
    return id.toString();
}


public LookupValue cast(Object valueObj){
    return (LookupValue) valueObj;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/cast"))

.queryParam("valueObj",valueObj);
LookupValue aux = restTemplate.getForObject(builder.toUriString(),LookupValue.class);
return aux;
}


}