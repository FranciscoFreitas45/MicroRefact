package de.metas.ui.web.order.products_proposal.process;
 import org.adempiere.util.lang.impl.TableRecordReference;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.ui.web.order.products_proposal.view.BPartnerProductsProposalViewFactory;
import de.metas.ui.web.view.CreateViewRequest;
public class WEBUI_BPartner_ProductsProposal_Launcher extends WEBUI_ProductsProposal_Launcher_Template{

@Autowired
 private  BPartnerProductsProposalViewFactory productsProposalViewFactory;


@Override
public CreateViewRequest createViewRequest(TableRecordReference recordRef){
    return productsProposalViewFactory.createViewRequest(recordRef);
}


}