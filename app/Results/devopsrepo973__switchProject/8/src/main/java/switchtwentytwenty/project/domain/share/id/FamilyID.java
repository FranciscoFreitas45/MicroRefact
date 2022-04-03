package switchtwentytwenty.project.domain.share.id;
 import java.util.Objects;
import java.util.UUID;
public class FamilyID implements ID{

 private  UUID id;

// Constructor Methods
/**
 * Sole constructor
 *
 * @param id - family id
 */
public FamilyID(UUID id) {
    Objects.requireNonNull(id);
    this.id = id;
}
@Override
public int hashCode(){
    return Objects.hash(id);
}


@Override
public boolean equals(Object other){
    if (this == other)
        return true;
    if (other == null || getClass() != other.getClass())
        return false;
    FamilyID familyID = (FamilyID) other;
    return Objects.equals(id, familyID.id);
}


@Override
public String toString(){
    return this.id.toString();
}


}