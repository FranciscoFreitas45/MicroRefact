import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class ProfesorCertificadoController {

 private ProfesorCertificadoService profesorcertificadoservice;


@GetMapping
("/Certificado/{id}/Profesor/getFk_id_profesor")
public Profesor getFk_id_profesor(@PathVariable(name="id") int int){
profesorcertificadoservice.getFk_id_profesor(int);
}


@PutMapping
("/Certificado/{id}/Profesor/setFk_id_profesor")
public void setFk_id_profesor(@PathVariable(name="id") int int,@RequestBody Profesor fk_id_profesor){
profesorcertificadoservice.setFk_id_profesor(int,fk_id_profesor);
}


}