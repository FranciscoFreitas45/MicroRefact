package es.us.isa.ideas.app.services;
 import org.springframework.data.jpa.repository.JpaRepository;
import es.us.isa.ideas.app.entities.DomainEntity;
public class BusinessService {


public X findById(int id){
    return getRepository().findOne(id);
}


public JpaRepository<X,Integer> getRepository()


}