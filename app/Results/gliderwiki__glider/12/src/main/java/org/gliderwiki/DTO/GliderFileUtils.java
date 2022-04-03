package org.gliderwiki.DTO;
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
public class GliderFileUtils extends FileUtils{

 public  Logger log;

 public  String attachment_root_dir;

 public  String CHARSET;

 public  long EXPIRE_MILLIS;

 public  int FAIL_RESULT;

 private  InputStream mInputStream;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public File getDestFile(String filePath){
    return new File(attachment_root_dir + "/glider/attachments" + filePath);
}


public File getParentDir(File file){
    String parentDirPath = FilenameUtils.getFullPathNoEndSeparator(file.getAbsolutePath());
    File parentDir = new File(parentDirPath);
    return parentDir;
}


public File getRootDestFile(String fileName){
    File tempFile = new File(attachment_root_dir + "/glider/attachments" + fileName);
    log.debug("attachment_root_dir : " + attachment_root_dir);
    log.debug("/glider/attachments" + fileName);
    File parentDir = getParentDir(tempFile);
    forceMkdir(parentDir);
    return tempFile;
}


public void htmlToPdfExport(HttpServletRequest request,HttpServletResponse response,String wikiPageNum){
    int exitCode = FAIL_RESULT;
    Runtime run = Runtime.getRuntime();
    Process p = null;
    try {
        p = run.exec(makePdfCommand(request.getScheme() + "://" + request.getServerName(), wikiPageNum));
        StreamLogger in = new StreamLogger(p.getInputStream());
        StreamLogger err = new StreamLogger(p.getErrorStream());
        in.start();
        err.start();
        exitCode = p.waitFor();
        log.info("exitCode : " + exitCode);
        // 프로그램이 에러없이 실행되었을 경우 pdf 파일 다운로드 함
        if (exitCode > FAIL_RESULT) {
            downloadFile(request, response, new File("/home/user/instance/nighthawk/webapps/ROOT/resource/pdf/" + wikiPageNum + ".pdf"), AttachmentType.DOWNLOAD);
        }
    } catch (Exception e) {
        log.debug("pdf export시 exception 발생! : {}", e.getMessage());
    } finally {
        if (p != null) {
            p.destroy();
        }
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/htmlToPdfExport"))

.queryParam("request",request)
.queryParam("response",response)
.queryParam("wikiPageNum",wikiPageNum)
;
restTemplate.put(builder.toUriString(),null);
}


}