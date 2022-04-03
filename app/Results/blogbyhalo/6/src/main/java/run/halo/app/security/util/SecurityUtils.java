package run.halo.app.security.util;
 import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import run.halo.app.model.entity.User;
public class SecurityUtils {

 private  String TOKEN_ACCESS_CACHE_PREFIX;

 private  String TOKEN_REFRESH_CACHE_PREFIX;

 private  String ACCESS_TOKEN_CACHE_PREFIX;

 private  String REFRESH_TOKEN_CACHE_PREFIX;

private SecurityUtils() {
}
@NonNull
public String buildTokenAccessKey(String accessToken){
    Assert.hasText(accessToken, "Access token must not be blank");
    return TOKEN_ACCESS_CACHE_PREFIX + accessToken;
}


@NonNull
public String buildTokenRefreshKey(String refreshToken){
    Assert.hasText(refreshToken, "Refresh token must not be blank");
    return TOKEN_REFRESH_CACHE_PREFIX + refreshToken;
}


@NonNull
public String buildRefreshTokenKey(User user){
    Assert.notNull(user, "User must not be null");
    return REFRESH_TOKEN_CACHE_PREFIX + user.getId();
}


@NonNull
public String buildAccessTokenKey(User user){
    Assert.notNull(user, "User must not be null");
    return ACCESS_TOKEN_CACHE_PREFIX + user.getId();
}


}