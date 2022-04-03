package de.metas.ui.web.window.descriptor.sql;
 import java.util.Objects;
import javax.annotation.Nullable;
import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.api.impl.ConstantStringExpression;
import org.compiere.util.Evaluatee;
import de.metas.printing.esb.base.util.Check;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@EqualsAndHashCode
@ToString
public class SqlSelectDisplayValue {

 private  String joinOnTableNameOrAlias;

 private  String joinOnColumnName;

 private  SqlForFetchingLookupById sqlExpression;

@Getter
 private  String columnNameAlias;


public IStringExpression toStringExpressionWithColumnNameAlias(){
    return IStringExpression.composer().append("(").append(toStringExpression()).append(") AS ").append(columnNameAlias).build();
}


public IStringExpression toStringExpression(){
    final String joinOnColumnNameFQ = !Check.isEmpty(joinOnTableNameOrAlias) ? joinOnTableNameOrAlias + "." + joinOnColumnName : joinOnColumnName;
    if (sqlExpression == null) {
        return ConstantStringExpression.of(joinOnColumnNameFQ);
    } else {
        return sqlExpression.toStringExpression(joinOnColumnNameFQ);
    }
}


public SqlSelectDisplayValue withJoinOnTableNameOrAlias(String joinOnTableNameOrAlias){
    return !Objects.equals(this.joinOnTableNameOrAlias, joinOnTableNameOrAlias) ? toBuilder().joinOnTableNameOrAlias(joinOnTableNameOrAlias).build() : this;
}


public String toSqlStringWithColumnNameAlias(Evaluatee ctx){
    return toStringExpressionWithColumnNameAlias().evaluate(ctx, OnVariableNotFound.Fail);
}


}