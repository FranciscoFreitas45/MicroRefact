package de.metas.ui.web.pickingV2.productsToPick.process;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.handlingunits.picking.candidate.commands.PickHUResult;
import de.metas.handlingunits.picking.requests.PickRequest;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.ui.web.pickingV2.config.PickingConfigV2;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRow;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRowsService;
public class ProductsToPick_PickSelected extends ProductsToPickViewBasedProcess{

@Autowired
 private  PickingCandidateService pickingCandidatesService;

@Autowired
 private  ProductsToPickRowsService productsToPickRowsService;


public void pickRow(ProductsToPickRow row){
    final PickHUResult result = pickingCandidatesService.pickHU(createPickRequest(row));
    updateViewRowFromPickingCandidate(row.getId(), result.getPickingCandidate());
}


@Override
@RunOutOfTrx
public String doIt(){
    getSelectedRows().stream().filter(ProductsToPickRow::isEligibleForPicking).forEach(this::pickRow);
    invalidateView();
    return MSG_OK;
}


public PickRequest createPickRequest(ProductsToPickRow row){
    final PickingConfigV2 pickingConfig = getPickingConfig();
    return productsToPickRowsService.createPickRequest(row, pickingConfig.isPickingReviewRequired());
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!isPickerProfile()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("only picker shall pick");
    }
    final List<ProductsToPickRow> selectedRows = getSelectedRows();
    if (selectedRows.isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    if (!selectedRows.stream().allMatch(ProductsToPickRow::isEligibleForPicking)) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("select only rows that can be picked");
    }
    return ProcessPreconditionsResolution.accept();
}


}