package de.metas.ui.web.window.datatypes.json;
 import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Data
public class JSONDocumentChangeLog {

 private JSONDocumentPath path;

 private String createdByUsername;

 private String createdTimestamp;

 private String lastChangedByUsername;

 private String lastChangedTimestamp;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private List<JSONDocumentChangeLogEntry> entries;


}