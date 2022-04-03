package org.vaadin.paul.spring.ui.views;
 import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.vaadin.paul.spring.app.security.CustomRequestCache;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
@Tag("sa-login-view")
@Route(value = LoginView.ROUTE)
@PageTitle("Login")
public class LoginView extends VerticalLayout{

 public  String ROUTE;

 private  LoginOverlay login;

 private  AuthenticationManager authenticationManager;

 private  CustomRequestCache requestCache;

 private  Button registerButton;

@Autowired
public LoginView(AuthenticationManager authenticationManager, CustomRequestCache requestCache) {
    this.authenticationManager = authenticationManager;
    this.requestCache = requestCache;
    // login.setAction("login");
    login.setOpened(true);
    login.setTitle("Centro sanitario login");
    login.setDescription("Pagina de logueo");
    login.addLoginListener(e -> {
        // 
        try {
            // try to authenticate with given credentials, should always return not null or throw an {@link AuthenticationException}
            final Authentication authentication = authenticationManager.authenticate(// 
            new UsernamePasswordAuthenticationToken(e.getUsername(), e.getPassword()));
            // if authentication was successful we will update the security context and redirect to the page requested first
            // 
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // 
            login.close();
            // 
            UI.getCurrent().navigate(requestCache.resolveRedirectUrl());
        } catch (AuthenticationException ex) {
            // 
            // show default error message
            // Note: You should not expose any detailed information here like "username is known but password is wrong"
            // as it weakens security.
            login.setError(true);
        }
    });
    // registerButton.addClickListener( event -> UI.getCurrent().navigate("createuser"));
    // add(registerButton);
    add(login);
}
}