package de.metas.ui.web.document.filter.provider.userQuery;
 import java.util.Optional;
import org.compiere.apps.search.IUserQueryField;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;
@Value
@Builder
@ToString(of = "columnName")
public class UserQueryField implements IUserQueryField{

 private  String columnName;

 private  ITranslatableString displayName;

 private  DocumentFieldWidgetType widgetType;

 private  Optional<LookupDescriptor> lookupDescriptor;


@Override
public String getColumnSQL(){
    // shall not be needed
    return null;
}


public UserQueryField cast(IUserQueryField userQueryField){
    return (UserQueryField) userQueryField;
}


@Override
public String getValueDisplay(Object value){
    // not needed
    return null;
}


@Override
public Object convertValueToFieldType(Object valueObj){
    return valueObj;
}


}