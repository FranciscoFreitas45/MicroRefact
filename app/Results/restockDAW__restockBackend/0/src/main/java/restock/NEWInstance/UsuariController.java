package restock.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UsuariController {

 private Usuari usuari;

 private Usuari usuari;


@PutMapping
("/setOrganitzacio")
public void setOrganitzacio(@RequestParam(name = "organitzacio") Organitzacio organitzacio){
usuari.setOrganitzacio(organitzacio);
}


}