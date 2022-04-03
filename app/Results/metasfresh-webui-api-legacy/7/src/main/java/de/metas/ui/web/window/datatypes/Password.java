package de.metas.ui.web.window.datatypes;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.MoreObjects;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@EqualsAndHashCode
public class Password {

 public  String OBFUSCATE_STRING;

 private  String password;


public Password cast(Object value){
    return (Password) value;
}


@JsonValue
public String toJson(){
    return OBFUSCATE_STRING;
}


@JsonCreator
public Password ofNullableString(String password){
    return password != null ? new Password(password) : null;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("password", "********").toString();
}


public String getAsString(){
    return password;
}


}