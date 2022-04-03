package de.metas.ui.web.window.descriptor.sql;
 import java.util.Set;
import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.exceptions.ExpressionEvaluationException;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.CtxName;
import org.compiere.util.CtxNames;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import com.google.common.collect.ImmutableSet;
import de.metas.document.sequence.IDocumentNoBuilderFactory;
import de.metas.ui.web.window.WindowConstants;
import de.metas.util.Services;
import lombok.NonNull;
public class AutoSequenceDefaultValueExpression implements IStringExpression{

 private  String PARAMETER_AD_Client_ID;

 private  String PARAMETER_AD_Org_ID;

 private  Set<CtxName> PARAMETERS;

 private  String tableName;


@Override
public String getExpressionString(){
    return "@Value@";
}


public AutoSequenceDefaultValueExpression of(String tableName){
    return new AutoSequenceDefaultValueExpression(tableName);
}


@Override
public Set<CtxName> getParameters(){
    return PARAMETERS;
}


@Override
public String evaluate(Evaluatee ctx,OnVariableNotFound onVariableNotFound){
    Integer adClientId = ctx.get_ValueAsInt(PARAMETER_AD_Client_ID, null);
    if (adClientId == null || adClientId < 0) {
        adClientId = Env.getAD_Client_ID(Env.getCtx());
    }
    Integer adOrgId = ctx.get_ValueAsInt(PARAMETER_AD_Org_ID, null);
    if (adOrgId == null || adOrgId < 0) {
        adOrgId = Env.getAD_Org_ID(Env.getCtx());
    }
    final IDocumentNoBuilderFactory documentNoFactory = Services.get(IDocumentNoBuilderFactory.class);
    final String value = documentNoFactory.forTableName(tableName, adClientId, adOrgId).setFailOnError(onVariableNotFound == OnVariableNotFound.Fail).setUsePreliminaryDocumentNo(true).build();
    if (value == null && onVariableNotFound == OnVariableNotFound.Fail) {
        throw new AdempiereException("No auto value sequence found for " + tableName + ", AD_Client_ID=" + adClientId + ", AD_Org_ID=" + adOrgId);
    }
    return value;
}


}