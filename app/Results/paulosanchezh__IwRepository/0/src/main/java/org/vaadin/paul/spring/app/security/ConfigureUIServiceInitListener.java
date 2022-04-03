package org.vaadin.paul.spring.app.security;
 import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;
import org.vaadin.paul.spring.ui.views.LoginView;
@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener{


public void beforeEnter(BeforeEnterEvent event){
    if (!SecurityUtils.isAccessGranted(event.getNavigationTarget())) {
        if (SecurityUtils.isUserLoggedIn()) {
            event.rerouteToError(NotFoundException.class);
        } else {
            event.rerouteTo(LoginView.class);
        }
    }
}


@Override
public void serviceInit(ServiceInitEvent event){
    event.getSource().addUIInitListener(uiEvent -> {
        final UI ui = uiEvent.getUI();
        ui.addBeforeEnterListener(this::beforeEnter);
    });
}


}