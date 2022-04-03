package com.yalcin.security.jwt;
 import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.yalcin.entity.Attempt;
import com.yalcin.repository.AttemptRepository;
import com.yalcin.util.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint{

 private  Logger logger;

@Autowired
 private AttemptRepository attemptRepository;


@Override
public void commence(HttpServletRequest request,HttpServletResponse response,AuthenticationException e){
    String jwt = getJwt(request);
    Map<String, Object> myMap = new HashMap<>();
    myMap.put("timestamp", new Date());
    myMap.put("status", HttpStatus.UNAUTHORIZED.value());
    myMap.put("path", "api/auth");
    if (jwt == null) {
        if (attemptRepository.existsByIp(request.getRemoteAddr())) {
            Optional<Attempt> optionalAttempt = attemptRepository.findById(request.getRemoteAddr());
            Attempt attempt = optionalAttempt.get();
            long hour = ChronoUnit.HOURS.between(attempt.getFirst_attempt_date(), LocalDateTime.now());
            if (hour >= 24) {
                attempt.setAttemptCounter(1);
                attempt.setFirst_attempt_date(LocalDateTime.now());
            } else {
                attempt.setAttemptCounter(attempt.getAttemptCounter() + 1);
            }
            attemptRepository.save(attempt);
        } else {
            Attempt attempt = new Attempt(request.getRemoteAddr(), 1, LocalDateTime.now());
            attemptRepository.save(attempt);
        }
        myMap.put("error", "JWT cannot be empty");
        String jsonString = null;
        try {
            jsonString = JsonUtil.buildJsonString(myMap);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        response.getWriter().write(jsonString);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    } else {
        final String expired = (String) request.getAttribute("expired");
        if (expired != null) {
            myMap.put("error", "JWT Expired.");
            String jsonString = null;
            try {
                jsonString = JsonUtil.buildJsonString(myMap);
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            response.getWriter().write(jsonString);
        } else {
            myMap.put("error", "Invalid Login details");
            String jsonString = null;
            try {
                jsonString = JsonUtil.buildJsonString(myMap);
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            response.getWriter().write(jsonString);
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}


public String getJwt(HttpServletRequest request){
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        return authHeader.replace("Bearer ", "");
    }
    return null;
}


}