package es.us.isa.ideas.app.converters;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import es.us.isa.ideas.app.entities.Researcher;
@Component
@Transactional
public class ResearcherToString extends DomainEntityToString<Researcher>{


}