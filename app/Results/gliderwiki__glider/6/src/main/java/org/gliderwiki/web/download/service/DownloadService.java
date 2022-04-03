package org.gliderwiki.web.download.service;
 import javax.servlet.http.HttpServletRequest;
public interface DownloadService {


public int sendActiveKey(String email,String activeKey,HttpServletRequest request)
;

}