package de.metas.ui.web.request.process;
 import com.google.common.collect.ImmutableList;
import de.metas.bpartner.service.IBPartnerDAO;
import de.metas.inout.IInOutDAO;
import de.metas.inout.InOutId;
import de.metas.process.JavaProcess;
import de.metas.process.ProcessExecutionResult.RecordsToOpen.OpenTarget;
import de.metas.process.RunOutOfTrx;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import de.metas.ui.web.window.model.NullDocumentChangesCollector;
import de.metas.util.Services;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.SpringContextHolder;
import org.compiere.model.I_AD_User;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_M_InOut;
import org.compiere.model.I_R_Request;
import org.springframework.beans.factory.annotation.Autowired;
public class WEBUI_CreateRequest extends JavaProcess{

@Autowired
 private  DocumentCollection documentCollection;

 private  IBPartnerDAO bPartnerDAO;

 private  IInOutDAO inOutDAO;


public void createRequestFromShipment(I_M_InOut shipment){
    final I_C_BPartner bPartner = bPartnerDAO.getById(shipment.getC_BPartner_ID());
    final I_AD_User defaultContact = Services.get(IBPartnerDAO.class).retrieveDefaultContactOrNull(bPartner, I_AD_User.class);
    final ImmutableList.Builder<JSONDocumentChangedEvent> events = ImmutableList.builder();
    events.add(JSONDocumentChangedEvent.replace(I_R_Request.COLUMNNAME_SalesRep_ID, getAD_User_ID()));
    events.add(JSONDocumentChangedEvent.replace(I_R_Request.COLUMNNAME_C_BPartner_ID, shipment.getC_BPartner_ID()));
    events.add(JSONDocumentChangedEvent.replace(I_R_Request.COLUMNNAME_M_InOut_ID, shipment.getM_InOut_ID()));
    events.add(JSONDocumentChangedEvent.replace(I_R_Request.COLUMNNAME_DateDelivered, shipment.getMovementDate()));
    if (defaultContact != null) {
        events.add(JSONDocumentChangedEvent.replace(I_R_Request.COLUMNNAME_AD_User_ID, defaultContact.getAD_User_ID()));
    }
    final DocumentPath documentPath = DocumentPath.builder().setDocumentType(WindowConstants.WINDOWID_R_Request).setDocumentId(DocumentId.NEW_ID_STRING).allowNewDocumentId().build();
    final DocumentId documentId = documentCollection.forDocumentWritable(documentPath, NullDocumentChangesCollector.instance, document -> {
        document.processValueChanges(events.build(), ReasonSupplier.NONE);
        return document.getDocumentId();
    });
    getResult().setRecordToOpen(TableRecordReference.of(I_R_Request.Table_Name, documentId.toInt()), documentPath.getWindowId().toInt(), OpenTarget.SingleDocumentModal);
}


public void createRequestFromBPartner(I_C_BPartner bpartner){
    final I_AD_User defaultContact = Services.get(IBPartnerDAO.class).retrieveDefaultContactOrNull(bpartner, I_AD_User.class);
    final ImmutableList.Builder<JSONDocumentChangedEvent> events = ImmutableList.builder();
    events.add(JSONDocumentChangedEvent.replace(I_R_Request.COLUMNNAME_SalesRep_ID, getAD_User_ID()));
    events.add(JSONDocumentChangedEvent.replace(I_R_Request.COLUMNNAME_C_BPartner_ID, bpartner.getC_BPartner_ID()));
    if (defaultContact != null) {
        events.add(JSONDocumentChangedEvent.replace(I_R_Request.COLUMNNAME_AD_User_ID, defaultContact.getAD_User_ID()));
    }
    final DocumentPath documentPath = DocumentPath.builder().setDocumentType(WindowConstants.WINDOWID_R_Request).setDocumentId(DocumentId.NEW_ID_STRING).allowNewDocumentId().build();
    final DocumentId documentId = documentCollection.forDocumentWritable(documentPath, NullDocumentChangesCollector.instance, document -> {
        document.processValueChanges(events.build(), ReasonSupplier.NONE);
        return document.getDocumentId();
    });
    getResult().setRecordToOpen(TableRecordReference.of(I_R_Request.Table_Name, documentId.toInt()), documentPath.getWindowId().toInt(), OpenTarget.SingleDocumentModal);
}


@Override
@RunOutOfTrx
public String doIt(){
    final String tableName = getTableName();
    if (I_C_BPartner.Table_Name.equals(tableName)) {
        final I_C_BPartner bPartner = bPartnerDAO.getById(getProcessInfo().getRecord_ID());
        createRequestFromBPartner(bPartner);
    } else if (I_M_InOut.Table_Name.equals(tableName)) {
        final I_M_InOut shipment = inOutDAO.getById(InOutId.ofRepoId(getProcessInfo().getRecord_ID()));
        createRequestFromShipment(shipment);
    } else {
        throw new IllegalStateException("Not supported: " + tableName);
    }
    return MSG_OK;
}


}