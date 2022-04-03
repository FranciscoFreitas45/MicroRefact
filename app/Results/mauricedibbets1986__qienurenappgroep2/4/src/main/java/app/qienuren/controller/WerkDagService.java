package app.qienuren.controller;
 import app.qienuren.model.WerkDag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
@Service
@Transactional
public class WerkDagService {

@Autowired
 private WerkDagRepository werkdagrepository;


public WerkDag addNieuwWerkDag(WerkDag werkdag){
    System.out.println("Dag aangemaakt");
    return werkdagrepository.save(werkdag);
}


}