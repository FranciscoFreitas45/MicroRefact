package DTO;
 import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntFunction;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.document.filter.DocumentFilterParam.Operator;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import de.metas.util.lang.RepoIdAware;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
public class DocumentFilter {

 private  String filterId;

 private  ITranslatableString caption;

 private  ImmutableList<DocumentFilterParam> parameters;

 private  ImmutableMap<String,DocumentFilterParam> parametersByName;

 private  ImmutableSet<String> internalParameterNames;

 private  boolean facetFilter;

 private  String filterId;

 private  ITranslatableString caption;

 private  boolean facetFilter;

 private  ArrayList<DocumentFilterParam> parameters;

 private  Set<String> internalParameterNames;


public Builder setFilterId(String filterId){
    this.filterId = filterId;
    return this;
}


public LocalDate getParameterValueAsLocalDateOrNull(String parameterName){
    final LocalDate defaultValue = null;
    return getParameterValueAsLocalDateOr(parameterName, defaultValue);
}


public String getParameterValueAsString(String parameterName,String defaultValue){
    final DocumentFilterParam param = getParameterOrNull(parameterName);
    if (param == null) {
        return defaultValue;
    }
    return param.getValueAsString();
}


public int getParameterValueAsInt(String parameterName,int defaultValue){
    final DocumentFilterParam param = getParameterOrNull(parameterName);
    if (param == null) {
        return defaultValue;
    }
    return param.getValueAsInt(defaultValue);
}


public LocalDate getParameterValueAsLocalDateOr(String parameterName,LocalDate defaultValue){
    final DocumentFilterParam param = getParameterOrNull(parameterName);
    if (param == null) {
        return defaultValue;
    }
    return param.getValueAsLocalDateOr(defaultValue);
}


public String getCaption(String adLanguage){
    return caption != null ? caption.translate(adLanguage) : null;
}


public boolean getParameterValueAsBoolean(String parameterName,boolean defaultValue){
    final DocumentFilterParam param = getParameterOrNull(parameterName);
    if (param == null) {
        return defaultValue;
    }
    return param.getValueAsBoolean(defaultValue);
}


public DocumentFilter inArrayFilter(String filterId,String fieldName,Collection<Integer> values){
    Check.assumeNotEmpty(values, "values is not empty");
    return builder().setFilterId(filterId).addParameter(DocumentFilterParam.builder().setFieldName(fieldName).setOperator(Operator.IN_ARRAY).setValue(ImmutableList.copyOf(values)).build()).build();
}


public Builder setParameters(List<DocumentFilterParam> parameters){
    if (!parameters.isEmpty()) {
        this.parameters = new ArrayList<>(parameters);
    } else {
        this.parameters = null;
    }
    return this;
}


public boolean isInternalParameter(String parameterName){
    return internalParameterNames.contains(parameterName);
}


public void addInternalParameterName(String parameterName){
    if (internalParameterNames == null) {
        internalParameterNames = new HashSet<>();
    }
    internalParameterNames.add(parameterName);
}


public T getParameterValueAs(String parameterName){
    final DocumentFilterParam param = getParameterOrNull(parameterName);
    if (param == null) {
        return null;
    }
    @SuppressWarnings("unchecked")
    final T value = (T) param.getValue();
    return value;
}


public ImmutableList<DocumentFilterParam> getParameters(){
    return parameters;
}


public DocumentFilterParam getParameterOrNull(String parameterName){
    return parametersByName.get(parameterName);
}


public T getParameterValueAsRepoIdOrNull(String parameterName,IntFunction<T> repoIdMapper){
    final DocumentFilterParam param = getParameterOrNull(parameterName);
    if (param == null) {
        return null;
    }
    return param.getValueAsRepoIdOrNull(repoIdMapper);
}


public DocumentFilterParam getParameter(String parameterName){
    final DocumentFilterParam parameter = getParameterOrNull(parameterName);
    if (parameter == null) {
        throw new AdempiereException("Parameter " + parameterName + " not found in " + this);
    }
    return parameter;
}


}