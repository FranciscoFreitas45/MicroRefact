package co.edu.uniquindio.gri.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.model.Produccion;
@RestController
@CrossOrigin
public class ProduccionInvestigadorController {

@Autowired
 private ProduccionInvestigadorService produccioninvestigadorservice;


@PutMapping
("/Investigador/{id}/Produccion/setProducciones")
public void setProducciones(@PathVariable(name="id") long id,@RequestBody List<Produccion> producciones){
produccioninvestigadorservice.setProducciones(id,producciones);
}


@GetMapping
("/Investigador/{id}/Produccion/getProducciones")
public List<Produccion> getProducciones(@PathVariable(name="id") long id){
return produccioninvestigadorservice.getProducciones(id);
}


}