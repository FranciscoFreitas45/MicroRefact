import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsUnqualifiedProductDetails;
import cn.com.cnc.fcc.repository.QmsUnqualifiedProductDetailsRepository;
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
public class QmsUnqualifiedProductDetailsResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsUnqualifiedProductDetailsRepository qmsUnqualifiedProductDetailsRepository;


@PostMapping("/qms-unqualified-product-details")
@Timed
public ResponseEntity<QmsUnqualifiedProductDetails> createQmsUnqualifiedProductDetails(QmsUnqualifiedProductDetails qmsUnqualifiedProductDetails){
    log.debug("REST request to save QmsUnqualifiedProductDetails : {}", qmsUnqualifiedProductDetails);
    if (qmsUnqualifiedProductDetails.getId() != null) {
        throw new BadRequestAlertException("A new qmsUnqualifiedProductDetails cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsUnqualifiedProductDetails result = qmsUnqualifiedProductDetailsRepository.save(qmsUnqualifiedProductDetails);
    return ResponseEntity.created(new URI("/api/qms-unqualified-product-details/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@PutMapping("/qms-unqualified-product-details")
@Timed
public ResponseEntity<QmsUnqualifiedProductDetails> updateQmsUnqualifiedProductDetails(QmsUnqualifiedProductDetails qmsUnqualifiedProductDetails){
    log.debug("REST request to update QmsUnqualifiedProductDetails : {}", qmsUnqualifiedProductDetails);
    if (qmsUnqualifiedProductDetails.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsUnqualifiedProductDetails result = qmsUnqualifiedProductDetailsRepository.save(qmsUnqualifiedProductDetails);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsUnqualifiedProductDetails.getId().toString())).body(result);
}


@GetMapping("/qms-unqualified-product-details")
@Timed
public ResponseEntity<List<QmsUnqualifiedProductDetails>> getAllQmsUnqualifiedProductDetails(Pageable pageable){
    log.debug("REST request to get a page of QmsUnqualifiedProductDetails");
    Page<QmsUnqualifiedProductDetails> page = qmsUnqualifiedProductDetailsRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-unqualified-product-details");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@DeleteMapping("/qms-unqualified-product-details/{id}")
@Timed
public ResponseEntity<Void> deleteQmsUnqualifiedProductDetails(Long id){
    log.debug("REST request to delete QmsUnqualifiedProductDetails : {}", id);
    qmsUnqualifiedProductDetailsRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@GetMapping("/qms-unqualified-product-details/{id}")
@Timed
public ResponseEntity<QmsUnqualifiedProductDetails> getQmsUnqualifiedProductDetails(Long id){
    log.debug("REST request to get QmsUnqualifiedProductDetails : {}", id);
    Optional<QmsUnqualifiedProductDetails> qmsUnqualifiedProductDetails = qmsUnqualifiedProductDetailsRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsUnqualifiedProductDetails);
}


}