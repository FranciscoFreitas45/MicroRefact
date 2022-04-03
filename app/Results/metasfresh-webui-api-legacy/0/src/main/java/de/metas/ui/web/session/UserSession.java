package de.metas.ui.web.session;
 import java.time.Duration;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Supplier;
import org.adempiere.service.ClientId;
import org.adempiere.service.ISysConfigBL;
import org.compiere.SpringContextHolder;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluatees;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import de.metas.i18n.Language;
import de.metas.logging.LogManager;
import de.metas.organization.IOrgDAO;
import de.metas.organization.OrgId;
import de.metas.organization.OrgInfo;
import de.metas.security.IUserRolePermissions;
import de.metas.security.RoleId;
import de.metas.security.UserRolePermissionsKey;
import de.metas.ui.web.base.session.UserPreference;
import de.metas.ui.web.exceptions.DeprecatedRestAPINotAllowedException;
import de.metas.ui.web.login.exceptions.AlreadyLoggedInException;
import de.metas.ui.web.login.exceptions.NotLoggedInAsSysAdminException;
import de.metas.ui.web.login.exceptions.NotLoggedInException;
import de.metas.ui.web.websocket.WebSocketConfig;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.user.UserId;
import de.metas.util.Check;
import de.metas.util.Services;
import de.metas.util.time.SystemTime;
import lombok.NonNull;
@Service
public class UserSession {

 static  Logger logger;

 private  ApplicationEventPublisher eventPublisher;

 private  UserSession _staticUserSession;

@Autowired
 private  InternalUserSessionData _data;

 private  String SYSCONFIG_DefaultLookupSearchStartDelayMillis;

@NonNull
 private  String adLanguage;

