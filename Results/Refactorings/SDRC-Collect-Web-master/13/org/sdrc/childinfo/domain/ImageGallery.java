import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Image_Gallery")
public class ImageGallery implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int galleryId;

@Column(name = "Image_Title")
 private  String title;

@Column(name = "Image_Message")
 private  String message;

@Column(name = "Image_Url")
 private  String imageUrl;

@Column(name = "created_date")
 private  Timestamp createdDate;

@Column(name = "created_by", length = 100)
 private  String createdBy;


public void setImageUrl(String imageUrl){
    this.imageUrl = imageUrl;
}


public String getTitle(){
    return title;
}


public void setCreatedDate(Timestamp createdDate){
    this.createdDate = createdDate;
}


public void setGalleryId(int galleryId){
    this.galleryId = galleryId;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public String getImageUrl(){
    return imageUrl;
}


public void setTitle(String title){
    this.title = title;
}


public String getMessage(){
    return message;
}


public Timestamp getCreatedDate(){
    return createdDate;
}


public void setMessage(String message){
    this.message = message;
}


public int getGalleryId(){
    return galleryId;
}


public String getCreatedBy(){
    return createdBy;
}


}