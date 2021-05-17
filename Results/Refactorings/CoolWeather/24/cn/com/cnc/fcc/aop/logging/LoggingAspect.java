import io.github.jhipster.config.JHipsterConstants;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import java.util.Arrays;
@Aspect
public class LoggingAspect {

 private  Logger log;

 private  Environment env;


@AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
public void logAfterThrowing(JoinPoint joinPoint,Throwable e){
    if (env.acceptsProfiles(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)) {
        log.error("Exception in {}.{}() with cause = \'{}\' and exception = \'{}\'", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL", e.getMessage(), e);
    } else {
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
    }
}


@Pointcut("within(@org.springframework.stereotype.Repository *)" + " || within(@org.springframework.stereotype.Service *)" + " || within(@org.springframework.web.bind.annotation.RestController *)")
public void springBeanPointcut(){
// Method is empty as this is just a Pointcut, the implementations are in the advices.
}


@Around("applicationPackagePointcut() && springBeanPointcut()")
public Object logAround(ProceedingJoinPoint joinPoint){
    if (log.isDebugEnabled()) {
        log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }
    try {
        Object result = joinPoint.proceed();
        if (log.isDebugEnabled()) {
            log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), result);
        }
        return result;
    } catch (IllegalArgumentException e) {
        log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        throw e;
    }
}


@Pointcut("within(cn.com.cnc.fcc.repository..*)" + " || within(cn.com.cnc.fcc.service..*)" + " || within(cn.com.cnc.fcc.web.rest..*)")
public void applicationPackagePointcut(){
// Method is empty as this is just a Pointcut, the implementations are in the advices.
}


}