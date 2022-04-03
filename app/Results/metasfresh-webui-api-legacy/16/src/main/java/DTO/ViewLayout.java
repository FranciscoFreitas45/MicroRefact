package DTO;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.ui.web.cache.ETag;
import de.metas.ui.web.cache.ETagAware;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.annotation.ViewColumnHelper;
import de.metas.ui.web.view.descriptor.annotation.ViewColumnHelper.ClassViewColumnOverrides;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor;
import de.metas.ui.web.window.descriptor.factory.standard.LayoutFactory;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
public class ViewLayout implements ETagAware{

 private  Logger logger;

 private  WindowId windowId;

 private  DetailId detailId;

 private  ViewProfileId profileId;

 private  ITranslatableString caption;

 private  ITranslatableString description;

 private  ITranslatableString emptyResultText;

 private  ITranslatableString emptyResultHint;

 private  ImmutableList<DocumentFilterDescriptor> filters;

 private  DocumentQueryOrderByList defaultOrderBys;

 private  ImmutableList<DocumentLayoutElementDescriptor> elements;

 private  String idFieldName;

 private  String focusOnFieldName;

 private  boolean hasAttributesSupport;

 private  IncludedViewLayout includedViewLayout;

 private  String allowNewCaption;

 private  ImmutableSet<ViewCloseAction> allowedViewCloseActions;

 private  boolean hasTreeSupport;

 private  boolean treeCollapsible;

 private  int treeExpandedDepth;

 public  int TreeExpandedDepth_AllCollapsed;

 public  int TreeExpandedDepth_ExpandedFirstLevel;

 public  int TreeExpandedDepth_AllExpanded;

 private  boolean allowOpeningRowDetails;

 private  boolean geoLocationSupport;

 private  AtomicInteger nextETagVersionSupplier;

 private  ETag eTag;

 private  ViewLayout from;

 private  WindowId windowId;

 private  ViewProfileId profileId;

 private  Collection<DocumentFilterDescriptor> filters;

 private  String allowNewCaption;

 private  Boolean hasTreeSupport;

 private  Boolean treeCollapsible;

 private  Integer treeExpandedDepth;

 private  Boolean geoLocationSupport;

 private  ArrayList<DocumentLayoutElementDescriptor> elements;

 private  ArrayList<DocumentQueryOrderBy> defaultOrderBys;

 private  WindowId windowId;

 private  DetailId detailId;

 private  ITranslatableString caption;

 private  ITranslatableString description;

 private  ITranslatableString emptyResultText;

 private  ITranslatableString emptyResultHint;

 private  Collection<DocumentFilterDescriptor> filters;

 private  DocumentQueryOrderByList defaultOrderBys;

 private  boolean hasAttributesSupport;

 private  IncludedViewLayout includedViewLayout;

 private  LinkedHashSet<ViewCloseAction> allowedViewCloseActions;

 private  ImmutableSet<ViewCloseAction> DEFAULT_allowedViewCloseActions;

 private  boolean hasTreeSupport;

 private  boolean treeCollapsible;

 private  int treeExpandedDepth;

 private  boolean allowOpeningRowDetails;

 private  List<DocumentLayoutElementDescriptor.Builder> elementBuilders;

 private  String idFieldName;

 private  String focusOnFieldName;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public String getCaption(String adLanguage){
    return caption.translate(adLanguage);
}


public String getFocusOnFieldName(){
    return focusOnFieldName;
}


@Override
public ETag getETag(){
    return eTag;
}


public ImmutableList<DocumentFilterDescriptor> getFilters(){
    if (filters == null || filters.isEmpty()) {
        return ImmutableList.of();
    } else {
        return filters.stream().sorted(Comparator.comparing(DocumentFilterDescriptor::getSortNo)).collect(ImmutableList.toImmutableList());
    }
}


public WindowId getWindowIdEffective(){
    return windowId != null ? windowId : from.windowId;
}


public int getTreeExpandedDepth(){
    return treeExpandedDepth;
}


public List<DocumentLayoutElementDescriptor.Builder> getElements(){
    return elementBuilders;
}


public String getIdFieldName(){
    return idFieldName;
}


public String getEmptyResultHint(String adLanguage){
    return emptyResultHint.translate(adLanguage);
}


public ArrayList<DocumentQueryOrderBy> getDefaultOrderBysToEdit(){
    if (defaultOrderBys == null) {
        defaultOrderBys = new ArrayList<>(from.defaultOrderBys.toList());
    }
    return defaultOrderBys;
}


public String getDescription(String adLanguage){
    return description.translate(adLanguage);
}


public ArrayList<DocumentLayoutElementDescriptor> getElementsToEdit(){
    if (elements == null) {
        elements = new ArrayList<>(from.elements);
    }
    return elements;
}


public Set<String> getFieldNames(){
    return elementBuilders.stream().flatMap(element -> element.getFieldNames().stream()).collect(GuavaCollectors.toImmutableSet());
}


public DetailId getDetailId(){
    return detailId;
}


public String getEmptyResultText(String adLanguage){
    return emptyResultText.translate(adLanguage);
}


public ViewProfileId getProfileId(){
    return profileId;
}


public ImmutableSet<ViewCloseAction> getAllowedViewCloseActions(){
    return allowedViewCloseActions != null ? ImmutableSet.copyOf(allowedViewCloseActions) : DEFAULT_allowedViewCloseActions;
}


public DocumentQueryOrderByList getDefaultOrderBys(){
    return defaultOrderBys != null ? defaultOrderBys : DocumentQueryOrderByList.EMPTY;
}


@Nullable
public IncludedViewLayout getIncludedViewLayout(){
    return includedViewLayout;
}


public WindowId getWindowId(){
    return windowId;
}


public String getAllowNewCaption(){
    return allowNewCaption;
}


public Builder builder(){
    return new Builder();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))

Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public Builder setWindowId(WindowId windowId){
    this.windowId = windowId;
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWindowId"))

.queryParam("windowId",windowId);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public Builder setCaption(String caption){
    setCaption(TranslatableStrings.constant(caption));
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCaption"))

.queryParam("caption",caption);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


}