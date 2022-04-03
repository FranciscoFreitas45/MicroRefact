package de.metas.ui.web.menu.datatypes.json;
 import java.util.List;
import java.util.Set;
import org.adempiere.exceptions.AdempiereException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent.JSONOperation;
public class JSONPatchMenuNodeRequest {

 private  String PATH_Favorite;

 private  Set<String> PATHS;

@JsonProperty("favorite")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean favorite;


public JSONPatchMenuNodeRequest ofChangeEvents(List<JSONDocumentChangedEvent> events){
    if (events == null || events.isEmpty()) {
        throw new AdempiereException("No events");
    }
    Boolean favorite = null;
    if (events != null && !events.isEmpty()) {
        for (final JSONDocumentChangedEvent event : events) {
            if (!event.isReplace()) {
                throw new AdempiereException("Only " + JSONOperation.replace + " are supported").setParameter("event", event);
            }
            if (PATH_Favorite.equals(event.getPath())) {
                favorite = event.getValueAsBoolean(null);
                if (favorite == null) {
                    throw new AdempiereException("Invalid value for " + PATH_Favorite).setParameter("event", event);
                }
            } else {
                throw new AdempiereException("Unknown path: " + event.getPath()).setParameter("event", event).setParameter("availablePaths", PATHS);
            }
        }
    }
    // Make sure we have at least on actual change
    if (favorite == null) {
        throw new AdempiereException("None of the requested changes are supported").setParameter("events", events);
    }
    return new JSONPatchMenuNodeRequest(favorite);
}


public Boolean getFavorite(){
    return favorite;
}


}