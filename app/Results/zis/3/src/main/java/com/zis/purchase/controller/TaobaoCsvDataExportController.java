package com.zis.purchase.controller;
 import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.ui.ModelMap;
import com.zis.bookinfo.dto.BookInfoAndDetailDTO;
import com.zis.bookinfo.service.BookService;
public class TaobaoCsvDataExportController {

 protected  String[] emails;

@Autowired
 protected  BookService bookService;

@Autowired
 protected  ThreadPoolTaskExecutor taskExecutor;


public List<BookInfoAndDetailDTO> queryBookInfoAndDetails(HttpServletRequest request)


public String getSuccessPage()


public void run(){
    bookService.generateTaobaoCsvDataFile(list, emails);
}


public String export(HttpServletRequest request,ModelMap map){
    // 查询数据
    final List<BookInfoAndDetailDTO> list = queryBookInfoAndDetails(request);
    // 异步执行
    Thread task = new Thread(new Runnable() {

        public void run() {
            bookService.generateTaobaoCsvDataFile(list, emails);
        }
    });
    taskExecutor.execute(task);
    // 预估操作时间
    int seconds = list.size() / 60;
    if (seconds < 2) {
        seconds = 2;
    }
    String msg = String.format("数据正在生成中，可能需要%s分钟，成功后将发送到邮箱%s", seconds, ArrayUtils.toString(emails));
    System.out.println(msg);
    // 返回并提示结果
    map.put("actionMessage", msg);
    return getSuccessPage();
}


}