package co.edu.uniquindio.gri.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.model.Programa;
@RestController
@CrossOrigin
public class ProgramaFacultadController {

@Autowired
 private ProgramaFacultadService programafacultadservice;


@PutMapping
("/Facultad/{id}/Programa/setPrograma")
public void setPrograma(@PathVariable(name="id") long id,@RequestBody List<Programa> programa){
programafacultadservice.setPrograma(id,programa);
}


@GetMapping
("/Facultad/{id}/Programa/getPrograma")
public List<Programa> getPrograma(@PathVariable(name="id") long id){
return programafacultadservice.getPrograma(id);
}


}