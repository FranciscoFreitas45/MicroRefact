package de.metas.ui.web.window.model.DocumentQueryOrderBy;
 import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;
import de.metas.ui.web.window.datatypes.json.JSONNullValue;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;
@ToString
public class ValueComparator implements Comparator<Object>{

 public  ValueComparator ASCENDING_NULLS_FIRST;

 public  ValueComparator ASCENDING_NULLS_LAST;

 public  ValueComparator DESCENDING_NULLS_FIRST;

 public  ValueComparator DESCENDING_NULLS_LAST;

 private  boolean ascending;

 private  boolean nullsLast;


@Override
public int compare(Object o1,Object o2){
    if (o1 == o2) {
        return 0;
    } else if (o1 == null || o1 instanceof JSONNullValue) {
        return nullsLast ? +1 : -1;
    } else if (o2 == null || o2 instanceof JSONNullValue) {
        return nullsLast ? -1 : +1;
    } else if (o1 instanceof Comparable) {
        @SuppressWarnings("unchecked")
        final Comparable<Object> o1cmp = (Comparable<Object>) o1;
        return o1cmp.compareTo(o2) * (ascending ? +1 : -1);
    } else {
        return o1.toString().compareTo(o2.toString()) * (ascending ? +1 : -1);
    }
}


public ValueComparator ofAscendingAndNullsLast(boolean ascending,boolean nullsLast){
    if (ascending) {
        return nullsLast ? ASCENDING_NULLS_LAST : ASCENDING_NULLS_FIRST;
    } else {
        return nullsLast ? DESCENDING_NULLS_LAST : DESCENDING_NULLS_FIRST;
    }
}


}