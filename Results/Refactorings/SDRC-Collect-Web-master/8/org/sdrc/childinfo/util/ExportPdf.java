import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import org.apache.commons.codec.binary.Base64;
import org.sdrc.childinfo.model.DashboardExport;
import org.sdrc.childinfo.model.UtDataModel;
import org.sdrc.childinfo.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfWriter;
@Component
public class ExportPdf implements PdfPTableEvent{

@Autowired
 private  DashboardService dashboardService;

@Autowired
 private  ServletContext context;


public PdfPTable createTable(DashboardExport dashboardExport,Document document){
    String areaId = dashboardExport.getAreaId();
    String indicatorId = dashboardExport.getIndicatorId();
    String sourceId = dashboardExport.getSourceId();
    String timePeriodId = dashboardExport.getTimePeriodId();
    Integer childLevel = dashboardExport.getChildLevel();
    PdfPTable table = new PdfPTable(4);
    List<UtDataModel> utDataModelList;
    utDataModelList = dashboardService.fetchPdfData(indicatorId, sourceId, areaId, timePeriodId, childLevel);
    float[] columnWidths = new float[] { 10f, 40f, 20f, 20f };
    table.setWidths(columnWidths);
    BaseFont bf;
    try {
        bf = BaseFont.createFont(context.getRealPath("resources\\fonts\\calibri.ttf"), BaseFont.WINANSI, false);
    } catch (Exception ex) {
        bf = null;
    }
    Font bigBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    Font small = new Font(Font.FontFamily.HELVETICA, 10);
    if (bf != null) {
        bigBold = new Font(bf, 10, Font.BOLD);
        small = new Font(bf, 10);
    }
    // Updated by sourav keshari nath
    PdfPCell cell2 = new PdfPCell(new Paragraph("SL NO", bigBold));
    cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
    PdfPCell cell3 = new PdfPCell(new Paragraph("AREA NAME", bigBold));
    cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
    PdfPCell cell4 = new PdfPCell(new Paragraph("DATA VALUE", bigBold));
    cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
    cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
    PdfPCell cell5 = new PdfPCell(new Paragraph("RANK", bigBold));
    cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
    cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
    cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell2);
    table.addCell(cell3);
    table.addCell(cell4);
    table.addCell(cell5);
    UtDataModel temp = new UtDataModel();
    System.out.println(utDataModelList.size() + "----size utDataModel");
    int n = utDataModelList.size();
    int i, j;
    for (i = 0; i < n - 1; i++) {
        for (j = i + 1; j < n; j++) {
            if (utDataModelList.get(i).getAreaName().compareTo(utDataModelList.get(j).getAreaName()) > 0) {
                temp = utDataModelList.get(i);
                utDataModelList.set(i, utDataModelList.get(j));
                utDataModelList.set(j, temp);
            }
        }
    }
    BaseColor mycolor = WebColors.getRGBColor("#E0E0E0");
    int count = 0;
    if (utDataModelList != null && utDataModelList.size() > 0) {
        for (UtDataModel utDataModel : utDataModelList) {
            boolean evenrow;
            count++;
            evenrow = count % 2 == 0;
            PdfPCell utData2 = new PdfPCell(new Paragraph("" + count, small));
            utData2.setHorizontalAlignment(Element.ALIGN_CENTER);
            if (evenrow)
                utData2.setBackgroundColor(mycolor);
            PdfPCell utData3 = new PdfPCell(new Paragraph(utDataModel.getAreaName(), small));
            if (evenrow)
                utData3.setBackgroundColor(mycolor);
            PdfPCell utData4 = new PdfPCell(new Paragraph(utDataModel.getValue(), small));
            utData4.setHorizontalAlignment(Element.ALIGN_CENTER);
            utData4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            if (evenrow)
                utData4.setBackgroundColor(mycolor);
            PdfPCell utData5 = new PdfPCell(new Paragraph(utDataModel.getRank(), small));
            utData5.setHorizontalAlignment(Element.ALIGN_CENTER);
            if (evenrow)
                utData5.setBackgroundColor(mycolor);
            table.addCell(utData2);
            table.addCell(utData3);
            table.addCell(utData4);
            table.addCell(utData5);
        }
    }
    return table;
}


@Override
public void tableLayout(PdfPTable table,float[][] widths,float[] heights,int headerRows,int rowStart,PdfContentByte[] canvases){
// TODO Auto-generated method stub
}


