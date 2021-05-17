import org.springframework.boot.context.properties.ConfigurationProperties;
public class Web {

 private  String fileShare;


public String getFileShare(){
    return fileShare;
}


public void setFileShare(String fileShare){
    this.fileShare = fileShare;
}


}