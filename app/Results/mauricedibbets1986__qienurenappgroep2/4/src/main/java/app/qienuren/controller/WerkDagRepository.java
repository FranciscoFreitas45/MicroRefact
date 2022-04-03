package app.qienuren.controller;
 import app.qienuren.model.WerkDag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface WerkDagRepository extends CrudRepository<WerkDag, Long>{


}