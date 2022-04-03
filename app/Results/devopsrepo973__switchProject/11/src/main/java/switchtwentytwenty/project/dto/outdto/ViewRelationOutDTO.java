package switchtwentytwenty.project.dto.outdto;
 import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class ViewRelationOutDTO extends RepresentationModel<ViewRelationOutDTO>{

@Getter
 private  List<String> relationList;

// Constructor
/**
 * Sole constructor
 *
 * @param relationList - relation list
 */
public ViewRelationOutDTO(List<String> relationList) {
    if (relationList != null) {
        this.relationList = new ArrayList<>(relationList);
    } else {
        this.relationList = new ArrayList<>();
    }
}
public void add(String element){
    this.relationList.add(element);
}


@Override
public int hashCode(){
    return Objects.hash(relationList);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof ViewRelationOutDTO))
        return false;
    ViewRelationOutDTO that = (ViewRelationOutDTO) o;
    return Objects.equals(relationList, that.relationList);
}


@Override
public String toString(){
    return relationList.toString();
}


public int listSize(){
    return relationList.size();
}


}