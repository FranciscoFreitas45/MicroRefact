package co.edu.uniquindio.gri.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.uniquindio.gri.dao.TipoDAO;
import co.edu.uniquindio.gri.model.Tipo;
@RestController
@RequestMapping("/rest/service")
public class TipoController {

@Autowired
 private TipoDAO tipoDAO;


@GetMapping("/tipos/{id}")
public ResponseEntity<Tipo> getTipoById(Long tipoId){
    Tipo tipo = tipoDAO.getTipoById(tipoId);
    if (tipo == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(tipo);
}


@GetMapping("/tipos")
public List<Tipo> getAllTipos(){
    return tipoDAO.getAllTipos();
}


}