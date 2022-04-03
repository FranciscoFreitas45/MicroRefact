package de.metas.ui.web.process.view;
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
public class ViewActionDescriptorsFactory {

 public  ViewActionDescriptorsFactory instance;

 private  Logger logger;

 private  CCache<String,ViewActionDescriptorsList> viewActionsDescriptorByViewClassname;

 private  Map<String,MutableInt> methodName2counter;


public ViewActionMethodArgumentExtractor createViewActionMethodArgumentExtractor(String parameterName,Class<?> parameterType,ViewActionParam annotation){
    if (annotation != null) {
        return (view, processParameters, selectedDocumentIds) -> processParameters.getFieldView(parameterName).getValueAs(parameterType);
    } else // 
    // selectedDocumentIds internal parameter
    if (DocumentIdsSelection.class.isAssignableFrom(parameterType)) {
        return (view, processParameters, selectedDocumentIds) -> selectedDocumentIds;
    } else // 
    // View parameter
    if (IView.class.isAssignableFrom(parameterType)) {
        return (view, processParameters, selectedDocumentIds) -> view;
    } else // 
    // Primitive type => not supported
    if (parameterType.isPrimitive()) {
        throw new IllegalArgumentException("Action method's primitive parameter " + parameterType + " is not supported for parameterName: " + parameterName);
    } else // 
    // Try getting the bean from spring context
    {
        return (view, processParameters, selectedDocumentIds) -> Adempiere.getBean(parameterType);
    }
}


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


public ViewActionDescriptorsList getFromClass(Class<?> clazz){
    return viewActionsDescriptorByViewClassname.getOrLoad(clazz.getName(), () -> createFromClass(clazz));
}


public ViewActionDescriptor createViewActionDescriptor(String actionId,Method viewActionMethod){
    if (!viewActionMethod.isAccessible()) {
        viewActionMethod.setAccessible(true);
    }
    final ViewActionDescriptorBuilder actionBuilder = ViewActionDescriptor.builder().actionId(actionId).viewActionMethod(viewActionMethod);
    final ViewAction viewActionAnn = viewActionMethod.getAnnotation(ViewAction.class);
    actionBuilder.caption(Services.get(IMsgBL.class).getTranslatableMsgText(viewActionAnn.caption())).description(Services.get(IMsgBL.class).getTranslatableMsgText(viewActionAnn.description())).defaultAction(viewActionAnn.defaultAction()).layoutType(viewActionAnn.layoutType());
    // 
    // Preconditions
    final Class<? extends ViewAction.Precondition> preconditionClass = viewActionAnn.precondition();
    ViewAction.Precondition preconditionSharedInstance;
    if (AlwaysAllowPrecondition.class.equals(preconditionClass)) {
        preconditionSharedInstance = AlwaysAllowPrecondition.instance;
    } else {
        preconditionSharedInstance = null;
    }
    actionBuilder.preconditionClass(preconditionClass).preconditionSharedInstance(preconditionSharedInstance);
    // 
    // View action method's return type
    actionBuilder.viewActionReturnTypeConverter(createReturnTypeConverter(viewActionMethod));
    // 
    // View action method's parameters
    {
        final Class<?>[] methodParameterTypes = viewActionMethod.getParameterTypes();
        final Annotation[][] methodParameterAnnotations = viewActionMethod.getParameterAnnotations();
        for (int parameterIndex = 0; parameterIndex < methodParameterTypes.length; parameterIndex++) {
            final String parameterName = String.valueOf(parameterIndex);
            final Class<?> methodParameterType = methodParameterTypes[parameterIndex];
            final ViewActionParam methodParameterAnnotation = Stream.of(methodParameterAnnotations[parameterIndex]).filter(ann -> ann instanceof ViewActionParam).map(ann -> (ViewActionParam) ann).findFirst().orElse(null);
            final ViewActionParamDescriptor paramDescriptor = ViewActionParamDescriptor.builder().parameterName(parameterName).parameterValueClass(methodParameterType).parameterAnnotation(methodParameterAnnotation).methodArgumentExtractor(createViewActionMethodArgumentExtractor(parameterName, methodParameterType, methodParameterAnnotation)).build();
            actionBuilder.viewActionParamDescriptor(paramDescriptor);
        }
    }
    return actionBuilder.build();
}


public ViewActionMethodReturnTypeConverter createReturnTypeConverter(Method method){
    final Class<?> viewActionReturnType = method.getReturnType();
    if (Void.class.equals(viewActionReturnType) || void.class.equals(viewActionReturnType)) {
        return returnValue -> null;
    } else if (ProcessInstanceResult.ResultAction.class.isAssignableFrom(viewActionReturnType)) {
        return returnType -> (ProcessInstanceResult.ResultAction) returnType;
    } else {
        throw new IllegalArgumentException("Action method's return type is not supported: " + method);
    }
}


public ViewActionDescriptorsList createFromClass(Class<?> clazz){
    final ActionIdGenerator actionIdGenerator = new ActionIdGenerator();
    @SuppressWarnings("unchecked")
    final Set<Method> viewActionMethods = ReflectionUtils.getAllMethods(clazz, ReflectionUtils.withAnnotation(ViewAction.class));
    final List<ViewActionDescriptor> viewActions = viewActionMethods.stream().map(viewActionMethod -> {
        try {
            return createViewActionDescriptor(actionIdGenerator.getActionId(viewActionMethod), viewActionMethod);
        } catch (final Throwable ex) {
            logger.warn("Failed creating view action descriptor for {}. Ignored", viewActionMethod, ex);
            return null;
        }
    }).filter(actionDescriptor -> actionDescriptor != null).collect(Collectors.toList());
    return ViewActionDescriptorsList.of(viewActions);
}


}