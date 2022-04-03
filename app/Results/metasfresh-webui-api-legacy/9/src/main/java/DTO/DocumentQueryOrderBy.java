package DTO;
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
public class DocumentQueryOrderBy {

 private  String fieldName;

 private  boolean ascending;

 private  boolean nullsLast;

 public  ValueComparator ASCENDING_NULLS_FIRST;

 public  ValueComparator ASCENDING_NULLS_LAST;

 public  ValueComparator DESCENDING_NULLS_FIRST;

 public  ValueComparator DESCENDING_NULLS_LAST;

 private  boolean ascending;

 private  boolean nullsLast;


public boolean getDefaultNullsLastByAscending(boolean ascending){
    // always nulls last
    return true;
}


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


public DocumentQueryOrderBy byFieldName(String fieldName,boolean ascending){
    final boolean nullsLast = getDefaultNullsLastByAscending(ascending);
    return new DocumentQueryOrderBy(fieldName, ascending, nullsLast);
}


public DocumentQueryOrderBy copyOverridingFieldName(String fieldName){
    if (Objects.equals(this.fieldName, fieldName)) {
        return this;
    }
    return new DocumentQueryOrderBy(fieldName, ascending, nullsLast);
}


public Object getFieldValue(T object,String fieldName,JSONOptions jsonOpts)


public DocumentQueryOrderBy parse(String orderByStr){
    if (orderByStr.charAt(0) == '+') {
        final String fieldName = orderByStr.substring(1);
        final boolean ascending = true;
        final boolean nullsLast = getDefaultNullsLastByAscending(ascending);
        return new DocumentQueryOrderBy(fieldName, ascending, nullsLast);
    } else if (orderByStr.charAt(0) == '-') {
        final String fieldName = orderByStr.substring(1);
        final boolean ascending = false;
        final boolean nullsLast = getDefaultNullsLastByAscending(ascending);
        return new DocumentQueryOrderBy(fieldName, ascending, nullsLast);
    } else {
        final String fieldName = orderByStr;
        final boolean ascending = true;
        final boolean nullsLast = getDefaultNullsLastByAscending(ascending);
        return new DocumentQueryOrderBy(fieldName, ascending, nullsLast);
    }
}


public Comparator<T> asComparator(FieldValueExtractor<T> fieldValueExtractor,JSONOptions jsonOpts){
    final Function<T, Object> keyExtractor = obj -> fieldValueExtractor.getFieldValue(obj, fieldName, jsonOpts);
    Comparator<? super Object> keyComparator = ValueComparator.ofAscendingAndNullsLast(ascending, nullsLast);
    return Comparator.comparing(keyExtractor, keyComparator);
}


}