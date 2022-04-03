package de.metas.ui.web.document.filter.sql;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import org.adempiere.ad.dao.ISqlQueryFilter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import com.google.common.base.MoreObjects;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
@EqualsAndHashCode
public class SqlParamsCollector {

 private  SqlParamsCollector NOT_COLLECTING;

 private  List<Object> params;

 private  List<Object> paramsRO;


public boolean isCollecting(){
    return params != null;
}


public void collectAll(Collection<? extends Object> sqlParams){
    if (sqlParams == null || sqlParams.isEmpty()) {
        return;
    }
    if (params == null) {
        throw new IllegalStateException("Cannot append " + sqlParams + " to not collecting params");
    }
    params.addAll(sqlParams);
}


public SqlParamsCollector wrapNullable(List<Object> list){
    if (list == null) {
        return NOT_COLLECTING;
    }
    return new SqlParamsCollector(list);
}


public SqlParamsCollector notCollecting(){
    return NOT_COLLECTING;
}


public SqlParamsCollector newInstance(){
    return new SqlParamsCollector(new ArrayList<>());
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(paramsRO).toString();
}


public String placeholder(Object sqlValue){
    if (params == null) {
        return DB.TO_SQL(sqlValue);
    } else {
        params.add(sqlValue);
        return "?";
    }
}


public List<Object> toList(){
    return paramsRO;
}


public void collect(SqlParamsCollector from){
    collectAll(from.params);
}


public List<Object> toLiveList(){
    return params;
}


}