package org.vaadin.paul.spring.ui.views;
 import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Binder.Binding;
import com.vaadin.flow.data.provider.DataProviderListener;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.paul.spring.MainView;
import org.vaadin.paul.spring.entities.Centro;
import org.vaadin.paul.spring.entities.Cita;
import org.vaadin.paul.spring.entities.Especialidad;
import org.vaadin.paul.spring.entities.Trabajador;
import org.vaadin.paul.spring.entities.User;
import org.vaadin.paul.spring.repositories.CentroRepository;
import org.vaadin.paul.spring.repositories.EspecialidadRepository;
import org.vaadin.paul.spring.repositories.TrabajadorRepository;
import org.vaadin.paul.spring.repositories.UserRepository;
import org.vaadin.paul.spring.Interface.CentroRepository;
import org.vaadin.paul.spring.Interface.TrabajadorRepository;
import org.vaadin.paul.spring.Interface.UserRepository;
import org.vaadin.paul.spring.Interface.EspecialidadRepository;
@Route(value = "crudCentro", layout = MainView.class)
@PageTitle("Gestión de Centros")
@Secured({ "ROLE_ADMIN" })
public class crudCentro extends VerticalLayout{

 private  CentroRepository repoCentro;

 private  TrabajadorRepository repoTrabajador;

 private  UserRepository repoUser;

 private  EspecialidadRepository repoEspecialidad;

