package run.halo.app.security.handler;
 import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import run.halo.app.exception.AbstractHaloException;
public interface AuthenticationFailureHandler {


public void onFailure(HttpServletRequest request,HttpServletResponse response,AbstractHaloException exception)
;

}