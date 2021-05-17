import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HttpRequest {


public String getData(String url,HttpMode mode,String parm,Map<String,String> heards){
    HttpURLConnection connection = null;
    String requestMode = "POST";
    String retResult = "";
    try {
        URL realUrl = new URL(url);
        connection = (HttpURLConnection) realUrl.openConnection();
        if (mode != null) {
            requestMode = mode.toString();
        }
        if (mode == HttpMode.POST) {
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod(requestMode);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
        }
        if (heards != null) {
            for (String key : heards.keySet()) {
                connection.setRequestProperty(key, heards.get(key));
            }
        }
        connection.connect();
        if (mode == HttpMode.POST) {
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            if (parm != null) {
                writer.write(parm.toString());
            }
            writer.flush();
        }
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer buffer = new StringBuffer();
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        inputStream = null;
        connection.disconnect();
        retResult = buffer.toString();
    } finally {
        if (connection != null) {
            connection.disconnect();
        }
    }
    return retResult;
}


}