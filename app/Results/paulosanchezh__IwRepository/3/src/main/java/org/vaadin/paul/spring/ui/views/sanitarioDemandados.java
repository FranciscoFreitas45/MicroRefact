package org.vaadin.paul.spring.ui.views;
 import java.util.Iterator;
import java.util.List;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.paul.spring.MainView;
import org.vaadin.paul.spring.entities.Especialidad;
import org.vaadin.paul.spring.entities.Sanitario;
import org.vaadin.paul.spring.repositories.EspecialidadRepository;
import org.vaadin.paul.spring.repositories.SanitarioRepository;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.Labels;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.PlotOptionsColumn;
import com.vaadin.flow.component.charts.model.SeriesTooltip;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.charts.model.YAxis;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.paul.spring.Interface.EspecialidadRepository;
@Route(value = "sanitariodemandados", layout = MainView.class)
@PageTitle("Profesionalidades mas demandados")
@Secured({ "ROLE_ADMIN" })
public class sanitarioDemandados extends VerticalLayout{

 private  EspecialidadRepository repoEspecialidad;

 private  SanitarioRepository repoSanitario;

 private  Chart chart;

 private  Chart chart2;

 private  ListSeries series;

 private  ListSeries series2;

 private  Configuration conf;

 private  Configuration conf2;

public sanitarioDemandados(SanitarioRepository repoSanitario, EspecialidadRepository repoEspecialidad) {
    chart = new Chart();
    conf = chart.getConfiguration();
    conf.setTitle("Profesionales Más Demandados");
    XAxis x = new XAxis();
    YAxis y = new YAxis();
    Labels yLabels = new Labels();
    yLabels.setFormat("{value}");
    y.setLabels(yLabels);
    y.setTitle("Citas");
    conf.addyAxis(y);
    series = new ListSeries();
    List<Sanitario> lSanitarios = repoSanitario.findAll();
    Iterator<Sanitario> itr = lSanitarios.iterator();
    while (itr.hasNext()) {
        Sanitario s = itr.next();
        x.addCategory(s.getNombreyApellidos());
        series.addData(repoSanitario.countBySanitario(s.getid()));
    }
    conf.addxAxis(x);
    PlotOptionsColumn rainfallOptions = new PlotOptionsColumn();
    SeriesTooltip rainfallTooltip = new SeriesTooltip();
    rainfallTooltip.setPointFormat("<span style=\"font-weight: bold; color: {series.color}\">" + "{series.name}</span>: <b>{point.y:.1f} mm</b> ");
    rainfallOptions.setTooltip(rainfallTooltip);
    series.setPlotOptions(rainfallOptions);
    series.setName("Profesionales Sanitarios");
    conf.addSeries(series);
    conf.setSubTitle("");
    add(chart);
    // ///////GRAFICA 2
    chart2 = new Chart();
    conf2 = chart2.getConfiguration();
    conf2.setTitle("Especialidades Más Demandadas");
    XAxis x2 = new XAxis();
    YAxis y2 = new YAxis();
    Labels yLabels2 = new Labels();
    yLabels2.setFormat("{value}");
    y2.setLabels(yLabels2);
    y2.setTitle("Citas");
    conf2.addyAxis(y2);
    series2 = new ListSeries();
    List<Especialidad> lEspecialidades = repoEspecialidad.findAll();
    Iterator<Especialidad> itr2 = lEspecialidades.iterator();
    while (itr2.hasNext()) {
        Especialidad e = itr2.next();
        x2.addCategory(e.getNombre());
        series2.addData(repoEspecialidad.countByEspecialidad(e.getId()));
    }
    conf2.addxAxis(x2);
    PlotOptionsColumn rainfallOptions2 = new PlotOptionsColumn();
    SeriesTooltip rainfallTooltip2 = new SeriesTooltip();
    rainfallTooltip2.setPointFormat("<span style=\"font-weight: bold; color: {series.color}\">" + "{series.name}</span>: <b>{point.y:.1f} mm</b> ");
    rainfallOptions2.setTooltip(rainfallTooltip);
    series2.setPlotOptions(rainfallOptions);
    series2.setName("Especialidades");
    conf2.addSeries(series2);
    conf2.setSubTitle("");
    add(chart2);
}
}