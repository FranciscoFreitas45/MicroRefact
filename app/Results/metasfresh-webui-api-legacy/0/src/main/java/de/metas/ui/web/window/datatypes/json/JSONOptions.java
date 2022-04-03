package de.metas.ui.web.window.datatypes.json;
 import java.time.ZoneId;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import de.metas.i18n.ILanguageDAO;
import de.metas.i18n.Language;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.session.UserSession;
import de.metas.util.Services;
import de.metas.util.time.SystemTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(doNotUseGetters = true)
@Getter
public class JSONOptions {

 public  String DEBUG_ATTRNAME;

 private  String adLanguage;

 private  ZoneId zoneId;


public JSONOptions ofAdLanguage(String adLanguage){
    return builder().adLanguage(adLanguage).build();
}


public String extractAdLanguageFromUserSession(UserSession userSession){
    final String sessionADLanguage = userSession.getAD_Language();
    if (sessionADLanguage != null) {
        return sessionADLanguage;
    }
    // 
    // Try fetching the AD_Language from "Accept-Language" HTTP header
    if (userSession.isUseHttpAcceptLanguage()) {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        if (httpServletRequest != null) {
            final String httpAcceptLanguage = httpServletRequest.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
            if (!Check.isEmpty(httpAcceptLanguage, true)) {
                final String requestLanguage = Services.get(ILanguageDAO.class).retrieveAvailableLanguages().getAD_LanguageFromHttpAcceptLanguage(httpAcceptLanguage, null);
                if (requestLanguage != null) {
                    return requestLanguage;
                }
            }
        }
    }
    throw new IllegalStateException("Cannot extract the AD_Language from user session");
}


public HttpServletRequest getHttpServletRequest(){
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    if (requestAttributes == null) {
        return null;
    }
    if (!(requestAttributes instanceof ServletRequestAttributes)) {
        return null;
    }
    final HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
    return servletRequest;
}


public JSONOptions of(UserSession userSession){
    return prepareFrom(userSession).build();
}


public JSONOptionsBuilder prepareFrom(UserSession userSession){
    return builder().adLanguage(extractAdLanguageFromUserSession(userSession)).zoneId(userSession.getTimeZone());
}


public JSONOptions newInstance(){
    final UserSession userSession = UserSession.getCurrentOrNull();
    return userSession != null ? of(userSession) : ofAdLanguage(Language.getBaseAD_Language());
}


public JSONOptions withAdLanguage(String adLanguage){
    if (this.adLanguage.equals(adLanguage)) {
        return this;
    } else {
        return toBuilder().adLanguage(adLanguage).build();
    }
}


}