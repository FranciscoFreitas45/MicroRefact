package de.metas.ui.web.view;
 import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@EqualsAndHashCode
@ToString
public class ViewRowIdsOrderedSelections {

@Getter
 private  ViewRowIdsOrderedSelection defaultSelectionBeforeFacetsFiltering;

@Getter
 private  ViewRowIdsOrderedSelection defaultSelection;

 private  ImmutableMap<DocumentQueryOrderByList,ViewRowIdsOrderedSelection> selectionsByOrderBys;


public ViewRowIdsOrderedSelections ofDefaultSelection(ViewRowIdsOrderedSelection defaultSelectionBeforeFacetsFiltering,ViewRowIdsOrderedSelection defaultSelection){
    final ImmutableMap<DocumentQueryOrderByList, ViewRowIdsOrderedSelection> selectionsByOrderBys = ImmutableMap.of();
    return new ViewRowIdsOrderedSelections(defaultSelectionBeforeFacetsFiltering, defaultSelection, selectionsByOrderBys);
}


public ViewRowIdsOrderedSelection getSelection(DocumentQueryOrderByList orderBys){
    final ViewRowIdsOrderedSelection selection = getSelectionOrNull(orderBys);
    if (selection == null) {
        throw new AdempiereException("No selection found for " + orderBys + " in " + this);
    }
    return selection;
}


public ViewRowIdsOrderedSelection getSelectionOrNull(DocumentQueryOrderByList orderBys){
    if (orderBys == null || orderBys.isEmpty()) {
        return defaultSelection;
    }
    if (DocumentQueryOrderByList.equals(defaultSelection.getOrderBys(), orderBys)) {
        return defaultSelection;
    }
    return selectionsByOrderBys.get(orderBys);
}


public ViewRowIdsOrderedSelection create(ViewRowIdsOrderedSelection defaultSelection,DocumentQueryOrderByList orderBys)


public ViewRowIdsOrderedSelections withOrderBysSelectionIfAbsent(DocumentQueryOrderByList orderBys,ViewRowIdsOrderedSelectionFactory factory){
    final ViewRowIdsOrderedSelection selection = getSelectionOrNull(orderBys);
    if (selection != null) {
        return this;
    }
    final ImmutableMap<DocumentQueryOrderByList, ViewRowIdsOrderedSelection> selectionsByOrderBysNew = ImmutableMap.<DocumentQueryOrderByList, ViewRowIdsOrderedSelection>builder().putAll(selectionsByOrderBys).put(orderBys, factory.create(defaultSelection, orderBys)).build();
    return new ViewRowIdsOrderedSelections(defaultSelectionBeforeFacetsFiltering, defaultSelection, selectionsByOrderBysNew);
}


public ViewRowIdsOrderedSelections withDefaultSelection(ViewRowIdsOrderedSelection defaultSelectionBeforeFacetsFiltering,ViewRowIdsOrderedSelection defaultSelection){
    if (ViewRowIdsOrderedSelection.equals(this.defaultSelectionBeforeFacetsFiltering, defaultSelectionBeforeFacetsFiltering) && ViewRowIdsOrderedSelection.equals(this.defaultSelection, defaultSelection)) {
        return this;
    } else {
        return ofDefaultSelection(defaultSelectionBeforeFacetsFiltering, defaultSelection);
    }
}


public ImmutableSet<String> getSelectionIds(){
    final ImmutableSet.Builder<String> selectionIds = ImmutableSet.builder();
    selectionIds.add(defaultSelection.getSelectionId());
    for (final ViewRowIdsOrderedSelection selection : selectionsByOrderBys.values()) {
        selectionIds.add(selection.getSelectionId());
    }
    return selectionIds.build();
}


}