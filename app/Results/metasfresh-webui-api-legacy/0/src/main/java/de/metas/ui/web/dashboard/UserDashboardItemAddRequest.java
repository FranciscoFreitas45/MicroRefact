package de.metas.ui.web.dashboard;
 import de.metas.ui.web.dashboard.json.JsonUserDashboardItemAddRequest;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;
@Builder
@Value
public class UserDashboardItemAddRequest {

 private  DashboardWidgetType widgetType;

 private  int kpiId;

@Default
 private  int position;

 private  UserDashboardItemChangeRequest changeRequest;


public UserDashboardItemAddRequest of(JsonUserDashboardItemAddRequest json,DashboardWidgetType widgetType,String adLanguage){
    final UserDashboardItemChangeRequest changeRequest = UserDashboardItemChangeRequest.builder().itemId(// new
    0).widgetType(widgetType).adLanguage(adLanguage).caption(json.getCaption()).interval(json.getInterval()).when(json.getWhen()).build();
    return UserDashboardItemAddRequest.builder().widgetType(widgetType).kpiId(json.getKpiId()).position(json.getPosition()).changeRequest(!changeRequest.isEmpty() ? changeRequest : null).build();
}


}