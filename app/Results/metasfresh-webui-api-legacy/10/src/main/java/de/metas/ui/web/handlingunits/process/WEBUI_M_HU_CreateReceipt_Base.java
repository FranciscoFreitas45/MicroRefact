package de.metas.ui.web.handlingunits.process;
 import java.util.List;
import java.util.Set;
import org.adempiere.ad.dao.ConstantQueryFilter;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.mm.attributes.api.AttributeConstants;
import org.adempiere.mm.attributes.api.IAttributeDAO;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.model.IQuery;
import org.compiere.model.I_M_Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableSet;
import de.metas.bpartner.BPartnerId;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_ReceiptSchedule;
import de.metas.handlingunits.receiptschedule.IHUReceiptScheduleBL;
import de.metas.handlingunits.receiptschedule.IHUReceiptScheduleBL.CreateReceiptsParameters;
import de.metas.handlingunits.receiptschedule.IHUReceiptScheduleBL.CreateReceiptsParameters.CreateReceiptsParametersBuilder;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.product.ProductRepository;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorRowAttributes;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import de.metas.vertical.pharma.securpharm.attribute.SecurPharmAttributesStatus;
import de.metas.vertical.pharma.securpharm.service.SecurPharmService;
import lombok.NonNull;
public class WEBUI_M_HU_CreateReceipt_Base extends WEBUI_M_HU_Receipt_Baseimplements IProcessPrecondition{

 private  AdMessageKey MSG_ScanRequired;

 private  AdMessageKey MSG_MissingMandatoryHUAttribute;

@Autowired
 private  IViewsRepository viewsRepo;

@Autowired
 private  DocumentCollection documentsCollection;

@Autowired
 private  SecurPharmService securPharmService;

@Autowired
 private  ProductRepository productRepository;

 private  IHUReceiptScheduleBL huReceiptScheduleBL;

 private  IAttributeDAO attributeDAO;


public I_M_ReceiptSchedule getReceiptSchedule(DocumentPath referencingDocumentPath){
    return documentsCollection.getTableRecordReference(referencingDocumentPath).getModel(this, I_M_ReceiptSchedule.class);
}


public ProcessPreconditionsResolution rejectIfNotPlanningHUStatus(HUEditorRow document){
    return document.isHUStatusPlanning() ? ProcessPreconditionsResolution.accept() : ProcessPreconditionsResolution.rejectWithInternalReason("Only planning HUs can be received");
}


public void customizeParametersBuilder(CreateReceiptsParametersBuilder parametersBuilder)


public ProcessPreconditionsResolution rejectIfSecurPharmAttributesAreNotOK(HUEditorRow document){
    // 
    // OK if this is not a Pharma product
    final HUEditorRowAttributes attributes = document.getAttributes();
    if (!attributes.hasAttribute(AttributeConstants.ATTR_SecurPharmScannedStatus)) {
        return ProcessPreconditionsResolution.accept();
    }
    // 
    // NOK if SecurPharm connection is not configured and we deal with a pharma product
    if (!securPharmService.hasConfig()) {
        return ProcessPreconditionsResolution.reject("SecurPharm not configured");
    }
    // 
    // NOK if not scanned and vendor != manufacturer
    final BPartnerId vendorId = document.getBPartnerId();
    final BPartnerId manufacturerId = productRepository.getById(document.getProductId()).getManufacturerId();
    if (!BPartnerId.equals(vendorId, manufacturerId)) {
        final SecurPharmAttributesStatus status = SecurPharmAttributesStatus.ofNullableCodeOrKnown(attributes.getValueAsString(AttributeConstants.ATTR_SecurPharmScannedStatus));
        if (status.isUnknown()) {
            return ProcessPreconditionsResolution.reject(Services.get(IMsgBL.class).getTranslatableMsgText(MSG_ScanRequired));
        }
    }
    // 
    // OK
    return ProcessPreconditionsResolution.accept();
}


public ProcessPreconditionsResolution rejectIfMandatoryAttributesAreNotFilled(HUEditorRow document){
    // 
    // Make sure all mandatory attributes are filled
    final HUEditorRowAttributes attributes = document.getAttributes();
    for (final String mandatoryAttributeName : attributes.getMandatoryAttributeNames()) {
        final Object value = attributes.getValue(mandatoryAttributeName);
        if (Check.isEmpty(value)) {
            final I_M_Attribute attribute = attributeDAO.retrieveAttributeByValue(mandatoryAttributeName);
            final I_M_Attribute translatedAttribute = InterfaceWrapperHelper.translate(attribute, I_M_Attribute.class);
            final ITranslatableString msg = msgBL.getTranslatableMsgText(MSG_MissingMandatoryHUAttribute, translatedAttribute.getName());
            return ProcessPreconditionsResolution.reject(msg);
        }
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
// IHUReceiptScheduleBL.processReceiptSchedules creates its own transaction
@RunOutOfTrx
public String doIt(){
    // Generate material receipts
    final List<I_M_ReceiptSchedule> receiptSchedules = getM_ReceiptSchedules();
    final Set<HuId> selectedHuIds = retrieveHUsToReceive();
    final CreateReceiptsParametersBuilder parametersBuilder = CreateReceiptsParameters.builder().commitEachReceiptIndividually(false).createReceiptWithDatePromised(false).ctx(getCtx()).destinationLocatorIdOrNull(// use receipt schedules' destination-warehouse settings
    null).printReceiptLabels(true).receiptSchedules(receiptSchedules).selectedHuIds(selectedHuIds);
    customizeParametersBuilder(parametersBuilder);
    final CreateReceiptsParameters parameters = parametersBuilder.build();
    huReceiptScheduleBL.processReceiptSchedules(parameters);
    // NOTE: at this point, the user was already notified about generated material receipts
    // Reset the view's affected HUs
    getView().invalidateAll();
    viewsRepo.notifyRecordsChanged(TableRecordReferenceSet.of(TableRecordReference.ofSet(receiptSchedules)));
    return MSG_OK;
}


@Override
public HUEditorView getView(){
    return getView(HUEditorView.class);
}


public Set<HuId> retrieveHUsToReceive(){
    // https://github.com/metasfresh/metasfresh/issues/1863
    // if the queryFilter is empty, then *do not* return everything to avoid an OOME
    final IQueryFilter<I_M_HU> processInfoFilter = getProcessInfo().getQueryFilterOrElse(ConstantQueryFilter.of(false));
    final IQuery<I_M_HU> query = Services.get(IQueryBL.class).createQueryBuilder(I_M_HU.class, this).filter(processInfoFilter).addOnlyActiveRecordsFilter().create();
    final Set<HuId> huIds = query.listIds().stream().map(HuId::ofRepoId).collect(ImmutableSet.toImmutableSet());
    if (huIds.isEmpty()) {
        throw new AdempiereException("@NoSelection@ @M_HU_ID@").appendParametersToMessage().setParameter("query", query);
    }
    return huIds;
}


@Override
public ProcessPreconditionsResolution rejectResolutionOrNull(HUEditorRow document){
    return ProcessPreconditionsResolution.firstRejectOrElseAccept(() -> rejectIfNotPlanningHUStatus(document), () -> rejectIfMandatoryAttributesAreNotFilled(document), () -> rejectIfSecurPharmAttributesAreNotOK(document));
}


public List<I_M_ReceiptSchedule> getM_ReceiptSchedules(){
    return getView().getReferencingDocumentPaths().stream().map(referencingDocumentPath -> getReceiptSchedule(referencingDocumentPath)).collect(GuavaCollectors.toImmutableList());
}


}