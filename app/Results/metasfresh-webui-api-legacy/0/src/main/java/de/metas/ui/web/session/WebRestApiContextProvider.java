package de.metas.ui.web.session;
 import java.io.Serializable;
import java.util.Properties;
import org.adempiere.context.ContextProvider;
import org.adempiere.util.lang.IAutoCloseable;
import org.adempiere.util.lang.NullAutoCloseable;
import org.compiere.util.Env;
import org.slf4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import de.metas.Profiles;
import de.metas.logging.LogManager;
import de.metas.util.AbstractPropertiesProxy;
import lombok.NonNull;
@SuppressWarnings("serial")
@Component
@Profile(Profiles.PROFILE_Webui)
public class WebRestApiContextProvider implements Serializable,ContextProvider{

 public  String CTXNAME_IsServerContext;

 public  String CTXNAME_IsWebUI;

 private  Logger logger;

 private  InheritableThreadLocal<Properties> temporaryCtxHolder;

 private  long serialVersionUID;

 private  AbstractPropertiesProxy ctxProxy;

 private  Properties serverCtx;

 private  boolean closed;


@Override
public void init(){
// nothing to do here
}


@Override
public void reset(){
    temporaryCtxHolder.remove();
    serverCtx.clear();
    logger.debug("Reset done");
}


@Override
public Properties getDelegate(){
    return getActualContext();
}


@Override
public Properties getContext(){
    return ctxProxy;
}


public Properties getActualContext(){
    // 
    // IMPORTANT: this method will be called very often, so please make sure it's FAST!
    // 
    // 
    // If there is currently a temporary context active, return it first
    final Properties temporaryCtx = temporaryCtxHolder.get();
    if (temporaryCtx != null) {
        logger.trace("Returning temporary context: {}", temporaryCtx);
        return temporaryCtx;
    }
    // 
    // Get the context from current session
    final UserSession userSession = UserSession.getCurrentOrNull();
    if (userSession != null) {
        final Properties userSessionCtx = userSession.getCtx();
        logger.trace("Returning user session context: {}", userSessionCtx);
        return userSessionCtx;
    }
    // 
    // If there was no current session it means we are running on server side, so return the server context
    logger.trace("Returning server context: {}", serverCtx);
    return serverCtx;
}


@Override
public void close(){
    if (closed) {
        return;
    }
    if (previousTempCtx != null) {
        temporaryCtxHolder.set(previousTempCtx);
    } else {
        temporaryCtxHolder.remove();
    }
    closed = true;
    logger.trace("Switched back from temporary context");
}


@Override
public IAutoCloseable switchContext(Properties ctx){
    // If we were asked to set the context proxy (the one which we are returning everytime),
    // then it's better to do nothing because this could end in a StackOverflowException.
    if (ctx == ctxProxy) {
        logger.trace("Not switching context because the given temporary context it's actually our context proxy: {}", ctx);
        return NullAutoCloseable.instance;
    }
    final Properties previousTempCtx = temporaryCtxHolder.get();
    temporaryCtxHolder.set(ctx);
    logger.trace("Switched to temporary context. \n New temporary context: {} \n Previous temporary context: {}", ctx, previousTempCtx);
    return new IAutoCloseable() {

        private boolean closed = false;

        @Override
        public void close() {
            if (closed) {
                return;
            }
            if (previousTempCtx != null) {
                temporaryCtxHolder.set(previousTempCtx);
            } else {
                temporaryCtxHolder.remove();
            }
            closed = true;
            logger.trace("Switched back from temporary context");
        }
    };
}


}