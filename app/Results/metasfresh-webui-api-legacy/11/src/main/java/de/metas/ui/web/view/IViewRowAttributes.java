package de.metas.ui.web.view;
 import java.util.List;
import de.metas.ui.web.view.descriptor.ViewRowAttributesLayout;
import de.metas.ui.web.view.json.JSONViewRowAttributes;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
public interface IViewRowAttributes {


public ViewRowAttributesLayout getLayout()
;

public JSONViewRowAttributes toJson(JSONOptions jsonOpts)
;

public LookupValuesList getAttributeTypeahead(String attributeName,String query)
;

public DocumentPath getDocumentPath()
;

public LookupValuesList getAttributeDropdown(String attributeName)
;

public void processChanges(List<JSONDocumentChangedEvent> events)
;

}