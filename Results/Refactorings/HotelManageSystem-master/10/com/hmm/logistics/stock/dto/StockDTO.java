import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import com.hmm.logistics.stock.entity.Stock;
import com.hmm.logistics.stock.util.StockType;
import com.hmm.logistics.stock.util.YesOrNoSend;
public class StockDTO {

 private  long id;

 private  String goodsName;

 private  String unit;

 private  float amount;

 private  String stockType;

 private  String yesOrNoSend;

 private  String goodsNo;


public void setGoodsNo(String goodsNo){
    this.goodsNo = goodsNo;
}


public String getGoodsName(){
    return goodsName;
}


public void setGoodsName(String goodsName){
    this.goodsName = goodsName;
}


public String getGoodsNo(){
    return goodsNo;
}


public long getId(){
    return id;
}


@SuppressWarnings({ "serial" })
public Specification<Stock> getWhereClause(StockDTO stockDTO){
    return new Specification<Stock>() {

        @Override
        public Predicate toPredicate(Root<Stock> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicate = new ArrayList<>();
            if (StringUtils.isNotBlank(stockDTO.getGoodsName())) {
                predicate.add(criteriaBuilder.like(root.get("goodsName").as(String.class), "%" + stockDTO.getGoodsName() + "%"));
            }
            if (StringUtils.isNotBlank(stockDTO.getStockType())) {
                if (stockDTO.getStockType().equals("COMMODITY")) {
                    predicate.add(criteriaBuilder.equal(root.get("stockType").as(StockType.class), StockType.COMMODITY));
                }
                if (stockDTO.getStockType().equals("DURABLE")) {
                    predicate.add(criteriaBuilder.equal(root.get("stockType").as(StockType.class), StockType.DURABLE));
                }
            }
            if (10 == stockDTO.getAmount()) {
                predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("amount").as(float.class), stockDTO.getAmount()));
            }
            if (StringUtils.isNotBlank(stockDTO.getYesOrNoSend())) {
                if (stockDTO.getYesOrNoSend().equals("未申请")) {
                    predicate.add(criteriaBuilder.equal(root.get("yesOrNoSend").as(YesOrNoSend.class), YesOrNoSend.NO));
                }
            }
            Predicate[] pre = new Predicate[predicate.size()];
            return query.where(predicate.toArray(pre)).getRestriction();
        }
    };
}


@Override
public Predicate toPredicate(Root<Stock> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicate = new ArrayList<>();
    if (StringUtils.isNotBlank(stockDTO.getGoodsName())) {
        predicate.add(criteriaBuilder.like(root.get("goodsName").as(String.class), "%" + stockDTO.getGoodsName() + "%"));
    }
    if (StringUtils.isNotBlank(stockDTO.getStockType())) {
        if (stockDTO.getStockType().equals("COMMODITY")) {
            predicate.add(criteriaBuilder.equal(root.get("stockType").as(StockType.class), StockType.COMMODITY));
        }
        if (stockDTO.getStockType().equals("DURABLE")) {
            predicate.add(criteriaBuilder.equal(root.get("stockType").as(StockType.class), StockType.DURABLE));
        }
    }
    if (10 == stockDTO.getAmount()) {
        predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("amount").as(float.class), stockDTO.getAmount()));
    }
    if (StringUtils.isNotBlank(stockDTO.getYesOrNoSend())) {
        if (stockDTO.getYesOrNoSend().equals("未申请")) {
            predicate.add(criteriaBuilder.equal(root.get("yesOrNoSend").as(YesOrNoSend.class), YesOrNoSend.NO));
        }
    }
    Predicate[] pre = new Predicate[predicate.size()];
    return query.where(predicate.toArray(pre)).getRestriction();
}


public void setUnit(String unit){
    this.unit = unit;
}


public String getStockType(){
    return stockType;
}


public void setStockType(String stockType){
    this.stockType = stockType;
}


public void setId(long id){
    this.id = id;
}


public String getYesOrNoSend(){
    return yesOrNoSend;
}


public String getUnit(){
    return unit;
}


public void setAmount(float amount){
    this.amount = amount;
}


public void setYesOrNoSend(String yesOrNoSend){
    this.yesOrNoSend = yesOrNoSend;
}


public float getAmount(){
    return amount;
}


}