package DTO;
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
public class JSONOptions {

 public  String DEBUG_ATTRNAME;

 private  String adLanguage;

 private  ZoneId zoneId;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


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


public JSONOptionsBuilder prepareFrom(UserSession userSession){
    return builder().adLanguage(extractAdLanguageFromUserSession(userSession)).zoneId(userSession.getTimeZone());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/prepareFrom"))

.queryParam("userSession",userSession);
JSONOptionsBuilder aux = restTemplate.getForObject(builder.toUriString(),JSONOptionsBuilder.class);
return aux;
}


}