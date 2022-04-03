package de.metas.ui.web.address.json;
 import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.window.datatypes.json.JSONDocumentPath;
import lombok.Value;
@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONCreateAddressRequest implements Serializable{

@JsonProperty("templateId")
 private  int templateId;

@JsonProperty("source")
@JsonInclude(JsonInclude.Include.NON_ABSENT)
 private  JSONDocumentPath source;


}