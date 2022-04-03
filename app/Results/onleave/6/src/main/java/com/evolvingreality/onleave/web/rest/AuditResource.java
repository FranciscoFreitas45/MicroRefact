package com.evolvingreality.onleave.web.rest;
 import com.evolvingreality.onleave.security.AuthoritiesConstants;
import com.evolvingreality.onleave.service.AuditEventService;
import com.evolvingreality.onleave.web.propertyeditors.LocaleDateTimeEditor;
import org.joda.time.LocalDateTime;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import java.util.List;
@RestController
@RequestMapping("/api")
public class AuditResource {

@Inject
 private  AuditEventService auditEventService;


@RequestMapping(value = "/audits/byDates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public List<AuditEvent> findByDates(LocalDateTime fromDate,LocalDateTime toDate){
    return auditEventService.findByDates(fromDate, toDate);
}


@InitBinder
public void initBinder(WebDataBinder binder){
    binder.registerCustomEditor(LocalDateTime.class, new LocaleDateTimeEditor("yyyy-MM-dd", false));
}


@RequestMapping(value = "/audits/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public List<AuditEvent> findAll(){
    return auditEventService.findAll();
}


}