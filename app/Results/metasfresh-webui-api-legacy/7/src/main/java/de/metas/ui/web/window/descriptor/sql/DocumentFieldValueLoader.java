package de.metas.ui.web.window.descriptor.sql;
 import java.sql.ResultSet;
import java.sql.SQLException;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
@FunctionalInterface
public interface DocumentFieldValueLoader {


public Object retrieveFieldValue(ResultSet rs,boolean isDisplayColumnAvailable,String adLanguage,LookupDescriptor lookupDescriptor)
;

}