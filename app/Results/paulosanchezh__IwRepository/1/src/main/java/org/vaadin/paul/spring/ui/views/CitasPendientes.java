package org.vaadin.paul.spring.ui.views;
 import java.time.LocalDate;
import org.vaadin.paul.spring.entities.Cita;
import org.vaadin.paul.spring.entities.HistorialClinico;
import org.vaadin.paul.spring.entities.User;
import org.vaadin.paul.spring.repositories.CitaRepository;
import org.vaadin.paul.spring.repositories.HistorialClinicoRepository;
import org.vaadin.paul.spring.repositories.SanitarioRepository;
import org.vaadin.paul.spring.repositories.TrabajadorRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.paul.spring.MainView;
import org.vaadin.paul.spring.app.security.SecurityUtils;
import org.vaadin.paul.spring.Interface.CitaRepository;
import org.vaadin.paul.spring.Interface.SanitarioRepository;
@Route(value = "citas-pendientes", layout = MainView.class)
@PageTitle("Citas Pendientes")
@Secured({ "ROLE_SANITARIO" })
public class CitasPendientes extends VerticalLayout{

 private  long serialVersionUID;

 private  CitaRepository repo;

 private  SanitarioRepository reposanitario;

 private  TrabajadorRepository repotrabajador;

 private  HistorialClinicoRepository repoHistorial;

 final  Grid<Cita> grid;

 private  HistorialClinico historial;

public CitasPendientes(CitaRepository repo, SanitarioRepository repouser, TrabajadorRepository repotrabajador, HistorialClinicoRepository repoHistorial) {
    this.repo = repo;
    this.reposanitario = repouser;
    this.repotrabajador = repotrabajador;
    this.repoHistorial = repoHistorial;
    this.grid = new Grid<>();
    grid.addColumn(Cita::getNombreyApellidospaciente, "Nombres y Apellidos").setHeader("Nombre y apellidos");
    grid.addColumn(Cita::getHora, "Hora").setHeader("Hora");
    grid.addColumn(new ComponentRenderer<>(cita -> {
        Button confirmbutton = new Button("Confirmar");
        confirmbutton.addClickListener(event -> {
            historial = listHistorial(cita);
            cita.setConfirmada(true);
            repo.save(cita);
            historial.getCitas().add(cita);
            listCustomers();
        });
        return confirmbutton;
    }));
    add(grid);
    listCustomers();
}
public void listCustomers(){
    LocalDate hoy = LocalDate.now();
    User u = (User) SecurityUtils.getAuthenticatedUser();
    grid.setItems(repo.findByFechaAndSanitarioAndConfirmada(hoy, reposanitario.findByTrabajador(repotrabajador.findByUser(u)), false));
}


public HistorialClinico listHistorial(Cita cita){
    return repoHistorial.findByPaciente(cita.getPaciente());
}


}