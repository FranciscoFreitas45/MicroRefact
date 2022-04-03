package de.metas.ui.web.session.json;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.i18n.Language;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONUserSession {

@JsonProperty("loggedIn")
 private  boolean loggedIn;

@JsonProperty("username")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String username;

@JsonProperty("fullname")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String fullname;

@JsonProperty("email")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String email;

@JsonProperty("avatarId")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String avatarId;

@JsonProperty("rolename")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String rolename;

@JsonProperty("language")
 private  JSONLookupValue language;

@JsonProperty("locale")
 private  JSONUserSessionLocale locale;

@JsonProperty("timeZone")
 private  String timeZone;

@JsonProperty("websocketEndpoint")
 private  String websocketEndpoint;

@JsonProperty("userProfileWindowId")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  WindowId userProfileWindowId;

@JsonProperty("userProfileId")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Integer userProfileId;


public JSONUserSession of(UserSession userSession){
    return new JSONUserSession(userSession);
}


}