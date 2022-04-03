package de.metas.ui.web.window.model.sql;
 import java.util.Optional;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.api.impl.CompositeStringExpression;
import de.metas.ui.web.window.descriptor.sql.SqlEntityBinding;
import de.metas.ui.web.window.descriptor.sql.SqlOrderByValue;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.NonNull;
public class SqlDocumentOrderByBuilder {

 private  SqlOrderByBindings bindings;

 private  String joinOnTableNameOrAlias;

 private  boolean useColumnNameAlias;


public SqlDocumentOrderByBuilder useColumnNameAlias(boolean useColumnNameAlias){
    this.useColumnNameAlias = useColumnNameAlias;
    return this;
}


public SqlDocumentOrderByBuilder newInstance(SqlOrderByBindings bindings){
    return new SqlDocumentOrderByBuilder(bindings);
}


public SqlOrderByValue getFieldOrderBy(String fieldName)


public SqlDocumentOrderByBuilder joinOnTableNameOrAlias(String joinOnTableNameOrAlias){
    this.joinOnTableNameOrAlias = joinOnTableNameOrAlias;
    return this;
}


public IStringExpression buildSqlOrderBy(SqlOrderByValue orderBy,boolean ascending,boolean nullsLast){
    if (orderBy.isNullExpression()) {
        return IStringExpression.NULL;
    }
    final CompositeStringExpression.Builder sql = IStringExpression.composer();
    if (useColumnNameAlias) {
        sql.append(orderBy.withJoinOnTableNameOrAlias(joinOnTableNameOrAlias).toSqlStringUsingColumnAlias());
    } else {
        sql.append("(").append(orderBy.withJoinOnTableNameOrAlias(joinOnTableNameOrAlias).toStringExpression()).append(")");
    }
    return sql.append(ascending ? " ASC" : " DESC").append(nullsLast ? " NULLS LAST" : " NULLS FIRST").build();
}


}