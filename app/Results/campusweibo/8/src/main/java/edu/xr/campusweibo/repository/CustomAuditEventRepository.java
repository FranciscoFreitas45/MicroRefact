package edu.xr.campusweibo.repository;
 import edu.xr.campusweibo.config.Constants;
import edu.xr.campusweibo.config.audit.AuditEventConverter;
import edu.xr.campusweibo.domain.PersistentAuditEvent;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
@Repository
public class CustomAuditEventRepository implements AuditEventRepository{

 private  String AUTHORIZATION_FAILURE;

 private  PersistenceAuditEventRepository persistenceAuditEventRepository;

 private  AuditEventConverter auditEventConverter;


@Override
@Transactional(propagation = Propagation.REQUIRES_NEW)
public void add(AuditEvent event){
    if (!AUTHORIZATION_FAILURE.equals(event.getType()) && !Constants.ANONYMOUS_USER.equals(event.getPrincipal())) {
        PersistentAuditEvent persistentAuditEvent = new PersistentAuditEvent();
        persistentAuditEvent.setPrincipal(event.getPrincipal());
        persistentAuditEvent.setAuditEventType(event.getType());
        Instant instant = Instant.ofEpochMilli(event.getTimestamp().getTime());
        persistentAuditEvent.setAuditEventDate(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));
        persistentAuditEvent.setData(auditEventConverter.convertDataToStrings(event.getData()));
        persistenceAuditEventRepository.save(persistentAuditEvent);
    }
}


@Override
public List<AuditEvent> find(String principal,Date after,String type){
    Iterable<PersistentAuditEvent> persistentAuditEvents = persistenceAuditEventRepository.findByPrincipalAndAuditEventDateAfterAndAuditEventType(principal, LocalDateTime.from(after.toInstant()), type);
    return auditEventConverter.convertToAuditEvent(persistentAuditEvents);
}


}