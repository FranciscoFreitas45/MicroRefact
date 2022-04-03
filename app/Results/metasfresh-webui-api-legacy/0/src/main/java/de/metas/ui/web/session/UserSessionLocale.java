package de.metas.ui.web.session;
 import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import org.compiere.util.DisplayType;
import de.metas.cache.CCache;
import de.metas.i18n.Language;
import lombok.NonNull;
import lombok.Value;
@Value
public class UserSessionLocale {

 private  CCache<String,UserSessionLocale> cache;

 private  String adLanguage;

 private  char numberDecimalSeparator;

 private  char numberGroupingSeparator;


public UserSessionLocale get(String adLanguage){
    return cache.getOrLoad(adLanguage, () -> new UserSessionLocale(adLanguage));
}


}