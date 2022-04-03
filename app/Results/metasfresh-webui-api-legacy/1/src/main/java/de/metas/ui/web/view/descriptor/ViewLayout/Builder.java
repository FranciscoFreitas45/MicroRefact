package de.metas.ui.web.view.descriptor.ViewLayout;
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
public class Builder {

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


public Builder addElementsFromViewRowClass(Class<T> viewRowClass,JSONViewDataType viewType){
    final List<DocumentLayoutElementDescriptor.Builder> elements = ViewColumnHelper.createLayoutElementsForClass(viewRowClass, viewType);
    if (elements.isEmpty()) {
        new AdempiereException("No elements found for viewRowClass=" + viewRowClass + " and viewType=" + viewType).throwIfDeveloperModeOrLogWarningElse(logger);
    }
    addElements(elements);
    return this;
}


public Builder setHasTreeSupport(boolean hasTreeSupport){
    this.hasTreeSupport = hasTreeSupport;
    return this;
}


public Builder setCaption(String caption){
    setCaption(TranslatableStrings.constant(caption));
    return this;
}


public Builder setDescription(ITranslatableString description){
    this.description = description;
    return this;
}


public Builder setEmptyResultText(ITranslatableString emptyResultText){
    this.emptyResultText = emptyResultText;
    return this;
}


public Builder removeElementByFieldName(String fieldName){
    for (final Iterator<DocumentLayoutElementDescriptor.Builder> it = elementBuilders.iterator(); it.hasNext(); ) {
        final DocumentLayoutElementDescriptor.Builder element = it.next();
        if (element.getFieldNames().contains(fieldName)) {
            element.removeFieldByFieldName(fieldName);
            if (element.getFieldsCount() == 0) {
                it.remove();
                continue;
            }
        }
    }
    return this;
}


public Builder setTreeExpandedDepth(int treeExpandedDepth){
    this.treeExpandedDepth = treeExpandedDepth;
    return this;
}


public Set<String> getFieldNames(){
    return elementBuilders.stream().flatMap(element -> element.getFieldNames().stream()).collect(GuavaCollectors.toImmutableSet());
}


public Builder setDefaultOrderBys(DocumentQueryOrderByList defaultOrderBys){
    this.defaultOrderBys = defaultOrderBys;
    return this;
}


public Builder clearViewCloseActions(){
    allowedViewCloseActions = new LinkedHashSet<>();
    return this;
}


public Builder setFocusOnFieldName(String focusOnFieldName){
    this.focusOnFieldName = focusOnFieldName;
    return this;
}


public Builder addElements(Stream<DocumentLayoutElementDescriptor.Builder> elementBuilders){
    elementBuilders.forEach(this::addElement);
    return this;
}


public ImmutableList<DocumentFilterDescriptor> getFilters(){
    if (filters == null || filters.isEmpty()) {
        return ImmutableList.of();
    } else {
        return filters.stream().sorted(Comparator.comparing(DocumentFilterDescriptor::getSortNo)).collect(ImmutableList.toImmutableList());
    }
}


public Builder setDetailId(DetailId detailId){
    this.detailId = detailId;
    return this;
}


public Builder setIdFieldName(String idFieldName){
    this.idFieldName = idFieldName;
    return this;
}


public Builder allowViewCloseAction(ViewCloseAction viewCloseAction){
    if (allowedViewCloseActions == null) {
        allowedViewCloseActions = new LinkedHashSet<>();
    }
    allowedViewCloseActions.add(viewCloseAction);
    return this;
}


public Builder addElementsFromViewRowClassAndFieldNames(Class<T> viewRowClass,JSONViewDataType viewDataType,ClassViewColumnOverrides columns){
    final List<DocumentLayoutElementDescriptor.Builder> elements = ViewColumnHelper.createLayoutElementsForClassAndFieldNames(viewRowClass, viewDataType, columns);
    // shall never happen
    Check.assumeNotEmpty(elements, "elements is not empty");
    addElements(elements);
    return this;
}


public Builder clearElements(){
    elementBuilders.clear();
    return this;
}


public boolean hasElements(){
    return !elementBuilders.isEmpty();
}


public Builder setAllowOpeningRowDetails(boolean allowOpeningRowDetails){
    this.allowOpeningRowDetails = allowOpeningRowDetails;
    return this;
}


public Builder setIncludedViewLayout(IncludedViewLayout includedViewLayout){
    this.includedViewLayout = includedViewLayout;
    return this;
}


public Builder setTreeCollapsible(boolean treeCollapsible){
    this.treeCollapsible = treeCollapsible;
    return this;
}


public ImmutableSet<ViewCloseAction> getAllowedViewCloseActions(){
    return allowedViewCloseActions != null ? ImmutableSet.copyOf(allowedViewCloseActions) : DEFAULT_allowedViewCloseActions;
}


public DocumentQueryOrderByList getDefaultOrderBys(){
    return defaultOrderBys != null ? defaultOrderBys : DocumentQueryOrderByList.EMPTY;
}


public ViewLayout build(){
    return new ViewLayout(this);
}


public Builder setEmptyResultHint(ITranslatableString emptyResultHint){
    this.emptyResultHint = emptyResultHint;
    return this;
}


public Builder setFilters(Collection<DocumentFilterDescriptor> filters){
    this.filters = filters;
    return this;
}


public List<DocumentLayoutElementDescriptor.Builder> getElements(){
    return elementBuilders;
}


public String getIdFieldName(){
    return idFieldName;
}


public Builder addFilter(DocumentFilterDescriptor filter){
    if (filters == null) {
        filters = new ArrayList<>();
    }
    filters.add(filter);
    return this;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("detailId", detailId).add("caption", caption).add("elements-count", elementBuilders.size()).toString();
}


public Builder setWindowId(WindowId windowId){
    this.windowId = windowId;
    return this;
}


public Builder addElement(DocumentLayoutElementDescriptor.Builder elementBuilder){
    elementBuilders.add(elementBuilder);
    return this;
}


public List<DocumentLayoutElementDescriptor> buildElements(){
    return elementBuilders.stream().filter(// have some field builders
    elementBuilder -> elementBuilder.getFieldsCount() > 0).map(elementBuilder -> elementBuilder.build()).collect(GuavaCollectors.toImmutableList());
}


public Builder setHasAttributesSupport(boolean hasAttributesSupport){
    this.hasAttributesSupport = hasAttributesSupport;
    return this;
}


}