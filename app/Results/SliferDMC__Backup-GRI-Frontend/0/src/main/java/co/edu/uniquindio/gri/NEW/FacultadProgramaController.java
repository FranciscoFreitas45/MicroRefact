package co.edu.uniquindio.gri.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.model.Facultad;
@RestController
@CrossOrigin
public class FacultadProgramaController {

@Autowired
 private FacultadProgramaService facultadprogramaservice;


@PutMapping
("/Programa/{id}/Facultad/setFacultad")
public void setFacultad(@PathVariable(name="id") long id,@RequestBody Facultad facultad){
facultadprogramaservice.setFacultad(id,facultad);
}


@GetMapping
("/Programa/{id}/Facultad/getFacultad")
public Facultad getFacultad(@PathVariable(name="id") long id){
return facultadprogramaservice.getFacultad(id);
}


}