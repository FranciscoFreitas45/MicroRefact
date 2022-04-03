package com.crazy.chapter15.duplicate.execise.notepad.action;
 import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import com.crazy.chapter15.duplicate.execise.notepad.Constants;
public class ConfirmDialogAction {

 private  JFrame f;

 private  JTextArea jta;

public ConfirmDialogAction(JFrame f, JTextArea jta) {
    this.f = f;
    this.jta = jta;
}
public void dialog(){
    if (!jta.getText().isEmpty()) {
        int choice = JOptionPane.showConfirmDialog(f, "是否保存已有内容到\n" + Constants.DEFAULT_FILE_PATH_NAME + Constants.TEXT_SUFFIX + "?", "notepad", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            saveFile();
        } else if (choice == JOptionPane.CANCEL_OPTION) {
            return;
        } else if (choice == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }
}


public void saveFile(){
    FileWriter writer = null;
    try {
        writer = new FileWriter(Constants.DEFAULT_FILE_PATH_NAME + Constants.TEXT_SUFFIX);
        writer.write(jta.getText().replaceAll("\n", "\r\n"));
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    System.exit(0);
}


}