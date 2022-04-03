package de.metas.ui.web.material.cockpit.process;
 import java.util.List;
import java.util.Set;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.util.lang.impl.TableRecordReference;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.material.cockpit.model.I_MD_Cockpit_DocumentDetail;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.material.cockpit.MaterialCockpitUtil;
import de.metas.ui.web.material.cockpit.MaterialCockpitView;
import de.metas.util.Check;
import de.metas.util.Services;
public class MD_Cockpit_DocumentDetail_Display extends MaterialCockpitViewBasedProcess{


public List<TableRecordReference> retrieveCockpitDetailRecordReferences(Set<Integer> cockpitRowIds){
    Check.assumeNotEmpty(cockpitRowIds, "cockpitRowIds is not empty");
    return Services.get(IQueryBL.class).createQueryBuilder(I_MD_Cockpit_DocumentDetail.class).addOnlyActiveRecordsFilter().addInArrayFilter(I_MD_Cockpit_DocumentDetail.COLUMN_MD_Cockpit_ID, cockpitRowIds).create().listIds().stream().map(cockpitDetailRecordId -> TableRecordReference.of(I_MD_Cockpit_DocumentDetail.Table_Name, cockpitDetailRecordId)).collect(ImmutableList.toImmutableList());
}


public Set<Integer> getSelectedCockpitRecordIdsRecursively(){
    final MaterialCockpitView materialCockpitView = getView();
    return getSelectedRowIds().stream().map(materialCockpitView::getById).flatMap(row -> row.getAllIncludedCockpitRecordIds().stream()).collect(ImmutableSet.toImmutableSet());
}


@Override
public String doIt(){
    final Set<Integer> cockpitRowIds = getSelectedCockpitRecordIdsRecursively();
    final List<TableRecordReference> cockpitDetailRecords = retrieveCockpitDetailRecordReferences(cockpitRowIds);
    getResult().setRecordsToOpen(cockpitDetailRecords, MaterialCockpitUtil.WINDOWID_MaterialCockpit_Detail_String);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (getSelectedRowIds().isEmpty()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("No MaterialCockpitrows are selected");
    }
    final Set<Integer> cockpitRowIds = getSelectedCockpitRecordIdsRecursively();
    if (cockpitRowIds.isEmpty()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("The selected rows are just dummys with all-zero");
    }
    return ProcessPreconditionsResolution.accept();
}


}