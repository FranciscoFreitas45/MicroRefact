package de.metas.ui.web.quickinput;
 import org.adempiere.exceptions.AdempiereException;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
@Value(staticConstructor = "of")
public class QuickInputDescriptor {

@NonNull
 private  DocumentEntityDescriptor entityDescriptor;

@NonNull
 private  QuickInputLayoutDescriptor layout;

@NonNull
@Getter(AccessLevel.NONE)
 private  Class<? extends IQuickInputProcessor> processorClass;


public IQuickInputProcessor createProcessor(){
    try {
        return processorClass.newInstance();
    } catch (final Exception ex) {
        throw new AdempiereException("Failed instantiating " + processorClass, ex);
    }
}


public DetailId getDetailId(){
    return getEntityDescriptor().getDetailId();
}


}