 private  Grid<Centro> gCentro;

public crudCentro(CentroRepository repoCentro, TrabajadorRepository repoTrabajador, UserRepository repoUser, EspecialidadRepository repoEspecialidad) {
    gCentro = new Grid<>();
    ListDataProvider<Centro> dataProvider = new ListDataProvider<Centro>(repoCentro.findAll());
    HeaderRow filterRow = gCentro.appendHeaderRow();
    Grid.Column<Centro> NombreColumn = gCentro.addColumn(Centro::getNombre, "Nombre" + " ").setHeader("Nombre");
    Grid.Column<Centro> TlfColumn = gCentro.addColumn(Centro::getTelefono, "Telefono" + " ").setHeader("Telefono");
    TextField NombreField = new TextField();
    NombreField.addValueChangeListener(event -> dataProvider.addFilter(centro -> StringUtils.containsIgnoreCase(centro.getNombre(), NombreField.getValue())));
    NombreField.setValueChangeMode(ValueChangeMode.EAGER);
    filterRow.getCell(NombreColumn).setComponent(NombreField);
    NombreField.setSizeFull();
    NombreField.setPlaceholder("Nombre");
    TextField tlfField = new TextField();
    tlfField.addValueChangeListener(event -> dataProvider.addFilter(centro -> StringUtils.containsIgnoreCase(centro.getTelefono(), tlfField.getValue())));
    tlfField.setValueChangeMode(ValueChangeMode.EAGER);
    filterRow.getCell(TlfColumn).setComponent(tlfField);
    tlfField.setSizeFull();
    tlfField.setPlaceholder("Telefono");
    gCentro.addColumn(new ComponentRenderer<>(centro -> {
        Button especialidadButton = new Button("Especialidades");
        especialidadButton.addClickListener(event -> {
            Grid<Especialidad> gEspecialidad = new Grid<>();
            Dialog dialog = new Dialog();
            dialog.add(gEspecialidad, new Button("Close", e -> dialog.close()));
            dialog.setModal(true);
            dialog.setDraggable(true);
            dialog.setResizable(false);
            dialog.setWidth("1200px");
            dialog.setHeight("1000px");
            gEspecialidad.setItems(centro.getEspecialidad());
            gEspecialidad.addColumn(Especialidad::getNombre).setHeader("Nombre Especialidad");
            gEspecialidad.addColumn(new ComponentRenderer<>(especialidad -> {
                Button deleteButton = new Button("Eliminar");
                deleteButton.addClickListener(delete -> {
                    List<Especialidad> lEspecialidad = centro.getEspecialidad();
                    lEspecialidad.remove(especialidad);
                    centro.setEspecialidad(lEspecialidad);
                    repoCentro.save(centro);
                    gEspecialidad.setItems(centro.getEspecialidad());
                });
                return deleteButton;
            }));
            dialog.open();
        });
        return especialidadButton;
    }));
    gCentro.addColumn(new ComponentRenderer<>(centro -> {
        Button addSanitarioButton = new Button("Añadir Especialidad");
        addSanitarioButton.addClickListener(event -> {
            Grid<Especialidad> gEspecialidad = new Grid<>();
            Dialog dialog = new Dialog();
            dialog.add(gEspecialidad, new Button("Close", e -> dialog.close()));
            dialog.setModal(true);
            dialog.setDraggable(true);
            dialog.setResizable(false);
            dialog.setWidth("1200px");
            dialog.setHeight("1000px");
            gEspecialidad.addColumn(Especialidad::getNombre).setHeader("Especialidad");
            gEspecialidad.setItems(repoEspecialidad.especialidadesQueNoTengaEseCentro(centro.getId()));
            gEspecialidad.addColumn(new ComponentRenderer<>(especialidad -> {
                Button addButton = new Button("Añadir");
                addButton.addClickListener(añadir -> {
                    List<Especialidad> lEspecialidades = centro.getEspecialidad();
                    lEspecialidades.add(especialidad);
                    centro.setEspecialidad(lEspecialidades);
                    repoCentro.save(centro);
                    gEspecialidad.setItems(repoEspecialidad.especialidadesQueNoTengaEseCentro(centro.getId()));
                });
                return addButton;
            }));
            dialog.open();
        });
        return addSanitarioButton;
    }));
    gCentro.addColumn(new ComponentRenderer<>(centro1 -> {
        Button trabajadorButton = new Button("Trabajadores");
        trabajadorButton.addClickListener(event -> {
            Grid<Trabajador> gTrabajador = new Grid<>();
            Dialog dialog = new Dialog();
            dialog.add(gTrabajador, new Button("Close", e -> dialog.close()));
            dialog.setModal(true);
            dialog.setDraggable(true);
            dialog.setResizable(false);
            dialog.setWidth("1200px");
            dialog.setHeight("1000px");
            List<Trabajador> lTrabajador = centro1.getTrabajadores();
            gTrabajador.setItems(centro1.getTrabajadores());
            gTrabajador.addColumn(Trabajador::getNombre).setHeader("Nombre Trabajador");
            gTrabajador.addColumn(Trabajador::getSalario).setHeader("Salario €");
            gTrabajador.addColumn(new ComponentRenderer<>(trabajador -> {
                Button deleteButtonTrabajador = new Button("Eliminar");
                deleteButtonTrabajador.addClickListener(delete -> {
                    Iterator<Trabajador> itr = lTrabajador.iterator();
                    while (itr.hasNext()) {
                        Trabajador t = itr.next();
                        if (t.equals(trabajador)) {
                            t.setCentro(null);
                            repoTrabajador.save(t);
                            itr.remove();
                        }
                    }
                    centro1.setTrabajadores(lTrabajador);
                    repoCentro.save(centro1);
                    gTrabajador.setItems(centro1.getTrabajadores());
                });
                return deleteButtonTrabajador;
            }));
            dialog.open();
        });
        return trabajadorButton;
    }));
    gCentro.addColumn(new ComponentRenderer<>(centro -> {
        Button addTrabajadorButton = new Button("Añadir Trabajador");
        addTrabajadorButton.addClickListener(event -> {
            Grid<Trabajador> gTrabajador = new Grid<>();
            Dialog dialog = new Dialog();
            dialog.add(gTrabajador, new Button("Close", e -> dialog.close()));
            dialog.setModal(true);
            dialog.setDraggable(true);
            dialog.setResizable(false);
            dialog.setWidth("1200px");
            dialog.setHeight("1000px");
            gTrabajador.addColumn(Trabajador::getNombre).setHeader("Nombre y Apellidos del médico");
            gTrabajador.addColumn(Trabajador::getSalario).setHeader("Salario €");
            gTrabajador.setItems(repoTrabajador.findByTrabajadoresNulos());
            gTrabajador.addColumn(new ComponentRenderer<>(trabajador -> {
                Button addButton = new Button("Añadir");
                addButton.addClickListener(añadir -> {
                    List<Trabajador> lTrabajadores = centro.getTrabajadores();
                    lTrabajadores.add(trabajador);
                    centro.setTrabajadores(lTrabajadores);
                    trabajador.setCentro(centro);
                    repoTrabajador.save(trabajador);
                    repoCentro.save(centro);
                    gTrabajador.setItems(repoTrabajador.findByTrabajadoresNulos());
                });
                return addButton;
            }));
            dialog.open();
        });
        return addTrabajadorButton;
    }));
    gCentro.addColumn(new ComponentRenderer<>(centro3 -> {
        Button editButtonCentro = new Button("Editar");
        editButtonCentro.addClickListener(event -> {
            TextField nombre = new TextField("Nombre");
            TextField telefono = new TextField("Telefono");
            Binder<Centro> bCentro = new Binder<>(Centro.class);
            nombre.setLabel("Nombre");
            // nombre.setPlaceholder(centro.getNombre());
            telefono.setLabel("Telefono");
            // telefono.setPlaceholder(centro.getTelefono());
            System.out.println(nombre.getValue());
            bCentro.forField(nombre).asRequired("Nombre no puede estar vacío").withValidator(nom -> repoCentro.findByName(nom, centro3.getId()) == null, "Este DNI ya existe, compruebe que ha sido introducido correctamente").bind(Centro::getNombre, Centro::setNombre);
            bCentro.forField(telefono).asRequired("Teléfono no puede estar vacío").withValidator(tel -> tel.length() == 9, "El formato del Teléfono no es correcto, El Teléfono consta de 9 dígitos").withValidator(tel -> StringUtils.isNumeric(tel) == true, "El Teléfono sólo debe contener carácteres numéricos").bind(Centro::getTelefono, Centro::setTelefono);
            bCentro.setBean(centro3);
            Dialog dialog = new Dialog();
            Button close = new Button("editar");
            close.addClickListener(event3 -> {
                if (bCentro.validate().isOk()) {
                    repoCentro.save(centro3);
                    gCentro.setItems(repoCentro.findAll());
                    dialog.close();
                }
            });
            dialog.open();
            dialog.add(nombre, telefono, close);
            dialog.setModal(true);
            dialog.setDraggable(true);
            dialog.setResizable(false);
            dialog.setWidth("500px");
            dialog.setHeight("260px");
        });
        return editButtonCentro;
    }));
    gCentro.addColumn(new ComponentRenderer<>(centro -> {
        Button deleteButtonCentro = new Button("Eliminar");
        deleteButtonCentro.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteButtonCentro.addClickListener(event -> {
            List<Trabajador> lTrabajador = centro.getTrabajadores();
            Iterator<Trabajador> itr = lTrabajador.iterator();
            while (itr.hasNext()) {
                Trabajador t = itr.next();
                t.setCentro(null);
                repoTrabajador.save(t);
            }
            repoCentro.delete(centro);
            gCentro.setItems(repoCentro.findAll());
        });
        return deleteButtonCentro;
    }));
    gCentro.setDataProvider(dataProvider);
    add(gCentro);
    Button addCentro = new Button("Crear centro");
    addCentro.addClickListener(event -> {
        Centro centro = new Centro();
        TextField nombre = new TextField("Nombre");
        TextField telefono = new TextField("Telefono");
        Binder<Centro> bCentro = new Binder<>(Centro.class);
        nombre.setLabel("Nombre");
        // nombre.setPlaceholder(centro.getNombre());
        telefono.setLabel("Telefono");
        // telefono.setPlaceholder(centro.getTelefono());
        System.out.println(nombre.getValue());
        bCentro.forField(nombre).asRequired("Nombre no puede estar vacío").withValidator(nom -> repoCentro.findBynombre(nom) == null, "El centro ya existe").bind(Centro::getNombre, Centro::setNombre);
        bCentro.forField(telefono).asRequired("Teléfono no puede estar vacío").withValidator(tel -> tel.length() == 9, "El formato del Teléfono no es correcto, El Teléfono consta de 9 dígitos").withValidator(tel -> StringUtils.isNumeric(tel) == true, "El Teléfono sólo debe contener carácteres numéricos").bind(Centro::getTelefono, Centro::setTelefono);
        bCentro.setBean(centro);
        Dialog dialog = new Dialog();
        Button close = new Button("editar");
        close.addClickListener(event3 -> {
            if (bCentro.validate().isOk()) {
                repoCentro.save(centro);
                gCentro.setItems(repoCentro.findAll());
                dialog.close();
            }
        });
        dialog.open();
        dialog.add(nombre, telefono, close);
        dialog.setModal(true);
        dialog.setDraggable(true);
        dialog.setResizable(false);
        dialog.setWidth("500px");
        dialog.setHeight("260px");
    });
    add(addCentro);
}
}