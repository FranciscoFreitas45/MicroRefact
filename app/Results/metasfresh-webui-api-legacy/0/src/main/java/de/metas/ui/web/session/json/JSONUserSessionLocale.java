package de.metas.ui.web.session.json;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import de.metas.ui.web.session.UserSessionLocale;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONUserSessionLocale {

 private  char numberDecimalSeparator;

 private  char numberGroupingSeparator;


public JSONUserSessionLocale of(UserSessionLocale locale){
    return new JSONUserSessionLocale(locale);
}


}