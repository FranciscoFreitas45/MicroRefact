import java.security.Principal;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.cocay.sicecd.model.Log_sys;
import com.cocay.sicecd.repo.Log_evento_sysRep;
import com.cocay.sicecd.repo.Log_sysRep;
import com.cocay.sicecd.repo.Usuario_sysRep;
@Service
public class Logging {

@Autowired
 private HttpServletRequest request;

@Autowired
 private Usuario_sysRep usuario;

@Autowired
 private Log_sysRep logr;

@Autowired
 private Log_evento_sysRep evento;

@Value("${host}")
 private String host;


public void setTrace(String action,String comments){
    logtrace(action, comments);
}


public void logtrace(String action,String comentario){
    Log_sys log = new Log_sys();
    try {
        request.getUserPrincipal();
    } catch (Exception ex) {
        request = null;
    }
    if (request != null) {
        Principal principal = request.getUserPrincipal();
        log.setIp(request.getRemoteAddr());
        if (principal == null) {
            log.setFk_id_usuario_sys(usuario.findById(1).get());
        } else {
            log.setFk_id_usuario_sys(usuario.findByRfc(principal.getName()).get(0));
        }
    } else {
        log.setIp(host);
        log.setFk_id_usuario_sys(usuario.findById(1).get());
    }
    log.setHora(new Date());
    log.setFk_id_log_evento_sys(evento.findById(action).get());
    if (comentario != null) {
        if (comentario.length() > 1000) {
            comentario = comentario.substring(0, 999);
        }
        log.setComentario(comentario);
    }
    logr.save(log);
}


}