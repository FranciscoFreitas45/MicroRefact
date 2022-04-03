package de.metas.ui.web.process.view;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.process.ViewAsPreconditionsContext;
import de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
import lombok.ToString;
@ToString
public class ViewActionDescriptorsList {

 public  ViewActionDescriptorsList EMPTY;

 private  ImmutableMap<String,ViewActionDescriptor> viewActionsByActionId;


public Stream<WebuiRelatedProcessDescriptor> streamDocumentRelatedProcesses(ViewAsPreconditionsContext viewContext){
    return viewActionsByActionId.values().stream().map(viewActionDescriptor -> viewActionDescriptor.toWebuiRelatedProcessDescriptor(viewContext));
}


public ViewActionDescriptorsList mergeWith(ViewActionDescriptorsList actionsToAdd){
    if (actionsToAdd == null || actionsToAdd.viewActionsByActionId.isEmpty()) {
        return this;
    }
    if (this.viewActionsByActionId.isEmpty()) {
        return actionsToAdd;
    }
    final Map<String, ViewActionDescriptor> newViewActionsByActionId = new HashMap<>(this.viewActionsByActionId);
    newViewActionsByActionId.putAll(actionsToAdd.viewActionsByActionId);
    return new ViewActionDescriptorsList(ImmutableMap.copyOf(newViewActionsByActionId));
}


public ViewActionDescriptorsList of(List<ViewActionDescriptor> actions){
    if (actions.isEmpty()) {
        return ViewActionDescriptorsList.EMPTY;
    }
    final ImmutableMap<String, ViewActionDescriptor> viewActionsByActionId = Maps.uniqueIndex(actions, ViewActionDescriptor::getActionId);
    return new ViewActionDescriptorsList(viewActionsByActionId);
}


public ViewActionDescriptor getAction(String actionId){
    final ViewActionDescriptor action = viewActionsByActionId.get(actionId);
    if (action == null) {
        throw new EntityNotFoundException("No view action found for id: " + actionId);
    }
    return action;
}


}