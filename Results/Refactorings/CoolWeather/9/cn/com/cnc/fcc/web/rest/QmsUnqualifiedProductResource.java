import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsUnqualifiedProduct;
import cn.com.cnc.fcc.repository.QmsUnqualifiedProductRepository;
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
public class QmsUnqualifiedProductResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsUnqualifiedProductRepository qmsUnqualifiedProductRepository;


@DeleteMapping("/qms-unqualified-products/{id}")
@Timed
public ResponseEntity<Void> deleteQmsUnqualifiedProduct(Long id){
    log.debug("REST request to delete QmsUnqualifiedProduct : {}", id);
    qmsUnqualifiedProductRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PutMapping("/qms-unqualified-products")
@Timed
public ResponseEntity<QmsUnqualifiedProduct> updateQmsUnqualifiedProduct(QmsUnqualifiedProduct qmsUnqualifiedProduct){
    log.debug("REST request to update QmsUnqualifiedProduct : {}", qmsUnqualifiedProduct);
    if (qmsUnqualifiedProduct.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsUnqualifiedProduct result = qmsUnqualifiedProductRepository.save(qmsUnqualifiedProduct);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsUnqualifiedProduct.getId().toString())).body(result);
}


@GetMapping("/qms-unqualified-products/{id}")
@Timed
public ResponseEntity<QmsUnqualifiedProduct> getQmsUnqualifiedProduct(Long id){
    log.debug("REST request to get QmsUnqualifiedProduct : {}", id);
    Optional<QmsUnqualifiedProduct> qmsUnqualifiedProduct = qmsUnqualifiedProductRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsUnqualifiedProduct);
}


@PostMapping("/qms-unqualified-products")
@Timed
public ResponseEntity<QmsUnqualifiedProduct> createQmsUnqualifiedProduct(QmsUnqualifiedProduct qmsUnqualifiedProduct){
    log.debug("REST request to save QmsUnqualifiedProduct : {}", qmsUnqualifiedProduct);
    if (qmsUnqualifiedProduct.getId() != null) {
        throw new BadRequestAlertException("A new qmsUnqualifiedProduct cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsUnqualifiedProduct result = qmsUnqualifiedProductRepository.save(qmsUnqualifiedProduct);
    return ResponseEntity.created(new URI("/api/qms-unqualified-products/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-unqualified-products")
@Timed
public ResponseEntity<List<QmsUnqualifiedProduct>> getAllQmsUnqualifiedProducts(Pageable pageable){
    log.debug("REST request to get a page of QmsUnqualifiedProducts");
    Page<QmsUnqualifiedProduct> page = qmsUnqualifiedProductRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-unqualified-products");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


}