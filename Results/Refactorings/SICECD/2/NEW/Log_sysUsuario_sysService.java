import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class Log_sysUsuario_sysService {

 private Log_sysRep log_sysrep;


public void setLog_sys_s(int pk_id_usuario_sys,List<Log_sys> log_sys_s){
log_sysrep.setLog_sys_s(pk_id_usuario_sys,log_sys_s);
}


public List<Log_sys> getLog_sys_s(int pk_id_usuario_sys){
log_sysrep.getLog_sys_s(pk_id_usuario_sys);
}


}