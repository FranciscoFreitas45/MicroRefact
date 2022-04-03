package org.gliderwiki.framework.filter.HttpHeadFilter;
 import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
public class NoBodyResponseWrapper extends HttpServletResponseWrapper{

 private  NoBodyOutputStream noBodyOutputStream;

 private  PrintWriter writer;

/**
 * Constructs a response adaptor wrapping the given response.
 *
 * @param response The response to wrap.
 */
public NoBodyResponseWrapper(HttpServletResponse response) {
    super(response);
}
@Override
public PrintWriter getWriter(){
    if (writer == null) {
        writer = new PrintWriter(new OutputStreamWriter(noBodyOutputStream, getCharacterEncoding()));
    }
    return writer;
}


public void setContentLength(){
    super.setContentLength(noBodyOutputStream.getContentLength());
}


}