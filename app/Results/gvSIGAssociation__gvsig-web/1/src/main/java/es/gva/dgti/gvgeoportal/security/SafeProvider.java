package es.gva.dgti.gvgeoportal.security;
 import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.Set;
import javax.xml.ws.soap.SOAPFaultException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.gvnix.addon.gva.security.providers.safe.GvNIXProviderSAFE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import es.gva.dgm.ayf.war.definitions.v2u00.AutenticaUsuarioLDAPWSRequest;
import es.gva.dgm.ayf.war.definitions.v2u00.AutenticaUsuarioLDAPWSResponse;
import es.gva.dgm.ayf.war.definitions.v2u00.AutenticacionArangiPortType;
import es.gva.dgm.ayf.war.definitions.v2u00.AutorizacionPortType;
import es.gva.dgm.ayf.war.definitions.v2u00.GetInformacionWSRequest;
import es.gva.dgm.ayf.war.definitions.v2u00.GetInformacionWSResponse;
import es.gva.dgm.ayf.war.definitions.v2u00.Permiso;
import es.gva.dgm.ayf.war.definitions.v2u00.Permisoapp;
import es.gva.dgm.ayf.war.definitions.v2u00.RetornaAutorizacionWSRequest;
import es.gva.dgm.ayf.war.definitions.v2u00.RetornaAutorizacionWSResponse;
import es.gva.dgm.ayf.war.definitions.v2u00.RetornaTodasAutorizacionesDNIWSRequest;
import es.gva.dgm.ayf.war.definitions.v2u00.RetornaTodasAutorizacionesDNIWSResponse;
@GvNIXProviderSAFE
public class SafeProvider extends AbstractUserDetailsAuthenticationProvider{

 private  Logger logger;

 private  PasswordEncoder passwordEncoder;

 private  SaltSource saltSource;

 private  String applicationId;

 private  String environment;

 private  boolean mapRoles;

 private  boolean active;

