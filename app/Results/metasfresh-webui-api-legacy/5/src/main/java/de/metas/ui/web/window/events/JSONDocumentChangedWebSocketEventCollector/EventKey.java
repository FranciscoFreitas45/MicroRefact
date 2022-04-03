package de.metas.ui.web.window.events.JSONDocumentChangedWebSocketEventCollector;
 import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONIncludedTabInfo;
import de.metas.ui.web.window.descriptor.DetailId;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;
@Value
public class EventKey {

 private WindowId windowId;

 private DocumentId documentId;


}