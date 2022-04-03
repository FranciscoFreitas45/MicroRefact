package com.crazy.chapter17.duplicate.lantalk;
 import java.net.SocketAddress;
public class UserInfo {

 private  String icon;

 private  String name;

 private  SocketAddress address;

 private  int lost;

 private  ChatFrame chatFrame;

public UserInfo() {
}public UserInfo(String icon, String name, SocketAddress address, int lost) {
    this.icon = icon;
    this.name = name;
    this.address = address;
    this.lost = lost;
}
public void setIcon(String icon){
    this.icon = icon;
}


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public ChatFrame getChatFrame(){
    return chatFrame;
}


public void setChatFrame(ChatFrame chatFrame){
    this.chatFrame = chatFrame;
}


public void setAddress(SocketAddress address){
    this.address = address;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + ((chatFrame == null) ? 0 : chatFrame.hashCode());
    result = prime * result + ((icon == null) ? 0 : icon.hashCode());
    result = prime * result + lost;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    UserInfo other = (UserInfo) obj;
    if (address == null) {
        if (other.address != null)
            return false;
    } else if (!address.equals(other.address))
        return false;
    if (chatFrame == null) {
        if (other.chatFrame != null)
            return false;
    } else if (!chatFrame.equals(other.chatFrame))
        return false;
    if (icon == null) {
        if (other.icon != null)
            return false;
    } else if (!icon.equals(other.icon))
        return false;
    if (lost != other.lost)
        return false;
    if (name == null) {
        if (other.name != null)
            return false;
    } else if (!name.equals(other.name))
        return false;
    return true;
}


public int getLost(){
    return lost;
}


public SocketAddress getAddress(){
    return address;
}


public void setLost(int lost){
    this.lost = lost;
}


public String getIcon(){
    return icon;
}


}