package org.gliderwiki.install;
 import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
public class LoadTableData {

 private Logger logger;

 private  Map<Integer,String> allTables;

public LoadTableData() {
}
public String getTable(Integer idx){
    return this.allTables.get(idx);
}


public Map getAllTables(){
    return this.allTables;
}


public String LoadTableData(String tableInitPath,String enc){
    FileInputStream fis = null;
    InputStreamReader ins = null;
    BufferedReader reader = null;
    String encoding = "";
    try {
        fis = new FileInputStream(tableInitPath + "/table_list.sql");
        ins = new InputStreamReader(fis, enc);
        logger.info("##############################");
        logger.info("FILE encoding : " + ins.getEncoding());
        logger.info("##############################");
        encoding = ins.getEncoding();
        reader = new BufferedReader(ins);
        int lineNum = 0;
        allTables = new HashMap<Integer, String>();
        while (true) {
            String line = reader.readLine();
            if (line != null || !line.equals("")) {
                allTables.put(lineNum, line);
            } else {
                break;
            }
            // System.out.println("lineNum : " + lineNum +" ,    Table : " + line);
            lineNum++;
        }
        reader.close();
    } catch (Exception e) {
        try {
            reader.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    return encoding;
}


}