package DTO;
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
public class DocumentFilterDescriptor {

 private  String filterId;

 private  int sortNo;

 private  ITranslatableString displayNameTrls;

 private  boolean frequentUsed;

 private  DocumentFilterInlineRenderMode inlineRenderMode;

 private  PanelLayoutType parametersLayoutType;

 private  ImmutableMap<String,DocumentFilterParamDescriptor> parametersByName;

 private  ImmutableList<DocumentFilterParam> internalParameters;

 private  boolean autoFilter;

 private  BarcodeScannerType barcodeScannerType;

 private  boolean facetFilter;

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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://16";


public PanelLayoutType getParametersLayoutType(){
    return parametersLayoutType != null ? parametersLayoutType : PanelLayoutType.Panel;
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


public String getDisplayName(String adLanguage){
    return displayNameTrls.translate(adLanguage);
}


public Collection<DocumentFilterParamDescriptor> getParameters(){
    return parametersByName.values();
}


public Builder builder(){
    return new Builder();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))

Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


}