package de.metas.purchasecandidate.reminder;
 import java.time.ZonedDateTime;
import java.util.List;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.RecordZoomWindowFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.metas.purchasecandidate.PurchaseCandidateReminder;
import de.metas.purchasecandidate.model.I_C_PurchaseCandidate;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.session.UserSession;
@RestController
@RequestMapping(PurchaseCandidateReminderSchedulerRestController.ENDPOINT)
public class PurchaseCandidateReminderSchedulerRestController {

 public  String ENDPOINT;

@Autowired
 private  PurchaseCandidateReminderScheduler reminderScheduler;

@Autowired
 private  UserSession userSession;


@PostMapping("/reinitialize")
public List<PurchaseCandidateReminder> reinitialize(){
    assertAuth();
    reminderScheduler.initialize();
    return reminderScheduler.getReminders();
}


@GetMapping
public List<PurchaseCandidateReminder> getReminders(){
    assertAuth();
    return reminderScheduler.getReminders();
}


public void assertAuth(){
    userSession.assertLoggedIn();
    final AdWindowId purchaseCandidatesWindowId = RecordZoomWindowFinder.findAdWindowId(I_C_PurchaseCandidate.Table_Name).get();
    if (!userSession.getUserRolePermissions().checkWindowPermission(purchaseCandidatesWindowId).hasWriteAccess()) {
        throw new AdempiereException("No read/write access to purchase candidates window");
    }
}


@PostMapping
public void addReminder(PurchaseCandidateReminder reminder){
    assertAuth();
    reminderScheduler.scheduleNotification(reminder);
}


@GetMapping("/nextDispatchTime")
public ZonedDateTime getNextDispatchTime(){
    assertAuth();
    return reminderScheduler.getNextDispatchTime();
}


}