package edu.xr.campusweibo.security;
 import edu.xr.campusweibo.domain.PersistentToken;
import edu.xr.campusweibo.repository.PersistentTokenRepository;
import edu.xr.campusweibo.repository.UserRepository;
import edu.xr.campusweibo.service.util.RandomUtil;
import io.github.jhipster.config.JHipsterProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Arrays;
@Service
public class PersistentTokenRememberMeServices extends AbstractRememberMeServices{

 private  Logger log;

 private  int TOKEN_VALIDITY_DAYS;

 private  int TOKEN_VALIDITY_SECONDS;

 private  PersistentTokenRepository persistentTokenRepository;

 private  UserRepository userRepository;


@Override
public UserDetails processAutoLoginCookie(String[] cookieTokens,HttpServletRequest request,HttpServletResponse response){
    PersistentToken token = getPersistentToken(cookieTokens);
    String login = token.getUser().getLogin();
    // Token also matches, so login is valid. Update the token value, keeping the *same* series number.
    log.debug("Refreshing persistent login token for user '{}', series '{}'", login, token.getSeries());
    token.setTokenDate(LocalDate.now());
    token.setTokenValue(RandomUtil.generateTokenData());
    token.setIpAddress(request.getRemoteAddr());
    token.setUserAgent(request.getHeader("User-Agent"));
    try {
        persistentTokenRepository.saveAndFlush(token);
        addCookie(token, request, response);
    } catch (DataAccessException e) {
        log.error("Failed to update token: ", e);
        throw new RememberMeAuthenticationException("Autologin failed due to data access problem", e);
    }
    return getUserDetailsService().loadUserByUsername(login);
}


@Override
@Transactional
public void logout(HttpServletRequest request,HttpServletResponse response,Authentication authentication){
    String rememberMeCookie = extractRememberMeCookie(request);
    if (rememberMeCookie != null && rememberMeCookie.length() != 0) {
        try {
            String[] cookieTokens = decodeCookie(rememberMeCookie);
            PersistentToken token = getPersistentToken(cookieTokens);
            persistentTokenRepository.delete(token);
        } catch (InvalidCookieException ice) {
            log.info("Invalid cookie, no persistent token could be deleted", ice);
        } catch (RememberMeAuthenticationException rmae) {
            log.debug("No persistent token found, so no token could be deleted", rmae);
        }
    }
    super.logout(request, response, authentication);
}


public PersistentToken getPersistentToken(String[] cookieTokens){
    if (cookieTokens.length != 2) {
        throw new InvalidCookieException("Cookie token did not contain " + 2 + " tokens, but contained '" + Arrays.asList(cookieTokens) + "'");
    }
    String presentedSeries = cookieTokens[0];
    String presentedToken = cookieTokens[1];
    PersistentToken token = persistentTokenRepository.findOne(presentedSeries);
    if (token == null) {
        // No series match, so we can't authenticate using this cookie
        throw new RememberMeAuthenticationException("No persistent token found for series id: " + presentedSeries);
    }
    // We have a match for this user/series combination
    log.info("presentedToken={} / tokenValue={}", presentedToken, token.getTokenValue());
    if (!presentedToken.equals(token.getTokenValue())) {
        // Token doesn't match series value. Delete this session and throw an exception.
        persistentTokenRepository.delete(token);
        throw new CookieTheftException("Invalid remember-me token (Series/token) mismatch. Implies previous " + "cookie theft attack.");
    }
    if (token.getTokenDate().plusDays(TOKEN_VALIDITY_DAYS).isBefore(LocalDate.now())) {
        persistentTokenRepository.delete(token);
        throw new RememberMeAuthenticationException("Remember-me login has expired");
    }
    return token;
}


public void addCookie(PersistentToken token,HttpServletRequest request,HttpServletResponse response){
    setCookie(new String[] { token.getSeries(), token.getTokenValue() }, TOKEN_VALIDITY_SECONDS, request, response);
}


@Override
public void onLoginSuccess(HttpServletRequest request,HttpServletResponse response,Authentication successfulAuthentication){
    String login = successfulAuthentication.getName();
    log.debug("Creating new persistent login for user {}", login);
    PersistentToken token = userRepository.findOneByLogin(login).map(u -> {
        PersistentToken t = new PersistentToken();
        t.setSeries(RandomUtil.generateSeriesData());
        t.setUser(u);
        t.setTokenValue(RandomUtil.generateTokenData());
        t.setTokenDate(LocalDate.now());
        t.setIpAddress(request.getRemoteAddr());
        t.setUserAgent(request.getHeader("User-Agent"));
        return t;
    }).orElseThrow(() -> new UsernameNotFoundException("User " + login + " was not found in the database"));
    try {
        persistentTokenRepository.saveAndFlush(token);
        addCookie(token, request, response);
    } catch (DataAccessException e) {
        log.error("Failed to save persistent token ", e);
    }
}


}