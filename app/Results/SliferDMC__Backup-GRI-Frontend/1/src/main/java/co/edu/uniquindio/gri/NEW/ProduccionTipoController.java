package co.edu.uniquindio.gri.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.model.Produccion;
@RestController
@CrossOrigin
public class ProduccionTipoController {

@Autowired
 private ProduccionTipoService producciontiposervice;


@GetMapping
("/Tipo/{id}/Produccion/getProduccion")
public List<Produccion> getProduccion(@PathVariable(name="id") long id){
return producciontiposervice.getProduccion(id);
}


@PutMapping
("/Tipo/{id}/Produccion/setProduccion")
public void setProduccion(@PathVariable(name="id") long id,@RequestBody List<Produccion> produccion){
producciontiposervice.setProduccion(id,produccion);
}


}