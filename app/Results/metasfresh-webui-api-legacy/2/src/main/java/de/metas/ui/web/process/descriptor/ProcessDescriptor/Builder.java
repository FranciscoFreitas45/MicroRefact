package de.metas.ui.web.process.descriptor.ProcessDescriptor;
 import java.util.Optional;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.ProcessPreconditionChecker;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.security.IUserRolePermissions;
import de.metas.ui.web.cache.ETag;
import de.metas.ui.web.cache.ETagAware;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.util.Check;
import lombok.Getter;
import lombok.NonNull;
public class Builder {

 private  ProcessDescriptorType type;

 private  ProcessId processId;

@Getter
 private  InternalName internalName;

 private  String processClassname;

 private  Optional<Class<?>> processClass;

 private  DocumentEntityDescriptor parametersDescriptor;

 private  ProcessLayout layout;

 private  Boolean startProcessDirectly;


public Builder setParametersDescriptor(DocumentEntityDescriptor parametersDescriptor){
    this.parametersDescriptor = parametersDescriptor;
    return this;
}


@Nullable
public Class<?> getProcessClassOrNull(){
    return processClass.orElse(null);
}


public String getProcessClassname(){
    return processClassname;
}


public DocumentEntityDescriptor getParametersDescriptor(){
    return parametersDescriptor;
}


public Builder setProcessId(ProcessId processId){
    this.processId = processId;
    return this;
}


public Builder setLayout(ProcessLayout layout){
    this.layout = layout;
    return this;
}


public Builder setType(ProcessDescriptorType type){
    this.type = type;
    return this;
}


public boolean computeIsStartProcessDirectly(){
    return (getParametersDescriptor() == null || getParametersDescriptor().getFields().isEmpty()) && TranslatableStrings.isEmpty(getLayout().getDescription());
}


public ProcessLayout getLayout(){
    Check.assumeNotNull(layout, "Parameter layout is not null");
    return layout;
}


public ProcessDescriptor build(){
    return new ProcessDescriptor(this);
}


public ProcessDescriptorType getType(){
    Check.assumeNotNull(type, "Parameter type is not null");
    return type;
}


public ProcessId getProcessId(){
    Check.assumeNotNull(processId, "Parameter processId is not null");
    return processId;
}


public Builder setStartProcessDirectly(boolean startProcessDirectly){
    this.startProcessDirectly = startProcessDirectly;
    return this;
}


public Builder setProcessClassname(String processClassname){
    this.processClassname = processClassname;
    processClass = loadProcessClass(processClassname);
    return this;
}


public Optional<Class<?>> loadProcessClass(String classname){
    if (Check.isEmpty(classname, true)) {
        return Optional.empty();
    }
    final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    try {
        final Class<?> processClass = classLoader.loadClass(classname);
        return Optional.of(processClass);
    } catch (final ClassNotFoundException e) {
        logger.error("Cannot process class: {}", classname, e);
        return Optional.empty();
    }
}


public Builder setInternalName(InternalName internalName){
    this.internalName = internalName;
    return this;
}


public boolean isStartProcessDirectly(){
    if (startProcessDirectly != null) {
        return startProcessDirectly;
    } else {
        return computeIsStartProcessDirectly();
    }
}


@Nullable
public Class<? extends IProcessDefaultParametersProvider> getProcessDefaultParametersProvider(){
    final Class<?> processClass = getProcessClassOrNull();
    if (processClass == null || !IProcessDefaultParametersProvider.class.isAssignableFrom(processClass)) {
        return null;
    }
    try {
        return processClass.asSubclass(IProcessDefaultParametersProvider.class);
    } catch (final Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        return null;
    }
}


}