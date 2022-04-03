package de.metas.ui.web.handlingunits;
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
@UtilityClass
public class HUIdsFilterHelper {

 private  ImmutableSet<HuId> initialHUIds;

 private  IHUQueryBuilder initialHUQuery;

 private  HashSet<HuId> mustHUIds;

 private  HashSet<HuId> shallNotHUIds;

 private  String FILTER_ID;

 private  String FILTER_PARAM_Data;

 public  HUIdsSqlDocumentFilterConverter SQL_DOCUMENT_FILTER_CONVERTER;

@VisibleForTesting
 static  String SQL_TRUE;


public HUIdsFilterData extractFilterData(DocumentFilter huIdsFilter){
    Preconditions.checkArgument(!isNotHUIdsFilter(huIdsFilter), "Not a HUIds filter: %s", huIdsFilter);
    return (HUIdsFilterData) huIdsFilter.getParameter(FILTER_PARAM_Data).getValue();
}


@Override
public boolean canConvert(String filterId){
    return Objects.equals(filterId, FILTER_ID);
}


public Set<HuId> getShallNotHUIds(){
    return ImmutableSet.copyOf(shallNotHUIds);
}


public DocumentFilter createFilter(HUIdsFilterData filterData){
    return DocumentFilter.singleParameterFilter(FILTER_ID, FILTER_PARAM_Data, Operator.EQUAL, filterData);
}


public ImmutableSet<HuId> getInitialHUIds(){
    return initialHUIds;
}


public HUIdsFilterData newEmpty(){
    final Collection<HuId> huIds = null;
    final IHUQueryBuilder initialHUQuery = null;
    return new HUIdsFilterData(huIds, initialHUQuery);
}


public boolean mustHUIds(Collection<HuId> mustHUIdsToAdd){
    if (mustHUIdsToAdd.isEmpty()) {
        return false;
    }
    final boolean added = mustHUIds.addAll(mustHUIdsToAdd);
    shallNotHUIds.removeAll(mustHUIdsToAdd);
    return added;
}


public boolean shallNotHUIds(Collection<HuId> shallNotHUIdsToAdd){
    if (shallNotHUIdsToAdd.isEmpty()) {
        return false;
    }
    final boolean added = shallNotHUIds.addAll(shallNotHUIdsToAdd);
    mustHUIds.removeAll(shallNotHUIdsToAdd);
    return added;
}


public HUIdsFilterData extractFilterDataOrNull(DocumentFilterList filters){
    final DocumentFilter huIdsFilter = findExistingOrNull(filters);
    if (huIdsFilter == null) {
        return null;
    }
    return HUIdsFilterHelper.extractFilterData(huIdsFilter);
}


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


public HUIdsFilterData ofHUIds(Collection<HuId> huIds){
    final IHUQueryBuilder initialFilter = null;
    return new HUIdsFilterData(huIds, initialFilter);
}


public boolean isNotHUIdsFilter(DocumentFilter filter){
    return !FILTER_ID.equals(filter.getFilterId());
}


public boolean hasInitialHUQuery(){
    return initialHUQuery != null;
}


public HUIdsFilterData ofHUQuery(IHUQueryBuilder initialHUQuery){
    final Collection<HuId> huIds = null;
    return new HUIdsFilterData(huIds, initialHUQuery);
}


public IHUQueryBuilder getInitialHUQueryOrNull(){
    return initialHUQuery != null ? initialHUQuery.copy() : null;
}


public HUIdsFilterData copy(){
    return new HUIdsFilterData(this);
}


public Set<HuId> getMustHUIds(){
    return ImmutableSet.copyOf(mustHUIds);
}


public DocumentFilter findExistingOrNull(DocumentFilterList filters){
    if (filters == null || filters.isEmpty()) {
        return null;
    }
    return filters.getFilterById(FILTER_ID).orElse(null);
}


}