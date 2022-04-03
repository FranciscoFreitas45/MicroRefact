package sn.security;
 import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
public class JwtTokenAuthFilter extends OncePerRequestFilter{

 private  JwtConfig jwtConfig;

public JwtTokenAuthFilter(JwtConfig jwtConfig) {
    this.jwtConfig = jwtConfig;
}
@Override
public void doFilterInternal(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,FilterChain filterChain){
    String header = httpServletRequest.getHeader(jwtConfig.getHeader());
    // Если не валидный - переходим к другому фильтру
    if (header == null || !header.startsWith(jwtConfig.getPrefix())) {
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        return;
    }
    String token = header.replace(jwtConfig.getPrefix(), "");
    try {
        // исключение может быть брошено, если, например, время действия токена истекло
        // 4. Проверяем токен
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(jwtConfig.getSecret().getBytes()).build().parseClaimsJws(token);
        String username = claims.getBody().getSubject();
        if (username != null) {
            @SuppressWarnings("unchecked")
            List<String> authorities = (List<String>) claims.getBody().get("authorities");
            // 5. Создаем авторизированный объект
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
            // 6. Аутентифицируем пользователя
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    } catch (Exception e) {
        SecurityContextHolder.clearContext();
        e.printStackTrace();
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
}


}