package de.metas.ui.web.window.datatypes.json;
 import java.time.ZonedDateTime;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import lombok.Data;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Data
public class JSONDocumentChangeLogEntry {

 private String columnDisplayName;

 private Object valueNew;

 private Object valueOld;

 private ZonedDateTime changedTimestamp;

 private String changedByUsername;


}