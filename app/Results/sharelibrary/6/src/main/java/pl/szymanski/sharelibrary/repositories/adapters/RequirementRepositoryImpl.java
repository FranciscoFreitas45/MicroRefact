package pl.szymanski.sharelibrary.repositories.adapters;
 import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.szymanski.sharelibrary.entity.Requirement;
import pl.szymanski.sharelibrary.repositories.jpa.RequirementJPARepository;
import pl.szymanski.sharelibrary.repositories.ports.RequirementRepository;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class RequirementRepositoryImpl implements RequirementRepository{

 private  RequirementJPARepository requirementJPARepository;


@Override
public Requirement saveRequirement(Requirement requirement){
    return requirementJPARepository.saveAndFlush(requirement);
}


@Override
public List<Requirement> getRequirements(){
    return requirementJPARepository.findAll();
}


@Override
public List<Requirement> getRequirementByExchange(Long exchangeId){
    return requirementJPARepository.getAllByExchangeId(exchangeId);
}


}