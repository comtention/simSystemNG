package consumers;

import Entity.Message;

import java.util.concurrent.LinkedBlockingQueue;

public class FromArduinosQueueConsumer extends Consumer{

    public FromArduinosQueueConsumer(int frequency, LinkedBlockingQueue<Message> queueToConsume) {
        super(frequency, queueToConsume);
    }

    public void run() {
        try {
            Message messageToConsume = queueToConsume.take();
            //TODO analyse message to know to which arduino we must send it
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(frequency);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
