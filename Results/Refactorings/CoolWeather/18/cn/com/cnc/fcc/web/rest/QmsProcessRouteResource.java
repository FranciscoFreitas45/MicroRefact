import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsProcessRoute;
import cn.com.cnc.fcc.repository.QmsProcessRouteRepository;
import cn.com.cnc.fcc.web.rest.errors.BadRequestAlertException;
import cn.com.cnc.fcc.web.rest.util.HeaderUtil;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class QmsProcessRouteResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsProcessRouteRepository qmsProcessRouteRepository;


@PutMapping("/qms-process-routes")
@Timed
public ResponseEntity<QmsProcessRoute> updateQmsProcessRoute(QmsProcessRoute qmsProcessRoute){
    log.debug("REST request to update QmsProcessRoute : {}", qmsProcessRoute);
    if (qmsProcessRoute.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsProcessRoute result = qmsProcessRouteRepository.save(qmsProcessRoute);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsProcessRoute.getId().toString())).body(result);
}


@GetMapping("/qms-process-routes/{id}")
@Timed
public ResponseEntity<QmsProcessRoute> getQmsProcessRoute(Long id){
    log.debug("REST request to get QmsProcessRoute : {}", id);
    Optional<QmsProcessRoute> qmsProcessRoute = qmsProcessRouteRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsProcessRoute);
}


@PostMapping("/qms-process-routes")
@Timed
public ResponseEntity<QmsProcessRoute> createQmsProcessRoute(QmsProcessRoute qmsProcessRoute){
    log.debug("REST request to save QmsProcessRoute : {}", qmsProcessRoute);
    if (qmsProcessRoute.getId() != null) {
        throw new BadRequestAlertException("A new qmsProcessRoute cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsProcessRoute result = qmsProcessRouteRepository.save(qmsProcessRoute);
    return ResponseEntity.created(new URI("/api/qms-process-routes/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-process-routes")
@Timed
public ResponseEntity<List<QmsProcessRoute>> getAllQmsProcessRoutes(Pageable pageable){
    log.debug("REST request to get a page of QmsProcessRoutes");
    Page<QmsProcessRoute> page = qmsProcessRouteRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-process-routes");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@DeleteMapping("/qms-process-routes/{id}")
@Timed
public ResponseEntity<Void> deleteQmsProcessRoute(Long id){
    log.debug("REST request to delete QmsProcessRoute : {}", id);
    qmsProcessRouteRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


}