package de.metas.ui.web.window.datatypes;
 import java.time.LocalDate;
import lombok.Value;
@Value(staticConstructor = "of")
public class DateRangeValue {

 private  LocalDate from;

 private  LocalDate to;


}