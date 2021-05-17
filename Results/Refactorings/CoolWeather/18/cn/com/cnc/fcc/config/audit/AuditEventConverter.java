import cn.com.cnc.fcc.domain.PersistentAuditEvent;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import java.util;
@Component
public class AuditEventConverter {


public Map<String,String> convertDataToStrings(Map<String,Object> data){
    Map<String, String> results = new HashMap<>();
    if (data != null) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            // Extract the data that will be saved.
            if (entry.getValue() instanceof WebAuthenticationDetails) {
                WebAuthenticationDetails authenticationDetails = (WebAuthenticationDetails) entry.getValue();
                results.put("remoteAddress", authenticationDetails.getRemoteAddress());
                results.put("sessionId", authenticationDetails.getSessionId());
            } else {
                results.put(entry.getKey(), Objects.toString(entry.getValue()));
            }
        }
    }
    return results;
}


public Map<String,Object> convertDataToObjects(Map<String,String> data){
    Map<String, Object> results = new HashMap<>();
    if (data != null) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            results.put(entry.getKey(), entry.getValue());
        }
    }
    return results;
}


public AuditEvent convertToAuditEvent(PersistentAuditEvent persistentAuditEvent){
    if (persistentAuditEvent == null) {
        return null;
    }
    return new AuditEvent(persistentAuditEvent.getAuditEventDate(), persistentAuditEvent.getPrincipal(), persistentAuditEvent.getAuditEventType(), convertDataToObjects(persistentAuditEvent.getData()));
}


}