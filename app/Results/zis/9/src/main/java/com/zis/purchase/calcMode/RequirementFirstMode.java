package com.zis.purchase.calcMode;
 import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.zis.purchase.calculate.BookAmountCalculateInterface;
@Component
public class RequirementFirstMode implements CalculateModeInterface{

@Resource
 private  BookAmountCalculateInterface requirementCalculater;

@Resource
 private  BookAmountCalculateInterface salesCalculater;

@Resource
 private  BookAmountCalculateInterface defaultCalculater;


public Integer doCalculate(int bookId,Integer repoId){
    Integer result = null;
    result = requirementCalculater.calculate(bookId, repoId);
    if (result != null && result > 0) {
        return result;
    }
    result = salesCalculater.calculate(bookId, repoId);
    if (result != null && result > 0) {
        return result;
    }
    return defaultCalculater.calculate(bookId, repoId);
}


}