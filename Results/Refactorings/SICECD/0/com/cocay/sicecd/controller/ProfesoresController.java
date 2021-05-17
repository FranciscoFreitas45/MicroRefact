import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.cocay.sicecd.LogTypes;
import com.cocay.sicecd.dto.ProfesorDto;
import com.cocay.sicecd.model.Estado;
import com.cocay.sicecd.model.Genero;
import com.cocay.sicecd.model.Grado_profesor;
import com.cocay.sicecd.model.Profesor;
import com.cocay.sicecd.model.Turno;
import com.cocay.sicecd.repo.EstadoRep;
import com.cocay.sicecd.repo.GeneroRep;
import com.cocay.sicecd.repo.Grado_profesorRep;
import com.cocay.sicecd.repo.ProfesorRep;
import com.cocay.sicecd.repo.TurnoRep;
import com.cocay.sicecd.service.Logging;
@Controller
@RequestMapping("AdministracionRegistroManual")
@PropertySource("classpath:application.properties")
public class ProfesoresController {

@Autowired
 private ProfesorRep profRep;

@Autowired
 private  EstadoRep stRep;

@Autowired
 private  Grado_profesorRep gpRep;

@Autowired
 private  TurnoRep tRep;

@Autowired
 private  GeneroRep gRep;

@Autowired
 private Logging log;

@Value("${path_constancia}")
 private  String ruta;


@RequestMapping(value = "/registrarAsesor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<String> agregarAs(ProfesorDto prof){
    Profesor pro = new Profesor();
    String apaterno = prof.getaPaterno();
    String amaterno = prof.getaMaterno();
    String nombres = prof.getNombres();
    String rfc = prof.getRfc();
    String telefono = prof.getTelefono();
    String correo = prof.getCorreo();
    String fechaSt = prof.getfNacimiento();
    Date fecha = null;
    try {
        fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaSt);
        pro.setFechaNac(fecha);
    } catch (ParseException e) {
        e.printStackTrace();
        pro.setFechaNac(null);
    }
    pro.setApellido_paterno(apaterno);
    pro.setApellido_materno(amaterno);
    pro.setNombre(nombres);
    pro.setRfc(rfc);
    pro.setTelefono(telefono);
    pro.setCorreo(correo);
    /*------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------*/
    /*Datos por default para la tabla*/
    List<Estado> est = stRep.findByNombre("No definido");
    Optional<Turno> trn = tRep.findById(4);
    Optional<Genero> gen = gRep.findById(Integer.parseInt(prof.getGenero()));
    Optional<Grado_profesor> gr = gpRep.findById(5);
    if (!est.isEmpty()) {
        pro.setFk_id_estado(est.get(0));
    }
    pro.setFk_id_turno(trn.get());
    pro.setGenero(gen.get());
    pro.setFk_id_grado_profesor(gr.get());
    log.setTrace(LogTypes.REGISTRAR_ASESOR);
    /*------------------------------------------------------------------------------*/
    profRep.save(pro);
    return ResponseEntity.ok("{\"status\":200,\"success \":\"Ok\",\"message\":\"¡Asesor agregado con exito!\",\"path\":\"/AdministracionProfesores/registrarAsesor\"}");
}


