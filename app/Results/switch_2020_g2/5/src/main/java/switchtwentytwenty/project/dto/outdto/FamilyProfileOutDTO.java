package switchtwentytwenty.project.dto.outdto;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
@AllArgsConstructor
public class FamilyProfileOutDTO extends RepresentationModel<FamilyProfileOutDTO>{

@Getter
 private  String familyName;

@Getter
 private  String registrationDate;

@Getter
 private  String administratorID;


}