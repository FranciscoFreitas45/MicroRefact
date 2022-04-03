package de.metas.ui.web.order.products_proposal.process;
 import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableList;
import de.metas.logging.LogManager;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessExecutionResult.ViewOpenTarget;
import de.metas.process.ProcessExecutionResult.WebuiViewToOpen;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRow;
import de.metas.ui.web.order.products_proposal.view.ProductsProposalView;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewId;
public class ProductsProposalViewBasedProcess extends ViewBasedProcessTemplateimplements IProcessPrecondition{

 private  Logger logger;

@Autowired
 private  IViewsRepository viewsRepo;


public List<ProductsProposalRow> getSelectedRows(){
    return getView().streamByIds(getSelectedRowIds()).collect(ImmutableList.toImmutableList());
}


@Override
public ProductsProposalRow getSingleSelectedRow(){
    return ProductsProposalRow.cast(super.getSingleSelectedRow());
}


public void closeAllViewsAndShowInitialView(){
    closeAllViewsExcludingInitialView();
    afterCloseOpenView(getInitialViewId());
}


public void afterCloseOpenView(ViewId viewId){
    getResult().setWebuiViewToOpen(WebuiViewToOpen.builder().viewId(viewId.toJson()).target(ViewOpenTarget.ModalOverlay).build());
}


public ProductsProposalView getInitialView(){
    return ProductsProposalView.cast(viewsRepo.getView(getInitialViewId()));
}


public void closeAllViewsExcludingInitialView(){
    IView currentView = getView();
    while (currentView != null && currentView.getParentViewId() != null) {
        try {
            viewsRepo.closeView(currentView.getViewId(), ViewCloseAction.CANCEL);
        } catch (Exception ex) {
            logger.warn("Failed closing view {}. Ignored", currentView, ex);
        }
        final ViewId viewId = currentView.getParentViewId();
        currentView = viewsRepo.getViewIfExists(viewId);
    }
}


@Override
public ProductsProposalView getView(){
    return ProductsProposalView.cast(super.getView());
}


public ViewId getInitialViewId(){
    return getView().getInitialViewId();
}


}