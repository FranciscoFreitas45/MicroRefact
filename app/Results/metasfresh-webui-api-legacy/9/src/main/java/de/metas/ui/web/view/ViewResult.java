package de.metas.ui.web.view;
 import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
@Immutable
public class ViewResult {

 private  ViewId viewId;

 private  ViewProfileId profileId;

 private  ViewId parentViewId;

 private  ITranslatableString viewDescription;

 private  ViewHeaderProperties viewHeaderProperties;

 private  long size;

 private  int queryLimit;

 private  boolean queryLimitHit;

 private  DocumentFilterList stickyFilters;

 private  DocumentFilterList filters;

 private  DocumentQueryOrderByList orderBys;

 private  int firstRow;

 private  int pageLength;

 private  ImmutableList<DocumentId> rowIds;

 private  ImmutableList<IViewRow> page;

 private  ImmutableMap<String,ViewResultColumn> columnInfosByFieldName;


public String getViewDescription(String adLanguage){
    if (viewDescription == null) {
        return null;
    }
    final String viewDescriptionStr = viewDescription.translate(adLanguage);
    return !Check.isEmpty(viewDescriptionStr, true) ? viewDescriptionStr : null;
}


public int getQueryLimit(){
    return queryLimit;
}


public boolean isQueryLimitHit(){
    return queryLimitHit;
}


public ViewHeaderProperties getHeaderProperties(){
    return viewHeaderProperties;
}


public List<IViewRow> getPage(){
    if (page == null) {
        throw new IllegalStateException("page not loaded for " + this);
    }
    return page;
}


public List<DocumentId> getRowIds(){
    if (rowIds != null) {
        return rowIds;
    }
    return getPage().stream().map(IViewRow::getId).collect(ImmutableList.toImmutableList());
}


public ViewResult ofViewAndRowIds(IView view,int firstRow,int pageLength,DocumentQueryOrderByList orderBys,List<DocumentId> rowIds){
    return builder().view(view).firstRow(firstRow).pageLength(pageLength).orderBys(orderBys).rowIds(rowIds).build();
}


public boolean isEmpty(){
    return getPage().isEmpty();
}


public ViewResult ofViewAndPage(IView view,int firstRow,int pageLength,DocumentQueryOrderByList orderBys,List<? extends IViewRow> page){
    return builder().view(view).firstRow(firstRow).pageLength(pageLength).orderBys(orderBys).rows(page).build();
}


public Map<String,ViewResultColumn> getColumnInfosByFieldName(){
    return columnInfosByFieldName;
}


public ViewProfileId getProfileId(){
    return profileId;
}


public DocumentQueryOrderByList getOrderBys(){
    return orderBys;
}


public long getSize(){
    return size;
}


public ViewResult ofView(IView view){
    return new ViewResult(view);
}


public ViewId getParentViewId(){
    return parentViewId;
}


public int getFirstRow(){
    return firstRow;
}


public DocumentFilterList getFilters(){
    return filters;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("viewId", viewId).add("profileId", profileId).add("filters", filters).add("orderBys", orderBys).add("firstRow", firstRow).add("pageLength", pageLength).add("page", page).toString();
}


public int getPageLength(){
    return pageLength;
}


public ViewId getViewId(){
    return viewId;
}


public DocumentFilterList getStickyFilters(){
    return stickyFilters;
}


public boolean isPageLoaded(){
    return page != null;
}


}