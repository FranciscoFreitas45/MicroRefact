package kielce.tu.weaii.telelearn.views;
 import kielce.tu.weaii.telelearn.models.GlobalNews;
import lombok.Value;
import java.time.LocalDateTime;
@Value
public class GlobalNewsView {

 private Long id;

 private String title;

 private UserView author;

 private String brief;

 private String htmlContent;

 private LocalDateTime publicationDate;


public GlobalNewsView from(GlobalNews model){
    return new GlobalNewsView(model.getId(), model.getTitle(), UserView.from(model.getAuthor(), false), model.getBrief(), model.getHtmlContent(), model.getPublicationDate());
}


}