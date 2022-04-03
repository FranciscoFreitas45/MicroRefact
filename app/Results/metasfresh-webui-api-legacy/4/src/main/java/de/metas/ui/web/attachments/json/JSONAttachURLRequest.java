package de.metas.ui.web.attachments.json;
 import java.net.URI;
import java.net.URISyntaxException;
import org.adempiere.exceptions.AdempiereException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.printing.esb.base.util.Check;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONAttachURLRequest {

@JsonProperty("name")
 private  String name;

@JsonProperty("url")
 private  String url;

@JsonIgnore
 private  URI uri;


}