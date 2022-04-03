package DTO;
 import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.util.Evaluatee;
import java.util.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.process.view.ViewActionDescriptorsList;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewResult;
import de.metas.ui.web.view.ViewRowsOrderBy;
import de.metas.ui.web.view.event.ViewChangesCollector;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
public class HUEditorView implements IView{

 private  ViewId parentViewId;

 private  DocumentId parentRowId;

 private  ViewId viewId;

 private  JSONViewDataType viewType;

 private  String referencingTableName;

 private  Set<DocumentPath> referencingDocumentPaths;

 private  ViewActionDescriptorsList actions;

 private  ImmutableList<RelatedProcessDescriptor> additionalRelatedProcessDescriptors;

 private  HUEditorViewBuffer rowsBuffer;

 private  DocumentFilterDescriptorsProvider filterDescriptors;

 private  DocumentFilterList filters;

 private  ImmutableMap<String,Object> parameters;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


@Override
public LookupValuesList getFilterParameterDropdown(String filterId,String filterParameterName,Evaluatee ctx){
    return filterDescriptors.getByFilterId(filterId).getParameterByName(filterParameterName).getLookupDataSource().get().findEntities(ctx);
}


@Override
public ViewResult getPage(int firstRow,int pageLength,ViewRowsOrderBy orderBys){
    final List<HUEditorRow> page = rowsBuffer.streamPage(firstRow, pageLength, HUEditorRowFilter.ALL, orderBys).collect(GuavaCollectors.toImmutableList());
    return ViewResult.ofViewAndPage(this, firstRow, pageLength, orderBys.toDocumentQueryOrderByList(), page);
}


@Override
public String getTableNameOrNull(DocumentId documentId){
    // commented out for now, see javadoc
    // if (documentId == null)
    // {
    // return null;
    // }
    // final HUEditorRow huEditorRow = getById(documentId);
    // final HUEditorRowType type = huEditorRow.getType();
    // if (type == HUEditorRowType.HUStorage)
    // {
    // return I_M_HU_Storage.Table_Name;
    // }
    return I_M_HU.Table_Name;
}


public String getReferencingTableName(){
    return referencingTableName;
}


@Override
public DocumentId getParentRowId(){
    return parentRowId;
}


@Override
public ViewActionDescriptorsList getActions(){
    return actions;
}


@Override
public ITranslatableString getDescription(){
    return TranslatableStrings.empty();
}


@Override
public JSONViewDataType getViewType(){
    return viewType;
}


@Override
public Set<DocumentPath> getReferencingDocumentPaths(){
    return referencingDocumentPaths;
}


@Override
public DocumentFilterList getFilters(){
    return filters;
}


@Override
public DocumentFilterList getStickyFilters(){
    return rowsBuffer.getStickyFilters();
}


@Override
public String getSqlWhereClause(DocumentIdsSelection rowIds,SqlOptions sqlOpts_NOTUSED){
    return rowsBuffer.getSqlWhereClause(rowIds);
}


@Override
public LookupValuesList getFilterParameterTypeahead(String filterId,String filterParameterName,String query,Evaluatee ctx){
    return filterDescriptors.getByFilterId(filterId).getParameterByName(filterParameterName).getLookupDataSource().get().findEntities(ctx, query);
}


@Override
public int getQueryLimit(){
    return -1;
}


@Override
public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors(){
    return additionalRelatedProcessDescriptors;
}


@Override
public TableRecordReference getTableRecordReferenceOrNull(DocumentId rowId){
    if (rowId == null) {
        return null;
    }
    final HUEditorRowId huRowId = HUEditorRowId.ofDocumentId(rowId);
    if (huRowId.isHU()) {
        return TableRecordReference.of(I_M_HU.Table_Name, huRowId.getHuId());
    } else {
        return null;
    }
}


public HUEditorRow getParentRowByChildIdOrNull(DocumentId childId){
    return rowsBuffer.getParentRowByChildIdOrNull(childId).orElse(null);
}


public boolean getParameterAsBoolean(String name,boolean defaultValue){
    final Boolean value = (Boolean) parameters.get(name);
    return value != null ? value.booleanValue() : defaultValue;
}


@Override
public DocumentQueryOrderByList getDefaultOrderBys(){
    return DocumentQueryOrderByList.EMPTY;
}


@Override
public ViewId getParentViewId(){
    return parentViewId;
}


@Override
public HUEditorRow getById(DocumentId rowId){
    return rowsBuffer.getById(rowId);
}


public ImmutableMap<String,Object> getParameters(){
    return parameters;
}


public Optional<T> getParameterAsId(String name){
    @SuppressWarnings("unchecked")
    final T value = (T) parameters.get(name);
    return Optional.ofNullable(value);
}


@Override
public ViewId getViewId(){
    return viewId;
}


public Stream<HUEditorRow> streamAllRecursive(HUEditorRowFilter filter){
    return rowsBuffer.streamAllRecursive(filter);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/streamAllRecursive"))

.queryParam("filter",filter);
Stream<HUEditorRow> aux = restTemplate.getForObject(builder.toUriString(),Stream<HUEditorRow>.class);
return aux;
}


public boolean matchesAnyRowRecursive(HUEditorRowFilter filter){
    return rowsBuffer.matchesAnyRowRecursive(filter);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/matchesAnyRowRecursive"))

.queryParam("filter",filter);
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}