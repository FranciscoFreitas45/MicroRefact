package de.metas.ui.web.session.UserSession;
 import java.time.Duration;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Supplier;
import org.adempiere.service.ClientId;
import org.adempiere.service.ISysConfigBL;
import org.compiere.SpringContextHolder;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluatees;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import de.metas.i18n.Language;
import de.metas.logging.LogManager;
import de.metas.organization.IOrgDAO;
import de.metas.organization.OrgId;
import de.metas.organization.OrgInfo;
import de.metas.security.IUserRolePermissions;
import de.metas.security.RoleId;
import de.metas.security.UserRolePermissionsKey;
import de.metas.ui.web.base.session.UserPreference;
import de.metas.ui.web.exceptions.DeprecatedRestAPINotAllowedException;
import de.metas.ui.web.login.exceptions.AlreadyLoggedInException;
import de.metas.ui.web.login.exceptions.NotLoggedInAsSysAdminException;
import de.metas.ui.web.login.exceptions.NotLoggedInException;
import de.metas.ui.web.websocket.WebSocketConfig;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.user.UserId;
import de.metas.util.Check;
import de.metas.util.Services;
import de.metas.util.time.SystemTime;
import lombok.NonNull;
@lombok.Value
public class LanguagedChangedEvent {

@NonNull
 private  String adLanguage;

 private  UserId adUserId;


}