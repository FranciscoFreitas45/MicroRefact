package de.metas.ui.web.dashboard;
 import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.ImmutableList;
import de.metas.elasticsearch.IESSystem;
import de.metas.logging.LogManager;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.dashboard.UserDashboardRepository.DashboardItemPatchPath;
import de.metas.ui.web.dashboard.UserDashboardRepository.UserDashboardKey;
import de.metas.ui.web.dashboard.json.JSONDashboard;
import de.metas.ui.web.dashboard.json.JSONDashboardChangedEventsList;
import de.metas.ui.web.dashboard.json.JSONDashboardChangedEventsList.JSONDashboardChangedEventsListBuilder;
import de.metas.ui.web.dashboard.json.JSONDashboardItem;
import de.metas.ui.web.dashboard.json.JSONDashboardItemChangedEvent;
import de.metas.ui.web.dashboard.json.JSONDashboardOrderChangedEvent;
import de.metas.ui.web.dashboard.json.JsonKPI;
import de.metas.ui.web.dashboard.json.JsonUserDashboardItemAddRequest;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.websocket.WebsocketSender;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.datatypes.json.JSONPatchEvent;
import de.metas.util.Services;
import io.swagger.annotations.ApiParam;
@RestController
@RequestMapping(value = DashboardRestController.ENDPOINT)
public class DashboardRestController {

 public  String ENDPOINT;

