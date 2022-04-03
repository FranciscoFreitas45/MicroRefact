package switchtwentytwenty.project.datamodel;
 import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence;
@Entity
@Table(name = "FamilyRelation")
@NoArgsConstructor
public class FamilyRelationJPA {

@EmbeddedId
@Getter
 private  FamilyRelationIDJPA id;

@Getter
 private  String relationType;

@ManyToOne()
@JoinColumn(name = "familyID")
 private  FamilyJPA familyJPA;

/**
 * Sole Constructor.
 *
 * @param id identifier
 * @param relationType relation type
 * @param familyJPA family JPA
 */
public FamilyRelationJPA(FamilyRelationIDJPA id, String relationType, FamilyJPA familyJPA) {
    this.id = id;
    this.relationType = relationType;
    this.familyJPA = familyJPA;
}
}