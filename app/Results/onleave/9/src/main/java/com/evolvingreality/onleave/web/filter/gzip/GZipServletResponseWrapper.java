package com.evolvingreality.onleave.web.filter.gzip;
 import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;
public class GZipServletResponseWrapper extends HttpServletResponseWrapper{

 private  GZipServletOutputStream gzipOutputStream;

 private  PrintWriter printWriter;

 private  boolean disableFlushBuffer;

public GZipServletResponseWrapper(HttpServletResponse response, GZIPOutputStream gzout) throws IOException {
    super(response);
    gzipOutputStream = new GZipServletOutputStream(gzout);
}
public void flush(){
    if (printWriter != null) {
        printWriter.flush();
    }
    if (gzipOutputStream != null) {
        gzipOutputStream.flush();
    }
}


@Override
public void flushBuffer(){
    // PrintWriter.flush() does not throw exception
    if (this.printWriter != null) {
        this.printWriter.flush();
    }
    if (this.gzipOutputStream != null) {
        this.gzipOutputStream.flush();
    }
    // doing this might leads to response already committed exception
    // when the PageInfo has not yet built but the buffer already flushed
    // Happens in Weblogic when a servlet forward to a JSP page and the forward
    // method trigger a flush before it forwarded to the JSP
    // disableFlushBuffer for that purpose is 'true' by default
    if (!disableFlushBuffer) {
        super.flushBuffer();
    }
}


@Override
public PrintWriter getWriter(){
    if (this.printWriter == null) {
        this.gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
        this.printWriter = new PrintWriter(new OutputStreamWriter(this.gzipOutputStream, getResponse().getCharacterEncoding()), true);
    }
    return this.printWriter;
}


@Override
public void setContentLength(int length){
// ignore, since content length of zipped content
// does not match content length of unzipped content.
}


public void setDisableFlushBuffer(boolean disableFlushBuffer){
    this.disableFlushBuffer = disableFlushBuffer;
}


@Override
public ServletOutputStream getOutputStream(){
    if (this.printWriter != null) {
        throw new IllegalStateException("PrintWriter obtained already - cannot get OutputStream");
    }
    return this.gzipOutputStream;
}


public void close(){
    // PrintWriter.close does not throw exceptions. Thus, the call does not need
    // be inside a try-catch block.
    if (this.printWriter != null) {
        this.printWriter.close();
    }
    if (this.gzipOutputStream != null) {
        this.gzipOutputStream.close();
    }
}


}