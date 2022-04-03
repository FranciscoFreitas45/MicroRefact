package de.metas.purchasecandidate.reminder;
 import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ScheduledFuture;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.model.RecordZoomWindowFinder;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.model.I_C_BPartner;
import org.compiere.util.TimeUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import com.google.common.collect.ImmutableList;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.service.IBPartnerBL;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.notification.INotificationBL;
import de.metas.notification.NotificationGroupName;
import de.metas.notification.Recipient;
import de.metas.notification.UserNotificationRequest;
import de.metas.notification.UserNotificationRequest.TargetViewAction;
import de.metas.purchasecandidate.PurchaseCandidateReminder;
import de.metas.purchasecandidate.PurchaseCandidateRepository;
import de.metas.purchasecandidate.model.I_C_PurchaseCandidate;
import de.metas.ui.web.WebRestApiApplication;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterParam;
import de.metas.ui.web.document.filter.DocumentFilterParam.Operator;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Services;
import lombok.NonNull;
@Component
public class PurchaseCandidateReminderScheduler implements InitializingBean{

 private  Logger logger;

 private  TaskScheduler taskScheduler;

 private  IViewsRepository viewsRepo;

 private  PurchaseCandidateRepository purchaseCandidateRepo;

 private  IBPartnerBL bpartnersService;

 private  NotificationGroupName NOTIFICATION_GROUP_NAME;

 private  String MSG_PurchaseCandidatesDue;

 private  RemindersQueue reminders;

 private  NextDispatch nextDispatch;

 private  SortedSet<PurchaseCandidateReminder> reminders;

 private Runnable task;

 private ScheduledFuture<?> scheduledFuture;

