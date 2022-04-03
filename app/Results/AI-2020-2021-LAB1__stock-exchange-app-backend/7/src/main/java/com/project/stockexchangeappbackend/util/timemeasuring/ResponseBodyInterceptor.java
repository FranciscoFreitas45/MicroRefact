package com.project.stockexchangeappbackend.util.timemeasuring;
 import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@AllArgsConstructor
public class ResponseBodyInterceptor extends HandlerInterceptorAdapter{

 private  String DB_QUERY_EXECUTION_TIME_HEADER;

 private  String BUSINESS_LOGIN_EXECUTION_TIME_HEADER;

 private  ProcessingTime processingTime;


@Override
public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView){
    response.addHeader(DB_QUERY_EXECUTION_TIME_HEADER, processingTime.getDatabaseOperationExecutionTime().toString());
    response.addHeader(BUSINESS_LOGIN_EXECUTION_TIME_HEADER, processingTime.getBusinessLogicExecutionTime().toString());
}


}