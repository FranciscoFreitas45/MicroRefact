package switchtwentytwenty.project.interfaceadaptor.icontroller.member;
 import org.springframework.http.ResponseEntity;
public interface IViewFamilyRelationsFromAPerson {


public ResponseEntity<Object> getFamilyRelationByPersonID(String personID)
;

}