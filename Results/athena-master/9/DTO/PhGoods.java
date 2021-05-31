import java.io.Serializable;
import javax.persistence;
import java.util.Date;
public class PhGoods implements Serializable{

 private  Long id;

 private  String name;

 private  String image;

 private  Long brandId;

 private  String brandName;

 private  String brandLogo;

 private  String isOffway;

 private  String type;

 private  String category;

 private  String isRelease;

 private  String status;

 private  Date createTime;

 private  String remark;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


@Column(name = "brand_name", length = 50)
public String getBrandName(){
    return brandName;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


@Column(name = "name", length = 100)
public String getName(){
    return name;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


@Column(name = "status", length = 2)
public String getStatus(){
    return status;
}


@Column(name = "brand_id", length = 11)
public Long getBrandId(){
    return brandId;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


@Column(name = "is_release", length = 2)
public String getIsRelease(){
    return isRelease;
}


@Column(name = "is_offway", length = 2)
public String getIsOffway(){
    return isOffway;
}


@Column(name = "brand_logo", length = 200)
public String getBrandLogo(){
    return brandLogo;
}


@Column(name = "category", length = 20)
public String getCategory(){
    return category;
}


@Column(name = "type", length = 20)
public String getType(){
    return type;
}


@Column(name = "image", length = 100)
public String getImage(){
    return image;
}


public void setStatus(String status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStatus"));

.queryParam("status",status);
restTemplate.put(builder.toUriString(),null);
}


}