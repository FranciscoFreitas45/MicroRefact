package de.metas.ui.web.crm.interceptor;
 import java.sql.Timestamp;
import org.adempiere.ad.modelvalidator.annotations.Interceptor;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_R_Request;
import org.compiere.model.ModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.metas.ui.web.window.model.DocumentCollection;
@Interceptor(I_R_Request.class)
@Component
public class R_Request {

@Autowired
 private  DocumentCollection documentsCollection;


@ModelChange(timings = { ModelValidator.TYPE_AFTER_NEW, ModelValidator.TYPE_AFTER_CHANGE }, ifColumnsChanged = I_R_Request.COLUMNNAME_ReminderDate)
public void updateBPartnerReminderDate(I_R_Request request){
    final Timestamp reminderDate = request.getReminderDate();
    if (reminderDate == null) {
        return;
    }
    final int adUserId = request.getSalesRep_ID();
    if (adUserId < 0) {
        return;
    }
    final I_C_BPartner bpartner = request.getC_BPartner();
    if (bpartner == null) {
        // nothing to do
        return;
    }
    if (bpartner.getSalesRepIntern_ID() == adUserId) {
        bpartner.setReminderDateIntern(reminderDate);
    } else if (bpartner.getSalesRep_ID() == adUserId) {
        bpartner.setReminderDateExtern(reminderDate);
    } else {
        // nothing to do
        return;
    }
    InterfaceWrapperHelper.save(bpartner);
    documentsCollection.invalidateDocumentByRecordId(I_C_BPartner.Table_Name, bpartner.getC_BPartner_ID());
}


}