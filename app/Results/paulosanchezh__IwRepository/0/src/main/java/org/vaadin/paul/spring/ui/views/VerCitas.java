package org.vaadin.paul.spring.ui.views;
 import java.util.List;
import org.vaadin.paul.spring.entities.Centro;
import org.vaadin.paul.spring.entities.Cita;
import org.vaadin.paul.spring.entities.Trabajador;
import org.vaadin.paul.spring.entities.User;
import org.vaadin.paul.spring.repositories.CentroRepository;
import org.vaadin.paul.spring.repositories.CitaRepository;
import org.vaadin.paul.spring.repositories.UserRepository;
import com.vaadin.flow.component.grid.Grid;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.value.HasValueChangeMode;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.sql.Delete;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.paul.spring.MainView;
import org.vaadin.paul.spring.app.security.SecurityUtils;
import org.vaadin.paul.spring.Interface.UserRepository;
import org.vaadin.paul.spring.Interface.User;
@Route(value = "ver-citas", layout = MainView.class)
@PageTitle("Citas")
@Secured({ "ROLE_USER" })
public class VerCitas extends VerticalLayout{

 private  Grid<Cita> grid;

 private  UserRepository repousuario;

 private  CitaRepository repo;

 private User user;

 private  DatePicker filtrofecha;

