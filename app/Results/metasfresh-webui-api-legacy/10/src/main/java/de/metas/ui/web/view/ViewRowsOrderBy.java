package de.metas.ui.web.view;
 import java.util.Comparator;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
@EqualsAndHashCode
@ToString
public class ViewRowsOrderBy {

 private  DocumentQueryOrderByList orderBys;

 private  JSONOptions jsonOpts;


public Comparator<T> toComparator(){
    return orderBys.toComparator(IViewRow::getFieldValueAsJsonObject, jsonOpts);
}


public ViewRowsOrderBy withOrderBys(DocumentQueryOrderByList orderBys){
    if (DocumentQueryOrderByList.equals(this.orderBys, orderBys)) {
        return this;
    } else {
        return new ViewRowsOrderBy(orderBys, jsonOpts);
    }
}


public ViewRowsOrderBy parseString(String orderBysListStr,JSONOptions jsonOpts){
    final DocumentQueryOrderByList orderBys = DocumentQueryOrderByList.parse(orderBysListStr);
    return of(orderBys, jsonOpts);
}


public ViewRowsOrderBy of(DocumentQueryOrderByList orderBys,JSONOptions jsonOpts){
    return new ViewRowsOrderBy(orderBys, jsonOpts);
}


public boolean isEmpty(){
    return orderBys.isEmpty();
}


public DocumentQueryOrderByList toDocumentQueryOrderByList(){
    return orderBys;
}


public ViewRowsOrderBy empty(JSONOptions jsonOpts){
    return new ViewRowsOrderBy(DocumentQueryOrderByList.EMPTY, jsonOpts);
}


public Comparator<T> toComparatorOrNull(){
    return !orderBys.isEmpty() ? toComparator() : null;
}


}