package com.optimize.chapter2.duplicate.decorator;
 public class PacketHTMLHeaderCreator extends PacketDecorator{

public PacketHTMLHeaderCreator(IPacketCreator c) {
    super(c);
}
@Override
public String handleContent(){
    StringBuffer sb = new StringBuffer();
    sb.append("<html>");
    sb.append("<body>");
    sb.append(component.handleContent());
    sb.append("</body>");
    sb.append("</html>");
    return sb.toString();
}


}