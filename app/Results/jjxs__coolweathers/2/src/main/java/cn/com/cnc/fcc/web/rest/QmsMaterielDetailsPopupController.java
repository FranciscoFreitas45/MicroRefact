package cn.com.cnc.fcc.web.rest;
 import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsMateriel;
import cn.com.cnc.fcc.repository.QmsMaterielRepository;
import cn.com.cnc.fcc.service.QmsMaterielDetailsPopupService;
import cn.com.cnc.fcc.service.dto.QmsMaterielDetailsPopupDTO;
import cn.com.cnc.fcc.service.util.PageUtil;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
@RestController
@RequestMapping("/api")
public class QmsMaterielDetailsPopupController {

 private  Logger log;

@Autowired
 private  QmsMaterielDetailsPopupService qmsMaterielDetailsPopupService;

@Resource
 private  PageUtil pageUtil;

 private  QmsMaterielRepository qmsMaterielRepository;

public QmsMaterielDetailsPopupController(QmsMaterielRepository qmsMaterielRepository) {
    this.qmsMaterielRepository = qmsMaterielRepository;
}
@GetMapping("/qms-materiel-details/popupData")
@Timed
public ResponseEntity<List<QmsMaterielDetailsPopupDTO>> getAllQmsMaterielTypes(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of getAllQmsMaterielTypes");
    String materielId = request.getParameter("materielId");
    String supplierName = request.getParameter("supplierName");
    List<QmsMaterielDetailsPopupDTO> materialTypeSelectionDtos = qmsMaterielDetailsPopupService.qmsMaterialTypeFindAll(materielId, supplierName);
    Page<QmsMaterielDetailsPopupDTO> page = pageUtil.listToPage(materialTypeSelectionDtos, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-materiel-types/popupData");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-materiel-details/New/{id}")
@Timed
public ResponseEntity<QmsMateriel> getQmsMaterielDetails(Long id){
    log.debug("REST request to get QmsMaterielDetails : {}", id);
    Optional<QmsMateriel> qmsMaterielDetails = qmsMaterielRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsMaterielDetails);
}


}