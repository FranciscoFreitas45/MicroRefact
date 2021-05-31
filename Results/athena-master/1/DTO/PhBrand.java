import java.io.Serializable;
import javax.persistence;
import java.util.Date;
public class PhBrand implements Serializable{

 private  Long id;

 private  String name;

 private  String logo;

 private  String info;

 private  String background;

 private  String realName;

 private  String phone;

 private  String province;

 private  String city;

 private  String county;

 private  String content;

 private  Date createTime;

 private  String remark;

 private  String status;

 private  Long returnAddrId;

 private  Long addrId;


@Column(name = "phone", length = 20)
public String getPhone(){
    return phone;
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


@Column(name = "status", length = 2)
public String getStatus(){
    return status;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


@Column(name = "return_addr_id", length = 200)
public Long getReturnAddrId(){
    return returnAddrId;
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


@Column(name = "logo", length = 200)
public String getLogo(){
    return logo;
}


@Column(name = "info")
public String getInfo(){
    return info;
}


@Column(name = "real_name", length = 50)
public String getRealName(){
    return realName;
}


@Column(name = "addr_id", length = 200)
public Long getAddrId(){
    return addrId;
}


@Column(name = "background", length = 200)
public String getBackground(){
    return background;
}


}