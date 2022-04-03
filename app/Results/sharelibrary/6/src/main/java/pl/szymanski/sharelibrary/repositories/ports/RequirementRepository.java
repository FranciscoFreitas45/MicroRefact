package pl.szymanski.sharelibrary.repositories.ports;
 import pl.szymanski.sharelibrary.entity.Requirement;
import java.util.List;
public interface RequirementRepository {


public Requirement saveRequirement(Requirement requirement)
;

public List<Requirement> getRequirements()
;

public List<Requirement> getRequirementByExchange(Long exchangeId)
;

}