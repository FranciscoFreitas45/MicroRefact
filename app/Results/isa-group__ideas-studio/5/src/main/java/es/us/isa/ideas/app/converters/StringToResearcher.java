package es.us.isa.ideas.app.converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import es.us.isa.ideas.app.entities.Researcher;
import es.us.isa.ideas.app.services.BusinessService;
import es.us.isa.ideas.app.services.ResearcherService;
import es.us.isa.ideas.app.Interface.ResearcherService;
@Component
@Transactional
public class StringToResearcher extends StringToDomainEntity<Researcher>implements Converter<String, Researcher>{

@Autowired
 private  ResearcherService service;


@Override
public BusinessService<Researcher> getBusinessService(){
    return service;
}


}