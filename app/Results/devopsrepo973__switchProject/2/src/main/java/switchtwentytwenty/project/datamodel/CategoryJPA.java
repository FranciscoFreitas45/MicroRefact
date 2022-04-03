package switchtwentytwenty.project.datamodel;
 import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence;
@Entity
@NoArgsConstructor
@Table(name = "Category")
public class CategoryJPA {

@Id
@Getter
 private  String id;

@Getter
 private  String designation;

@Column(name = "FamilyID")
@Getter
 private  String familyId;

@Getter
@Column(name = "Standard")
 private  boolean isStandard;

@Getter
@Column(name = "ParentID")
 private  String parentID;

/**
 * Constructor of a standard jpa category.
 *
 * @param id          of the category
 * @param parentID    of the category
 * @param designation of the category
 * @param isStandard  - boolean that checks if the category is standard
 */
public CategoryJPA(String id, String parentID, String designation, boolean isStandard) {
    this.id = id;
    this.parentID = parentID;
    this.designation = designation;
    this.isStandard = isStandard;
}/**
 * Constructor of a custom jpa category.
 *
 * @param id          of the category
 * @param parentID    of the category
 * @param designation of the category
 * @param familyId    - if of the family
 * @param isStandard  - boolean that checks if the category is standard
 */
public CategoryJPA(String id, String parentID, String designation, String familyId, boolean isStandard) {
    this.id = id;
    this.parentID = parentID;
    this.designation = designation;
    this.isStandard = isStandard;
    this.familyId = familyId;
}
}