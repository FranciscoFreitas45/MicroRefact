package de.metas.ui.web.material.cockpit.filters;
 import java.time.LocalDate;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import lombok.Builder;
import lombok.Value;
@Value
@Builder
public class DateFilterVO {

 public  DateFilterVO EMPTY;

 public  String FILTER_ID;

 public  String PARAM_Date;

 private LocalDate date;


}