package starter;

import component.ArduinoManager;
import component.TelnetReader;
import component.TelnetSender;
import consumers.Consumer;
import consumers.FromArduinosQueueConsumer;
import consumers.FromTelnetQueueConsumer;
import entity.ArduinoMessage;
import entity.GenericMessage;
import entity.ProsimMessage;
import message.MessageMapper;

import java.io.File;
import java.util.concurrent.LinkedBlockingQueue;

public class Starter {
    public static LinkedBlockingQueue<GenericMessage> fromArduinoQueue;
    public static LinkedBlockingQueue<GenericMessage> fromTelnetQueue;
    public static File configurationXMLFile = new File(Starter.class.getClassLoader().getResource("./configTest.xml").getFile());
    public static TelnetSender telnetSender;

    public static void main(String[] args) {
        String telnetIp = args[0];
        int telnetPort = Integer.valueOf(args[1]);

        //Read arduino and proSim messages from XML
        MessageMapper.fillArduinoProsimMap();
        MessageMapper.fillProsimArduinoMap();

        Starter starter = new Starter();
        //create the queue the telnet consumer will read and the arduinos will feed
        starter.fromArduinoQueue = new LinkedBlockingQueue<GenericMessage>();
        //create the queue the arduino consumer will read and the telnet will feed
        starter.fromTelnetQueue = new LinkedBlockingQueue<GenericMessage>();

        System.exit(0);

        // connect all the arduinos
        ArduinoManager.connectAllArduino();

        // connect telnet sender
        telnetSender = new TelnetSender(telnetIp, telnetPort);
        telnetSender.connect();

        // connect telnet reader
        TelnetReader telnetReader = new TelnetReader(telnetIp, telnetPort);
        telnetReader.connect();
        Thread telnetReaderThread = new Thread(telnetReader);
        telnetReaderThread.start();

        //Create and start FromTelnetQueueConsumer
        Consumer toArduinoQueueConsumer = new FromTelnetQueueConsumer(100, fromTelnetQueue);
        Thread toArduinoQueueConsumerThread = new Thread(toArduinoQueueConsumer);
        toArduinoQueueConsumerThread.start();
        toArduinoQueueConsumer.start();

        //Create and start FromArduinosQueueConsumer
        FromArduinosQueueConsumer toTelnetQueueConsumer = new FromArduinosQueueConsumer(100, fromArduinoQueue);
        Thread toTelnetQueueConsumerThread = new Thread(toTelnetQueueConsumer);
        toTelnetQueueConsumerThread.start();
        toTelnetQueueConsumer.start();
    }
}
