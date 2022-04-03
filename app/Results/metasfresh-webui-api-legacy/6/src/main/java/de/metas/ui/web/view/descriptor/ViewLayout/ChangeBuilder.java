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
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangeBuilder {

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


public ChangeBuilder defaultOrderBy(DocumentQueryOrderBy orderBy){
    getDefaultOrderBysToEdit().add(orderBy);
    return this;
}


public ArrayList<DocumentQueryOrderBy> getDefaultOrderBysToEdit(){
    if (defaultOrderBys == null) {
        defaultOrderBys = new ArrayList<>(from.defaultOrderBys.toList());
    }
    return defaultOrderBys;
}


public ChangeBuilder treeSupport(boolean hasTreeSupport,Boolean treeCollapsible,Integer treeExpandedDepth){
    this.hasTreeSupport = hasTreeSupport;
    this.treeCollapsible = treeCollapsible;
    this.treeExpandedDepth = treeExpandedDepth;
    return this;
}


public ChangeBuilder allowNewCaption(String allowNewCaption){
    this.allowNewCaption = allowNewCaption;
    return this;
}


public void setElements(ArrayList<DocumentLayoutElementDescriptor> elements){
    this.elements = elements;
}


public ChangeBuilder filters(Collection<DocumentFilterDescriptor> filters){
    this.filters = filters;
    return this;
}


public ArrayList<DocumentLayoutElementDescriptor> getElementsToEdit(){
    if (elements == null) {
        elements = new ArrayList<>(from.elements);
    }
    return elements;
}


public ChangeBuilder windowId(WindowId windowId){
    this.windowId = windowId;
    return this;
}


public ChangeBuilder clearDefaultOrderBys(){
    getDefaultOrderBysToEdit().clear();
    return this;
}


public ViewLayout build(){
    final WindowId windowIdEffective = getWindowIdEffective();
    final ViewProfileId profileIdEffective = !ViewProfileId.isNull(profileId) ? profileId : from.profileId;
    final ImmutableList<DocumentFilterDescriptor> filtersEffective = ImmutableList.copyOf(filters != null ? filters : from.getFilters());
    final String allowNewCaptionEffective = allowNewCaption != null ? allowNewCaption : from.allowNewCaption;
    final boolean hasTreeSupportEffective = hasTreeSupport != null ? hasTreeSupport.booleanValue() : from.hasTreeSupport;
    final boolean treeCollapsibleEffective = treeCollapsible != null ? treeCollapsible.booleanValue() : from.treeCollapsible;
    final int treeExpandedDepthEffective = treeExpandedDepth != null ? treeExpandedDepth.intValue() : from.treeExpandedDepth;
    final boolean geoLocationSupportEffective = geoLocationSupport != null ? geoLocationSupport.booleanValue() : from.geoLocationSupport;
    final ImmutableList<DocumentLayoutElementDescriptor> elementsEffective = elements != null ? ImmutableList.copyOf(elements) : from.elements;
    final DocumentQueryOrderByList defaultOrderBysEffective = DocumentQueryOrderByList.ofList(defaultOrderBys);
    // If there will be no change then return this
    if (Objects.equals(from.windowId, windowIdEffective) && Objects.equals(from.profileId, profileIdEffective) && Objects.equals(from.filters, filtersEffective) && Objects.equals(from.allowNewCaption, allowNewCaptionEffective) && from.hasTreeSupport == hasTreeSupportEffective && from.treeCollapsible == treeCollapsibleEffective && from.treeExpandedDepth == treeExpandedDepthEffective && Objects.equals(from.elements, elementsEffective) && DocumentQueryOrderByList.equals(from.defaultOrderBys, defaultOrderBysEffective) && Objects.equals(from.geoLocationSupport, geoLocationSupportEffective)) {
        return from;
    }
    return new ViewLayout(from, windowIdEffective, profileIdEffective, filtersEffective, defaultOrderBysEffective, allowNewCaptionEffective, hasTreeSupportEffective, treeCollapsibleEffective, treeExpandedDepthEffective, geoLocationSupportEffective, elementsEffective);
}


public ChangeBuilder elementsOrder(String fieldNames){
    final ImmutableMap<String, DocumentLayoutElementDescriptor> elementsByFieldName = Maps.uniqueIndex(getElementsToEdit(), DocumentLayoutElementDescriptor::getFirstFieldName);
    final ArrayList<DocumentLayoutElementDescriptor> elementsNew = new ArrayList<>();
    for (final String fieldName : fieldNames) {
        final DocumentLayoutElementDescriptor element = elementsByFieldName.get(fieldName);
        if (element == null) {
            logger.warn("Field {} was not found. Will be ignored." + "\n Available field names are: {}." + "\n If this is a standard view, pls check if the field added to window {}.", fieldName, elementsByFieldName.keySet(), getWindowIdEffective());
            continue;
        }
        elementsNew.add(element);
    }
    setElements(elementsNew);
    return this;
}


public ChangeBuilder profileId(ViewProfileId profileId){
    this.profileId = profileId;
    return this;
}


public WindowId getWindowIdEffective(){
    return windowId != null ? windowId : from.windowId;
}


public ChangeBuilder geoLocationSupport(boolean geoLocationSupport){
    this.geoLocationSupport = geoLocationSupport;
    return this;
}


public ChangeBuilder element(DocumentLayoutElementDescriptor element){
    getElementsToEdit().add(element);
    return this;
}


}