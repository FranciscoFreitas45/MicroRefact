package de.metas.ui.web.product.process;
 import java.util.ArrayList;
import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.mm.attributes.api.ILotNumberDateAttributeDAO;
import org.compiere.model.I_M_Attribute;
import org.compiere.model.I_M_InOut;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.IHUQueryBuilder;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.ddorder.api.IHUDDOrderBL;
import de.metas.handlingunits.ddorder.api.IHUDDOrderDAO;
import de.metas.handlingunits.ddorder.api.impl.HUs2DDOrderProducer.HUToDistribute;
import de.metas.handlingunits.inout.IHUInOutDAO;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.X_M_HU;
import de.metas.handlingunits.quarantine.HULotNumberQuarantineService;
import de.metas.invoicecandidate.api.IInvoiceCandBL;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.product.LotNumberQuarantine;
import de.metas.product.LotNumberQuarantineRepository;
import de.metas.product.ProductId;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.Check;
import de.metas.util.Services;
public class WEBUI_M_Product_LotNumber_Quarantine extends ViewBasedProcessTemplateimplements IProcessPrecondition{

@Autowired
 private  LotNumberQuarantineRepository lotNoQuarantineRepo;

@Autowired
 private  HULotNumberQuarantineService huLotNoQuarantineService;

 private  IInvoiceCandBL invoiceCandBL;

 private  IHUInOutDAO huInOutDAO;

 private  IHUDDOrderBL huDDOrderBL;

 private  ILotNumberDateAttributeDAO lotNumberDateAttributeDAO;

 private  IHUDDOrderDAO huDDOrderDAO;

 private  List<HUToDistribute> husToQuarantine;


public List<I_M_HU> retrieveHUsForAttributeStringValue(int productId,I_M_Attribute attribute,String value){
    final IHUQueryBuilder huQueryBuilder = Services.get(IHandlingUnitsDAO.class).createHUQueryBuilder().addOnlyWithAttribute(attribute, value).addHUStatusesToInclude(ImmutableList.of(X_M_HU.HUSTATUS_Picked, X_M_HU.HUSTATUS_Active));
    if (productId > 0) {
        huQueryBuilder.addOnlyWithProductId(ProductId.ofRepoId(productId));
    }
    return huQueryBuilder.list();
}


@Override
public String doIt(){
    getView().streamByIds(getSelectedRowIds()).map(row -> row.getId().toInt()).distinct().forEach(this::createQuarantineHUsByLotNoQuarantineId);
    huDDOrderBL.createQuarantineDDOrderForHUs(husToQuarantine);
    setInvoiceCandsInDispute();
    return MSG_OK;
}


public void setInvoiceCandsInDispute(){
    husToQuarantine.stream().map(HUToDistribute::getHu).flatMap(hu -> huInOutDAO.retrieveInOutLinesForHU(hu).stream()).forEach(invoiceCandBL::markInvoiceCandInDisputeForReceiptLine);
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    if (selectedRowIds.isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    return ProcessPreconditionsResolution.accept();
}


public void createQuarantineHUsByLotNoQuarantineId(int lotNoQuarantineId){
    final LotNumberQuarantine lotNoQuarantine = lotNoQuarantineRepo.getById(lotNoQuarantineId);
    final I_M_Attribute lotNoAttribute = lotNumberDateAttributeDAO.getLotNumberAttribute();
    if (lotNoAttribute == null) {
        throw new AdempiereException("Not lotNo attribute found.");
    }
    final int productId = lotNoQuarantine.getProductId();
    final String lotNoValue = lotNoQuarantine.getLotNo();
    final List<I_M_HU> husForAttributeStringValue = retrieveHUsForAttributeStringValue(productId, lotNoAttribute, lotNoValue);
    for (final I_M_HU hu : husForAttributeStringValue) {
        if (huDDOrderDAO.existsDDOrderLineCandidateForHUId(hu.getM_HU_ID())) {
            // the HU is already quarantined
            continue;
        }
        final List<de.metas.handlingunits.model.I_M_InOutLine> inOutLinesForHU = huInOutDAO.retrieveInOutLinesForHU(hu);
        if (Check.isEmpty(inOutLinesForHU)) {
            continue;
        }
        huLotNoQuarantineService.markHUAsQuarantine(hu);
        final I_M_InOut firstReceipt = inOutLinesForHU.get(0).getM_InOut();
        final int bpartnerId = firstReceipt.getC_BPartner_ID();
        final int bpLocationId = firstReceipt.getC_BPartner_Location_ID();
        husToQuarantine.add(HUToDistribute.builder().hu(hu).quarantineLotNo(lotNoQuarantine).bpartnerId(bpartnerId).bpartnerLocationId(bpLocationId).build());
    }
}


}