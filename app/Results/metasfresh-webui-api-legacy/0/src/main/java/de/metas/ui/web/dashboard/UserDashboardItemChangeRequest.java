package de.metas.ui.web.dashboard;
 import java.util.Arrays;
import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.dashboard.UserDashboardRepository.DashboardItemPatchPath;
import de.metas.ui.web.dashboard.json.JsonUserDashboardItemAddRequest;
import de.metas.ui.web.dashboard.json.JsonUserDashboardItemAddRequest.JSONInterval;
import de.metas.ui.web.dashboard.json.JsonUserDashboardItemAddRequest.JSONWhen;
import de.metas.ui.web.window.datatypes.json.JSONPatchEvent;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Builder
@Value
public class UserDashboardItemChangeRequest {

 private  int itemId;

@NonNull
 private  DashboardWidgetType widgetType;

@NonNull
 private  String adLanguage;

 private  String caption;

 private  JSONInterval interval;

 private  JSONWhen when;

 private  int position;


public UserDashboardItemChangeRequest of(DashboardWidgetType widgetType,int itemId,String adLanguage,List<JSONPatchEvent<DashboardItemPatchPath>> events){
    if (events.isEmpty()) {
        throw new AdempiereException("no events");
    }
    final UserDashboardItemChangeRequest.UserDashboardItemChangeRequestBuilder changeRequestBuilder = UserDashboardItemChangeRequest.builder().itemId(itemId).widgetType(widgetType).adLanguage(adLanguage);
    // 
    // Extract change actions
    for (final JSONPatchEvent<DashboardItemPatchPath> event : events) {
        if (!event.isReplace()) {
            throw new AdempiereException("Invalid event operation").setParameter("event", event);
        }
        final DashboardItemPatchPath path = event.getPath();
        if (DashboardItemPatchPath.caption.equals(path)) {
            final String caption = event.getValueAsString();
            changeRequestBuilder.caption(caption);
        } else if (DashboardItemPatchPath.interval.equals(path)) {
            final JSONInterval interval = event.getValueAsEnum(JsonUserDashboardItemAddRequest.JSONInterval.class);
            changeRequestBuilder.interval(interval);
        } else if (DashboardItemPatchPath.when.equals(path)) {
            final JSONWhen when = event.getValueAsEnum(JsonUserDashboardItemAddRequest.JSONWhen.class);
            changeRequestBuilder.when(when);
        } else if (DashboardItemPatchPath.position.equals(path)) {
            final int position = event.getValueAsInteger(-1);
            changeRequestBuilder.position(position);
        } else {
            throw new AdempiereException("Unknown path").setParameter("event", event).setParameter("availablePaths", Arrays.asList(DashboardItemPatchPath.values()));
        }
    }
    final UserDashboardItemChangeRequest changeRequest = changeRequestBuilder.build();
    if (changeRequest.isEmpty()) {
        throw new AdempiereException("no changes to perform").setParameter("events", events);
    }
    return changeRequest;
}


public boolean isEmpty(){
    return Check.isEmpty(caption, true) && interval == null && when == null && position < 0;
}


}