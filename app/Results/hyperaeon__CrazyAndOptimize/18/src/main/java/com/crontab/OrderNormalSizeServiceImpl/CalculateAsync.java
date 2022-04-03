package com.crontab.OrderNormalSizeServiceImpl;
 import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.google.gson.Gson;
import com.ssc.faw.util.GenException;
import com.test.CurrencyPairGroup;
public class CalculateAsync implements Callable<String>{


@Override
public String call(){
    return calculateAllNormalSize();
}


}