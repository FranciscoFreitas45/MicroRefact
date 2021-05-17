import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.cocay.sicecd.service.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import com.cocay.sicecd.LogTypes;
import com.cocay.sicecd.model.Inscripcion;
import com.cocay.sicecd.model.Profesor;
import com.cocay.sicecd.model.Usuario_sys;
import com.cocay.sicecd.repo.Estatus_usuario_sysRep;
import com.cocay.sicecd.repo.InscripcionRep;
import com.cocay.sicecd.repo.Perfil_sysRep;
import com.cocay.sicecd.repo.ProfesorRep;
import com.cocay.sicecd.repo.Usuario_sysRep;
import com.cocay.sicecd.service.SendMailService;
@Controller
public class AltaUsuarios {

@Autowired
 private Usuario_sysRep _usuarioSys;

@Autowired
 private Estatus_usuario_sysRep estatusSys;

@Autowired
 private Perfil_sysRep perfilSys;

@Autowired
 private SendMailService _email;

@Autowired
 private Logging log;

@Value("${path_dominio}")
 private String dominio;

@Value("spring.mail.username")
 private String origen;


@PostMapping("/AdministracionCursos/altaUsuario")
public ResponseEntity<String> darAltaUsuario(Usuario_sys consulta){
    if (_usuarioSys.existsByRfc(consulta.getRfc())) {
        return ResponseEntity.ok("El usuario con este rfc ya ha sido agregado");
    }
    if (_usuarioSys.existsByCorreo(consulta.getCorreo())) {
        return ResponseEntity.ok("El usuario con este correo ya ha sido agregado");
    }
    consulta.setFk_id_estatus_usuario_sys(estatusSys.findByNombre("Inactivo").get(0));
    consulta.setConfirmacion("true");
    consulta.setConfirmacioncorreo("false");
    consulta.setConfirmarecupera("false");
    String codigo = String.valueOf((int) (Math.random() * 1000) + 1);
    consulta.setCodigo(codigo);
    _usuarioSys.save(consulta);
    Usuario_sys guardado = _usuarioSys.findByRfc(consulta.getRfc()).get(0);
    String link = dominio + "configuracionPass?codigo=" + codigo + "&usuario=" + guardado.getPk_id_usuario_sys();
    String from = origen;
    String to = consulta.getCorreo();
    String subject = "Activación de cuenta";
    String body = "Hola da clic al siguiente  link \n" + link + "\npara activar tu cuenta y configurar una nueva contrase&ntilde;a.";
    try {
        _email.sendMail(from, to, subject, body);
    } catch (Exception e) {
        return ResponseEntity.ok("Ha oucrrido un error al enviar el correo, verifica que la direccion sea valida, o tu conexion.");
    }
    log.setTrace(LogTypes.ALTA_USUARIO);
    return ResponseEntity.ok("Usuario Agregado con exito");
}


@PostMapping("/activacion")
public String ActivacionAdministrador(int id,String codigo,String contrasena){
    if (_usuarioSys.existsById(id)) {
        Usuario_sys candidato = (_usuarioSys.findById(id)).get();
        if (codigo.equals(candidato.getCodigo())) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            candidato.setPassword(passwordEncoder.encode(contrasena));
            candidato.setFk_id_estatus_usuario_sys(estatusSys.findByNombre("Activo").get(0));
            candidato.setConfirmacion("false");
            _usuarioSys.save(candidato);
        }
        if (codigo.equals(candidato.getCodigorecupera())) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            candidato.setPassword(passwordEncoder.encode(contrasena));
            candidato.setConfirmarecupera("false");
            _usuarioSys.save(candidato);
        }
    }
    log.setTrace(LogTypes.ACTIVA_USUARIO);
    return "redirect:/login?mensaje=Ya puedes realizar login";
}


@RequestMapping(value = "/AdministracionCursos/formAltaUsuario", method = RequestMethod.GET)
public String formularioAltaUsuario(){
    return "altaUsuario/agregaUsuario";
}


@GetMapping("/configuracionPass")
public String configuracionPass(int id,String codigo,Model model){
    model.addAttribute("id", id);
    model.addAttribute("codigo", codigo);
    return "altaUsuario/configuracionpass";
}


}