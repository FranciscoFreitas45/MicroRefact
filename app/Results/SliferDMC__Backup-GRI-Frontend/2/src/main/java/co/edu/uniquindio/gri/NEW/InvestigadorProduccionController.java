package co.edu.uniquindio.gri.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.model.Investigador;
@RestController
@CrossOrigin
public class InvestigadorProduccionController {

@Autowired
 private InvestigadorProduccionService investigadorproduccionservice;


@GetMapping
("/Produccion/{id}/Investigador/getInvestigador")
public Investigador getInvestigador(@PathVariable(name="id") long id){
return investigadorproduccionservice.getInvestigador(id);
}


@PutMapping
("/Produccion/{id}/Investigador/setInvestigador")
public void setInvestigador(@PathVariable(name="id") long id,@RequestBody Investigador investigador){
investigadorproduccionservice.setInvestigador(id,investigador);
}


}