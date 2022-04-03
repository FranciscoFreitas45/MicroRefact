package es.us.isa.ideas.app.social;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
@Component
public class SocialSignInAdapter implements SignInAdapter{

@Autowired
 private  RequestCache requestCache;

@Autowired
 private  SignInUtils signInUtils;


public String extractOriginalUrl(NativeWebRequest request){
    HttpServletRequest nativeReq = request.getNativeRequest(HttpServletRequest.class);
    HttpServletResponse nativeRes = request.getNativeResponse(HttpServletResponse.class);
    SavedRequest saved = requestCache.getRequest(nativeReq, nativeRes);
    if (saved == null) {
        return null;
    }
    requestCache.removeRequest(nativeReq, nativeRes);
    removeAutheticationAttributes(nativeReq.getSession(false));
    return saved.getRedirectUrl();
}


public void removeAutheticationAttributes(HttpSession session){
    if (session == null) {
        return;
    }
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
}


@Override
public String signIn(String localUserId,Connection<?> connection,NativeWebRequest request){
    signInUtils.signin(localUserId, connection.getKey().getProviderId());
    return extractOriginalUrl(request);
}


}