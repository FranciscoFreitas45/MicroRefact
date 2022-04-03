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
public class ChangeMusicListener extends MouseAdapter{


public void mouseClicked(MouseEvent e){
    if (e.getClickCount() >= 2) {
        UserInfo user = friendsList.getSelectedValue();
        if (user.getChatFrame() != null) {
            user.setChatFrame(new ChatFrame(null, user));
        }
        if (!user.getChatFrame().isShowing()) {
            user.getChatFrame().setVisible(true);
        }
    }
}


}