package de.metas.ui.web.session;
 import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import java.util.Optional;
import java.util.Properties;
import org.adempiere.service.ClientId;
import org.compiere.util.Env;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import com.google.common.base.MoreObjects;
import de.metas.i18n.Language;
import de.metas.organization.OrgId;
import de.metas.security.RoleId;
import de.metas.ui.web.base.session.UserPreference;
import de.metas.user.UserId;
@Component
@Primary
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@lombok.Data
public class InternalUserSessionData implements Serializable{

 private  long serialVersionUID;

 private  boolean initialized;

 private  String sessionId;

 private  UserPreference userPreference;

 private  boolean loggedIn;

 private  Locale locale;

 private  Properties ctx;

 private  String userFullname;

 private  String userEmail;

 private  String avatarId;

@Value("${metasfresh.webui.debug.showColumnNamesForCaption:false}")
 private  boolean defaultShowColumnNamesForCaption;

 private  boolean showColumnNamesForCaption;

@Value("${metasfresh.webui.debug.allowDeprecatedRestAPI:false}")
 private  boolean defaultAllowDeprecatedRestAPI;

 private  boolean allowDeprecatedRestAPI;

@Value("${metasfresh.webui.http.cache.maxAge:60}")
 private  int defaultHttpCacheMaxAge;

 private  int httpCacheMaxAge;

@Value("${metasfresh.webui.http.use.AcceptLanguage:false}")
 private  boolean defaultUseHttpAcceptLanguage;

 private  boolean useHttpAcceptLanguage;


public Optional<UserId> getLoggedUserIdIfExists(){
    return Env.getLoggedUserIdIfExists(getCtx());
}


public Language getLanguage(){
    return Env.getLanguage(getCtx());
}


public ClientId getClientId(){
    return Env.getClientId(getCtx());
}


public String getRoleName(){
    return Env.getContext(getCtx(), Env.CTXNAME_AD_Role_Name);
}


public void writeObject(java.io.ObjectOutputStream out){
    out.defaultWriteObject();
    UserSession.logger.trace("User session serialized: {}", this);
}


public OrgId getOrgId(){
    return Env.getOrgId(getCtx());
}


public UserId getLoggedUserId(){
    return Env.getLoggedUserId(getCtx());
}


public RoleId getLoggedRoleId(){
    return Env.getLoggedRoleId(getCtx());
}


public String getAdLanguage(){
    return Env.getContext(getCtx(), Env.CTXNAME_AD_Language);
}


public void readObject(java.io.ObjectInputStream in){
    in.defaultReadObject();
    UserSession.logger.trace("User session deserialized: {}", this);
}


public String verifyLanguageAndSet(Language lang){
    final Properties ctx = getCtx();
    final String adLanguageOld = Env.getContext(ctx, Env.CTXNAME_AD_Language);
    // 
    // Check the language (and update it if needed)
    final Language validLang = Env.verifyLanguageFallbackToBase(lang);
    // 
    // Actual update
    final String adLanguageNew = validLang.getAD_Language();
    Env.setContext(ctx, Env.CTXNAME_AD_Language, adLanguageNew);
    this.locale = validLang.getLocale();
    UserSession.logger.debug("Changed AD_Language: {} -> {}, {}", adLanguageOld, adLanguageNew, validLang);
    return adLanguageOld;
}


public void initializeNow(){
    // 
    // Set initial properties
    setShowColumnNamesForCaption(defaultShowColumnNamesForCaption);
    setAllowDeprecatedRestAPI(defaultAllowDeprecatedRestAPI);
    setHttpCacheMaxAge(defaultHttpCacheMaxAge);
    setUseHttpAcceptLanguage(defaultUseHttpAcceptLanguage);
    // 
    // Set initial language
    try {
        final Language language = findInitialLanguage();
        verifyLanguageAndSet(language);
    } catch (final Throwable ex) {
        UserSession.logger.warn("Failed setting the language, but moving on", ex);
    }
}


public Properties getCtx(){
    return ctx;
}


public String getUserName(){
    return Env.getContext(getCtx(), Env.CTXNAME_AD_User_Name);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("sessionId", sessionId).add("loggedIn", loggedIn).add("locale", locale).add("userPreferences", userPreference).add("defaultUseHttpAcceptLanguage", defaultUseHttpAcceptLanguage).toString();
}


public void initializeIfNeeded(){
    if (!initialized) {
        synchronized (this) {
            if (!initialized) {
                initializeNow();
                initialized = true;
            }
        }
    }
}


public Language findInitialLanguage(){
    final Locale locale = LocaleContextHolder.getLocale();
    if (locale != null) {
        final Language language = Language.findLanguageByLocale(locale);
        if (language != null) {
            return language;
        }
    }
    return Language.getBaseLanguage();
}


}