import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
public class LoseGoodsDTO {

 private  Long id;

 private  String goodsName;

 private  String goodsRepresent;

 private  String goodsPut;

 private  String goodsGet;

 private  String goodsGetNo;

 private  String goodsGetPhone;


public String getGoodsName(){
    return goodsName;
}


public void setGoodsName(String goodsName){
    this.goodsName = goodsName;
}


public void setGoodsPut(String goodsPut){
    this.goodsPut = goodsPut;
}


public void setGoodsGetNo(String goodsGetNo){
    this.goodsGetNo = goodsGetNo;
}


public String getGoodsPut(){
    return goodsPut;
}


public Long getId(){
    return id;
}


public void setGoodsRepresent(String goodsRepresent){
    this.goodsRepresent = goodsRepresent;
}


@SuppressWarnings({ "serial" })
public Specification<LoseGoods> getWhereClause(LoseGoodsDTO loseGoodsDTO){
    return new Specification<LoseGoods>() {

        @Override
        public Predicate toPredicate(Root<LoseGoods> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicate = new ArrayList<>();
            Predicate[] pre = new Predicate[predicate.size()];
            return query.where(predicate.toArray(pre)).getRestriction();
        }
    };
}


@Override
public Predicate toPredicate(Root<LoseGoods> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicate = new ArrayList<>();
    Predicate[] pre = new Predicate[predicate.size()];
    return query.where(predicate.toArray(pre)).getRestriction();
}


public String getGoodsRepresent(){
    return goodsRepresent;
}


public String getGoodsGetPhone(){
    return goodsGetPhone;
}


public String getGoodsGet(){
    return goodsGet;
}


public void setGoodsGetPhone(String goodsGetPhone){
    this.goodsGetPhone = goodsGetPhone;
}


public String getGoodsGetNo(){
    return goodsGetNo;
}


public void setId(Long id){
    this.id = id;
}


public void setGoodsGet(String goodsGet){
    this.goodsGet = goodsGet;
}


}