package edu.xr.campusweibo.web.rest;
 import edu.xr.campusweibo.service.AuditEventService;
import edu.xr.campusweibo.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/management/audits")
public class AuditResource {

 private  AuditEventService auditEventService;


@GetMapping
public ResponseEntity<List<AuditEvent>> getAll(Pageable pageable){
    Page<AuditEvent> page = auditEventService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/management/audits");
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
}


@GetMapping("/{id:.+}")
public ResponseEntity<AuditEvent> get(Long id){
    return ResponseUtil.wrapOrNotFound(auditEventService.find(id));
}


@GetMapping(params = { "fromDate", "toDate" })
public ResponseEntity<List<AuditEvent>> getByDates(LocalDate fromDate,LocalDate toDate,Pageable pageable){
    Page<AuditEvent> page = auditEventService.findByDates(fromDate.atTime(0, 0), toDate.atTime(23, 59), pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/management/audits");
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
}


}