package switchtwentytwenty.project.dto.toservicedto;
 import lombok.Getter;
public class CustomCategoryDTO {

@Getter
 private  String designation;

@Getter
 private  String parentID;

@Getter
 private  String familyID;

/**
 * Sole constructor.
 *
 * @param designation of the category
 * @param parentID    of the category
 * @param familyID    - family of the custom category
 */
public CustomCategoryDTO(String designation, String parentID, String familyID) {
    this.designation = designation;
    this.parentID = parentID;
    this.familyID = familyID;
}
}