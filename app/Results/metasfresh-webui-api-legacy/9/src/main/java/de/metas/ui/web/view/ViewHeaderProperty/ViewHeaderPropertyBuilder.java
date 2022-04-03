package de.metas.ui.web.view.ViewHeaderProperty;
 import java.time.ZonedDateTime;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
public class ViewHeaderPropertyBuilder {


public ViewHeaderPropertyBuilder value(ZonedDateTime value){
    return value(TranslatableStrings.dateAndTime(value));
}


}