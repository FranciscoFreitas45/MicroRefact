package DTO;
 import java.sql.ResultSet;
import java.sql.SQLException;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
public interface DocumentFieldValueLoader {


public Object retrieveFieldValue(ResultSet rs,boolean isDisplayColumnAvailable,String adLanguage,LookupDescriptor lookupDescriptor)
;

}