package de.metas.ui.web.board;
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
@Builder
@Value
public class BoardCardFieldDescriptor {

@NonNull
 private  ITranslatableString caption;

@NonNull
 private  String fieldName;

@NonNull
 private  DocumentFieldWidgetType widgetType;

@NonNull
 private  ImmutableSet<String> sqlSelectValues;

 private  boolean usingDisplayColumn;

 private  SqlSelectDisplayValue sqlSelectDisplayValue;

@NonNull
 private  SqlOrderByValue sqlOrderBy;

@NonNull
 private  BoardFieldLoader fieldLoader;


public Object retrieveValueAsJson(ResultSet rs,String adLanguage)


}