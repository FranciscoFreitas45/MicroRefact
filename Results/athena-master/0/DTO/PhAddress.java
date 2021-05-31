import java.io.Serializable;
import javax.persistence;
import java.util.Date;
public class PhAddress implements Serializable{

 private  Long id;

 private  String unionid;

 private  String realName;

 private  String phone;

 private  String province;

 private  String city;

 private  String county;

 private  String content;

 private  Date createTime;

 private  String remark;


@Column(name = "phone", length = 20)
public String getPhone(){
    return phone;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


@Column(name = "unionid", length = 200)
public String getUnionid(){
    return unionid;
}


@Column(name = "content", length = 200)
public String getContent(){
    return content;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


@Column(name = "real_name", length = 50)
public String getRealName(){
    return realName;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


@Column(name = "province", length = 20)
public String getProvince(){
    return province;
}


@Column(name = "county", length = 20)
public String getCounty(){
    return county;
}


@Column(name = "city", length = 20)
public String getCity(){
    return city;
}


}