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
public interface ADMessageFilter {


public boolean acceptMessageKey(String adMessageKey)
;

}