package org.sdrc.childinfo.web;
 import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.sdrc.childinfo.model.DashboardExport;
import org.sdrc.childinfo.model.MapSvg;
import org.sdrc.childinfo.model.UtDataModel;
import org.sdrc.childinfo.service.DashboardService;
import org.sdrc.childinfo.util.ExportPdf;
import org.sdrc.childinfo.util.ImageEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import org.sdrc.Interface.DashboardController;
import org.sdrc.Interface.DashboardService;
@Controller
public class ExportController implements PdfPTableEvent{

@Autowired
 private  ServletContext context;

@Autowired
 private  DashboardController dashboardController;

@Autowired
 private  ResourceBundleMessageSource workspaceMessageSource;

@Autowired
 private  DashboardService dashboardService;

@Autowired
 private  ExportPdf exportPdf;


@RequestMapping(value = "/exportToExcel", method = RequestMethod.POST)
public String exportToExcel(){
    throw new RuntimeException("Still code to be written");
}


@Override
public void tableLayout(PdfPTable table,float[][] widths,float[] heights,int headerRows,int rowStart,PdfContentByte[] canvases){
// TODO Auto-generated method stub
}


@RequestMapping(value = "/downloadPDF", method = RequestMethod.POST)
public void downLoad(String name,HttpServletResponse response){
    java.io.InputStream inputStream;
    try {
        // String fileName = "D:/Pdf/India_test.pdf";
        // System.out.println(fileName);
        String fileName = name.replaceAll("%3A", ":").replaceAll("%2F", "/");
        inputStream = new FileInputStream(fileName);
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", new java.io.File(fileName).getName());
        response.setHeader(headerKey, headerValue);
        response.setContentType("pdf");
        ServletOutputStream outputStream = response.getOutputStream();
        FileCopyUtils.copy(inputStream, outputStream);
        outputStream.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


@RequestMapping(value = "/exportImage1", method = RequestMethod.POST, headers = { "Content-type=application/json" })
@ResponseBody
public String makeSvgToImage(List<MapSvg> mapSvgs){
    String imgPath = "";
    System.out.println("mapSvgs===>>>>>>" + mapSvgs.size());
    ImageEncoder encoder = new ImageEncoder();
    org.w3c.dom.Document document = encoder.createDocument(mapSvgs);
    try {
        imgPath = encoder.save(document);
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return imgPath;
}


@RequestMapping(value = "/exportToProjectionAndBurdenPdf", method = RequestMethod.POST)
@ResponseBody
public String exportToProjectionAndBurdenPdf(String dataList,String topBottomimg,String legendimg,DashboardExport dashboardExport,HttpServletResponse response){
    java.io.InputStream inputStream;
    ObjectMapper mapper = new ObjectMapper();
    UtDataModel[] dataModelList = mapper.readValue(dataList, UtDataModel[].class);
    // (UtDataModel)dataModelList;
    List<UtDataModel> utDataModelList = new ArrayList<UtDataModel>();
    utDataModelList = java.util.Arrays.asList(dataModelList);
    try {
        String fileName = exportPdf.createPdfForProjectionAndBurden(topBottomimg, legendimg, dashboardExport, utDataModelList);
        return fileName;
    } catch (FileNotFoundException e) {
        return "failure";
    } catch (IOException e) {
        e.printStackTrace();
        return "failure";
    }
}


}