package de.metas.ui.web.address.AddressDescriptorFactory;
 import java.util.function.BiConsumer;
import java.util.function.Function;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.service.ISysConfigBL;
import org.compiere.model.I_C_Country;
import org.compiere.model.I_C_Location;
import org.compiere.model.I_C_Postal;
import org.compiere.model.I_C_Region;
import org.springframework.stereotype.Component;
import de.metas.cache.CCache;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.location.CountryId;
import de.metas.location.ICountryDAO;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor.DocumentEntityDataBindingDescriptorBuilder;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor;
import de.metas.ui.web.window.model.DocumentsRepository;
import de.metas.ui.web.window.model.IDocumentFieldView;
import de.metas.util.Services;
public class AddressDataBindingDescriptorBuilder implements DocumentEntityDataBindingDescriptorBuilder{

 private  DocumentEntityDataBindingDescriptor dataBinding;


@Override
public DocumentEntityDataBindingDescriptor getOrBuild(){
    return dataBinding;
}


@Override
public DocumentsRepository getDocumentsRepository(){
    throw new IllegalStateException("No repository available for " + this);
}


}