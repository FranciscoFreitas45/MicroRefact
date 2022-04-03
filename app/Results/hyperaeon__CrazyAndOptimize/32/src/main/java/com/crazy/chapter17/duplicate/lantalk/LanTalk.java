package com.crazy.chapter17.duplicate.lantalk;
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
public class LanTalk extends JFrame{

 private  DefaultListModel<UserInfo> listModel;

 private  JList<UserInfo> friendsList;

 private  DateFormat formatter;

 private  ImageIcon icon;

 private  String name;

 private  Color background;

 private  Color foreground;

public LanTalk() {
    super("局域网聊天");
    friendsList.setCellRenderer(new ImageCellRenderer());
    listModel.addElement(new UserInfo("all", "All people", null, -2000));
    friendsList.addMouseListener(new ChangeMousicListener());
    add(new JScrollPane(friendsList));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(2, 2, 160, 600);
}
@Override
public Component getListCellRendererComponent(JList<? extends UserInfo> list,UserInfo value,int index,boolean isSelected,boolean cellHasFocus){
    icon = new ImageIcon("ico/" + value.getIcon() + ".gif");
    name = value.getName();
    background = isSelected ? list.getSelectionBackground() : list.getBackground();
    foreground = isSelected ? list.getSelectionForeground() : list.getForeground();
    return this;
}


public int getUserNum(){
    return listModel.size();
}


public void removeUser(int pos){
    listModel.removeElementAt(pos);
}


public UserInfo getUserInfoBySocketAddress(SocketAddress address){
    for (int i = 0; i < getUserNum(); i++) {
        UserInfo user = getUser(i);
        if (user.getAddress() != null && user.getAddress().equals(address)) {
            return user;
        }
    }
    return null;
}


public Dimension getPreferredSize(){
    return new Dimension(60, 80);
}


public void addUser(UserInfo user){
    listModel.addElement(user);
}


public UserInfo getUser(int index){
    return listModel.elementAt(index);
}


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


public void main(String[] args){
    LanTalk lanTalk = new LanTalk();
    new LoginFrame(lanTalk, "Please input username and password");
}


public void processMsg(DatagramPacket singlePacket,boolean single){
    InetSocketAddress srcAddress = (InetSocketAddress) singlePacket.getSocketAddress();
    if (single) {
        srcAddress = new InetSocketAddress(srcAddress.getHostName(), srcAddress.getPort() - 1);
    }
    UserInfo srcUser = getUserInfoBySocketAddress(srcAddress);
    if (srcUser != null) {
        UserInfo alertUser = single ? srcUser : getUser(0);
        if (alertUser.getChatFrame() == null) {
            alertUser.setChatFrame(new ChatFrame(null, alertUser));
        }
        String tipMsg = single ? " Says to you : " : " Says to everyone";
        alertUser.getChatFrame().addString(srcUser.getName() + tipMsg + "..................." + formatter.format(new Date()) + ")\n" + new String(singlePacket.getData(), 0, singlePacket.getLength()) + "\n");
        if (!user.getChatFrame().isShowing()) {
            user.getChatFrame().setVisible(true);
        }
    }
}


}