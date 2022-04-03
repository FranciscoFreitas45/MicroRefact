package de.metas.ui.web.globalaction;
 public interface GlobalActionHandler {


public GlobalActionType getTypeHandled()
;

public GlobalActionHandlerResult handleEvent(GlobalActionEvent event)
;

}