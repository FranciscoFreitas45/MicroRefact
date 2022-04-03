package pl.szymanski.sharelibrary.response;
 import lombok.AllArgsConstructor;
import lombok.Data;
import pl.szymanski.sharelibrary.entity.Requirement;
import java.time.LocalDateTime;
@AllArgsConstructor
@Data
public class RequirementResponse {

 private  Long id;

 private  ExchangeResponse exchange;

 private  BaseUserResponse user;

 private  LocalDateTime createTime;

 private  boolean isActual;


public RequirementResponse of(Requirement requirement){
    return new RequirementResponse(requirement.getId(), ExchangeResponse.of(requirement.getExchange()), BaseUserResponse.of(requirement.getUser()), requirement.getCreateTime(), requirement.isActual());
}


}