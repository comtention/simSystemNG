package component;

import Entity.Message;
import starter.Starter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TelnetReader extends Telnet implements Runnable{


    public TelnetReader(String ip, int port) {
        super(ip, port);
    }

    public TelnetReader(String ip, int port, String name) {
        super(ip, port, name);
    }

    public void run() {
        BufferedReader ins = new BufferedReader(new InputStreamReader(telnetClient.getInputStream()));

        while(true){
            try {
                String readData = ins.readLine();
                if(readData != null) {
                    Message MessageFromTelnet = new Message();

                    /*if(readData.contains("=")){
                        readData = readData.replace("=0", "_OFF");
                        readData = readData.replace("=1", "_ON");
                        readData = readData.replace("=2", "_ON");
                    }*/
                    MessageFromTelnet.setContent(readData);
                    Starter.fromArduinoQueue.offer(MessageFromTelnet);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
