package DTO;
 import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.util.GuavaCollectors;
public class JSONViewOrderBy {

 private  String fieldName;

 private  boolean ascending;


public JSONViewOrderBy of(DocumentQueryOrderBy orderBy){
    return new JSONViewOrderBy(orderBy.getFieldName(), orderBy.isAscending());
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("fieldName", fieldName).add("asc", ascending).toString();
}


public String getFieldName(){
    return fieldName;
}


public boolean isAscending(){
    return ascending;
}


public List<JSONViewOrderBy> ofList(DocumentQueryOrderByList orderBys){
    if (orderBys == null || orderBys.isEmpty()) {
        return ImmutableList.of();
    }
    return orderBys.stream().map(orderBy -> of(orderBy)).filter(jsonOrderBy -> jsonOrderBy != null).collect(GuavaCollectors.toImmutableList());
}


}