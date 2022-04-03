package com.gbcom.system.servlet.init;
 import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.apache.log4j.Logger;
import p1.DOGException;
import com.gbcom.omc.validate.Author;
import com.gbcom.omc.validate.OmcVerifier;
public class InitServerDlg {

 private  Logger log;

 private  InitServerDlg instance;

 private  JOptionPane optionPane;

 private  JDialog createDialog;

 private  String showMessage;

 private  boolean isValidate;

/**
 * 构造方法
 */
private InitServerDlg() {
    optionPane = new JOptionPane("xx", JOptionPane.QUESTION_MESSAGE, JOptionPane.CLOSED_OPTION);
    createDialog = optionPane.createDialog("初始化服务提示框");
}
public String getShowMessage(){
    return showMessage;
}


public boolean isValidate(){
    return isValidate;
}


public void showDailog(String message){
    final SwingWorker<?, ?> worker = new SwingWorker<Object, Object>() {

        @Override
        protected Object doInBackground() throws Exception {
            return null;
        }

        @Override
        protected void done() {
            super.done();
            if (message.equalsIgnoreCase("success")) {
                createDialog.setVisible(false);
            } else {
                if (createDialog.isVisible()) {
                    if (!showMessage.equals(message)) {
                        showMessage = message;
                        optionPane.setMessage(message);
                        optionPane.repaint();
                        createDialog.repaint();
                    }
                } else {
                    optionPane.setMessage(message);
                    createDialog.setContentPane(optionPane);
                    createDialog.pack();
                    createDialog.setLocationRelativeTo(createDialog);
                    createDialog.setVisible(true);
                }
            }
            try {
                sendMessage(message);
            } catch (Exception e) {
                log.error("Exception：", e);
            }
        }
    };
    worker.execute();
}


public void setValidate(boolean isValidate){
    InitServerDlg.isValidate = isValidate;
}


@Override
public Object doInBackground(){
    return null;
}


public void sendMessage(String message){
}


public void main(String[] args){
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            int cpenum = 100;
            long result = OmcVerifier.validate();
            if (result != 0L) {
                InitServerDlg.getInstance().showDailog(DOGException.getErrorMsg((int) result, 0));
                InitServerDlg.setValidate(false);
            } else {
                String cpeAuthorNum = OmcVerifier.getAuthorData(Author.AC);
                if (cpenum > Integer.valueOf(cpeAuthorNum)) {
                    InitServerDlg.getInstance().showDailog("您的授权项数目为" + cpeAuthorNum + "小于您的实际使用值" + cpenum);
                    InitServerDlg.setValidate(false);
                } else {
                    InitServerDlg.getInstance().showDailog("success");
                    InitServerDlg.setValidate(true);
                }
            }
        }
    };
    Timer timer = new Timer();
    timer.schedule(task, 10000, 20000);
}


@Override
public void run(){
    int cpenum = 100;
    long result = OmcVerifier.validate();
    if (result != 0L) {
        InitServerDlg.getInstance().showDailog(DOGException.getErrorMsg((int) result, 0));
        InitServerDlg.setValidate(false);
    } else {
        String cpeAuthorNum = OmcVerifier.getAuthorData(Author.AC);
        if (cpenum > Integer.valueOf(cpeAuthorNum)) {
            InitServerDlg.getInstance().showDailog("您的授权项数目为" + cpeAuthorNum + "小于您的实际使用值" + cpenum);
            InitServerDlg.setValidate(false);
        } else {
            InitServerDlg.getInstance().showDailog("success");
            InitServerDlg.setValidate(true);
        }
    }
}


public InitServerDlg getInstance(){
    if (null == instance) {
        instance = new InitServerDlg();
    }
    return instance;
}


@Override
public void done(){
    super.done();
    if (message.equalsIgnoreCase("success")) {
        createDialog.setVisible(false);
    } else {
        if (createDialog.isVisible()) {
            if (!showMessage.equals(message)) {
                showMessage = message;
                optionPane.setMessage(message);
                optionPane.repaint();
                createDialog.repaint();
            }
        } else {
            optionPane.setMessage(message);
            createDialog.setContentPane(optionPane);
            createDialog.pack();
            createDialog.setLocationRelativeTo(createDialog);
            createDialog.setVisible(true);
        }
    }
    try {
        sendMessage(message);
    } catch (Exception e) {
        log.error("Exception：", e);
    }
}


}