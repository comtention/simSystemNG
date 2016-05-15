package component;

import gnu.io.NRSerialPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Entity.Message;

import java.util.concurrent.LinkedBlockingQueue;

public class Arduino implements Runnable{
    private NRSerialPort serialPort;
    private String port;
    private String identifier;
    private LinkedBlockingQueue<Message> messageToSendToArduinoQueue = new LinkedBlockingQueue<Message>();
    public LinkedBlockingQueue<Message> messageToSendToTelnetQueue;

    private static Logger logger = LoggerFactory.getLogger(Arduino.class);

    public Arduino(String port, LinkedBlockingQueue<Message> messageToSendToTelnetQueue) {
        this.port = port;
        this.messageToSendToTelnetQueue = messageToSendToTelnetQueue;
    }

    public void connect(){
        if(serialPort == null) {
            serialPort = new NRSerialPort(port, 9600);
            serialPort.connect();
            logger.info("Arduino {} is now connected.", port);
        }else{
            logger.info("Arduino {} is already connected.", port);
        }
    }

    public void disconnect(){
        serialPort.disconnect();
        logger.info("Arduino {} is now disconnected.", port);
    }

    public void sendMessageToArduino(Message message){
        messageToSendToArduinoQueue.offer(message);
    }

    /**
     * Retrieve arduino identifier and listening message
     */
    public void run() {
        identifyArduino();
        //TODO listening message

        //TODO consume messageToSendToArduinoQueue
    }

    /**
     * loop until arduino is identified
     */
    private void identifyArduino(){
        while(identifier == null){
            String receivedIndentfier = "";
            //TODO wait identification

            this. identifier = receivedIndentfier;
            ArduinoManager.arduinoList.add(this);
        }
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
