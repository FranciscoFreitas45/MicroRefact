package org.gliderwiki.mock.wiki.common.etc;
 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class Calculator {


public int sum(){
    Operation oper = new Operation() {

        @Override
        public int exec(int result, String s) {
            result += Integer.valueOf(s);
            return result;
        }
    };
    return templateLine(oper) - 1;
}


@Override
public int exec(int result,String s){
    result *= Integer.valueOf(s);
    return result;
}


public int templateLine(Operation oper){
    String[] arrList = new String[4];
    int result = 1;
    try {
        URL url = new URL("http://bluepoet.dothome.co.kr/test.html");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = null;
        String str = "";
        while ((line = br.readLine()) != null) {
            str += line;
        }
        arrList = str.split("<br>");
        for (String s : arrList) {
            result = oper.exec(result, s);
        }
        return result;
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return result;
}


public int multi(){
    Operation oper = new Operation() {

        @Override
        public int exec(int result, String s) {
            result *= Integer.valueOf(s);
            return result;
        }
    };
    return templateLine(oper);
}


}