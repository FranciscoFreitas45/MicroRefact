package run.halo.app.Interface;
public interface AuthenticationFailureHandler {

   public void onFailure(HttpServletRequest request,HttpServletResponse response,AbstractHaloException exception);
}