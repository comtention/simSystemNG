package exception;

/**
 * Created by Comtention on 15/05/2016.
 */
public class MessageContentNotFoundInMessageException extends ArduinoNotFoundInArduinoManagerException {
    public MessageContentNotFoundInMessageException(){
        super();
    }

    public MessageContentNotFoundInMessageException(String message){
        super(message);
    }
}
