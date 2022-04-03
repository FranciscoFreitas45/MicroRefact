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


public LookupValue fromObject(Object id,String displayName){
    if (id == null) {
        return null;
    }
    if (id instanceof Integer) {
        return new IntegerLookupValue((int) id, TranslatableStrings.constant(displayName), null, /* helpText */
        null, /* attributes */
        null);
    } else {
        return new StringLookupValue(id.toString(), TranslatableStrings.constant(displayName), null, /* helpText */
        null, /* attributes */
        null, /* active */
        null);
    }
}


public Object getId(){
    return id;
}


public ITranslatableString getDescriptionTrl(){
    return description;
}


public boolean isActive(){
    final Boolean active = getActive();
    return active == null || active.booleanValue();
}


public IntegerLookupValue unknown(int id){
    return new IntegerLookupValue(id, TranslatableStrings.constant("<" + id + ">"), null, /* description */
    null, /* attributes */
    false);
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


public LookupValue cast(Object valueObj){
    return (LookupValue) valueObj;
}


public T transform(Function<LookupValue,T> transformation){
    return transformation.apply(this);
}


public T getAttribute(String name){
    if (additionalAttributes == null) {
        return null;
    }
    @SuppressWarnings("unchecked")
    final T value = (T) additionalAttributes.get(name);
    return value;
}


@Override
public int hashCode(){
    // NOTE: only the ID is considered on hashCode and equals
    return Objects.hashCode(id);
}


public IntegerLookupValue of(StringLookupValue stringLookupValue){
    if (stringLookupValue == null) {
        return null;
    }
    return new IntegerLookupValue(stringLookupValue.getIdAsInt(), stringLookupValue.displayName, stringLookupValue.description, null, /* attributes */
    stringLookupValue.getActive());
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


public LookupValue fromNamePair(NamePair namePair,String adLanguage,LookupValue defaultValue){
    if (namePair == null) {
        return defaultValue;
    }
    final ITranslatableString displayNameTrl;
    final ITranslatableString descriptionTrl;
    if (adLanguage == null) {
        displayNameTrl = TranslatableStrings.anyLanguage(namePair.getName());
        descriptionTrl = TranslatableStrings.anyLanguage(namePair.getDescription());
    } else {
        displayNameTrl = TranslatableStrings.singleLanguage(adLanguage, namePair.getName());
        descriptionTrl = TranslatableStrings.singleLanguage(adLanguage, namePair.getDescription());
    }
    if (namePair instanceof ValueNamePair) {
        final ValueNamePair vnp = (ValueNamePair) namePair;
        final ValueNamePairValidationInformation validationInformation = vnp.getValidationInformation();
        return StringLookupValue.of(vnp.getValue(), displayNameTrl, descriptionTrl, validationInformation);
    } else if (namePair instanceof KeyNamePair) {
        final KeyNamePair knp = (KeyNamePair) namePair;
        return IntegerLookupValue.of(knp.getKey(), displayNameTrl, descriptionTrl);
    } else {
        // shall not happen
        throw new IllegalArgumentException("Unknown namePair: " + namePair + " (" + namePair.getClass() + ")");
    }
}


public LookupValue concat(LookupValue lookupValue1,LookupValue lookupValue2){
    if (lookupValue1 == null) {
        return lookupValue2;
    }
    if (lookupValue2 == null) {
        return lookupValue1;
    }
    final String id = Joiner.on("_").skipNulls().join(lookupValue1.getIdAsString(), lookupValue2.getIdAsString());
    final ITranslatableString displayName = TranslatableStrings.join(" ", lookupValue1.getDisplayNameTrl(), lookupValue2.getDisplayNameTrl());
    final ITranslatableString description = TranslatableStrings.join(" ", lookupValue1.getDescriptionTrl(), lookupValue2.getDescriptionTrl());
    return StringLookupValue.of(id, displayName, description);
}


public ITranslatableString getDisplayNameTrl(){
    return displayName;
}


public String getDisplayName(String adLanguage){
    return displayName.translate(adLanguage);
}


public Object normalizeId(Object idObj,boolean numericKey){
    if (idObj == null) {
        return null;
    } else if (numericKey) {
        if (idObj instanceof Number) {
            final int idInt = ((Number) idObj).intValue();
            if (idInt < 0) {
                return null;
            }
            return idInt;
        } else if (idObj instanceof RepoIdAware) {
            return ((RepoIdAware) idObj).getRepoId();
        } else {
            final String idStr = idObj.toString().trim();
            if (idStr.isEmpty()) {
                return null;
            }
            final int idInt = Integer.parseInt(idStr);
            if (idInt < 0) {
                return null;
            }
            return idInt;
        }
    } else // string key
    {
        if (idObj instanceof ReferenceListAwareEnum) {
            return ((ReferenceListAwareEnum) idObj).getCode();
        } else {
            return idObj.toString();
        }
    }
}


@Override
public boolean equals(Object obj){
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (!(obj instanceof LookupValue)) {
        return false;
    }
    final LookupValue other = (LookupValue) obj;
    // NOTE: only the ID is considered on hashCode and equals
    return DataTypes.equals(id, other.id);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("id", id).add("displayName", displayName).add("description", description).add("additionalAttributes", additionalAttributes).add("active", active).add("validationInformation", validationInformation).toString();
}


public Boolean getActive(){
    return active;
}


public String getIdAsString(){
    return id.toString();
}


}