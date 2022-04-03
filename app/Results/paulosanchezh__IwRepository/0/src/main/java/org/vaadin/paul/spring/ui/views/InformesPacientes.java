package org.vaadin.paul.spring.ui.views;
 import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.paul.spring.MainView;
import org.vaadin.paul.spring.app.security.SecurityUtils;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.vaadin.paul.spring.entities.Cita;
import org.vaadin.paul.spring.entities.Informe;
import org.vaadin.paul.spring.entities.User;
import org.vaadin.paul.spring.repositories.CitaRepository;
import org.vaadin.paul.spring.repositories.InformeRepository;
import org.vaadin.paul.spring.repositories.SanitarioRepository;
import org.vaadin.paul.spring.repositories.TrabajadorRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.paul.spring.Interface.SanitarioRepository;
import org.vaadin.paul.spring.Interface.TrabajadorRepository;
@Route(value = "informes-pacientes", layout = MainView.class)
@PageTitle("Informes Pacientes")
@Secured({ "ROLE_SANITARIO" })
public class InformesPacientes extends FormLayout{

 private  Grid<Cita> grid;

 private  InformeRepository repoinformes;

 private  SanitarioRepository reposanitario;

 private  TrabajadorRepository repotrabajador;

 private  CitaRepository repo;

 private  List<Informe> informes;

public InformesPacientes(InformeRepository repoinformes, SanitarioRepository reposanitario, CitaRepository repo, TrabajadorRepository repotrabajador) throws FileNotFoundException {
    this.repoinformes = repoinformes;
    this.grid = new Grid<>();
    this.reposanitario = reposanitario;
    this.repo = repo;
    this.repotrabajador = repotrabajador;
    ListDataProvider<Cita> dataProvider = new ListDataProvider<Cita>(listCitas());
    this.grid.setDataProvider(dataProvider);
    Grid.Column<Cita> SanitarioColumn = this.grid.addColumn(Cita::getNombreyApellidospaciente, "Paciente").setHeader("Paciente");
    Grid.Column<Cita> FechaColumn = this.grid.addColumn(Cita::getFecha, "Fecha").setHeader("Fecha");
    Grid.Column<Cita> HoraColumn = this.grid.addColumn(Cita::getHora, "Hora").setHeader("Hora");
    Grid.Column<Cita> CentroColumn = this.grid.addColumn(Cita::getCentroString, "Centro").setHeader("Centro");
    HeaderRow filterRow = grid.appendHeaderRow();
    // Filtro Sanitario
    TextField SanitarioField = new TextField();
    SanitarioField.addValueChangeListener(event -> dataProvider.addFilter(cita -> StringUtils.containsIgnoreCase(cita.getNombreyApellidospaciente(), SanitarioField.getValue())));
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
    grid.addColumn(new ComponentRenderer<>(cita -> {
        Informe informe = cita.getInforme();
        Accordion Datos_informe = new Accordion();
        Button confirmbutton = new Button("Informe");
        H1 h = new H1(cita.getNombreyApellidospaciente());
        Dialog dialog = new Dialog();
        dialog.add(h, Datos_informe);
        dialog.add(new Button("Close", e -> dialog.close()));
        if (!informe.getFirma()) {
            confirmbutton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            dialog.add(new Button("Firmar", e -> {
                informe.setFirma(true);
                repoinformes.save(informe);
                Notification.show("El informe ha sido firmado");
                dataProvider.refreshItem(cita);
                dialog.close();
            }));
        }
        Datos_informe.add("Porque", new Span(informe.getPorQue()));
        Datos_informe.add("Diagnostico", new Span(informe.getDiagnostico()));
        Datos_informe.add("Enfermedad Actual", new Span(informe.getEnfermedadActual()));
        Datos_informe.add("Intervencion", new Span(informe.getIntervencion()));
        Datos_informe.add("Medicamentos", new Span(informe.getMedicamentos()));
        Datos_informe.add("Plan Clinico", new Span(informe.getPlanClinico()));
        dialog.setModal(true);
        dialog.setDraggable(true);
        dialog.setResizable(false);
        dialog.setWidth("1200px");
        dialog.setHeight("1000px");
        confirmbutton.addClickListener(event -> {
            dialog.open();
        });
        return confirmbutton;
    }));
    add(grid);
}
public Collection<Cita> listCitas(){
    User u = (User) SecurityUtils.getAuthenticatedUser();
    List<Cita> citas = repo.findBySanitarioAndInforme(reposanitario.findByTrabajador(repotrabajador.findByUser(u)).getid());
    return citas;
}


}