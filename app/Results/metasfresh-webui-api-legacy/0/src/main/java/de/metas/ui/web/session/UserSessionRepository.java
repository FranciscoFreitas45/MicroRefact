package de.metas.ui.web.session;
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
@Component
// NOTE: we need Adempiere as parameter to make sure it was initialized. Else the "addModelInterceptor" will fail.
@DependsOn(Adempiere.BEAN_NAME)
public class UserSessionRepository {

@Autowired
 private  WebsocketSender websocketSender;

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


public void load(UserSession userSession){
    final I_AD_User fromUser = Services.get(IUserDAO.class).getById(userSession.getLoggedUserId());
    loadFromAD_User(userSession, fromUser);
}


public String buildUserFullname(I_AD_User user){
    final StringBuilder fullname = new StringBuilder();
    final String firstname = user.getFirstname();
    if (!Check.isEmpty(firstname, true)) {
        fullname.append(firstname.trim());
    }
    final String lastname = user.getLastname();
    if (!Check.isEmpty(lastname, true)) {
        if (fullname.length() > 0) {
            fullname.append(" ");
        }
        fullname.append(lastname.trim());
    }
    if (fullname.length() <= 0) {
        final String login = user.getLogin();
        if (// shall not happen to be empty
        !Check.isEmpty(login, true)) {
            fullname.append(login.trim());
        }
    }
    if (fullname.length() <= 0) {
        fullname.append(user.getAD_User_ID());
    }
    return fullname.toString();
}


public void setAD_Language(UserId adUserId,String adLanguage){
    final I_AD_User user = Services.get(IUserDAO.class).getByIdInTrx(adUserId, I_AD_User.class);
    user.setAD_Language(adLanguage);
    InterfaceWrapperHelper.save(user);
}


public void loadFromAD_User(UserSession userSession,I_AD_User fromUser){
    final JSONUserSessionChangesEventBuilder changesCollector = JSONUserSessionChangesEvent.builder();
    // Fullname
    final String userFullnameOld = userSession.setUserFullname(buildUserFullname(fromUser));
    final String userFullnameNew = userSession.getUserFullname();
    if (!Objects.equals(userFullnameNew, userFullnameOld)) {
        changesCollector.fullname(userFullnameNew);
    }
    // EMail
    final String userEmailOld = userSession.setUserEmail(fromUser.getEMail());
    final String userEmailNew = userSession.getUserEmail();
    if (!Objects.equals(userEmailNew, userEmailOld)) {
        changesCollector.email(userEmailNew);
    }
    // Avatar
    {
        final int avatarIdInt = fromUser.getAvatar_ID();
        final String avatarIdOld = userSession.setAvatarId(avatarIdInt > 0 ? String.valueOf(avatarIdInt) : null);
        final String avatarIdNew = userSession.getAvatarId();
        if (!Objects.equals(avatarIdNew, avatarIdOld)) {
            // IMPORTANT: convert the null to empty string to make sure the "avatarId" is communicated to frontend.
            changesCollector.avatarId(Strings.nullToEmpty(avatarIdNew));
        }
    }
    // Language
    final String adLanguageToSet = fromUser.getAD_Language();
    if (!Check.isEmpty(adLanguageToSet)) {
        String adLanguageOld = userSession.setAD_Language(adLanguageToSet);
        String adLanguageNew = userSession.getAD_Language();
        if (!Objects.equals(adLanguageNew, adLanguageOld)) {
            changesCollector.language(userSession.getLanguageAsJson());
        }
    }
    // 
    // Fire user session changed websocket event
    final JSONUserSessionChangesEvent changesEvent = changesCollector.build();
    if (!changesEvent.isEmpty()) {
        final String websocketEndpoint = WebSocketConfig.buildUserSessionTopicName(userSession.getLoggedUserId());
        websocketSender.convertAndSend(websocketEndpoint, changesEvent);
    }
}


}