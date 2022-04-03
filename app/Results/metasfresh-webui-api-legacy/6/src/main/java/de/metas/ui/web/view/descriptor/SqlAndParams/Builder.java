package de.metas.ui.web.view.descriptor.SqlAndParams;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.util.Check;
import lombok.NonNull;
import lombok.Value;
public class Builder {

 private  StringBuilder sql;

 private  ArrayList<Object> sqlParams;


public int getParametersCount(){
    return sqlParams != null ? sqlParams.size() : 0;
}


public boolean hasParameters(){
    return sqlParams != null && !sqlParams.isEmpty();
}


public SqlAndParams build(){
    final String sql = this.sql != null ? this.sql.toString() : "";
    final Object[] sqlParamsArray = sqlParams != null ? sqlParams.toArray() : null;
    return new SqlAndParams(sql, sqlParamsArray);
}


public boolean isEmpty(){
    return (sql == null || sql.length() == 0) && !hasParameters();
}


@Deprecated
public String toString(){
    return MoreObjects.toStringHelper(this).add("sql", sql).add("sqlParams", sqlParams).toString();
}


public Builder append(SqlAndParams other){
    return append(other.sql, other.sqlParams);
}


public Builder appendIfHasParameters(CharSequence sql){
    if (hasParameters()) {
        return append(sql);
    } else {
        return this;
    }
}


}