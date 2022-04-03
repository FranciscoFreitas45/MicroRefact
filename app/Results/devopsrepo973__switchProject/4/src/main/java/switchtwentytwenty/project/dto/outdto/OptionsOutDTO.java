package switchtwentytwenty.project.dto.outdto;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
public class OptionsOutDTO extends RepresentationModel<OptionsOutDTO>{

@Getter
@Setter
 private  List<String> allow;


}