package com.crazy.chapter15.duplicate.execise.notepad.actionListener;
 import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import com.crazy.chapter15.duplicate.execise.notepad.action.ConfirmDialogAction;
public class OpenItemActionListener implements ActionListener{

 private  JFrame f;

 private  JTextArea jta;

public OpenItemActionListener(JFrame f, JTextArea jta) {
    this.f = f;
    this.jta = jta;
}
@Override
public void actionPerformed(ActionEvent e){
    FileDialog fileDialog = new FileDialog(f, "打开文件", FileDialog.LOAD);
    fileDialog.setVisible(true);
    // filter the file name.
    fileDialog.setFilenameFilter(new FilenameFilter() {

        @Override
        public boolean accept(File dir, String name) {
            if (dir.isFile() && dir.getName().endsWith(".txt")) {
                return true;
            }
            return false;
        }
    });
    if (!jta.getText().isEmpty()) {
        createDialog();
    }
    String fileName = fileDialog.getFile();
    File file = null;
    FileInputStream inputStream = null;
    try {
        /**
         * open file, put the file content into jta.
         */
        if (fileName != null && fileName.length() > 0) {
            file = new File(fileName);
            inputStream = new FileInputStream(file);
            FileChannel channel = inputStream.getChannel();
            MappedByteBuffer mappedBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            Charset charset = Charset.forName("GBK");
            CharsetDecoder decoder = charset.newDecoder();
            CharBuffer buffer = decoder.decode(mappedBuffer);
            // clear the content of jta.
            jta.setText("");
            jta.append(buffer.toString());
        }
    } catch (IOException exception) {
        System.out.println("open file failed");
        exception.printStackTrace();
    } finally {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}


public void createDialog(){
    new ConfirmDialogAction(f, jta).dialog();
}


@Override
public boolean accept(File dir,String name){
    if (dir.isFile() && dir.getName().endsWith(".txt")) {
        return true;
    }
    return false;
}


}