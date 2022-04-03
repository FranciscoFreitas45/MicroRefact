package de.metas.ui.web.pickingslotsClearing.process;
 import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.adempiere.ad.dao.IQueryBL;
import java.util.Objects;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorRowFilter;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.util.Check;
import de.metas.util.Services;
public class PackingHUsViewBasedProcess extends HUEditorProcessTemplate{

 private  IQueryBL queryBL;


public Stream<HUEditorRow> streamEligibleHURows(){
    return streamSelectedRows(HUEditorRowFilter.select(Select.ONLY_TOPLEVEL));
}


public List<I_M_HU> retrieveEligibleHUs(){
    final Set<HuId> huIds = streamEligibleHURows().map(HUEditorRow::getHuId).filter(Objects::nonNull).collect(ImmutableSet.toImmutableSet());
    // shall not happen
    Check.assumeNotEmpty(huIds, "huIds is not empty");
    final List<I_M_HU> hus = queryBL.createQueryBuilder(I_M_HU.class).addInArrayFilter(I_M_HU.COLUMN_M_HU_ID, huIds).addOnlyActiveRecordsFilter().create().list(I_M_HU.class);
    // shall not happen
    Check.assumeNotEmpty(hus, "hus is not empty");
    return hus;
}


}