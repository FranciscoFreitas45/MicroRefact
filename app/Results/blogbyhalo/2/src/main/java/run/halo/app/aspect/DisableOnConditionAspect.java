package run.halo.app.aspect;
 import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import run.halo.app.annotation.DisableOnCondition;
import run.halo.app.config.properties.HaloProperties;
import run.halo.app.exception.ForbiddenException;
import run.halo.app.model.enums.Mode;
@Aspect
@Slf4j
@Component
public class DisableOnConditionAspect {

 private  HaloProperties haloProperties;

public DisableOnConditionAspect(HaloProperties haloProperties) {
    this.haloProperties = haloProperties;
}
@Pointcut("within(run.halo.app.controller..*)")
public void pointcut(){
}


@Around("pointcut() && @annotation(disableApi)")
public Object around(ProceedingJoinPoint joinPoint,DisableOnCondition disableApi){
    Mode mode = disableApi.mode();
    if (haloProperties.getMode().equals(mode)) {
        throw new ForbiddenException("禁止访问");
    }
    return joinPoint.proceed();
}


}