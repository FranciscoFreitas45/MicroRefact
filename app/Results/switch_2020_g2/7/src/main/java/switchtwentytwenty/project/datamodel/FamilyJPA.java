package switchtwentytwenty.project.datamodel;
 import lombok;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "Family")
@NoArgsConstructor
public class FamilyJPA {

@Id
@Getter
 private  String id;

@Getter
 private  String registrationDate;

@Getter
@OneToMany(mappedBy = "familyJPA", cascade = CascadeType.ALL)
 private  List<FamilyRelationJPA> familyRelationList;

@Getter
 private  String name;

@Getter
 private  String administratorID;

@Setter
@Getter
 private  String cashAccountID;

@Getter
 private  String ledgerID;

/**
 * Sole constructor
 * @param id - familyID
 * @param registrationDate - registration date
 * @param name - family name
 * @param administratorID - admin ID
 * @param ledgerID - family ledger id
 */
public FamilyJPA(String id, String registrationDate, String name, String administratorID, String ledgerID) {
    this.id = id;
    this.registrationDate = registrationDate;
    this.name = name;
    this.administratorID = administratorID;
    this.ledgerID = ledgerID;
    this.familyRelationList = new ArrayList<>();
}
public void addFamilyRelation(FamilyRelationJPA familyRelationJPA){
    this.familyRelationList.add(familyRelationJPA);
}


}