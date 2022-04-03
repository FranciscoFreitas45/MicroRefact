package com.optimize.chapter2.decorator;
 public class PacketHTMLHeaderCreator extends PacketDecorator{

public PacketHTMLHeaderCreator(IPacketCreator c) {
    super(c);
}
@Override
public String handleContent(){
    StringBuilder sb = new StringBuilder();
    sb.append("<html>");
    sb.append("<body>");
    sb.append(component.handleContent());
    sb.append("</body>");
    sb.append("</html>\n");
    return sb.toString();
}


}