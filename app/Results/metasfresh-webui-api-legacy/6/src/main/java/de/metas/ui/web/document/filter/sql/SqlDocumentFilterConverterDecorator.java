package de.metas.ui.web.document.filter.sql;
 import org.springframework.stereotype.Component;
import de.metas.ui.web.view.SqlViewFactory;
import de.metas.ui.web.window.datatypes.WindowId;
public interface SqlDocumentFilterConverterDecorator {


public SqlDocumentFilterConverter decorate(SqlDocumentFilterConverter converter)
;

public WindowId getWindowId()
;

}