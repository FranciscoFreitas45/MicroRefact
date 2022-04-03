package run.halo.app.security.token;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class AuthToken {

@JsonProperty("access_token")
 private  String accessToken;

@JsonProperty("expired_in")
 private  int expiredIn;

@JsonProperty("refresh_token")
 private  String refreshToken;


}