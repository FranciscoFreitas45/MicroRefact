package es.gva.dgti.gvgeoportal.security;
 import java.io.Serializable;
import org.gvnix.addon.gva.security.providers.safe.GvNIXUserAuthoritySAFE;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.security.core.GrantedAuthority;
@GvNIXUserAuthoritySAFE
@RooJavaBean
public class SafeUserAuthority implements Serializable,GrantedAuthority{


}