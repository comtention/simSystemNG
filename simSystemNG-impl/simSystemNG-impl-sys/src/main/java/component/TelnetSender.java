package component;

import entity.ProsimMessage;

public class TelnetSender extends Telnet{

    public TelnetSender(String ip, int port) {
        super(ip, port);
    }

    public TelnetSender(String ip, int port, String name) {
        super(ip, port, name);
    }

    public void send(ProsimMessage message){
        outChannel.println(message.toString());
        outChannel.flush();
    }
}
