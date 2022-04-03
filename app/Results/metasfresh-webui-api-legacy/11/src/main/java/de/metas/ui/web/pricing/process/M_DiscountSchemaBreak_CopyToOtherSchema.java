package de.metas.ui.web.pricing.process;
 import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_M_DiscountSchema;
import org.compiere.model.I_M_DiscountSchemaBreak;
import de.metas.pricing.conditions.PricingConditionsId;
import de.metas.pricing.conditions.service.IPricingConditionsRepository;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.util.Services;
public class M_DiscountSchemaBreak_CopyToOtherSchema extends ViewBasedProcessTemplateimplements IProcessPrecondition{

 private  IPricingConditionsRepository pricingConditionsRepo;

@Param(parameterName = I_M_DiscountSchema.COLUMNNAME_M_DiscountSchema_ID)
 private  int p_PricingConditionsId;


@Override
public String doIt(){
    final IQueryFilter<I_M_DiscountSchemaBreak> queryFilter = getProcessInfo().getQueryFilterOrElse(null);
    if (queryFilter == null) {
        throw new AdempiereException("@NoSelection@");
    }
    pricingConditionsRepo.copyDiscountSchemaBreaks(queryFilter, PricingConditionsId.ofRepoId(p_PricingConditionsId));
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (getSelectedRowIds().isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    return ProcessPreconditionsResolution.accept();
}


}