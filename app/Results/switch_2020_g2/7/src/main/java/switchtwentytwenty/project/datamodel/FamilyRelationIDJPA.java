package switchtwentytwenty.project.datamodel;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class FamilyRelationIDJPA implements Serializable{

 private  long serialVersionUID;

@Getter
@Column(name = "personID")
 private  String personID;

@Getter
@Column(name = "kinID")
 private  String kinID;


}