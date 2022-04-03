package switchtwentytwenty.project.dto.indto;
 import lombok.Getter;
public class CustomCategoryInDTO {

@Getter
 private  String designation;

@Getter
 private  String parentID;

/**
 * Sole constructor.
 *
 * @param designation - String designation of the category that the user wants to create
 * @param parentID    - String parent ID
 */
public CustomCategoryInDTO(String designation, String parentID) {
    this.designation = designation;
    this.parentID = parentID;
}
@Override
public String toString(){
    return designation + parentID;
}


}