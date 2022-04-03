package de.metas.ui.web.view;
 import lombok.NonNull;
import lombok.Value;
import de.metas.i18n.ITranslatableString;
@Value(staticConstructor = "of")
public class ViewProfile {

@NonNull
 private  ViewProfileId profileId;

@NonNull
 private  ITranslatableString caption;


}