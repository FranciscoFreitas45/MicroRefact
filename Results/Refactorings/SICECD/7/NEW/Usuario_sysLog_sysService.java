import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class Usuario_sysLog_sysService {

 private Usuario_sysRep usuario_sysrep;


public void setFk_id_usuario_sys(int int,Usuario_sys fk_id_usuario_sys){
usuario_sysrep.setFk_id_usuario_sys(int,fk_id_usuario_sys);
}


public Usuario_sys getFk_id_usuario_sys(int int){
usuario_sysrep.getFk_id_usuario_sys(int);
}


}