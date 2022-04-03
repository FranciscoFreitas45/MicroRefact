package de.metas.ui.web.view;
 import java.time.ZonedDateTime;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class ViewHeaderProperty {

@NonNull
 private ITranslatableString caption;

@NonNull
 private ITranslatableString value;


public ViewHeaderPropertyBuilder value(ZonedDateTime value){
    return value(TranslatableStrings.dateAndTime(value));
}


}