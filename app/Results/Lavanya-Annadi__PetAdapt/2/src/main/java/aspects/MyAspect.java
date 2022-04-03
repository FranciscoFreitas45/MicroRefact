package aspects;
 import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.AfterAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.swagger.model.Statistics;
import io.swagger.repository.StatisticsRepository;
@Aspect
@Component
public class MyAspect {

@Autowired
 private StatisticsRepository statisticRepository;


@Before("execution(* io.swagger.controller.UserController.addUser(..))")
public void before_addUser(){
    System.out.println("FINDALL METHOD USED123");
    long id = 1;
    Statistics stat = statisticRepository.getOne(id);
    stat.setmethodcalls(stat.getMethodcalls() + 1);
    statisticRepository.save(stat);
    System.out.println("FINDALL METHOD USED1234");
}


@After("execution(* io.swagger.controller.UserController.login(..))")
public void after_login(){
    System.out.println("FINDALL METHOD USED12356767");
    long id = 2;
    Statistics stat = statisticRepository.getOne(id);
    stat.setmethodcalls(stat.getMethodcalls() + 1);
    statisticRepository.save(stat);
    System.out.println("FINDALL METHOD USED1234erterte");
}


}