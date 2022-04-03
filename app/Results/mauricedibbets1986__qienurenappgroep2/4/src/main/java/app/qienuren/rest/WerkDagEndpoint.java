package app.qienuren.rest;
 import app.qienuren.controller.WerkDagService;
import app.qienuren.model.WerkDag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/werkdag")
public class WerkDagEndpoint {

@Autowired
 private WerkDagService werkdagservice;


@PostMapping("/nieuw")
public WerkDag nieuwWerkDag(WerkDag werkdag){
    System.out.println();
    return werkdagservice.addNieuwWerkDag(werkdag);
}


}