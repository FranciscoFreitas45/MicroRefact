package de.metas.ui.web.debug;
 import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.adempiere.ad.dao.IQueryStatisticsLogger;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import ch.qos.logback.classic.Level;
import de.metas.cache.CacheMgt;
import de.metas.event.Topic;
import de.metas.event.Type;
import de.metas.logging.LogManager;
import de.metas.notification.INotificationBL;
import de.metas.notification.UserNotificationRequest;
import de.metas.notification.UserNotificationRequest.TargetRecordAction;
import de.metas.notification.UserNotificationRequest.UserNotificationRequestBuilder;
import de.metas.notification.UserNotificationTargetType;
import de.metas.security.IUserRolePermissionsDAO;
import de.metas.ui.web.base.model.I_T_WEBUI_ViewSelection;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.debug.JSONCacheResetResult.JSONCacheResetResultBuilder;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.menu.MenuTreeRepository;
import de.metas.ui.web.process.ProcessRestController;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.SqlViewFactory;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.ViewResult;
import de.metas.ui.web.view.ViewRowOverridesHelper;
import de.metas.ui.web.view.descriptor.annotation.ViewColumnHelper;
import de.metas.ui.web.view.event.JSONViewChanges;
import de.metas.ui.web.view.event.ViewChanges;
import de.metas.ui.web.view.json.JSONViewResult;
import de.metas.ui.web.websocket.WebSocketConfig;
import de.metas.ui.web.websocket.WebsocketEventLogRecord;
import de.metas.ui.web.websocket.WebsocketSender;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.user.UserId;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized") })
@RestController
@RequestMapping(value = DebugRestController.ENDPOINT)
public class DebugRestController {

 public  String ENDPOINT;

@Autowired
 private  UserSession userSession;

@Autowired
 private  DocumentCollection documentCollection;

@Autowired
 private  MenuTreeRepository menuTreeRepo;

@Autowired
@Lazy
 private  IViewsRepository viewsRepo;

@Autowired
@Lazy
 private  SqlViewFactory sqlViewFactory;

@Autowired
@Lazy
 private  ProcessRestController processesController;

@Autowired
@Lazy
 private  IQueryStatisticsLogger statisticsLogger;

@Autowired
@Lazy
 private  WebsocketSender websocketSender;

@Autowired
@Lazy
 private  ObjectMapper sharedJsonObjectMapper;

 private  Set<String> loggerNames;


public JSONOptions newJSONOptions(){
    return JSONOptions.of(userSession);
}


public Set<String> getLoggerNames(){
    return loggerNames;
}


@GetMapping("/changeJsonEngineCofiguration")
public void changeJsonEngineConfiguration(Boolean failOnUnknownProperties){
    if (failOnUnknownProperties != null) {
        sharedJsonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknownProperties);
    }
}


@RequestMapping(value = "/disableDeprecatedRestAPI", method = RequestMethod.GET)
public boolean isAllowDeprecatedRestAPI(){
    userSession.assertLoggedIn();
    return userSession.isAllowDeprecatedRestAPI();
}


@RequestMapping(value = "/views/list", method = RequestMethod.GET)
public List<JSONViewResult> getViewsList(){
    userSession.assertLoggedIn();
    final JSONOptions jsonOpts = newJSONOptions();
    return viewsRepo.getViews().stream().map(ViewResult::ofView).map(viewResult -> JSONViewResult.of(viewResult, ViewRowOverridesHelper.NULL, jsonOpts)).collect(GuavaCollectors.toImmutableList());
}


@PostMapping("/websocket/post")
public void postToWebsocket(String endpoint,String messageStr){
    userSession.assertLoggedIn();
    final Charset charset = Charset.forName("UTF-8");
    final Map<String, Object> headers = ImmutableMap.<String, Object>builder().put("simpMessageType", SimpMessageType.MESSAGE).put("contentType", new MimeType("application", "json", charset)).build();
    final Message<?> message = new GenericMessage<>(messageStr.getBytes(charset), headers);
    websocketSender.sendMessage(endpoint, message);
}


public void sendWebsocketViewChangedNotification(ViewId viewId,DocumentIdsSelection changedRowIds){
    userSession.assertLoggedIn();
    final ViewChanges viewChanges = new ViewChanges(viewId);
    viewChanges.addChangedRowIds(changedRowIds);
    JSONViewChanges jsonViewChanges = JSONViewChanges.of(viewChanges);
    final String endpoint = WebSocketConfig.buildViewNotificationsTopicName(jsonViewChanges.getViewId());
    try {
        websocketSender.convertAndSend(endpoint, jsonViewChanges);
    } catch (final Exception ex) {
        throw AdempiereException.wrapIfNeeded(ex).appendParametersToMessage().setParameter("json", jsonViewChanges);
    }
}


@GetMapping("http.cache.maxAge")
public Map<String,Object> setHttpCacheMaxAge(int httpCacheMaxAge){
    userSession.assertLoggedIn();
    final long httpCacheMaxAgeOld = userSession.getHttpCacheMaxAge();
    userSession.setHttpCacheMaxAge(httpCacheMaxAge);
    return ImmutableMap.of("value", httpCacheMaxAge, "valueOld", httpCacheMaxAgeOld);
}


