package org.jeecgframework.core.util;
 import java.util.HashSet;
import java.util.Set;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
public class PinyinUtil {


public String stringArrayToString(String[] str){
    return stringArrayToString(str, "");
}


public String charArrayToString(char[] ch){
    return charArrayToString(ch, " ");
}


public String[] stringToPinyin(String src,boolean isPolyphone,String separator){
    // 判断字符串是否为空
    if ("".equals(src) || null == src) {
        return null;
    }
    char[] srcChar = src.toCharArray();
    int srcCount = srcChar.length;
    String[] srcStr = new String[srcCount];
    for (int i = 0; i < srcCount; i++) {
        srcStr[i] = charToPinyin(srcChar[i], isPolyphone, separator);
    }
    return srcStr;
}


public char[] getHeadByChar(char src){
    return getHeadByChar(src, true);
}


public String charToPinyin(char src,boolean isPolyphone,String separator){
    // 创建汉语拼音处理类
    HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
    // 输出设置，大小写，音标方式
    defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    StringBuffer tempPinying = new StringBuffer();
    // 如果是中文
    if (src > 128) {
        try {
            // 转换得出结果
            String[] strs = PinyinHelper.toHanyuPinyinStringArray(src, defaultFormat);
            // 是否查出多音字，默认是查出多音字的第一个字符
            if (isPolyphone && null != separator) {
                for (int i = 0; i < strs.length; i++) {
                    tempPinying.append(strs[i]);
                    if (strs.length != (i + 1)) {
                        // 多音字之间用特殊符号间隔起来
                        tempPinying.append(separator);
                    }
                }
            } else {
                tempPinying.append(strs[0]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
    } else {
        tempPinying.append(src);
    }
    return tempPinying.toString();
}


public String[][] DoExchange(String[][] strJaggedArray){
    int len = strJaggedArray.length;
    if (len >= 2) {
        int len1 = strJaggedArray[0].length;
        int len2 = strJaggedArray[1].length;
        int newlen = len1 * len2;
        String[] temp = new String[newlen];
        int Index = 0;
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                temp[Index] = strJaggedArray[0][i] + strJaggedArray[1][j];
                Index++;
            }
        }
        String[][] newArray = new String[len - 1][];
        for (int i = 2; i < len; i++) {
            newArray[i - 1] = strJaggedArray[i];
        }
        newArray[0] = temp;
        return DoExchange(newArray);
    } else {
        return strJaggedArray;
    }
}


public Set<String> getPinyin(String src){
    if (src != null && !src.trim().equalsIgnoreCase("")) {
        char[] srcChar;
        srcChar = src.toCharArray();
        // 汉语拼音格式输出类
        HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();
        // 输出设置，大小写，音标方式等
        hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        String[][] temp = new String[src.length()][];
        for (int i = 0; i < srcChar.length; i++) {
            char c = srcChar[i];
            // 是中文或者a-z或者A-Z转换拼音(我的需求，是保留中文或者a-z或者A-Z)
            if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {
                try {
                    temp[i] = PinyinHelper.toHanyuPinyinStringArray(srcChar[i], hanYuPinOutputFormat);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else if (((int) c >= 65 && (int) c <= 90) || ((int) c >= 97 && (int) c <= 122)) {
                temp[i] = new String[] { String.valueOf(srcChar[i]) };
            } else {
                temp[i] = new String[] { "" };
            }
        }
        String[] pingyinArray = Exchange(temp);
        Set<String> pinyinSet = new HashSet<String>();
        for (int i = 0; i < pingyinArray.length; i++) {
            pinyinSet.add(pingyinArray[i]);
        }
        return pinyinSet;
    }
    return null;
}


public String getPinYin(String src){
    char[] t1 = null;
    t1 = src.toCharArray();
    // org.jeecgframework.core.util.LogUtil.info(t1.length);
    String[] t2 = new String[t1.length];
    // org.jeecgframework.core.util.LogUtil.info(t2.length);
    // 设置汉字拼音输出的格式
    HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
    t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    t3.setVCharType(HanyuPinyinVCharType.WITH_V);
    String t4 = "";
    int t0 = t1.length;
    try {
        for (int i = 0; i < t0; i++) {
            // 判断能否为汉字字符
            // org.jeecgframework.core.util.LogUtil.info(t1[i]);
            if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                // 将汉字的几种全拼都存到t2数组中
                t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                // 取出该汉字全拼的第一种读音并连接到字符串t4后
                t4 += t2[0];
            } else {
                // 如果不是汉字字符，间接取出字符并连接到字符串t4后
                t4 += Character.toString(t1[i]);
            }
        }
    } catch (BadHanyuPinyinOutputFormatCombination e) {
        e.printStackTrace();
    }
    return t4;
}


public String[] Exchange(String[][] strJaggedArray){
    String[][] temp = DoExchange(strJaggedArray);
    return temp[0];
}


public String getCnASCII(String cnStr){
    StringBuffer strBuf = new StringBuffer();
    // 将字符串转换成字节序列
    byte[] bGBK = cnStr.getBytes();
    for (int i = 0; i < bGBK.length; i++) {
        // org.jeecgframework.core.util.LogUtil.info(Integer.toHexString(bGBK[i] & 0xff));
        // 将每个字符转换成ASCII码
        strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
    }
    return strBuf.toString();
}


public String makeStringByStringSet(Set<String> stringSet){
    StringBuilder str = new StringBuilder();
    int i = 0;
    for (String s : stringSet) {
        if (i == stringSet.size() - 1) {
            str.append(s);
        } else {
            str.append(s + ",");
        }
        i++;
    }
    return str.toString().toLowerCase();
}


public String getPinYinHeadChar(String str){
    String convert = "";
    for (int j = 0; j < str.length(); j++) {
        char word = str.charAt(j);
        // 提取汉字的首字母
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
        if (pinyinArray != null) {
            convert += pinyinArray[0].charAt(0);
        } else {
            convert += word;
        }
    }
    return convert;
}


public String converterToFirstSpell(String chines){
    String pinyinName = "";
    char[] nameChar = chines.toCharArray();
    HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
    defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    for (int i = 0; i < nameChar.length; i++) {
        if (nameChar[i] > 128) {
            try {
                pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        } else {
            pinyinName += nameChar[i];
        }
    }
    return pinyinName;
}


@SuppressWarnings("deprecation")
public String hanziToPinyin(String hanzi,String separator){
    // 创建汉语拼音处理类
    HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
    // 输出设置，大小写，音标方式
    defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    String pinyingStr = "";
    try {
        // update--begin--author:Yandong date:20180525 for:TASK #2713 【jeecg maven】 验证 版本 替换 正式版本--
        pinyingStr = PinyinHelper.toHanYuPinyinString(hanzi, defaultFormat, separator, false);
    // update--end--author:Yandong date:20180525 for:TASK #2713 【jeecg maven】 验证 版本 替换 正式版本--
    } catch (BadHanyuPinyinOutputFormatCombination e) {
        e.printStackTrace();
    }
    return pinyingStr;
}


public String converterToSpell(String chines){
    String pinyinName = "";
    char[] nameChar = chines.toCharArray();
    HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
    defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    for (int i = 0; i < nameChar.length; i++) {
        if (nameChar[i] > 128) {
            try {
                pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        } else {
            pinyinName += nameChar[i];
        }
    }
    return pinyinName;
}


public String[] getHeadByString(String src,boolean isCapital,String separator){
    char[] chars = src.toCharArray();
    String[] headString = new String[chars.length];
    int i = 0;
    for (char ch : chars) {
        char[] chs = getHeadByChar(ch, isCapital);
        StringBuffer sb = new StringBuffer();
        if (null != separator) {
            int j = 1;
            for (char ch1 : chs) {
                sb.append(ch1);
                if (j != chs.length) {
                    sb.append(separator);
                }
                j++;
            }
        } else {
            sb.append(chs[0]);
        }
        headString[i] = sb.toString();
        i++;
    }
    return headString;
}


}