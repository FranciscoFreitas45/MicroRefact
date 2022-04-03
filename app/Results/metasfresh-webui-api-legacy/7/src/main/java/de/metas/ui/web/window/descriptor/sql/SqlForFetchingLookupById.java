package de.metas.ui.web.window.descriptor.sql;
 import java.util.Set;
import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.IStringExpression;
import org.compiere.util.CtxName;
import org.compiere.util.CtxNames;
import org.compiere.util.Evaluatees;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
@EqualsAndHashCode
@ToString
public class SqlForFetchingLookupById {

 public  CtxName SQL_PARAM_KeyId;

 public  String SQL_PARAM_VALUE_ShowInactive_Yes;

 public  String SQL_PARAM_VALUE_ShowInactive_No;

 public  CtxName SQL_PARAM_ShowInactive;

 private  IStringExpression sql;


public boolean requiresParameter(String parameterName){
    return sql.requiresParameter(parameterName);
}


public IStringExpression toStringExpression(String joinOnColumnNameFQ){
    return sql.resolvePartial(Evaluatees.mapBuilder().put(SQL_PARAM_KeyId, joinOnColumnNameFQ).put(SQL_PARAM_ShowInactive, SQL_PARAM_VALUE_ShowInactive_Yes).build());
}


public Set<CtxName> getParameters(){
    return sql.getParameters();
}


public String evaluate(LookupDataSourceContext evalCtx){
    return sql.evaluate(evalCtx, OnVariableNotFound.Fail);
}


}