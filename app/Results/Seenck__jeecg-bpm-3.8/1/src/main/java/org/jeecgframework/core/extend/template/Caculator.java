package org.jeecgframework.core.extend.template;
 import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Stack;
import java.util.Vector;
public class Caculator {

 public  java.text.NumberFormat nfd;

 public  java.text.NumberFormat nfi;

 public  DecimalFormat dfd;

 public  DecimalFormat dfi;

 private boolean ops;

 private double value;

 private Character opVal;

 private int opPriority;

 private Stack<Item> opStack;

 private Vector<Item> calcStack;

/**
 */
public Caculator() {
// TODO Auto-generated constructor stub
}
public void doOps(StringBuffer buffer,Item newItem){
    while (!opStack.isEmpty()) {
        Item item = opStack.peek();
        if (item.opVal != '(' && item.opPriority >= newItem.opPriority) {
            calcStack.add(item);
            opStack.pop();
            buffer.append(item.opVal);
        } else {
            break;
        }
    }
    opStack.push(newItem);
}


public String transInfixToPosfix(String in){
    char[] cin = in.toCharArray();
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < cin.length; i++) {
        Item newItem = new Item();
        newItem.opPriority = 1;
        newItem.ops = false;
        switch(cin[i]) {
            case '+':
                newItem.opPriority = 1;
                newItem.ops = true;
                newItem.opVal = '+';
                doOps(buffer, newItem);
                break;
            case '-':
                newItem.opPriority = 1;
                newItem.ops = true;
                newItem.opVal = '-';
                doOps(buffer, newItem);
                break;
            case '*':
                newItem.opPriority = 2;
                newItem.ops = true;
                newItem.opVal = '*';
                doOps(buffer, newItem);
                break;
            case '/':
                newItem.opPriority = 2;
                newItem.ops = true;
                newItem.opVal = '/';
                doOps(buffer, newItem);
                break;
            case '(':
                newItem.ops = true;
                newItem.opVal = '(';
                opStack.push(newItem);
                break;
            case ')':
                boolean meetClose = false;
                while (!opStack.isEmpty()) {
                    Item item = opStack.peek();
                    if (item.ops && item.opVal != '(') {
                        calcStack.add(item);
                        opStack.pop();
                        buffer.append(item.opVal);
                    } else if (item.ops) {
                        opStack.pop();
                        meetClose = true;
                        break;
                    }
                }
                if (!meetClose) {
                    org.jeecgframework.core.util.LogUtil.info(in);
                    throw new RuntimeException();
                }
                break;
            default:
                int j = i;
                for (; j < cin.length && ((cin[j] >= '0' && cin[j] <= '9') || cin[j] == '.' || cin[j] == 'E'); j++) ;
                if (j == i) {
                    throw new RuntimeException("wrong input.");
                }
                newItem.ops = false;
                try {
                    newItem.value = Double.parseDouble(new String(cin, i, j - i));
                } catch (NumberFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    org.jeecgframework.core.util.LogUtil.info("cal数字生成错误！！");
                }
                buffer.append(newItem.value);
                calcStack.add(newItem);
                i = j - 1;
                break;
        }
    }
    while (!opStack.isEmpty()) {
        Item item = opStack.pop();
        calcStack.add(item);
        buffer.append(item.opVal);
    }
    return buffer.toString();
}


public void main(String[] args){
    Caculator calc = new Caculator();
    // org.jeecgframework.core.util.LogUtil.info(calc.transInfixToPosfix("1+2*3+7.5-(4/2+8)/5"));
    // org.jeecgframework.core.util.LogUtil.info("value is:"+calc.calc());
    // org.jeecgframework.core.util.LogUtil.info(calc.transInfixToPosfix("110815.00047*2"));
    // org.jeecgframework.core.util.LogUtil.info("value is:"+calc.calc());
    // org.jeecgframework.core.util.LogUtil.info(calc.transInfixToPosfix("110815.125047*2"));
    // org.jeecgframework.core.util.LogUtil.info("value is:"+calc.calc());
    // org.jeecgframework.core.util.LogUtil.info(calc.transInfixToPosfix("((110815.0347)*2+10)/0"));
    // org.jeecgframework.core.util.LogUtil.info("value is:"+calc.calc());
    // org.jeecgframework.core.util.LogUtil.info(calc.transInfixToPosfix("0.02"));
    // org.jeecgframework.core.util.LogUtil.info("value is:"+calc.calc());
    // org.jeecgframework.core.util.LogUtil.info(calc.transInfixToPosfix("0.0"));
    // org.jeecgframework.core.util.LogUtil.info("value is:"+calc.calc());
    org.jeecgframework.core.util.LogUtil.info(calc.transInfixToPosfix("1.3E7+0.5"));
    org.jeecgframework.core.util.LogUtil.info("value is:" + calc.calc());
    Double dd = Double.parseDouble("1.35378957E7");
    org.jeecgframework.core.util.LogUtil.info(Caculator.getDtoI(dd));
    org.jeecgframework.core.util.LogUtil.info(dd);
}


public String calc(){
    Stack<Item> tmp = new Stack<Item>();
    while (!calcStack.isEmpty()) {
        Item it = calcStack.remove(0);
        if (!it.ops) {
            tmp.push(it);
        } else {
            double op2 = tmp.pop().value;
            // tmp.pop().value;
            double op1 = 0L;
            if (!tmp.isEmpty()) {
                op1 = tmp.pop().value;
            }
            Item newItem = new Item();
            newItem.ops = true;
            switch(it.opVal) {
                case '+':
                    newItem.value = op1 + op2;
                    break;
                case '-':
                    newItem.value = op1 - op2;
                    break;
                case '*':
                    newItem.value = op1 * op2;
                    break;
                case '/':
                    newItem.value = op1 / op2;
                    if (newItem.value == Double.NEGATIVE_INFINITY || newItem.value == Double.POSITIVE_INFINITY || new Double(newItem.value).toString().equals("NaN")) {
                        newItem.value = 0.0;
                    }
                    // double tt=0.0/0.0;
                    // double ff=Double.longBitsToDouble(0x7ff8000000000000L);
                    // if(new Double(tt).toString().equals("NaN"))
                    // {
                    // org.jeecgframework.core.util.LogUtil.info(tt);
                    // }
                    break;
            }
            tmp.push(newItem);
        }
    }
    Double result = tmp.pop().value;
    return getDtoI(result);
/* double c=0;
        long b = (long)Math.round(result * 100); //小数点后两位前移，并四舍五入 
        if((b % 100)!=0)
        {
        	c = (double)b / 100.00; //还原小数点后两位
        	return dfd.format(c);
        }else
        {
        	c = (double)b / 100.00;
        	return dfi.format(c);
        }
        */
}


public String getDtoI(Double d){
    double c = 0;
    // 小数点后两位前移，并四舍五入
    long b = (long) Math.round(d * 100);
    if ((b % 100) != 0) {
        // 还原小数点后两位
        c = (double) b / 100.00;
        return dfd.format(c);
    } else {
        c = (double) b / 100.00;
        return dfi.format(c);
    }
}


}