package goorum.goorum.util;
 import goorum.goorum.domain.Article;
import goorum.goorum.domain.Boardlist;
import goorum.goorum.domain.Replylist;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
public class Conversion {

 private  int DATE_START;

 private  int DATE_END;

 private  int TIME_START;

 private  int TIME_END;

 private  int BEGIN;

 private  int MAX_TITLE_LENGTH;

 private  int MAX_CONTENT_LENGTH;

 private  int MAX_IMAGE_LENGHT;


public void convertTitleLength(List<Boardlist> boards){
    boards.forEach(board -> {
        if (board.getTitle().length() > MAX_TITLE_LENGTH) {
            StringBuilder title = new StringBuilder(board.getTitle().substring(BEGIN, MAX_TITLE_LENGTH));
            title.append("...");
            board.setTitle(title.toString());
        }
    });
}


public void convertDateFormatForArticleList(Article article){
    if (article.getDate().length() != 21)
        return;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String today = sdf.format(new Date());
    if (article.getDate().substring(DATE_START, DATE_END).equals(today)) {
        article.setDate(article.getDate().substring(TIME_START, TIME_END));
    } else {
        article.setDate(article.getDate().substring(DATE_START, DATE_END));
    }
}


public void convertDateFormatForArticle(Article article){
    article.setDate(article.getDate().substring(DATE_START, TIME_END));
}


public void convertContent(Article article){
    String oldContent = article.getContent();
    article.setContent(oldContent.replace("\n", "<br>"));
}


public String convertImageName(String originalFilename){
    if (originalFilename.length() > MAX_TITLE_LENGTH) {
        StringBuilder sb = new StringBuilder();
        sb.append(originalFilename.substring(BEGIN, MAX_IMAGE_LENGHT));
        sb.append(".png");
        return sb.toString();
    }
    return originalFilename;
}


public void convertContentLength(List<Replylist> replies){
    replies.forEach(reply -> {
        if (reply.getContent().length() > MAX_CONTENT_LENGTH) {
            StringBuilder content = new StringBuilder(reply.getContent().substring(BEGIN, MAX_CONTENT_LENGTH));
            content.append("...");
            reply.setContent(content.toString());
        }
    });
}


public int calcStartPage(int page){
    return ((page - 1) / 10) * 10 + 1;
}


}