package de.metas.ui.web.view.descriptor.SqlViewRowFieldBinding;
 import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Nullable;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.sql.SqlEntityFieldBinding;
import de.metas.ui.web.window.descriptor.sql.SqlOrderByValue;
import de.metas.ui.web.window.descriptor.sql.SqlSelectDisplayValue;
import de.metas.ui.web.window.descriptor.sql.SqlSelectValue;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@FunctionalInterface
public interface SqlViewRowFieldLoader {


public Object retrieveValue(ResultSet rs,String adLanguage)
;

}