 private  Logger logger;

@Autowired
 private  UserSession userSession;

@Autowired
 private  UserDashboardRepository userDashboardRepo;

@Autowired
 private  Client elasticsearchClient;

@Autowired
 private  WebsocketSender websocketSender;


public JSONOptions newJSONOpts(){
    return JSONOptions.of(userSession);
}


public boolean isElasticSearchEnabled(){
    return Services.get(IESSystem.class).isEnabled();
}


@GetMapping("/targetIndicators")
public JSONDashboard getTargetIndicatorsDashboard(){
    return getJSONDashboard(DashboardWidgetType.TargetIndicator);
}


public KPIDataResult getKPIData(DashboardWidgetType widgetType,int itemId,long fromMillis,long toMillis,boolean prettyValues){
    userSession.assertLoggedIn();
    final UserDashboardItem dashboardItem = getUserDashboardForReading().getItemById(widgetType, itemId);
    final KPI kpi = dashboardItem.getKPI();
    final TimeRange timeRange = dashboardItem.getTimeRangeDefaults().createTimeRange(fromMillis, toMillis);
    final JSONOptions jsonOptions = JSONOptions.of(userSession);
    return KPIDataLoader.newInstance(elasticsearchClient, kpi, jsonOptions).setTimeRange(timeRange).setFormatValues(prettyValues).retrieveData().setItemId(dashboardItem.getId());
}


public UserDashboard getUserDashboardForWriting(){
    if (!isElasticSearchEnabled()) {
        return UserDashboard.EMPTY;
    }
    final UserDashboard dashboard = userDashboardRepo.getUserDashboard(UserDashboardKey.of(userSession.getClientId()));
    // TODO: assert writable by current user
    return dashboard;
}


public JSONDashboardItem addDashboardItem(JsonUserDashboardItemAddRequest jsonRequest,DashboardWidgetType widgetType){
    userSession.assertLoggedIn();
    final UserDashboardItemAddRequest request = UserDashboardItemAddRequest.of(jsonRequest, widgetType, userSession.getAD_Language());
    final int itemId = userDashboardRepo.addUserDashboardItem(getUserDashboardForWriting(), request);
    // 
    // Notify on websocket
    final UserDashboard dashboard = getUserDashboardForReading();
    sendEvents(dashboard, JSONDashboardChangedEventsList.builder().event(JSONDashboardOrderChangedEvent.of(dashboard.getId(), widgetType, dashboard.getItemIds(widgetType))).build());
    // Return newly created item
    final UserDashboardItem targetIndicatorItem = dashboard.getItemById(widgetType, itemId);
    return JSONDashboardItem.of(targetIndicatorItem, newJSONLayoutOptions());
}


@PatchMapping("/kpis/{itemId}")
public JSONDashboardItem changeKPIItem(int itemId,List<JSONPatchEvent<DashboardItemPatchPath>> events){
    return changeDashboardItem(DashboardWidgetType.KPI, itemId, events);
}


public void deleteDashboardItem(DashboardWidgetType widgetType,int itemId){
    userSession.assertLoggedIn();
    userDashboardRepo.deleteUserDashboardItem(getUserDashboardForWriting(), widgetType, itemId);
    // 
    // Notify on websocket
    final UserDashboard dashboard = getUserDashboardForReading();
    sendEvents(dashboard, JSONDashboardChangedEventsList.builder().event(JSONDashboardOrderChangedEvent.of(dashboard.getId(), widgetType, dashboard.getItemIds(widgetType))).build());
}


public UserDashboard getUserDashboardForReading(){
    if (!isElasticSearchEnabled()) {
        return UserDashboard.EMPTY;
    }
    final UserDashboard dashboard = userDashboardRepo.getUserDashboard(UserDashboardKey.of(userSession.getClientId()));
    // TODO: assert readable by current user
    return dashboard;
}


@GetMapping("/targetIndicators/{itemId}/data")
public KPIDataResult getTargetIndicatorData(int itemId,long fromMillis,long toMillis,boolean prettyValues){
    return getKPIData(DashboardWidgetType.TargetIndicator, itemId, fromMillis, toMillis, prettyValues);
}


@GetMapping("/kpis/available")
public List<JsonKPI> getKPIsAvailableToAdd(int firstRow,int pageLength){
    userSession.assertLoggedIn();
    final Collection<KPI> kpis = userDashboardRepo.getKPIsAvailableToAdd();
    final JSONOptions jsonOpts = newJSONOpts();
    return kpis.stream().map(kpi -> JsonKPI.of(kpi, jsonOpts)).sorted(Comparator.comparing(JsonKPI::getCaption)).skip(firstRow >= 0 ? firstRow : 0).limit(pageLength > 0 ? pageLength : Integer.MAX_VALUE).collect(ImmutableList.toImmutableList());
}


@PostMapping("/kpis/new")
public JSONDashboardItem addKPIItem(JsonUserDashboardItemAddRequest jsonRequest){
    return addDashboardItem(jsonRequest, DashboardWidgetType.KPI);
}


@GetMapping("/kpis")
public JSONDashboard getKPIsDashboard(){
    return getJSONDashboard(DashboardWidgetType.KPI);
}


public JSONDocumentLayoutOptions newJSONLayoutOptions(){
    return JSONDocumentLayoutOptions.of(userSession);
}


public void sendEvents(UserDashboard dashboard,JSONDashboardChangedEventsList events){
    if (events.isEmpty()) {
        return;
    }
    final String websocketEndpoint = dashboard.getWebsocketEndpoint();
    websocketSender.convertAndSend(websocketEndpoint, events);
    logger.trace("Notified WS {}: {}", websocketEndpoint, events);
}


@DeleteMapping("/targetIndicators/{itemId}")
public void deleteTargetIndicatorItem(int itemId){
    deleteDashboardItem(DashboardWidgetType.TargetIndicator, itemId);
}


@PostMapping("/targetIndicators/new")
public JSONDashboardItem addTargetIndicatorItem(JsonUserDashboardItemAddRequest jsonRequest){
    return addDashboardItem(jsonRequest, DashboardWidgetType.TargetIndicator);
}


@DeleteMapping("/kpis/{itemId}")
public void deleteKPIItem(int itemId){
    deleteDashboardItem(DashboardWidgetType.KPI, itemId);
}


@PatchMapping("/targetIndicators/{itemId}")
public JSONDashboardItem changeTargetIndicatorItem(int itemId,List<JSONPatchEvent<DashboardItemPatchPath>> events){
    return changeDashboardItem(DashboardWidgetType.TargetIndicator, itemId, events);
}


public JSONDashboard getJSONDashboard(DashboardWidgetType widgetType){
    userSession.assertLoggedIn();
    final UserDashboard userDashboard = getUserDashboardForReading();
    return JSONDashboard.of(userDashboard.getItems(widgetType), userDashboard.getWebsocketEndpoint(), newJSONLayoutOptions());
}


public JSONDashboardItem changeDashboardItem(DashboardWidgetType widgetType,int itemId,List<JSONPatchEvent<DashboardItemPatchPath>> events){
    userSession.assertLoggedIn();
    // 
    // Chage the dashboard item
    final UserDashboardItemChangeRequest request = UserDashboardItemChangeRequest.of(widgetType, itemId, userSession.getAD_Language(), events);
    final UserDashboardItemChangeResult changeResult = userDashboardRepo.changeUserDashboardItem(getUserDashboardForWriting(), request);
    // 
    // Notify on websocket
    final UserDashboard dashboard = getUserDashboardForReading();
    {
        final JSONDashboardChangedEventsListBuilder eventBuilder = JSONDashboardChangedEventsList.builder().event(JSONDashboardItemChangedEvent.of(changeResult.getDashboardId(), changeResult.getItemId()));
        if (changeResult.isPositionChanged()) {
            eventBuilder.event(JSONDashboardOrderChangedEvent.of(changeResult.getDashboardId(), changeResult.getDashboardWidgetType(), changeResult.getDashboardOrderedItemIds()));
        }
        sendEvents(dashboard, eventBuilder.build());
    }
    // Return the changed item
    {
        final UserDashboardItem item = dashboard.getItemById(widgetType, itemId);
        return JSONDashboardItem.of(item, newJSONLayoutOptions());
    }
}


}