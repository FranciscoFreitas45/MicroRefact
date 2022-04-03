package com.cocay.sicecd.controller;
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
import com.cocay.sicecd.Interface.InscripcionRep;
import com.cocay.sicecd.Interface.GrupoRep;
import com.cocay.sicecd.Interface.CursoRep;
import com.cocay.sicecd.Interface.Tipo_cursoRep;
import com.cocay.sicecd.Interface.Logging;
@Controller
@RequestMapping("AdministracionModificaciones")
@PropertySource("classpath:application.properties")
public class ModificarAsesores {

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


@RequestMapping(value = "/listaAsesores", method = RequestMethod.GET)
public ModelAndView consultarAsesores(ModelMap model){
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
    // Se filtran todos los profesores que no tengan fecha de nacimiento
    list_p1 = list_p1.stream().filter(x -> x.getFk_id_estado().getPk_id_estado() == 34).collect(Collectors.toList());
    if (!list_p1.isEmpty()) {
        model.put("asesores", list_p1);
        return new ModelAndView("ModificarUsuario/listaAsesores", model);
    } else {
        return new ModelAndView("/Avisos/ErrorBusqueda");
    }
}


@PostMapping(value = "/editarasesor")
public ResponseEntity<String> editarAsesor(ProfesorDto profesor){
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
                mod.setFechaNac(calendario.getTime());
            }
        }
    }
    System.out.println(cambios);
    log.setTrace(LogTypes.MODIFICAR_ASESOR);
    if (!cambios.equals("")) {
        proRep.save(mod);
    }
    return ResponseEntity.ok("¡Asesor editado con exito!");
}


@GetMapping(value = "/pantallaModificacionA")
public ModelAndView formEditarAsesor(String rfc){
    Profesor cambio = (proRep.findByRfc(rfc));
    ProfesorDto pdt = new ProfesorDto();
    pdt.setaMaterno(cambio.getApellido_materno());
    pdt.setaPaterno(cambio.getApellido_paterno());
    pdt.setNombres(cambio.getNombre());
    pdt.setCorreo(cambio.getCorreo());
    if (cambio.getFechaNac() != null) {
        pdt.setfNacimiento(cambio.getFechaNac().toString().substring(0, 10));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String ffecha = formatter.format(cambio.getFechaNac());
        pdt.setFormFecha(ffecha);
    }
    pdt.setGenero(cambio.getGenero().toString());
    pdt.setIdProfesor(cambio.getPk_id_profesor());
    pdt.setRfc(cambio.getRfc());
    pdt.setTelefono(cambio.getTelefono());
    ModelAndView model = new ModelAndView("ModificarUsuario/pantallaModificacionA");
    model.addObject("asesor", pdt);
    return model;
}


}