package com.meli.weather.api.exceptionhandler;
 import com.meli.weather.api.representation.ErrorResponse;
import com.meli.weather.commons.exception.NotFoundException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import javax.inject.Singleton;
@Produces
@Singleton
@Requires(classes = { NotFoundException.class, ExceptionHandler.class })
public class NotFoundExceptionHandler implements ExceptionHandler<NotFoundException, HttpResponse<?>>{


@Override
public HttpResponse<?> handle(HttpRequest request,NotFoundException exception){
    return HttpResponse.notFound(new ErrorResponse(exception.getMessage()));
}


}