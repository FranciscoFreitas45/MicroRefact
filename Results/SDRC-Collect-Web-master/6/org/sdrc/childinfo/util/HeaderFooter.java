import javax.servlet.ServletContext;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
public class HeaderFooter extends PdfPageEventHelper {

 private Phrase[] header;

 private int pagenumber;

 private  ServletContext context;


public void onOpenDocument(PdfWriter writer,Document document){
    header[0] = new Phrase("Movie history");
}


public void onChapter(PdfWriter writer,Document document,float paragraphPosition,Paragraph title){
    header[1] = new Phrase(title.getContent());
    pagenumber = 1;
}


public void onEndPage(PdfWriter writer,Document document){
    Image image;
    Font fontStyle = new Font();
    fontStyle.setColor(255, 255, 255);
    fontStyle.setSize(10);
    Font fontStyle2 = new Font();
    fontStyle2.setColor(20, 29, 34);
    fontStyle2.setSize(4);
    try {
        image = Image.getInstance(context.getRealPath("resources\\images\\footer.png"));
        int indentation = 0;
        float scaler = ((document.getPageSize().getWidth() - indentation) / image.getWidth()) * 100;
        image.scalePercent(scaler);
        image.setAbsolutePosition(0, 0);
        document.add(image);
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    String date = CommonUtility.currentDateAndTime();
    String domainNameUnicef = "http://www.unicef.org";
    ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(String.format(domainNameUnicef), fontStyle2), document.left(70), document.bottomMargin() - 17, 0);
    String domainName = "from http://childinfo.org";
    ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(String.format("Page - %d, Printed on : %s %s", pagenumber, date, domainName), fontStyle), (document.getPageSize().getWidth()) / 2, document.bottomMargin() - 25, 0);
}


public void onStartPage(PdfWriter writer,Document document){
    pagenumber++;
    Image image;
    try {
        image = Image.getInstance(context.getRealPath("resources\\images\\header.png"));
        int indentation = 0;
        float scaler = ((document.getPageSize().getWidth() - indentation) / image.getWidth()) * 100;
        image.scalePercent(scaler);
        image.setAbsolutePosition(0, document.getPageSize().getHeight() + document.topMargin() - image.getHeight());
        document.add(image);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}


}