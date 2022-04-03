package de.metas.ui.web.globalaction;
 import org.springframework.stereotype.Component;
@Component
public class OpenViewGlobalActionHandler implements GlobalActionHandler{


@Override
public GlobalActionType getTypeHandled(){
    return GlobalActionType.OPEN_VIEW;
}


@Override
public GlobalActionHandlerResult handleEvent(GlobalActionEvent event){
    return OpenViewGlobalActionHandlerResult.ofPayload(event.getPayload());
}


}