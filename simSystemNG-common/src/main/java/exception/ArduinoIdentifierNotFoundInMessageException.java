package exception;

/**
 * Created by Comtention on 15/05/2016.
 */
public class ArduinoIdentifierNotFoundInMessageException extends ArduinoNotFoundInArduinoManagerException {
    public ArduinoIdentifierNotFoundInMessageException(){
        super();
    }

    public ArduinoIdentifierNotFoundInMessageException(String message){
        super(message);
    }
}
