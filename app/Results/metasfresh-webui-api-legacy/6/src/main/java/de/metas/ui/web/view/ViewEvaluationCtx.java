package de.metas.ui.web.view;
 import java.time.ZoneId;
import java.util.Optional;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluatees;
import de.metas.security.UserRolePermissionsKey;
import de.metas.security.impl.AccessSqlStringExpression;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.user.UserId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class ViewEvaluationCtx {

@Getter
 private  Optional<UserId> loggedUserId;

@Getter
 private  String adLanguage;

@Getter
 private  ZoneId timeZone;

@Getter
 private  UserRolePermissionsKey permissionsKey;

 private  Evaluatee _evaluatee;

 private  JSONOptions _jsonOptions;


public JSONOptions toJSONOptions(){
    JSONOptions jsonOptions = this._jsonOptions;
    if (jsonOptions == null) {
        jsonOptions = _jsonOptions = createJSONOptions();
    }
    return jsonOptions;
}


public JSONOptions createJSONOptions(){
    return JSONOptions.builder().adLanguage(adLanguage).zoneId(timeZone).build();
}


public ViewEvaluationCtx newInstanceFromCurrentContext(){
    final Properties ctx = Env.getCtx();
    return _builder().loggedUserId(Env.getLoggedUserIdIfExists(ctx)).adLanguage(Env.getAD_Language(ctx)).timeZone(UserSession.getTimeZoneOrSystemDefault()).permissionsKey(UserRolePermissionsKey.fromContext(ctx)).build();
}


public Evaluatee createEvaluatee(){
    return Evaluatees.mapBuilder().put(Env.CTXNAME_AD_User_ID, loggedUserId.map(UserId::getRepoId).orElse(-1)).put(Env.CTXNAME_AD_Language, adLanguage).put(AccessSqlStringExpression.PARAM_UserRolePermissionsKey.getName(), permissionsKey.toPermissionsKeyString()).build();
}


public Evaluatee toEvaluatee(){
    Evaluatee evaluatee = _evaluatee;
    if (evaluatee == null) {
        evaluatee = _evaluatee = createEvaluatee();
    }
    return evaluatee;
}


}