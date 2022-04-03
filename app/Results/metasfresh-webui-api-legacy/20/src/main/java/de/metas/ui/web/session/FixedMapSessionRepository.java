package de.metas.ui.web.session;
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionExpiredEvent;
import com.google.common.base.Stopwatch;
import de.metas.logging.LogManager;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
@ToString(of = { "defaultMaxInactiveInterval" })
public class FixedMapSessionRepository implements SessionRepository<ExpiringSession>{

 private  Logger logger;

 private  Map<String,ExpiringSession> sessions;

 private  ApplicationEventPublisher applicationEventPublisher;

 private  Integer defaultMaxInactiveInterval;


public void purgeExpiredSessionsNoFail(){
    try {
        purgeExpiredSessions();
    } catch (final Throwable ex) {
        logger.warn("Failed purging expired sessions. Ignored.", ex);
    }
}


@Override
public void save(ExpiringSession session){
    sessions.put(session.getId(), new MapSession(session));
}


public void deleteAndFireEvent(String id,boolean expired){
    final ExpiringSession deletedSession = sessions.remove(id);
    // Fire event
    if (deletedSession != null) {
        if (expired) {
            applicationEventPublisher.publishEvent(new SessionExpiredEvent(this, id));
        } else {
            applicationEventPublisher.publishEvent(new SessionDeletedEvent(this, id));
        }
    }
}


@Override
public ExpiringSession getSession(String id){
    final ExpiringSession saved = sessions.get(id);
    if (saved == null) {
        return null;
    }
    if (saved.isExpired()) {
        final boolean expired = true;
        deleteAndFireEvent(saved.getId(), expired);
        return null;
    }
    return new MapSession(saved);
}


@Override
public void delete(String id){
    final boolean expired = false;
    deleteAndFireEvent(id, expired);
}


@Override
public ExpiringSession createSession(){
    final ExpiringSession result = new MapSession();
    if (defaultMaxInactiveInterval != null) {
        result.setMaxInactiveIntervalInSeconds(defaultMaxInactiveInterval);
    }
    // Fire event
    applicationEventPublisher.publishEvent(new SessionCreatedEvent(this, result.getId()));
    return result;
}


public void purgeExpiredSessions(){
    final Stopwatch stopwatch = Stopwatch.createStarted();
    int countExpiredSessions = 0;
    final List<ExpiringSession> sessionsToCheck = new ArrayList<>(sessions.values());
    for (final ExpiringSession session : sessionsToCheck) {
        if (session.isExpired()) {
            deleteAndFireEvent(session.getId(), true);
            countExpiredSessions++;
        }
    }
    logger.debug("Purged {}/{} expired sessions in {}", countExpiredSessions, sessionsToCheck.size(), stopwatch);
}


}