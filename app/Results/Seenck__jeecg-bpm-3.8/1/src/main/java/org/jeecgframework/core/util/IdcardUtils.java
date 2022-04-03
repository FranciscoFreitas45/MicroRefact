package org.jeecgframework.core.util;
 import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
public class IdcardUtils extends StringUtils{

 public  int CHINA_ID_MIN_LENGTH;

 public  int CHINA_ID_MAX_LENGTH;

 public  String[] cityCode;

 public  int[] power;

 public  String[] verifyCode;

 public  int MIN;

 public  Map<String,String> cityCodes;

 public  Map<String,Integer> twFirstCode;

 public  Map<String,Integer> hkFirstCode;


public int[] converCharToInt(char[] ca){
    int len = ca.length;
    int[] iArr = new int[len];
    try {
        for (int i = 0; i < len; i++) {
            iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
        }
    } catch (NumberFormatException e) {
        e.printStackTrace();
    }
    return iArr;
}


public String getProvinceByIdCard(String idCard){
    int len = idCard.length();
    String sProvince = null;
    String sProvinNum = "";
    if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
        sProvinNum = idCard.substring(0, 2);
    }
    sProvince = cityCodes.get(sProvinNum);
    return sProvince;
}


public boolean isNum(String val){
    return val == null || "".equals(val) ? false : val.matches("^[0-9]*$");
}


