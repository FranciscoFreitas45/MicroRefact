package org.jeecgframework.tag.core.util;
 import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class GzipUtilities {


public boolean isGzipDisabled(HttpServletRequest request){
    String flag = request.getParameter("disableGzip");
    return ((flag != null) && (!flag.equalsIgnoreCase("false")));
}


public boolean isGzipSupported(HttpServletRequest request){
    String encodings = request.getHeader("Accept-Encoding");
    return ((encodings != null) && (encodings.indexOf("gzip") != -1));
}


public OutputStream getGzipWriter(HttpServletResponse response){
    return (new GZIPOutputStream(response.getOutputStream()));
}


}