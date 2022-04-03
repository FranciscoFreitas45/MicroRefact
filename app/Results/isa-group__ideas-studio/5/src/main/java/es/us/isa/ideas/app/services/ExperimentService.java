package es.us.isa.ideas.app.services;
 import javax.inject.Inject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.us.isa.ideas.app.entities.Experiment;
import es.us.isa.ideas.app.repositories.ExperimentRepository;
@Service
@Transactional
public class ExperimentService extends BusinessService<Experiment>{

@Inject
 private  ExperimentRepository experimentRepository;


@Override
public JpaRepository<Experiment,Integer> getRepository(){
    return experimentRepository;
}


}