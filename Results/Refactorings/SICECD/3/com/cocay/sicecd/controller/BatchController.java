import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cocay.sicecd.dto.CursoDto;
import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Grupo;
import com.cocay.sicecd.model.Profesor;
import com.cocay.sicecd.repo.CursoRep;
import com.cocay.sicecd.repo.GrupoRep;
import com.cocay.sicecd.repo.InscripcionRep;
import com.cocay.sicecd.repo.ProfesorRep;
import com.cocay.sicecd.service.SendMailService;
@Controller
public class BatchController {

@Autowired
 private JobLauncher jobLauncher;

@Autowired
@Qualifier("jobCurso")
 private Job jobCurso;

@Autowired
@Qualifier("jobGrupo")
 private Job jobGrupo;

@Autowired
@Qualifier("jobProfesor")
 private Job jobProfesor;

@Autowired
@Qualifier("jobInscripcion")
 private Job jobInscripcion;

@Autowired
@Qualifier("excelFileToDatabaseJob")
 private Job excelFileToDatabaseJob;

@Autowired
 private CursoRep curso;

@Autowired
 private GrupoRep grupo;

@Autowired
 private ProfesorRep profesor;

@Autowired
 private InscripcionRep inscripcion;

@Autowired
 private SendMailService _email;

 private String carpeta;

