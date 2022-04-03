package de.metas.ui.web.window.model;
 import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import lombok.Builder;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class DocumentSaveStatus {

 private  DocumentSaveStatus STATUS_Unknown;

 private  DocumentSaveStatus STATUS_Saved;

 private  DocumentSaveStatus STATUS_Deleted;

 private  DocumentSaveStatus STATUS_NotSavedJustCreated;

 private  DocumentSaveStatus STATUS_SavedJustLoaded;

@JsonProperty("saved")
 private  boolean saved;

@JsonProperty("hasChanges")
 private  boolean hasChangesToBeSaved;

@JsonProperty("error")
 private  boolean error;

@JsonProperty("reason")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String reason;

 private  boolean deleted;

 private  Integer _hashcode;


public DocumentSaveStatus notSavedJustCreated(){
    return STATUS_NotSavedJustCreated;
}


public DocumentSaveStatus saved(){
    return STATUS_Saved;
}


public boolean equalsIgnoreReason(Object obj){
    final boolean ignoreReason = true;
    return equals(obj, ignoreReason);
}


public String getReason(){
    return reason;
}


public DocumentSaveStatus unknown(){
    return STATUS_Unknown;
}


public boolean hasChangesToBeSaved(){
    return hasChangesToBeSaved;
}


public DocumentSaveStatus notSaved(Exception exception){
    final String reason = exception.getLocalizedMessage();
    return builder().hasChangesToBeSaved(true).error(true).reason(reason).build();
}


public DocumentSaveStatus deleted(){
    return STATUS_Deleted;
}


public boolean isError(){
    return error;
}


public boolean isDeleted(){
    return deleted;
}


@Override
public int hashCode(){
    if (_hashcode == null) {
        _hashcode = Objects.hash(hasChangesToBeSaved, error, reason);
    }
    return _hashcode;
}


public boolean isSaved(){
    return saved;
}


public boolean equals(Object obj,boolean ignoreReason){
    if (this == obj) {
        return true;
    }
    if (!(obj instanceof DocumentSaveStatus)) {
        return false;
    }
    final DocumentSaveStatus other = (DocumentSaveStatus) obj;
    return hasChangesToBeSaved == other.hasChangesToBeSaved && deleted == other.deleted && error == other.error && (ignoreReason || Objects.equals(reason, other.reason));
}


public DocumentSaveStatus savedJustLoaded(){
    return STATUS_SavedJustLoaded;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("saved", saved).add("deleted", deleted).add("hasChangesToBeSaved", hasChangesToBeSaved).add("error", error).add("reason", reason).toString();
}


public boolean isSavedOrDeleted(){
    return isSaved() || isDeleted();
}


}