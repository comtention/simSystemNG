package component;

import entity.ArduinoMessage;
import gnu.io.*;
import message.CustomMessage;
import message.MessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import starter.Starter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Arduino implements Runnable{
    SerialPort serialPort;
    String port;
    int identifier = -1;
    LinkedBlockingQueue<ArduinoMessage> messageToSendToArduinoQueue = new LinkedBlockingQueue<ArduinoMessage>();
    OutputStream output;
    BufferedReader input;

    private static Logger logger = LoggerFactory.getLogger(Arduino.class);

    public Arduino() {
    }

    public Arduino(String port) {
        this.port = port;
    }

    public void connect(){
        if(serialPort == null) {
            CommPortIdentifier id;
            try {
                id = CommPortIdentifier.getPortIdentifier(serialPort);
                SerialPort serialPort = id.open("test", 2000);
                serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
                output = serialPort.getOutputStream();

                Thread.sleep(2000);
                logger.info("Arduino {} is now connected.", port);
            } catch (NoSuchPortException e) {
                e.printStackTrace();
            } catch (PortInUseException e) {
                e.printStackTrace();
            } catch (UnsupportedCommOperationException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            logger.info("Arduino {} is now connected.", port);
        }else{
            logger.info("Arduino {} is already connected.", port);
        }
    }

    public void disconnect(){
        try {
            output.flush();
            serialPort.close();
            logger.info("Arduino {} is now disconnected.", port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToArduino(ArduinoMessage message){
        messageToSendToArduinoQueue.offer(message);
    }

    /**
     * Retrieve arduino identifier and listening message
     */
    public void run() {
        while(true) {
            if (identifier < 0) {
                identifyArduino();
            }
            try {
                //Consume message
                logger.debug("{} messages in messageToSendToArduinoQueue.", messageToSendToArduinoQueue.size());
                if(messageToSendToArduinoQueue.size() > 0) {
                    ArduinoMessage messageToSendToArduino = messageToSendToArduinoQueue.take();
                    logger.debug("Arduino {} will consume message : {}", identifier,  java.util.Arrays.toString(messageToSendToArduino.getContent()));
                    sendMessageWithStartAndEnd(messageToSendToArduino.getContent());
                }

                //Read message
                int[] message = readMessage();
                if(message.length > 0){
                    ArduinoMessage arduinoMessage = new ArduinoMessage();
                    arduinoMessage.setContent(transformMessageForProsim(message));
                    logger.debug("Arduino {} has received message : {}", identifier,  java.util.Arrays.toString(message));
                    Starter.fromArduinoQueue.add(arduinoMessage);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Transform int[] to comma separated string
     * Add Arduino identifier
     */
    String transformMessageForProsim(int[] byteString){
        String message = "";
        message += identifier + MessageMapper.ARDUINO_MESSAGE_SEPARATOR;

        if(byteString.length == 1){
            message += Integer.toString(byteString[0]);
        }else{
            for(int i = 0; i < byteString.length; i++){
                message += byteString[i];
                if(i < byteString.length - 1){
                    message += ",";
                }
            }
        }
        return message;
    }

    /**
     * loop until arduino is identified
     */
    private void identifyArduino(){
        while(identifier < 0){
            //Ask for identification
            sendMessageWithStartAndEnd(new int[]{CustomMessage.IDENTIFIER_MSG});

            int[] message = readMessage();
            if(message[0] == CustomMessage.IDENTIFIER_MSG){
                logger.info("We receive an identifier message");

                if(message.length == 2){
                    int receivedIndentfier = message[1];
                    logger.info("The identifier is: {}", receivedIndentfier);
                    this.identifier = receivedIndentfier;
                    ArduinoManager.arduinoList.add(this);
                }else{
                    logger.error("The identifier message has not identifier number");
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    int[] readMessage(){
        boolean isStartMessageAlreadyRead = false; // true if startByte has been already read
        boolean isEndMessageAlreadyRead = false; // true if startByte has been already read
        List<Integer> message = new ArrayList<>();
        while(isEndMessageAlreadyRead == false) {
            try {
                int byt;
                if (input.ready()) {
                    byt = input.read();

                    if (byt == CustomMessage.START_MSG) {
                        isStartMessageAlreadyRead = true;
                    }else if (byt == CustomMessage.END_MSG) {
                        if(isStartMessageAlreadyRead == true){
                            isEndMessageAlreadyRead = true;
                        }else{ //if we receive stopMessage without startMessage
                            logger.error("Stop message read without start message. Already read messages are: {}", java.util.Arrays.toString(message.toArray()));
                            message.clear();
                            logger.error("The already read content is cleaned.");
                        }
                    }else{ //other message than start/end messages
                        if(isStartMessageAlreadyRead == true){
                            message.add(byt);
                        }else { //if we receive a message without startMessage
                            logger.error("Message read without start message. This message: {} will be lost.", byt);
                        }
                    }
                }
                else{
                    return new int[]{};
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int[] byteArrayMessage = new int[message.size()];
        for(int i = 0; i < message.size(); i++){
            byteArrayMessage[i] = message.get(i);
        }
        return byteArrayMessage;
    }

    public void sendMessageWithStartAndEnd(int[] message){
        try {
            output.write(CustomMessage.START_MSG);
            for(int byt: message){
                output.write(byt);
            }
            output.write(CustomMessage.END_MSG);

            output.flush();

        } catch (IOException e) {
            e.printStackTrace();//TODO
        }
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
}
