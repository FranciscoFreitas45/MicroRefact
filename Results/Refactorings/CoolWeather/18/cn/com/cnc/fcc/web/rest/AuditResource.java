import cn.com.cnc.fcc.service.AuditEventService;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import java.time.LocalDate;
import java.time.ZoneId;
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
    Page<AuditEvent> page = auditEventService.findByDates(fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant(), toDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).toInstant(), pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/management/audits");
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
}


}