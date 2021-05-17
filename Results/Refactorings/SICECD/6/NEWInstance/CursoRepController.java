import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class CursoRepController {

 private CursoRep cursorep;


@GetMapping
("/findByUniqueClaveCurso")
public Curso findByUniqueClaveCurso(@RequestParam(name = "clave") String clave){
  return cursorep.findByUniqueClaveCurso(clave);
}


@PutMapping
("/saveC")
public void saveC(@RequestParam(name = "clave") String clave,@RequestParam(name = "nombre") String nombre){
cursorep.saveC(clave,nombre);
}


@GetMapping
("/findByUniqueClaveCurso")
public Curso findByUniqueClaveCurso(@RequestParam(name = "clave") String clave){
  return cursorep.findByUniqueClaveCurso(clave);
}


@GetMapping
("/findByUniqueClaveCurso")
public Curso findByUniqueClaveCurso(@RequestParam(name = "clave") String clave){
  return cursorep.findByUniqueClaveCurso(clave);
}


}