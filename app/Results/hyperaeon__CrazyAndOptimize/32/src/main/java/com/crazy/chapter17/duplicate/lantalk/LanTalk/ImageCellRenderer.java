package com.crazy.chapter17.duplicate.lantalk.LanTalk;
 import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import com.crazy.chapter13.LoginFrame;
public class ImageCellRenderer extends JPanelimplements ListCellRenderer<UserInfo>{

 private  ImageIcon icon;

 private  String name;

 private  Color background;

 private  Color foreground;


@Override
public Component getListCellRendererComponent(JList<? extends UserInfo> list,UserInfo value,int index,boolean isSelected,boolean cellHasFocus){
    icon = new ImageIcon("ico/" + value.getIcon() + ".gif");
    name = value.getName();
    background = isSelected ? list.getSelectionBackground() : list.getBackground();
    foreground = isSelected ? list.getSelectionForeground() : list.getForeground();
    return this;
}


public Dimension getPreferredSize(){
    return new Dimension(60, 80);
}


public void paintComponent(Graphics g){
    int imageWidth = icon.getImage().getWidth(null);
    int imageHeight = icon.getImage().getHeight(null);
    g.setColor(background);
    g.fillRect(0, 0, getWidth(), getHeight());
    g.setColor(foreground);
    g.drawImage(icon.getImage(), getWidth() / 2 - imageWidth / 2, 10, null);
    g.drawString(name, getWidth() / 2 - name.length() * 10, imageHeight + 30);
    g.setFont(new Font("SansSerif", Font.BOLD, 18));
}


}