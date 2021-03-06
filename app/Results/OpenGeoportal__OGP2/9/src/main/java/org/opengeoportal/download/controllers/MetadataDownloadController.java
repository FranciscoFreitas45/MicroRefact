package org.opengeoportal.download.controllers;
 import javax.servlet.http.HttpServletResponse;
import org.opengeoportal.download.MetadataRetriever;
import org.opengeoportal.metadata.LayerInfoRetriever;
import org.opengeoportal.utilities.OgpFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.opengeoportal.Interface.LayerInfoRetriever;
@Controller
@RequestMapping("/getMetadata")
public class MetadataDownloadController {

 final  Logger logger;

@Autowired
 private  MetadataRetriever metadataRetriever;

@Autowired
 private  LayerInfoRetriever layerInfoRetriever;


@RequestMapping(method = RequestMethod.GET)
@ResponseBody
public void processMetadataDownload(String id,HttpServletResponse response){
    handleMetadataRequest(id, false, "html", response);
}


public String getContentDisposition(Boolean attachment){
    String disposition;
    if (attachment) {
        disposition = "attachment";
    } else {
        disposition = "inline";
    }
    return disposition;
}


public String getContentType(String format){
    String mimeType;
    if (format.equalsIgnoreCase("html")) {
        mimeType = "application/html;charset=UTF-8";
    } else if (format.equalsIgnoreCase("xml")) {
        mimeType = "application/xml;charset=UTF-8";
    } else {
        throw new Exception("Unrecognized mime-type.");
    }
    return mimeType;
}


public void handleMetadataRequest(String id,Boolean download,String format,HttpServletResponse response){
    String metadataString = getMetadataString(id, format);
    response.setContentLength(metadataString.getBytes("UTF-8").length);
    response.setHeader("Content-Disposition", getContentDisposition(download) + "; filename=\"" + getFileName(id) + "." + format.toLowerCase().trim() + "\"");
    response.setContentType(getContentType(format));
    // return a link to the zip file, or info to create link
    response.getWriter().write(metadataString);
}


public String getMetadataString(String layerId,String format){
    String metadataString = "";
    if (format.equalsIgnoreCase("xml")) {
        metadataString = this.metadataRetriever.getXMLStringFromId(layerId, "fgdc");
    } else if (format.equalsIgnoreCase("html")) {
        metadataString = this.metadataRetriever.getMetadataAsHtml(layerId);
    } else {
        throw new Exception("Unrecognized format: " + format);
    }
    return metadataString;
}


public String getFileName(String id){
    String fileName = null;
    try {
        fileName = layerInfoRetriever.getAllLayerInfo(id).getName();
    } catch (Exception e) {
        e.printStackTrace();
        fileName = id;
    }
    return OgpFileUtils.filterName(fileName);
}


}