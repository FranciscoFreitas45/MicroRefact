package com.zis.purchase.action;
 import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import com.opensymphony.xwork2.ActionSupport;
import com.zis.bookinfo.dto.BookInfoAndDetailDTO;
import com.zis.bookinfo.service.BookService;
public class TaobaoCsvDataExportAction extends ActionSupport{

 private  long serialVersionUID;

 protected  String[] emails;

 protected  BookService bookService;

 protected  ThreadPoolTaskExecutor taskExecutor;


public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor){
    this.taskExecutor = taskExecutor;
}


public List<BookInfoAndDetailDTO> queryBookInfoAndDetails()


public void run(){
    bookService.generateTaobaoCsvDataFile(list, emails);
}


public void setEmails(String[] emails){
    this.emails = emails;
}


public void setBookService(BookService bookService){
    this.bookService = bookService;
}


public String export(){
    // 查询数据
    final List<BookInfoAndDetailDTO> list = queryBookInfoAndDetails();
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
    // 返回并提示结果
    this.addActionMessage(msg);
    return SUCCESS;
}


}