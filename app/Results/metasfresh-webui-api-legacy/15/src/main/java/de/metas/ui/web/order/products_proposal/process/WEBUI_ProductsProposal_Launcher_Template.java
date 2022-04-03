package de.metas.ui.web.order.products_proposal.process;
 import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.process.JavaProcess;
import de.metas.process.ProcessExecutionResult.ViewOpenTarget;
import de.metas.process.ProcessExecutionResult.WebuiViewToOpen;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
public class WEBUI_ProductsProposal_Launcher_Template extends JavaProcess{

@Autowired
 private  IViewsRepository viewsRepo;


public CreateViewRequest createViewRequest(TableRecordReference recordRef)


@Override
public String doIt(){
    final TableRecordReference recordRef = TableRecordReference.of(getTableName(), getRecord_ID());
    final IView view = viewsRepo.createView(createViewRequest(recordRef));
    final ViewId viewId = view.getViewId();
    getResult().setWebuiViewToOpen(WebuiViewToOpen.builder().viewId(viewId.toJson()).target(ViewOpenTarget.ModalOverlay).build());
    return MSG_OK;
}


}