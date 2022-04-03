package co.edu.uniquindio.gri.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.model.Centro;
@RestController
@CrossOrigin
public class CentroGrupoController {

@Autowired
 private CentroGrupoService centrogruposervice;


@GetMapping
("/Grupo/{id}/Centro/getCentro")
public Centro getCentro(@PathVariable(name="id") long id){
return centrogruposervice.getCentro(id);
}


@PutMapping
("/Grupo/{id}/Centro/setCentro")
public void setCentro(@PathVariable(name="id") long id,@RequestBody Centro centro){
centrogruposervice.setCentro(id,centro);
}


}