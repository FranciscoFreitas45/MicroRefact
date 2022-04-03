package DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


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


public ViewId getParentViewId(){
    return parentViewId;
}


public int getFirstRow(){
    return firstRow;
}


public DocumentFilterList getFilters(){
    return filters;
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


public ViewResult ofViewAndPage(IView view,int firstRow,int pageLength,DocumentQueryOrderByList orderBys,List<? extends IViewRow> page){
    return builder().view(view).firstRow(firstRow).pageLength(pageLength).orderBys(orderBys).rows(page).build();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofViewAndPage"))

.queryParam("view",view);
.queryParam("firstRow",firstRow);
.queryParam("pageLength",pageLength);
.queryParam("orderBys",orderBys);
.queryParam("page",page);
ViewResult aux = restTemplate.getForObject(builder.toUriString(),ViewResult.class);
return aux;
}


}