package consumers;

import Entity.Message;

import java.util.concurrent.LinkedBlockingQueue;

public class FromTelnetQueueConsumer extends Consumer{

    public FromTelnetQueueConsumer(int frequency, LinkedBlockingQueue<Message> queueToConsume) {
        super(frequency, queueToConsume);
    }

    public void run() {
        if(isActive == true){
            //TODO
        }

        try {
            Thread.sleep(frequency);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
