package br.com.wtag.lottery.service;
 import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import br.com.wtag.lottery.model.entity.RandomNumbers;
public class LotteryService {

 private  String MAX_MUST_BE_GREATER_THAN_MIN;

 private  Integer MINUS_ONE;

 private  Integer ONE;

 private  Integer ZERO;


public List<Integer> getRandomNumberListForExclude(List<RandomNumbers> randomNumberListForExclude){
    List<Integer> randomNumberList = new ArrayList<>();
    if (randomNumberListForExclude != null && !randomNumberListForExclude.isEmpty()) {
        for (Integer x = ZERO; x < randomNumberListForExclude.size(); x++) {
            randomNumberList.add(randomNumberListForExclude.get(x).getRandomNumber());
        }
    }
    return randomNumberList;
}


public Integer getRandomNumber(Integer min,Integer max,List<RandomNumbers> randomNumberListForExclude){
    if (max.compareTo(min) == MINUS_ONE) {
        throw new IllegalArgumentException(MAX_MUST_BE_GREATER_THAN_MIN);
    }
    Integer maximumNumberIncluded = max + ONE;
    Integer randomNumber = new Random().ints(min, maximumNumberIncluded).limit(ONE).findFirst().getAsInt();
    List<Integer> randomNumberList = getRandomNumberListForExclude(randomNumberListForExclude);
    if (randomNumberList != null && !randomNumberList.isEmpty()) {
        while (randomNumberList.contains(randomNumber)) {
            randomNumber = new Random().ints(min, maximumNumberIncluded).limit(ONE).findFirst().getAsInt();
        }
    }
    return randomNumber;
}


}