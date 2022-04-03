package de.metas.ui.web.window.descriptor.DocumentLayoutDescriptor;
 import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.window.datatypes.DebugProperties;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.exceptions.DocumentLayoutDetailNotFoundException;
import de.metas.util.Check;
import lombok.Getter;
import lombok.NonNull;
public class Builder {

 private  Logger logger;

 private  WindowId windowId;

 private  ITranslatableString caption;

 private  DocumentLayoutElementDescriptor documentSummaryElement;

 private  DocumentLayoutElementDescriptor docActionElement;

 private  DocumentLayoutSingleRow.Builder singleRowLayout;

 private  ViewLayout.Builder _gridView;

 private  ViewLayout _sideListView;

 private  List<DocumentLayoutDetailDescriptor> details;

 private  Map<String,String> debugProperties;

 private  Stopwatch stopwatch;


public Builder setDocumentSummaryElement(DocumentLayoutElementDescriptor documentSummaryElement){
    this.documentSummaryElement = documentSummaryElement;
    return this;
}


public Builder setGridView(ViewLayout.Builder gridView){
    this._gridView = gridView;
    return this;
}


public Builder setDocActionElement(DocumentLayoutElementDescriptor docActionElement){
    this.docActionElement = docActionElement;
    return this;
}


public Builder setCaption(ITranslatableString caption){
    this.caption = TranslatableStrings.nullToEmpty(caption);
    return this;
}


public Builder setSideListView(ViewLayout sideListViewLayout){
    this._sideListView = sideListViewLayout;
    return this;
}


public ViewLayout getSideList(){
    Preconditions.checkNotNull(_sideListView, "sideList");
    return _sideListView;
}


public void buildDetailsRecurse(DocumentLayoutDetailDescriptor detail,ImmutableMap.Builder<DetailId,DocumentLayoutDetailDescriptor> map){
    putIfNotEmpty(detail, map);
    for (final DocumentLayoutDetailDescriptor subDetail : detail.getSubTabLayouts()) {
        buildDetailsRecurse(subDetail, map);
    }
}


public ViewLayout.Builder getGridView(){
    return _gridView;
}


public Builder addDetail(DocumentLayoutDetailDescriptor detail){
    if (detail == null) {
        return this;
    }
    if (detail.isEmpty()) {
        logger.trace("Skip adding detail to layout because it is empty; detail={}", detail);
        return this;
    }
    details.add(detail);
    return this;
}


public Builder putDebugProperty(String name,String value){
    debugProperties.put(name, value);
    return this;
}


public DocumentLayoutDescriptor build(){
    // 
    // Debug informations:
    putDebugProperty("generator-thread", Thread.currentThread().getName());
    putDebugProperty("generator-timestamp", Instant.now().toString());
    if (stopwatch != null) {
        putDebugProperty("generator-duration", stopwatch.toString());
    }
    return new DocumentLayoutDescriptor(this);
}


public Builder setSingleRowLayout(DocumentLayoutSingleRow.Builder singleRowLayout){
    this.singleRowLayout = singleRowLayout;
    return this;
}


public Map<DetailId,DocumentLayoutDetailDescriptor> buildDetails(){
    final ImmutableMap.Builder<DetailId, DocumentLayoutDetailDescriptor> map = ImmutableMap.builder();
    for (final DocumentLayoutDetailDescriptor detail : details) {
        putIfNotEmpty(detail, map);
    }
    return map.build();
}


public Map<DetailId,DocumentLayoutDetailDescriptor> buildAllDetails(){
    final ImmutableMap.Builder<DetailId, DocumentLayoutDetailDescriptor> map = ImmutableMap.builder();
    for (final DocumentLayoutDetailDescriptor detail : details) {
        buildDetailsRecurse(detail, map);
    }
    return map.build();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("windowId", windowId).toString();
}


public Builder setWindowId(WindowId windowId){
    this.windowId = windowId;
    return this;
}


public void putIfNotEmpty(DocumentLayoutDetailDescriptor detail,ImmutableMap.Builder<DetailId,DocumentLayoutDetailDescriptor> map){
    if (detail.isEmpty()) {
        return;
    }
    map.put(detail.getDetailId(), detail);
}


public DocumentLayoutSingleRow.Builder getSingleRowLayout(){
    return singleRowLayout;
}


public Builder setStopwatch(Stopwatch stopwatch){
    this.stopwatch = stopwatch;
    return this;
}


}