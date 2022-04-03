package de.metas.ui.web.i18n.I18NRestController;
 import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.UnaryOperator;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.X_AD_Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.base.Splitter;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.IADMessageDAO;
import de.metas.i18n.ILanguageBL;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.Language;
import de.metas.i18n.po.POTrlRepository;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.session.UserSession;
import de.metas.util.Services;
import lombok.NonNull;
public class MessagesTreeLoader {

 private  ADMessageFilter filter;


public MessagesTreeLoader filter(ADMessageFilter filter){
    this.filter = filter;
    return this;
}


@SuppressWarnings("unchecked")
public void addMessageToTree(Map<String,Object> tree,String key,String value){
    final List<String> keyParts = Splitter.on('.').splitToList(key);
    Map<String, Object> currentNode = tree;
    for (int i = 0; i < keyParts.size() - 1; i++) {
        final String keyPart = keyParts.get(i);
        final Object currentNodeObj = currentNode.get(keyPart);
        if (currentNodeObj == null) {
            final Map<String, Object> parentNode = currentNode;
            currentNode = new LinkedHashMap<>();
            parentNode.put(keyPart, currentNode);
        } else if (currentNodeObj instanceof Map) {
            currentNode = (Map<String, Object>) currentNodeObj;
        } else {
            // discarding the old value, shall not happen
            final Map<String, Object> parentNode = currentNode;
            currentNode = new LinkedHashMap<>();
            parentNode.put(keyPart, currentNode);
        }
    }
    final String keyPart = keyParts.get(keyParts.size() - 1);
    currentNode.put(keyPart, value);
}


public Map<String,Object> load(String adLanguage){
    final LinkedHashMap<String, Object> tree = new LinkedHashMap<>();
    tree.put("_language", adLanguage);
    loadInto(tree, adLanguage, adMessageKey -> adMessageKey);
    return tree;
}


public Map<String,Object> loadAllLanguages(){
    final Set<String> adLanguages = Services.get(ILanguageBL.class).getAvailableLanguages().getAD_Languages();
    final LinkedHashMap<String, Object> tree = new LinkedHashMap<>();
    tree.put("_baseLanguage", Language.getBaseAD_Language());
    for (final String adLanguage : adLanguages) {
        loadInto(tree, adLanguage, adMessageKey -> adMessageKey + "." + adLanguage);
    }
    return tree;
}


public MessagesTreeLoader newInstance(){
    return new MessagesTreeLoader();
}


public void loadInto(Map<String,Object> tree,String adLanguage,UnaryOperator<String> keyMapper){
    final IMsgBL msgBL = Services.get(IMsgBL.class);
    msgBL.getMsgMap(adLanguage, AD_MESSAGE_PREFIX, true).forEach((adMessageKey, msgText) -> {
        if (filter == null || filter.acceptMessageKey(adMessageKey)) {
            addMessageToTree(tree, keyMapper.apply(adMessageKey), msgText);
        }
    });
}


}