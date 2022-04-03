package de.metas.ui.web.window.model;
 import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class DocumentValidStatus {

 private  boolean VALID_Yes;

 private  boolean VALID_No;

 private  Boolean INITIALVALUE_Yes;

@SuppressWarnings("unused")
 private  Boolean INITIALVALUE_No;

 private  Boolean INITIALVALUE_Unknown;

 private  String REASON_Null;

 private  String FIELDNAME_Null;

 private  DocumentValidStatus STATE_InitialInvalid;

 private  DocumentValidStatus STATE_Valid;

 private  DocumentValidStatus STATE_InvalidIncludedDocument;

@JsonProperty("valid")
 private  boolean valid;

@JsonProperty("initialValue")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean initialValue;

@JsonProperty("reason")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String reason;

@JsonProperty("fieldName")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String fieldName;

 private  Integer _hashcode;

 private  String _toString;


public String getReason(){
    return reason;
}


public boolean isValid(){
    return valid;
}


public DocumentValidStatus documentInitiallyInvalid(){
    return STATE_InitialInvalid;
}


@Override
public int hashCode(){
    if (_hashcode == null) {
        _hashcode = Objects.hash(valid, initialValue, reason, fieldName);
    }
    return _hashcode;
}


@Override
public boolean equals(Object obj){
    if (this == obj) {
        return true;
    }
    if (!(obj instanceof DocumentValidStatus)) {
        return false;
    }
    final DocumentValidStatus other = (DocumentValidStatus) obj;
    return valid == other.valid && Objects.equals(initialValue, other.initialValue) && Objects.equals(reason, other.reason) && Objects.equals(fieldName, other.fieldName);
}


public DocumentValidStatus invalid(Throwable error){
    return new DocumentValidStatus(VALID_No, error.getLocalizedMessage(), FIELDNAME_Null, INITIALVALUE_Unknown);
}


@Override
public String toString(){
    if (_toString == null) {
        final StringBuilder sb = new StringBuilder();
        sb.append(valid ? "Valid" : "Invalid");
        if (initialValue != null && initialValue) {
            sb.append("-Initial");
        }
        if (reason != null && !reason.isEmpty()) {
            sb.append("('").append(reason).append("')");
        }
        _toString = sb.toString();
    }
    return _toString;
}


public DocumentValidStatus documentValid(){
    return STATE_Valid;
}


public DocumentValidStatus validField(String fieldName,boolean isInitialValue){
    return new DocumentValidStatus(VALID_Yes, REASON_Null, fieldName, isInitialValue);
}


public DocumentValidStatus invalidFieldMandatoryNotFilled(String fieldName,boolean isInitialValue){
    return new DocumentValidStatus(VALID_No, "Mandatory field " + fieldName + " not filled", fieldName, isInitialValue);
}


public DocumentValidStatus fieldInitiallyInvalid(){
    return STATE_InitialInvalid;
}


public DocumentValidStatus invalidIncludedDocument(){
    return STATE_InvalidIncludedDocument;
}


public boolean isInitialInvalid(){
    return this == STATE_InitialInvalid;
}


}