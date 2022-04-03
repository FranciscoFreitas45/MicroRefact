package de.metas.ui.web.window.descriptor.factory.standard;
 import java.util.Optional;
import java.util.Set;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DisplayType;
import org.slf4j.Logger;
import com.google.common.collect.ImmutableSet;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.exceptions.DocumentLayoutBuildException;
import de.metas.util.Check;
import lombok.NonNull;
public class DescriptorsFactoryHelper {

 private  Logger logger;

 private  Set<String> COLUMNNAMES_Switch;


public Class<?> getValueClass(DocumentFieldWidgetType widgetType,Optional<LookupDescriptor> lookupDescriptor){
    final Class<?> widgetValueClass = widgetType.getValueClassOrNull();
    // 
    // Try fetching the valueClass from lookup
    if (lookupDescriptor.isPresent()) {
        final Class<?> lookupValueClass = lookupDescriptor.get().getValueClass();
        // shall not happen
        Check.assumeNotNull(lookupValueClass, "Parameter lookupValueClass is not null for {}", lookupDescriptor);
        if (widgetValueClass == null) {
            return lookupValueClass;
        } else if (lookupValueClass.equals(widgetValueClass)) {
            return lookupValueClass;
        } else if (LookupValue.class.isAssignableFrom(lookupValueClass) && LookupValuesList.class.equals(widgetValueClass)) {
            return LookupValuesList.class;
        } else {
            throw new AdempiereException("WidgetType's class is not compatible with LookupDescriptor's class" + "\n WidgetType: " + widgetType + "\n WidgetType value class: " + widgetValueClass + "\n LookupDescriptor: " + lookupDescriptor + "\n LookupDescriptor value class: " + lookupValueClass);
        }
    }
    // 
    // Use the standard widget's valueClass, if any
    if (widgetValueClass != null) {
        return widgetValueClass;
    }
    // 
    // HARDCODED case: if Button, return String
    if (widgetType == DocumentFieldWidgetType.Button) {
        return String.class;
    }
    // 
    // Fallback
    throw new IllegalArgumentException("No value class found for widgetType=" + widgetType);
}


public DocumentLayoutElementFieldDescriptor.LookupSource extractLookupSource(int displayType,int adReferenceValueId){
    if (DisplayType.Search == displayType) {
        return DocumentLayoutElementFieldDescriptor.LookupSource.lookup;
    } else if (DisplayType.List == displayType) {
        return DocumentLayoutElementFieldDescriptor.LookupSource.list;
    } else if (DisplayType.TableDir == displayType) {
        return DocumentLayoutElementFieldDescriptor.LookupSource.list;
    } else if (DisplayType.Table == displayType) {
        return DocumentLayoutElementFieldDescriptor.LookupSource.list;
    } else if (DisplayType.isAnyLookup(displayType)) {
        return DocumentLayoutElementFieldDescriptor.LookupSource.lookup;
    } else if (DisplayType.Button == displayType && adReferenceValueId > 0) {
        return DocumentLayoutElementFieldDescriptor.LookupSource.list;
    } else {
        return null;
    }
}


public DocumentFieldWidgetType extractWidgetType(DocumentLayoutElementFieldDescriptor.LookupSource lookupSource){
    Check.assumeNotNull(lookupSource, "Parameter lookupSource is not null");
    switch(lookupSource) {
        case list:
            return DocumentFieldWidgetType.List;
        case lookup:
            return DocumentFieldWidgetType.Lookup;
        default:
            throw new AdempiereException("LookupSource " + lookupSource + " is not supported");
    }
}


}