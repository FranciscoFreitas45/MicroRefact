package de.metas.ui.web.handlingunits;
 import java.util.function.Predicate;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHUQueryBuilder;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.util.Services;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
@UtilityClass
public class HUEditorRowFilters {


public IHUQueryBuilder toHUQueryBuilderPart(HUEditorRowFilter filter){
    final IHUQueryBuilder huQueryBuilder = Services.get(IHandlingUnitsDAO.class).createHUQueryBuilder();
    // 
    // Filter by row type
    // IMPORTANT: don't filter out TUs/CUs because it might be that we are searching for included rows too
    // and in case we are filtering them out here the included TUs/CUs won't be found later...
    final Select rowType = filter.getSelect();
    if (rowType == Select.ALL) {
    // nothing
    } else if (rowType == Select.ONLY_TOPLEVEL) {
        huQueryBuilder.setOnlyTopLevelHUs(true);
    } else // else if (rowType == Select.LU)
    // else if (rowType == Select.TU)
    {
    // throw new AdempiereException("Not supported: " + rowType);
    }
    // Filter by string filter
    // final String stringFilter = filter.getUserInputFilter();
    // if (!Check.isEmpty(stringFilter, true))
    // {
    // throw new AdempiereException("String filter not supported: " + stringFilter);
    // }
    // Exclude M_HU_IDs
    huQueryBuilder.addHUIdsToExclude(HuId.toRepoIds(filter.getExcludeHUIds()));
    // Include HUStatuses
    huQueryBuilder.addHUStatusesToInclude(filter.getOnlyHUStatuses());
    // Exclude HUStatuses
    huQueryBuilder.addHUStatusesToExclude(filter.getExcludeHUStatuses());
    return huQueryBuilder;
}


public Predicate<HUEditorRow> toPredicate(HUEditorRowFilter filter){
    Predicate<HUEditorRow> predicate = Predicates.alwaysTrue();
    if (filter == HUEditorRowFilter.ALL) {
        return predicate;
    }
    // Filter by row type
    final Select rowType = filter.getSelect();
    if (rowType == Select.ALL) {
    // nothing
    } else if (rowType == Select.ONLY_TOPLEVEL) {
        predicate = predicate.and(HUEditorRow::isTopLevel);
    } else if (rowType == Select.LU) {
        predicate = predicate.and(HUEditorRow::isLU);
    } else if (rowType == Select.TU) {
        predicate = predicate.and(HUEditorRow::isTU);
    } else if (rowType == Select.CU) {
        predicate = predicate.and(HUEditorRow::isCU);
    } else {
        throw new AdempiereException("Unknown: " + rowType);
    }
    // Filter by string filter
    final String stringFilter = filter.getUserInputFilter();
    if (!Check.isEmpty(stringFilter, true)) {
        predicate = predicate.and(row -> row.matchesStringFilter(stringFilter));
    }
    // Exclude M_HU_IDs
    final ImmutableSet<HuId> excludeHUIds = filter.getExcludeHUIds();
    if (!excludeHUIds.isEmpty()) {
        predicate = predicate.and(row -> !excludeHUIds.contains(row.getHuId()));
    }
    // Include HUStatuses
    final ImmutableSet<String> onlyHUStatuses = filter.getOnlyHUStatuses();
    if (!onlyHUStatuses.isEmpty()) {
        predicate = predicate.and(row -> onlyHUStatuses.contains(row.getHUStatus()));
    }
    // Exclude HUStatuses
    final ImmutableSet<String> excludeHUStatuses = filter.getExcludeHUStatuses();
    if (!excludeHUStatuses.isEmpty()) {
        predicate = predicate.and(row -> !excludeHUStatuses.contains(row.getHUStatus()));
    }
    return predicate;
}


}