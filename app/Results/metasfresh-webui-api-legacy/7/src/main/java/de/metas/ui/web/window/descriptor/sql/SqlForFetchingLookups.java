package de.metas.ui.web.window.descriptor.sql;
 import java.util.Set;
import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.IStringExpression;
import org.compiere.util.CtxName;
import org.compiere.util.CtxNames;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
@EqualsAndHashCode
@ToString
public class SqlForFetchingLookups {

 public  CtxName PARAM_Offset;

 public  CtxName PARAM_Limit;

 private  IStringExpression sql;


public IStringExpression toStringExpression(){
    return sql;
}


public Object toOneLineString(){
    return sql.toOneLineString();
}


public Set<CtxName> getParameters(){
    return sql.getParameters();
}


public String evaluate(LookupDataSourceContext evalCtx){
    return sql.evaluate(evalCtx, OnVariableNotFound.Fail);
}


}