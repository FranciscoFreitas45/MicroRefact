package de.metas.ui.web.process.view.ViewActionDescriptorsFactory;
 import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.adempiere.util.lang.MutableInt;
import org.compiere.Adempiere;
import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import de.metas.cache.CCache;
import de.metas.i18n.IMsgBL;
import de.metas.logging.LogManager;
import de.metas.ui.web.process.ProcessInstanceResult;
import de.metas.ui.web.process.view.ViewAction.AlwaysAllowPrecondition;
import de.metas.ui.web.process.view.ViewActionDescriptor.ViewActionDescriptorBuilder;
import de.metas.ui.web.process.view.ViewActionDescriptor.ViewActionMethodArgumentExtractor;
import de.metas.ui.web.process.view.ViewActionDescriptor.ViewActionMethodReturnTypeConverter;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.Services;
import lombok.NonNull;
public class ActionIdGenerator {

 private  Map<String,MutableInt> methodName2counter;


public String getActionId(Method actionMethod){
    final String methodName = actionMethod.getName();
    final MutableInt counter = methodName2counter.computeIfAbsent(methodName, k -> new MutableInt(0));
    final int methodNameSuffix = counter.incrementAndGet();
    if (methodNameSuffix == 1) {
        return methodName;
    } else if (methodNameSuffix > 1) {
        return methodName + methodNameSuffix;
    } else {
        // shall NEVER happen
        throw new IllegalStateException("internal error: methodNameSuffix <= 0");
    }
}


}