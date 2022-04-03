package de.metas.ui.web.window.descriptor.sql;
 import java.util.Objects;
import javax.annotation.Nullable;
import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.api.impl.ConstantStringExpression;
import org.compiere.util.Evaluatee;
import de.metas.util.Check;
import de.metas.util.StringUtils;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
@EqualsAndHashCode
@ToString
public class SqlOrderByValue {

 private  SqlSelectDisplayValue sqlSelectDisplayValue;

 private  SqlSelectValue sqlSelectValue;

 private  String columnName;

 private  String joinOnTableNameOrAlias;

 private  String columnNameFQ;

 private  String columnNameAliasFQ;


public IStringExpression toStringExpression(){
    if (sqlSelectDisplayValue != null) {
        return sqlSelectDisplayValue.toStringExpression();
    } else if (sqlSelectValue != null) {
        return ConstantStringExpression.of(sqlSelectValue.toSqlString());
    } else if (columnNameFQ != null) {
        return ConstantStringExpression.of(columnNameFQ);
    } else {
        return IStringExpression.NULL;
    }
}


public String toSqlStringUsingColumnAlias(){
    return columnNameAliasFQ;
}


public SqlOrderByValue withJoinOnTableNameOrAlias(String joinOnTableNameOrAlias){
    final String joinOnTableNameOrAliasEffective = StringUtils.trimBlankToNull(joinOnTableNameOrAlias);
    return !Objects.equals(this.joinOnTableNameOrAlias, joinOnTableNameOrAliasEffective) ? toBuilder().joinOnTableNameOrAlias(joinOnTableNameOrAliasEffective).build() : this;
}


public SqlOrderByValue ofColumnName(String joinOnTableNameOrAlias,String columnName){
    return SqlOrderByValue.builder().joinOnTableNameOrAlias(joinOnTableNameOrAlias).columnName(columnName).build();
}


public boolean isNullExpression(){
    return toStringExpression().isNullExpression();
}


public String toSqlString(Evaluatee ctx){
    return toStringExpression().evaluate(ctx, OnVariableNotFound.Fail);
}


public String computeColumnNameFQ(String tableName,String columnName){
    return tableName != null ? tableName + "." + columnName : columnName;
}


}