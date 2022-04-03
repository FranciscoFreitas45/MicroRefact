package de.metas.ui.web.document.filter.provider.userQuery;
 import java.util.ArrayList;
import java.util.UUID;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MQuery;
import com.google.common.collect.ImmutableMap;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterParam;
import de.metas.ui.web.document.filter.DocumentFilterParam.Operator;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
@UtilityClass
public class MQueryDocumentFilterHelper {

 private  ImmutableMap<MQuery.Operator,Operator> MQueryOperator2Operator;


public DocumentFilterParam createDocumentFilterParamFromMQueryRestriction(MQuery mquery,int restrictionIndex){
    try {
        if (mquery.isDirectWhereClause(restrictionIndex)) {
            final boolean joinAnd = mquery.isJoinAnd(restrictionIndex);
            final String sqlWhereClause = mquery.getDirectWhereClause(restrictionIndex);
            return DocumentFilterParam.ofSqlWhereClause(joinAnd, sqlWhereClause);
        }
        final boolean joinAnd = mquery.isJoinAnd(restrictionIndex);
        final String fieldName = mquery.getColumnName(restrictionIndex);
        final Operator operator = MQueryDocumentFilterHelper.fromMQueryOperator(mquery.getOperator(restrictionIndex));
        final Object value = mquery.getCode(restrictionIndex);
        final Object valueTo = mquery.getCodeTo(restrictionIndex);
        return DocumentFilterParam.builder().setJoinAnd(joinAnd).setFieldName(fieldName).setOperator(operator).setValue(value).setValueTo(valueTo).build();
    } catch (final Exception ex) {
        throw new AdempiereException("Failed converting MQuery's restriction to " + DocumentFilterParam.class + "\n MQuery: " + mquery + "\n Restriction index: " + // 
        restrictionIndex, ex);
    }
}


public DocumentFilter createDocumentFilterFromMQuery(MQuery mquery,ITranslatableString caption){
    final ArrayList<DocumentFilterParam> parameters = new ArrayList<>();
    for (int restrictionIdx = 0, restrictionsCount = mquery.getRestrictionCount(); restrictionIdx < restrictionsCount; restrictionIdx++) {
        final DocumentFilterParam param = createDocumentFilterParamFromMQueryRestriction(mquery, restrictionIdx);
        parameters.add(param);
    }
    final String filterId;
    if (parameters.size() == 1 && !parameters.get(0).isSqlFilter()) {
        filterId = parameters.get(0).getFieldName();
    } else {
        // FIXME: find a better filterId
        filterId = "MQuery-" + UUID.randomUUID();
    }
    return DocumentFilter.builder().setFilterId(filterId).setCaption(caption).setParameters(parameters).build();
}


public Operator fromMQueryOperator(MQuery.Operator mqueryOperator){
    final Operator operator = MQueryOperator2Operator.get(mqueryOperator);
    if (operator == null) {
        throw new AdempiereException("Unsupported MQuery operator: " + mqueryOperator);
    }
    return operator;
}


}