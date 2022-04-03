package de.metas.ui.web.mail.json;
 import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.mail.WebuiEmail;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import lombok.Getter;
import lombok.NonNull;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Getter
public class JSONEmail {

 private  String emailId;

 private  JSONLookupValue from;

 private  List<JSONLookupValue> to;

 private  String subject;

 private  String message;

 private  List<JSONLookupValue> attachments;


public JSONEmail of(WebuiEmail email,String adLanguage){
    return new JSONEmail(email, adLanguage);
}


}