 private  String UPLOADED_FOLDER;


@RequestMapping(value = "/consultarInscripcionBatch", method = RequestMethod.GET)
public ModelAndView consultarInscripcionBatch(ModelMap model,HttpServletRequest request){
    return new ModelAndView("/BatchTemplate/consultarInscripcionBatch");
}


@RequestMapping(value = "/consultarCursos", method = RequestMethod.GET)
public ModelAndView consultarCursos(ModelMap model,HttpServletRequest request){
    List<Curso> list_p = curso.loadAllCursos();
    if (!list_p.isEmpty()) {
        model.put("cursos", list_p);
        return new ModelAndView("/BatchTemplate/consultarCursos", model);
    } else {
        return new ModelAndView("/BatchTemplate/consultarCursos");
    }
}


@RequestMapping(value = "/consultarProfesor", method = RequestMethod.GET)
public ModelAndView consultarProfesor(ModelMap model,HttpServletRequest request){
    List<Profesor> list_p = profesor.loadAllProfesor();
    if (!list_p.isEmpty()) {
        model.put("profesores", list_p);
        return new ModelAndView("/BatchTemplate/consultarProfesor", model);
    } else {
        return new ModelAndView("/BatchTemplate/consultarProfesor");
    }
}


@RequestMapping(value = "/pruebaBatch", method = RequestMethod.GET)
public ModelAndView pruebaBatch(ModelMap model,HttpServletRequest request){
    List<Profesor> list_p = profesor.loadAllProfesor();
    if (!list_p.isEmpty()) {
        model.put("profesores", list_p);
        return new ModelAndView("/BatchTemplate/prueba", model);
    } else {
        return new ModelAndView("/BatchTemplate/prueba");
    }
}


// //new annotation since 4.3
@PostMapping("/consultarGrupo")
public ModelAndView singleFilegrupo(MultipartFile file){
    try {
        // Get the file and save it somewhere
        byte[] bytes = file.getBytes();
        Path ruta = Paths.get(UPLOADED_FOLDER);
        if (!Files.exists(ruta)) {
            Files.createDirectory(ruta);
        }
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
        File fileToImport = new File(UPLOADED_FOLDER + file.getOriginalFilename());
        // Launch the Batch Job
        JobExecution jobExecution = jobLauncher.run(jobGrupo, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).addString("fullPathFileName", fileToImport.getAbsolutePath()).toJobParameters());
        fileToImport.delete();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return new ModelAndView("BatchTemplate/consultarGrupo");
}


@RequestMapping(value = "/AvisosCorreo", method = RequestMethod.GET)
public ModelAndView avisos(ModelMap model,HttpServletRequest request){
    return new ModelAndView("/CorreosTemplate/AvisosCorreo", model);
}


// //new annotation since 4.3
@PostMapping("/consultarInscripcionBatch")
public ModelAndView singleFileinscripcion(MultipartFile file){
    try {
        // Get the file and save it somewhere
        byte[] bytes = file.getBytes();
        Path ruta = Paths.get(UPLOADED_FOLDER);
        if (!Files.exists(ruta)) {
            Files.createDirectory(ruta);
        }
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
        File fileToImport = new File(UPLOADED_FOLDER + file.getOriginalFilename());
        // Launch the Batch Job
        JobExecution jobExecution = jobLauncher.run(jobInscripcion, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).addString("fullPathFileName", fileToImport.getAbsolutePath()).toJobParameters());
        fileToImport.delete();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return new ModelAndView("BatchTemplate/consultarInscripcionBatch");
}


@RequestMapping(value = "/BusquedaCorreo", method = RequestMethod.POST)
public ModelAndView busqueda(ModelMap model,HttpServletRequest request){
    String nombre = request.getParameter("nombre").toUpperCase();
    ModelAndView mv = new ModelAndView("search::search_list");
    List<Profesor> list_p1 = profesor.findAll();
    List<Profesor> list_p2 = profesor.findAll();
    // Filtrando por Nombre
    if (nombre != "") {
        for (Profesor p : list_p1) {
            String nom = p.getNombre().toUpperCase();
            if (!nom.contains(nombre)) {
                list_p2.remove(p);
            }
        }
    }
    if (!list_p1.isEmpty()) {
        model.put("profesores", list_p2);
        mv.addObject("searchList", list_p2);
        return new ModelAndView("/CorreosTemplate/AvisosCorreo", model);
    } else {
        return new ModelAndView("/CorreosTemplate/AvisosCorreo", model);
    }
}


// //new annotation since 4.3
@PostMapping("/consultarProfesor")
public ModelAndView singleFileprofesor(MultipartFile file){
    try {
        // Get the file and save it somewhere
        byte[] bytes = file.getBytes();
        Path ruta = Paths.get(UPLOADED_FOLDER);
        if (!Files.exists(ruta)) {
            Files.createDirectory(ruta);
        }
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
        File fileToImport = new File(UPLOADED_FOLDER + file.getOriginalFilename());
        // Launch the Batch Job
        JobExecution jobExecution = jobLauncher.run(jobProfesor, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).addString("fullPathFileName", fileToImport.getAbsolutePath()).toJobParameters());
        fileToImport.delete();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return new ModelAndView("BatchTemplate/consultarProfesor");
}


// //new annotation since 4.3
@PostMapping("/consultarCursos")
public ModelAndView singleFileUpload(MultipartFile file){
    try {
        // Get the file and save it somewhere
        byte[] bytes = file.getBytes();
        Path ruta = Paths.get(UPLOADED_FOLDER);
        if (!Files.exists(ruta)) {
            Files.createDirectory(ruta);
        }
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
        File fileToImport = new File(UPLOADED_FOLDER + file.getOriginalFilename());
        // Launch the Batch Job
        JobExecution jobExecution = jobLauncher.run(jobCurso, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).addString("fullPathFileName", fileToImport.getAbsolutePath()).toJobParameters());
        fileToImport.delete();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return new ModelAndView("BatchTemplate/consultarCursos");
}


@RequestMapping(value = "/consultarGrupo", method = RequestMethod.GET)
public ModelAndView consultarGrupo(ModelMap model,HttpServletRequest request){
    List<Grupo> list_p = grupo.loadAllGrupo();
    if (!list_p.isEmpty()) {
        model.put("grupos", list_p);
        return new ModelAndView("/BatchTemplate/consultarGrupo", model);
    } else {
        return new ModelAndView("/BatchTemplate/consultarGrupo");
    }
}


@RequestMapping(value = "/runExcel", method = RequestMethod.GET)
public ModelAndView insertaExcel(ModelMap model,HttpServletRequest request){
    Logger logger = LoggerFactory.getLogger(this.getClass());
    try {
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(excelFileToDatabaseJob, jobParameters);
    } catch (Exception e) {
        logger.info(e.getMessage());
    }
    return new ModelAndView("/BatchTemplate/consultarProfesor");
}


}