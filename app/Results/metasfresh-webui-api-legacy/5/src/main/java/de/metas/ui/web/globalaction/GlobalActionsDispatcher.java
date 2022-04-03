package de.metas.ui.web.globalaction;
 import java.util.List;
import java.util.Optional;
import org.adempiere.exceptions.AdempiereException;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import de.metas.logging.LogManager;
import lombok.NonNull;
@Component
public class GlobalActionsDispatcher {

 private  Logger logger;

 private  ImmutableMap<GlobalActionType,GlobalActionHandler> handlers;


public GlobalActionHandlerResult dispatchEvent(GlobalActionEvent event){
    return getHandler(event.getType()).handleEvent(event);
}


public GlobalActionHandler getHandler(GlobalActionType type){
    final GlobalActionHandler handler = handlers.get(type);
    if (handler == null) {
        throw new AdempiereException("No handler found for " + type);
    }
    return handler;
}


}