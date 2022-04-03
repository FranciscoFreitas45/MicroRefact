package de.metas.ui.web.window.datatypes.json;
 import java.time.Duration;
import java.util.Collection;
import java.util.List;
import org.compiere.util.TimeUtil;
import org.slf4j.Logger;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import de.metas.logging.LogManager;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.json.JSONDocumentFilter;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.model.DocumentReference;
import lombok.NonNull;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONDocumentReference {

 private  Logger logger;

@JsonProperty("id")
 private  String id;

@JsonProperty("internalName")
 private  String internalName;

@JsonProperty("caption")
 private  String caption;

@JsonProperty("documentType")
 private  WindowId windowId;

@JsonProperty("documentsCount")
 private  int documentsCount;

@JsonProperty("filter")
 private  JSONDocumentFilter filter;

@JsonProperty("loadDuration")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String loadDurationStr;


public String formatDuration(Duration loadDuration){
    return TimeUtil.formatElapsed(loadDuration);
}


public JSONDocumentReference of(DocumentReference documentReference,JSONOptions jsonOpts){
    try {
        return new JSONDocumentReference(documentReference, jsonOpts);
    } catch (Exception ex) {
        logger.warn("Failed convering {} to {}. Skipped", documentReference, JSONDocumentReference.class, ex);
        return null;
    }
}


public List<JSONDocumentReference> ofList(Collection<DocumentReference> documentReferences,JSONOptions jsonOpts){
    if (documentReferences.isEmpty()) {
        return ImmutableList.of();
    }
    return documentReferences.stream().map(documentReference -> of(documentReference, jsonOpts)).filter(jsonDocumentReference -> jsonDocumentReference != null).collect(ImmutableList.toImmutableList());
}


}