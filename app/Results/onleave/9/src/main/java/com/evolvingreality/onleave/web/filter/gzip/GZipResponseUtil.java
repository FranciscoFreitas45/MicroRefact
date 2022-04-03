package com.evolvingreality.onleave.web.filter.gzip;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class GZipResponseUtil {

 private  Logger LOG;

 private  int EMPTY_GZIPPED_CONTENT_SIZE;

/**
 * Utility class. No public constructor.
 */
private GZipResponseUtil() {
// noop
}
public void addGzipHeader(HttpServletResponse response){
    response.setHeader("Content-Encoding", "gzip");
    boolean containsEncoding = response.containsHeader("Content-Encoding");
    if (!containsEncoding) {
        throw new GzipResponseHeadersNotModifiableException("Failure when attempting to set " + "Content-Encoding: gzip");
    }
}


public boolean shouldGzippedBodyBeZero(byte[] compressedBytes,HttpServletRequest request){
    // Check for 0 length body
    if (compressedBytes.length == EMPTY_GZIPPED_CONTENT_SIZE) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("{} resulted in an empty response.", request.getRequestURL());
        }
        return true;
    } else {
        return false;
    }
}


public boolean shouldBodyBeZero(HttpServletRequest request,int responseStatus){
    // Check for NO_CONTENT
    if (responseStatus == HttpServletResponse.SC_NO_CONTENT) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("{} resulted in a {} response. Removing message body in accordance with RFC2616.", request.getRequestURL(), HttpServletResponse.SC_NO_CONTENT);
        }
        return true;
    }
    // Check for NOT_MODIFIED
    if (responseStatus == HttpServletResponse.SC_NOT_MODIFIED) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("{} resulted in a {} response. Removing message body in accordance with RFC2616.", request.getRequestURL(), HttpServletResponse.SC_NOT_MODIFIED);
        }
        return true;
    }
    return false;
}


}