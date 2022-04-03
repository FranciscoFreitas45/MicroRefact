package org.jugbd.mnet.audit;
 import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.jugbd.mnet.dao.AuditLogDao;
import org.jugbd.mnet.domain.AuditLog;
import org.jugbd.mnet.domain.Auditable;
import org.jugbd.mnet.domain.enums.AuditAction;
import org.jugbd.mnet.web.ApplicationContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.jugbd.mnet.DTO.Auditable;
import org.jugbd.mnet.DTO.Auditable;
@Component
public class AuditLogInterceptor extends EmptyInterceptor{

 private  Logger log;

 private  Set inserts;

 private  Set updates;

 private  Set deletes;


@Override
public boolean onFlushDirty(Object entity,Serializable id,Object[] currentState,Object[] previousState,String[] propertyNames,Type[] types){
    log.debug("onFlushDirty()");
    if (entity instanceof Auditable) {
        updates.add(entity);
    }
    return false;
}


@Override
public void onDelete(Object entity,Serializable id,Object[] state,String[] propertyNames,Type[] types){
    log.debug("onDelete()");
    if (entity instanceof Auditable) {
        deletes.add(entity);
    }
}


@Override
public void preFlush(Iterator entities){
    log.debug("preFlush()");
}


@Override
public boolean onSave(Object entity,Serializable id,Object[] state,String[] propertyNames,Type[] types){
    log.debug("onSave()");
    if (entity instanceof Auditable) {
        inserts.add(entity);
    }
    return false;
}


@Override
public void postFlush(Iterator entities){
    log.debug("postFlush()");
    AuditLogDao auditLogDao = (AuditLogDao) ApplicationContextProvider.getApplicationContext().getBean("auditLogDao");
    try {
        for (Iterator it = inserts.iterator(); it.hasNext(); ) {
            Auditable entity = (Auditable) it.next();
            logEvent(AuditAction.CREATED, entity, auditLogDao);
        }
        for (Iterator it = updates.iterator(); it.hasNext(); ) {
            Auditable entity = (Auditable) it.next();
            logEvent(AuditAction.UPDATED, entity, auditLogDao);
        }
        for (Iterator it = deletes.iterator(); it.hasNext(); ) {
            Auditable entity = (Auditable) it.next();
            logEvent(AuditAction.DELETED, entity, auditLogDao);
        }
    } finally {
        inserts.clear();
        updates.clear();
        deletes.clear();
    }
}


public String make(Object entity){
    try {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        mapper.registerModule(new Hibernate4Module());
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        ObjectWriter writer = mapper.writer();
        return writer.writeValueAsString(entity);
    } catch (IOException e) {
        log.error("Can't serialize entity", e);
    }
    return null;
}


public void logEvent(AuditAction action,Auditable entity,AuditLogDao auditLogDao){
    log.debug(" action ={}, entity.getId()={}, entity.getLogDetail()={}", action, entity.getId());
    AuditLog auditLog = new AuditLog();
    auditLog.setAction(action);
    auditLog.setDetail(make(entity));
    auditLog.setEntityName(entity.getClass().getName());
    auditLog.setEntityId(entity.getId());
    auditLogDao.save(auditLog);
}


}