public boolean validateIdCard15(String idCard){
    if (idCard.length() != CHINA_ID_MIN_LENGTH) {
        return false;
    }
    if (isNum(idCard)) {
        String proCode = idCard.substring(0, 2);
        if (cityCodes.get(proCode) == null) {
            return false;
        }
        String birthCode = idCard.substring(6, 12);
        Date birthDate = null;
        try {
            birthDate = new SimpleDateFormat("yy").parse(birthCode.substring(0, 2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        if (birthDate != null)
            cal.setTime(birthDate);
        if (!valiDate(cal.get(Calendar.YEAR), Integer.valueOf(birthCode.substring(2, 4)), Integer.valueOf(birthCode.substring(4, 6)))) {
            return false;
        }
    } else {
        return false;
    }
    return true;
}


public String[] validateIdCard10(String idCard){
    String[] info = new String[3];
    String card = idCard.replaceAll("[\\(|\\)]", "");
    if (card.length() != 8 && card.length() != 9 && idCard.length() != 10) {
        return null;
    }
    if (idCard.matches("^[a-zA-Z][0-9]{9}$")) {
        // 台湾
        info[0] = "台湾";
        org.jeecgframework.core.util.LogUtil.info("11111");
        String char2 = idCard.substring(1, 2);
        if (char2.equals("1")) {
            info[1] = "M";
            org.jeecgframework.core.util.LogUtil.info("MMMMMMM");
        } else if (char2.equals("2")) {
            info[1] = "F";
            org.jeecgframework.core.util.LogUtil.info("FFFFFFF");
        } else {
            info[1] = "N";
            info[2] = "false";
            org.jeecgframework.core.util.LogUtil.info("NNNN");
            return info;
        }
        info[2] = validateTWCard(idCard) ? "true" : "false";
    } else if (idCard.matches("^[1|5|7][0-9]{6}\\(?[0-9A-Z]\\)?$")) {
        // 澳门
        info[0] = "澳门";
        info[1] = "N";
    // TODO
    } else if (idCard.matches("^[A-Z]{1,2}[0-9]{6}\\(?[0-9A]\\)?$")) {
        // 香港
        info[0] = "香港";
        info[1] = "N";
        info[2] = validateHKCard(idCard) ? "true" : "false";
    } else {
        return null;
    }
    return info;
}


public String getBirthByIdCard(String idCard){
    Integer len = idCard.length();
    if (len < CHINA_ID_MIN_LENGTH) {
        return null;
    } else if (len == CHINA_ID_MIN_LENGTH) {
        idCard = conver15CardTo18(idCard);
    }
    return idCard.substring(6, 14);
}


public String getCheckCode18(int iSum){
    String sCode = "";
    switch(iSum % 11) {
        case 10:
            sCode = "2";
            break;
        case 9:
            sCode = "3";
            break;
        case 8:
            sCode = "4";
            break;
        case 7:
            sCode = "5";
            break;
        case 6:
            sCode = "6";
            break;
        case 5:
            sCode = "7";
            break;
        case 4:
            sCode = "8";
            break;
        case 3:
            sCode = "9";
            break;
        case 2:
            sCode = "x";
            break;
        case 1:
            sCode = "0";
            break;
        case 0:
            sCode = "1";
            break;
    }
    return sCode;
}


public boolean validateCard(String idCard){
    String card = idCard.trim();
    if (validateIdCard18(card)) {
        return true;
    }
    if (validateIdCard15(card)) {
        return true;
    }
    String[] cardval = validateIdCard10(card);
    if (cardval != null) {
        if (cardval[2].equals("true")) {
            return true;
        }
    }
    return false;
}


public String getGenderByIdCard(String idCard){
    String sGender = "N";
    if (idCard.length() == CHINA_ID_MIN_LENGTH) {
        idCard = conver15CardTo18(idCard);
    }
    String sCardNum = idCard.substring(16, 17);
    if (Integer.parseInt(sCardNum) % 2 != 0) {
        sGender = "M";
    } else {
        sGender = "F";
    }
    return sGender;
}


public boolean valiDate(int iYear,int iMonth,int iDate){
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int datePerMonth;
    if (iYear < MIN || iYear >= year) {
        return false;
    }
    if (iMonth < 1 || iMonth > 12) {
        return false;
    }
    switch(iMonth) {
        case 4:
        case 6:
        case 9:
        case 11:
            datePerMonth = 30;
            break;
        case 2:
            boolean dm = ((iYear % 4 == 0 && iYear % 100 != 0) || (iYear % 400 == 0)) && (iYear > MIN && iYear < year);
            datePerMonth = dm ? 29 : 28;
            break;
        default:
            datePerMonth = 31;
    }
    return (iDate >= 1) && (iDate <= datePerMonth);
}


public boolean validateIdCard18(String idCard){
    boolean bTrue = false;
    if (idCard.length() == CHINA_ID_MAX_LENGTH) {
        // 前17位
        String code17 = idCard.substring(0, 17);
        // 第18位
        String code18 = idCard.substring(17, CHINA_ID_MAX_LENGTH);
        if (isNum(code17)) {
            char[] cArr = code17.toCharArray();
            if (cArr != null) {
                int[] iCard = converCharToInt(cArr);
                int iSum17 = getPowerSum(iCard);
                // 获取校验位
                String val = getCheckCode18(iSum17);
                if (val.length() > 0) {
                    if (val.equalsIgnoreCase(code18)) {
                        bTrue = true;
                    }
                }
            }
        }
    }
    return bTrue;
}


public boolean validateHKCard(String idCard){
    String card = idCard.replaceAll("[\\(|\\)]", "");
    Integer sum = 0;
    if (card.length() == 9) {
        sum = (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 9 + (Integer.valueOf(card.substring(1, 2).toUpperCase().toCharArray()[0]) - 55) * 8;
        card = card.substring(1, 9);
    } else {
        sum = 522 + (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 8;
    }
    String mid = card.substring(1, 7);
    String end = card.substring(7, 8);
    char[] chars = mid.toCharArray();
    Integer iflag = 7;
    for (char c : chars) {
        sum = sum + Integer.valueOf(c + "") * iflag;
        iflag--;
    }
    if (end.toUpperCase().equals("A")) {
        sum = sum + 10;
    } else {
        sum = sum + Integer.valueOf(end);
    }
    return (sum % 11 == 0) ? true : false;
}


public Short getYearByIdCard(String idCard){
    Integer len = idCard.length();
    if (len < CHINA_ID_MIN_LENGTH) {
        return null;
    } else if (len == CHINA_ID_MIN_LENGTH) {
        idCard = conver15CardTo18(idCard);
    }
    return Short.valueOf(idCard.substring(6, 10));
}


public boolean validateTWCard(String idCard){
    String start = idCard.substring(0, 1);
    String mid = idCard.substring(1, 9);
    String end = idCard.substring(9, 10);
    Integer iStart = twFirstCode.get(start);
    Integer sum = iStart / 10 + (iStart % 10) * 9;
    char[] chars = mid.toCharArray();
    Integer iflag = 8;
    for (char c : chars) {
        sum = sum + Integer.valueOf(c + "") * iflag;
        iflag--;
    }
    return (sum % 10 == 0 ? 0 : (10 - sum % 10)) == Integer.valueOf(end) ? true : false;
}


public String conver15CardTo18(String idCard){
    String idCard18 = "";
    if (idCard.length() != CHINA_ID_MIN_LENGTH) {
        return null;
    }
    if (isNum(idCard)) {
        // 获取出生年月日
        String birthday = idCard.substring(6, 12);
        Date birthDate = null;
        try {
            birthDate = new SimpleDateFormat("yyMMdd").parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        if (birthDate != null)
            cal.setTime(birthDate);
        // 获取出生年(完全表现形式,如：2010)
        String sYear = String.valueOf(cal.get(Calendar.YEAR));
        idCard18 = idCard.substring(0, 6) + sYear + idCard.substring(8);
        // 转换字符数组
        char[] cArr = idCard18.toCharArray();
        if (cArr != null) {
            int[] iCard = converCharToInt(cArr);
            int iSum17 = getPowerSum(iCard);
            // 获取校验位
            String sVal = getCheckCode18(iSum17);
            if (sVal.length() > 0) {
                idCard18 += sVal;
            } else {
                return null;
            }
        }
    } else {
        return null;
    }
    return idCard18;
}


public int getAgeByIdCard(String idCard){
    int iAge = 0;
    if (idCard.length() == CHINA_ID_MIN_LENGTH) {
        idCard = conver15CardTo18(idCard);
    }
    String year = idCard.substring(6, 10);
    Calendar cal = Calendar.getInstance();
    int iCurrYear = cal.get(Calendar.YEAR);
    iAge = iCurrYear - Integer.valueOf(year);
    return iAge;
}


public Short getMonthByIdCard(String idCard){
    Integer len = idCard.length();
    if (len < CHINA_ID_MIN_LENGTH) {
        return null;
    } else if (len == CHINA_ID_MIN_LENGTH) {
        idCard = conver15CardTo18(idCard);
    }
    return Short.valueOf(idCard.substring(10, 12));
}


public int getPowerSum(int[] iArr){
    int iSum = 0;
    if (power.length == iArr.length) {
        for (int i = 0; i < iArr.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    iSum = iSum + iArr[i] * power[j];
                }
            }
        }
    }
    return iSum;
}


public Short getDateByIdCard(String idCard){
    Integer len = idCard.length();
    if (len < CHINA_ID_MIN_LENGTH) {
        return null;
    } else if (len == CHINA_ID_MIN_LENGTH) {
        idCard = conver15CardTo18(idCard);
    }
    return Short.valueOf(idCard.substring(12, 14));
}


}