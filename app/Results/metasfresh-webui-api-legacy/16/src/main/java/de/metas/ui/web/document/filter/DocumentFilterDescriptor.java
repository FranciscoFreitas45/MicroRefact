package de.metas.ui.web.document.filter;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.process.BarcodeScannerType;
import de.metas.ui.web.window.datatypes.DebugProperties;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import lombok.EqualsAndHashCode;
import lombok.Getter;
// required for (ETag) caching
@EqualsAndHashCode
public class DocumentFilterDescriptor {

@Getter
 private  String filterId;

@Getter
 private  int sortNo;

 private  ITranslatableString displayNameTrls;

@Getter
 private  boolean frequentUsed;

@Getter
 private  DocumentFilterInlineRenderMode inlineRenderMode;

@Getter
 private  PanelLayoutType parametersLayoutType;

 private  ImmutableMap<String,DocumentFilterParamDescriptor> parametersByName;

@Getter
 private  ImmutableList<DocumentFilterParam> internalParameters;

@Getter
 private  boolean autoFilter;

@Getter
 private  BarcodeScannerType barcodeScannerType;

@Getter
 private  boolean facetFilter;

@Getter
 private  DebugProperties debugProperties;

 private  String filterId;

 private  int sortNo;

 private  ITranslatableString displayNameTrls;

 private  boolean frequentUsed;

 private  DocumentFilterInlineRenderMode inlineRenderMode;

 private  PanelLayoutType parametersLayoutType;

 private  List<DocumentFilterParamDescriptor.Builder> parameters;

 private  List<DocumentFilterParam> internalParameters;

 private  boolean facetFilter;

 private  Map<String,Object> debugProperties;


public Builder addParameters(Collection<DocumentFilterParamDescriptor.Builder> parameters){
    this.parameters.addAll(parameters);
    return this;
}


public Builder addInternalParameter(DocumentFilterParam parameter){
    internalParameters.add(parameter);
    return this;
}


public Builder setFilterId(String filterId){
    this.filterId = filterId;
    return this;
}


public Builder setParametersLayoutType(PanelLayoutType parametersLayoutType){
    this.parametersLayoutType = parametersLayoutType;
    return this;
}


public PanelLayoutType getParametersLayoutType(){
    return parametersLayoutType != null ? parametersLayoutType : PanelLayoutType.Panel;
}


public Builder setFrequentUsed(boolean frequentUsed){
    this.frequentUsed = frequentUsed;
    return this;
}


public DocumentFilterInlineRenderMode getInlineRenderMode(){
    return inlineRenderMode != null ? inlineRenderMode : DocumentFilterInlineRenderMode.BUTTON;
}


public ITranslatableString getDisplayNameTrls(){
    if (displayNameTrls != null) {
        return displayNameTrls;
    }
    if (parameters.size() == 1) {
        return parameters.get(0).getDisplayName();
    }
    return null;
}


public DocumentFilterParamDescriptor getParameterByName(String parameterName){
    final DocumentFilterParamDescriptor parameter = parametersByName.get(parameterName);
    if (parameter == null) {
        throw new NoSuchElementException("Parameter '" + parameterName + "' not found in " + this);
    }
    return parameter;
}


public Builder setDisplayName(String displayName){
    displayNameTrls = TranslatableStrings.constant(displayName);
    return this;
}


public Builder addParameter(DocumentFilterParamDescriptor.Builder parameter){
    parameters.add(parameter);
    return this;
}


public Builder setFacetFilter(boolean facetFilter){
    this.facetFilter = facetFilter;
    return this;
}


public ImmutableMap<String,DocumentFilterParamDescriptor> buildParameters(){
    final Map<String, Integer> nextParamIndexByFieldName = new HashMap<>();
    return parameters.stream().peek((paramBuilder) -> {
        final String fieldName = paramBuilder.getFieldName();
        final Integer nextParamIndex = nextParamIndexByFieldName.get(fieldName);
        if (nextParamIndex == null) {
            paramBuilder.setParameterName(fieldName);
            nextParamIndexByFieldName.put(fieldName, 2);
        } else {
            paramBuilder.setParameterName(fieldName + nextParamIndex);
            nextParamIndexByFieldName.put(fieldName, nextParamIndex + 1);
        }
    }).map(paramBuilder -> paramBuilder.build()).collect(GuavaCollectors.toImmutableMapByKey(param -> param.getParameterName()));
}


public Builder putDebugProperty(String name,Object value){
    Check.assumeNotEmpty(name, "name is not empty");
    if (debugProperties == null) {
        debugProperties = new LinkedHashMap<>();
    }
    debugProperties.put("debug-" + name, value);
    return this;
}


public boolean hasParameters(){
    return !parameters.isEmpty();
}


public String getDisplayName(String adLanguage){
    return displayNameTrls.translate(adLanguage);
}


public DocumentFilterDescriptor build(){
    return new DocumentFilterDescriptor(this);
}


public Collector<DocumentFilterParamDescriptor.Builder,?,Builder> collectParameters(){
    final Supplier<List<DocumentFilterParamDescriptor.Builder>> supplier = ArrayList::new;
    final BiConsumer<List<DocumentFilterParamDescriptor.Builder>, DocumentFilterParamDescriptor.Builder> accumulator = (list, filter) -> list.add(filter);
    final BinaryOperator<List<DocumentFilterParamDescriptor.Builder>> combiner = (list1, list2) -> {
        list1.addAll(list2);
        return list1;
    };
    final Function<List<DocumentFilterParamDescriptor.Builder>, Builder> finisher = (params) -> addParameters(params);
    return Collector.of(supplier, accumulator, combiner, finisher);
}


public Builder builder(){
    return new Builder();
}


public Collection<DocumentFilterParamDescriptor> getParameters(){
    return parametersByName.values();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("filterId", filterId).add("parameters", parametersByName.isEmpty() ? null : parametersByName.values()).add("internalParameters", internalParameters.isEmpty() ? null : internalParameters).toString();
}


public Builder setSortNo(int sortNo){
    this.sortNo = sortNo;
    return this;
}


public Builder setInlineRenderMode(DocumentFilterInlineRenderMode inlineRenderMode){
    this.inlineRenderMode = inlineRenderMode;
    return this;
}


}