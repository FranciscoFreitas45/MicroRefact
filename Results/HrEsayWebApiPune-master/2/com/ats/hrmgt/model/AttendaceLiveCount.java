import javax.persistence;
@Entity
public class AttendaceLiveCount {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "lv_sumup_id")
 private  int lvSumupId;

@Column(name = "atts_sd_show")
 private  String attsSdShow;

@Column(name = "cnt")
 private  int cnt;


public String getAttsSdShow(){
    return attsSdShow;
}


public void setCnt(int cnt){
    this.cnt = cnt;
}


public void setLvSumupId(int lvSumupId){
    this.lvSumupId = lvSumupId;
}


public void setAttsSdShow(String attsSdShow){
    this.attsSdShow = attsSdShow;
}


@Override
public String toString(){
    return "AttendaceLiveCount [lvSumupId=" + lvSumupId + ", attsSdShow=" + attsSdShow + ", cnt=" + cnt + "]";
}


public int getLvSumupId(){
    return lvSumupId;
}


public int getCnt(){
    return cnt;
}


}