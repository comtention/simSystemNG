package consumers;

import entity.GenericMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

public abstract class Consumer implements Runnable {
    protected String name = this.getClass().getName();
    protected boolean isActive = false;
    protected int frequency = 100;
    protected LinkedBlockingQueue<GenericMessage> queueToConsume;

    Logger logger = LoggerFactory.getLogger(Consumer.class);

    public Consumer(int frequency, LinkedBlockingQueue<GenericMessage> queueToConsume) {
        this.frequency = frequency;
        this.queueToConsume = queueToConsume;
    }

    public void stop(){
        isActive = false;
        logger.info("Consumer {} is just stopped.", name);
    }

    public void start(){
        isActive = true;
        logger.info("Consumer {} is just started.", name);
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
        logger.info("The frequency of consumer {} is just modified to {}.", name, frequency);
    }

    public boolean isActive(){
        return isActive;
    }
}
