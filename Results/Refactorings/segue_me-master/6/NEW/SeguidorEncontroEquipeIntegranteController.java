import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class SeguidorEncontroEquipeIntegranteController {

 private SeguidorEncontroEquipeIntegranteService seguidorencontroequipeintegranteservice;


@GetMapping
("/EncontroEquipeIntegrante/{id}/Seguidor/getSeguidor")
public Seguidor getSeguidor(@PathVariable(name="id") Integer Integer){
seguidorencontroequipeintegranteservice.getSeguidor(Integer);
}


@PutMapping
("/EncontroEquipeIntegrante/{id}/Seguidor/setSeguidor")
public void setSeguidor(@PathVariable(name="id") Integer Integer,@RequestBody Seguidor seguidor){
seguidorencontroequipeintegranteservice.setSeguidor(Integer,seguidor);
}


}