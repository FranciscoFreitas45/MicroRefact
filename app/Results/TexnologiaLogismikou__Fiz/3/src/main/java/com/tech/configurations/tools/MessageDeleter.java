package com.tech.configurations.tools;
 import com.tech.models.entities.Message;
import com.tech.services.interfaces.IMessageService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class MessageDeleter {

 private  int minuteInMillis;

@Autowired
 private IMessageService MS;


public void executeCleaning(){
    List<Message> messageList = MS.getAllMessages();
    for (Message vLookUp : messageList) {
        Date deletionDate = new Date(vLookUp.getDate().getTime() + (vLookUp.getTtl() * minuteInMillis));
        if (deletionDate.before(new Date())) {
            MS.delete(vLookUp);
        }
    }
}


@Scheduled(fixedRate = 5000)
public void run(){
    executeCleaning();
}


}