package org.vaadin.paul.spring.ui.views;
 import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.vaadin.paul.spring.MainView;
import org.vaadin.paul.spring.app.security.SecurityUtils;
import org.vaadin.paul.spring.entities.Centro;
import org.vaadin.paul.spring.entities.Cita;
import org.vaadin.paul.spring.entities.Rol;
import org.vaadin.paul.spring.entities.Sanitario;
import org.vaadin.paul.spring.entities.User;
import org.vaadin.paul.spring.repositories.RolRepository;
import org.vaadin.paul.spring.repositories.UserRepository;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.paul.spring.Interface.UserRepository;
import org.vaadin.paul.spring.Interface.User;
@Route(value = "register", layout = MainView.class)
@PageTitle("Página de registro")
public class createUser extends FormLayout{

 private RolRepository repoRol;

 private UserRepository repoUser;

 private  Button saveButton;

 private  TextField apellidos;

 private  TextField nombre;

 private  TextField dni;

 private  TextField direccion;

 private  TextField tlf;

 private  TextField username;

 private PasswordField passwordField;

 private PasswordField confirmPasswordField;

 private User usuario;

public createUser(UserRepository repoUser, RolRepository repoRol) {
    Binder<User> binder = new Binder<>(User.class);
    ComboBox<Rol> cRol = new ComboBox<>();
    cRol.setItems(repoRol.findAll());
    cRol.setItemLabelGenerator(Rol::getNombre);
    binder.forField(apellidos).asRequired("Apellidos no puede estar vacío").bind(User::getApellidos, User::setApellidos);
    binder.forField(nombre).asRequired("El nombre no puede estar vacío").bind(User::getNombre, User::setNombre);
    dni.setLabel("DNI (sin letra)");
    binder.forField(dni).asRequired("DNI no puede estar vacío").withValidator(dni -> dni.length() == 8, "El formato del Dni no es correcto, El dni consta de 8 dígitos").withValidator(dni -> StringUtils.isNumeric(dni) == true, "El DNI sólo debe contener caracteres numéricos").withValidator(dni -> repoUser.findBydni(dni) == null, "Este DNI ya existe, compruebe que ha sido introducido correctamente").bind(User::getDni, User::setDni);
    binder.forField(direccion).asRequired("La direccion no puede estar vacía").bind(User::getDireccion, User::setDireccion);
    binder.forField(tlf).asRequired("El teléfono no puede estar vacío").withValidator(telefono -> telefono.length() == 9, "El formato del Teléfono no es correcto, El Teléfono consta de 9 dígitos").withValidator(telefono -> StringUtils.isNumeric(telefono) == true, "El Teléfono sólo debe contener carácteres numéricos").bind(User::getTlf, User::setTlf);
    // binder.forField(username)
    // .asRequired("El nombre de usuario no puede estar vacío")
    // .withValidator(username -> repoUser, "")
    // .bind(User::getUsername, User::setUsername);
    binder.forField(passwordField).asRequired("La contraseña no puede estar vacía").withValidator(pass -> pass.length() > 5, "La contraña es demasiado corta, deben ser de al menos 6 carácteres").withValidator(pass -> StringUtils.isAlpha(pass) == false, "La contraseña tiene que ser alfanumérica, tiene que contener letras y numeros").bind(User::getPassword, User::setPassword);
    binder.forField(confirmPasswordField).asRequired("Debes confirmar la contraseña").withValidator(user -> {
        if (passwordField.isEmpty() || confirmPasswordField.isEmpty()) {
            return true;
        } else {
            return Objects.equals(passwordField.getValue(), confirmPasswordField.getValue());
        }
    }, "Las contraseñas deben coincidir, revise los campos").bind(User::getPassword, (person, password) -> {
    });
    // binder.forField(cRol)
    // .asRequired("Debes escoger un Rol")
    // .bind(User::getRol, User::setRol);
    Label validationStatus = new Label();
    binder.setStatusLabel(validationStatus);
    binder.setBean(usuario);
    usuario.setBaja(false);
    usuario.setUsername(usuario.getDni());
    Dialog firstDialog = new Dialog();
    firstDialog.add(new Text("Te has registrado correctamente"), new Button("Volver", e -> {
        firstDialog.close();
        UI.getCurrent().navigate("login");
    }));
    firstDialog.setModal(false);
    firstDialog.setDraggable(false);
    firstDialog.setResizable(false);
    firstDialog.setCloseOnEsc(false);
    saveButton.addClickListener(event -> {
        if (binder.validate().isOk()) {
            repoUser.save(usuario);
            firstDialog.open();
        }
    });
    add(nombre);
    add(apellidos);
    add(dni);
    add(direccion);
    add(tlf);
    // add(username);
    add(passwordField);
    add(confirmPasswordField);
    // add(cRol);
    add(saveButton);
}
}