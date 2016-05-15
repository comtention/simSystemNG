package component;

import org.apache.commons.net.telnet.TelnetClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;


public abstract class Telnet {
    protected String ip;
    protected int port;
    protected String name;
    protected TelnetClient telnetClient;
    protected PrintWriter outChannel;

    private static Logger logger = LoggerFactory.getLogger(Telnet.class);

    public Telnet(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public Telnet(String ip, int port, String name) {
        this.ip = ip;
        this.port = port;
        this.name = name;
    }

    public void connect(){
        if(telnetClient == null) {
            telnetClient = new TelnetClient();
            try {
                telnetClient.connect(ip, port);
                logger.info("The telnet client is now connected to {}:{}.", ip, port);
                outChannel = new PrintWriter(telnetClient.getOutputStream());
            } catch (IOException e) {
                logger.error("Impossible to connect Prosim to {}:{}.", ip, port);
                java.lang.System.exit(0);
            }
        }
        else{
            logger.info("The telnet client is already connected to {}:{}.", ip, port);
        }
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
