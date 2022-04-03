package de.metas.ui.web.window.datatypes.json;
 import java.time.format.DateTimeFormatter;
import org.compiere.util.Env;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class JSONDateConfig {

 public  JSONDateConfig DEFAULT;

@NonNull
 private DateTimeFormatter zonedDateTimeFormatter;

@NonNull
 private DateTimeFormatter timestampFormatter;

@NonNull
 private DateTimeFormatter localDateFormatter;

@NonNull
 private DateTimeFormatter localTimeFormatter;


}