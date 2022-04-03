package de.metas.ui.web.board.BoardCardFieldDescriptor;
 import java.sql.ResultSet;
import java.sql.SQLException;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.sql.SqlOrderByValue;
import de.metas.ui.web.window.descriptor.sql.SqlSelectDisplayValue;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@FunctionalInterface
public interface BoardFieldLoader {


public Object retrieveValueAsJson(ResultSet rs,String adLanguage)
;

}