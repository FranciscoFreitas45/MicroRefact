import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.cocay.sicecd.LogTypes;
import com.cocay.sicecd.model.Usuario_sys;
import com.cocay.sicecd.repo.Usuario_sysRep;
import com.cocay.sicecd.service.Logging;
import com.cocay.sicecd.service.SendMailService;
@Controller
public class EditarPerfil {

@Autowired
 private Usuario_sysRep _usuarioSys;

@Autowired
 private SendMailService _email;

@Value("spring.mail.username")
 private String origen;

@Autowired
 private Logging log;

@Autowired
 private HttpServletRequest request;

@Value("${path_dominio}")
 private String dominio;


@GetMapping("/AdministracionCursos/listaUsuarios")
public ModelAndView listaUsuarios(){
    ModelAndView model = new ModelAndView("editarPerfil/listausuarios");
    model.addObject("usuarios", _usuarioSys.sinprecargado());
    return model;
}


@PostMapping("/AdministracionCursos/renviarrecupera")
public ResponseEntity<String> renviarRecupera(Usuario_sys consulta){
    Usuario_sys guardado = _usuarioSys.findByRfc(consulta.getRfc()).get(0);
    String codigo = String.valueOf((int) (Math.random() * 1000) + 1);
    String link = dominio + "configuracionPass?codigo=" + codigo + "&usuario=" + guardado.getPk_id_usuario_sys();
    String from = origen;
    String to = guardado.getCorreo();
    String subject = "Recupera Contraseña";
    String body = "Hola da clic al siguiente  link \n" + link + "\nrecuperar tu contrase&ntilde;a.";
    try {
        _email.sendMail(from, to, subject, body);
    } catch (Exception e) {
        return ResponseEntity.ok("Ha oucrrido un error al enviar el correo, verifica que la direccion sea valida, o tu conexion.");
    }
    guardado.setCodigorecupera(codigo);
    guardado.setConfirmarecupera("true");
    _usuarioSys.save(guardado);
    log.setTrace(LogTypes.RECUPERA_PASSWORD);
    return ResponseEntity.ok("Correo reenviado");
}


@GetMapping(value = "/confirmacorreo")
public String confirmaCorreo(int id,String codigo){
    if (_usuarioSys.existsById(id)) {
        Usuario_sys u = _usuarioSys.findById(id).get();
        if (u.getCodigoCorreo().equals(codigo)) {
            u.setCorreo(u.getCorreocambio());
            u.setCorreocambio("");
            u.setConfirmacioncorreo("false");
            _usuarioSys.save(u);
        }
    }
    return "redirect:/login?mensaje=CorreoActualizado";
}


@PostMapping("/AdministracionCursos/renviarcambiocorreo")
public ResponseEntity<String> renviarCambioCorreo(Usuario_sys consulta){
    Usuario_sys guardado = _usuarioSys.findByRfc(consulta.getRfc()).get(0);
    editarCorreo(guardado, consulta);
    log.setTrace(LogTypes.RENVIA_CAMBIO_CORREO);
    return ResponseEntity.ok("Correo reenviado");
}


@PostMapping(value = "/AdministracionCursos/editarperfilusuario")
public ResponseEntity<String> editarPerfilUsuario(Usuario_sys consulta){
    Usuario_sys guardado = _usuarioSys.findById(consulta.getPk_id_usuario_sys()).get();
    String cambios = "";
    if (!guardado.getRfc().equals(consulta.getRfc())) {
        cambios += "Rfc de " + guardado.getRfc() + " a " + consulta.getRfc() + "\n";
        guardado.setRfc(consulta.getRfc());
    }
    if (!guardado.getNombre().equals(consulta.getNombre())) {
        cambios += "Nombre de " + guardado.getNombre() + " a " + consulta.getNombre() + "\n";
        guardado.setNombre(consulta.getNombre());
    }
    if (!guardado.getApellido_paterno().equals(consulta.getApellido_paterno())) {
        cambios += "Apellido Paterno de " + guardado.getApellido_paterno() + " a " + consulta.getApellido_paterno() + "\n";
        guardado.setApellido_paterno(consulta.getApellido_paterno());
    }
    if (!guardado.getApellido_materno().equals(consulta.getApellido_materno())) {
        cambios += "Apellido Materno de " + guardado.getApellido_materno() + " a " + consulta.getApellido_materno() + "\n";
        guardado.setApellido_materno(consulta.getApellido_materno());
    }
    if (!guardado.getFk_id_perfil_sys().getNombre().equals(consulta.getFk_id_perfil_sys().getNombre())) {
        cambios += "Perfil " + guardado.getFk_id_perfil_sys().getNombre() + " a " + consulta.getFk_id_perfil_sys().getNombre() + "\n";
        guardado.setFk_id_perfil_sys(consulta.getFk_id_perfil_sys());
    }
    if (!guardado.getFk_id_estatus_usuario_sys().getNombre().equals(consulta.getFk_id_estatus_usuario_sys().getNombre())) {
        cambios += "Estatus " + guardado.getFk_id_estatus_usuario_sys().getNombre() + " a " + consulta.getFk_id_estatus_usuario_sys().getNombre() + "\n";
        guardado.setFk_id_estatus_usuario_sys(consulta.getFk_id_estatus_usuario_sys());
    }
    if (!consulta.getCorreo().equals(guardado.getCorreo())) {
        editarCorreo(guardado, consulta);
    }
    String frome = origen;
    String toe = guardado.getCorreo();
    String subjecte = "Edicion de Perfil";
    String bodye = "Hola se han echo una serie de cambios en tu perfil\n" + cambios;
    if (!cambios.equals("")) {
        try {
            _email.sendMail(frome, toe, subjecte, bodye);
        } catch (Exception e) {
            return ResponseEntity.ok("Ha oucrrido un error al enviar el correo, verifica tu conexion.");
        }
        _usuarioSys.save(guardado);
    }
    log.setTrace(LogTypes.EDITA_USUARIO, null, guardado.getRfc());
    return ResponseEntity.ok("Usuario Editado con exito");
}


@GetMapping(value = "/AdministracionCursos/formEditarPerfilUsuario")
public ModelAndView formEditarPerfilUsuario(String rfc){
    Usuario_sys cambio = (_usuarioSys.findByRfc(rfc.trim())).get(0);
    ModelAndView model = new ModelAndView("editarPerfil/formEditarPerfilUsuario");
    model.addObject("usuario", cambio);
    return model;
}


@PostMapping("/AdministracionCursos/renviaactiva")
public ResponseEntity<String> renviarActivacion(Usuario_sys consulta){
    String codigo = String.valueOf((int) (Math.random() * 1000) + 1);
    Usuario_sys guardado = _usuarioSys.findByRfc(consulta.getRfc()).get(0);
    String link = dominio + "configuracionPass?codigo=" + codigo + "&usuario=" + guardado.getPk_id_usuario_sys();
    String from = origen;
    String to = guardado.getCorreo();
    String subject = "Activación de cuenta";
    String body = "Hola da clic al siguiente  link \n" + link + "\npara activar tu cuenta y configurar una nueva contrase&ntilde;a.";
    try {
        _email.sendMail(from, to, subject, body);
    } catch (Exception e) {
        return ResponseEntity.ok("Ha oucrrido un error al enviar el correo, verifica tu conexion.");
    }
    guardado.setConfirmacion("true");
    guardado.setCodigo(codigo);
    _usuarioSys.save(guardado);
    log.setTrace(LogTypes.RENVIA_CORREO_ACTIVACION);
    return ResponseEntity.ok("Correo reenviado");
}


public void editarCorreo(Usuario_sys guardado,Usuario_sys consulta){
    String codigo = String.valueOf((int) (Math.random() * 1000) + 1);
    String link = dominio + "confirmacorreo?codigo=" + codigo + "&usuario=" + guardado.getPk_id_usuario_sys();
    String from = origen;
    String to = consulta.getCorreo();
    String subject = "Cambio de correo";
    String body = "Hola da clic al siguiente  link \n" + link + "\npara confirmar tu correo.";
    _email.sendMail(from, to, subject, body);
    guardado.setCodigoCorreo(codigo);
    guardado.setConfirmacioncorreo("true");
    guardado.setCorreocambio(consulta.getCorreo());
    _usuarioSys.save(guardado);
}


}