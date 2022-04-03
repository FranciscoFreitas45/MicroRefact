package switchtwentytwenty.project.domain.share.familydata;
 import lombok.Getter;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;
import switchtwentytwenty.project.util.Util;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
public class RelationType implements ValueObject{

@Getter
 private  String denomination;

@Getter
 private  String oppositeDenomination;

// Constructor Methods
/**
 * Sole constructor
 *
 * @param denomination         - denomination of the relation type
 * @param oppositeDenomination - denomination of the opposite relation type
 */
private RelationType(String denomination, String oppositeDenomination) {
    this.denomination = denomination;
    this.oppositeDenomination = oppositeDenomination;
}
public RelationType getInverse(){
    return new RelationType(this.oppositeDenomination, this.denomination);
}


@Override
public int hashCode(){
    return Objects.hash(denomination, oppositeDenomination);
}


public String getOppositeRelationTypeName(String relationTypeName){
    String denomination;
    String oppositeDenomination;
    Properties properties = Util.loadConfigurationFile(Constants.RELATION_TYPE_CONFIG_FILE);
    Set<String> keys = properties.stringPropertyNames();
    for (String key : keys) {
        String value = properties.getProperty(key);
        denomination = value.split("&")[0];
        oppositeDenomination = value.split("&")[1];
        if (denomination.equals(relationTypeName)) {
            return oppositeDenomination;
        } else if (oppositeDenomination.equals(relationTypeName)) {
            return denomination;
        }
    }
    throw new InvalidRelationTypeException("This relation type is not valid");
}


@Override
public boolean equals(Object object){
    if (this == object)
        return true;
    if (object == null || getClass() != object.getClass())
        return false;
    RelationType that = (RelationType) object;
    return Objects.equals(denomination, that.denomination) && Objects.equals(oppositeDenomination, that.oppositeDenomination);
}


@Override
public String toString(){
    return this.denomination;
}


public RelationType getInstance(String relationTypeName){
    isNullOrEmptyString(relationTypeName);
    String oppositeRelationTypeName = getOppositeRelationTypeName(relationTypeName);
    return new RelationType(relationTypeName, oppositeRelationTypeName);
}


public void isNullOrEmptyString(String string){
    if (string == null) {
        throw new NullPointerException("Null string");
    }
    if (string.trim().length() == 0) {
        throw new IllegalArgumentException("Empty string");
    }
}


public boolean isAChildRelation(){
    return this.denomination.equals(Constants.CHILD);
}


public boolean isAParentRelation(){
    return this.denomination.equals(Constants.PARENT);
}


}