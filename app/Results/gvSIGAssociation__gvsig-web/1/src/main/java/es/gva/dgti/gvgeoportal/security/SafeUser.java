package es.gva.dgti.gvgeoportal.security;
 import java.io.Serializable;
import org.gvnix.addon.gva.security.providers.safe.GvNIXUserSAFE;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.security.core.userdetails.UserDetails;
@GvNIXUserSAFE
@RooJavaBean
public class SafeUser implements Serializable,UserDetails{


}