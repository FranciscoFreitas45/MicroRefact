package com.evolvingreality.onleave.service;
 import com.evolvingreality.onleave.config.audit.AuditEventConverter;
import com.evolvingreality.onleave.model.PersistentAuditEvent;
import com.evolvingreality.onleave.repository.PersistenceAuditEventRepository;
import org.joda.time.LocalDateTime;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;
import java.util.List;
@Service
@Transactional
public class AuditEventService {

@Inject
 private  PersistenceAuditEventRepository persistenceAuditEventRepository;

@Inject
 private  AuditEventConverter auditEventConverter;


public List<AuditEvent> findByDates(LocalDateTime fromDate,LocalDateTime toDate){
    List<PersistentAuditEvent> persistentAuditEvents = persistenceAuditEventRepository.findAllByAuditEventDateBetween(fromDate, toDate);
    return auditEventConverter.convertToAuditEvent(persistentAuditEvents);
}


public List<AuditEvent> findAll(){
    return auditEventConverter.convertToAuditEvent(persistenceAuditEventRepository.findAll());
}


}