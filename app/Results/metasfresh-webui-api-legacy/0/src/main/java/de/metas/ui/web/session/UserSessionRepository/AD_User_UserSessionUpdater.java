package de.metas.ui.web.session.UserSessionRepository;
 import java.util.Objects;
import org.adempiere.ad.modelvalidator.IModelInterceptorRegistry;
import org.adempiere.ad.modelvalidator.annotations.Interceptor;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.Adempiere;
import org.compiere.model.ModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import com.google.common.base.Strings;
import de.metas.adempiere.model.I_AD_User;
import de.metas.ui.web.session.json.JSONUserSessionChangesEvent;
import de.metas.ui.web.session.json.JSONUserSessionChangesEvent.JSONUserSessionChangesEventBuilder;
import de.metas.ui.web.websocket.WebSocketConfig;
import de.metas.ui.web.websocket.WebsocketSender;
import de.metas.user.UserId;
import de.metas.user.api.IUserDAO;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.AllArgsConstructor;
@Interceptor(I_AD_User.class)
@AllArgsConstructor
public class AD_User_UserSessionUpdater {

 private  UserSessionRepository userSessionRepo;


@ModelChange(timings = ModelValidator.TYPE_AFTER_CHANGE, afterCommit = true)
public void onUserChanged(I_AD_User user){
    if (!user.isSystemUser()) {
        return;
    }
    final UserId userId = UserId.ofRepoId(user.getAD_User_ID());
    final UserSession userSession = UserSession.getCurrentIfMatchingOrNull(userId);
    if (userSession == null) {
        return;
    }
    userSessionRepo.loadFromAD_User(userSession, user);
}


}