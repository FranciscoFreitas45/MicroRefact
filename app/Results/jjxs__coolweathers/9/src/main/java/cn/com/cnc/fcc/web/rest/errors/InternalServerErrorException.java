package cn.com.cnc.fcc.web.rest.errors;
 import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
public class InternalServerErrorException extends AbstractThrowableProblem{

 private  long serialVersionUID;

public InternalServerErrorException(String message) {
    super(ErrorConstants.DEFAULT_TYPE, message, Status.INTERNAL_SERVER_ERROR);
}
}