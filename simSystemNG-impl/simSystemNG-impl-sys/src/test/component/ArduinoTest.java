package component;

import component.Arduino;
import message.MessageMapper;
import org.junit.*;

import java.io.BufferedReader;

/**
 * Created by Comtention on 16/05/2016.
 */
public class ArduinoTest extends Arduino {

    @Test
    public void transformMessageForProsimTestComplex(){
        //Arrange
        this.setIdentifier(20);
        int[] message = {1,20,45};

        //Act
        String prosimMessage = transformMessageForProsim(message);

        //Assert
        System.out.println(prosimMessage);
        Assert.assertTrue(prosimMessage.equals("20" + MessageMapper.ARDUINO_MESSAGE_SEPARATOR + "1,20,45"));
    }

    @Test
    public void transformMessageForProsimTestSimple(){
        //Arrange
        this.setIdentifier(20);
        int[] message = {1};

        //Act
        String prosimMessage = transformMessageForProsim(message);

        //Assert
        System.out.println(prosimMessage);
        Assert.assertTrue(prosimMessage.equals("20" + MessageMapper.ARDUINO_MESSAGE_SEPARATOR + "1"));
    }
}