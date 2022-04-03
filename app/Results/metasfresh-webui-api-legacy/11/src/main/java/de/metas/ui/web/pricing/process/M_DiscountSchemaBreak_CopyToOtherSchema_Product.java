package de.metas.ui.web.pricing.process;
 import org.adempiere.ad.dao.ConstantQueryFilter;
import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.ad.dao.impl.TypedSqlQueryFilter;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_M_DiscountSchema;
import org.compiere.model.I_M_DiscountSchemaBreak;
import org.compiere.model.I_M_Product;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.ITranslatableString;
import de.metas.pricing.conditions.PricingConditionsId;
import de.metas.pricing.conditions.service.IPricingConditionsRepository;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.product.ProductId;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.Services;
import lombok.NonNull;
public class M_DiscountSchemaBreak_CopyToOtherSchema_Product extends ViewBasedProcessTemplateimplements IProcessDefaultParametersProvider,IProcessPrecondition{

 private  AdMessageKey MSG;

 private  IPricingConditionsRepository pricingConditionsRepo;

 final  String PARAM_M_Product_ID;

@Param(parameterName = I_M_DiscountSchema.COLUMNNAME_M_DiscountSchema_ID, mandatory = true)
 private  int p_PricingConditionsId;

@Param(parameterName = PARAM_M_Product_ID, mandatory = true)
 private  int p_ProductId;


public boolean rowsHaveSingleProductId(){
    // getProcessInfo().getQueryFilterOrElse(ConstantQueryFilter.of(false)); doesn't work from checkPreconditionsApplicable
    final String viewSqlWhereClause = getViewSqlWhereClause(getSelectedRowIds());
    final IQueryFilter<I_M_DiscountSchemaBreak> selectionQueryFilter = TypedSqlQueryFilter.of(viewSqlWhereClause);
    return pricingConditionsRepo.isSingleProductId(selectionQueryFilter);
}


@Override
public Object getParameterDefaultValue(IProcessDefaultParameter parameter){
    final String parameterName = parameter.getColumnName();
    if (PARAM_M_Product_ID.equals(parameterName)) {
        final IQueryFilter<I_M_DiscountSchemaBreak> selectionQueryFilter = getProcessInfo().getQueryFilterOrElse(ConstantQueryFilter.of(false));
        final ProductId uniqueProductIdForSelection = pricingConditionsRepo.retrieveUniqueProductIdForSelectionOrNull(selectionQueryFilter);
        if (uniqueProductIdForSelection == null) {
            // should not happen because of the preconditions above
            return IProcessDefaultParametersProvider.DEFAULT_VALUE_NOTAVAILABLE;
        }
        return uniqueProductIdForSelection.getRepoId();
    } else {
        return IProcessDefaultParametersProvider.DEFAULT_VALUE_NOTAVAILABLE;
    }
}


@Override
public String doIt(){
    final IQueryFilter<I_M_DiscountSchemaBreak> queryFilter = getProcessInfo().getQueryFilterOrElse(null);
    if (queryFilter == null) {
        throw new AdempiereException("@NoSelection@");
    }
    final boolean allowCopyToSameSchema = true;
    pricingConditionsRepo.copyDiscountSchemaBreaksWithProductId(queryFilter, PricingConditionsId.ofRepoId(p_PricingConditionsId), ProductId.ofRepoId(p_ProductId), allowCopyToSameSchema);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (getSelectedRowIds().isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    if (!rowsHaveSingleProductId()) {
        final ITranslatableString msg = msgBL.getTranslatableMsgText(MSG);
        return ProcessPreconditionsResolution.reject(msg);
    }
    return ProcessPreconditionsResolution.accept();
}


public String getViewSqlWhereClause(DocumentIdsSelection rowIds){
    final String breaksTableName = I_M_DiscountSchemaBreak.Table_Name;
    return getView().getSqlWhereClause(rowIds, SqlOptions.usingTableName(breaksTableName));
}


}