package de.metas.ui.web.view;
 import java.util.List;
import java.util.Map;
import java.util.Set;
import org.adempiere.exceptions.DBException;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.sql.SqlOptions;
public interface IViewDataRepository {


public String getSqlWhereClause(ViewId viewId,DocumentFilterList filters,DocumentIdsSelection rowIds,SqlOptions sqlOpts)
;

public void scheduleDeleteSelections(Set<String> selectionIds)
;

public ViewRowIdsOrderedSelection createOrderedSelection(ViewEvaluationCtx viewEvalCtx,ViewId viewId,DocumentFilterList filters,boolean applySecurityRestrictions,SqlDocumentFilterConverterContext context)
;

public List<DocumentId> retrieveRowIdsByPage(ViewEvaluationCtx viewEvalCtx,ViewRowIdsOrderedSelection orderedSelection,int firstRow,int pageLength)
;

public String getTableName()
;

public DocumentFilterDescriptorsProvider getViewFilterDescriptors()
;

public Map<String,DocumentFieldWidgetType> getWidgetTypesByFieldName()
;

public List<IViewRow> retrievePage(ViewEvaluationCtx viewEvalCtx,ViewRowIdsOrderedSelection orderedSelection,int firstRow,int pageLength)
;

public List<Object> retrieveFieldValues(ViewEvaluationCtx viewEvalCtx,String selectionId,String fieldName,int limit)
;

public ViewRowIdsOrderedSelection createOrderedSelectionFromSelection(ViewEvaluationCtx viewEvalCtx,ViewRowIdsOrderedSelection fromSelection,DocumentFilterList filters,DocumentQueryOrderByList orderBys,SqlDocumentFilterConverterContext filterConverterCtx)
;

public void deleteSelection(String selectionId)
;

public ViewRowIdsOrderedSelection removeRowIdsNotMatchingFilters(ViewRowIdsOrderedSelection selection,DocumentFilterList filters,Set<DocumentId> rowIds)
;

public List<T> retrieveModelsByIds(ViewId viewId,DocumentIdsSelection rowIds,Class<T> modelClass)
;

public IViewRow retrieveById(ViewEvaluationCtx viewEvalCtx,ViewId viewId,DocumentId rowId)
;

}