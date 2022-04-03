package de.metas.ui.web.view;
 import de.metas.ui.web.window.datatypes.DocumentId;
public interface IViewRowAttributesProvider {


public IViewRowAttributes getAttributes(DocumentId rowId,DocumentId attributesKey)
;

public void invalidateAll()
;

}