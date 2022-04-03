package de.metas.ui.web.view;
 import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import javax.annotation.Nullable;
import org.adempiere.util.lang.SynchronizedMutable;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.Builder;
import lombok.NonNull;
public class ViewRowIdsOrderedSelectionsHolder {

 private  IViewDataRepository viewDataRepository;

 private  ViewId viewId;

 private  boolean applySecurityRestrictions;

 private  Supplier<ViewEvaluationCtx> viewEvaluationCtxSupplier;

 private  DocumentFilterList filtersExcludingFacets;

 private  DocumentFilterList facetFilters;

 private  AtomicBoolean selectionDeleteBeforeCreate;

 private  SynchronizedMutable<ViewRowIdsOrderedSelections> currentSelectionsRef;


public int getQueryLimit(){
    return getDefaultSelection().getQueryLimit();
}


public boolean isQueryLimitHit(){
    return getDefaultSelection().isQueryLimitHit();
}


public ViewRowIdsOrderedSelection getDefaultSelection(){
    return getCurrentSelections().getDefaultSelection();
}


public void forgetCurrentSelections(){
    selectionDeleteBeforeCreate.set(true);
    final ViewRowIdsOrderedSelections selections = currentSelectionsRef.setValueAndReturnPrevious(null);
    if (selections != null) {
        final ImmutableSet<String> selectionIds = selections.getSelectionIds();
        viewDataRepository.scheduleDeleteSelections(selectionIds);
    }
}


public ViewRowIdsOrderedSelection getOrderedSelection(DocumentQueryOrderByList orderBys){
    return computeCurrentSelections(selections -> computeOrderBySelectionIfAbsent(selections, orderBys)).getSelection(orderBys);
}


public ViewRowIdsOrderedSelections createViewRowIdsOrderedSelections(){
    if (selectionDeleteBeforeCreate.get()) {
        viewDataRepository.deleteSelection(viewId.getViewId());
    }
    final ViewEvaluationCtx viewEvalCtx = getViewEvaluationCtx();
    final ViewRowIdsOrderedSelection selectionBeforeFacetsFiltering = viewDataRepository.createOrderedSelection(viewEvalCtx, viewId, filtersExcludingFacets, applySecurityRestrictions, SqlDocumentFilterConverterContext.EMPTY);
    final ViewRowIdsOrderedSelection selection;
    if (!facetFilters.isEmpty()) {
        selection = viewDataRepository.createOrderedSelectionFromSelection(viewEvalCtx, selectionBeforeFacetsFiltering, facetFilters, /* orderBys */
        DocumentQueryOrderByList.EMPTY, SqlDocumentFilterConverterContext.EMPTY);
    } else {
        selection = selectionBeforeFacetsFiltering;
    }
    return ViewRowIdsOrderedSelections.ofDefaultSelection(selectionBeforeFacetsFiltering, selection);
}


public ViewEvaluationCtx getViewEvaluationCtx(){
    return viewEvaluationCtxSupplier.get();
}


public ViewRowIdsOrderedSelections computeCurrentSelections(UnaryOperator<ViewRowIdsOrderedSelections> remappingFunction){
    return currentSelectionsRef.compute(previousSelections -> {
        final ViewRowIdsOrderedSelections selections = previousSelections != null ? previousSelections : createViewRowIdsOrderedSelections();
        return remappingFunction.apply(selections);
    });
}


public ViewRowIdsOrderedSelection createSelectionFromSelection(ViewRowIdsOrderedSelection fromSelection,DocumentQueryOrderByList orderBys){
    return viewDataRepository.createOrderedSelectionFromSelection(getViewEvaluationCtx(), fromSelection, DocumentFilterList.EMPTY, orderBys, SqlDocumentFilterConverterContext.EMPTY);
}


public long getSize(){
    return getDefaultSelection().getSize();
}


public DocumentQueryOrderByList getDefaultOrderBys(){
    return getDefaultSelection().getOrderBys();
}


public ViewRowIdsOrderedSelections getCurrentSelections(){
    return currentSelectionsRef.computeIfNull(this::createViewRowIdsOrderedSelections);
}


public ViewRowIdsOrderedSelection getDefaultSelectionBeforeFacetsFiltering(){
    return getCurrentSelections().getDefaultSelectionBeforeFacetsFiltering();
}


public ViewRowIdsOrderedSelections computeCurrentSelectionsIfPresent(UnaryOperator<ViewRowIdsOrderedSelections> remappingFunction){
    return currentSelectionsRef.computeIfNotNull(remappingFunction);
}


public ViewRowIdsOrderedSelections removeRowIdsNotMatchingFilters(ViewRowIdsOrderedSelections selections,Set<DocumentId> rowIds){
    final ViewRowIdsOrderedSelection defaultSelectionBeforeFacetsFiltering = viewDataRepository.removeRowIdsNotMatchingFilters(selections.getDefaultSelectionBeforeFacetsFiltering(), filtersExcludingFacets, rowIds);
    final ViewRowIdsOrderedSelection defaultSelection;
    if (!facetFilters.isEmpty()) {
        defaultSelection = viewDataRepository.removeRowIdsNotMatchingFilters(selections.getDefaultSelection(), facetFilters, rowIds);
    } else {
        defaultSelection = defaultSelectionBeforeFacetsFiltering;
    }
    return selections.withDefaultSelection(defaultSelectionBeforeFacetsFiltering, defaultSelection);
}


public ViewRowIdsOrderedSelections computeOrderBySelectionIfAbsent(ViewRowIdsOrderedSelections selections,DocumentQueryOrderByList orderBys){
    return selections.withOrderBysSelectionIfAbsent(orderBys, this::createSelectionFromSelection);
}


}