 private  boolean filtrarPorAplicacion;

@Autowired
 private  AutenticacionArangiPortType safeAutenticacionClient;

@Autowired
 private  AutorizacionPortType safeAutorizacionClient;


public SafeUser convertWSInfoToUserTodasAplicaciones(GetInformacionWSResponse userFromWS,String username,List<Permiso> listPermisos){
    SafeUser user = new SafeUser();
    user.setNombre(userFromWS.getNombre());
    user.setEmail(userFromWS.getEmail());
    user.setApellido1(userFromWS.getApellido1());
    user.setApellido2(userFromWS.getApellido2());
    user.setCif(userFromWS.getCif());
    user.setHabilitado(userFromWS.getHabilitado());
    user.setIdHDFI(userFromWS.getIdHDFI());
    user.setIusserDN(userFromWS.getIssuerDN());
    user.setNif(userFromWS.getNif());
    user.setOid(userFromWS.getOid());
    user.setRazonSocial(userFromWS.getRazonSocial());
    user.setRepresentante(userFromWS.getRepresentante());
    user.setSerialNumber(userFromWS.getSerialNumber());
    user.setSubjectDN(userFromWS.getSubjectDN());
    user.setTipoAut(userFromWS.getTipoAut());
    user.setTipoCertificado(userFromWS.getTipoCertificado());
    // Spring Security User info
    user.setUsername(username);
    // Status info
    user.setAccountNonExpired(true);
    // Status info
    user.setAccountNonLocked(true);
    // Status info
    user.setCredentialsNonExpired(true);
    // Status info
    user.setEnabled(true);
    // Roles
    if (listPermisos == null) {
        throw new BadCredentialsException(" El usuario proporcionado no tiene módulos asignados");
    }
    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
    Iterator<Permiso> iter = listPermisos.iterator();
    while (iter.hasNext()) {
        Permiso permiso = iter.next();
        String rolUsu = convertToApplicationRol(permiso.getIdgrupo());
        if (rolUsu != null) {
            String[] roles = rolUsu.split(",");
            for (int i = 0; i < roles.length; i++) {
                SafeUserAuthority usAuth = new SafeUserAuthority();
                usAuth.setAuthority(roles[i]);
                usAuth.setIdgrupo(permiso.getIdgrupo());
                usAuth.setIdaplicacion(permiso.getIdaplicacion());
                usAuth.setNif(userFromWS.getNif());
                usAuth.setUsrtipo(permiso.getUsrtipo());
                usAuth.setIdrol(permiso.getIdrol());
                authorities.add(usAuth);
            }
        }
    }
    if (authorities.isEmpty()) {
        throw new BadCredentialsException("El usuario proporcionado no tiene módulos válidos " + "para acceder a la aplicación");
    }
    user.setAuthorities(authorities);
    return user;
}


public SafeUser convertWSInfoToUser(GetInformacionWSResponse userFromWS,String username,List<Permisoapp> listPermisos){
    SafeUser user = new SafeUser();
    user.setNombre(userFromWS.getNombre());
    user.setEmail(userFromWS.getEmail());
    user.setApellido1(userFromWS.getApellido1());
    user.setApellido2(userFromWS.getApellido2());
    user.setCif(userFromWS.getCif());
    user.setHabilitado(userFromWS.getHabilitado());
    user.setIdHDFI(userFromWS.getIdHDFI());
    user.setIusserDN(userFromWS.getIssuerDN());
    user.setNif(userFromWS.getNif());
    user.setOid(userFromWS.getOid());
    user.setRazonSocial(userFromWS.getRazonSocial());
    user.setRepresentante(userFromWS.getRepresentante());
    user.setSerialNumber(userFromWS.getSerialNumber());
    user.setSubjectDN(userFromWS.getSubjectDN());
    user.setTipoAut(userFromWS.getTipoAut());
    user.setTipoCertificado(userFromWS.getTipoCertificado());
    // Spring Security User info
    user.setUsername(username);
    // Status info
    user.setAccountNonExpired(true);
    // Status info
    user.setAccountNonLocked(true);
    // Status info
    user.setCredentialsNonExpired(true);
    // Status info
    user.setEnabled(true);
    // Roles
    if (listPermisos == null) {
        throw new BadCredentialsException(" El usuario proporcionado no tiene módulos asignados");
    }
    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
    Iterator<Permisoapp> iter = listPermisos.iterator();
    while (iter.hasNext()) {
        Permisoapp permisoApp = iter.next();
        String rolUsu = convertToApplicationRol(permisoApp.getIdgrupo());
        if (rolUsu != null) {
            String[] roles = rolUsu.split(",");
            for (int i = 0; i < roles.length; i++) {
                SafeUserAuthority usAuth = new SafeUserAuthority();
                usAuth.setAuthority(roles[i]);
                usAuth.setIdgrupo(permisoApp.getIdgrupo());
                usAuth.setIdaplicacion(permisoApp.getIdaplicacion());
                usAuth.setNif(permisoApp.getNif());
                usAuth.setUsrtipo(permisoApp.getUsrtipo());
                usAuth.setIdrol(permisoApp.getIdrol());
                authorities.add(usAuth);
            }
        }
    }
    if (authorities.isEmpty()) {
        throw new BadCredentialsException("El usuario proporcionado no tiene módulos válidos " + "para acceder a la aplicación");
    }
    user.setAuthorities(authorities);
    return user;
}


public UserDetails retrieveUser(String userTokenized,UsernamePasswordAuthenticationToken authentication){
    // Getting username or token
    String[] requestParts = userTokenized.split("::");
    String username = "";
    String token = "";
    if (requestParts.length > 0) {
        username = requestParts[0];
    }
    if (requestParts.length > 1) {
        token = requestParts[1];
    }
    String presentedPassword = authentication.getCredentials().toString();
    if (getActive()) {
        try {
            // If token is not defined, get token using username and
            // password
            if (StringUtils.isBlank(token)) {
                AutenticaUsuarioLDAPWSRequest aut = new AutenticaUsuarioLDAPWSRequest();
                aut.setUsuarioLDAP(username);
                aut.setPwdLDAP(presentedPassword);
                AutenticaUsuarioLDAPWSResponse response1 = safeAutenticacionClient.autenticaUsuarioLDAPWS(aut);
                token = response1.getToken();
            }
            GetInformacionWSRequest getInformacionWSRequest = new GetInformacionWSRequest();
            getInformacionWSRequest.setToken(token);
            GetInformacionWSResponse getInformacionWSResponse = safeAutenticacionClient.getInformacionWS(getInformacionWSRequest);
            // Checking if is necessary filter by applicationId
            RetornaAutorizacionWSRequest retornaUsuApliAut = null;
            RetornaTodasAutorizacionesDNIWSRequest retornaTodasAut = null;
            String usuarioHDFI = getInformacionWSResponse.getIdHDFI();
            if (getFiltrarPorAplicacion() && StringUtils.isNotBlank(usuarioHDFI)) {
                retornaUsuApliAut = new RetornaAutorizacionWSRequest();
                String applicationId = getApplicationId();
                retornaUsuApliAut.setIdAplicacion(applicationId);
                retornaUsuApliAut.setUsuarioHDFI(usuarioHDFI);
            } else {
                retornaTodasAut = new RetornaTodasAutorizacionesDNIWSRequest();
                retornaTodasAut.setTipoBusqueda("ambas");
                retornaTodasAut.setUsuarioDNI(getInformacionWSResponse.getNif());
            }
            // Checking if is necessary filter by applicationId
            SafeUser user = null;
            if (getFiltrarPorAplicacion() && StringUtils.isNotBlank(usuarioHDFI)) {
                RetornaAutorizacionWSResponse autorizaResponse = safeAutorizacionClient.retornaAutorizacionWS(retornaUsuApliAut);
                List<Permisoapp> listaPermisos = autorizaResponse.getPermisoapp();
                user = convertWSInfoToUser(getInformacionWSResponse, username, listaPermisos);
            } else {
                RetornaTodasAutorizacionesDNIWSResponse autorizaResponse = safeAutorizacionClient.retornaTodasAutorizacionesDNIWS(retornaTodasAut);
                List<Permiso> listaPermisos = autorizaResponse.getLista();
                user = convertWSInfoToUserTodasAplicaciones(getInformacionWSResponse, username, listaPermisos);
            }
            Object salt = null;
            if (this.saltSource != null) {
                salt = this.saltSource.getSalt(user);
            }
            user.setPassword(passwordEncoder.encodePassword(presentedPassword, salt));
            return user;
        } catch (Exception e) {
            logger.warn("Solicitud de login denegada (usuario='" + username + "'): " + e.getLocalizedMessage());
            if (e instanceof SOAPFaultException) {
                // String message = e.getMessage();
                String message = "";
                if (message.indexOf("autenticaUsuarioLDAPWS") > 0) {
                    message = "Por favor, compruebe que el usuario y password son correctos";
                }
                throw new AuthenticationServiceException("Acceso denegado " + message, e);
            } else {
                throw new AuthenticationServiceException("Error en servicio web de login al validar al usuario", e);
            }
        }
    } else {
        SafeUser user = new SafeUser();
        try {
            user.setUsername(username);
            user.setPassword(presentedPassword);
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);
            Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
            SafeUserAuthority consAuth = new SafeUserAuthority();
            consAuth.setAuthority("ADMIN");
            authorities.add(consAuth);
            user.setAuthorities(authorities);
        } catch (Exception e) {
            String message = e.getLocalizedMessage();
            message = message.substring(message.lastIndexOf(":") + 1, message.length());
            throw new AuthenticationServiceException("Error en servicio web de login al validar al usuario." + message, e);
        }
        return user;
    }
}


public String convertToApplicationRol(String idgrupo){
    if (!mapRoles) {
        return idgrupo;
    }
    if (prop == null) {
        prop = new Properties();
        loader = Thread.currentThread().getContextClassLoader();
        stream = loader.getResourceAsStream("safe_client_roles.properties");
        try {
            prop.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    StringBuilder sbuilder = new StringBuilder("SAFE.");
    if (getEnvironment() != null && !getEnvironment().isEmpty()) {
        sbuilder.append(getEnvironment());
        sbuilder.append('.');
    }
    sbuilder.append("role.");
    sbuilder.append(idgrupo);
    try {
        return prop.getProperty(sbuilder.toString());
    } catch (MissingResourceException e) {
        return idgrupo;
    }
}


}