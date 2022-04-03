package de.metas.ui.web.window.model;
 import de.metas.i18n.ITranslatableString;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
public class DocumentFieldWarning {

 private ITranslatableString caption;

 private String message;

 private boolean error;


}