@PostMapping("/view/{viewId}/deleteRows")
public String viewDeleteRowIds(String viewIdStr,String rowIdsStr){
    userSession.assertLoggedIn();
    final ViewId viewId = ViewId.ofViewIdString(viewIdStr);
    final DocumentIdsSelection rowIds = DocumentIdsSelection.ofCommaSeparatedString(rowIdsStr);
    // 
    // Delete from DB selection
    String sql = "DELETE FROM " + I_T_WEBUI_ViewSelection.Table_Name + " WHERE " + I_T_WEBUI_ViewSelection.COLUMNNAME_UUID + "=" + DB.TO_STRING(viewId.getViewId()) + " AND " + I_T_WEBUI_ViewSelection.COLUMNNAME_IntKey1 + "=" + DB.buildSqlList(rowIds.toIntSet());
    final int countDeleted = DB.executeUpdateEx(sql, ITrx.TRXNAME_None);
    // 
    // Clear view's cache
    final IView view = viewsRepo.getView(viewId);
    rowIds.forEach(view::invalidateRowById);
    // 
    // Notify
    sendWebsocketViewChangedNotification(viewId, rowIds);
    return "Deleted " + countDeleted + " rows";
}


@RequestMapping(value = "/debugProtocol", method = RequestMethod.GET)
public void setDebugProtocol(boolean enabled){
    userSession.assertLoggedIn();
    WindowConstants.setProtocolDebugging(enabled);
}


@GetMapping("http.use.AcceptLanguage")
public Map<String,Object> setUseHttpAcceptLanguage(boolean useHttpAcceptLanguage){
    userSession.assertLoggedIn();
    final boolean useHttpAcceptLanguageOld = userSession.isUseHttpAcceptLanguage();
    userSession.setUseHttpAcceptLanguage(useHttpAcceptLanguage);
    return ImmutableMap.of("value", useHttpAcceptLanguage, "valueOld", useHttpAcceptLanguageOld);
}


@RequestMapping(value = "/eventBus/postEvent", method = RequestMethod.GET)
public void postEvent(String topicName,String message,int toUserId,boolean important,String targetTypeStr,String targetDocumentType,String targetDocumentId){
    userSession.assertLoggedIn();
    final Topic topic = Topic.builder().name(topicName).type(Type.LOCAL).build();
    final UserNotificationRequestBuilder request = UserNotificationRequest.builder().topic(topic).recipientUserId(UserId.ofRepoIdOrNull(toUserId)).important(important);
    final UserNotificationTargetType targetType = Check.isEmpty(targetTypeStr) ? null : UserNotificationTargetType.forJsonValue(targetTypeStr);
    if (targetType == UserNotificationTargetType.Window) {
        final String targetTableName = documentCollection.getDocumentDescriptorFactory().getDocumentDescriptor(WindowId.fromJson(targetDocumentType)).getEntityDescriptor().getTableName();
        final TableRecordReference targetRecord = TableRecordReference.of(targetTableName, Integer.parseInt(targetDocumentId));
        request.targetAction(TargetRecordAction.of(targetRecord));
    }
    Services.get(INotificationBL.class).send(request.build());
}


@ApiResponses(value = { @ApiResponse(code = 200, message = "cache reset done") })
@RequestMapping(value = "/cacheReset", method = RequestMethod.GET)
public JSONCacheResetResult cacheReset(boolean forgetNotSavedDocuments){
    userSession.assertLoggedIn();
    final JSONCacheResetResultBuilder result = JSONCacheResetResult.builder();
    // 
    {
        final long count = CacheMgt.get().reset();
        result.log("CacheMgt: invalidate " + count + " items");
    }
    // 
    {
        final String documentsResult = documentCollection.cacheReset(forgetNotSavedDocuments);
        result.log("documents: " + documentsResult);
    }
    // 
    {
        menuTreeRepo.cacheReset();
        result.log("menuTreeRepo: cache invalidated");
    }
    // 
    {
        processesController.cacheReset();
        result.log("processesController: cache invalidated");
    }
    // 
    {
        ViewColumnHelper.cacheReset();
        result.log("viewColumnHelper: cache invalidated");
    }
    // 
    {
        Services.get(IUserRolePermissionsDAO.class).resetLocalCache();
        result.log("user/role permissions: cache invalidated");
    }
    // 
    {
        System.gc();
        result.log("system: garbage collected");
    }
    return result.build();
}


@RequestMapping(value = "/showColumnNamesForCaption", method = RequestMethod.PUT)
public void setShowColumnNamesForCaption(String showColumnNamesForCaptionStr){
    userSession.assertLoggedIn();
    final Boolean showColumnNamesForCaption = DisplayType.toBoolean(showColumnNamesForCaptionStr, null);
    if (showColumnNamesForCaption == null) {
        throw new AdempiereException("Invalid boolean value: `" + showColumnNamesForCaptionStr + "`");
    }
    userSession.setShowColumnNamesForCaption(showColumnNamesForCaption);
    final boolean forgetNotSavedDocuments = true;
    cacheReset(forgetNotSavedDocuments);
}


