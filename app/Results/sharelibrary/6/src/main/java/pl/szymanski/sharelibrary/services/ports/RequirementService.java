package pl.szymanski.sharelibrary.services.ports;
 import pl.szymanski.sharelibrary.entity.Requirement;
import pl.szymanski.sharelibrary.requests.CreateRequirementRequest;
import java.util.List;
public interface RequirementService {


public Requirement createRequirement(CreateRequirementRequest createRequirementRequest)
;

public List<Requirement> getUserRequirements(Long userId)
;

}