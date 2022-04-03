package de.metas.ui.web.document.filter.DocumentFilter;
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
public class Builder {

 private  String filterId;

 private  ITranslatableString caption;

 private  boolean facetFilter;

 private  ArrayList<DocumentFilterParam> parameters;

 private  Set<String> internalParameterNames;


public Builder setFacetFilter(boolean facetFilter){
    this.facetFilter = facetFilter;
    return this;
}


public Builder setParameters(List<DocumentFilterParam> parameters){
    if (!parameters.isEmpty()) {
        this.parameters = new ArrayList<>(parameters);
    } else {
        this.parameters = null;
    }
    return this;
}


public Builder addInternalParameter(DocumentFilterParam parameter){
    addParameter(parameter);
    addInternalParameterName(parameter.getFieldName());
    return this;
}


public Builder setFilterId(String filterId){
    this.filterId = filterId;
    return this;
}


public boolean hasParameters(){
    return !Check.isEmpty(parameters) || !Check.isEmpty(internalParameterNames);
}


public DocumentFilter build(){
    return new DocumentFilter(this);
}


public void addInternalParameterName(String parameterName){
    if (internalParameterNames == null) {
        internalParameterNames = new HashSet<>();
    }
    internalParameterNames.add(parameterName);
}


public Builder setCaption(String caption){
    return setCaption(TranslatableStrings.constant(caption));
}


public Builder addParameter(DocumentFilterParam parameter){
    if (parameters == null) {
        parameters = new ArrayList<>();
    }
    parameters.add(parameter);
    return this;
}


}