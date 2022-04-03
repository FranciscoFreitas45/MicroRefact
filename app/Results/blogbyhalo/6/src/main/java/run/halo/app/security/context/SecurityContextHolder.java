package run.halo.app.security.context;
 import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
public class SecurityContextHolder {

 private  ThreadLocal<SecurityContext> CONTEXT_HOLDER;

private SecurityContextHolder() {
}
public void setContext(SecurityContext context){
    CONTEXT_HOLDER.set(context);
}


public void clearContext(){
    CONTEXT_HOLDER.remove();
}


@NonNull
public SecurityContext getContext(){
    // Get from thread local
    SecurityContext context = CONTEXT_HOLDER.get();
    if (context == null) {
        // If no context is available now then create an empty context
        context = createEmptyContext();
        // Set to thread local
        CONTEXT_HOLDER.set(context);
    }
    return context;
}


@NonNull
public SecurityContext createEmptyContext(){
    return new SecurityContextImpl(null);
}


}