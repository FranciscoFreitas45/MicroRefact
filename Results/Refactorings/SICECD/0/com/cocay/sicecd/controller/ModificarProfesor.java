import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.cocay.sicecd.LogTypes;
import com.cocay.sicecd.dto.GrupoDto;
import com.cocay.sicecd.dto.InscripcionDto;
import com.cocay.sicecd.dto.ProfesorDto;
import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Estado;
import com.cocay.sicecd.model.Genero;
import com.cocay.sicecd.model.Grado_profesor;
import com.cocay.sicecd.model.Grupo;
import com.cocay.sicecd.model.Inscripcion;
import com.cocay.sicecd.model.Profesor;
import com.cocay.sicecd.model.Tipo_curso;
import com.cocay.sicecd.model.Turno;
import com.cocay.sicecd.repo.CursoRep;
import com.cocay.sicecd.repo.EstadoRep;
import com.cocay.sicecd.repo.GeneroRep;
import com.cocay.sicecd.repo.Grado_profesorRep;
import com.cocay.sicecd.repo.GrupoRep;
import com.cocay.sicecd.repo.InscripcionRep;
import com.cocay.sicecd.repo.ProfesorRep;
import com.cocay.sicecd.repo.Tipo_cursoRep;
import com.cocay.sicecd.repo.TurnoRep;
import com.cocay.sicecd.service.Logging;
@Controller
@RequestMapping("AdministracionModificaciones")
@PropertySource("classpath:application.properties")
public class ModificarProfesor {

@Autowired
 private ProfesorRep proRep;

@Autowired
 private InscripcionRep insRep;

@Autowired
 private GrupoRep grRep;

@Autowired
 private CursoRep crRep;

@Autowired
 private GeneroRep genRep;

@Autowired
 private EstadoRep stRep;

@Autowired
 private Grado_profesorRep gpRep;

@Autowired
 private Tipo_cursoRep tcRep;

@Autowired
 private TurnoRep tnRep;

@Autowired
 private Logging log;

@Value("${path_constancia}")
 private  String ruta;


@PostMapping(value = "/editarprofesor")
public ResponseEntity<String> editarProfesor(Locale locale,ProfesorDto profesor,MultipartFile constancia,MultipartFile comprobante,MultipartFile rfc_pdf,MultipartFile curp_pdf){
    int id = profesor.getIdProfesor();
    Profesor mod = proRep.findById(id).get();
    String cambios = "";
    if (!mod.getRfc().equals(profesor.getRfc())) {
        cambios += "Rfc de " + mod.getRfc() + " a " + profesor.getRfc() + "\n";
        mod.setRfc(profesor.getRfc());
    }
    if (!mod.getNombre().equals(profesor.getNombres())) {
        cambios += "Nombre de " + mod.getNombre() + " a " + profesor.getNombres() + "\n";
        mod.setNombre(profesor.getNombres());
    }
    if (!mod.getApellido_paterno().equals(profesor.getaPaterno())) {
        cambios += "Apellido Paterno de " + mod.getApellido_paterno() + " a " + profesor.getaPaterno() + "\n";
        mod.setApellido_paterno(profesor.getaPaterno());
    }
    if (!mod.getApellido_materno().equals(profesor.getaMaterno())) {
        cambios += "Apellido Materno de " + mod.getApellido_materno() + " a " + profesor.getaMaterno() + "\n";
        mod.setApellido_materno(profesor.getaMaterno());
    }
    if (mod.getCurp() != null) {
        Profesor repetido = proRep.findByCurp(profesor.getCurp());
        if (repetido != null && mod.getRfc() != repetido.getRfc() && !mod.getCurp().equals("")) {
            return ResponseEntity.ok("Error: curp ya registrada");
        } else {
            if (!mod.getCurp().equals(profesor.getCurp())) {
                cambios += "Curp de " + mod.getCurp() + " a " + profesor.getCurp() + "\n";
                mod.setCurp(profesor.getCurp());
            }
        }
    }
    if (!mod.getCorreo().equals(profesor.getCorreo())) {
        cambios += "Correo de " + mod.getCorreo() + " a " + profesor.getCorreo() + "\n";
        mod.setCorreo(profesor.getCorreo());
    }
    if (mod.getTelefono() == null) {
        cambios += "Telefono de " + mod.getTelefono() + " a " + profesor.getTelefono() + "\n";
        mod.setTelefono(profesor.getTelefono());
    } else {
        if (!mod.getTelefono().equals(profesor.getTelefono())) {
            cambios += "Telefono de " + mod.getTelefono() + " a " + profesor.getTelefono() + "\n";
            mod.setTelefono(profesor.getTelefono());
        }
    }
    if (mod.getCiudad_localidad() == null) {
        cambios += "Ciudad o localidad de " + mod.getCiudad_localidad() + " a " + profesor.getCilo() + "\n";
        mod.setCiudad_localidad(profesor.getCilo());
    } else {
        if (!mod.getCiudad_localidad().equals(profesor.getCilo())) {
            cambios += "Ciudad o localidad de " + mod.getCiudad_localidad() + " a " + profesor.getCilo() + "\n";
            mod.setCiudad_localidad(profesor.getCilo());
        }
    }
    if (mod.getPlantel() == null) {
        cambios += "Plantel de " + mod.getPlantel() + " a " + profesor.getPlantel() + "\n";
        mod.setPlantel(profesor.getPlantel());
    } else {
        if (!mod.getPlantel().equals(profesor.getPlantel())) {
            cambios += "Plantel de " + mod.getPlantel() + " a " + profesor.getPlantel() + "\n";
            mod.setPlantel(profesor.getPlantel());
        }
    }
    if (mod.getClave_plantel() == null) {
        cambios += "clave de Plantel de " + mod.getClave_plantel() + " a " + profesor.getcPlantel() + "\n";
        mod.setClave_plantel(profesor.getcPlantel());
    } else {
        if (!mod.getClave_plantel().equals(profesor.getcPlantel())) {
            cambios += "clave de Plantel de " + mod.getClave_plantel() + " a " + profesor.getcPlantel() + "\n";
            mod.setClave_plantel(profesor.getcPlantel());
        }
    }
    if (mod.getOcupacion() == null) {
        cambios += "Ocupacion de " + mod.getOcupacion() + " a " + profesor.getOcupacion() + "\n";
        mod.setOcupacion(profesor.getOcupacion());
    } else {
        if (!mod.getOcupacion().equals(profesor.getOcupacion())) {
            cambios += "Ocupacion de " + mod.getOcupacion() + " a " + profesor.getOcupacion() + "\n";
            mod.setOcupacion(profesor.getOcupacion());
        }
    }
    Estado st = stRep.findById(Integer.parseInt(profesor.getEstado())).get();
    if (!Integer.toString(mod.getFk_id_estado().getPk_id_estado()).equals(profesor.getEstado())) {
        mod.setFk_id_estado(st);
    }
    Grado_profesor gp = gpRep.findById(Integer.parseInt(profesor.getGrado())).get();
    if (!Integer.toString(mod.getFk_id_grado_profesor().getPk_id_grado_profesor()).equals(profesor.getGrado())) {
        mod.setFk_id_grado_profesor(gp);
    }
    Turno turn = tnRep.findById(Integer.parseInt(profesor.getTurno())).get();
    if (!Integer.toString(mod.getFk_id_turno().getPk_id_turno()).equals(profesor.getTurno())) {
        mod.setFk_id_turno(turn);
    }
    if (mod.getFechaNac() == null) {
        String fechaSt = profesor.getfNacimiento();
        Date fecha = null;
        try {
            fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaSt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mod.setFechaNac(fecha);
    } else {
        if (profesor.getfNacimiento() != null) {
            String fechaSt = profesor.getfNacimiento();
            Date fecha = null;
            try {
                fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaSt);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (!mod.getFechaNac().equals(fecha)) {
                Calendar calendario = Calendar.getInstance();
                calendario.setTime(fecha);
                calendario.add(Calendar.DAY_OF_YEAR, 1);
                mod.setFechaNac(calendario.getTime());
            }
        }
    }
    if (constancia != null) {
        String originalName2 = constancia.getOriginalFilename();
        if (mod.getCertificado_doc() == null) {
            mod.setCertificado_doc(originalName2);
        } else {
            if (!mod.getCertificado_doc().equals(originalName2)) {
                File here = new File(".");
                String rutaActual = here.getAbsolutePath().replace(".", "");
                String rutaR = rutaActual.replace("\\", "/") + ruta.replace("./", "") + Integer.toString(mod.getPk_id_profesor()) + "/" + mod.getCertificado_doc();
                File archivo_anterior = new File(rutaR);
                archivo_anterior.delete();
            }
        }
        saveConstancia(constancia, mod);
        mod.setCertificado_doc(originalName2);
    }
    if (comprobante != null) {
        String originalName2 = comprobante.getOriginalFilename();
        if (mod.getComprobante_doc() == null) {
            mod.setComprobante_doc(originalName2);
        } else {
            if (!mod.getComprobante_doc().equals(originalName2)) {
                File here = new File(".");
                String rutaActual = here.getAbsolutePath().replace(".", "");
                String rutaR = rutaActual.replace("\\", "/") + ruta.replace("./", "") + Integer.toString(mod.getPk_id_profesor()) + "/" + mod.getComprobante_doc();
                File archivo_anterior = new File(rutaR);
                archivo_anterior.delete();
            }
        }
        saveConstancia(comprobante, mod);
        mod.setComprobante_doc(originalName2);
    }
    if (rfc_pdf != null) {
        String originalName2 = rfc_pdf.getOriginalFilename();
        System.out.println("---------El nombre del rfc es:" + originalName2);
        if (mod.getRfc_doc() == null) {
            mod.setRfc_doc(originalName2);
        } else {
            if (!mod.getRfc_doc().equals(originalName2)) {
                File here = new File(".");
                String rutaActual = here.getAbsolutePath().replace(".", "");
                String rutaR = rutaActual.replace("\\", "/") + ruta.replace("./", "") + Integer.toString(mod.getPk_id_profesor()) + "/" + mod.getRfc_doc();
                File archivo_anterior = new File(rutaR);
                archivo_anterior.delete();
            }
        }
        saveConstancia(rfc_pdf, mod);
        mod.setRfc_doc(originalName2);
    }
    if (curp_pdf != null) {
        String originalName2 = curp_pdf.getOriginalFilename();
        if (mod.getCurp_doc() == null) {
            mod.setCurp_doc(originalName2);
        } else {
            if (!mod.getCurp_doc().equals(originalName2)) {
                File here = new File(".");
                String rutaActual = here.getAbsolutePath().replace(".", "");
                String rutaR = rutaActual.replace("\\", "/") + ruta.replace("./", "") + Integer.toString(mod.getPk_id_profesor()) + "/" + mod.getCurp_doc();
                File archivo_anterior = new File(rutaR);
                archivo_anterior.delete();
            }
        }
        saveConstancia(curp_pdf, mod);
        mod.setCurp_doc(originalName2);
    }
    System.out.println(cambios);
    log.setTrace(LogTypes.MODIFICAR_PARTICIPANTE);
    if (!cambios.equals("")) {
        proRep.save(mod);
    }
    return ResponseEntity.ok("¡Participante editado con exito!");
}


@RequestMapping(value = "/listaProfesores", method = RequestMethod.GET)
public ModelAndView consultarProfesorEstado(ModelMap model){
    List<Profesor> list_p1 = proRep.findAll();
    // Se filtran a los participantes de los asesores
    for (Profesor p : list_p1) {
        if (p.getFk_id_estado() == null) {
            Optional<Estado> estado = stRep.findById(34);
            Estado defa = estado.get();
            p.setFk_id_estado(defa);
            proRep.save(p);
        }
        if (p.getFk_id_grado_profesor() == null) {
            Optional<Grado_profesor> gd = gpRep.findById(5);
            Grado_profesor gdp = gd.get();
            p.setFk_id_grado_profesor(gdp);
            proRep.save(p);
        }
        if (p.getFk_id_turno() == null) {
            Optional<Turno> tr = tnRep.findById(4);
            Turno tur = tr.get();
            p.setFk_id_turno(tur);
            proRep.save(p);
        }
        if (p.getGenero() == null) {
            Optional<Genero> g = genRep.findById(3);
            Genero gen = g.get();
            p.setGenero(gen);
            proRep.save(p);
        }
    }
    list_p1 = list_p1.stream().filter(x -> x.getFk_id_estado().getPk_id_estado() != 34).collect(Collectors.toList());
    if (!list_p1.isEmpty()) {
        model.put("profesores", list_p1);
        return new ModelAndView("ModificarUsuario/listaProfesores", model);
    } else {
        return new ModelAndView("/Avisos/ErrorBusqueda");
    }
}


@GetMapping(value = "/pantallaModificacion")
public ModelAndView formEditarPerfilUsuario(String rfc){
    Profesor cambio = (proRep.findByRfc(rfc));
    ProfesorDto prof = new ProfesorDto();
    prof.setIdProfesor(cambio.getPk_id_profesor());
    prof.setaMaterno(cambio.getApellido_materno());
    prof.setaPaterno(cambio.getApellido_paterno());
    if (cambio.getCertificado_doc() != null) {
        prof.setCertificado_doc(cambio.getCertificado_doc());
    }
    if (cambio.getCiudad_localidad() != null) {
        prof.setCilo(cambio.getCiudad_localidad());
    }
    if (cambio.getComprobante_doc() != null) {
        prof.setComprobante_doc(cambio.getComprobante_doc());
    }
    prof.setCorreo(cambio.getCorreo());
    if (cambio.getClave_plantel() != null) {
        prof.setcPlantel(cambio.getClave_plantel());
    }
    if (cambio.getFechaNac() != null) {
        prof.setfNacimiento(cambio.getFechaNac().toString().substring(0, 10));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String ffecha = formatter.format(cambio.getFechaNac());
        prof.setFormFecha(ffecha);
    }
    if (cambio.getCurp() != null) {
        prof.setCurp(cambio.getCurp());
    }
    if (cambio.getCurp_doc() != null) {
        prof.setCurp_doc(cambio.getCurp_doc());
    }
    prof.setEstado(Integer.toString(cambio.getFk_id_estado().getPk_id_estado()));
    prof.setNombreEstado(cambio.getFk_id_estado().getNombre());
    prof.setGenero(Integer.toString(cambio.getGenero().getPk_id_genero()));
    prof.setNombreGenero(cambio.getGenero().getGenero());
    prof.setGrado(Integer.toString(cambio.getFk_id_grado_profesor().getPk_id_grado_profesor()));
    prof.setNombreGrado(cambio.getFk_id_grado_profesor().getNombre());
    prof.setNombres(cambio.getNombre());
    if (cambio.getOcupacion() != null) {
        prof.setOcupacion(cambio.getOcupacion());
    }
    if (cambio.getPlantel() != null) {
        prof.setPlantel(cambio.getPlantel());
    }
    prof.setRfc(cambio.getRfc());
    if (cambio.getRfc_doc() != null) {
        prof.setRfc_doc(cambio.getRfc_doc());
    }
    if (cambio.getTelefono() != null) {
        prof.setTelefono(cambio.getTelefono());
    }
    prof.setTurno(Integer.toString(cambio.getFk_id_turno().getPk_id_turno()));
    prof.setNombreTurno(cambio.getFk_id_turno().getNombre());
    ModelAndView model = new ModelAndView("ModificarUsuario/pantallaModificacion");
    model.addObject("profesor", prof);
    return model;
}


public String saveConstancia(MultipartFile constancia,Profesor pr){
    String path = Integer.toString(pr.getPk_id_profesor());
    System.out.println("path: " + path);
    String originalName = constancia.getOriginalFilename();
    System.out.println("FileName: " + originalName);
    String folder = ruta + path + "/";
    try {
        FileUtils.forceMkdir(new File(folder));
        try (FileOutputStream fos = new FileOutputStream(new File(folder + originalName))) {
            IOUtils.copy(constancia.getInputStream(), fos);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return path;
}


}