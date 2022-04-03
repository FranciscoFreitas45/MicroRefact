package de.metas.ui.web.login.json;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.util.hash.HashableString;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
@ToString(exclude = "password")
public class JSONLoginAuthRequest {

@JsonProperty("username")
 private String username;

@JsonProperty("password")
 private String password;


public HashableString getPasswordAsEncryptableString(){
    return HashableString.ofPlainValue(password);
}


}