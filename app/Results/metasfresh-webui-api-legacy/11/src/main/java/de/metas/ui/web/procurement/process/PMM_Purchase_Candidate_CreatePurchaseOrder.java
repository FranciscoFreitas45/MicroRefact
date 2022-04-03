package de.metas.ui.web.procurement.process;
 import de.metas.Profiles;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.procurement.base.model.I_PMM_PurchaseCandidate;
import de.metas.procurement.base.order.async.PMM_GenerateOrders;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.Services;
import org.adempiere.ad.dao.ICompositeQueryFilter;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.impl.CompareQueryFilter;
import org.adempiere.ad.dao.impl.TypedSqlQueryFilter;
import org.springframework.context.annotation.Profile;
import java.math.BigDecimal;
@Profile(Profiles.PROFILE_Webui)
public class PMM_Purchase_Candidate_CreatePurchaseOrder extends ViewBasedProcessTemplateimplements IProcessPrecondition{

 private  int recordsEnqueued;

 private  IQueryBL queryBL;


@Override
public String doIt(){
    final SqlOptions sqlOptions = SqlOptions.usingTableName(I_PMM_PurchaseCandidate.Table_Name);
    final String sqlWhereClause = getView().getSqlWhereClause(DocumentIdsSelection.ALL, sqlOptions);
    final ICompositeQueryFilter<I_PMM_PurchaseCandidate> i_pmm_purchaseCandidateICompositeQueryFilter = queryBL.createCompositeQueryFilter(I_PMM_PurchaseCandidate.class).addCompareFilter(I_PMM_PurchaseCandidate.COLUMNNAME_QtyOrdered, CompareQueryFilter.Operator.GREATER, BigDecimal.ZERO).addFilter(TypedSqlQueryFilter.of(sqlWhereClause));
    recordsEnqueued = PMM_GenerateOrders.prepareEnqueuing().filter(i_pmm_purchaseCandidateICompositeQueryFilter).enqueue();
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    // only showing the action if there are rows in the view
    if (getView().size() <= 0) {
        return ProcessPreconditionsResolution.reject();
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public void postProcess(boolean success){
    if (!success) {
        return;
    }
    // 
    // Notify frontend that the view shall be refreshed because we changed some candidates
    if (recordsEnqueued > 0) {
        invalidateView();
    }
}


}