import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.HstServerInfo;
import cn.com.cnc.fcc.repository.HstServerInfoRepository;
import cn.com.cnc.fcc.web.rest.errors.BadRequestAlertException;
import cn.com.cnc.fcc.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class HstServerInfoResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  HstServerInfoRepository hstServerInfoRepository;


@DeleteMapping("/hst-server-infos/{id}")
@Timed
public ResponseEntity<Void> deleteHstServerInfo(Long id){
    log.debug("REST request to delete HstServerInfo : {}", id);
    hstServerInfoRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@GetMapping("/hst-server-infos/{id}")
@Timed
public ResponseEntity<HstServerInfo> getHstServerInfo(Long id){
    log.debug("REST request to get HstServerInfo : {}", id);
    Optional<HstServerInfo> hstServerInfo = hstServerInfoRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(hstServerInfo);
}


@GetMapping("/hst-server-infos")
@Timed
public List<HstServerInfo> getAllHstServerInfos(){
    log.debug("REST request to get all HstServerInfos");
    return hstServerInfoRepository.findAll();
}


@PutMapping("/hst-server-infos")
@Timed
public ResponseEntity<HstServerInfo> updateHstServerInfo(HstServerInfo hstServerInfo){
    log.debug("REST request to update HstServerInfo : {}", hstServerInfo);
    if (hstServerInfo.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    HstServerInfo result = hstServerInfoRepository.save(hstServerInfo);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hstServerInfo.getId().toString())).body(result);
}


@PostMapping("/hst-server-infos")
@Timed
public ResponseEntity<HstServerInfo> createHstServerInfo(HstServerInfo hstServerInfo){
    log.debug("REST request to save HstServerInfo : {}", hstServerInfo);
    if (hstServerInfo.getId() != null) {
        throw new BadRequestAlertException("A new hstServerInfo cannot already have an ID", ENTITY_NAME, "idexists");
    }
    HstServerInfo result = hstServerInfoRepository.save(hstServerInfo);
    return ResponseEntity.created(new URI("/api/hst-server-infos/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


}