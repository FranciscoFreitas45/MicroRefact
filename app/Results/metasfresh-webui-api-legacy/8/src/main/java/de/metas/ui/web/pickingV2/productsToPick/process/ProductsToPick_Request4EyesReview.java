package de.metas.ui.web.pickingV2.productsToPick.process;
 import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.globalaction.GlobalActionEvent;
import de.metas.ui.web.globalaction.GlobalActionEvents;
import de.metas.ui.web.pickingV2.PickingConstantsV2;
import de.metas.ui.web.pickingV2.productsToPick.ProductsToPickView;
import de.metas.ui.web.view.ViewId;
public class ProductsToPick_Request4EyesReview extends ProductsToPickViewBasedProcess{


@Override
public String doIt(){
    final ProductsToPickView view = getView();
    final ViewId viewId = view.getViewId();
    final GlobalActionEvent openViewEvent = GlobalActionEvents.openView(viewId, PickingConstantsV2.PROFILE_ID_ProductsToPickView_Review);
    getResult().setDisplayQRCode(openViewEvent.toDisplayQRCodeProcessResult());
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (isReviewProfile()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("already reviewing");
    }
    final ProductsToPickView view = getView();
    if (!view.isEligibleForReview()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not all rows are eligible");
    }
    return ProcessPreconditionsResolution.accept();
}


}