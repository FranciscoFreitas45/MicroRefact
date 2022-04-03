package switchtwentytwenty.project.dto.outdto;
 import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class SystemRelationsOutDTO extends RepresentationModel<SystemRelationsOutDTO>{

@Getter
 private  List<String> systemRelationsList;

// Constructor
/**
 * Sole constructor
 *
 * @param relationList - relation list
 */
public SystemRelationsOutDTO(List<String> relationList) {
    if (relationList != null) {
        this.systemRelationsList = new ArrayList<>(relationList);
    } else {
        this.systemRelationsList = new ArrayList<>();
    }
}
@Override
public int hashCode(){
    return Objects.hash(systemRelationsList);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof SystemRelationsOutDTO))
        return false;
    SystemRelationsOutDTO that = (SystemRelationsOutDTO) o;
    return Objects.equals(systemRelationsList, that.systemRelationsList);
}


@Override
public String toString(){
    return systemRelationsList.toString();
}


}