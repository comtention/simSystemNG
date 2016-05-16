package consumers;

import component.Arduino;
import component.ArduinoManager;
import entity.ArduinoMessage;
import entity.GenericMessage;
import entity.ProsimMessage;
import exception.ArduinoIdentifierNotFoundInMessageException;
import exception.ArduinoNotFoundInArduinoManagerException;
import exception.MessageContentNotFoundInMessageException;
import message.MessageMapper;

import java.util.concurrent.LinkedBlockingQueue;

public class FromTelnetQueueConsumer extends Consumer {

    public FromTelnetQueueConsumer(int frequency, LinkedBlockingQueue<GenericMessage> queueToConsume) {
        super(frequency, queueToConsume);
    }

    public void run() {

        while (isActive) {
            ProsimMessage messageToConsume = null;
            String arduinoCompleteMessage = "";
            int arduinoIdentifier = -1;
            String contentMessage;
            try {
                messageToConsume = (ProsimMessage) queueToConsume.take();
                arduinoCompleteMessage = MessageMapper.prosimArduinoMap.get(messageToConsume.getContent());

                if(arduinoCompleteMessage == null){
                    logger.error("No corresponding arduino message for the prosim mesage: {}", messageToConsume.getContent());
                }

                arduinoIdentifier = MessageMapper.getArduinoIdentifier(arduinoCompleteMessage);
                contentMessage = MessageMapper.getArduinoMessageContent(arduinoCompleteMessage);
                Arduino arduino = ArduinoManager.getArduinoByIndentifier(arduinoIdentifier);

                ArduinoMessage messageToSent = new ArduinoMessage();
                messageToSent.setContent(contentMessage);
                arduino.sendMessageToArduino(messageToSent);

                Thread.sleep(frequency);

            } catch (InterruptedException e) {
                logger.error("", e);
            } catch (MessageContentNotFoundInMessageException e) {
                logger.error("No arduino identifier found in the message: {}", arduinoCompleteMessage);
            } catch (ArduinoIdentifierNotFoundInMessageException e) {
                logger.error("No arduino identifier found in the message: {}", arduinoCompleteMessage);
            } catch (ArduinoNotFoundInArduinoManagerException e) {
                logger.error("No arduino found in the ArduinoManager for identifier: {}", arduinoIdentifier);
            }
        }
    }
}
