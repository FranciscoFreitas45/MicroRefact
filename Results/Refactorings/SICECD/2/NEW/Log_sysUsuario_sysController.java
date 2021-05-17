import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class Log_sysUsuario_sysController {

 private Log_sysUsuario_sysService log_sysusuario_sysservice;


@PutMapping
("/Usuario_sys/{id}/Log_sys/setLog_sys_s")
public void setLog_sys_s(@PathVariable(name="id") int pk_id_usuario_sys,@RequestBody List<Log_sys> log_sys_s){
log_sysusuario_sysservice.setLog_sys_s(pk_id_usuario_sys,log_sys_s);
}


@GetMapping
("/Usuario_sys/{id}/Log_sys/getLog_sys_s")
public List<Log_sys> getLog_sys_s(@PathVariable(name="id") int pk_id_usuario_sys){
log_sysusuario_sysservice.getLog_sys_s(pk_id_usuario_sys);
}


}