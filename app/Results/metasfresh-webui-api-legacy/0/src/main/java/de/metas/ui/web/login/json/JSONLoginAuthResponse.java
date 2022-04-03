package de.metas.ui.web.login.json;
 import java.util.Collection;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableSet;
import de.metas.util.Check;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONLoginAuthResponse {

@JsonProperty("roles")
 private Set<JSONLoginRole> roles;

@JsonProperty("loginComplete")
 private boolean loginComplete;


public JSONLoginAuthResponse of(Collection<JSONLoginRole> roles){
    Check.assumeNotEmpty(roles, "roles is not empty");
    final boolean loginComplete = false;
    return new JSONLoginAuthResponse(roles, loginComplete);
}


public JSONLoginAuthResponse loginComplete(JSONLoginRole role){
    Check.assumeNotNull(role, "Parameter role is not null");
    final Set<JSONLoginRole> roles = ImmutableSet.of(role);
    final boolean loginComplete = true;
    return new JSONLoginAuthResponse(roles, loginComplete);
}


}