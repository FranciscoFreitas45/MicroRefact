package de.metas.ui.web.handlingunits;
 import java.util.List;
import java.util.Set;
import de.metas.handlingunits.HuId;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.handlingunits.HUIdsFilterHelper.HUIdsFilterData;
import de.metas.ui.web.view.ViewEvaluationCtx;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewRowIdsOrderedSelection;
import de.metas.ui.web.view.descriptor.SqlViewRowIdsConverter;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.util.collections.PagedIterator.Page;
public interface HUEditorViewRepository {


public List<HUEditorRow> retrieveHUEditorRows(Set<HuId> huIds,HUEditorRowFilter filter)
;

public boolean containsAnyOfRowIds(ViewRowIdsOrderedSelection selection,DocumentIdsSelection rowIds)
;

public String buildSqlWhereClause(ViewRowIdsOrderedSelection selection,DocumentIdsSelection rowIds)
;

public HUEditorRow retrieveForHUId(HuId huId)
;

public Set<HuId> retrieveHUIdsEffective(HUIdsFilterData huIdsFilter,DocumentFilterList filters,SqlDocumentFilterConverterContext context)
;

public ViewRowIdsOrderedSelection createSelectionFromSelection(ViewEvaluationCtx viewEvalCtx,ViewRowIdsOrderedSelection fromSelection,DocumentQueryOrderByList orderBys)
;

public SqlViewRowIdsConverter getRowIdsConverter()
;

public ViewRowIdsOrderedSelection createSelection(ViewEvaluationCtx viewEvalCtx,ViewId viewId,DocumentFilterList filters,DocumentQueryOrderByList orderBys,SqlDocumentFilterConverterContext filterConverterCtx)
;

public void invalidateCache()
;

public ViewRowIdsOrderedSelection removeRowIdsFromSelection(ViewRowIdsOrderedSelection selection,DocumentIdsSelection rowIdsToRemove)
;

public void deleteSelection(ViewRowIdsOrderedSelection selection)
;

public ViewRowIdsOrderedSelection addRowIdsToSelection(ViewRowIdsOrderedSelection selection,DocumentIdsSelection rowIdsToAdd)
;

public Page<HuId> retrieveHUIdsPage(ViewEvaluationCtx viewEvalCtx,ViewRowIdsOrderedSelection selection,int firstRow,int maxRows)
;

public void warmUp(Set<HuId> huIds)
;

}