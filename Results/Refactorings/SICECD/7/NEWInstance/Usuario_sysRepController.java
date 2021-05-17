import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class Usuario_sysRepController {

 private Usuario_sysRep usuario_sysrep;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return usuario_sysrep.findById(Object);
}


@GetMapping
("/findByRfc")
public List<Usuario_sys> findByRfc(@RequestParam(name = "name") String name){
  return usuario_sysrep.findByRfc(name);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return usuario_sysrep.findById(Object);
}


@GetMapping
("/findByRfc")
public List<Usuario_sys> findByRfc(@RequestParam(name = "name") String name){
  return usuario_sysrep.findByRfc(name);
}


}