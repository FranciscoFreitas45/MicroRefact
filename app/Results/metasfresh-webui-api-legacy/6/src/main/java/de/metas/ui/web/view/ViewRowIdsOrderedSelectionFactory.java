package de.metas.ui.web.view;
 import java.util.Set;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.NonNull;
public interface ViewRowIdsOrderedSelectionFactory {


public ViewRowIdsOrderedSelection createOrderedSelection(ViewEvaluationCtx viewEvalCtx,ViewId viewId,DocumentFilterList filters,DocumentQueryOrderByList orderBys,boolean applySecurityRestrictions,SqlDocumentFilterConverterContext context)
;

public String getSqlWhereClause(ViewId viewId,DocumentIdsSelection rowIds)
;

public void scheduleDeleteSelections(Set<String> selectionIds)
;

public boolean containsAnyOfRowIds(ViewRowIdsOrderedSelection selection,DocumentIdsSelection rowIds)
;

public ViewRowIdsOrderedSelection removeRowIdsFromSelection(ViewRowIdsOrderedSelection selection,DocumentIdsSelection rowIds)
;

public ViewRowIdsOrderedSelection createOrderedSelectionFromSelection(ViewEvaluationCtx viewEvalCtx,ViewRowIdsOrderedSelection fromSelection,DocumentFilterList filters,DocumentQueryOrderByList orderBys,SqlDocumentFilterConverterContext filterConverterCtx)
;

public ViewRowIdsOrderedSelection addRowIdsToSelection(ViewRowIdsOrderedSelection selection,DocumentIdsSelection rowIds)
;

public void deleteSelection(String selectionId){
    deleteSelections(ImmutableSet.of(selectionId));
}
;

public void deleteSelections(Set<String> selectionIds)
;

}