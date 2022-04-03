package cn.com.cnc.fcc.Interface;
public interface TokenProvider {

   public String createToken(Authentication authentication,boolean rememberMe);
}