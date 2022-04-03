package de.metas.purchasecandidate.reminder.PurchaseCandidateReminderScheduler;
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
@lombok.Value
@lombok.Builder
public class NextDispatch {

 private Runnable task;

 private ScheduledFuture<?> scheduledFuture;

 private ZonedDateTime notificationTime;


public void cancel(){
    final boolean canceled = scheduledFuture.cancel(false);
    logger.trace("Cancel requested for {} (result was: {})", this, canceled);
}


public NextDispatch schedule(Runnable task,ZonedDateTime date,TaskScheduler taskScheduler){
    final ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(task, TimeUtil.asDate(date));
    return builder().task(task).scheduledFuture(scheduledFuture).notificationTime(date).build();
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


}