package de.metas.ui.web.session;
 import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.adempiere.util.concurrent.CustomizableThreadFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import de.metas.logging.LogManager;
@Configuration
@EnableSpringHttpSession
@EnableConfigurationProperties(SessionProperties.class)
public class SessionConfig {

 private  Logger logger;

 private  String BEANNAME_SessionScheduledExecutorService;

@Value("${metasfresh.session.checkExpiredSessionsRateInMinutes:10}")
 private  int checkExpiredSessionsRateInMinutes;


@Bean(BEANNAME_SessionScheduledExecutorService)
public ScheduledExecutorService sessionScheduledExecutorService(){
    return Executors.newScheduledThreadPool(// corePoolSize
    1, CustomizableThreadFactory.builder().setDaemon(true).setThreadNamePrefix(SessionConfig.class.getName()).build());
}


@Bean
public SessionRepository<ExpiringSession> sessionRepository(SessionProperties properties,ApplicationEventPublisher applicationEventPublisher){
    final FixedMapSessionRepository sessionRepository = FixedMapSessionRepository.builder().applicationEventPublisher(applicationEventPublisher).defaultMaxInactiveInterval(properties.getTimeout()).build();
    logger.info("Using session repository: {}", sessionRepository);
    if (checkExpiredSessionsRateInMinutes > 0) {
        final ScheduledExecutorService scheduledExecutor = sessionScheduledExecutorService();
        scheduledExecutor.scheduleAtFixedRate(// command, don't fail because on failure the task won't be re-scheduled so it's game over
        sessionRepository::purgeExpiredSessionsNoFail, // initialDelay
        checkExpiredSessionsRateInMinutes, // period
        checkExpiredSessionsRateInMinutes, // timeUnit
        TimeUnit.MINUTES);
        logger.info("Checking expired sessions each {} minutes", checkExpiredSessionsRateInMinutes);
    }
    return sessionRepository;
}


}