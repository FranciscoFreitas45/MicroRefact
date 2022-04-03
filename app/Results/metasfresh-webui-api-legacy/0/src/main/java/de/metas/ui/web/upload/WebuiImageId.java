package de.metas.ui.web.upload;
 import org.adempiere.exceptions.AdempiereException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.EqualsAndHashCode;
import lombok.ToString;
// cannot use it because of "otherProperties"
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@ToString
@EqualsAndHashCode
public class WebuiImageId implements RepoIdAware{

 private  int repoId;


@JsonValue
@Override
public int getRepoId(){
    return repoId;
}


public WebuiImageId ofRepoIdOrNull(int repoId){
    return repoId > 0 ? ofRepoId(repoId) : null;
}


@JsonCreator
public WebuiImageId ofNullableObject(Object obj){
    if (obj == null) {
        return null;
    }
    try {
        final int id;
        if (obj instanceof Number) {
            id = ((Number) obj).intValue();
        } else {
            final String idStr = obj.toString().trim();
            id = !idStr.isEmpty() ? Integer.parseInt(idStr) : -1;
        }
        return ofRepoIdOrNull(id);
    } catch (final Exception ex) {
        throw new AdempiereException("Cannot convert `" + obj + "` from " + obj.getClass() + " to " + WebuiImageId.class, ex);
    }
}


public WebuiImageId ofRepoId(int repoId){
    return new WebuiImageId(repoId);
}


}