 private  UserId adUserId;


public Optional<UserId> getLoggedUserIdIfExists(){
    return getData().getLoggedUserIdIfExists();
}


public boolean isWebuiThread(){
    return RequestContextHolder.getRequestAttributes() != null;
}


public String setAD_Language(String adLanguage){
    Check.assumeNotEmpty(adLanguage, "adLanguage is not empty");
    final Language lang = Language.getLanguage(adLanguage);
    final InternalUserSessionData data = getData();
    final String adLanguageOld = data.verifyLanguageAndSet(lang);
    final String adLanguageNew = data.getAdLanguage();
    logger.info("Changed AD_Language: {} -> {}, {}", adLanguageOld, adLanguageNew, lang);
    // Fire event
    if (!Objects.equals(adLanguageOld, adLanguageNew)) {
        eventPublisher.publishEvent(new LanguagedChangedEvent(adLanguageNew, getLoggedUserId()));
    }
    return adLanguageOld;
}


@NonNull
public ZoneId getTimeZone(){
    final OrgId orgId = getOrgId();
    final OrgInfo orgInfo = Services.get(IOrgDAO.class).getOrgInfoById(orgId);
    if (orgInfo.getTimeZone() != null) {
        return orgInfo.getTimeZone();
    }
    return SystemTime.zoneId();
}


public OrgId getOrgId(){
    return getData().getOrgId();
}


public UserRolePermissionsKey getUserRolePermissionsKey(){
    // TODO: cache the permissions key
    return UserRolePermissionsKey.fromContext(getData().getCtx());
}


public void setShowColumnNamesForCaption(boolean showColumnNamesForCaption){
    final InternalUserSessionData data = getData();
    final boolean showColumnNamesForCaptionOld = data.isShowColumnNamesForCaption();
    data.setShowColumnNamesForCaption(showColumnNamesForCaption);
    logSettingChanged("ShowColumnNamesForCaption", showColumnNamesForCaption, showColumnNamesForCaptionOld);
}


public String getUserName(){
    return getData().getUserName();
}


public IUserRolePermissions getCurrentPermissions(){
    return getCurrent().getUserRolePermissions();
}


public void assertDeprecatedRestAPIAllowed(){
    if (!getData().isAllowDeprecatedRestAPI()) {
        throw new DeprecatedRestAPINotAllowedException();
    }
}


public UserId getLoggedUserId(){
    return getData().getLoggedUserId();
}


public String getAvatarId(){
    return getData().getAvatarId();
}


public void setUseHttpAcceptLanguage(boolean useHttpAcceptLanguage){
    final InternalUserSessionData data = getData();
    final boolean useHttpAcceptLanguageOld = data.isUseHttpAcceptLanguage();
    data.setUseHttpAcceptLanguage(useHttpAcceptLanguage);
    logSettingChanged("UseHttpAcceptLanguage", useHttpAcceptLanguage, useHttpAcceptLanguageOld);
}


public void assertLoggedInAsSysAdmin(){
    assertLoggedIn();
    final RoleId adRoleId = getData().getLoggedRoleId();
    if (!adRoleId.isSystem()) {
        throw new NotLoggedInAsSysAdminException();
    }
}


public void assertNotLoggedIn(){
    if (getData().isLoggedIn()) {
        throw new AlreadyLoggedInException();
    }
}


public boolean isLoggedIn(){
    return getData().isLoggedIn();
}


public void setAllowDeprecatedRestAPI(boolean allowDeprecatedRestAPI){
    final InternalUserSessionData data = getData();
    final boolean allowDeprecatedRestAPIOld = data.isAllowDeprecatedRestAPI();
    data.setAllowDeprecatedRestAPI(allowDeprecatedRestAPI);
    logSettingChanged("AllowDeprecatedRestAPI", allowDeprecatedRestAPI, allowDeprecatedRestAPIOld);
}


public UserSession getCurrentOrNull(){
    // 
    // Quickly check if the session scoped UserSession bean will be really available
    // NOTE: it's not about that the object will be null but if it's method calls will be really working
    if (!isWebuiThread()) {
        return null;
    }
    // 
    UserSession userSession = _staticUserSession;
    if (userSession == null) {
        synchronized (UserSession.class) {
            if (_staticUserSession == null) {
                userSession = _staticUserSession = SpringContextHolder.instance.getBean(UserSession.class);
            }
        }
    }
    return userSession;
}


public UserSession getCurrent(){
    final UserSession userSession = getCurrentOrNull();
    if (userSession == null) {
        throw new NotLoggedInException("no session found");
    }
    return userSession;
}


public int getHttpCacheMaxAge(){
    return getData().getHttpCacheMaxAge();
}


public String getSessionId(){
    return getData().getSessionId();
}


public boolean isShowColumnNamesForCaption(){
    return getData().isShowColumnNamesForCaption();
}


public void assertLoggedIn(){
    if (!getData().isLoggedIn()) {
        throw new NotLoggedInException();
    }
}


public ClientId getClientId(){
    return getData().getClientId();
}


public String setUserEmail(String userEmail){
    final InternalUserSessionData data = getData();
    final String userEmailOld = data.getUserEmail();
    data.setUserEmail(userEmail);
    return userEmailOld;
}


public boolean isAllowDeprecatedRestAPI(){
    return getData().isAllowDeprecatedRestAPI();
}


public String getWebsocketEndpoint(){
    return WebSocketConfig.buildUserSessionTopicName(getLoggedUserId());
}


public void setLoggedIn(boolean loggedIn){
    final InternalUserSessionData data = getData();
    final boolean currentlyLoggedIn = data.isLoggedIn();
    if (currentlyLoggedIn == loggedIn) {
        return;
    }
    if (loggedIn) {
        data.setLoggedIn(true);
        data.getUserPreference().loadPreference(data.getCtx());
        logger.trace("User session logged in: {}", data);
    } else {
        data.setLoggedIn(false);
        data.setUserPreference(new UserPreference());
        logger.trace("User session logged out: {}", data);
    }
}


public String setAvatarId(String avatarId){
    final InternalUserSessionData data = getData();
    final String avatarIdOld = data.getAvatarId();
    data.setAvatarId(avatarId);
    return avatarIdOld;
}


public String setUserFullname(String userFullname){
    final InternalUserSessionData data = getData();
    final String userFullnameOld = data.getUserFullname();
    data.setUserFullname(userFullname);
    return userFullnameOld;
}


public UserSessionLocale getUserSessionLocale(){
    return UserSessionLocale.get(getAD_Language());
}


public Supplier<Duration> getDefaultLookupSearchStartDelay(){
    return () -> {
        final int defaultLookupSearchStartDelayMillis = Services.get(ISysConfigBL.class).getIntValue(SYSCONFIG_DefaultLookupSearchStartDelayMillis, 0);
        return defaultLookupSearchStartDelayMillis > 0 ? Duration.ofMillis(defaultLookupSearchStartDelayMillis) : Duration.ZERO;
    };
}


public InternalUserSessionData getData(){
    _data.initializeIfNeeded();
    return _data;
}


public JSONLookupValue getLanguageAsJson(){
    final Language language = getLanguage();
    return JSONLookupValue.of(language.getAD_Language(), language.getName());
}


public Language getLanguage(){
    return getData().getLanguage();
}


public String getUserEmail(){
    return getData().getUserEmail();
}


public String getRoleName(){
    return getData().getRoleName();
}


public void logSettingChanged(String name,Object value,Object valueOld){
    UserSession.logger.warn("/*********************************************************************************************\\");
    UserSession.logger.warn("Setting changed: {} = {} (Old: {})", name, value, valueOld);
    UserSession.logger.warn("\\*********************************************************************************************/");
}


public boolean isLoggedInAs(UserId userId){
    return isLoggedIn() && UserId.equals(getLoggedUserId(), userId);
}


public void setHttpCacheMaxAge(int httpCacheMaxAge){
    final InternalUserSessionData data = getData();
    final int httpCacheMaxAgeOld = data.getHttpCacheMaxAge();
    data.setHttpCacheMaxAge(httpCacheMaxAge);
    logSettingChanged("HttpCacheMaxAge", httpCacheMaxAge, httpCacheMaxAgeOld);
}


public String getAD_Language(){
    return getData().getAdLanguage();
}


public Evaluatee toEvaluatee(){
    return Evaluatees.ofCtx(getData().getCtx());
}


public UserPreference getUserPreference(){
    return getData().getUserPreference();
}


public IUserRolePermissions getUserRolePermissions(){
    return Env.getUserRolePermissions(getData().getCtx());
}


public boolean isUseHttpAcceptLanguage(){
    return getData().isUseHttpAcceptLanguage();
}


public String getUserFullname(){
    return getData().getUserFullname();
}


public UserSession getCurrentIfMatchingOrNull(UserId adUserId){
    final UserSession userSession = getCurrentOrNull();
    if (userSession == null) {
        return null;
    }
    final UserId loggedUserId = userSession.getLoggedUserIdIfExists().orElse(null);
    if (!UserId.equals(loggedUserId, adUserId)) {
        return null;
    }
    return userSession;
}


public Properties getCtx(){
    return getData().getCtx();
}


@Deprecated
public ZoneId getTimeZoneOrSystemDefault(){
    final UserSession userSession = getCurrentOrNull();
    return userSession != null ? userSession.getTimeZone() : SystemTime.zoneId();
}


public Locale getLocale(){
    return getData().getLocale();
}


}