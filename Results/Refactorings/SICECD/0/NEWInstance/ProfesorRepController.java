import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class ProfesorRepController {

 private ProfesorRep profesorrep;


@GetMapping
("/findByCompleteNameList")
public List<Profesor> findByCompleteNameList(@RequestParam(name = "nombre") String nombre,@RequestParam(name = "apellido_paterno") String apellido_paterno,@RequestParam(name = "apellido_materno") String apellido_materno){
  return profesorrep.findByCompleteNameList(nombre,apellido_paterno,apellido_materno);
}


}