 private ZonedDateTime notificationTime;


public boolean add(PurchaseCandidateReminder reminder){
    return reminders.add(reminder);
}


public void cancel(){
    final boolean canceled = scheduledFuture.cancel(false);
    logger.trace("Cancel requested for {} (result was: {})", this, canceled);
}


public IView createPurchaseCandidatesView(PurchaseCandidateReminder reminder){
    final IView view = viewsRepo.createView(CreateViewRequest.builder(getWindowId(I_C_PurchaseCandidate.Table_Name)).addStickyFilters(createViewStickyFilter(reminder)).applySecurityRestrictions(// don't apply security restrictions because at this point the AD_Client_ID and AD_Org_ID are ZERO
    false).build());
    if (view.size() <= 0) {
        viewsRepo.closeView(view.getViewId(), ViewCloseAction.CANCEL);
        return null;
    } else {
        return view;
    }
}


public List<PurchaseCandidateReminder> getReminders(){
    return reminders.toList();
}


public void scheduleNextDispatch(){
    final ZonedDateTime minNotificationTime = reminders.getMinNotificationTime();
    if (minNotificationTime == null) {
        return;
    }
    if (nextDispatch == null) {
        nextDispatch = NextDispatch.schedule(this::dispatchNotificationsAndReschedule, minNotificationTime, taskScheduler);
    } else {
        nextDispatch = nextDispatch.rescheduleIfAfter(minNotificationTime, taskScheduler);
    }
}


public void scheduleNotification(PurchaseCandidateReminder reminder){
    if (!reminders.add(reminder)) {
        return;
    }
    scheduleNextDispatch();
}


public void dispatchNotificationsAndReschedule(){
    try {
        final List<PurchaseCandidateReminder> remindersToDispatch = removeAllRemindersUntil(ZonedDateTime.now());
        remindersToDispatch.forEach(this::dispatchNotificationNoFail);
    } finally {
        scheduleNextDispatch();
    }
}


public List<PurchaseCandidateReminder> toList(){
    return ImmutableList.copyOf(reminders);
}


public void dispatchNotificationNoFail(PurchaseCandidateReminder reminder){
    try {
        dispatchNotification(reminder);
    } catch (final Exception ex) {
        logger.warn("Failed dispatching notifications for {}. Skipped", reminder, ex);
    }
}


public void dispatchNotification(PurchaseCandidateReminder reminder){
    final IView purchaseCandidatesView = createPurchaseCandidatesView(reminder);
    if (purchaseCandidatesView == null) {
        logger.trace("No records found for {}. Nothing to notify.", reminder);
        return;
    }
    final ViewId viewId = purchaseCandidatesView.getViewId();
    final long count = purchaseCandidatesView.size();
    final UserNotificationRequest notification = UserNotificationRequest.builder().notificationGroupName(NOTIFICATION_GROUP_NAME).recipient(Recipient.allRolesContainingGroup(NOTIFICATION_GROUP_NAME)).contentADMessage(MSG_PurchaseCandidatesDue).contentADMessageParam(count).contentADMessageParam(TableRecordReference.of(I_C_BPartner.Table_Name, reminder.getVendorBPartnerId().getRepoId())).targetAction(TargetViewAction.builder().adWindowId(viewId.getWindowId().toIntOr(-1)).viewId(viewId.toJson()).build()).build();
    Services.get(INotificationBL.class).send(notification);
}


public List<PurchaseCandidateReminder> removeAllRemindersUntil(ZonedDateTime maxNotificationTime){
    return reminders.removeAllUntil(maxNotificationTime);
}


public NextDispatch schedule(Runnable task,ZonedDateTime date,TaskScheduler taskScheduler){
    final ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(task, TimeUtil.asDate(date));
    return builder().task(task).scheduledFuture(scheduledFuture).notificationTime(date).build();
}


public DocumentFilter createViewStickyFilter(PurchaseCandidateReminder reminder){
    final BPartnerId vendorBPartnerId = reminder.getVendorBPartnerId();
    final String vendorName = bpartnersService.getBPartnerValueAndName(vendorBPartnerId);
    final ZonedDateTime notificationTime = reminder.getNotificationTime();
    final ITranslatableString caption = TranslatableStrings.join(" / ", vendorName, TranslatableStrings.dateAndTime(notificationTime));
    return DocumentFilter.builder().setFilterId("filterByVendorIdAndReminderDate").setCaption(caption).addParameter(DocumentFilterParam.ofNameOperatorValue(I_C_PurchaseCandidate.COLUMNNAME_Vendor_ID, Operator.EQUAL, vendorBPartnerId.getRepoId())).addParameter(DocumentFilterParam.ofNameOperatorValue(I_C_PurchaseCandidate.COLUMNNAME_ReminderDate, Operator.LESS_OR_EQUAL, notificationTime)).build();
}


public ZonedDateTime getMinNotificationTime(){
    if (reminders.isEmpty()) {
        return null;
    }
    return reminders.first().getNotificationTime();
}


@Override
public void afterPropertiesSet(){
    initialize();
}


public List<PurchaseCandidateReminder> removeAllUntil(ZonedDateTime maxNotificationTime){
    final List<PurchaseCandidateReminder> result = new ArrayList<>();
    for (final Iterator<PurchaseCandidateReminder> it = reminders.iterator(); it.hasNext(); ) {
        final PurchaseCandidateReminder reminder = it.next();
        final ZonedDateTime notificationTime = reminder.getNotificationTime();
        if (notificationTime.compareTo(maxNotificationTime) <= 0) {
            it.remove();
            result.add(reminder);
        }
    }
    return result;
}


public NextDispatch rescheduleIfAfter(ZonedDateTime date,TaskScheduler taskScheduler){
    if (!notificationTime.isAfter(date) && !scheduledFuture.isDone()) {
        logger.trace("Skip rescheduling {} because it's not after {}", date);
        return this;
    }
    cancel();
    final ScheduledFuture<?> nextScheduledFuture = taskScheduler.schedule(task, TimeUtil.asTimestamp(date));
    NextDispatch nextDispatch = NextDispatch.builder().task(task).scheduledFuture(nextScheduledFuture).notificationTime(date).build();
    logger.trace("Rescheduled {} to {}", this, nextDispatch);
    return nextDispatch;
}


public void initialize(){
    final Set<PurchaseCandidateReminder> reminders = purchaseCandidateRepo.retrieveReminders();
    this.reminders.setReminders(reminders);
    if (nextDispatch != null) {
        nextDispatch.cancel();
        nextDispatch = null;
    }
    scheduleNextDispatch();
}


public ZonedDateTime getNextDispatchTime(){
    return nextDispatch != null ? nextDispatch.getNotificationTime() : null;
}


public void setReminders(Collection<PurchaseCandidateReminder> reminders){
    this.reminders.clear();
    this.reminders.addAll(reminders);
}


public WindowId getWindowId(String tableName){
    final AdWindowId adWindowId = RecordZoomWindowFinder.findAdWindowId(tableName).get();
    return WindowId.of(adWindowId);
}


}