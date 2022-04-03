package co.edu.uniquindio.gri.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.model.Tipo;
@RestController
@CrossOrigin
public class TipoProduccionController {

@Autowired
 private TipoProduccionService tipoproduccionservice;


@PutMapping
("/Produccion/{id}/Tipo/setTipo")
public void setTipo(@PathVariable(name="id") long id,@RequestBody Tipo tipo){
tipoproduccionservice.setTipo(id,tipo);
}


@GetMapping
("/Produccion/{id}/Tipo/getTipo")
public Tipo getTipo(@PathVariable(name="id") long id){
return tipoproduccionservice.getTipo(id);
}


}