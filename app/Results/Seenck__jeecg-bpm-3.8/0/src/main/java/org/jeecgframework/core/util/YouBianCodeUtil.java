package org.jeecgframework.core.util;
 public class YouBianCodeUtil {

 private  int numLength;

 public  int zhanweiLength;


public String getNextYouBianCode(String code){
    String newcode = "";
    if (code == null || code == "") {
        String zimu = "A";
        String num = getStrNum(1);
        newcode = zimu + num;
    } else {
        String before_code = code.substring(0, code.length() - 1 - numLength);
        String after_code = code.substring(code.length() - 1 - numLength, code.length());
        char after_code_zimu = after_code.substring(0, 1).charAt(0);
        Integer after_code_num = Integer.parseInt(after_code.substring(1));
        // org.jeecgframework.core.util.LogUtil.info(after_code);
        // org.jeecgframework.core.util.LogUtil.info(after_code_zimu);
        // org.jeecgframework.core.util.LogUtil.info(after_code_num);
        String nextNum = "";
        char nextZimu = 'A';
        // 先判断数字等于999*，则计数从1重新开始，递增
        if (after_code_num == getMaxNumByLength(numLength)) {
            nextNum = getNextStrNum(0);
        } else {
            nextNum = getNextStrNum(after_code_num);
        }
        // 先判断数字等于999*，则字母从A重新开始,递增
        if (after_code_num == getMaxNumByLength(numLength)) {
            nextZimu = getNextZiMu(after_code_zimu);
        } else {
            nextZimu = after_code_zimu;
        }
        // 例如Z99，下一个code就是Z99A01
        if ('Z' == after_code_zimu && getMaxNumByLength(numLength) == after_code_num) {
            newcode = code + (nextZimu + nextNum);
        } else {
            newcode = before_code + (nextZimu + nextNum);
        }
    }
    return newcode;
}


public char getNextZiMu(char zimu){
    if (zimu == 'Z') {
        return 'A';
    }
    zimu++;
    return zimu;
}


public int getMaxNumByLength(int length){
    if (length == 0) {
        return 0;
    }
    String max_num = "";
    for (int i = 0; i < length; i++) {
        max_num = max_num + "9";
    }
    return Integer.parseInt(max_num);
}


public String getNextStrNum(int num){
    return getStrNum(getNextNum(num));
}


public String getStrNum(int num){
    String s = String.format("%0" + numLength + "d", num);
    return s;
}


public int getNextNum(int num){
    num++;
    return num;
}


public String[] cutYouBianCode(String code){
    if (code == null || StringUtil.isEmpty(code)) {
        return null;
    } else {
        // 获取标准长度为numLength+1,截取的数量为code.length/numLength+1
        int c = code.length() / (numLength + 1);
        String[] cutcode = new String[c];
        for (int i = 0; i < c; i++) {
            cutcode[i] = code.substring(0, (i + 1) * (numLength + 1));
        }
        return cutcode;
    }
}


public void main(String[] args){
    // org.jeecgframework.core.util.LogUtil.info(getNextZiMu('C'));
    // org.jeecgframework.core.util.LogUtil.info(getNextNum(8));
    org.jeecgframework.core.util.LogUtil.info(getSubYouBianCode("C99A01", "B03"));
// org.jeecgframework.core.util.LogUtil.info(cutYouBianCode("C99A01B01")[2]);
}


public String getSubYouBianCode(String parentCode,String localCode){
    if (localCode != null && localCode != "") {
        // ----update-begin--------autor:张肖江----------date:20150120--- for:解决postcode生成bug，----
        // return parentCode + getNextYouBianCode(localCode);
        return getNextYouBianCode(localCode);
    // ----update-end----------autor:张肖江----------date:20150120--- for:解决postcode生成bug，----
    } else {
        parentCode = parentCode + "A" + getNextStrNum(0);
    }
    return parentCode;
}


}