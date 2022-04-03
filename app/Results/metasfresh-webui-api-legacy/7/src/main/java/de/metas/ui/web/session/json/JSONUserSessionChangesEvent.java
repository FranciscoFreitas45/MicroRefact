package de.metas.ui.web.session.json;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.util.time.SystemTime;
import lombok.Builder;
import lombok.ToString;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Builder
@ToString
public class JSONUserSessionChangesEvent {

@JsonProperty("fullname")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String fullname;

@JsonProperty("email")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String email;

@JsonProperty("avatarId")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String avatarId;

@JsonProperty("language")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  JSONLookupValue language;

@JsonProperty("timestamp")
 private  String timestamp;


public boolean isEmpty(){
    return fullname == null && email == null && avatarId == null && language == null;
}


}