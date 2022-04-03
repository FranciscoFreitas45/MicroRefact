package com.crazy.chapter15.duplicate.execise.notepad.actionListener;
 import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import com.crazy.chapter15.duplicate.execise.notepad.action.ConfirmDialogAction;
public class ExitItemActionListener implements ActionListener{

 private  JTextArea jta;

 private  JFrame f;

public ExitItemActionListener(JFrame f, JTextArea jta) {
    this.jta = jta;
    this.f = f;
}
@Override
public void actionPerformed(ActionEvent e){
    if (!jta.getText().isEmpty()) {
        new ConfirmDialogAction(f, jta).dialog();
    } else {
        System.exit(0);
    }
}


}