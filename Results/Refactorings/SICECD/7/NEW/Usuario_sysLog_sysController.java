import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class Usuario_sysLog_sysController {

 private Usuario_sysLog_sysService usuario_syslog_sysservice;


@PutMapping
("/Log_sys/{id}/Usuario_sys/setFk_id_usuario_sys")
public void setFk_id_usuario_sys(@PathVariable(name="id") int int,@RequestBody Usuario_sys fk_id_usuario_sys){
usuario_syslog_sysservice.setFk_id_usuario_sys(int,fk_id_usuario_sys);
}


@GetMapping
("/Log_sys/{id}/Usuario_sys/getFk_id_usuario_sys")
public Usuario_sys getFk_id_usuario_sys(@PathVariable(name="id") int int){
usuario_syslog_sysservice.getFk_id_usuario_sys(int);
}


}