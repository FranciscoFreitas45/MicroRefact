package DTO;
 import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.util.Evaluatee;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.process.view.ViewActionDescriptorsList;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.sql.SqlOptions;
import lombok.NonNull;
public interface IView {


public LookupValuesList getFilterParameterDropdown(String filterId,String filterParameterName,Evaluatee ctx)
;

public boolean isAllowClosingPerUserRequest(){
    return true;
}
;

public ViewResult getPage(int firstRow,int pageLength,ViewRowsOrderBy orderBy)
;

public String getTableNameOrNull(){
    return getTableNameOrNull(null);
}
;

public DocumentId getParentRowId()
;

public ViewActionDescriptorsList getActions(){
    return ViewActionDescriptorsList.EMPTY;
}
;

public ITranslatableString getDescription(){
    return TranslatableStrings.empty();
}
;

public JSONViewDataType getViewType()
;

public Set<DocumentPath> getReferencingDocumentPaths()
;

public DocumentFilterList getFilters()
;

public DocumentFilterList getStickyFilters()
;

public boolean hasAttributesSupport(){
    return false;
}
;

public String getSqlWhereClause(DocumentIdsSelection rowIds,SqlOptions sqlOpts)
;

public LookupValuesList getFilterParameterTypeahead(String filterId,String filterParameterName,String query,Evaluatee ctx)
;

public int getQueryLimit()
;

public ViewResult getPageWithRowIdsOnly(int firstRow,int pageLength,ViewRowsOrderBy orderBy){
    return getPage(firstRow, pageLength, orderBy);
}
;

public ViewHeaderProperties getHeaderProperties(){
    return ViewHeaderProperties.EMPTY;
}
;

public TableRecordReference getTableRecordReferenceOrNull(DocumentId rowId){
    final int recordId = rowId.toIntOr(-1);
    if (recordId < 0) {
        return null;
    }
    final String tableName = getTableNameOrNull(rowId);
    if (tableName == null) {
        return null;
    }
    return TableRecordReference.of(tableName, recordId);
}
;

public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors(){
    return ImmutableList.of();
}
;

public ViewProfileId getProfileId(){
    return ViewProfileId.NULL;
}
;

public DocumentQueryOrderByList getDefaultOrderBys()
;

public ViewId getParentViewId()
;

public void invalidateAll()
;

public IViewRow getById(DocumentId rowId)
;

public List<T> retrieveModelsByIds(DocumentIdsSelection rowIds,Class<T> modelClass)
;

public ViewId getViewId()
;

}