@RequestMapping(value = "/registrarParticipante", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<String> registrarParticipantes(Locale locale,ProfesorDto prof,MultipartFile constancia,MultipartFile comprobante,MultipartFile rfc_pdf,MultipartFile curp_pdf){
    Profesor profe = new Profesor();
    String apaterno = prof.getaPaterno();
    System.out.println("Apellido paterno: " + apaterno);
    String amaterno = prof.getaMaterno();
    System.out.println("Apellido materno" + amaterno);
    String nombres = prof.getNombres();
    System.out.println("nombre: " + nombres);
    String curp = prof.getCurp();
    profe.setCurp(curp);
    List<Profesor> alt = profRep.findHigherID();
    Profesor ultProfe = new Profesor();
    if (!alt.isEmpty()) {
        ultProfe = profRep.findHigherID().get(0);
    } else {
        ultProfe.setPk_id_profesor(0);
    }
    Integer idPath = ultProfe.getPk_id_profesor() + 1;
    if (curp.equals("")) {
        profe.setCurp("No definido" + idPath);
    } else {
        if (profRep.findByCurp(curp) != null) {
            return ResponseEntity.ok("{\"status\":200,\"success \":\"Ok\",\"message\":\"Error: ¡Curp ya registrado!\",\"path\":\"/AdministracionProfesores/registrarParticipante\"}");
        }
    }
    System.out.println("El curp: " + curp);
    String rfc = prof.getRfc();
    System.out.println("El rfc: " + rfc);
    String correo = prof.getCorreo();
    System.out.println("El correo es: " + correo);
    String telefono = prof.getTelefono();
    System.out.println("El telefono es: " + telefono);
    if (constancia != null) {
        String originalName = constancia.getOriginalFilename();
        saveConstancia(constancia);
        profe.setCertificado_doc(originalName);
    }
    if (comprobante != null) {
        String originalName2 = comprobante.getOriginalFilename();
        saveConstancia(comprobante);
        profe.setComprobante_doc(originalName2);
    }
    if (rfc_pdf != null) {
        String originalName3 = rfc_pdf.getOriginalFilename();
        saveConstancia(rfc_pdf);
        profe.setRfc_doc(originalName3);
    }
    if (curp_pdf != null) {
        String originalName4 = curp_pdf.getOriginalFilename();
        saveConstancia(curp_pdf);
        profe.setCurp_doc(originalName4);
    }
    String fechaSt = prof.getfNacimiento();
    if (!fechaSt.equals("")) {
        Date fecha = null;
        try {
            fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaSt);
            profe.setFechaNac(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            profe.setFechaNac(null);
        }
    }
    // /*base*/
    Integer estado = Integer.parseInt(prof.getEstado());
    Optional<Estado> est = stRep.findById(estado);
    String cilo = prof.getCilo();
    /*base*/
    Integer genero = Integer.parseInt(prof.getGenero());
    Optional<Genero> gen = gRep.findById(genero);
    String plantel = prof.getPlantel();
    /*base*/
    Integer turno = Integer.parseInt(prof.getTurno());
    Optional<Turno> trn = tRep.findById(turno);
    String cplantel = prof.getcPlantel();
    /*base*/
    Integer grado = Integer.parseInt(prof.getGrado());
    Optional<Grado_profesor> gr = gpRep.findById(grado);
    String ocupacion = prof.getOcupacion();
    profe.setApellido_paterno(apaterno);
    profe.setApellido_materno(amaterno);
    profe.setNombre(nombres);
    profe.setRfc(rfc);
    profe.setCorreo(correo);
    profe.setTelefono(telefono);
    profe.setFk_id_estado(est.get());
    profe.setCiudad_localidad(cilo);
    profe.setGenero(gen.get());
    profe.setPlantel(plantel);
    profe.setFk_id_turno(trn.get());
    profe.setClave_plantel(cplantel);
    profe.setFk_id_grado_profesor(gr.get());
    profe.setOcupacion(ocupacion);
    log.setTrace(LogTypes.REGISTRAR_PARTICIPANTE);
    profRep.save(profe);
    return ResponseEntity.ok("{\"status\":200,\"success \":\"Ok\",\"message\":\"¡Participante agregado con exito!\",\"path\":\"/AdministracionProfesores/registrarParticipante\"}");
}


@RequestMapping(value = "/registrarParticipantes", method = RequestMethod.GET)
public String RegistrarParti(Model model,HttpServletRequest request){
    return "ProfesoresController/registrarParticipante";
}


public String saveConstancia(MultipartFile constancia){
    List<Profesor> alt = profRep.findHigherID();
    Profesor ultProfe = new Profesor();
    if (!alt.isEmpty()) {
        ultProfe = profRep.findHigherID().get(0);
    } else {
        ultProfe.setPk_id_profesor(0);
    }
    Integer idPath = ultProfe.getPk_id_profesor() + 1;
    String path = Integer.toString(idPath);
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
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return path;
}


@RequestMapping(value = "/registrarAsesor2", method = RequestMethod.GET)
public String RegistrarAsesores(Model model,HttpServletRequest request){
    return "ProfesoresController/registrarAsesor";
}


}