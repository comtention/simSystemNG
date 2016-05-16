package consumers;

import component.Arduino;
import entity.ArduinoMessage;
import entity.GenericMessage;
import entity.ProsimMessage;
import message.MessageMapper;
import starter.Starter;

import java.util.concurrent.LinkedBlockingQueue;

public class FromArduinosQueueConsumer extends Consumer {

    public FromArduinosQueueConsumer(int frequency, LinkedBlockingQueue<GenericMessage> queueToConsume) {
        super(frequency, queueToConsume);
    }

    public void run() {
        while (isActive) {
            try {
                ArduinoMessage messageToConsume = (ArduinoMessage) queueToConsume.take();
                String prosimMessage = MessageMapper.arduinoProsimMap.get(messageToConsume.getContent());

                if(prosimMessage == null){
                    logger.error("No corresponding prosim message for the arduino mesage: {}", prosimMessage);
                }

                ProsimMessage messageToSent = new ProsimMessage();
                messageToSent.setContent(prosimMessage);
                Starter.telnetSender.send(messageToSent);

                Thread.sleep(frequency);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}