@PostMapping("/viewDefaultProfile/{windowId}")
public void setDefaultViewProfile(String windowIdStr,String profileIdStr){
    userSession.assertLoggedIn();
    sqlViewFactory.setDefaultProfileId(WindowId.fromJson(windowIdStr), ViewProfileId.fromJson(profileIdStr));
}


@GetMapping("/logger/{loggerName}/_getUpToRoot")
public List<Map<String,Object>> getLoggersUpToRoot(String loggerName){
    userSession.assertLoggedIn();
    final Logger logger = LogManager.getLogger(loggerName);
    if (logger == null) {
        throw new EntityNotFoundException("No logger found for " + loggerName);
    }
    final List<Map<String, Object>> loggerInfos = new ArrayList<>();
    // 
    LogManager.forAllLevelsUpToRoot(logger, currentLogger -> {
        final Map<String, Object> info = new HashMap<>();
        info.put("name", currentLogger.getName());
        info.put("id", System.identityHashCode(currentLogger));
        if (currentLogger instanceof ch.qos.logback.classic.Logger) {
            final ch.qos.logback.classic.Logger logbackLogger = (ch.qos.logback.classic.Logger) currentLogger;
            final Level level = logbackLogger.getLevel();
            final Level effectiveLevel = logbackLogger.getEffectiveLevel();
            info.put("level", level == null ? null : level.toString());
            info.put("level-effective", effectiveLevel == null ? null : effectiveLevel.toString());
        } else {
            info.put("warning", "unknown level for logger object " + currentLogger + " (" + currentLogger.getClass() + ")");
        }
        loggerInfos.add(info);
    });
    // 
    return loggerInfos;
}


@RequestMapping(value = "/traceSqlQueries", method = RequestMethod.GET)
public void setTraceSqlQueries(boolean enabled){
    userSession.assertLoggedIn();
    if (enabled) {
        statisticsLogger.enableWithSqlTracing();
    } else {
        statisticsLogger.disable();
    }
}


@GetMapping("websocketEvents")
public List<WebsocketEventLogRecord> getWebsocketLoggedEvents(String destinationFilter){
    userSession.assertLoggedIn();
    return websocketSender.getLoggedEvents(destinationFilter);
}


@RequestMapping(value = "/allowDeprecatedRestAPI", method = RequestMethod.PUT)
public void setAllowDeprecatedRestAPI(String allowDeprecatedRestAPI){
    userSession.assertLoggedIn();
    userSession.setAllowDeprecatedRestAPI(DisplayType.toBoolean(allowDeprecatedRestAPI));
}


@RequestMapping(value = "/lookups/cacheStats", method = RequestMethod.GET)
public List<String> getLookupCacheStats(){
    userSession.assertLoggedIn();
    return LookupDataSourceFactory.instance.getCacheStats().stream().map(stats -> stats.toString()).collect(GuavaCollectors.toImmutableList());
}


@GetMapping("/conext")
public Map<String,String> getContext(){
    userSession.assertLoggedIn();
    final LinkedHashMap<String, String> map = new LinkedHashMap<>();
    final Properties ctx = Env.getCtx();
    final ArrayList<String> keys = new ArrayList<>(ctx.stringPropertyNames());
    Collections.sort(keys);
    for (final String key : keys) {
        final String value = ctx.getProperty(key);
        map.put(key, value);
    }
    return map;
}


@GetMapping("websocketLogging")
public void setWebsocketLoggingConfig(boolean enabled,int maxLoggedEvents){
    userSession.assertLoggedIn();
    websocketSender.setLogEventsEnabled(enabled);
    websocketSender.setLogEventsMaxSize(maxLoggedEvents);
}


@GetMapping("/logger/_setLevel/{level}")
public Set<String> setLoggerLevel(LoggingModule module,String loggerName,String levelStr){
    userSession.assertLoggedIn();
    // 
    // Get Level to set
    final Level level;
    if (Check.isEmpty(levelStr, true)) {
        level = null;
    } else {
        level = LogManager.asLogbackLevel(levelStr);
        if (level == null) {
            throw new IllegalArgumentException("level is not valid");
        }
    }
    // 
    // Get logger names
    final Set<String> loggerNamesEffective = new LinkedHashSet<>();
    if (module != null) {
        loggerNamesEffective.addAll(module.getLoggerNames());
    }
    if (!Check.isEmpty(loggerName, true)) {
        loggerNamesEffective.add(loggerName.trim());
    }
    // 
    // Set level to effective logger names
    for (final String loggerNameEffective : loggerNamesEffective) {
        final Logger logger = LogManager.getLogger(loggerNameEffective);
        if (logger == null) {
            throw new EntityNotFoundException("No logger found for " + loggerNameEffective);
        }
        final boolean set = LogManager.setLoggerLevel(logger, level);
        if (!set) {
            throw new IllegalStateException("For some reason " + logger + " could not be set to level " + level);
        }
    }
    return loggerNamesEffective;
}


}