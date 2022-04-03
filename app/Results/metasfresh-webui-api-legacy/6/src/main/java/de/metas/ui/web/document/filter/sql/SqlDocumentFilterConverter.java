package de.metas.ui.web.document.filter.sql;
 import javax.annotation.Nullable;
import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.ad.dao.impl.TypedSqlQueryFilter;
import org.compiere.util.DB;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.window.model.sql.SqlOptions;
import lombok.NonNull;
public interface SqlDocumentFilterConverter {


public String getSql(SqlParamsCollector sqlParamsOut,DocumentFilterList filters,SqlOptions sqlOpts,SqlDocumentFilterConverterContext context){
    if (filters.isEmpty()) {
        return "";
    }
    final StringBuilder sqlWhereClauseBuilder = new StringBuilder();
    for (final DocumentFilter filter : filters.toList()) {
        final String sqlFilter = getSql(sqlParamsOut, filter, sqlOpts, context);
        if (Check.isEmpty(sqlFilter, true)) {
            continue;
        }
        if (sqlWhereClauseBuilder.length() > 0) {
            sqlWhereClauseBuilder.append("\n AND ");
        }
        sqlWhereClauseBuilder.append(DB.TO_COMMENT(filter.getFilterId())).append("(").append(sqlFilter).append(")");
    }
    return sqlWhereClauseBuilder.toString();
}
;

public IQueryFilter<T> createQueryFilter(DocumentFilterList filters,SqlOptions sqlOpts,SqlDocumentFilterConverterContext context){
    final SqlParamsCollector sqlFilterParams = SqlParamsCollector.newInstance();
    final String sqlFilter = getSql(sqlFilterParams, filters, sqlOpts, context);
    return TypedSqlQueryFilter.of(sqlFilter, sqlFilterParams.toList());
}
;

public boolean canConvert(String filterId)
;

}