public PdfPTable createTableForProjectionAndBurden(DashboardExport dashboardExport,Document document,List<UtDataModel> utDataModelList){
    PdfPTable table = new PdfPTable(4);
    float[] columnWidths = new float[] { 10f, 40f, 20f, 20f };
    table.setWidths(columnWidths);
    BaseFont bf;
    try {
        bf = BaseFont.createFont(context.getRealPath("resources\\fonts\\calibri.ttf"), BaseFont.WINANSI, false);
    } catch (Exception ex) {
        bf = null;
    }
    Font bigBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    Font small = new Font(Font.FontFamily.HELVETICA, 10);
    if (bf != null) {
        bigBold = new Font(bf, 10, Font.BOLD);
        small = new Font(bf, 10);
    }
    // Updated by sourav keshari nath
    PdfPCell cell2 = new PdfPCell(new Paragraph("SL NO", bigBold));
    cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
    PdfPCell cell3 = new PdfPCell(new Paragraph("AREA NAME", bigBold));
    cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
    PdfPCell cell4 = new PdfPCell(new Paragraph("DATA VALUE", bigBold));
    cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
    cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
    PdfPCell cell5 = new PdfPCell(new Paragraph("RANK", bigBold));
    cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
    cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
    cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell2);
    table.addCell(cell3);
    table.addCell(cell4);
    table.addCell(cell5);
    UtDataModel temp = new UtDataModel();
    System.out.println(utDataModelList.size() + "----size utDataModel");
    int n = utDataModelList.size();
    int i, j;
    for (i = 0; i < n - 1; i++) {
        for (j = i + 1; j < n; j++) {
            if (utDataModelList.get(i).getAreaName().compareTo(utDataModelList.get(j).getAreaName()) > 0) {
                temp = utDataModelList.get(i);
                utDataModelList.set(i, utDataModelList.get(j));
                utDataModelList.set(j, temp);
            }
        }
    }
    BaseColor mycolor = WebColors.getRGBColor("#E0E0E0");
    int count = 0;
    if (utDataModelList != null && utDataModelList.size() > 0) {
        for (UtDataModel utDataModel : utDataModelList) {
            boolean evenrow;
            count++;
            evenrow = count % 2 == 0;
            PdfPCell utData2 = new PdfPCell(new Paragraph("" + count, small));
            utData2.setHorizontalAlignment(Element.ALIGN_CENTER);
            if (evenrow)
                utData2.setBackgroundColor(mycolor);
            PdfPCell utData3 = new PdfPCell(new Paragraph(utDataModel.getAreaName(), small));
            if (evenrow)
                utData3.setBackgroundColor(mycolor);
            int convertedValue = (int) Double.parseDouble(utDataModel.getValue());
            PdfPCell utData4 = new PdfPCell(new Paragraph(String.valueOf(convertedValue), small));
            utData4.setHorizontalAlignment(Element.ALIGN_CENTER);
            utData4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            if (evenrow)
                utData4.setBackgroundColor(mycolor);
            PdfPCell utData5 = new PdfPCell(new Paragraph(utDataModel.getRank(), small));
            utData5.setHorizontalAlignment(Element.ALIGN_CENTER);
            if (evenrow)
                utData5.setBackgroundColor(mycolor);
            table.addCell(utData2);
            table.addCell(utData3);
            table.addCell(utData4);
            table.addCell(utData5);
        }
    }
    return table;
}


public String createPdfForProjectionAndBurden(String topBottomimg,String legendimg,DashboardExport dashboardExport,List<UtDataModel> utDataModelList){
    String area = dashboardExport.getArea();
    String indicator = dashboardExport.getIndicator();
    String source = dashboardExport.getSource();
    String timePeriod = dashboardExport.getTimePeriod();
    String imgPath = dashboardExport.getImgPath();
    // top3 bottom3
    topBottomimg = topBottomimg.split(",")[1];
    byte[] decodedImage = Base64.decodeBase64(topBottomimg);
    // legend
    legendimg = legendimg.split(",")[1];
    byte[] decodelegendimg = Base64.decodeBase64(legendimg);
    String filepath = "";
    try {
        Document document = new Document(PageSize.A4.rotate());
        filepath = ResourceBundle.getBundle("spring/app").getString("jpgimage.path");
        filepath = filepath + "/" + area + ".pdf";
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filepath));
        // wiring up header footer event
        Font small = new Font(Font.FontFamily.HELVETICA, 10);
        HeaderFooter event = new HeaderFooter(context);
        // wiring up header footer event
        writer.setPageEvent(event);
        document.open();
        Font font = FontFactory.getFont("HELVETICA", 14);
        Font font1 = FontFactory.getFont("HELVETICA");
        Paragraph paragraph = new Paragraph();
        paragraph.setFont(small);
        paragraph.setSpacingAfter(20);
        Paragraph paragraph1 = new Paragraph();
        paragraph1.setAlignment(Element.ALIGN_LEFT);
        paragraph1.setSpacingAfter(2);
        Chunk chunk1 = new Chunk("" + indicator + ", " + area + ", " + timePeriod, font);
        paragraph1.add(chunk1);
        Paragraph paragraph2 = new Paragraph();
        paragraph2.setAlignment(Element.ALIGN_LEFT);
        paragraph2.setSpacingAfter(2);
        Chunk chunk2 = new Chunk(source, font1);
        paragraph2.add(chunk2);
        Image image = Image.getInstance(imgPath);
        int indentation = 0;
        Image image1 = Image.getInstance(decodedImage);
        int indentation1 = 0;
        Image image2 = Image.getInstance(decodelegendimg);
        int indentation2 = 0;
        float scaler, scaler1, scaler2, scaler3;
        scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - indentation) / image.getWidth()) * 62;
        image.scalePercent(scaler);
        image.setAbsolutePosition(160, 80);
        scaler1 = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - indentation1) / image1.getWidth()) * 25;
        // System.out.println(area+"-------"+scaler1);
        image1.setAbsolutePosition(40, 80);
        image1.scalePercent(scaler1);
        scaler2 = ((document.getPageSize().getWidth() - document.rightMargin() - indentation2) / image2.getWidth()) * 17;
        image2.setAbsolutePosition(680, 80);
        image2.scalePercent(scaler2);
        PdfPTable table = createTableForProjectionAndBurden(dashboardExport, document, utDataModelList);
        document.add(paragraph);
        document.add(paragraph1);
        document.add(paragraph2);
        document.add(image);
        document.add(image1);
        document.add(image2);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEXTPAGE);
        document.add(table);
        document.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return filepath;
}


}