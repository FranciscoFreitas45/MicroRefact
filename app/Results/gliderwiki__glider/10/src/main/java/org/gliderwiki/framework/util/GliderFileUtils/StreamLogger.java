package org.gliderwiki.framework.util.GliderFileUtils;
 import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.gliderwiki.web.domain.AttachmentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
public class StreamLogger extends Thread{

 private  InputStream mInputStream;

public StreamLogger(InputStream is) {
    this.mInputStream = is;
}
public void run(){
    InputStreamReader isr = null;
    BufferedReader br = null;
    try {
        isr = new InputStreamReader(mInputStream);
        br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null) {
        // System.out.println(line);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        IOUtils.closeQuietly(isr);
        IOUtils.closeQuietly(br);
    }
}


}