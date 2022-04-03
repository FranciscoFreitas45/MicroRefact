package de.metas.ui.web.handlingunits.HUIdsFilterHelper;
 import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import org.adempiere.ad.dao.ISqlQueryFilter;
import org.adempiere.model.PlainContextAware;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHUQueryBuilder;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.DocumentFilterParam.Operator;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.document.filter.sql.SqlParamsCollector;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.Services;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.UtilityClass;
public class HUIdsSqlDocumentFilterConverter implements SqlDocumentFilterConverter{

@VisibleForTesting
 static  String SQL_TRUE;


@Override
public String getSql(SqlParamsCollector sqlParamsOut,DocumentFilter filter,SqlOptions sqlOpts_NOTUSED,SqlDocumentFilterConverterContext context){
    final HUIdsFilterData huIdsFilter = extractFilterData(filter);
    final ImmutableList<HuId> onlyHUIds;
    final boolean mustHuIdsSpecified = huIdsFilter.getMustHUIds() != null && !huIdsFilter.getMustHUIds().isEmpty();
    final boolean initialHuIdsSpecified = huIdsFilter.getInitialHUIds() != null;
    if (initialHuIdsSpecified && mustHuIdsSpecified) {
        onlyHUIds = ImmutableList.copyOf(Iterables.concat(huIdsFilter.getInitialHUIds(), huIdsFilter.getMustHUIds()));
    } else if (initialHuIdsSpecified) {
        // huIdsFilter.getMustHUIds() == null
        onlyHUIds = ImmutableList.copyOf(huIdsFilter.getInitialHUIds());
    } else if (mustHuIdsSpecified) {
        onlyHUIds = ImmutableList.copyOf(huIdsFilter.getMustHUIds());
    } else {
        onlyHUIds = null;
    }
    if (onlyHUIds == null && !huIdsFilter.hasInitialHUQuery()) {
        // no restrictions were specified; pass through
        return SQL_TRUE;
    }
    // 
    // Create HU query
    IHUQueryBuilder huQuery = huIdsFilter.getInitialHUQueryOrNull();
    if (huQuery == null) {
        huQuery = Services.get(IHandlingUnitsDAO.class).createHUQueryBuilder();
    }
    huQuery.setContext(PlainContextAware.newOutOfTrx());
    // Only HUs
    if (onlyHUIds != null) {
        huQuery.addOnlyHUIds(HuId.toRepoIds(onlyHUIds));
    }
    // Exclude HUs
    huQuery.addHUIdsToExclude(HuId.toRepoIds(huIdsFilter.getShallNotHUIds()));
    final ISqlQueryFilter sqlQueryFilter = ISqlQueryFilter.cast(huQuery.createQueryFilter());
    final String sql = sqlQueryFilter.getSql();
    sqlParamsOut.collectAll(sqlQueryFilter);
    return sql;
}


@Override
public boolean canConvert(String filterId){
    return Objects.equals(filterId, FILTER_ID);
}


}