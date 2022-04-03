package de.metas.ui.web.pattribute.json;
 import javax.annotation.Nullable;
import org.adempiere.mm.attributes.AttributeSetInstanceId;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.window.datatypes.json.JSONDocumentPath;
import lombok.NonNull;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONCreateASIRequest {

@JsonProperty("templateId")
 private  AttributeSetInstanceId templateId;

@JsonProperty("source")
@JsonInclude(JsonInclude.Include.NON_ABSENT)
 private  JSONDocumentPath source;


}