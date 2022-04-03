package run.halo.app.utils;
 import java.io.PrintWriter;
import java.io.StringWriter;
public class ExceptionUtils {


public String getStackTrace(Throwable throwable){
    final StringWriter sw = new StringWriter();
    final PrintWriter pw = new PrintWriter(sw, true);
    throwable.printStackTrace(pw);
    return sw.getBuffer().toString();
}


}