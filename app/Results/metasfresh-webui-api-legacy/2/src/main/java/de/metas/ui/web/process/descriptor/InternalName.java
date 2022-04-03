package de.metas.ui.web.process.descriptor;
 import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import de.metas.util.Check;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
@EqualsAndHashCode(doNotUseGetters = true)
public class InternalName {

 private  String stringValue;


@JsonCreator
public InternalName ofString(String str){
    final String strNorm = normalizeString(str);
    if (str.isEmpty()) {
        throw new AdempiereException("Invalid internal name: " + str);
    }
    return new InternalName(strNorm);
}


@Nullable
public InternalName ofNullableString(String str){
    return Check.isEmpty(str, true) ? null : ofString(str);
}


@Override
@Deprecated
public String toString(){
    return getAsString();
}


@JsonValue
public String getAsString(){
    return stringValue;
}


@NonNull
public String normalizeString(String str){
    return str.trim().replace(" ", "_");
}


}