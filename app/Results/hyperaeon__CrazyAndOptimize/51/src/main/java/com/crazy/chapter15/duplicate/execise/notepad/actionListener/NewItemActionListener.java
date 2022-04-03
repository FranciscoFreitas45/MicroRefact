package com.crazy.chapter15.duplicate.execise.notepad.actionListener;
 import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import com.crazy.chapter15.duplicate.execise.notepad.action.ConfirmDialogAction;
public class NewItemActionListener implements ActionListener{

 private  JFrame f;

 private  JTextArea jta;

public NewItemActionListener(JFrame f, JTextArea jta) {
    this.f = f;
    this.jta = jta;
}
public JFrame getF(){
    return f;
}


@Override
public void actionPerformed(ActionEvent e){
    if (!jta.getText().isEmpty()) {
        new ConfirmDialogAction(f, jta).dialog();
    }
}


public void setF(JFrame f){
    this.f = f;
}


public void setJta(JTextArea jta){
    this.jta = jta;
}


public JTextArea getJta(){
    return jta;
}


}