 private  TimePicker filtrohora;

public VerCitas(UserRepository repousuario, CitaRepository repo) {
    this.repo = repo;
    this.repousuario = repousuario;
    this.grid = new Grid<>();
    Button crearcitabutton = new Button("Coger cita");
    H1 h = new H1(this.user.getNombreyApellidos());
    Binder<Cita> binder = new Binder<>(Cita.class);
    ListDataProvider<Cita> dataProvider = new ListDataProvider<Cita>(listcitas());
    this.grid.setDataProvider(dataProvider);
    HeaderRow filterRow = grid.appendHeaderRow();
    Grid.Column<Cita> SanitarioColumn = this.grid.addColumn(Cita::getNombreyApellidosSanitario, "Sanitario" + " ").setHeader("Sanitario");
    Grid.Column<Cita> FechaColumn = this.grid.addColumn(Cita::getFecha, "Fecha").setHeader("Fecha");
    Grid.Column<Cita> HoraColumn = this.grid.addColumn(Cita::getHora, "Hora").setHeader("Hora");
    Grid.Column<Cita> CentroColumn = this.grid.addColumn(Cita::getCentroString, "Centro").setHeader("Centro");
    Grid.Column<Cita> ConfirmadaColumn = this.grid.addColumn(Cita::getConfirmadaString, "Confirmada").setHeader("Confirmada");
    // Filtro Sanitario
    TextField SanitarioField = new TextField();
    SanitarioField.addValueChangeListener(event -> dataProvider.addFilter(cita -> StringUtils.containsIgnoreCase(cita.getNombreyApellidosSanitario(), SanitarioField.getValue())));
    SanitarioField.setValueChangeMode(ValueChangeMode.EAGER);
    filterRow.getCell(SanitarioColumn).setComponent(SanitarioField);
    SanitarioField.setSizeFull();
    SanitarioField.setPlaceholder("Sanitario");
    // filtro Fecha
    TextField FechaField = new TextField();
    FechaField.addValueChangeListener(event -> dataProvider.addFilter(cita -> StringUtils.containsIgnoreCase(cita.getFecha().toString(), FechaField.getValue())));
    FechaField.setValueChangeMode(ValueChangeMode.EAGER);
    filterRow.getCell(FechaColumn).setComponent(FechaField);
    FechaField.setSizeFull();
    FechaField.setPlaceholder("Fecha");
    // Filtro Hora
    TextField HoraField = new TextField();
    HoraField.addValueChangeListener(event -> dataProvider.addFilter(cita -> StringUtils.containsIgnoreCase(cita.getHora().toString(), HoraField.getValue())));
    HoraField.setValueChangeMode(ValueChangeMode.EAGER);
    filterRow.getCell(HoraColumn).setComponent(HoraField);
    HoraField.setSizeFull();
    HoraField.setPlaceholder("Hora");
    // Filtro Centro
    TextField CentroField = new TextField();
    CentroField.addValueChangeListener(event -> dataProvider.addFilter(cita -> StringUtils.containsIgnoreCase(cita.getCentroString(), CentroField.getValue())));
    CentroField.setValueChangeMode(ValueChangeMode.EAGER);
    filterRow.getCell(CentroColumn).setComponent(CentroField);
    CentroField.setSizeFull();
    CentroField.setPlaceholder("Centro");
    // Filtro confirmada
    TextField ConfirmadaField = new TextField();
    ConfirmadaField.addValueChangeListener(event -> dataProvider.addFilter(cita -> StringUtils.containsIgnoreCase(cita.getConfirmadaString(), ConfirmadaField.getValue())));
    ConfirmadaField.setValueChangeMode(ValueChangeMode.EAGER);
    filterRow.getCell(ConfirmadaColumn).setComponent(ConfirmadaField);
    ConfirmadaField.setSizeFull();
    ConfirmadaField.setPlaceholder("Confirmada");
    grid.addColumn(new ComponentRenderer<>(cita -> {
        Button modificarbutton = new Button("Modificar");
        DatePicker fecha = new DatePicker("Fecha cita");
        TimePicker hora = new TimePicker("Hora cita");
        Trabajador trabajador = listTrabajador(cita);
        if (cita.getConfirmada())
            modificarbutton.setEnabled(false);
        if (fecha.getValue() != null)
            if (LocalDate.now().compareTo(fecha.getValue()) == 0) {
                if (trabajador.getHoraInicio().compareTo(LocalTime.now()) > 0) {
                    hora.setMinTime(trabajador.getHoraInicio());
                } else {
                    int min, hour;
                    if (LocalTime.now().getMinute() >= 30) {
                        min = 0;
                        hour = +2;
                    } else {
                        min = 30;
                        hour = 1;
                    }
                    hora.setMinTime(LocalTime.of(LocalTime.now().getHour() + hour, min, 0));
                }
            }
        hora.setStep(Duration.ofMinutes(30));
        hora.setMinTime(trabajador.getHoraInicio());
        hora.setMaxTime(trabajador.getHoraFinal());
        fecha.setMin(LocalDate.now());
        modificarbutton.addClickListener(event -> {
            binder.forField(fecha).asRequired("Debes de selecionar una fecha").bind(Cita::getFecha, Cita::setFecha);
            add(fecha);
            binder.forField(hora).asRequired("Debes de selecionar una hora").bind(Cita::getHora, Cita::setHora);
            add(hora);
            Dialog dialog = new Dialog();
            dialog.setModal(true);
            dialog.setDraggable(true);
            dialog.setResizable(false);
            dialog.setWidth("1200px");
            dialog.setHeight("1000px");
            dialog.open();
            Button closebutton = new Button("close", e -> dialog.close());
            Button confirmarbutton = new Button("Confirmar");
            dialog.add(fecha, hora, confirmarbutton, closebutton);
            binder.setBean(cita);
            confirmarbutton.addClickListener(e1 -> {
                if (binder.validate().isOk()) {
                    Notification.show("La cita ha sido modificada");
                    repo.save(cita);
                }
                dataProvider.refreshItem(cita);
                dialog.close();
            });
        });
        return modificarbutton;
    }));
    grid.addColumn(new ComponentRenderer<>(cita -> {
        Button cancelarbutton = new Button("Cancelar Cita");
        if (cita.getConfirmada())
            cancelarbutton.setEnabled(false);
        cancelarbutton.addClickListener(event -> {
            eliminarcita(cita);
            Notification.show("La cita ha sido cancelada");
            dataProvider.getItems().remove(cita);
            dataProvider.refreshAll();
        });
        cancelarbutton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        return cancelarbutton;
    }));
    add(h);
    crearcitabutton.addClickListener(event -> {
        crearcitabutton.getUI().ifPresent(ui -> ui.navigate(solicitarCita.class));
    });
    add(grid);
    add(crearcitabutton);
}
public Trabajador listTrabajador(Cita cita){
    return cita.getSanitario().getTrabajador();
}


public void eliminarcita(Cita c){
    repo.deleteById(c.getId());
}


public Collection<Cita> listcitas(){
    return repo.findByPaciente(repousuario.findByid(this.user.getId()));
}


}