package de.metas.ui.web.purchasecandidate.interceptor;
 import org.adempiere.ad.modelvalidator.annotations.Interceptor;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.compiere.model.ModelValidator;
import org.springframework.stereotype.Component;
import de.metas.purchasecandidate.PurchaseCandidateReminder;
import de.metas.purchasecandidate.PurchaseCandidateRepository;
import de.metas.purchasecandidate.model.I_C_PurchaseCandidate;
import de.metas.purchasecandidate.reminder.PurchaseCandidateReminderScheduler;
import lombok.NonNull;
@Interceptor(I_C_PurchaseCandidate.class)
@Component
public class C_PurchaseCandidate {

 private  PurchaseCandidateReminderScheduler scheduler;


@// 
ModelChange(// 
timings = { ModelValidator.TYPE_AFTER_NEW, ModelValidator.TYPE_AFTER_CHANGE, ModelValidator.TYPE_AFTER_DELETE }, // 
ifColumnsChanged = { I_C_PurchaseCandidate.COLUMNNAME_ReminderDate, I_C_PurchaseCandidate.COLUMNNAME_Vendor_ID }, afterCommit = true)
public void scheduleReminderForWebui(I_C_PurchaseCandidate record){
    final PurchaseCandidateReminder reminder = PurchaseCandidateRepository.toPurchaseCandidateReminderOrNull(record);
    if (reminder == null) {
        return;
    }
    scheduler.scheduleNotification(reminder);
}


}