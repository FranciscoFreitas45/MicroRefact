package de.metas.ui.web.view.descriptor;
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
@Value
public class SqlAndParams {

 private  String sql;

 private  List<Object> sqlParams;

 private  StringBuilder sql;

 private  ArrayList<Object> sqlParams;


public Builder toBuilder(){
    return builder().append(this);
}


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


public SqlAndParams and(Collection<SqlAndParams> sqlAndParamsCollection){
    Check.assumeNotEmpty(sqlAndParamsCollection, "sqlAndParamsCollection is not empty");
    if (sqlAndParamsCollection.size() == 1) {
        return sqlAndParamsCollection.iterator().next();
    } else {
        final Builder builder = builder();
        for (final SqlAndParams sqlAndParams : sqlAndParamsCollection) {
            if (!builder.isEmpty()) {
                builder.append(" AND ");
            }
            builder.append("(").append(sqlAndParams).append(")");
        }
        return builder.build();
    }
}


public SqlAndParams of(CharSequence sql,Object sqlParamsArray){
    return new SqlAndParams(sql, sqlParamsArray);
}


public Builder builder(){
    return new Builder();
}


public boolean isEmpty(){
    return (sql == null || sql.length() == 0) && !hasParameters();
}


@Deprecated
public String toString(){
    return MoreObjects.toStringHelper(this).add("sql", sql).add("sqlParams", sqlParams).toString();
}


public Object[] getSqlParamsArray(){
    return sqlParams == null ? null : sqlParams.toArray();
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