package de.metas.ui.web.window.model;
 import java.time.Duration;
import javax.annotation.Nullable;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
public class DocumentReference {

 private  String id;

 private  String internalName;

 private  ITranslatableString caption;

 private  WindowId windowId;

 private  int documentsCount;

 private  DocumentFilter filter;

 private  Duration loadDuration;


public String getCaption(String adLanguage){
    return caption.translate(adLanguage);
}


}