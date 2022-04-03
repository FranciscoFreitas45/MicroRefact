package co.edu.uniquindio.gri.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.model.Grupo;
@RestController
@CrossOrigin
public class GrupoCentroController {

@Autowired
 private GrupoCentroService grupocentroservice;


@PutMapping
("/Centro/{id}/Grupo/setGrupo")
public void setGrupo(@PathVariable(name="id") long id,@RequestBody List<Grupo> grupo){
grupocentroservice.setGrupo(id,grupo);
}


@GetMapping
("/Centro/{id}/Grupo/getGrupo")
public List<Grupo> getGrupo(@PathVariable(name="id") long id){
return grupocentroservice.getGrupo(id);
}


}