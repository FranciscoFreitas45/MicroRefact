package de.metas.ui.web.window.datatypes;
 import java.util.OptionalInt;
import javax.annotation.Nullable;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.exceptions.AdempiereException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import de.metas.util.Check;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
@EqualsAndHashCode
public class WindowId {

 private  String value;

 private  OptionalInt valueInt;


public AdWindowId toAdWindowId(){
    return AdWindowId.ofRepoId(toInt());
}


public int toInt(){
    return toOptionalInt().orElseThrow(() -> new AdempiereException("WindowId cannot be converted to int: " + this));
}


public int toIntOr(int fallbackValue){
    return toOptionalInt().orElse(fallbackValue);
}


public WindowId ofNullable(AdWindowId adWindowId){
    return adWindowId != null ? new WindowId(adWindowId.getRepoId()) : null;
}


public WindowId fromNullableJson(String json){
    return json != null ? fromJson(json) : null;
}


@JsonValue
public String toJson(){
    return value;
}


public OptionalInt parseOptionalInt(){
    try {
        return OptionalInt.of(Integer.parseInt(value));
    } catch (final Exception ex) {
        return OptionalInt.empty();
    }
}


public DocumentId toDocumentId(){
    return DocumentId.of(value);
}


public WindowId of(DocumentId documentTypeId){
    if (documentTypeId.isInt()) {
        return new WindowId(documentTypeId.toInt());
    } else {
        return new WindowId(documentTypeId.toJson());
    }
}


public boolean isInt(){
    try {
        toInt();
        return true;
    } catch (Exception ex) {
        return false;
    }
}


@JsonCreator
public WindowId fromJson(String json){
    return new WindowId(json);
}


@Override
@Deprecated
public String toString(){
    return toJson();
}


public AdWindowId toAdWindowIdOrNull(){
    return AdWindowId.ofRepoIdOrNull(toIntOr(-1));
}


public OptionalInt toOptionalInt(){
    OptionalInt valueInt = this.valueInt;
    if (valueInt == null) {
        valueInt = this.valueInt = parseOptionalInt();
    }
    return valueInt;
}


}