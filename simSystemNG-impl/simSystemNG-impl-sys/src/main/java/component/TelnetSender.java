package component;

import Entity.Message;

public class TelnetSender extends Telnet{

    public TelnetSender(String ip, int port) {
        super(ip, port);
    }

    public TelnetSender(String ip, int port, String name) {
        super(ip, port, name);
    }

    //TODO test
    public void send(Message message){
        outChannel.println(message.toString());
        outChannel.flush();
    }
}
