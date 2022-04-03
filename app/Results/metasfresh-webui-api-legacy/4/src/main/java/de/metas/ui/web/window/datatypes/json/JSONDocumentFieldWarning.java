package de.metas.ui.web.window.datatypes.json;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.window.model.DocumentFieldWarning;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONDocumentFieldWarning {

@JsonProperty("caption")
 private  String caption;

@JsonProperty("message")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String message;

@JsonProperty("error")
 private  boolean error;


public JSONDocumentFieldWarning ofNullable(DocumentFieldWarning fieldWarning,String adLanguage){
    if (fieldWarning == null) {
        return null;
    }
    return new JSONDocumentFieldWarning(fieldWarning, adLanguage);
}


}