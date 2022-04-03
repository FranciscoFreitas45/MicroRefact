package switchtwentytwenty.project.dto.toservicedto;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Objects;
@AllArgsConstructor
public class StandardCategoryDTO {

@Getter
 private  String designation;

@Getter
 private  String id;

@Getter
 private  String parentID;


@Override
public int hashCode(){
    return Objects.hash(designation, id, parentID);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof StandardCategoryDTO))
        return false;
    StandardCategoryDTO that = (StandardCategoryDTO) o;
    return Objects.equals(designation, that.designation) && Objects.equals(id, that.id) && Objects.equals(parentID, that.parentID);
}


}