package run.halo.app.utils;
 import cn.hutool.extra.servlet.ServletUtil;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
public class ServletUtils {

private ServletUtils() {
}
@Nullable
public String getHeaderIgnoreCase(String header){
    return getCurrentRequest().map(request -> ServletUtil.getHeaderIgnoreCase(request, header)).orElse(null);
}


@Nullable
public String getRequestIp(){
    return getCurrentRequest().map(ServletUtil::getClientIP).orElse(null);
}


@NonNull
public Optional<HttpServletRequest> getCurrentRequest(){
    return Optional.ofNullable(RequestContextHolder.getRequestAttributes()).filter(requestAttributes -> requestAttributes instanceof ServletRequestAttributes).map(requestAttributes -> (ServletRequestAttributes) requestAttributes).map(ServletRequestAttributes::getRequest);
}


}