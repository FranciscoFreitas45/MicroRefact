import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class EncontroCirculoEncontroController {

 private EncontroCirculoEncontroService encontrocirculoencontroservice;


@GetMapping
("/Encontro/{id}/EncontroCirculo/getEncontroCirculoList")
public List<EncontroCirculo> getEncontroCirculoList(@PathVariable(name="id") Integer id){
encontrocirculoencontroservice.getEncontroCirculoList(id);
}


@PutMapping
("/Encontro/{id}/EncontroCirculo/setEncontroCirculoList")
public void setEncontroCirculoList(@PathVariable(name="id") Integer id,@RequestBody List<EncontroCirculo> encontroCirculoList){
encontrocirculoencontroservice.setEncontroCirculoList(id,encontroCirculoList);
}


}