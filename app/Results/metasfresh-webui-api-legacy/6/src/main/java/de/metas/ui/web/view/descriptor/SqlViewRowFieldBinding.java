package de.metas.ui.web.view.descriptor;
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
@Value
public class SqlViewRowFieldBinding implements SqlEntityFieldBinding{

 private  String fieldName;

 private  String columnName;

 private  boolean keyColumn;

 private  DocumentFieldWidgetType widgetType;

 private  boolean virtualColumn;

 private  Class<?> sqlValueClass;

 private  SqlSelectValue sqlSelectValue;

 private  SqlSelectDisplayValue sqlSelectDisplayValue;

 private  SqlOrderByValue sqlOrderBy;

 private  SqlViewRowFieldLoader fieldLoader;


public Object retrieveValue(ResultSet rs,String adLanguage)


}