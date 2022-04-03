package org.vaadin.paul.spring;
 import org.vaadin.paul.spring.app.security.SecurityUtils;
import org.vaadin.paul.spring.entities.User;
import org.vaadin.paul.spring.ui.views;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.applayout.AppLayout.Section;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.Interface.User;
@Route(value = "")
@PWA(name = "Vaadin Application", shortName = "Vaadin App", description = "This is an example Vaadin application.", enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends AppLayout{

 private  Tabs menu;

 private  H1 viewTitle;

 private  User user;

@Autowired
public MainView() {
    setPrimarySection(Section.DRAWER);
    addToNavbar(true, createHeaderContent());
    menu = createMenu();
    addToDrawer(createDrawerContent(menu));
}
public Component[] createMenuItems(){
    user = (User) SecurityUtils.getAuthenticatedUser();
    if (user != null && user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
        return new Tab[] { createTab("Coger Cita", solicitarCita.class), createTab("Informes", InformesUsuario.class), createTab("Ver Citas", VerCitas.class) };
    } else if (user != null && user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SANITARIO"))) {
        return new Tab[] { createTab("Citas del día", CitasDelDia.class), createTab("Citas Pendientes", CitasPendientes.class), createTab("Informes Pacientes", InformesPacientes.class), createTab("Ver Pacientes", VerPacientes.class) };
    } else {
        return new Tab[] { // createTab("Estadisticas", PruebaEstadistica.class),
        createTab("Crear Usuario", createUser.class), createTab("Gestionar Centros", crudCentro.class), createTab("Gestionar Usuarios", crudUser.class), createTab("Profesionales demandados", sanitarioDemandados.class) };
    }
}


public Optional<Tab> getTabForComponent(Component component){
    return menu.getChildren().filter(tab -> ComponentUtil.getData(tab, Class.class).equals(component.getClass())).findFirst().map(Tab.class::cast);
}


public Tabs createMenu(){
    final Tabs tabs = new Tabs();
    tabs.setOrientation(Tabs.Orientation.VERTICAL);
    tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
    tabs.setId("tabs");
    tabs.add(createMenuItems());
    return tabs;
}


public Tab createTab(String text,Class<? extends Component> navigationTarget){
    final Tab tab = new Tab();
    tab.add(new RouterLink(text, navigationTarget));
    ComponentUtil.setData(tab, Class.class, navigationTarget);
    return tab;
}


@Override
public void afterNavigation(){
    super.afterNavigation();
    getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);
    viewTitle.setText(getCurrentPageTitle());
}


public Component createDrawerContent(Tabs menu){
    VerticalLayout layout = new VerticalLayout();
    Element logoutLink = ElementFactory.createAnchor("logout", "Logout");
    layout.getThemeList().set("dark", true);
    layout.setSizeFull();
    layout.setPadding(false);
    layout.setSpacing(false);
    layout.getThemeList().set("spacing-s", true);
    layout.setAlignItems(FlexComponent.Alignment.STRETCH);
    HorizontalLayout logoLayout = new HorizontalLayout();
    logoLayout.setId("logo");
    logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
    logoLayout.setSpacing(true);
    logoLayout.add(new Image("icons/logo.png", "Logo"));
    logoLayout.add(new H1("IW"));
    // logoLayout.add(logoutLink);
    getElement().appendChild(logoutLink);
    layout.add(logoLayout, menu);
    return layout;
}


public Component createHeaderContent(){
    HorizontalLayout layout = new HorizontalLayout();
    layout.setId("header");
    layout.getThemeList().set("dark", true);
    layout.setWidthFull();
    layout.setSpacing(true);
    layout.setAlignItems(FlexComponent.Alignment.CENTER);
    layout.add(new DrawerToggle());
    viewTitle = new H1("Centros Sanitarios La Paz");
    layout.add(viewTitle);
    layout.add(new Image("icons/logo.png", "Logo"));
    return layout;
}


public String getCurrentPageTitle(){
    return getContent().getClass().getAnnotation(PageTitle.class).value();
}


}