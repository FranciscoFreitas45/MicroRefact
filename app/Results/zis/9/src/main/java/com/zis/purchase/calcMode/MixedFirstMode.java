package com.zis.purchase.calcMode;
 import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.zis.purchase.calculate.BookAmountCalculateInterface;
@Component
public class MixedFirstMode implements CalculateModeInterface{

@Resource
 private  BookAmountCalculateInterface mixedCalculater;

@Resource
 private  BookAmountCalculateInterface defaultCalculater;


public Integer doCalculate(int bookId,Integer repoId){
    Integer result = null;
    result = mixedCalculater.calculate(bookId, repoId);
    if (result != null && result > 0) {
        return result;
    }
    return defaultCalculater.calculate(bookId, repoId);
}


}