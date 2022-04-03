package gov.cdc.nccdphp.bootstrap;
 import gov.cdc.nccdphp.domain.Manager;
import gov.cdc.nccdphp.repositories.ManagerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
@Component
public class ManagerLoader implements ApplicationListener<ContextRefreshedEvent>{

@Autowired
 private  ManagerRepository managerRepository;

 private  Logger log;


@Override
public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
    Manager katty = new Manager();
    katty.setName("Katty Perry");
    katty.setEmail("pinup2016@hollywood.com");
    katty.setPhoneNumber("(770) 679-9000");
    managerRepository.save(katty);
    Manager taylor = new Manager();
    taylor.setName("Taylor Swift");
    taylor.setEmail("ttswift@apple.com");
    taylor.setPhoneNumber("(770) 679-9000");
    managerRepository.save(taylor);
    Manager michael = new Manager();
    michael.setName("Michael Jackson");
    michael.setEmail("mickey@neverland.org");
    michael.setPhoneNumber("(770) 679-9000");
    michael.setActive(false);
    managerRepository.save(michael);
}


}